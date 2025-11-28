package org.example.pazduolingo.Main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pazduolingo.Utilites.WindowManager;

public class IntroQuizWindow {

    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/pazduolingo/Settings/settings.fxml"));
        Parent rootPane = fxmlLoader.load();

//        SettingsController controller = fxmlLoader.getController();

        Scene scene = new Scene(rootPane);
        stage.setTitle("Intro");
        stage.setScene(scene);

        WindowManager.getInstance().addStage(stage);
        WindowManager.getInstance().setTheme();

        stage.show();
    }

}
