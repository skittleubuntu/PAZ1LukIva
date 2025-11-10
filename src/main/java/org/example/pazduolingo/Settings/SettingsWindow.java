package org.example.pazduolingo.Settings;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SettingsWindow {

    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/pazduolingo/Settings/settings.fxml"));
        Parent rootPane = fxmlLoader.load();

//        SettingsController controller = fxmlLoader.getController();

        Scene scene = new Scene(rootPane);
        stage.setTitle("Settings");
        stage.setScene(scene);
        stage.show();
    }
}
