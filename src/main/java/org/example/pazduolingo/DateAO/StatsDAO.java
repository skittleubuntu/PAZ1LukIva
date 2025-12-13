package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.Quiz;


import java.sql.*;

public class StatsDAO {

    private static final String DB_URL = "jdbc:sqlite:database.db";

    public static int getRounds(int noteID, int quizID){
        String sql = "SELECT rounds FROM note_stats WHERE notes_id = ? and quizes_id = ?";
        int rounds = -1;
        try (Connection conn = DriverManager.getConnection(DB_URL)){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,noteID);
            pstm.setInt(2, quizID);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){

                rounds = rs.getInt("rounds");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rounds;

    }

    public static int getCorrectAnswers(int noteID, int quizID){
        String sql = "SELECT correct_answers FROM note_stats WHERE notes_id = ? and quizes_id = ?";
        int corr = -1;
        try (Connection conn = DriverManager.getConnection(DB_URL)){
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,noteID);
            pstm.setInt(2, quizID);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()){

                corr = rs.getInt("correct_answers");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return corr;

    }

    public static int getOverallRounds(){
        String sql = "SELECT SUM(rounds) as total_rounds FROM note_stats";
        int corr = -1;
        try (Connection conn = DriverManager.getConnection(DB_URL)){
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                corr = rs.getInt("total_rounds");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return corr;
    }

    public static int getOverallCorrectAnswers(){
        String sql = "SELECT SUM(correct_answers) as total_correct_answers FROM note_stats";
        int corr = -1;
        try (Connection conn = DriverManager.getConnection(DB_URL)){
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                corr = rs.getInt("total_correct_answers");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return corr;
    }

    public static void addRounds(int noteID, int quizID) {

        String updateSql = "UPDATE note_stats SET rounds = rounds + 1 WHERE notes_id = ? AND quizes_id = ?";

        String insertSql = "INSERT INTO note_stats (notes_id, quizes_id, rounds) VALUES (?, ?, 1)";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, noteID);
                updateStmt.setInt(2, quizID);

                int updatedRows = updateStmt.executeUpdate();

                if (updatedRows == 0) {

                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, noteID);
                        insertStmt.setInt(2, quizID);

                        insertStmt.executeUpdate();
                        System.out.println("New round counter inserted.");
                    }
                } else {
                    System.out.println("Round counter updated.");
                }
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }


    public static void addCorrectAnswers(int noteID, int quizID) {

        String updateSql = "UPDATE note_stats SET correct_answers = correct_answers + 1 WHERE notes_id = ? AND quizes_id = ?";

        String insertSql = "INSERT INTO note_stats (notes_id, quizes_id, correct_answers) VALUES (?, ?, 1)";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {

            try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                updateStmt.setInt(1, noteID);
                updateStmt.setInt(2, quizID);

                int updatedRows = updateStmt.executeUpdate();

                if (updatedRows == 0) {

                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, noteID);
                        insertStmt.setInt(2, quizID);

                        insertStmt.executeUpdate();
                        System.out.println("New correct_answers counter inserted.");
                    }
                } else {
                    System.out.println("correct_answers counter updated.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getAccuracy(int noteID, int quizID) {
        double rounds = getRounds(noteID, quizID);

        if (rounds == 0) {
            return 0;
        }

        return (int) Math.round((getCorrectAnswers(noteID, quizID) / rounds)*100);
    }

    public static int getWrongAnswers(int noteID, int quizID) {
        return getRounds(noteID, quizID) - getCorrectAnswers(noteID, quizID);
    }

    public static int getOverallAccuracy() {
        double rounds = getOverallRounds();

        if (rounds == 0) {
            return 0;
        }
        return (int) Math.round((getOverallCorrectAnswers() / rounds)* 100);
    }

    public static int getOverallWrongAnswers() {
        return getOverallRounds() - getOverallCorrectAnswers();
    }

    public static void deleteQuizStats(Quiz quiz){
        String sql = "DELETE FROM note_stats WHERE quizes_id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL)){

            conn.setAutoCommit(false);
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1,quiz.getID());
                pstmt.executeUpdate();
                conn.commit();

            }
            catch (SQLException e ){
                conn.rollback();
                throw e;
            }
            QuestionDAO.deleteQuestionByQuizID(conn,quiz.getID());

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }


    }

}