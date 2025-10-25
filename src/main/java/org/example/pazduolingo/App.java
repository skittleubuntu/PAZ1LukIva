package org.example.pazduolingo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pazduolingo.Main.MainSceneController;

public class App extends Application {

    public void start(Stage stage) throws Exception {

        MainSceneController controller = new MainSceneController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"));
        fxmlLoader.setController(controller);
        Parent rootPane = fxmlLoader.load();

        Scene scene = new Scene(rootPane);
        stage.setTitle("Application"); //nazov aplikacie
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
