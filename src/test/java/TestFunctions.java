package org.example.pazduolingo.DateAO;

import org.example.pazduolingo.QuizClass.*;
import org.example.pazduolingo.Utilites.Functions;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


//todo
class TestFunctions {


    //test list for test
    Note n1 = new Note(1, 21, "A", 0);
    Note n2 = new Note(2, 22, "A#", 0);
    Note n3 = new Note(3, 23, "B", 0);
    List<Note> notes1 = List.of(n1, n2, n3);
    List<Note> notes2 = List.of(n1, n3);


    @Test
    void getListWithouNote() {
        List<Note> notes3 = Functions.getListWithoutNote(notes1, n2);
        assertEquals(notes2,notes3);
    }




}
