package org.example.pazduolingo.QuizClass;

import java.util.*;

public class Question {

    private final Set<Note> notes;
    private final Note correctAnswer;


    public Question(Set<Note> notes) {

        this.notes = new HashSet<>(notes);
        this.correctAnswer = pickRandomNote();
    }

    private Note pickRandomNote() {
        List<Note> list = new ArrayList<>(notes);
        return list.get(new Random().nextInt(list.size()));
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
