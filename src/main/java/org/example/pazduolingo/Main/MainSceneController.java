package org.example.pazduolingo.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.QuizClass.QuizWindow;
import org.example.pazduolingo.Settings.SettingsWindow;
import org.example.pazduolingo.Stats.StatsWindow;
import org.example.pazduolingo.Training.TrainingWindow;
import org.example.pazduolingo.QuizEditor.QuizEditorWindow;

import java.util.List;

public class MainSceneController {

    @FXML
    private Button editorButton;

    @FXML
    private Button settingsButton;

    @FXML
    private Button statsButton;

    @FXML
    private Button trainingButton;

    @FXML
    private ListView<Quiz> quizListView;

    @FXML
    private ComboBox<String> quizFilter;


    @FXML
    private Button deleteButton;


    private static MainSceneController instance;

    @FXML
    private Button startButton;

    private static ObservableList<Quiz> quizzes = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        instance = this;
        loadQuiz();

        quizFilter.getItems().addAll("Default", "Custom");
        quizFilter.setValue("Default");


        deleteButton.setOnAction(event -> {
            Quiz selected = quizListView.getSelectionModel().getSelectedItem();
            if (selected == null){
                return;
            }

            removeQuiz(selected);


        });



        startButton.setOnAction(event ->{

            Quiz selected = quizListView.getSelectionModel().getSelectedItem();
            if (selected == null) {

                return;
            }
           QuizWindow quizWindow = new QuizWindow();
           Stage quizStage = new Stage();
            try {
                quizWindow.start(quizStage, selected.getID());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });


        trainingButton.setOnAction(event -> {
            try {
                TrainingWindow trainingApp = new TrainingWindow();
                Stage trainingStage = new Stage();
                trainingApp.start(trainingStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        editorButton.setOnAction(event -> {
            try {
                QuizEditorWindow quizEditor = new QuizEditorWindow();
                Stage quizStage = new Stage();
                quizEditor.start(quizStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        statsButton.setOnAction(event -> {
            try {
                StatsWindow statsWindow = new StatsWindow();
                Stage statsStage = new Stage();
                statsWindow.start(statsStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        settingsButton.setOnAction(event -> {
            try {
                SettingsWindow settingsWindow = new SettingsWindow();
                Stage settingsStage = new Stage();
                settingsWindow.start(settingsStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



    }

    public static void reloadQuiz() {
        if (instance != null) {
            instance.loadQuiz();
        }
    }

    public void loadQuiz(){
        quizzes = FXCollections.observableArrayList(QuizDAO.loadQuiz());
        quizListView.setItems(quizzes);
    }


    public void removeQuiz(Quiz quiz){
        QuizDAO.deleteQuiz(quiz);
        List<Quiz> quizes =  QuizDAO.loadQuiz();


        quizzes = FXCollections.observableArrayList(quizes);
        quizListView.setItems(quizzes);

    }
}
