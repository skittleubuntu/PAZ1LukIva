package org.example.pazduolingo.Main;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.example.pazduolingo.Settings.SettingsWindow;
import org.example.pazduolingo.Stats.StatsWindow;
import org.example.pazduolingo.Training.TrainingWindow;
import org.example.pazduolingo.QuizClass.QuizWindow;


public class MainSceneController {

    @FXML
    private Button editorButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button statsButton;

    @FXML
    private Button trainingButton;

    @FXML
    private ComboBox<String> quizFilter;

    @FXML
    void initialize() {

        quizFilter.getItems().addAll("Default", "Custom");
        quizFilter.setValue("Default");

        trainingButton.setOnAction(event -> {


            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();;

            try {
                TrainingWindow trainingApp = new TrainingWindow();
                Stage trainingStage = new Stage();
                trainingApp.start(trainingStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        editorButton.setOnAction(event -> {

            try {
                org.example.pazduolingo.QuizEditor.QuizEditorWindow quizEditor = new org.example.pazduolingo.QuizEditor.QuizEditorWindow();
                Stage quizStage = new Stage();
                quizEditor.start(quizStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        statsButton.setOnAction(event -> {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                StatsWindow statsWindow = new StatsWindow();
                Stage statsStage = new Stage();
                statsWindow.start(statsStage);
            } catch (Exception e){
                e.printStackTrace();
            }
                });

        settingsButton.setOnAction(event -> {

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            try {
                SettingsWindow settingsWindow = new SettingsWindow();
                Stage settingsStage = new Stage();
                settingsWindow.start(settingsStage);
            } catch (Exception e) {
                e.printStackTrace();

            }
        });
    }

}
