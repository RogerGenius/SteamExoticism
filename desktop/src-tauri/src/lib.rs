//! SteamExoticism 桌面壳（Tauri 2）。
//!
//! 当前为骨架：已注册 shell 与 single-instance 插件。
//! M5 阶段将在此实现后端进程生命周期管理（见下方 TODO）。

#[cfg_attr(mobile, tauri::mobile_entry_point)]
pub fn run() {
    tauri::Builder::default()
        // 单实例：防止重复启动导致后端端口冲突
        .plugin(tauri_plugin_single_instance::init(|_app, _args, _cwd| {
            // TODO(M5): 聚焦已存在的窗口
        }))
        // 用于拉起后端 java 进程
        .plugin(tauri_plugin_shell::init())
        // TODO(M5): 在 .setup() 中完成后端生命周期：
        //   1. 用 tauri_plugin_shell 的 Command spawn 后端进程，例如
        //      `jre/bin/javaw.exe -jar app.jar --server.port=0`（jre 为 jlink 裁剪后的运行时，
        //      与 app.jar 一起作为 resources 随安装包分发）；
        //   2. 监听其 stdout，解析 "Tomcat started on port(xxxx)" 获取实际端口；
        //   3. 将窗口 URL 更新为 http://127.0.0.1:{port}；
        //   4. 在窗口关闭（on_window_event::CloseRequested / RunEvent::Exit）时结束后端进程。
        .run(tauri::generate_context!())
        .expect("error while running tauri application");
}
