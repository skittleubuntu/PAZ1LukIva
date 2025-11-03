package org.example.pazduolingo.QuizClass;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.example.pazduolingo.Utilites.Factory;
import org.example.pazduolingo.Utilites.Sounder;

import java.util.ArrayList;
import java.util.List;

public class LessonController {

    @FXML
    private Label questionLabel;

    @FXML
    private Button noteQuestionButton;

    @FXML
    private HBox answersContainer;

    private final Lesson lesson = new Lesson();
    private Quiz currentQuiz;

    @FXML
    void initialize() {
        startNewQuiz();
    }

    private void startNewQuiz() {
        //todo set number of notes
        lesson.generateLesson(1, 4);
        currentQuiz = lesson.getQuestions().get(0);
        showQuiz();
    }



    private void showQuiz() {

        playQuestion(currentQuiz.getCorrectAnswer());
        noteQuestionButton.setText("PLAY");
        answersContainer.getChildren().clear();
        noteQuestionButton.setOnAction(event -> playQuestion(currentQuiz.getCorrectAnswer()));
        List<Note> noteList = new ArrayList<>(currentQuiz.getNotes());

        for (Note note : noteList) {
            Button answerButton = new Button(note.getName());
            answerButton.setPrefWidth(150);
            answerButton.setPrefHeight(70);
            answerButton.setStyle("-fx-font-size: 18px;");
            HBox.setMargin(answerButton, new Insets(5));

            answerButton.setOnAction(event -> handleAnswer(note));
            answersContainer.getChildren().add(answerButton);
        }
    }

    private void handleAnswer(Note note) {
        if (currentQuiz.checkAnswer(note)) {
            questionLabel.setText("✅ Correct! Next question...");
            startNewQuiz();
        } else {
            questionLabel.setText("❌ Wrong! Try again...");
        }
    }


    private void playQuestion(Note note){



            System.out.println("Clicked: " + note.getName());

            //todo setting type of instrumental
            InstrumentType type = InstrumentType.valueOf("PIANO".toUpperCase());


            Sounder sounder = Factory.createSounder(type);

            new Thread(() -> sounder.play(note.getMidiNumber(), 100)).start();

    }

}
