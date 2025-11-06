package org.example.pazduolingo.QuizClass;

import org.example.pazduolingo.DateAO.NoteDAO;

import java.util.*;

public class Quiz {

    private final List<Question> questions = new ArrayList<>();








    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Question q : questions) {
            sb.append(q).append("\n");
        }
        return sb.toString();
    }
}
