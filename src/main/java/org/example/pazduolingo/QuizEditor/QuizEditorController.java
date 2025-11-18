package org.example.pazduolingo.QuizEditor;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.QuizClass.Note;

public class QuizEditorController {

    @FXML private TextField quizTitle;
    @FXML private TextArea quizDescription;
    @FXML private VBox questionContainer;
    @FXML private Button addQuestionButton;

    @FXML
    public void initialize() {
        addQuestionButton.setOnAction(e -> addQuestion());
    }

    private void addQuestion() {




        VBox questionBox = new VBox(10);
        questionBox.setPadding(new Insets(10));
        questionBox.setStyle("-fx-border-color: gray; -fx-border-radius: 5; -fx-border-width: 1;");

        Label questionLabel = new Label("Question  " + (questionContainer.getChildren().size() + 1));

        ComboBox<String> note1 = new ComboBox<>();
        ComboBox<String> note2 = new ComboBox<>();
        ComboBox<String> note3 = new ComboBox<>();
        ComboBox<String> note4 = new ComboBox<>();

        //todo
        for (Note note : NoteDAO.getAllNotes()){
            note1.getItems().add(note.getName());
            note2.getItems().add(note.getName());
            note3.getItems().add(note.getName());
            note4.getItems().add(note.getName());
        }


        note1.setMaxWidth(Double.MAX_VALUE);
        note2.setMaxWidth(Double.MAX_VALUE);
        note3.setMaxWidth(Double.MAX_VALUE);
        note4.setMaxWidth(Double.MAX_VALUE);


        Button freqNote = new Button("freqNote");
        freqNote.setMaxWidth(Double.MAX_VALUE);


        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Easy");

        System.out.println(true);
        ComboBox<String> instrumentBox = new ComboBox<>();
        instrumentBox.getItems().addAll("Piano", "Guitar", "Violin", "Flute");
        instrumentBox.setValue("Piano");

        HBox options = new HBox(10, difficultyBox, instrumentBox);

        questionBox.getChildren().addAll(
                questionLabel,
                note1, note2, note3, note4,
                freqNote,
                options
        );

        questionContainer.getChildren().add(questionBox);
    }
}
