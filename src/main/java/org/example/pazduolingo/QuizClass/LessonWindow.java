package org.example.pazduolingo.Lesson;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LessonWindow extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //TODO pred zaciatkom option window na vyber relativity

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pazduolingo/Lesson/LessonView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Lesson");
        stage.setScene(scene);
        stage.show();
    }
}
