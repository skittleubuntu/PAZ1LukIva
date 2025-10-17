package org.example.pazduolingo;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

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
    void initialize() {
        lessonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Lessons");
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
