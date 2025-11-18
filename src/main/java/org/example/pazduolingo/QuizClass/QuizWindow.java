package org.example.pazduolingo.QuizClass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class QuizWindow {


    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pazduolingo/Lesson/LessonView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Lesson");
        stage.setScene(scene);
        stage.show();
    }
}
