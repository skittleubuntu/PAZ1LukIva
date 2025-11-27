package org.example.pazduolingo.Utilites;

import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.Settings.Settings;

import java.util.ArrayList;
import java.util.List;

public class WindowManager {

    private static WindowManager instance;

    private final List<Stage> stages;

    private WindowManager() {
        stages = new ArrayList<>();
    }

    public static void initialize() {
        if (instance == null) {
            instance = new WindowManager();
        }
    }

    public static WindowManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("WindowManager has not been initialized");
        }
        return instance;
    }

    public void setTheme() {
        Settings settings = SettingsDAO.loadSettings();
        String themeFile = "/org/example/pazduolingo/Styles/"+ (settings.Theme).toLowerCase() + "_theme.css";
        String cssUrl = Factory.class.getResource(themeFile).toExternalForm();


        for(Stage stage : stages){
            Scene scene = stage.getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(cssUrl);
        }
    }

    public void addStage(Stage stage) {
        stages.add(stage);
    }
}
