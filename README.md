# SteamExoticism

个人单机使用的工具软件。前端 Vue 3，后端 Spring Boot + SQLite（MyBatis-Plus），并提供 Web 与 Tauri 桌面客户端两种成品形态。

## 技术栈

| 层 | 选型 |
|---|---|
| 前端 | Vue 3 + Vite + TypeScript + Vue Router + Pinia + Axios + Element Plus |
| 后端 | Spring Boot 3.5.16（JDK 17）+ MyBatis-Plus 3.5.17 + SQLite（sqlite-jdbc）+ HikariCP |
| 桌面 | Tauri 2（系统 WebView2，sidecar 拉起后端 jar） |

## 目录结构

```
SteamExoticism/
├── backend/    # Spring Boot 后端：controller / service / mapper / entity / dto / common / config
├── frontend/   # Vue3 + Vite 前端：api / router / stores / views / components / layout / styles
├── desktop/    # Tauri 2 桌面壳工程（src-tauri）
└── docs/       # 开发文档
```

## 快速开始（开发模式，前后端分离）

前置环境：JDK 17、Maven 3.9、Node.js 20+（含 npm）。

```bash
# 1. 启动后端（http://localhost:18080）
cd backend
mvn spring-boot:run

# 2. 启动前端（http://localhost:5173，/api 已代理到 18080）
cd frontend
npm install
npm run dev
```

浏览器打开 http://localhost:5173 ，首页会显示前后端连接与数据库状态。

## 构建单 jar（Web 形态，M4 里程碑）

目标：前端 dist 打包进后端 static，`java -jar app.jar` 后浏览器访问即用。具体步骤见 `docs/开发计划.md` 的 M4 小节（尚未实现）。

## 桌面客户端（M5 里程碑）

Tauri 2 桌面壳，见 `desktop/README.md`（尚未实现，需安装 Rust 工具链）。

## 项目约定

- 后端接口统一以 `/api` 开头，返回统一结构 `Result<T>`（code / message / data）。
- 代码分层：`controller` → `service` → `mapper` → `entity`，DTO 放 `dto` 包。
- SQLite 数据库文件默认在运行目录下 `./data/app.db`（WAL 模式），日志输出到 `./logs/`。
- 主键使用 `@TableId(type = IdType.AUTO)`，业务表主键为 `INTEGER PRIMARY KEY AUTOINCREMENT`。
- 分页插件已配置为 `DbType.SQLITE`（依赖 `mybatis-plus-jsqlparser`）。
