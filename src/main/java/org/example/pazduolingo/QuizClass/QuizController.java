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

public class QuizController {

    @FXML
    private Label questionLabel;

    @FXML
    private Button noteQuestionButton;

    @FXML
    private HBox answersContainer;

    private final Quiz quiz = new Quiz();
    private Question currentQuestion;

    @FXML
    void initialize() {
        startNewQuiz();
    }

    private void startNewQuiz() {
        //todo set number of notes
        quiz.generateLesson(1, 4);
        currentQuestion = quiz.getQuestions().get(0);
        showQuiz();
    }



    private void showQuiz() {

        playQuestion(currentQuestion.getCorrectAnswer());
        noteQuestionButton.setText("PLAY");
        answersContainer.getChildren().clear();
        noteQuestionButton.setOnAction(event -> playQuestion(currentQuestion.getCorrectAnswer()));
        List<Note> noteList = new ArrayList<>(currentQuestion.getNotes());

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
        if (currentQuestion.checkAnswer(note)) {
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
