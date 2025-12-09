package org.example.pazduolingo;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.pazduolingo.Main.MainSceneController;
import org.example.pazduolingo.Utilites.LanguageManager;
import org.example.pazduolingo.Utilites.WindowManager;

public class App extends Application {

    public void start(Stage stage) throws Exception {

        WindowManager.initialize();
        LanguageManager.initialize();

        String fxmlPath = "/org/example/pazduolingo/MainView.fxml";
        MainSceneController controller = new MainSceneController();

        WindowManager.getInstance().openWindow(fxmlPath, controller, "Hearo", null);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
