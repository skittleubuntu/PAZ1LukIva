package org.example.pazduolingo.Utilites;

import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.QuizClass.Question;
import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.Settings.Settings;

import java.util.*;

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

    public static String allNotesFromQuiz(Quiz quiz){
        String result = "";
        Set<Note> notesSet = new HashSet<>();
        Settings settings = SettingsDAO.loadSettings();
        List<Note> allNotes = NoteDAO.getAllNotes();
        for (Question q : quiz.getQuestions()) {
            for (Note n : q.getNotes()) {

                if(settings.Type.equals("#")) {
                    notesSet.add(n);
                }
                else{
                    notesSet.add(Factory.getFloatNote(n,allNotes));
                }
            }
        }

        List<Note> notesList = new ArrayList<>(notesSet);

        for (int i = 0; i < notesList.size(); i++) {
            if (i < notesList.size() - 1) {
                result += notesList.get(i).getName() + ", ";
                continue;
            }
            result += notesList.get(i).getName();
        }

        return result;
    }

    public static List<Note> allNotesFromQuizList(Quiz quiz){
        Set<Note> notesSet = new HashSet<>();

        for (Question q : quiz.getQuestions()) {
            for (Note n : q.getNotes()) {
                notesSet.add(n);
            }
        }

        List<Note> notesList = new ArrayList<>(notesSet);

        return notesList;
    };
    
}
