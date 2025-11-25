package DAOTest;

import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.QuizClass.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SQLDAOTest {


    @Test
    void testDropTables() {

        Note n = new Note(1, 21, "A", 0);
        Question q = new Question(List.of(n), QuestionDifficulty.EASY, InstrumentType.GUITAR, n);
        Quiz quiz = new Quiz(List.of(q), "Temporary Quiz", "To be deleted");

        QuizDAO.saveQuiz(quiz);


        List<Quiz> before = QuizDAO.loadQuiz();
        assertFalse(before.isEmpty(), "Before drop must be 1 quiz");

        SqlDAO.dropTables();

        List<Quiz> after = QuizDAO.loadQuiz();
        assertTrue(after.isEmpty(), "Must be null");
    }
}
