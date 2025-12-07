package DAOTest;

import org.example.hearo.DateAO.QuizDAO;
import org.example.hearo.DateAO.SqlDAO;
import org.example.hearo.QuizClass.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SQLDAOTest {


    @Test
    void testDropTables() {



        Note n = new Note(1, 21, "A", 0);
        Question q = new Question(List.of(n), QuestionDifficulty.EASY, InstrumentType.GUITAR, n);
        Quiz quiz = new Quiz(1,List.of(q), "Temporary Quiz", "To be deleted");


        QuizDAO.saveQuiz(quiz);


        List<Quiz> before = QuizDAO.loadQuiz();


            assertFalse(before.isEmpty());



        SqlDAO.dropTables();

        List<Quiz> after = QuizDAO.loadQuiz();





            assertTrue(after.isEmpty());

    }
}
