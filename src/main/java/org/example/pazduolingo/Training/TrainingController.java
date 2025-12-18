package org.example.pazduolingo.Training;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.QuizClass.InstrumentType;
import org.example.pazduolingo.Settings.Settings;
import org.example.pazduolingo.Utilites.Factory;
import org.example.pazduolingo.Utilites.Functions;
import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.Utilites.LanguageManager;
import org.example.pazduolingo.Utilites.Sounder;

import java.util.*;

public class TrainingController {

    @FXML
    private GridPane buttonGrid;

    @FXML
    private ScrollPane scrollPane;

    private List<Note> notes = new ArrayList<>();

    private final int MAX_COLUMNS = 8;

    @FXML
    private ComboBox<String> choiceFilter;

    @FXML
    private ComboBox<String> choiceOrder;

    @FXML
    private ComboBox<String> choiceInstrument;

    @FXML
    public void initialize() {
        LanguageManager lm = LanguageManager.getInstance();

        choiceOrder.getItems().setAll(
                lm.getTranslation("training.frequency"),
                lm.getTranslation("training.name"));
        choiceOrder.setValue(lm.getTranslation("training.frequency"));

        choiceFilter.getItems().setAll(
                lm.getTranslation("training.none"),
                lm.getTranslation("training.natural"),
                lm.getTranslation("training.accidental"));
        choiceFilter.setValue(lm.getTranslation("training.none"));

        choiceInstrument.getItems().addAll(
                lm.getTranslation("training.piano"),
                lm.getTranslation("training.guitar"),
                lm.getTranslation("training.violin"),
                lm.getTranslation("training.flute"));
        choiceInstrument.setValue(lm.getTranslation("training.piano"));

        choiceOrder.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateNotes());
        choiceFilter.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateNotes());

        notes = NoteDAO.getAllNotes();
        updateNotes();
    }

    private void updateNotes() {
        LanguageManager lm = LanguageManager.getInstance();

        List<Note> result = NoteDAO.getAllNotes();


        String sortType = choiceOrder.getValue();
        if (sortType.equals(lm.getTranslation("training.frequency"))) {
            Collections.sort(result);
        } else if (sortType.equals(lm.getTranslation("training.name"))) {
            result = Functions.orderByName(result);
        }


        String filter = choiceFilter.getValue();
        if (filter.equals(lm.getTranslation("training.natural"))) {
            result = Functions.filterStandard(result);
        } else if (filter.equals(lm.getTranslation("training.accidental"))) {
            result = Functions.filterDies(result);
        }

        notes = result;
        createButtons();
    }

    private void createButtons() {
        buttonGrid.getChildren().clear();
        Settings settings = SettingsDAO.loadSettings();
        List<Note> allNotes = NoteDAO.getAllNotes();
        List<Note> finalNotes = new ArrayList<>();


        for (Note n : notes) {
            System.out.println(n.getName());
            if (settings.Type.equals("#")) {
                finalNotes.add(n);
            } else {
                finalNotes.add(Factory.getFloatNote(n, allNotes));
            }
        }


        int col = 0;
        int row = 0;

        for (Note note : finalNotes) {
            Button button = new Button(note.getName());
            button.getStyleClass().add("trainingButton");
            button.prefWidthProperty().bind(
                    scrollPane.widthProperty().subtract((MAX_COLUMNS + 1) * 10).divide(MAX_COLUMNS)
            );
            button.prefHeightProperty().bind(button.prefWidthProperty());
            button.setOnAction(event -> handleNoteClick(note));

            buttonGrid.add(button, col, row);

            col++;
            if (col >= MAX_COLUMNS) {
                col = 0;
                row++;
            }
        }
    }

    private void handleNoteClick(Note note) {
        LanguageManager lm = LanguageManager.getInstance();

        int index = choiceInstrument.getSelectionModel().getSelectedIndex();

        InstrumentType type = switch (index) {
            case 1 -> InstrumentType.GUITAR;
            case 2 -> InstrumentType.VIOLIN;
            case 3 -> InstrumentType.FLUTE;
            default -> InstrumentType.PIANO;
        };

        Sounder sounder = Factory.createSounder(type);
        System.out.println("Played note: " + note.getName());
        new Thread(() -> sounder.play(note.getMidiNumber(), SettingsDAO.loadSettings().Volume)).start();
    }
}
