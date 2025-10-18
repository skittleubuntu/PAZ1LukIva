package org.example.pazduolingo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;

import java.util.Set;

public class TrainingController {

    @FXML
    private GridPane buttonGrid;

    @FXML
    private ScrollPane scrollPane;

    private Set<Note> notes;

    private final int MAX_COLUMNS = 10;

    public void setNotes(Set<Note> notes) {
        this.notes = notes;
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

            // Прив'язуємо розмір кнопки до ширини ScrollPane
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
        Sounder player = new Sounder();
        player.play(note.getMidiNumber(), 100);
    }
}
