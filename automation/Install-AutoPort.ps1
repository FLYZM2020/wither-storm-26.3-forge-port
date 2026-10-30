#requires -Version 7.2
[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)][string]$RepositoryPath,
    [string]$TaskName = 'WitherStormPortAuto'
)
$ErrorActionPreference = 'Stop'
if (-not $IsWindows) { throw 'This installer requires Windows and PowerShell 7.' }
$root = (Resolve-Path -LiteralPath $RepositoryPath).Path
$runner = Join-Path $root 'automation/Run-AutoPort.ps1'
if (-not (Test-Path -LiteralPath $runner)) { throw 'Clone the complete repository and use its root directory.' }
$pwsh = (Get-Process -Id $PID).Path
# Task Scheduler requires one argument string. Reject quotes that would break its quoting.
if ($root.Contains('"') -or $runner.Contains('"')) { throw 'Repository path cannot contain a double quote.' }
$argument = '-NoLogo -NoProfile -NonInteractive -ExecutionPolicy Bypass -File "{0}" -RepositoryPath "{1}"' -f $runner, $root
$action = New-ScheduledTaskAction -Execute $pwsh -Argument $argument -WorkingDirectory $root
$hourly = New-ScheduledTaskTrigger -Once -At (Get-Date).AddMinutes(2) -RepetitionInterval (New-TimeSpan -Hours 1) -RepetitionDuration (New-TimeSpan -Days 365)
$logon = New-ScheduledTaskTrigger -AtLogOn -User "$env:USERDOMAIN\$env:USERNAME"
$settings = New-ScheduledTaskSettingsSet -MultipleInstances IgnoreNew -StartWhenAvailable -ExecutionTimeLimit ([TimeSpan]::Zero)
$principal = New-ScheduledTaskPrincipal -UserId "$env:USERDOMAIN\$env:USERNAME" -LogonType Interactive -RunLevel Limited
Register-ScheduledTask -TaskName $TaskName -Action $action -Trigger @($hourly, $logon) -Settings $settings -Principal $principal -Force | Out-Null
Write-Host "Installed scheduled task '$TaskName'. It runs only while this Windows user is logged on."
Write-Host 'Run the runner manually first and check its log before relying on the schedule.'
