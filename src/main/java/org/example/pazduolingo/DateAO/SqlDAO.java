package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.Utilites.Factory;
import org.springframework.jdbc.core.JdbcOperations;

public class SqlDAO {

    private final JdbcOperations jdbc;

    public SqlDAO(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public void dropTables() {
        // Окремо видаляємо таблиці та скидаємо sequence
        jdbc.execute("PRAGMA foreign_keys = OFF");

        jdbc.execute("DELETE FROM questions_has_notes");
        jdbc.execute("DELETE FROM note_stats");
        jdbc.execute("DELETE FROM questions");
        jdbc.execute("DELETE FROM quizes");
        jdbc.execute("DELETE FROM sqlite_sequence");

        jdbc.execute("PRAGMA foreign_keys = ON");
    }


}
