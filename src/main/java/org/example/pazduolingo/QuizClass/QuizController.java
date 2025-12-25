package org.example.pazduolingo.QuizClass;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.DateAO.StatsDAO;
import org.example.pazduolingo.Settings.Settings;
import org.example.pazduolingo.Utilites.Factory;
import org.example.pazduolingo.Utilites.LanguageManager;
import org.example.pazduolingo.Utilites.Sounder;
import org.example.pazduolingo.Utilites.WindowManager;

import java.util.ArrayList;
import java.util.List;

public class QuizController {
    private StatsDAO StatsDAO = Factory.getStatsDao();
    private NoteDAO NoteDAO = Factory.getNoteDao();
    private SettingsDAO settingsDAO = Factory.getSettingsDao();
    private QuizDAO QuizDAO = Factory.getQuizDao();
    @FXML
    private Label questionLabel;

    @FXML
    private Label refNoteLabel;

    @FXML
    private HBox infoBox;

    @FXML
    private Button noteQuestionButton;

    @FXML
    private HBox answersContainer;

    @FXML
    private ProgressBar accBar;

    @FXML
    private Label lifeInfo;

    @FXML
    private Label questionNumber;

    @FXML
    private Button nextButton;

    @FXML
    private Label instrumentLabel;

    private int lives;

    private double progress = 0;

    private Quiz quiz;
    private Question currentQuestion;
    private int currentIndex = 0;

    private int quizId;

    private boolean questionComplete = false;
    private boolean answeredWrong = false;

    public void setQuizId(int id) {
        this.quizId = id;
    }

