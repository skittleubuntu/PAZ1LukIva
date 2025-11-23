package org.example.pazduolingo.QuizClass;

import org.example.pazduolingo.DateAO.NoteDAO;

import java.util.*;

public class Question {

    private final List<Note> notes;
    private final Note correctAnswer;
    private QuestionDifficult difficult;
    private InstrumentType instrumentType;
    private Note freqNote;


    public Question(List<Note> notes, QuestionDifficult difficult, InstrumentType instrumentType, Note freqNote) {
        this.notes = new ArrayList<>(notes);
        this.correctAnswer = pickRandomNote();
        this.difficult = difficult;
        this.instrumentType = instrumentType;
        this.freqNote = freqNote;

    }



    public Note pickRandomNote() {
        return notes.get(new Random().nextInt(notes.size()));
    }

    public List<Note> getNotes() {
        return notes;
    }

    public Note getCorrectAnswer() {
        return correctAnswer;
    }

    public QuestionDifficult getDifficult(){
        return difficult;
    }
    public InstrumentType getInstrumentType(){
        return instrumentType;
    }
    public Note getFreqNote(){
        return freqNote;
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
