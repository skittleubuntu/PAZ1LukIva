package org.example.pazduolingo.QuizClass;

import org.example.pazduolingo.DateAO.NoteDAO;

import java.util.*;

public class Quiz {

    private List<Question> questions = new ArrayList<>();
    private String name;
    private String description;



    public Quiz(List<Question> questions, String name, String description){
        this.questions = questions;
        this.name = name;
        this.description = description;
    }



    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }



    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Name: ").append(name).append("\n");
        sb.append("Des: ").append(description).append("\n");

        for (Question q : questions) {
            sb.append(q.toString()).append("\n");
        }
        return sb.toString();
    }
}
