package org.example.hearo.DateAO;

import org.example.hearo.Settings.Settings;

import java.sql.*;

public class SettingsDAO {

    private static final String DB_URL = "jdbc:sqlite:database.db";


    public static void saveSettings(Settings settings) {
        String sql = "INSERT INTO settings (Id, Theme, Type, Language, Volume) " +
                "VALUES (1, ?, ?, ?, ?) " +
                "ON CONFLICT(Id) DO UPDATE SET " +
                "Theme = excluded.Theme, " +
                "Type = excluded.Type, " +
                "Language = excluded.Language, " +
                "Volume = excluded.Volume";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
           

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, settings.Theme);
                pstmt.setString(2, settings.Type);
                pstmt.setString(3, settings.Language);
                pstmt.setInt(4, settings.Volume);

                pstmt.executeUpdate();

            } catch (SQLException e) {
              
                throw new RuntimeException(e);

            }

        } catch (SQLException e) {
          
            throw new RuntimeException(e);
        }

    }


    public static Settings loadSettings(){
        Settings settings = new Settings();
        String sql = "SELECT Theme, Type, Language, Volume FROM settings WHERE Id = 1";


        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)){

               

            ResultSet rs = stmt.executeQuery();

            int volume = rs.getInt("Volume");
            String language = rs.getString("Language");
            String theme = rs.getString("Theme");
            String type = rs.getString("Type");

            settings.Language = language;
            settings.Type = type;
            settings.Volume = volume;
            settings.Theme = theme;

            return settings;

        } catch (SQLException e) {
          
            throw new RuntimeException(e);
        }



    }

}
