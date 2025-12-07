package org.example.hearo.Utilities;

import org.example.hearo.DateAO.NoteDAO;
import org.example.hearo.QuizClass.Note;
import org.example.hearo.Utilites.Functions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FunctionsTest {

    @Test
    void testOrderByName() {

        List<Note> notes = List.of(
                NoteDAO.getNoteByName("A#1"),
                NoteDAO.getNoteByName("B1"),
                NoteDAO.getNoteByName("F4"),
                NoteDAO.getNoteByName("C#3")
        );

        List<Note> sorted = Functions.orderByName(notes);


        assertEquals('A', sorted.get(0).getName().charAt(0));
        assertEquals('B', sorted.get(1).getName().charAt(0));
        assertEquals('C', sorted.get(2).getName().charAt(0));
        assertEquals('F', sorted.get(3).getName().charAt(0));
    }

    @Test
    void testFilterStandard() {

        List<Note> notes = NoteDAO.getAllNotes();

        List<Note> result = Functions.filterStandard(notes);

        assertTrue(result.stream().noneMatch(n -> n.getName().contains("#")));


        assertTrue(result.size() > 0);
    }

    @Test
    void testFilterDies() {

        List<Note> notes = NoteDAO.getAllNotes();

        List<Note> result = Functions.filterDies(notes);


        assertTrue(result.size() > 0);
        assertTrue(result.stream().allMatch(n ->
                n.getName().contains("#"))
        );
    }

    @Test
    void testNoteLetterOrder() {
        assertEquals(0, Functions.noteLetterOrder("A"));
        assertEquals(1, Functions.noteLetterOrder("B"));
        assertEquals(2, Functions.noteLetterOrder("C"));
        assertEquals(3, Functions.noteLetterOrder("D"));
        assertEquals(4, Functions.noteLetterOrder("E"));
        assertEquals(5, Functions.noteLetterOrder("F"));
        assertEquals(6, Functions.noteLetterOrder("G"));


        assertEquals(999, Functions.noteLetterOrder("H"));
        assertEquals(999, Functions.noteLetterOrder("Z"));
    }
}
