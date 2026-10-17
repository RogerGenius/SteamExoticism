# SteamExoticism 桌面壳（Tauri 2）

当前为**骨架**：已配置 Tauri 2 工程、shell / single-instance 插件与窗口参数，但尚未实现「启动后端 → 解析端口 → 加载页面」的完整流程（M5 里程碑）。

## 前置条件

- Rust 工具链（rustup + MSVC Build Tools），本机尚未安装。
- Node.js 20+（用于 `npm install` 拉取 `@tauri-apps/cli`）。

## M5 实施步骤（待办）

1. 安装 Rust：<https://rustup.rs/>（Windows 需 Visual Studio Build Tools）。
2. 生成应用图标：在 `desktop/` 下执行 `npx @tauri-apps/cli icon`（默认读取 `../frontend` 下无图标时需先准备一张 1024x1024 的 png）。
3. 用 `jlink` 裁剪最小 JRE（模块清单参考 Spring Boot 官方文档），与 `app.jar` 一并放入 `src-tauri/resources/`。
4. 在 `src-tauri/src/lib.rs` 中实现后端进程 spawn、端口解析、窗口加载与退出清理。
5. 构建安装包：`npm run tauri build`（产物为 NSIS/MSI 安装包）。

## 常用命令

```bash
cd desktop
npm install           # 拉取 @tauri-apps/cli
npm run tauri dev     # 开发模式（自动启动 frontend dev server）
npm run tauri build   # 打包安装包
```
