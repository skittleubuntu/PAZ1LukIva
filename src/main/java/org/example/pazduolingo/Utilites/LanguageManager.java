package org.example.pazduolingo.Utilites;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.Settings.Settings;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {

    private static LanguageManager instance;

    private static ObjectProperty<Locale> locale;


    public static void initialize() {
        if (instance == null) {
            instance = new LanguageManager();

            Settings settings = SettingsDAO.loadSettings();

            switch (settings.Language) {
                case "English" -> locale = new SimpleObjectProperty<Locale>(new Locale("en"));
                case "Slovak" -> locale = new SimpleObjectProperty<Locale>(new Locale("sk"));
            }

        }
    }

    public static LanguageManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("LanguageManager has not been initialized");
        }
        return instance;
    }

    public Locale getLocale() {
        return locale.get();
    }

    public void setLocale() {
        Settings settings = SettingsDAO.loadSettings();

        switch(settings.Language) {
            case "English" -> getInstance().localeProperty().set(new Locale("en"));
            case "Slovak" -> getInstance().localeProperty().set(new Locale("sk"));
        }

    }

    public ObjectProperty<Locale> localeProperty() {
        return locale;
    }

    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("language", getLocale());
    }

    //TODO: dokoncit
    public String getTranslation(String key) {
        return getResourceBundle().getString(key);
    }
}
