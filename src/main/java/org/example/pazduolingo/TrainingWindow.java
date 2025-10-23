package org.example.pazduolingo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;
import java.util.Set;

public class TrainingWindow {
    //zaklad pre buduci okienka (ja som tam riesil chyby cca 1 hodinu)
    //TODO!!!!!!!V FXML NEPRIDAVAME CONTROLLER!!!!!!!!!!!
    public void start(Stage stage) throws Exception {
        NoteDAO noteDAO = new NoteDAO();
        List<Note> notes = noteDAO.getAllNotes();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("train_view.fxml"));
        Parent rootPane = fxmlLoader.load();


        TrainingController controller = fxmlLoader.getController();
        controller.setNotes(notes);

        Scene scene = new Scene(rootPane);
        stage.setTitle("Training");
        stage.setScene(scene);
        stage.show();
    }
}
