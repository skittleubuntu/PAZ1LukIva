package org.example.pazduolingo.Main;

import org.example.pazduolingo.QuizClass.Lesson;
import org.example.pazduolingo.QuizClass.Quiz;

import java.util.List;

public class Main {



    public static void main(String[] args){

        Lesson lesson = new Lesson();
        lesson.generateLesson(4,4);

        System.out.println(lesson.toString());
        List<Quiz> quizList = lesson.getQuestions();
        for(Quiz q: quizList){
            System.out.println(q.toString());
        }

    }


}
