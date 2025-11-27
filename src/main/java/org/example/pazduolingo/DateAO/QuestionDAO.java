package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.InstrumentType;
import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.QuizClass.Question;
import org.example.pazduolingo.QuizClass.QuestionDifficulty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {


    private static final String DB_URL = "jdbc:sqlite:database.db";


    public static List<Question> loadQuestionForQuiz(int quizId) {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT id, idFreq, question_duficult, instrumentType FROM questions WHERE quizes_id = ?;";

        try (Connection conn = DriverManager.getConnection(DB_URL)) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
           
            pstmt.setInt(1, quizId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int questionId = rs.getInt("id");
                Integer idFreq = rs.getInt("idFreq");
                if (rs.wasNull()) {
                    idFreq = null;
                }
                String difficultText = rs.getString("question_duficult");
                String instrumentText = rs.getString("instrumentType");


                Note freqNote = null;
                if (idFreq != null) {
                    freqNote = NoteDAO.getNoteByID(idFreq);
                }

                List<Note> notes = NoteDAO.loadNotesForQuestion(conn, questionId);

                QuestionDifficulty difficult = QuestionDifficulty.valueOf(difficultText);
                InstrumentType instrumentType = InstrumentType.valueOf(instrumentText);

                Question question = new Question(notes, difficult, instrumentType, freqNote);
                questions.add(question);

            }


        } catch (SQLException e) {
          
            throw new RuntimeException(e);
        }

        return questions;
    }





        public static void saveQuestion (Connection conn, Question q,int quizId) throws SQLException {
            String insertQuestionSQL = "INSERT INTO questions (quizes_id, idFreq, question_duficult, instrumentType) VALUES (?, ?, ?, ?)";
            int questionId;


            try (PreparedStatement pstmtQ = conn.prepareStatement(insertQuestionSQL, Statement.RETURN_GENERATED_KEYS)) {
                pstmtQ.setInt(1, quizId);

                if (q.getFreqNote() != null) {
                    pstmtQ.setInt(2, q.getFreqNote().getId());
                }
                pstmtQ.setString(3, q.getDifficult().toString());
                pstmtQ.setString(4, q.getInstrumentType().toString());
                pstmtQ.executeUpdate();

                try (ResultSet rs = pstmtQ.getGeneratedKeys()) {
                    if (rs.next()) {
                        questionId = rs.getInt(1);
                    } else {

                        throw new SQLException();
                    }
                }
                catch (
                        SQLException e
                ){
                  
                    throw e;
                }
            }


            NoteDAO.linkNotesToQuestion(conn, questionId, q);
        }




}
