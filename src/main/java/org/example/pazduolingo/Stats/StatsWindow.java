package org.example.pazduolingo.Stats;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pazduolingo.Utilites.WindowManager;

public class StatsWindow {

    public void start(Stage stage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/pazduolingo/Stats/StatsView.fxml"));
        Parent rootPane = fxmlLoader.load();

        Scene scene = new Scene(rootPane);
        stage.setTitle("Stats");
        stage.setScene(scene);

        WindowManager.getInstance().addStage(stage);
        WindowManager.getInstance().setTheme();

        stage.show();
    }
}
