package org.example.pazduolingo.Training;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import org.example.pazduolingo.DateAO.NoteDAO;
import org.example.pazduolingo.QuizClass.InstrumentType;
import org.example.pazduolingo.Utilites.Factory;
import org.example.pazduolingo.Utilites.Functions;
import org.example.pazduolingo.QuizClass.Note;
import org.example.pazduolingo.Utilites.Sounder;

import java.util.*;

public class TrainingController {

    @FXML
    private GridPane buttonGrid;



    @FXML
    private ScrollPane scrollPane;

    private List<Note> notes;

    private final int MAX_COLUMNS = 10;


    //TODO
    @FXML
    private ChoiceBox<String> choiceFilter;

    @FXML
    private ChoiceBox<String> choiceOrder;

    @FXML
    private ChoiceBox<String> choiceInstrument;


    @FXML
    public void initialize() {
        choiceOrder.setValue("By Octave");
        choiceOrder.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            onChoiceChanged(newVal);
        });
        choiceInstrument.getItems().addAll("Piano", "Guitar", "Violin", "Flute");
        choiceInstrument.setValue("Piano");
    }

    private void onChoiceChanged(String newVal) {
        if (newVal.equals("By Octave")) {
            notes = NoteDAO.getAllNotes();
            System.out.println("By octave");
        } else if (newVal.equals("By Name")) {
            notes = Functions.orderByName(notes);
        }
        createButtons();
    }

    //take notes from DAO
    public void setNotes(List<Note> notes) {
        this.notes = notes;
        Collections.sort(notes);
        if (buttonGrid != null) {
            createButtons();
        }
    }

    private void createButtons() {
        buttonGrid.getChildren().clear();

        int col = 0;
        int row = 0;

        for (Note note : notes) {
            Button button = new Button(note.getName());


            button.prefWidthProperty().bind(scrollPane.widthProperty().subtract((MAX_COLUMNS+1)*10).divide(MAX_COLUMNS));
            button.prefHeightProperty().bind(button.prefWidthProperty()); // квадрат

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
        System.out.println("Clicked: " + note.getName());

        String selectedInstrument = choiceInstrument != null ? choiceInstrument.getValue() : "Piano";
        InstrumentType type = InstrumentType.valueOf(selectedInstrument.toUpperCase());


        Sounder sounder = Factory.createSounder(type);

        new Thread(() -> sounder.play(note.getMidiNumber(), 100)).start();
    }
}
