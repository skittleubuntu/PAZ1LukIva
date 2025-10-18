package org.example.pazduolingo;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainSceneController {

    @FXML
    private Button editorButton;

    @FXML
    private Button lessonButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button statsButton;

    @FXML
    private Button trainingButton;

    @FXML
    void initialize() {
        lessonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Lessons");
            }
        });

        trainingButton.setOnAction(event -> {
            System.out.println("Opening Training window...");


            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            currentStage.close();

            try {
                TrainingWindow trainingApp = new TrainingWindow();
                Stage trainingStage = new Stage();
                trainingApp.start(trainingStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        editorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Quiz editor");
            }
        });

        statsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Stats");
            }
        });

        settingsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Settings");
            }
        });
    }

}
