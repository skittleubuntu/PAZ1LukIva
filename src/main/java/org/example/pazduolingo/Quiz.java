package org.example.pazduolingo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Quiz {

    private Set<Note> notes;
    private Note correctAnswer;
    private QuestionType type;

    private List<Note> n;

    public Set<Note> getNotes() {
        return  new HashSet<Note>(notes);
    }

    public Note getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionType getType() {
        return type;
    }

    public Quiz(QuestionType type) {
        pickNote();
        this.type = type;
    }


    public void loadNotes(Set<Note> selectedNotes){
        Set<Note> notes = new HashSet<>();
        this.notes = notes;
    }

    //neviem ci to ma byt v tejto triede
    public void options(){
        for (Note note: notes){
            //pre kazdu notu zo setu vytvori tlacidlo na stlacenie
        }
    }

    public void pickNote(){
        for(Note note : notes){
            this.correctAnswer = note;
            return;
        }
    }

    //neviem ci je potrebne ked som implementoval equals a hash v triede Note
    public boolean result(Note answer){
        if (answer == correctAnswer){
            return true;
        }
        return false;
    }

}
