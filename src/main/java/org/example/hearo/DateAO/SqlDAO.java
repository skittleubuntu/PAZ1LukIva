package org.example.hearo.DateAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlDAO {

    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static void dropTables(){
        //command found on stackoverflow

        String[] sql = {
                "PRAGMA foreign_keys = OFF;",
                "BEGIN TRANSACTION;",
                "DELETE FROM questions_has_notes;",
                "DELETE FROM note_stats;",
                "DELETE FROM questions;",
                "DELETE FROM quizes;",
                "DELETE FROM sqlite_sequence;",
                "COMMIT;",
                "PRAGMA foreign_keys = ON;"
        };
   
        try(Connection conn = DriverManager.getConnection(DB_URL)){
           
            Statement stmt = conn.createStatement();
            for (String query : sql) {
                stmt.executeUpdate(query);
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getConnection() {

   
        try {

            return DriverManager.getConnection(DB_URL);

        } catch (SQLException e) {
          
            throw new RuntimeException();
        }
    }


}
