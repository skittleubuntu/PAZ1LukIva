package org.example.pazduolingo.QuizEditor;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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


        questionContainer.setSpacing(5);

        VBox questionBox = new VBox(10);
        questionBox.setAlignment(Pos.CENTER);
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


        note1.setMaxWidth(65);
        note2.setMaxWidth(65);
        note3.setMaxWidth(65);
        note4.setMaxWidth(65);


        Button remove = new Button("Remove");
        remove.setMinWidth(80);


        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Easy");
        difficultyBox.setMinWidth(80);
        difficultyBox.setMaxWidth(80);

        System.out.println(true);
        ComboBox<String> instrumentBox = new ComboBox<>();
        instrumentBox.getItems().addAll("Piano", "Guitar", "Violin", "Flute");
        instrumentBox.setValue("Piano");
        difficultyBox.setMinWidth(80);
        difficultyBox.setMaxWidth(80);

        HBox answers = new HBox(10, note1, note2, note3, note4);
        answers.setAlignment(Pos.CENTER);

        HBox options = new HBox(10, difficultyBox, instrumentBox, remove);
        options.setAlignment(Pos.CENTER);

        questionBox.getChildren().addAll(
                questionLabel,
                answers,
                options
        );

        questionContainer.getChildren().add(questionBox);
    }
}
