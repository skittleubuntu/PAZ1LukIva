package org.example.pazduolingo.Stats;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.Utilites.LanguageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatsController {

//    @FXML
//    private ListView<String> overallStatsList;
//
//    private ObservableList<String> overallStats = FXCollections.observableArrayList();

    @FXML
    private TableView<Statistic> overallStatsTable;

    @FXML
    private TableColumn<Statistic,String> overallStatsName;

    @FXML
    private TableColumn<Statistic,String> overallStatsValue;

    @FXML
    private TableView<Statistic> quizStatsTable;

    @FXML
    private TableColumn<Statistic, String> quizStatsName;

    @FXML
    private TableColumn<Statistic, String> quizStatsValue;

    @FXML
    void initialize() {

        overallStatsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        overallStatsValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        quizStatsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        quizStatsValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        overallStatsValue.getStyleClass().add("valueColumn");
        quizStatsValue.getStyleClass().add("valueColumn");


        loadOverallStats(StatsDAO.getOverallAccuracy(), StatsDAO.getOverallRounds(), StatsDAO.getOverallCorrectAnswers(), StatsDAO.getOverallWrongAnswers());
        loadQuizStats();

    }

    public void loadOverallStats(int accuracy, int rounds, int correctAnswers, int wrongAnswers) {
        LanguageManager lm = LanguageManager.getInstance();

        List<Statistic> overallStats = Arrays.asList(
                new Statistic(lm.getTranslation("stats.accuracy"), accuracy + "%"),
                new Statistic(lm.getTranslation("stats.roundsPlayed"), Integer.toString(rounds)),
                new Statistic(lm.getTranslation("stats.correctAnswers"), Integer.toString(correctAnswers)),
                new Statistic(lm.getTranslation("stats.wrongAnswers"), Integer.toString(wrongAnswers))
        );

        ObservableList<Statistic> statistics = FXCollections.observableList(overallStats);
        overallStatsTable.setItems(statistics);


//        overallStats.setAll(
//                "Accuracy: " + accuracy + "%",
//                "Rounds played: " + roundsPlayed,
//
//                "Correct answers: " + correctAnswers,
//                "Wrong answers: " + wrongAnswers
//        );
    }

    public void loadQuizStats() {
        List<Quiz> quizzes = QuizDAO.loadQuiz();
        List<Statistic> quizStats = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            String name = quiz.getName();
            String accuracy = StatsDAO.getQuizAccuracy(quiz.getID()) + "%";

            quizStats.add(new Statistic(name, accuracy));
        }

        ObservableList<Statistic> statistics = FXCollections.observableArrayList(quizStats);

        quizStatsTable.setItems(statistics);

    }

}
