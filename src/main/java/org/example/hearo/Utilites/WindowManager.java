package org.example.hearo.Utilites;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.hearo.DateAO.SettingsDAO;
import org.example.hearo.Settings.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WindowManager {


    private static WindowManager instance;

    private final List<StageData> stages;

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
        String themeFile = "/org/example/hearo/Styles/" + (settings.Theme).toLowerCase() + "_theme.css";
        String cssUrl = Factory.class.getResource(themeFile).toExternalForm();


        for (StageData stageData : stages) {
            Scene scene = stageData.stage().getScene();
            scene.getStylesheets().clear();
            scene.getStylesheets().add(cssUrl);
        }
    }


    public void reloadAllStages() {
        for (StageData stageData : stages) {
            Scene scene = stageData.stage().getScene();

            if (scene == null || stageData.fxmlPath() == null) {
                continue;
            }

            try {
                URL fxmlUrl = new URL(stageData.fxmlPath());

                FXMLLoader loader = new FXMLLoader(fxmlUrl, LanguageManager.getInstance().getResourceBundle());
                loader.setController(stageData.controller());
                Parent root = loader.load();
                scene.setRoot(root);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void openWindow(String fxmlPath, Object controller, String title, Modality modality) throws Exception {

        URL fxmlUrl = getClass().getResource(fxmlPath);
        if (fxmlUrl == null) {
            throw new Exception("FXML resource not found: " + fxmlPath);
        }

        Stage stage = new Stage();

        StageData stageData = new StageData(stage, fxmlUrl.toExternalForm(), controller);
        stages.add(stageData);

        stage.setOnHidden(event -> {
            stages.remove(stageData);
        });

        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath), LanguageManager.getInstance().getResourceBundle());
        loader.setController(controller);

        Parent root = loader.load();

        Scene scene = new Scene(root);

        Settings settings =  SettingsDAO.loadSettings();
        String themeFile = "/org/example/hearo/Styles/" + (settings.Theme).toLowerCase() + "_theme.css";
        String cssUrl = Factory.class.getResource(themeFile).toExternalForm();

        scene.getStylesheets().clear();
        scene.getStylesheets().add(cssUrl);

        stage.setTitle(title);
        stage.setScene(scene);

        if (modality != null) {
            stage.initModality(modality);
            stage.showAndWait();
        }
        else {
            stage.show();
        }

    }
}
