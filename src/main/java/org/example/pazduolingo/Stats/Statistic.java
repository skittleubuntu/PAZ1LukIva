package org.example.pazduolingo.Stats;

import org.example.pazduolingo.QuizClass.Quiz;

public class Statistic {
    private String name;
    private String value;
    private Quiz quiz;

    public Statistic(String name, String value, Quiz quiz) {
        this.name = name;
        this.value = value;
        this.quiz = quiz;
    }

    public Statistic(String name, String value) {
        this.name = name;
        this.value = value;
        this.quiz = null;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public Quiz getQuiz() {
        return quiz;
    }

}
