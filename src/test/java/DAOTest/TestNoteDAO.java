package DAOTest;

import org.example.hearo.DateAO.NoteDAO;
import org.example.hearo.DateAO.QuizDAO;
import org.example.hearo.DateAO.SqlDAO;
import org.example.hearo.QuizClass.*;
import org.example.hearo.Utilites.Factory;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestNoteDAO {

    //test data
    Note n1 = new Note(1, 21, "A", 0);
    Note n2 = new Note(2, 22, "A#", 0);
    Note n3 = new Note(3, 23, "B", 0);
    List<Note> notes = List.of(n1, n2, n3);
    Note n4 = Factory.getFloatNote(n2, NoteDAO.getAllNotes());

    Question q1 = new Question(notes, QuestionDifficulty.EASY, InstrumentType.GUITAR, n2);
    Question q2 = new Question(notes, QuestionDifficulty.MEDIUM, InstrumentType.VIOLIN, n1);
    List<Question> questions = List.of(q1, q2);

    Quiz quiz = new Quiz(1,questions, "Test Quiz", "Test save of quiz");


    @Test
    void testGetAllNotes_NotEmpty() {


            List<Note> notes = NoteDAO.getAllNotes();

            assertNotNull(notes);
            assertFalse(notes.isEmpty());


    }


    @Test
    void testGetRandomNote_ExistsInList() {


        Note random = NoteDAO.getRandomNote();

        assertTrue(NoteDAO.getAllNotes().contains(random),
                "random note must be in list");
    }





    @Test
    void testGetRandomNote_DontDuplicate() {

            Note n1 = NoteDAO.getRandomNote();
            Note n2 = NoteDAO.getRandomNote();

            assertTrue(n1.getId() != n2.getId(), "notes must be different");

    }


    @Test
    void testGetNoteByID() {
            Note noteID52 = NoteDAO.getNoteByID(52);
            assertNotNull(noteID52);

            assertEquals(52, noteID52.getId());


    }


    @Test
    void testGetNotesFromQuiz() {

            SqlDAO.dropTables();
            QuizDAO.saveQuiz(quiz);
            Connection conn = SqlDAO.getConnection();
            List<Note> notes = NoteDAO.loadNotesForQuestion(conn, 1);
            assertEquals(notes, this.notes);

    }


    @Test
    void testGetNoteByName() {

            Note testNote = NoteDAO.getNoteByName("A0");
            Note testFloatNote = NoteDAO.getNoteByName("B♭0");
            assertNotNull(testNote);
            assertNotNull(testFloatNote);

            assertEquals(testNote, n1);
            assertEquals(testFloatNote,n4);



    }
}
