package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.Quiz;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;

public class StatsDAO {

    private final JdbcOperations jdbc;

    public StatsDAO(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }



    public int getRounds(int noteID, int quizID) {
        try {
            Integer value = jdbc.queryForObject(
                    "SELECT rounds FROM note_stats WHERE notes_id = ? AND quizes_id = ?",
                    Integer.class,
                    noteID, quizID
            );
            return value != null ? value : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int getCorrectAnswers(int noteID, int quizID) {
        try {
            Integer value = jdbc.queryForObject(
                    "SELECT correct_answers FROM note_stats WHERE notes_id = ? AND quizes_id = ?",
                    Integer.class,
                    noteID, quizID
            );
            return value != null ? value : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int getAccuracy(int noteID, int quizID) {
        int rounds = getRounds(noteID, quizID);
        if (rounds == 0) return 0;
        int correct = getCorrectAnswers(noteID, quizID);
        return (int) Math.round(((double) correct / rounds) * 100);
    }

    public int getWrongAnswers(int noteID, int quizID) {
        return getRounds(noteID, quizID) - getCorrectAnswers(noteID, quizID);
    }

    // --- QUIZ STATS METHODS ---

    public int getQuizRounds(int quizID) {
        try {
            Integer value = jdbc.queryForObject(
                    "SELECT SUM(rounds) FROM note_stats WHERE quizes_id = ?",
                    Integer.class,
                    quizID
            );
            return value != null ? value : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int getQuizCorrectAnswers(int quizID) {
        try {
            Integer value = jdbc.queryForObject(
                    "SELECT SUM(correct_answers) FROM note_stats WHERE quizes_id = ?",
                    Integer.class,
                    quizID
            );
            return value != null ? value : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int getQuizAccuracy(int quizID) {
        int rounds = getQuizRounds(quizID);
        if (rounds == 0) return 0;
        int correct = getQuizCorrectAnswers(quizID);
        return (int) Math.round(((double) correct / rounds) * 100);
    }

    // --- OVERALL STATS METHODS ---

    public int getOverallRounds() {
        try {
            Integer value = jdbc.queryForObject(
                    "SELECT SUM(rounds) FROM note_stats",
                    Integer.class
            );
            return value != null ? value : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int getOverallCorrectAnswers() {
        try {
            Integer value = jdbc.queryForObject(
                    "SELECT SUM(correct_answers) FROM note_stats",
                    Integer.class
            );
            return value != null ? value : 0;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    public int getOverallAccuracy() {
        int rounds = getOverallRounds();
        if (rounds == 0) return 0;
        int correct = getOverallCorrectAnswers();
        return (int) Math.round(((double) correct / rounds) * 100);
    }

    public int getOverallWrongAnswers() {
        return getOverallRounds() - getOverallCorrectAnswers();
    }


    public void addRounds(int noteID, int quizID) {
        int updated = jdbc.update(
                "UPDATE note_stats SET rounds = rounds + 1 WHERE notes_id = ? AND quizes_id = ?",
                noteID, quizID
        );

        if (updated == 0) {
            jdbc.update(
                    "INSERT INTO note_stats (notes_id, quizes_id, rounds, correct_answers) VALUES (?, ?, 1, 0)",
                    noteID, quizID
            );
        }
    }

    public void addCorrectAnswers(int noteID, int quizID) {
        int updated = jdbc.update(
                "UPDATE note_stats SET correct_answers = correct_answers + 1 WHERE notes_id = ? AND quizes_id = ?",
                noteID, quizID
        );

        if (updated == 0) {
            jdbc.update(
                    "INSERT INTO note_stats (notes_id, quizes_id, rounds, correct_answers) VALUES (?, ?, 0, 1)",
                    noteID, quizID
            );
        }
    }



    public void deleteQuizStats(Quiz quiz) {
        jdbc.update(
                "DELETE FROM note_stats WHERE quizes_id = ?",
                quiz.getID()
        );
    }
}
