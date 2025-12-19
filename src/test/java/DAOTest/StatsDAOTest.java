package DAOTest;

import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StatsDAOTest {

    @BeforeEach
    void setup() {
        SqlDAO.dropTables();
    }

    @Test
    void testAddRoundsAndGetRounds() {
        int quizID = 1;
        int noteID = 67;

        StatsDAO.addRounds(noteID, quizID);
        StatsDAO.addRounds(noteID, quizID);

        int rounds = StatsDAO.getRounds(noteID, quizID);

        assertEquals(2, rounds);
    }

    @Test
    void testAddCorrectAnswersAndGetCorrectAnswers() {
        int quizID = 1;
        int noteID = 67;

        StatsDAO.addCorrectAnswers(noteID, quizID);
        StatsDAO.addCorrectAnswers(noteID, quizID);

        int correct = StatsDAO.getCorrectAnswers(noteID, quizID);

        assertEquals(2, correct);
    }

    @Test
    void testAccuracyForNote() {
        int quizID = 1;
        int noteID = 67;

        StatsDAO.addRounds(noteID, quizID);
        StatsDAO.addRounds(noteID, quizID);
        StatsDAO.addCorrectAnswers(noteID, quizID);

        int accuracy = StatsDAO.getAccuracy(noteID, quizID);

        assertEquals(50, accuracy);
    }

    @Test
    void testWrongAnswers() {
        int quizID = 1;
        int noteID = 67;

        StatsDAO.addRounds(noteID, quizID);
        StatsDAO.addRounds(noteID, quizID);
        StatsDAO.addCorrectAnswers(noteID, quizID);

        int wrong = StatsDAO.getWrongAnswers(noteID, quizID);

        assertEquals(1, wrong);
    }

    @Test
    void testQuizRoundsAndCorrectAnswers() {
        int quizID = 1;

        StatsDAO.addRounds(60, quizID);
        StatsDAO.addRounds(61, quizID);
        StatsDAO.addRounds(61, quizID);

        StatsDAO.addCorrectAnswers(60, quizID);
        StatsDAO.addCorrectAnswers(61, quizID);

        assertEquals(3, StatsDAO.getQuizRounds(quizID));
        assertEquals(2, StatsDAO.getQuizCorrectAnswers(quizID));
    }

    @Test
    void testQuizAccuracy() {
        int quizID = 1;

        StatsDAO.addRounds(60, quizID);
        StatsDAO.addRounds(61, quizID);
        StatsDAO.addCorrectAnswers(60, quizID);

        int accuracy = StatsDAO.getQuizAccuracy(quizID);

        assertEquals(50, accuracy);
    }

    @Test
    void testOverallStats() {
        StatsDAO.addRounds(60, 1);
        StatsDAO.addRounds(61, 1);
        StatsDAO.addRounds(62, 2);

        StatsDAO.addCorrectAnswers(60, 1);
        StatsDAO.addCorrectAnswers(62, 2);

        assertEquals(3, StatsDAO.getOverallRounds());
        assertEquals(2, StatsDAO.getOverallCorrectAnswers());
        assertEquals(67, StatsDAO.getOverallAccuracy());
    }

    @Test
    void testZeroRoundsAccuracy() {
        int quizID = 1;
        int noteID = 99;

        assertEquals(0, StatsDAO.getAccuracy(noteID, quizID));
        assertEquals(0, StatsDAO.getQuizAccuracy(quizID));
        assertEquals(0, StatsDAO.getOverallAccuracy());
    }
}
