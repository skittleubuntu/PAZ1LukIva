package org.example.pazduolingo.Stats;

public class NoteStatistic {
    private String note;
    private int rounds;
    private int correct;
    private int wrong;
    private String accuracy;

    public NoteStatistic(String note, int wrong, int correct, int rounds, String accuracy) {
        this.note = note;
        this.accuracy = accuracy;
        this.wrong = wrong;
        this.correct = correct;
        this.rounds = rounds;
    }

    public String getNote() {
        return note;
    }

    public int getRounds() {
        return rounds;
    }

    public int getCorrect() {
        return correct;
    }

    public int getWrong() {
        return wrong;
    }

    public String getAccuracy() {
        return accuracy;
    }
}
