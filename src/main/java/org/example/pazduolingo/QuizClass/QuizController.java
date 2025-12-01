package org.example.pazduolingo.QuizClass;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import org.example.pazduolingo.DateAO.QuizDAO;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.Utilites.Factory;
import org.example.pazduolingo.Utilites.Sounder;

import java.util.ArrayList;
import java.util.List;

public class QuizController {

    @FXML
    private Label questionLabel;

    @FXML
    private Label freqNoteLabel;

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


    private int lifes;

    private double progres = 0;


    private Quiz quiz;
    private Question currentQuestion;
    private int currentIndex = 0;

    private int quizId;

    private boolean questionComplete = false;

    public void setQuizId(int id) {
        this.quizId = id;
        loadQuiz();
    }

    @FXML
    void initialize() {



        nextButton.setOnAction(event -> goToNextQuestion());

        noteQuestionButton.setOnAction(event -> {

            if (lifes > 0) {
                new Thread(() -> {
                    if (currentQuestion.getFreqNote() != null) {
                        playNote(currentQuestion.getFreqNote());
                        freqNoteLabel.setStyle("-fx-background-color: green; -fx-background-radius: 10;");

                        try {
                            Thread.sleep(700);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (SettingsDAO.loadSettings().Theme.equals("Light")) {
                        freqNoteLabel.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
                    } else {
                        freqNoteLabel.setStyle("-fx-background-color: black; -fx-background-radius: 10;");
                    }
                    playNote(currentQuestion.getCorrectAnswer());
                }).start();
                lifes --;

                if (lifes > 10000){
                    lifeInfo.setText("Lifes: ∞");
                }
                else {
                    lifeInfo.setText("Lifes: " + lifes);
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



        if (progres < 100) {

            instrumentLabel.setText("Instrument: "  + currentQuestion.getInstrumentType().toString());
            questionComplete = false;
            questionLabel.setStyle("-fx-background-color: white; -fx-background-radius: 10");
            questionLabel.setText("what is the note?");
            nextButton.setVisible(false);
            if (currentQuestion == null) return;


            new Thread(() -> {
                if (currentQuestion.getFreqNote() != null) {
                    playNote(currentQuestion.getFreqNote());
                    freqNoteLabel.setStyle("-fx-background-color: green; -fx-background-radius: 10;");

                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (SettingsDAO.loadSettings().Theme.equals("Light")) {
                    freqNoteLabel.setStyle("-fx-background-color: white; -fx-background-radius: 10;");
                } else {
                    freqNoteLabel.setStyle("-fx-background-color: black; -fx-background-radius: 10;");
                }
                playNote(currentQuestion.getCorrectAnswer());
            }).start();

            if (currentQuestion.getFreqNote() != null) {
                freqNoteLabel.setText("FreqNote: " + currentQuestion.getFreqNote().getName());
            } else {
                freqNoteLabel.setText("FreqNote: None");
            }

            switch (currentQuestion.getDifficult()) {
                case QuestionDifficulty.EASY -> lifes = Integer.MAX_VALUE;
                case QuestionDifficulty.MEDIUM -> lifes = 3;
                case QuestionDifficulty.HARD -> lifes = 0;
            }


            if (lifes == Integer.MAX_VALUE || lifes > 100000) {
                lifeInfo.setText("Lifes: ∞");

            } else {
                lifeInfo.setText("Lifes: " + lifes);
            }

            questionNumber.setText("Question " + (currentIndex + 1));
            answersContainer.getChildren().clear();

            List<Note> noteList = new ArrayList<>(currentQuestion.getNotes());
            for (Note note : noteList) {
                Button answerButton = new Button(note.getName());
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

        if (!questionComplete){

            if (currentQuestion.checkAnswer(note)) {

                questionComplete = true;
                questionLabel.setText("Correct! Next question...");
                progres += (double) 50 / quiz.getQuestions().size();
                accBar.setProgress(progres / 100);
                nextButton.setVisible(true);
                questionLabel.setStyle("-fx-background-color: green; -fx-background-radius: 10");
                if (progres >= 100.0){

                    goToNextQuestion();
                }


            } else {
                questionLabel.setStyle("-fx-background-color: red; -fx-background-radius: 10");
                progres -= (double) 100 / quiz.getQuestions().size();
                if (progres < 0) {
                    progres = 0;
                }
                accBar.setProgress(progres / 100);
                playNote(note);
                questionLabel.setText("Wrong! Try again...");
            }
        }
        else if (progres >= 100){
            goToNextQuestion();
        }
    }

    private void goToNextQuestion() {
        currentIndex++;
        currentIndex = currentIndex % quiz.getQuestions().size();
            currentQuestion = quiz.getQuestions().get(currentIndex);
        currentQuestion.setCorrectAnswer();
            showQuiz();

        if (progres >= 100.0) {
            questionLabel.setText("Quiz completed!");
            infoBox.getChildren().clear();
            answersContainer.getChildren().clear();
            noteQuestionButton.setVisible(false
            );

            nextButton.setVisible(false);
            questionNumber.setVisible(false);
            instrumentLabel.setVisible(false);

        }
    }

    private void playNote(Note note) {
        if (note == null) return;
        InstrumentType type = currentQuestion.getInstrumentType();
        Sounder sounder = Factory.createSounder(type);
        new Thread(() -> sounder.play(note.getMidiNumber(), 100)).start();
    }
}
