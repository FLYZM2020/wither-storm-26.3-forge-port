# 凋灵风暴 Forge 26.3 移植工程

这是个人使用的未完成开发项目。目标为 Minecraft Java 26.3、Forge 66.1.0 和 Java 25。当前 JAR 是未经游戏内验证的开发版，并非完整模组。

- `forge-port/`：当前 Forge 移植工程。
- `downloads/`：当前开发版 JAR。
- `port-candidates/`、`reference/`、`recovered-source/`：旧版候选及参考源码。
- `witherstormmod-1.20.1-4.2.1-all.jar`：旧版参考文件，不适用于 26.3。
- `进度.md`：已完成内容及未完成项。

Windows 可通过仓库页面的 **Code → Download ZIP** 下载源码；只需要开发版 JAR 时可进入 `downloads/` 下载。构建时在 `forge-port` 目录运行 `gradlew.bat build`，并准备 Java 25。

- `automation/README.md`：Windows 定时接续、独立工作树与草稿 PR 的安装和恢复步骤。
