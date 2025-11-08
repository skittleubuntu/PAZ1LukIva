package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.QuizClass.Question;
import org.example.pazduolingo.QuizClass.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {


    private static final String DB_URL = "jdbc:sqlite:database";


    public static void saveQuiz(Quiz quiz) {
        String insertQuizSQL = "INSERT INTO quizes (name, description) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            conn.setAutoCommit(false);

            int quizId;
            try (PreparedStatement pstmt = conn.prepareStatement(insertQuizSQL, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, quiz.getName());
                pstmt.setString(2, quiz.getDescription());
                pstmt.executeUpdate();

                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        quizId = rs.getInt(1);
                    } else {
                        throw new SQLException("");
                    }
                }
            }


            for (Question q : quiz.getQuestions()) {
                QuestionDAO.saveQuestion(conn, q, quizId);
            }

            conn.commit();
            System.out.println("Quiz '" + quiz.getName() + "' збережено!");

        } catch (SQLException e) {

        }
    }


    public static List<Quiz> loadQuiz(){
        List<Quiz> quizzes = new ArrayList<>();

        String getAllQuizes = "SELECT id, name , description FROM quizes";


        try (Connection conn = DriverManager.getConnection(DB_URL)){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(getAllQuizes);

            while (rs.next()){

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String desc = rs.getString("description");


                List<Question> questions = QuestionDAO.loadQuestionForQuiz(id);

                quizzes.add(new Quiz(questions,name,desc));

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return quizzes;
    }


    public static void dropTables(){
        //command found on stackoverflow

        String[] sql = {
                "PRAGMA foreign_keys = OFF;",
                "BEGIN TRANSACTION;",
                "DELETE FROM questions_has_notes;",
                "DELETE FROM note_stats;",
                "DELETE FROM questions;",
                "DELETE FROM quizes;",
                "DELETE FROM notes;",
                "COMMIT;",
                "PRAGMA foreign_keys = ON;"
        };

        try(Connection conn = DriverManager.getConnection(DB_URL)){
            Statement stmt = conn.createStatement();
            for (String query : sql) {
                stmt.executeUpdate(query);
            }

            System.out.println("Tables droped!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
