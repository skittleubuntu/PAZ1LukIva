package org.example.hearo;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.hearo.Main.MainSceneController;
import org.example.hearo.Utilites.LanguageManager;
import org.example.hearo.Utilites.WindowManager;

public class App extends Application {

    public void start(Stage stage) throws Exception {

        WindowManager.initialize();
        LanguageManager.initialize();

        String fxmlPath = "/org/example/hearo/MainView.fxml";
        MainSceneController controller = new MainSceneController();

        WindowManager.getInstance().openWindow(fxmlPath, controller, "Hearo", null);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
