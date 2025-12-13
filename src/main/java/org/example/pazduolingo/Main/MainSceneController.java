package org.example.pazduolingo.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.pazduolingo.DateAO.FileDAO;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.QuizClass.QuizController;
import org.example.pazduolingo.QuizEditor.QuizEditorController;
import org.example.pazduolingo.Settings.SettingsController;
import org.example.pazduolingo.Stats.StatsController;
import org.example.pazduolingo.Training.TrainingController;
import org.example.pazduolingo.Utilites.WindowManager;

import java.io.File;
import java.util.List;

import javafx.scene.Node;

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
    private Button addButton;


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
            try {
                String fxmlPath = "/org/example/pazduolingo/Quiz/QuizView.fxml";
                QuizController quizController = new QuizController();
                quizController.setQuizId(selected.getID());
                WindowManager.getInstance().openWindow(fxmlPath, quizController, "Quiz", Modality.APPLICATION_MODAL);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        });


        trainingButton.setOnAction(event -> {
            try {
                String fxmlPath = "/org/example/pazduolingo/Training/TrainingView.fxml";
                TrainingController trainingController = new TrainingController();
                WindowManager.getInstance().openWindow(fxmlPath, trainingController, "Training", Modality.APPLICATION_MODAL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        editorButton.setOnAction(event -> {
            try {
                String fxmlPath = "/org/example/pazduolingo/QuizEditor/QuizEditorView.fxml";
                QuizEditorController  quizEditorController = new QuizEditorController();
                WindowManager.getInstance().openWindow(fxmlPath, quizEditorController, "Quiz editor", Modality.APPLICATION_MODAL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        statsButton.setOnAction(event -> {
            try {
                String fxmlPath = "/org/example/pazduolingo/Stats/StatsView.fxml";
                StatsController statsController = new StatsController();
                WindowManager.getInstance().openWindow(fxmlPath, statsController, "Stats", Modality.APPLICATION_MODAL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        settingsButton.setOnAction(event -> {
            try {
                String fxmlPath = "/org/example/pazduolingo/Settings/SettingsView.fxml";
                SettingsController settingsController = new SettingsController();
                WindowManager.getInstance().openWindow(fxmlPath, settingsController, "Settings", Modality.APPLICATION_MODAL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        addButton.setOnAction(e -> {

            File file = getSelectedFilePath(addButton);
            Quiz quiz = FileDAO.loadQuizFromFile(file);
            QuizDAO.saveQuiz(quiz);
            reloadQuiz();



        });


    }




    private File getSelectedFilePath(Node sourceNode) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose quiz");


        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Quizzes", "*.quiz"),

                new FileChooser.ExtensionFilter("All", "*.*")
        );

        // Отримання посилання на головне вікно (Stage)
        // Це обов'язково для виклику showOpenDialog
        Stage stage = (Stage) sourceNode.getScene().getWindow();

        // Відображення діалогового вікна та очікування вибору
        File selectedFile = fileChooser.showOpenDialog(stage);

        return selectedFile;
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
        StatsDAO.deleteQuizStats(quiz);
        List<Quiz> quizes =  QuizDAO.loadQuiz();


        quizzes = FXCollections.observableArrayList(quizes);
        quizListView.setItems(quizzes);

    }
}
