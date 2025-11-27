package org.example.pazduolingo.QuizEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pazduolingo.Utilites.WindowManager;

public class QuizEditorWindow{


    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/pazduolingo/QuizEditor/QuizEditorView.fxml"));
        Parent rootPane = fxmlLoader.load();

//        SettingsController controller = fxmlLoader.getController();

        Scene scene = new Scene(rootPane);
        stage.setTitle("QuizEditor");
        stage.setScene(scene);

        WindowManager.getInstance().addStage(stage);
        WindowManager.getInstance().setTheme();

        stage.show();
    }
}