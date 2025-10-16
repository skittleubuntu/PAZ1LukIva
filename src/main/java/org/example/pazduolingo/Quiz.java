package org.example.pazduolingo;

import java.util.*;

public class Quiz {

    private Set<Note> notes;
    private Note correctAnswer;
    private QuestionType type;

    public Set<Note> getNotes() {
        return  new HashSet<Note>(notes);
    }

    public Note getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionType getType() {
        return type;
    }

    public Quiz(QuestionType type, Set<Note> notes) {
        pickNote();
        this.notes = notes;
        this.type = type;
    }




    //neviem ci to ma byt v tejto triede
    public void options(){
        for (Note note: notes){
            //pre kazdu notu zo setu vytvori tlacidlo na stlacenie
        }
    }

    //choose random note
    public void pickNote() {
        if (notes == null || notes.isEmpty()) return;
        List<Note> noteList = new ArrayList<>(notes);
        Random random = new Random();
        int index = random.nextInt(noteList.size());
        this.correctAnswer = noteList.get(index);
    }

    //neviem ci je potrebne ked som implementoval equals a hash v triede Note
    public boolean result(Note answer){
        if (answer == correctAnswer){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("type: ").append(type).append("\n");
        sb.append("notes: ");

        // Перебираємо всі ноти у Set
        int i = 0;
        for (Note note : notes) {
            sb.append(note.getName()).append(note.getOctave());
            if (i < notes.size() - 1) sb.append(", ");
            i++;
        }

        // Можна показати правильну відповідь (опціонально)
        // sb.append("\nПравильна відповідь: ").append(correctAnswer.getName()).append(correctAnswer.getOctave());

        return sb.toString();
    }

}
