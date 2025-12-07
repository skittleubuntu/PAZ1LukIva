package org.example.hearo.QuizClass;

import java.util.*;

public class Question {

    private final List<Note> notes;
    private Note correctAnswer;
    private QuestionDifficulty difficult;
    private InstrumentType instrumentType;
    private Note refNote;


    public Question(List<Note> notes, QuestionDifficulty difficult, InstrumentType instrumentType, Note refNote) {
        this.notes = new ArrayList<>(notes);
        this.correctAnswer = pickRandomNote();
        this.difficult = difficult;
        this.instrumentType = instrumentType;
        this.refNote = refNote;
    }



    public Note pickRandomNote() {
        return notes.get(new Random().nextInt(notes.size()));
    }


    public void setCorrectAnswer(){
        this.correctAnswer = pickRandomNote();
        System.out.println("New note: " + correctAnswer.getName());
    }


    public List<Note> getNotes() {
        return notes;
    }

    public Note getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionDifficulty getDifficult(){
        return difficult;
    }
    public InstrumentType getInstrumentType(){
        return instrumentType;
    }
    public Note getRefNote(){
        return refNote;
    }

    public boolean checkAnswer(Note answer) {
        return correctAnswer.equals(answer);
    }


    public String toString() {
       StringBuilder result = new StringBuilder();

       result.append("Question: \n");
       for(Note note : notes){
           result.append(note.toString()).append("\n");

       }
       result.append("Corect answer: ").append(getCorrectAnswer().toString()).append("\n");
       result.append("Instrument type: ").append(instrumentType.toString()).append("    DIFF: ").append(difficult.toString());

       return result.toString();
    }
}
