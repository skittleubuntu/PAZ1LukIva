package DAOTest;

import org.example.pazduolingo.DateAO.QuestionDAO;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.QuizClass.*;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestQuestionDAO {


    //testQuiz for test
    Note n1 = new Note(1, 21, "A", 0);
    Note n2 = new Note(2, 22, "A#", 0);
    Note n3 = new Note(3, 23, "B", 0);
    List<Note> notes = List.of(n1, n2, n3);


    Question q1 = new Question(notes, QuestionDifficulty.EASY, InstrumentType.GUITAR, n2);
    Question q2 = new Question(notes, QuestionDifficulty.MEDIUM, InstrumentType.VIOLIN, n1);
    List<Question> questions = List.of(q1, q2);


    Quiz quiz = new Quiz(questions, "Test Quiz", "Test save of quiz");

    @BeforeEach
    void setup() {
        SqlDAO.dropTables();
        QuizDAO.saveQuiz(quiz);
    }


    @Order(1)
    @Test
    void testLoadQuestionForQuiz(){


        List<Question> questions2 = QuestionDAO.loadQuestionForQuiz(1);

        assertEquals(questions2.size(), questions.size());

        for (int i = 0; i < questions2.size(); i++) {
            assertEquals(questions.get(i).getNotes(), questions2.get(i).getNotes());

        }



    }

    @Order(2)
    @Test
    void  testSaveQuestionToQuiz(){
        //we have rn only 2 questions for our quiz with id 1
        //lets create onather one question and save for our quiz
        //when we get questions for quiz we must take 3 questions

        Note n1 = new Note(1, 21, "A", 0);
        Note n2 = new Note(2, 22, "A#", 0);
        Note n3 = new Note(3, 23, "B", 0);
        List<Note> notes = List.of(n1, n2, n3);


        Question q = new Question(notes, QuestionDifficulty.EASY, InstrumentType.GUITAR, n2);

        Connection conn = SqlDAO.getConnection();

        try {
            QuestionDAO.saveQuestion(conn,q,1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        Quiz quiz = QuizDAO.loadQuizByID(1);

        System.out.println("Size of Quiz: " + quiz.getQuestions().size());
        assertEquals(quiz.getQuestions().size(), 3);


    }


    @AfterAll
    static void after(){
        SqlDAO.dropTables();
    }



}