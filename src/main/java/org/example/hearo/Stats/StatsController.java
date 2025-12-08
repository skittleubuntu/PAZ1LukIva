package org.example.hearo.Stats;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.example.hearo.DateAO.StatsDAO;

public class StatsController {

    @FXML
    private ListView<String> overallStatsList;

    private ObservableList<String> overallStats = FXCollections.observableArrayList();

    @FXML
    private ListView<String> quizStatsList;

    private ObservableList<String> quizStats = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        overallStatsList.setItems(overallStats);
        setOverallStats(StatsDAO.getOverallAccuracy(), StatsDAO.getOverallRounds(), StatsDAO.getOverallCorrectAnswers(), StatsDAO.getOverallWrongAnswers());

    }

    public void setOverallStats(int accuracy, int roundsPlayed, int correctAnswers, int wrongAnswers) {
        overallStats.setAll(
                "Accuracy: " + accuracy + "%",
                "Rounds played: " + roundsPlayed,
                "Correct answers: " + correctAnswers,
                "Wrong answers: " + wrongAnswers
        );
    }

    public void setQuizStats() {
        //TODO: podla quizoch ktore su ulozene v databaze sa tu budu pridavat statistiky
    }

}
