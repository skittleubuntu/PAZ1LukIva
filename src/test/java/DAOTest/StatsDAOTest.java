package DAOTest;

import org.example.pazduolingo.DateAO.DatabaseProfile;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.Utilites.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcOperations;

import static org.junit.jupiter.api.Assertions.*;

public class StatsDAOTest {

    private StatsDAO statsDAO;
    private SqlDAO sqlDAO;
    @BeforeEach
    void setup() {


        Factory.setDatabaseMode(DatabaseProfile.TEST);
        sqlDAO = Factory.getSQLDao();
        sqlDAO.dropTables();
        statsDAO = Factory.getStatsDao();

    }

    @Test
    void testAddRoundsAndGetRounds() {
        int quizID = 1;
        int noteID = 67;

        statsDAO.addRounds(noteID, quizID);
        statsDAO.addRounds(noteID, quizID);

        int rounds = statsDAO.getRounds(noteID, quizID);
        assertEquals(2, rounds);
    }

    @Test
    void testAddCorrectAnswersAndGetCorrectAnswers() {
        int quizID = 1;
        int noteID = 67;

        statsDAO.addCorrectAnswers(noteID, quizID);
        statsDAO.addCorrectAnswers(noteID, quizID);

        int correct = statsDAO.getCorrectAnswers(noteID, quizID);
        assertEquals(2, correct);
    }

    @Test
    void testAccuracyForNote() {
        int quizID = 1;
        int noteID = 67;

        statsDAO.addRounds(noteID, quizID);
        statsDAO.addRounds(noteID, quizID);
        statsDAO.addCorrectAnswers(noteID, quizID);

        int accuracy = statsDAO.getAccuracy(noteID, quizID);
        assertEquals(50, accuracy);
    }

    @Test
    void testWrongAnswers() {
        int quizID = 1;
        int noteID = 67;

        statsDAO.addRounds(noteID, quizID);
        statsDAO.addRounds(noteID, quizID);
        statsDAO.addCorrectAnswers(noteID, quizID);

        int wrong = statsDAO.getWrongAnswers(noteID, quizID);
        assertEquals(1, wrong);
    }

    @Test
    void testQuizRoundsAndCorrectAnswers() {
        int quizID = 1;

        statsDAO.addRounds(60, quizID);
        statsDAO.addRounds(61, quizID);
        statsDAO.addRounds(61, quizID);

        statsDAO.addCorrectAnswers(60, quizID);
        statsDAO.addCorrectAnswers(61, quizID);

        assertEquals(3, statsDAO.getQuizRounds(quizID));
        assertEquals(2, statsDAO.getQuizCorrectAnswers(quizID));
    }

    @Test
    void testQuizAccuracy() {
        int quizID = 1;

        statsDAO.addRounds(60, quizID);
        statsDAO.addRounds(61, quizID);
        statsDAO.addCorrectAnswers(60, quizID);

        int accuracy = statsDAO.getQuizAccuracy(quizID);
        assertEquals(50, accuracy);
    }

    @Test
    void testOverallStats() {
        statsDAO.addRounds(60, 1);
        statsDAO.addRounds(61, 1);
        statsDAO.addRounds(62, 2);

        statsDAO.addCorrectAnswers(60, 1);
        statsDAO.addCorrectAnswers(62, 2);

        assertEquals(3, statsDAO.getOverallRounds());
        assertEquals(2, statsDAO.getOverallCorrectAnswers());
        assertEquals(67, statsDAO.getOverallAccuracy());
    }

    @Test
    void testZeroRoundsAccuracy() {
        int quizID = 1;
        int noteID = 99;

        assertEquals(0, statsDAO.getAccuracy(noteID, quizID));
        assertEquals(0, statsDAO.getQuizAccuracy(quizID));
        assertEquals(0, statsDAO.getOverallAccuracy());
    }
}
