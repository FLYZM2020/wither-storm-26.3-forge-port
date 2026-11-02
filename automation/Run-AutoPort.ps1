#requires -Version 7.2
[CmdletBinding()]
param([Parameter(Mandatory = $true)][string]$RepositoryPath, [switch]$PreflightOnly)
$ErrorActionPreference = 'Stop'
$PSNativeCommandUseErrorActionPreference = $false
if (-not $IsWindows) { throw 'This runner requires Windows and PowerShell 7.' }
$root = (Resolve-Path -LiteralPath $RepositoryPath).Path
$expectedRepository = 'FLYZM2020/wither-storm-26.3-forge-port'
$hash = [Convert]::ToHexString([Security.Cryptography.SHA256]::HashData([Text.Encoding]::UTF8.GetBytes($root.ToLowerInvariant()))).Substring(0, 16)
$stateDir = Join-Path $env:LOCALAPPDATA "WitherStormPortAuto/$hash"
New-Item -ItemType Directory -Path $stateDir -Force | Out-Null
$logFile = Join-Path $stateDir 'runner.log'
$stateFile = Join-Path $stateDir 'state.json'
$lock = $null
function Log([string]$message) {
    $line = '{0} {1}' -f (Get-Date -Format 'yyyy-MM-dd HH:mm:ss zzz'), $message
    Add-Content -LiteralPath $logFile -Value $line -Encoding utf8
    Write-Host $line
}
function Invoke-GitCommand([string]$dir, [string[]]$arguments) {
    $output = @(& git -C $dir @arguments 2>&1)
    if ($LASTEXITCODE -ne 0) { throw "Git failed ($($arguments[0])); see local configuration and connectivity." }
    return $output
}
function Require-Native([string]$name) {
    if (-not (Get-Command $name -ErrorAction SilentlyContinue)) { throw "Missing required executable: $name" }
}
function Get-PRs([string]$branch) {
    $data = & gh pr list --repo $expectedRepository --state all --head $branch --json number,state,isDraft,baseRefName,headRefOid 2>&1
    if ($LASTEXITCODE -ne 0) { throw 'Cannot query pull requests; check GitHub login and access.' }
    return @($data | ConvertFrom-Json)
}
try {
    try {
        $lock = [IO.File]::Open((Join-Path $stateDir 'runner.lock'), [IO.FileMode]::OpenOrCreate, [IO.FileAccess]::ReadWrite, [IO.FileShare]::None)
    } catch { Write-Host 'Another run is active; skipping.'; exit 0 }
    Log 'Starting preflight.'
    foreach ($name in @('git', 'gh', 'codex')) { Require-Native $name }
    if (-not $env:JAVA_HOME -or -not (Test-Path (Join-Path $env:JAVA_HOME 'bin/java.exe'))) { throw 'Set JAVA_HOME to a JDK 25 installation.' }
    if (-not (Test-Path (Join-Path $env:JAVA_HOME 'bin/javac.exe'))) { throw 'JAVA_HOME must point to a JDK, not only a Java runtime.' }
    $javaVersion = @(& (Join-Path $env:JAVA_HOME 'bin/java.exe') -version 2>&1) -join ' '
    if ($LASTEXITCODE -ne 0 -or $javaVersion -notmatch 'version "25(?:[.\-+]|\")') { throw 'JAVA_HOME must point to JDK 25.' }
    $top = (Invoke-GitCommand $root @('rev-parse', '--show-toplevel') | Select-Object -First 1)
    if (([IO.Path]::GetFullPath($top)).TrimEnd('\','/') -ine $root.TrimEnd('\','/')) { throw 'RepositoryPath must be the Git repository root.' }
    if (@(Invoke-GitCommand $root @('status', '--porcelain', '--untracked-files=all')).Count -gt 0) { throw 'The main checkout is dirty; preserve or commit changes before automation.' }
    $null = Invoke-GitCommand $root @('var', 'GIT_AUTHOR_IDENT')
    $null = & codex login status 2>&1
    if ($LASTEXITCODE -ne 0) { throw 'Codex is not logged in. Run codex login interactively.' }
    $info = & gh repo view $expectedRepository --json nameWithOwner,isPrivate 2>&1
    if ($LASTEXITCODE -ne 0) { throw 'Cannot access the expected GitHub repository with gh.' }
    $info = $info | ConvertFrom-Json
    if ($info.nameWithOwner -ine $expectedRepository -or -not $info.isPrivate) { throw 'Expected repository is unavailable or is not private.' }
    $remote = (Invoke-GitCommand $root @('remote', 'get-url', 'origin') | Select-Object -First 1)
    if ($remote -notmatch '(?i)(github\.com[:/])FLYZM2020/wither-storm-26\.3-forge-port(?:\.git)?/?$') { throw 'origin does not identify the expected private repository.' }
    $null = Invoke-GitCommand $root @('fetch', 'origin', 'main')
    if ($PreflightOnly) { Log 'Preflight succeeded; no agent, worktree, commit, or PR was created.'; exit 0 }

    $state = $null
    if (Test-Path -LiteralPath $stateFile) { $state = Get-Content -LiteralPath $stateFile -Raw | ConvertFrom-Json }
    if ($state) {
        if ($state.repository -ine $expectedRepository -or $state.root -ine $root -or $state.branch -notmatch '^codex-auto/[a-z0-9-]+$') { throw 'Invalid automation state; inspect state.json before continuing.' }
        $branch = [string]$state.branch
        $worktree = [string]$state.worktree
        $expectedTree = Join-Path $stateDir 'worktree'
        if ($worktree -ine $expectedTree -or -not (Test-Path -LiteralPath $worktree)) { throw 'Automation worktree is missing or unexpected; inspect state.json.' }
        if ((Invoke-GitCommand $worktree @('branch', '--show-current') | Select-Object -First 1) -cne $branch) { throw 'Automation worktree is on an unexpected branch.' }
        $prs = @(Get-PRs $branch)
        if ($prs.Count -gt 1) { throw 'More than one PR was found for the automation branch.' }
        if ($prs.Count -eq 1 -and $prs[0].state -eq 'MERGED') {
            if (@(Invoke-GitCommand $worktree @('status', '--porcelain', '--untracked-files=all')).Count -gt 0) { throw 'Merged PR has uncommitted work; preserve it before restarting.' }
            $null = Invoke-GitCommand $root @('worktree', 'remove', $worktree)
            Remove-Item -LiteralPath $stateFile
            $state = $null
        } elseif ($prs.Count -eq 1 -and ($prs[0].state -ne 'OPEN' -or -not $prs[0].isDraft -or $prs[0].baseRefName -cne 'main')) {
            throw 'The automation PR is closed, no longer a draft, or has a different base; manual review is required.'
        }
    }
    if (-not $state) {
        $branch = 'codex-auto/' + (Get-Date -AsUTC -Format 'yyyyMMdd-HHmmss') + '-' + [guid]::NewGuid().ToString('N').Substring(0, 6)
        $worktree = Join-Path $stateDir 'worktree'
        if (Test-Path -LiteralPath $worktree) { throw 'An unrecorded worktree already exists; preserve it and inspect manually.' }
        $null = Invoke-GitCommand $root @('worktree', 'add', '-b', $branch, $worktree, 'origin/main')
        @{repository=$expectedRepository; root=$root; branch=$branch; worktree=$worktree} | ConvertTo-Json | Set-Content -LiteralPath $stateFile -Encoding utf8
    }
    $headBefore = (Invoke-GitCommand $worktree @('rev-parse', 'HEAD') | Select-Object -First 1)
    $null = & git -C $worktree merge-base --is-ancestor origin/main HEAD 2>&1
    if ($LASTEXITCODE -ne 0) { throw 'main has advanced beyond the automation branch; update the branch manually before continuing.' }
    $remoteHead = @(Invoke-GitCommand $root @('ls-remote', '--heads', 'origin', "refs/heads/$branch"))
    if ($remoteHead.Count -gt 0 -and ($remoteHead[0] -split '\s+')[0] -cne $headBefore) { throw 'Remote automation branch changed; refusing to overwrite it.' }
    if ($prs -and $prs.Count -eq 1 -and $prs[0].state -eq 'OPEN' -and $prs[0].headRefOid -cne $headBefore) { throw 'PR head changed; manual review is required.' }
    $promptPath = Join-Path $worktree 'automation/agent-prompt.md'
    if (-not (Test-Path -LiteralPath $promptPath)) { throw 'Agent prompt is missing in the worktree.' }
    $lastMessage = Join-Path $stateDir 'last-agent-message.txt'
    Log "Running Codex in dedicated worktree on $branch."
    # Codex can use the worktree, but cannot authorize interactive prompts or publish.
    Get-Content -LiteralPath $promptPath -Raw | & codex -a never exec --sandbox workspace-write --ephemeral -C $worktree --output-last-message $lastMessage - *>> $logFile
    if ($LASTEXITCODE -ne 0) { throw 'Codex did not finish successfully; local changes were preserved. Check runner.log for quota, login, or execution details.' }
    if ((Invoke-GitCommand $worktree @('rev-parse', 'HEAD') | Select-Object -First 1) -cne $headBefore) { throw 'Codex changed the Git HEAD; refusing to publish automatically.' }
    $changed = @((Invoke-GitCommand $worktree @('-c','core.quotepath=false','diff','--name-only','HEAD')) + (Invoke-GitCommand $worktree @('-c','core.quotepath=false','ls-files','--others','--exclude-standard')) | Where-Object { $_ })
    foreach ($path in $changed) {
        if ($path -notmatch '^(forge-port/|README\.md$|进度\.md$)') { throw "Changes outside the allowed project paths were preserved: $path" }
    }
    if ($changed.Count -gt 0) {
        Log 'Building the development JAR; in-game verification is left to the user.'
        Push-Location (Join-Path $worktree 'forge-port')
        try { & .\gradlew.bat jar --no-daemon --max-workers=2 *>> $logFile; if ($LASTEXITCODE -ne 0) { throw 'Gradle build failed; local changes were preserved.' } }
        finally { Pop-Location }
        $jar = Join-Path $worktree 'forge-port/build/libs/witherstormmod-0.0.1-dev.jar'
        if (-not (Test-Path -LiteralPath $jar)) { throw 'Build did not produce the expected development JAR.' }
        Copy-Item -LiteralPath $jar -Destination (Join-Path $worktree 'downloads/witherstormmod-0.0.1-dev.jar') -Force
        $null = Invoke-GitCommand $worktree @('add', '--', 'forge-port', 'README.md', '进度.md', 'downloads/witherstormmod-0.0.1-dev.jar')
        $null = Invoke-GitCommand $worktree @('commit', '-m', 'Continue Forge port development')
    }
    $newHead = (Invoke-GitCommand $worktree @('rev-parse', 'HEAD') | Select-Object -First 1)
    $base = (Invoke-GitCommand $worktree @('merge-base', 'HEAD', 'origin/main') | Select-Object -First 1)
    if ($newHead -cne $base) {
        # A regular push rejects concurrent changes; never force push.
        $null = Invoke-GitCommand $worktree @('push', 'origin', "HEAD:refs/heads/$branch")
        $prs = @(Get-PRs $branch)
        if ($prs.Count -eq 0) {
            $bodyFile = Join-Path $stateDir 'pr-body.md'
            $body = @'
自动移植工作：详细改动和未完成事项见仓库中的进度.md。

已构建开发版 JAR；未进行游戏内验证。请在合并前检查代码并自行验证游戏行为。
'@
            Set-Content -LiteralPath $bodyFile -Value $body -Encoding utf8
            $null = & gh pr create --repo $expectedRepository --head $branch --base main --draft --title '自动移植：Forge 26.3' --body-file $bodyFile 2>&1
            if ($LASTEXITCODE -ne 0) { throw 'Branch was pushed, but draft PR creation failed; next run will retry.' }
        }
        Log 'Changes pushed to the private automation branch and draft PR.'
    } else { Log 'No new project changes to publish.' }
} catch {
    Log ("Stopped: " + $_.Exception.Message)
    exit 1
} finally {
    if ($lock) { $lock.Dispose() }
}
