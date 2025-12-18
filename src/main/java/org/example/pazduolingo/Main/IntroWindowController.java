package org.example.pazduolingo.Main;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Modality;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.QuizClass.QuizController;
import org.example.pazduolingo.Utilites.WindowManager;

public class IntroWindowController {


    private Quiz quiz;


    @FXML
    private Label nameLabel;

    @FXML
    private Label descLabel;

    @FXML
    private ProgressBar accBar;

    @FXML
    private Button startButton;


    public void setQuiz(Quiz quiz){
        this.quiz = quiz;
    }


    @FXML
    void initialize(){



        //65

        nameLabel.setText(quiz.getName());
        descLabel.setText(quiz.getDescription());
        accBar.setProgress((double) StatsDAO.getQuizAccuracy(quiz.getID()) /100);



        startButton.setOnAction(ev->{


            try {
                String fxmlPath = "/org/example/pazduolingo/Quiz/QuizView.fxml";
                QuizController quizController = new QuizController();
                quizController.setQuizId(quiz.getID());
                startButton.getScene().getWindow().hide();
                WindowManager.getInstance().openWindow(fxmlPath, quizController, quiz.getName(), null);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }



        });

    }






}
