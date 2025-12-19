package org.example.pazduolingo.QuizClass;

import java.util.*;

public class Quiz {

    private int id;
    private List<Question> questions = new ArrayList<>();
    private String name;
    private String description;

    public Quiz(int id,List<Question> questions, String name, String description){
        this.id = id;
        this.questions = questions;
        this.name = name;
        this.description = description;
    }

    public int getID(){
        return id;
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

    @Override
    public String toString() {
        return name;
    }
}
