package org.example.pazduolingo.Utilites;

import org.example.pazduolingo.DateAO.*;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.QuizClass.InstrumentType;
import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.List;

public class Factory {

    private static DatabaseProfile mode = DatabaseProfile.PROD;
    private static SqlDAO sqlDAO;
    private static JdbcOperations jdbc;
    private static SettingsDAO settingsDao;
    private static QuizDAO quizDao;
    private static NoteDAO noteDao;
    private static QuestionDAO questionDao;
    private static StatsDAO statsDAO;
    private static final Object lock = new Object();


    public static void setDatabaseMode(DatabaseProfile newMode) {
        mode = newMode;
    }


    private static JdbcOperations createJdbc() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("org.sqlite.JDBC");
        if (mode == DatabaseProfile.TEST) {
            ds.setUrl("jdbc:sqlite:database_test.db");
        } else {
            ds.setUrl("jdbc:sqlite:database.db");
        }
        return new JdbcTemplate(ds);
    }

    public static JdbcOperations getJdbc() {
        if (jdbc == null) {
            System.out.println("jdbc created here");
            synchronized (lock) {
                if (jdbc == null) {
                    jdbc = createJdbc();
                }
            }
        }
        return jdbc;
    }


    public static SqlDAO getSQLDao(){
        if (sqlDAO == null) {
            synchronized (lock) {
                if (sqlDAO == null) {
                    sqlDAO = new SqlDAO(getJdbc());
                }
            }
        }
        return sqlDAO;
    }

    public static QuestionDAO getQuestionDao() {
        if (questionDao == null) {
            synchronized (lock) {
                if (questionDao == null) {
                    questionDao = new QuestionDAO(getJdbc());
                }
            }
        }
        return questionDao;
    }


    public static StatsDAO getStatsDao() {
        if (statsDAO == null) {
            synchronized (lock) {
                if (statsDAO == null) {
                    statsDAO = new StatsDAO(getJdbc());
                }
            }
        }
        return statsDAO;
    }






    public static SettingsDAO getSettingsDao() {
        if (settingsDao == null) {
            synchronized (lock) {
                if (settingsDao == null) {
                    settingsDao = new SettingsDAO(getJdbc());
                }
            }
        }
        return settingsDao;
    }

    public static QuizDAO getQuizDao() {
        if (quizDao == null) {
            synchronized (lock) {
                if (quizDao == null) {
                    quizDao = new QuizDAO(getJdbc());
                }
            }
        }
        return quizDao;
    }

    public static NoteDAO getNoteDao() {
        if (noteDao == null) {
            synchronized (lock) {
                if (noteDao == null) {
                    noteDao = new NoteDAO(getJdbc());
                }
            }
        }
        return noteDao;
    }


    public static Sounder createSounder(InstrumentType type) {
        int midiInstrument;
        int duration;

        switch (type) {
            case GUITAR -> { midiInstrument = 24; duration = 800; }
            case VIOLIN -> { midiInstrument = 40; duration = 1200; }
            case FLUTE -> { midiInstrument = 73; duration = 1000; }
            case PIANO -> { midiInstrument = 0; duration = 1000; }
            default -> { midiInstrument = 0; duration = 1000; }
        }

        return new Sounder(midiInstrument, duration);
    }

    public static Note getFloatNote(Note note, List<Note> notes) {
        String name;

        if (note.getNameClear().contains("#")) {
            int nextIndex = note.getId();
            if (nextIndex < notes.size()) {
                name = notes.get(nextIndex).getNameClear().replace("#", "") + "♭";
            } else {
                name = note.getNameClear();
            }
        } else {
            name = note.getNameClear();
        }

        return new Note(
                note.getId(),
                note.getMidiNumber(),
                name,
                note.getOctave()
        );
    }
}
