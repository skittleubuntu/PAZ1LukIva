package org.example.hearo.Utilites;

import org.example.hearo.QuizClass.Note;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Functions {


    public static List<Note> orderByName(List<Note> notes) {
        if (notes == null) return Collections.emptyList();

        List<Note> sortedNotes = new ArrayList<>(notes);

        sortedNotes.sort(Comparator.comparingInt(n -> noteLetterOrder(n.getName())));

        return sortedNotes;
    }


    public static List<Note> filterStandard(List<Note> notes) {
        List<Note> result = new ArrayList<>();
        for (Note n : notes) {
            String name = n.getName();
            if (!name.contains("#") && !name.contains("b")) {
                result.add(n);
            }
        }
        return result;
    }

    public static List<Note> filterDies(List<Note> notes) {
        List<Note> result = new ArrayList<>();
        for (Note n : notes) {
            String name = n.getName();
            if (name.contains("#") || name.contains("b")) {
                result.add(n);
            }
        }
        return result;
    }


    public static int noteLetterOrder(String noteName) {

        char letter = noteName.charAt(0);

        return switch (letter) {
            case 'A' -> 0;
            case 'B' -> 1;
            case 'C' -> 2;
            case 'D' -> 3;
            case 'E' -> 4;
            case 'F' -> 5;
            case 'G' -> 6;
            default -> 999;
        };
    }












}
