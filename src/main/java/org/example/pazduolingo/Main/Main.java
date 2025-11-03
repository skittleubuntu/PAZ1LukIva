package org.example.pazduolingo.Main;

import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.QuizClass.Question;

import java.util.List;

public class Main {



    public static void main(String[] args){

        Quiz quiz = new Quiz();
        quiz.generateLesson(4,4);

        System.out.println(quiz.toString());
        List<Question> questionList = quiz.getQuestions();
        for(Question q: questionList){
            System.out.println(q.toString());
        }

    }


}
