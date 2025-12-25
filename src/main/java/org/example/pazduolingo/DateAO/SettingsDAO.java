package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.Settings.Settings;
import org.springframework.jdbc.core.JdbcOperations;

import java.sql.*;


import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;


public class SettingsDAO {

    private final JdbcOperations jdbc;

    private final RowMapper<Settings> settingsRowMapper = (rs, rowNum) -> {
        Settings settings = new Settings();
        settings.Theme = rs.getString("Theme");
        settings.Type = rs.getString("Type");
        settings.Language = rs.getString("Language");
        settings.Volume = rs.getInt("Volume");
        return settings;
    };

    public SettingsDAO(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }
    

    public void saveSettings(Settings settings) {
        String sql = """
            INSERT INTO settings (Id, Theme, Type, Language, Volume)
            VALUES (1, ?, ?, ?, ?)
            ON CONFLICT(Id) DO UPDATE SET
                Theme = excluded.Theme,
                Type = excluded.Type,
                Language = excluded.Language,
                Volume = excluded.Volume
            """;

        jdbc.update(sql,
                settings.Theme,
                settings.Type,
                settings.Language,
                settings.Volume);
    }

    public Settings loadSettings() {
        String sql = "SELECT Theme, Type, Language, Volume FROM settings WHERE Id = 1";
        return jdbc.queryForObject(sql, settingsRowMapper);
    }
}
