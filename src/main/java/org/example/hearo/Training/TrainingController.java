package org.example.hearo.Training;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import org.example.hearo.DateAO.NoteDAO;
import org.example.hearo.DateAO.SettingsDAO;
import org.example.hearo.QuizClass.InstrumentType;
import org.example.hearo.Settings.Settings;
import org.example.hearo.Utilites.Factory;
import org.example.hearo.Utilites.Functions;
import org.example.hearo.QuizClass.Note;
import org.example.hearo.Utilites.Sounder;

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


        choiceOrder.setValue("By Octave");


        choiceFilter.setValue("None");

        choiceInstrument.getItems().addAll("Piano", "Guitar", "Violin", "Flute");
        choiceInstrument.setValue("Piano");

        choiceOrder.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateNotes());
        choiceFilter.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateNotes());

        notes = NoteDAO.getAllNotes();
        updateNotes();
    }

    private void updateNotes() {

        List<Note> result = NoteDAO.getAllNotes();


        String sortType = choiceOrder.getValue();
        if (sortType.equals("By Octave")) {
            Collections.sort(result);
        } else if (sortType.equals("By Name")) {
            result = Functions.orderByName(result);
        }


        String filter = choiceFilter.getValue();
        if (filter.equals("Standard")) {
            result = Functions.filterStandard(result);
        } else if (filter.equals("Dies")) {
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
        String selectedInstrument = choiceInstrument != null ? choiceInstrument.getValue() : "Piano";
        InstrumentType type = InstrumentType.valueOf(selectedInstrument.toUpperCase());
        Sounder sounder = Factory.createSounder(type);
        System.out.println("Played note: " + note.getName());
        new Thread(() -> sounder.play(note.getMidiNumber(), SettingsDAO.loadSettings().Volume)).start();
    }
}
