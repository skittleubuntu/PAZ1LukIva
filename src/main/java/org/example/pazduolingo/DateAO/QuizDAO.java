package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.Question;
import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.Utilites.Factory;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

public class QuizDAO {

    private final JdbcOperations jdbc;
    private  QuestionDAO questionDAO;

    public QuizDAO(JdbcOperations jdbc) {
        this.jdbc = jdbc;
        this.questionDAO = Factory.getQuestionDao();
    }

    private final RowMapper<Quiz> quizRowMapper = (rs, rowNum) -> {
        Integer quizId = rs.getObject("id", Integer.class);
        String name = rs.getString("name");
        String description = rs.getString("description");

        List<Question> questions = questionDAO.loadQuestionForQuiz(quizId);
        return new Quiz(quizId, questions, name, description);
    };

    public void saveQuiz(Quiz quiz) {
        String sql = "INSERT INTO quizes (name, description) VALUES (?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, quiz.getName());
            ps.setString(2, quiz.getDescription());
            return ps;
        }, keyHolder);

        int quizId = keyHolder.getKey().intValue();

        for (Question q : quiz.getQuestions()) {
            questionDAO.saveQuestion(quizId, q);
        }
    }

    public Quiz loadQuizByID(int id) {
        String sql = "SELECT id, name, description FROM quizes WHERE id = ?";
        List<Quiz> quizzes = jdbc.query(sql, quizRowMapper, id);
        return quizzes.isEmpty() ? null : quizzes.get(0);
    }


    public List<Quiz> loadQuiz() {
        String sql = "SELECT id, name, description FROM quizes";
        return jdbc.query(sql, quizRowMapper);
    }


    public void deleteQuiz(Quiz quiz) {
        List<Question> questions = quiz.getQuestions();
        for (Question q : questions) {
            questionDAO.deleteQuestionByQuizID(quiz.getID());
        }
        jdbc.update("DELETE FROM quizes WHERE id = ?", quiz.getID());
    }
}