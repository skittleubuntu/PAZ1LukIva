package DAOTest;

import org.example.pazduolingo.DateAO.*;
import org.example.pazduolingo.QuizClass.*;
import org.example.pazduolingo.Utilites.Factory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestQuizDAO {

    private QuizDAO quizDAO;
    private QuestionDAO questionDAO;
    private NoteDAO noteDAO;

    private Note n1, n2, n3;
    private List<Note> notes;
    private Question q1, q2;
    private List<Question> questions;
    private Quiz quiz;
    private SqlDAO sqlDAO;

    @BeforeAll
    void setupFactory() {
        Factory.setDatabaseMode(DatabaseProfile.TEST);
        sqlDAO = Factory.getSQLDao();
        sqlDAO.dropTables();
        quizDAO = Factory.getQuizDao();
        questionDAO = Factory.getQuestionDao();
        noteDAO = Factory.getNoteDao();
    }

    @BeforeEach
    void setupData() {
        sqlDAO.dropTables();


        n1 = new Note(1, 21, "A", 0);
        n2 = new Note(2, 22, "A#", 0);
        n3 = new Note(3, 23, "B", 0);
        notes = List.of(n1, n2, n3);

        q1 = new Question(notes, QuestionDifficulty.EASY, InstrumentType.GUITAR, n2);
        q2 = new Question(notes, QuestionDifficulty.MEDIUM, InstrumentType.VIOLIN, n1);
        questions = List.of(q1, q2);

        quiz = new Quiz(1, questions, "Test Quiz", "Test save of quiz");
    }

    @Test
    @DisplayName("Save quiz")
    void testSaveQuiz() {
        quizDAO.saveQuiz(quiz);
        List<Quiz> quizzes = quizDAO.loadQuiz();

        assertEquals(1, quizzes.size());
        assertEquals("Test Quiz", quizzes.get(0).getName());
    }

    @Test
    @DisplayName("Load quiz")
    void testLoadQuiz() {
        Note n = new Note(1, 21, "A", 0);
        List<Note> noteList = List.of(n);
        Question q = new Question(noteList, QuestionDifficulty.EASY, InstrumentType.GUITAR, n);
        Quiz newQuiz = new Quiz(2, List.of(q), "Another Quiz", "Simple test quiz");

        quizDAO.saveQuiz(newQuiz);

        List<Quiz> loaded = quizDAO.loadQuiz();
        assertFalse(loaded.isEmpty());
        assertEquals("Another Quiz", loaded.get(0).getName());
        assertNotNull(loaded.get(0).getQuestions());
    }

    @Test
    void testLoadQuizByID() {
        quizDAO.saveQuiz(quiz);
        Quiz loadedQuiz = quizDAO.loadQuizByID(1);

        assertNotNull(loadedQuiz);
        assertEquals(quiz.getQuestions().get(0).getNotes().size(),
                loadedQuiz.getQuestions().get(0).getNotes().size());
    }

    @Test
    void testDeleteQuiz() {
        quizDAO.saveQuiz(quiz);

        assertEquals(1, quizDAO.loadQuiz().size());
        assertEquals(2, questionDAO.loadQuestionForQuiz(1).size());
        assertEquals(3, noteDAO.loadNotesForQuestion(1).size());

        quizDAO.deleteQuiz(quiz);

        assertEquals(0, quizDAO.loadQuiz().size());
        assertEquals(0, questionDAO.loadQuestionForQuiz(1).size());
        assertEquals(0, noteDAO.loadNotesForQuestion(1).size());
    }
}