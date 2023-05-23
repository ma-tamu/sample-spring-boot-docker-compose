package com.example.demo.controller.api;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("sample")
public class SampleRestController {

    private final JdbcTemplate jdbcTemplate;

    public SampleRestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> get(@PathVariable("id") String id) {
        final var sql = """
                        SELECT
                          *
                        FROM
                          user
                        WHERE
                          id = ?
                        """;
        final var user = jdbcTemplate.queryForMap(sql, id);
        return ResponseEntity.ok(user);
    }


    @PostMapping("entry")
    @Transactional
    public ResponseEntity<String> entry(@RequestBody Map<String, Object> body) {
        final var id = UUID.randomUUID().toString().replace("-", "");

        final var sql = """
                        INSERT INTO user (id, loginid, name, created_by, updated_by)
                        VALUES (?, ?, ?, 'NULL', 'NULL')
                        """;
        jdbcTemplate.update(sql, id, body.get("loginid"), body.get("name"));

        return ResponseEntity.ok("""
                                    {
                                        "id": %s
                                    }
                                    """.formatted(id));
    }
}
