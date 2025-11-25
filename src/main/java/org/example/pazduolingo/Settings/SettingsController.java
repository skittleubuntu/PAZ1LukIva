package org.example.pazduolingo.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.pazduolingo.DateAO.SettingsDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class SettingsController {

    @FXML
    private RadioButton darkRadioButton;

    @FXML
    private RadioButton flatsRadioButton;

    @FXML
    private ComboBox<String> languageComboBox;

    @FXML
    private RadioButton lightRadioButton;

    @FXML
    private Button saveButton;

    @FXML
    private RadioButton sharpsRadioButton;

    @FXML
    private Slider volumeSlider;

    private ToggleGroup themeGroup;
    private ToggleGroup notationGroup;

    private Settings settings = new Settings();

    @FXML
    void initialize() {

        languageComboBox.getItems().addAll("English", "Slovak");

        saveButton.setOnAction(event -> {
            saveSettings();
        });

        //nastavenie radio buttnoch do svojich skupin aby sa zaskrtol vzdy iba jeden zo skupiny
        themeGroup = new ToggleGroup();
        darkRadioButton.setToggleGroup(themeGroup);
        lightRadioButton.setToggleGroup(themeGroup);

        notationGroup = new ToggleGroup();
        sharpsRadioButton.setToggleGroup(notationGroup);
        flatsRadioButton.setToggleGroup(notationGroup);

        loadSettings();
    }

    void saveSettings() {

        //ziskanie vybranej hodnoty v radio buttnoch
        RadioButton selectedTheme = (RadioButton) themeGroup.getSelectedToggle();
        RadioButton selectedNotation = (RadioButton) notationGroup.getSelectedToggle();

        //vrati text zvoleneho radio buttonu
        System.out.println("Theme : " + selectedTheme.getText());
        System.out.println("Notation : " + selectedNotation.getText());

        //ziskanie hodnoty language z comboboxu
        String language = languageComboBox.getValue();
        System.out.println("Language : " + language);

        //ziskanie hodnoty pri volume slidery
        int volume = (int) volumeSlider.getValue();
        System.out.println("Volume : " + volume);


        settings.Theme = selectedTheme.getText();
        settings.Type = selectedNotation.getText();
        settings.Language = language;
        settings.Volume = volume;


        SettingsDAO.saveSettings(settings);

    }

    void loadSettings() {
        settings = SettingsDAO.loadSettings();
        //todo

    }


}
