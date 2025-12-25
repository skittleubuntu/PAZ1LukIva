package org.example.pazduolingo.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;

import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.QuizClass.QuizController;
import org.example.pazduolingo.Utilites.Functions;
import org.example.pazduolingo.Utilites.WindowManager;

public class IntroWindowController {

    private Quiz quiz;

    @FXML
    private Label nameLabel;

    @FXML
    private Label descLabel;

    @FXML
    private Label notesLabel;

    @FXML
    private Button startButton;

    public void setQuiz(Quiz quiz){
        this.quiz = quiz;
    }

    @FXML
    void initialize(){
        nameLabel.setText(quiz.getName());
        descLabel.setText(quiz.getDescription());
        notesLabel.setText(Functions.allNotesFromQuiz(quiz));

        startButton.setOnAction(ev->{

            try {
                String fxmlPath = "/org/example/pazduolingo/Quiz/QuizView.fxml";
                QuizController quizController = new QuizController();
                quizController.setQuizId(quiz.getID());
                WindowManager.getInstance().openWindow(fxmlPath, quizController, quiz.getName(), Modality.APPLICATION_MODAL);
                WindowManager.closeWindow(startButton);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });

    }

}