    @FXML
    void initialize() {
        LanguageManager lm = LanguageManager.getInstance();

        loadQuiz();
        nextButton.setOnAction(event -> goToNextQuestion());

        noteQuestionButton.setOnAction(event -> {

            if (lives > 0) {
                new Thread(() -> {
                    if (currentQuestion.getRefNote() != null) {
                        playNote(currentQuestion.getRefNote());
                        refNoteLabel.setStyle("labelBackColor: #00c100;");

                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    refNoteLabel.setStyle("labelBackColor: transparent;");
                    playNote(currentQuestion.getCorrectAnswer());
                }).start();
                lives--;

                if (lives > 10000){
                    lifeInfo.setText(lm.getTranslation("quiz.playLimit") + " ∞");
                }
                else {
                    lifeInfo.setText(lm.getTranslation("quiz.playLimit") + " " + lives);
                }
            } });
        }


    private void loadQuiz() {

        quiz = QuizDAO.loadQuizByID(quizId);
        if (quiz != null && !quiz.getQuestions().isEmpty()) {
            currentIndex = 0;
            currentQuestion = quiz.getQuestions().get(currentIndex);

            showQuiz();
        } else {
            questionLabel.setText("No questions available.");
        }
    }

    private void showQuiz() {
        LanguageManager lm = LanguageManager.getInstance();

        if (progress < 100) {

            instrumentLabel.setText(""  + currentQuestion.getInstrumentType().toString());
            questionComplete = false;

            questionLabel.setStyle("labelBackColor: transparent;");;

            questionLabel.setText(lm.getTranslation("quiz.whatNote"));
            nextButton.setVisible(false);
            if (currentQuestion == null) return;

            new Thread(() -> {
                if (currentQuestion.getRefNote() != null) {
                    playNote(currentQuestion.getRefNote());
                    refNoteLabel.setStyle("labelBackColor: #00c100;");

                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                refNoteLabel.setStyle("labelBackColor: transparent;");
                playNote(currentQuestion.getCorrectAnswer());
            }).start();

            if (currentQuestion.getRefNote() != null) {
                if(settingsDAO.loadSettings().Type.equals("#")) {
                    refNoteLabel.setText("" + currentQuestion.getRefNote().getName());
                }
                else{
                    refNoteLabel.setText("" + Factory.getFloatNote(currentQuestion.getRefNote(), NoteDAO.getAllNotes()).getName());
                }
                } else {
                refNoteLabel.setText("");
}

            switch (currentQuestion.getDifficult()) {
                case QuestionDifficulty.EASY -> lives = Integer.MAX_VALUE;
                case QuestionDifficulty.MEDIUM -> lives = 3;
                case QuestionDifficulty.HARD -> lives = 0;
            }

            if (lives == Integer.MAX_VALUE || lives > 100000) {
                lifeInfo.setText(lm.getTranslation("quiz.playLimit") + " ∞");

            } else {
                lifeInfo.setText(lm.getTranslation("quiz.playLimit") + " " + lives);
            }

            questionNumber.setText(lm.getTranslation("quiz.question") + (currentIndex + 1));
            answersContainer.getChildren().clear();

            List<Note> noteList = new ArrayList<>(currentQuestion.getNotes());
            for (Note note : noteList) {
                Button answerButton = null;
                if (settingsDAO.loadSettings().Type.equals("#"))
                {
               answerButton = new Button(note.getName());}
                else{
                   answerButton = new Button(Factory.getFloatNote(note, NoteDAO.getAllNotes()).getName());
                }
                answerButton.setPrefWidth(150);
                answerButton.setPrefHeight(70);
                answerButton.setStyle("-fx-font-size: 18px;");
                HBox.setMargin(answerButton, new Insets(5));

                answerButton.setOnAction(event -> handleAnswer(note));
                answersContainer.getChildren().add(answerButton);
            }
        }
    }

    private void handleAnswer(Note note) {
        LanguageManager lm = LanguageManager.getInstance();

        if (!questionComplete){

            if (currentQuestion.checkAnswer(note)) {

                if (!answeredWrong){
                    StatsDAO.addCorrectAnswers(currentQuestion.getCorrectAnswer().getId(), quizId);
                    StatsDAO.addRounds(currentQuestion.getCorrectAnswer().getId(), quizId);
                    progress += (double) 50 / quiz.getQuestions().size();
                }
                answeredWrong = false;

                questionComplete = true;
                questionLabel.setText(lm.getTranslation("quiz.correct"));
                accBar.setProgress(progress / 100);
                nextButton.setVisible(true);
                questionLabel.setStyle("labelBackColor: #00c100");
                if (progress >= 100.0){

                    goToNextQuestion();
                }

            } else {

                if(!answeredWrong) {
                    StatsDAO.addRounds(currentQuestion.getCorrectAnswer().getId(), quizId);
                }
                answeredWrong = true;


                questionLabel.setStyle("labelBackColor: red");
                progress -= (double) 100 / quiz.getQuestions().size();
                if (progress < 0) {
                    progress = 0;
                }
                accBar.setProgress(progress / 100);
                playNote(note);
                questionLabel.setText(lm.getTranslation("quiz.wrong"));
            }
        }
        else if (progress >= 100){
            goToNextQuestion();
        }
    }

    private void goToNextQuestion() {
        LanguageManager lm = LanguageManager.getInstance();

        currentIndex++;
        currentIndex = currentIndex % quiz.getQuestions().size();
        currentQuestion = quiz.getQuestions().get(currentIndex);
        currentQuestion.setCorrectAnswer();

        showQuiz();

        if (progress >= 100.0) {
            questionLabel.setText(lm.getTranslation("quiz.completed"));
            answersContainer.getChildren().clear();
            noteQuestionButton.setVisible(false);

            nextButton.setText(lm.getTranslation("quiz.finish"));
            nextButton.setOnAction(event -> WindowManager.closeWindow(nextButton));

            questionNumber.setVisible(false);
            instrumentLabel.setVisible(false);
            refNoteLabel.setVisible(false);
            lifeInfo.setVisible(false);

        }
    }

    private void playNote(Note note) {
        if (note == null) return;
        InstrumentType type = currentQuestion.getInstrumentType();
        Sounder sounder = Factory.createSounder(type);

        Settings settings = settingsDAO.loadSettings();
        new Thread(() -> sounder.play(note.getMidiNumber(), settings.Volume)).start();
    }

}
