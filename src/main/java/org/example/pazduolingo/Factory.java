package org.example.pazduolingo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Factory {





    public static List<Note> orderByName(List<Note> notes) {
        if (notes == null) {
            return Collections.emptyList();
        }


        List<Note> sortedNotes = new ArrayList<>(notes);
        sortedNotes.sort(Comparator.comparing(Note::getName));
        return sortedNotes;
    }

    public static List<Note> orderByOctave(List<Note> notes) {
        if (notes == null) {
            return Collections.emptyList();
        }


        List<Note> sortedNotes = new ArrayList<>(notes);
        sortedNotes.sort(Comparator.comparing(Note::getOctave));
        return sortedNotes;
    }

}
