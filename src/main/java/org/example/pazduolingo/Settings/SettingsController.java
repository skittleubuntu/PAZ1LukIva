package org.example.pazduolingo.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;

public class SettingsController {

    @FXML
    private RadioButton darkRadioButton;

    @FXML
    private RadioButton flatsRadioButton;

    @FXML
    private ComboBox<?> languageComboBox;

    @FXML
    private RadioButton lightRadioButton;

    @FXML
    private Button saveButton;

    @FXML
    private RadioButton sharpsRadioButton;

    @FXML
    private Slider volumeSlider;

    void initialize() {

        saveButton.setOnAction(event -> {
            System.out.println("Save button pressed");
        });
    }

}
