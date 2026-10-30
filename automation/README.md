# Windows 本机自动接续

此方案使用本机 Codex 登录和 Windows 任务计划程序，处理私有仓库中的移植任务。自动运行需要 Windows 用户保持登录、电脑处于开机且非睡眠状态、网络可用。额度不足时本次运行会失败并保留改动；下次触发时会再次尝试，不能保证额度何时恢复或登录永远有效。

脚本在 `%LOCALAPPDATA%\WitherStormPortAuto\<仓库路径哈希>\worktree` 创建独立工作树；原始仓库必须干净。每次成功构建后提交到 `codex-auto/*` 分支，创建或更新指向 `main` 的草稿 PR；只推送，不自动合并或强制推送。脚本不会自动运行游戏或验证玩法。Codex 执行受本机版本的 `workspace-write` 沙箱约束，具体隔离和网络能力取决于客户端和 Windows 配置；不要把脚本当作完整的安全隔离。

## 准备

1. 先将包含 `automation/` 的设置 PR 合并到私有仓库 `main`，然后在 Windows 使用 GitHub Desktop **Fetch origin → Pull origin** 更新整个仓库。
2. 安装 PowerShell 7.2 或更高版本、Git、GitHub CLI、Codex CLI 和 JDK 25，并确保当前 Windows 用户的 `PATH` 能找到 `git`、`gh` 和 `codex`。Codex 在 Windows 上的可用性及无人值守能力取决于已安装版本和账户。
3. 在 PowerShell 7 中执行 `gh auth login`、`gh auth setup-git` 和 `codex login`，确保账号有私有仓库读写及创建 PR 的权限。不要将密码、令牌或本机凭据写进仓库或日志。
4. 将用户环境变量 `JAVA_HOME` 设置为 JDK 25 安装目录，重新打开 PowerShell 7。确认 `& "$env:JAVA_HOME\bin\java.exe" -version` 输出版本 25，且目录中存在 `bin\javac.exe`。Gradle Wrapper 首次运行需要网络下载依赖。Git 需要已配置 `user.name` 和 `user.email`。

## 先检查，再安装

在 PowerShell 7 进入克隆后的仓库根目录，执行：

```powershell
$repo = (Get-Location).Path
& .\automation\Run-AutoPort.ps1 -RepositoryPath $repo -PreflightOnly
```

检查成功后，先手动运行一次，确认本机 Codex 确实可以非交互执行、构建和创建草稿 PR：

```powershell
& .\automation\Run-AutoPort.ps1 -RepositoryPath $repo
```

手动运行可能持续较长时间，期间不要关闭窗口。确认草稿 PR 及其内容后再安装计划任务：

```powershell
& .\automation\Install-AutoPort.ps1 -RepositoryPath $repo
```

安装器为当前登录用户设置登录时运行、每小时尝试一次，以及错过运行后的补运行；同一时间只允许一个实例。小时重复触发器有效期为 365 天，到期前重新运行安装器。该任务不唤醒睡眠电脑，不会在用户退出登录时运行。

## 日志与恢复

查看日志位置：

```powershell
Get-ChildItem "$env:LOCALAPPDATA\WitherStormPortAuto" -Recurse -Filter runner.log |
  Select-Object -ExpandProperty FullName
```

`runner.log` 包含 Codex 和构建输出；检查后再分享，避免泄露本机路径或敏感信息。`last-agent-message.txt`、`state.json` 和工作树位于同一目录。

- 额度、登录、构建或网络出错：检查日志并修复问题。未提交的工作留在专用工作树，不会被脚本清除；下一次运行可接着处理。
- 若 `main` 前进、自动分支远端被修改、PR 被关闭或改为非草稿，脚本会停止，避免覆盖他人工作。先检查工作树与 PR；需要更新分支时由你手动处理，完成后重试。**不要删除有未提交更改的工作树。**
- PR 合并且工作树干净后，下次运行移除该工作树，并基于最新 `origin/main` 创建新分支。如果删除了工作树或状态文件，先检查并保存原工作；脚本不会自动恢复未知工作树。
- 删除任务：`Unregister-ScheduledTask -TaskName WitherStormPortAuto -Confirm:$false`。删除任务不会删除工作树、分支或 PR。

源码和 JAR 是开发版本；构建成功不代表完整移植或游戏内功能正确。
