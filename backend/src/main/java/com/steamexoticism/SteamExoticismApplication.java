package com.steamexoticism;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * SteamExoticism 应用入口。
 */
@SpringBootApplication
@MapperScan("com.steamexoticism.mapper")
public class SteamExoticismApplication {

    public static void main(String[] args) {
        initRuntimeDirectories();
        SpringApplication.run(SteamExoticismApplication.class, args);
    }

    /**
     * SQLite 不会自动创建父目录，这里在启动早期确保数据与日志目录存在，
     * 避免首次连接数据库时因目录缺失而失败。
     */
    private static void initRuntimeDirectories() {
        try {
            Files.createDirectories(Path.of("data"));
            Files.createDirectories(Path.of("logs"));
        } catch (IOException e) {
            System.err.println("创建运行时目录失败: " + e.getMessage());
        }
    }
}
