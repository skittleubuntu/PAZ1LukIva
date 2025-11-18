package org.example.pazduolingo.Training;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.QuizClass.Note;

import java.util.List;

public class TrainingWindow {

    //zaklad pre buduci okienka (ja som tam riesil chyby cca 1 hodinu)
    //TODO!!!!!!!V FXML NEPRIDAVAME CONTROLLER!!!!!!!!!!!


    public void start(Stage stage) throws Exception {
        NoteDAO noteDAO = new NoteDAO();
        List<Note> notes = noteDAO.getAllNotes();

        var url = getClass().getResource("/org/example/pazduolingo/Training/train_view.fxml");
        System.out.println("FXML URL: " + url);

        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Parent rootPane = fxmlLoader.load();


        TrainingController controller = fxmlLoader.getController();
        controller.setNotes(notes);

        Scene scene = new Scene(rootPane);
        stage.setTitle("Training");
        stage.setScene(scene);
        stage.show();
    }
}
