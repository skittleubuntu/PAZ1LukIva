package org.example.pazduolingo.QuizClass;

import org.example.pazduolingo.DateAO.NoteDAO;

import java.util.*;

public class Question {

    private final List<Note> notes;
    private final Note correctAnswer;


    public Question(List<Note> notes) {
        this.notes = new ArrayList<>(notes);
        this.correctAnswer = NoteDAO.getRandomNote();
    }



    private Note pickRandomNote() {
        return notes.get(new Random().nextInt(notes.size()));
    }

    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    public Note getCorrectAnswer() {
        return correctAnswer;
    }


    public boolean checkAnswer(Note answer) {
        return correctAnswer.equals(answer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("type: ").append("\nnotes: ");
        int i = 0;
        for (Note note : notes) {
            sb.append(note.getName()).append(note.getOctave());
            if (i < notes.size() - 1) sb.append(", ");
            i++;
        }
        return sb.toString();
    }
}
