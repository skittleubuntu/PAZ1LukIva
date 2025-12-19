package org.example.pazduolingo.Stats;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.QuizClass.Question;
import org.example.pazduolingo.QuizClass.Quiz;
import org.example.pazduolingo.Settings.Settings;
import org.example.pazduolingo.Utilites.Factory;
import org.example.pazduolingo.Utilites.Functions;
import org.example.pazduolingo.Utilites.LanguageManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizStatsController {

    @FXML
    private PieChart accuracyPieChart;

    @FXML
    private TableView<NoteStatistic> noteStatsTable;

    @FXML
    private TableColumn<NoteStatistic, String> noteName;

    @FXML
    private TableColumn<NoteStatistic, Integer> rounds;

    @FXML
    private TableColumn<NoteStatistic, Integer> correctAnswers;

    @FXML
    private TableColumn<NoteStatistic, Integer> wrongAnswers;

    @FXML
    private TableColumn<NoteStatistic, String> accuracy;

    @FXML
    private TableView<Statistic> quizStatsTable;

    @FXML
    private TableColumn<Statistic, String> statsName;

    @FXML
    private TableColumn<Statistic, String> statsValue;

    Quiz quiz;

    public QuizStatsController(Quiz quiz) {
        this.quiz = quiz;
    }

    @FXML
    void initialize(){
        LanguageManager lm = LanguageManager.getInstance();

        int accuracy = StatsDAO.getQuizAccuracy(quiz.getID());
        int rounds = StatsDAO.getQuizRounds(quiz.getID());
        int correct = StatsDAO.getQuizCorrectAnswers(quiz.getID());
        int wrong = rounds - correct;

        noteName.setCellValueFactory(new PropertyValueFactory<>("note"));
        this.rounds.setCellValueFactory(new PropertyValueFactory<>("rounds"));
        correctAnswers.setCellValueFactory(new PropertyValueFactory<>("correct"));
        wrongAnswers.setCellValueFactory(new PropertyValueFactory<>("wrong"));
        this.accuracy.setCellValueFactory(new PropertyValueFactory<>("accuracy"));

        statsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        statsValue.setCellValueFactory(new PropertyValueFactory<>("value"));

        noteName.getStyleClass().add("centerColumn");
        this.rounds.getStyleClass().add("rightColumn");
        correctAnswers.getStyleClass().add("rightColumn");
        wrongAnswers.getStyleClass().add("rightColumn");
        this.accuracy.getStyleClass().add("rightColumn");

        statsValue.getStyleClass().add("centerColumn");

        if (rounds > 0) {
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(lm.getTranslation("stats.correct"), correct),
                    new PieChart.Data(lm.getTranslation("stats.wrong"), wrong)
            );

            accuracyPieChart.setData(pieChartData);

        }

        loadQuizStats(accuracy, rounds, correct, wrong);
        loadNoteStats();
    }

    private void loadQuizStats(int accuracy, int rounds, int correct, int wrong) {
        LanguageManager lm = LanguageManager.getInstance();

        List<Statistic> overallStats = Arrays.asList(
                new Statistic(lm.getTranslation("stats.accuracy"), accuracy + "%"),
                new Statistic(lm.getTranslation("stats.roundsPlayed"), Integer.toString(rounds)),
                new Statistic(lm.getTranslation("stats.correctAnswers"), Integer.toString(correct)),
                new Statistic(lm.getTranslation("stats.wrongAnswers"), Integer.toString(wrong))
        );

        ObservableList<Statistic> statistics = FXCollections.observableList(overallStats);
        quizStatsTable.setItems(statistics);

    }


    private void loadNoteStats() {
        Settings sett = SettingsDAO.loadSettings();
        List<Note> allnote = NoteDAO.getAllNotes();
        List<Note> notes = Functions.allNotesFromQuizList(quiz);
        List<NoteStatistic> noteStats = new ArrayList<>();

        for (Note n : notes) {
            int rounds = StatsDAO.getRounds(n.getId(), quiz.getID());
            int correct = StatsDAO.getCorrectAnswers(n.getId(), quiz.getID());
            int wrong = StatsDAO.getWrongAnswers(n.getId(), quiz.getID());
            int accuracy = StatsDAO.getAccuracy(n.getId(), quiz.getID());


            if (sett.Type.equals("#")) {
                noteStats.add(new NoteStatistic(n.getName(), wrong, correct, rounds, accuracy + "%"));
            }
            else{
                noteStats.add(new NoteStatistic(Factory.getFloatNote(n,allnote).getName(), wrong, correct, rounds, accuracy + "%"));
            }

        }

        noteStatsTable.setItems(FXCollections.observableList(noteStats));
    }
}
