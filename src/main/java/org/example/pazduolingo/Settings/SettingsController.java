package org.example.pazduolingo.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    private String[] settings;

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


        //TODO: treba pridat ulozenie do tabulky settings z SQLite databazy
        try (PrintWriter pw = new PrintWriter(new File("settings.csv"))){
            pw.println(selectedTheme.getText() + "," + selectedNotation.getText() + "," + language + "," + volume);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    void loadSettings() {

        try(Scanner sc = new Scanner(new File("settings.csv"))){
            settings = sc.next().split(",");

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String selectedTheme = settings[0];
        String selectedNotation = settings[1];

        //podla stringu v settings.csv nastavim ktory radio button ma byt aktivny
        switch(selectedTheme){
            case "Dark" -> themeGroup.selectToggle(darkRadioButton);
            case "Light" -> themeGroup.selectToggle(lightRadioButton);
        }

        switch(selectedNotation){
            case "#"  -> notationGroup.selectToggle(sharpsRadioButton);
            case "♭"  -> notationGroup.selectToggle(flatsRadioButton);
        }

        languageComboBox.setValue(settings[2]);
        volumeSlider.setValue(Integer.parseInt(settings[3]));
    }
}
