package org.example.hearo.DateAO;

import org.example.hearo.QuizClass.Question;
import org.example.hearo.QuizClass.Quiz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuizDAO {


    private static final String DB_URL = "jdbc:sqlite:database.db";


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


        } catch (SQLException e) {
          

        }
    }

    public static Quiz loadQuizByID(int id){

        String sql = "SELECT id, name, description FROM quizes WHERE id = ?";
        
   
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(sql)) {


            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (!rs.next()) {

                return null;
            }

            int quizId = rs.getInt("id");
            String name = rs.getString("name");
            String desc = rs.getString("description");


            List<Question> questions = QuestionDAO.loadQuestionForQuiz(quizId);

            return new Quiz(id,questions, name, desc);

        } catch (SQLException e) {
          
            throw new RuntimeException();
        }
    }

    public static void deleteQuiz(Quiz quiz){
        String sql = "DELETE FROM quizes WHERE id = ?";

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

                quizzes.add(new Quiz(id,questions,name,desc));

            }


        } catch (SQLException e) {
          
            throw new RuntimeException(e);
        }

        return quizzes;
    }







}
