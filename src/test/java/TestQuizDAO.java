import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.QuizClass.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class TestQuizDAO {




    Note n1 = new Note(1, 21, "A", 0);
    Note n2 = new Note(2, 22, "A#", 0);
    Note n3 = new Note(3, 23, "B", 0);
    List<Note> notes = List.of(n1, n2, n3);


    Question q1 = new Question(notes, QuestionDifficult.EASY, InstrumentType.GUITAR, n2);
    Question q2 = new Question(notes, QuestionDifficult.MEDIUM, InstrumentType.VIOLIN, n1);
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
        assertEquals(1, quizzes.size(), "In database.db must be only 1 quiz");
        assertEquals("Test Quiz", quizzes.get(0).getName());
    }

    @Test
    @Order(2)
    @DisplayName("Load quiz")
    void testLoadQuiz() {

        Note n1 = new Note(1, 21, "A", 0);
        List<Note> notes = List.of(n1);
        Question q = new Question(notes, QuestionDifficult.EASY, InstrumentType.GUITAR, n1);
        Quiz quiz = new Quiz(List.of(q), "Another Quiz", "Simple test quiz");

        QuizDAO.saveQuiz(quiz);


        List<Quiz> loaded = QuizDAO.loadQuiz();

        assertFalse(loaded.isEmpty(), "Must be at least 1 quiz");
        assertEquals("Another Quiz", loaded.get(0).getName());
        assertNotNull(loaded.get(0).getQuestions());
    }




    @Test
    @Order(3)
    void testLoadQuizByID(){
        QuizDAO.saveQuiz(quiz);
        //cuz we have only 1 quiz
        Quiz quiz1 = QuizDAO.loadQuizByID(1);
        System.out.println(quiz1);
        assertEquals(quiz1.getQuestions().get(0).getNotes(), quiz.getQuestions().get(0).getNotes());
    }




    @Test
    @Order(4)
    @DisplayName("Drop tables")
    void testDropTables() {

        Note n = new Note(1, 21, "A", 0);
        Question q = new Question(List.of(n), QuestionDifficult.EASY, InstrumentType.GUITAR, n);
        Quiz quiz = new Quiz(List.of(q), "Temporary Quiz", "To be deleted");

        QuizDAO.saveQuiz(quiz);


        List<Quiz> before = QuizDAO.loadQuiz();
        assertFalse(before.isEmpty(), "Before drop must be 1 quiz");


        SqlDAO.dropTables();


        List<Quiz> after = QuizDAO.loadQuiz();
        assertTrue(after.isEmpty(), "Must be null");
    }
}
