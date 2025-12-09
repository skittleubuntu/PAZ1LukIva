package org.example.pazduolingo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pazduolingo.Main.MainSceneController;
import org.example.pazduolingo.Utilites.Factory;
import org.example.pazduolingo.Utilites.WindowManager;

import java.util.Locale;
import java.util.ResourceBundle;

public class App extends Application {

    public void start(Stage stage) throws Exception {

        WindowManager.initialize();
        WindowManager windowManager = WindowManager.getInstance();

        //toto je zatial lebo este nie je spraveny LanguageManager
        Locale defaultLocale = new Locale("en");
        ResourceBundle resourceBundle = ResourceBundle.getBundle("lang", defaultLocale);

        MainSceneController controller = new MainSceneController();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view.fxml"), resourceBundle);

        fxmlLoader.setController(controller);
        Parent rootPane = fxmlLoader.load();

        Scene scene = new Scene(rootPane);
        stage.setTitle("Application");
        stage.setScene(scene);

        windowManager.addStage(stage);
        windowManager.setTheme();

        stage.show();

        System.out.println(scene.getStylesheets().toString());
    }

    public static void main(String[] args) {
        launch(args);
    }

}
