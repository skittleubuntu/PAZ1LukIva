package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.Note;
import org.junit.jupiter.api.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TestNoteDAO {



    @Test
    void testGetAllNotes_NotEmpty() {
        List<Note> notes = NoteDAO.getAllNotes();
        assertNotNull(notes);
        assertFalse(notes.isEmpty(), "List must be full");
    }

    @Test
    void testGetRandomNote_ExistsInList() {
        Note random = NoteDAO.getRandomNote();
        assertTrue(NoteDAO.getAllNotes().contains(random),
                "random note must be in list");
    }

    @Test
    void testGetRandomNote_DontDUplicate(){
        Note n1 = NoteDAO.getRandomNote();
        Note n2 = NoteDAO.getRandomNote();

        assertTrue(n1.getId() != n2.getId(), "Notes must be different");

    }

    @Test
    void testGetNoteByID(){
        Note noteID52 = NoteDAO.getNoteByID(52);
        assert noteID52 != null;
        assertEquals(52, noteID52.getId());

    }

}