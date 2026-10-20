package io.github.rogergenius.steamexoticism.controller;

import io.github.rogergenius.steamexoticism.common.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 健康检查：用于验证前后端连通与 SQLite 连接是否正常。
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HealthController {

    private final JdbcTemplate jdbcTemplate;

    @GetMapping("/health")
    public Result<Map<String, Object>> health() {
        Map<String, Object> info = new LinkedHashMap<>();
        info.put("status", "UP");
        info.put("app", "SteamExoticism");
        info.put("time", LocalDateTime.now().toString());
        try {
            Integer one = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            info.put("database", one != null && one == 1 ? "UP" : "DOWN");
        } catch (Exception e) {
            info.put("database", "DOWN: " + e.getMessage());
        }
        return Result.ok(info);
    }
}
