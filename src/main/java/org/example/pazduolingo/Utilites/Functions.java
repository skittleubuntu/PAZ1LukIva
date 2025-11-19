package org.example.pazduolingo.Utilites;

import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.QuizClass.Quiz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Functions {


    public static List<Note> orderByName(List<Note> notes) {
        if (notes == null) {
            return Collections.emptyList();
        }


        List<Note> sortedNotes = new ArrayList<>(notes);
        sortedNotes.sort(Comparator.comparing(Note::getName));
        return sortedNotes;
    }


    public static List<Note> getListWithoutNote(List<Note> note, Note removedNote){
        //todo
        return note;
    }


    public static List<Note> getNotesInQuiz(Quiz quiz){
        //todo
        List<Note> note = new ArrayList<>();
        return note;
    }







}
