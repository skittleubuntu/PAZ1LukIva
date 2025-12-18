package org.example.pazduolingo.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.Utilites.LanguageManager;
import org.example.pazduolingo.Utilites.WindowManager;

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
    private Button saveAndExitButton;

    @FXML
    private RadioButton sharpsRadioButton;

    @FXML
    private Slider volumeSlider;

    private ToggleGroup themeGroup;
    private ToggleGroup notationGroup;

    private Settings settings = new Settings();

    @FXML
    void initialize() {

        languageComboBox.getItems().addAll("English", "Slovak","Ukrainian");

        saveButton.setOnAction(event -> {
            saveSettings();
        });

        saveAndExitButton.setOnAction(event -> {
            saveSettings();
            saveAndExitButton.getScene().getWindow().hide();
        });



        //nastavenie radio buttnoch do svojich skupin aby sa zaskrtol vzdy iba jeden zo skupiny
        themeGroup = new ToggleGroup();
        darkRadioButton.setToggleGroup(themeGroup);
        lightRadioButton.setToggleGroup(themeGroup);

        notationGroup = new ToggleGroup();
        sharpsRadioButton.setToggleGroup(notationGroup);
        flatsRadioButton.setToggleGroup(notationGroup);

        darkRadioButton.setUserData("Dark");
        lightRadioButton.setUserData("Light");

        loadSettings();
    }

    void saveSettings() {

        //ziskanie vybranej hodnoty v radio buttnoch
        RadioButton selectedTheme = (RadioButton) themeGroup.getSelectedToggle();
        RadioButton selectedNotation = (RadioButton) notationGroup.getSelectedToggle();

        //vrati text zvoleneho radio buttonu


        //ziskanie hodnoty language z comboboxu
        String language = languageComboBox.getValue();


        //ziskanie hodnoty pri volume slidery
        int volume = (int) volumeSlider.getValue();



        settings.Theme = (String) selectedTheme.getUserData();
        settings.Type = selectedNotation.getText();
        settings.Language = language;
        settings.Volume = volume;

        SettingsDAO.saveSettings(settings);

        WindowManager.getInstance().setTheme();

        LanguageManager.getInstance().setLocale();

        WindowManager.getInstance().reloadAllStages();
    }

    void loadSettings() {
        Settings settings = SettingsDAO.loadSettings();


        switch(settings.Theme){
            case "Dark" -> themeGroup.selectToggle(darkRadioButton);
            case "Light" -> themeGroup.selectToggle(lightRadioButton);
        }

        switch(settings.Type){
            case "#"  -> notationGroup.selectToggle(sharpsRadioButton);
            case "♭"  -> notationGroup.selectToggle(flatsRadioButton);
        }

        languageComboBox.setValue(settings.Language);
        volumeSlider.setValue(settings.Volume);
    }


}
