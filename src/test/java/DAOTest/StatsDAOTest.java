package DAOTest;

import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.example.pazduolingo.QuizClass.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class StatsDAOTest {



    @BeforeEach
    void setup(){

        SqlDAO.dropTables();

    }


    @Test
    void testRounds(){
        int quizID = 1;
        int noteID = 67;



        StatsDAO.addRounds(noteID,quizID);

        int rounds = StatsDAO.getRounds(noteID,quizID);

        assertEquals(1,rounds);


    }


    @Test
    void testCorrectAnswers(){
        int quizID = 1;
        int noteID = 67;



        StatsDAO.addCorrectAnswers(noteID,quizID);

        int corr = StatsDAO.getCorrectAnswers(noteID,quizID);

        assertEquals(1,corr);


    }



}
