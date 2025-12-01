package org.example.pazduolingo.QuizClass;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pazduolingo.Utilites.WindowManager;

public class QuizWindow {


    public void start(Stage stage, int id) throws Exception {



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/pazduolingo/Quiz/QuizView.fxml"));
        Scene scene = new Scene(loader.load());

        QuizController quizController = loader.getController();
        quizController.setQuizId(id);

        stage.setTitle("Lesson");
        stage.setScene(scene);

        WindowManager.getInstance().addStage(stage);
        WindowManager.getInstance().setTheme();

        stage.show();
    }
}
