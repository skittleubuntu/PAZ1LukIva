package DAOTest;

import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.QuizClass.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestQuizDAO {




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

    }

    @Test
    @Order(1)
    @DisplayName("Save quiz")
    void testSaveQuiz() {


        SqlDAO.dropTables();
        QuizDAO.saveQuiz(quiz);
        List<Quiz> quizzes = QuizDAO.loadQuiz();

            assertEquals(1, quizzes.size());
            assertEquals("Test Quiz", quizzes.getFirst().getName());

    }

    @Test
    @Order(2)
    @DisplayName("Load quiz")
    void testLoadQuiz() {

        Note n1 = new Note(1, 21, "A", 0);
        List<Note> notes = List.of(n1);
        Question q = new Question(notes, QuestionDifficulty.EASY, InstrumentType.GUITAR, n1);
        Quiz quiz = new Quiz(List.of(q), "Another Quiz", "Simple test quiz");

        QuizDAO.saveQuiz(quiz);


        List<Quiz> loaded = QuizDAO.loadQuiz();
            assertFalse(loaded.isEmpty(), "Must be at least 1 quiz");
            assertEquals("Another Quiz", loaded.getFirst().getName());
            assertNotNull(loaded.getFirst().getQuestions());


    }




    @Test
    @Order(3)
    void testLoadQuizByID(){

        QuizDAO.saveQuiz(quiz);
        //cuz we have only 1 quiz
        Quiz quiz1 = QuizDAO.loadQuizByID(1);


            assertNotNull(quiz1);
            assertEquals(quiz1.getQuestions().getFirst().getNotes(), quiz.getQuestions().getFirst().getNotes());

    }



}
