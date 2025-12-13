package org.example.pazduolingo.QuizEditor;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.pazduolingo.DateAO.FileDAO;
import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.Main.MainSceneController;
import org.example.pazduolingo.QuizClass.*;
import org.example.pazduolingo.Settings.Settings;
import org.example.pazduolingo.Utilites.Factory;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizEditorController {

    @FXML private TextField quizTitle;
    @FXML private TextArea quizDescription;
    @FXML private VBox questionContainer;
    @FXML private Button addQuestionButton;
    @FXML private Button saveButton;

    @FXML
    private Button saveAsButton;


    private List<Note> notes;

    private Settings settings;

    @FXML
    public void initialize() {
        addQuestionButton.setOnAction(e -> addQuestion());
        saveAsButton.setOnAction(e -> saveQuizAsFile());
        saveButton.setOnAction(e -> onSave());
        settings = SettingsDAO.loadSettings();
        notes = NoteDAO.getAllNotes();
    }

    private void removeQuestion(int questionIndex) {
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



//    private void updateComboBox(List<Note> notes, List<ComboBox<String>> comboBoxList){
//
//        //make result with settings
//        List<Note> result = NoteDAO.getAllNotes();
//        if (!settings.Type.equals("#")){
//            result.replaceAll(note -> Factory.getFloatNote(note, NoteDAO.getAllNotes()));
//        }
//
//
//        //remove exists notes
//        for (ComboBox<String> comboBox : comboBoxList){
//            result.remove(NoteDAO.getNoteByName(comboBox.getValue()));
//        }
//
//
//
//        //add for every combobox new list
//        for (ComboBox<String> comboBox : comboBoxList) {
//            String cur = comboBox.getValue();
//            comboBox.getItems().clear();
//
//            for(Note n: result){
//                comboBox.getItems().add(n.getName());
//            }
//
//
//        }
//
//    }

    private void addQuestion() {

        questionContainer.setSpacing(5);
        int index = questionContainer.getChildren().size();

        VBox questionBox = new VBox(20);
        questionBox.getStyleClass().add("questionBox");
        questionBox.setAlignment(Pos.CENTER);
        questionBox.setPadding(new Insets(10));
//        questionBox.setStyle("-fx-border-color: gray; -fx-border-radius: 5; -fx-border-width: 1;");
        questionBox.setUserData(index);

        Label questionLabel = new Label("Question " + (index + 1));


        ComboBox<String> note1Box = new ComboBox<>();
        ComboBox<String> note2Box = new ComboBox<>();
        ComboBox<String> note3Box = new ComboBox<>();
        ComboBox<String> note4Box = new ComboBox<>();


        List<ComboBox<String>> comboBoxList = List.of(note1Box,note2Box,note3Box,note4Box);


        //note1Box.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateComboBox(notes, comboBoxList));
        //note2Box.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateComboBox(notes,comboBoxList));
        //note3Box.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateComboBox(notes,comboBoxList));
        //note4Box.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateComboBox(notes,comboBoxList));

        ComboBox<String> freqNoteBox = new ComboBox<>();

        freqNoteBox.getItems().add("None");

        for (Note n : notes) {

            if(settings.Type.equals("#")) {

                note1Box.getItems().add(n.getName());
                note2Box.getItems().add(n.getName());
                note3Box.getItems().add(n.getName());
                note4Box.getItems().add(n.getName());
                freqNoteBox.getItems().add(n.getName());
            }
            else{
                Note nFloat = Factory.getFloatNote(n, notes);
                note1Box.getItems().add(nFloat.getName());
                note2Box.getItems().add(nFloat.getName());
                note3Box.getItems().add(nFloat.getName());
                note4Box.getItems().add(nFloat.getName());
                freqNoteBox.getItems().add(nFloat.getName());
            }


        }




        Button remove = new Button("Remove");
        remove.setMinWidth(80);
        remove.setOnAction(e -> removeQuestion(index));


        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("Easy", "Medium", "Hard");
        difficultyBox.setValue("Easy");
        difficultyBox.setMinWidth(80);


        ComboBox<String> instrumentBox = new ComboBox<>();
        instrumentBox.getItems().addAll("Piano", "Guitar", "Violin", "Flute");
        freqNoteBox.setPromptText("Freq note");
        instrumentBox.setValue("Piano");
        instrumentBox.setMinWidth(80);


        HBox answers = new HBox(10, note1Box, note2Box, note3Box, note4Box);
        answers.setAlignment(Pos.CENTER);

        HBox options = new HBox(10, difficultyBox, instrumentBox,freqNoteBox, remove);
        options.setAlignment(Pos.CENTER);

        questionBox.getChildren().addAll(questionLabel, answers, options);
        questionContainer.getChildren().add(questionBox);
    }




    private void onSave() {

        Quiz quizToSave = getQuiz();

        if (quizToSave == null){
            return;
        }

        QuizDAO.saveQuiz(quizToSave);
        saveButton.getScene().getWindow().hide();
        MainSceneController.reloadQuiz();

    }


    private File getSelectedFilePath(Node sourceNode, String quizName) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select place to save ");
        fileChooser.setInitialFileName(quizName + ".quiz");

        Stage stage = (Stage) sourceNode.getScene().getWindow();

        return fileChooser.showSaveDialog(stage);
    }


    private void saveQuizAsFile(){
        Quiz quizToSave = getQuiz();
        assert quizToSave != null;
        File fileToSave = getSelectedFilePath(saveButton,quizToSave.getName());

        if (fileToSave == null) {
          return;
        }

        FileDAO.saveQuizToFile(quizToSave, fileToSave);
        QuizDAO.saveQuiz(quizToSave);
        saveButton.getScene().getWindow().hide();
        MainSceneController.reloadQuiz();

    }



    private Quiz getQuiz(){


        List<Question> questions = new ArrayList<>();
        String name = quizTitle.getText();
        String desc = quizDescription.getText();

        if (name.isEmpty()){
            return null;
        }

        if(questionContainer.getChildren().isEmpty()){
            return null;
        }

        for (Node node : questionContainer.getChildren()) {



            VBox questionBox = (VBox) node;

            HBox answers = (HBox) questionBox.getChildren().get(1);

            ComboBox<String> note1 = (ComboBox<String>) answers.getChildren().get(0);
            ComboBox<String> note2 = (ComboBox<String>) answers.getChildren().get(1);
            ComboBox<String> note3 = (ComboBox<String>) answers.getChildren().get(2);
            ComboBox<String> note4 = (ComboBox<String>) answers.getChildren().get(3);

            HBox options = (HBox) questionBox.getChildren().get(2);
            ComboBox<String> difficulty = (ComboBox<String>) options.getChildren().get(0);
            ComboBox<String> instrument = (ComboBox<String>) options.getChildren().get(1);
            ComboBox<String> freqNoteBox = (ComboBox<String>) options.getChildren().get(2);



            List<Note> notes = new ArrayList<>();


            if (note1.getValue() == null || note2.getValue() == null || note3.getValue() == null || note4.getValue() == null ){
                return null;
            }

            Set<String> uniqueNotes = new HashSet<>();
            uniqueNotes.add(note1.getValue());
            uniqueNotes.add(note2.getValue());
            uniqueNotes.add(note3.getValue());
            uniqueNotes.add(note4.getValue());


            if (uniqueNotes.size() < 4) {

                return null;
            }


            notes.add(NoteDAO.getNoteByName(note1.getValue()));
            notes.add(NoteDAO.getNoteByName(note2.getValue()));
            notes.add(NoteDAO.getNoteByName(note3.getValue()));
            notes.add(NoteDAO.getNoteByName(note4.getValue()));

            Note freqNote = NoteDAO.getNoteByName(freqNoteBox.getValue());




            Question question = new Question(notes, QuestionDifficulty.valueOf(difficulty.getValue().toUpperCase()), InstrumentType.valueOf(instrument.getValue().toUpperCase()), freqNote);

            questions.add(question);

        }

        return new Quiz(1,questions,name,desc);
    }


}
