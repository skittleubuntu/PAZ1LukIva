package org.example.pazduolingo.Stats;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.Utilites.LanguageManager;
import org.example.pazduolingo.Utilites.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatsController {

//    @FXML
//    private ListView<String> overallStatsList;
//
//    private ObservableList<String> overallStats = FXCollections.observableArrayList();
    @FXML
    private PieChart accuracyPieChart;

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

        overallStatsValue.getStyleClass().add("centerColumn");
        quizStatsValue.getStyleClass().add("centerColumn");

        quizStatsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                if(quizStatsTable.getSelectionModel().getSelectedIndex() == -1) {
                    return;
                }
                Statistic selected = quizStatsTable.getSelectionModel().getSelectedItem();
                handleQuizSelection(selected);
            }
        });

        int rounds = StatsDAO.getOverallRounds();
        int correct = StatsDAO.getOverallCorrectAnswers();
        int wrong = StatsDAO.getOverallWrongAnswers();
        int accuracy = StatsDAO.getOverallAccuracy();

        LanguageManager lm = LanguageManager.getInstance();

        if (rounds > 0) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(lm.getTranslation("stats.correct"), correct),
                    new PieChart.Data(lm.getTranslation("stats.wrong"), wrong)
            );

            accuracyPieChart.setData(pieChartData);

        }

        loadOverallStats(accuracy, rounds, correct, wrong);
        loadQuizStats();

    }


    private void handleQuizSelection(Statistic selectedStat) {

        String quizName = selectedStat.getName();
        Quiz quiz = selectedStat.getQuiz();

        try {
            String fxmlPath = "/org/example/pazduolingo/Stats/QuizStatsView.fxml";
            QuizStatsController quizStatsController = new QuizStatsController(quiz);
            WindowManager.getInstance().openWindow(fxmlPath, quizStatsController, quizName + " stats", null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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

    }

    public void loadQuizStats() {
        List<Quiz> quizzes = QuizDAO.loadQuiz();
        List<Statistic> quizStats = new ArrayList<>();

        for (Quiz quiz : quizzes) {
            String name = quiz.getName();
            String accuracy = StatsDAO.getQuizAccuracy(quiz.getID()) + "%";
            String quizID = Integer.toString(quiz.getID());

            quizStats.add(new Statistic(name, accuracy, quiz));
        }

        ObservableList<Statistic> statistics = FXCollections.observableArrayList(quizStats);

        quizStatsTable.setItems(statistics);

    }

}
