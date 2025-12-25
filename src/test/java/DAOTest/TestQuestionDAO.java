package DAOTest;

import org.example.pazduolingo.DateAO.*;
import org.example.pazduolingo.QuizClass.*;
import org.example.pazduolingo.Utilites.Factory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestQuestionDAO {

    private QuestionDAO questionDAO;
    private QuizDAO quizDAO;

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
        questionDAO = Factory.getQuestionDao();
        quizDAO = Factory.getQuizDao();
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
        quizDAO.saveQuiz(quiz);
    }

    @Test
    void testLoadQuestionForQuiz() {
        List<Question> loadedQuestions = questionDAO.loadQuestionForQuiz(1);
        assertEquals(questions.size(), loadedQuestions.size());

        for (int i = 0; i < questions.size(); i++) {
            assertEquals(questions.get(i).getNotes().size(), loadedQuestions.get(i).getNotes().size());
        }
    }

    @Test
    void testSaveQuestionToQuiz() {
        Question newQuestion = new Question(notes, QuestionDifficulty.EASY, InstrumentType.PIANO, n3);
        questionDAO.saveQuestion(1, newQuestion);

        Quiz loadedQuiz = quizDAO.loadQuizByID(1);
        assertNotNull(loadedQuiz);
        assertEquals(3, loadedQuiz.getQuestions().size());
    }

    @AfterAll
    void cleanup() {
        sqlDAO.dropTables();
    }
}