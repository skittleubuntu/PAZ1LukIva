package org.example.pazduolingo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.sound.midi.MidiUnavailableException;
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
