package org.example.pazduolingo.QuizEditor;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
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

    private void removeQuestion(int questionIndex){
        questionContainer.getChildren().remove(questionIndex);

        for (int i = 0; i < questionContainer.getChildren().size(); i++) {
            VBox questionBox = (VBox) questionContainer.getChildren().get(i);
            questionBox.setUserData(i);

            Label label = (Label) questionBox.getChildren().get(0);
            label.setText("Question " + (i + 1));

            HBox options = (HBox) questionBox.getChildren().get(2);
            Button removeBtn = (Button) options.getChildren().get(2);
            int finalI = i;
            removeBtn.setOnAction(e -> removeQuestion(finalI));
        }
    }

    private void addQuestion() {

        questionContainer.setSpacing(5);

        int index = questionContainer.getChildren().size();

        VBox questionBox = new VBox(20);
        questionBox.setAlignment(Pos.CENTER);
        questionBox.setPadding(new Insets(10));
        questionBox.setStyle("-fx-border-color: gray; -fx-border-radius: 5; -fx-border-width: 1;");
        questionBox.setUserData(index);

        Label questionLabel = new Label("Question " + (index + 1));

        ComboBox<String> note1 = new ComboBox<>();
        ComboBox<String> note2 = new ComboBox<>();
        ComboBox<String> note3 = new ComboBox<>();
        ComboBox<String> note4 = new ComboBox<>();

        for (Note note : NoteDAO.getAllNotes()){
            note1.getItems().add(note.getName());
            note2.getItems().add(note.getName());
            note3.getItems().add(note.getName());
            note4.getItems().add(note.getName());
        }

        note1.setMaxWidth(75);
        note2.setMaxWidth(75);
        note3.setMaxWidth(75);
        note4.setMaxWidth(75);

        Button remove = new Button("Remove");
        remove.setMinWidth(80);
        remove.setOnAction(e -> removeQuestion(index));

        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Easy");
        difficultyBox.setMinWidth(80);
        difficultyBox.setMaxWidth(80);

        ComboBox<String> instrumentBox = new ComboBox<>();
        instrumentBox.getItems().addAll("Piano", "Guitar", "Violin", "Flute");
        instrumentBox.setValue("Piano");
        instrumentBox.setMinWidth(80);
        instrumentBox.setMaxWidth(80);

        HBox answers = new HBox(10, note1, note2, note3, note4);
        answers.setAlignment(Pos.CENTER);

        HBox options = new HBox(10, difficultyBox, instrumentBox, remove);
        options.setAlignment(Pos.CENTER);

        questionBox.getChildren().addAll(questionLabel, answers, options);

        questionContainer.getChildren().add(questionBox);
    }
}
