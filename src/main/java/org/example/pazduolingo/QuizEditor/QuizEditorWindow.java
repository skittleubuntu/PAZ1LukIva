package org.example.pazduolingo.QuizEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuizEditorWindow{


    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pazduolingo/QuizEditor/QuizEditorView.fxml"));
        Parent root = loader.load();

        stage.setTitle("Quiz Editor");
        stage.setScene(new Scene(root, 700, 600));
        stage.show();
    }
}