package DAOTest;

import org.example.pazduolingo.DateAO.DatabaseProfile;
import org.example.pazduolingo.DateAO.SettingsDAO;
import org.example.pazduolingo.DateAO.SqlDAO;
import org.example.pazduolingo.Settings.Settings;

import org.example.pazduolingo.Utilites.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsDAOTest {

    private SettingsDAO settingsDAO;
    private SqlDAO sqlDAO;
    @BeforeEach
    void setUp() {

        Factory.setDatabaseMode(DatabaseProfile.TEST);
        settingsDAO = Factory.getSettingsDao();
        sqlDAO = Factory.getSQLDao();
        sqlDAO.dropTables();
    }

    @Test
    void saveAndLoadSettingsTest() {

        Settings settings = new Settings();
        settings.Theme = "Light";
        settings.Volume = 100;
        settings.Type = "#";
        settings.Language = "English";

        settingsDAO.saveSettings(settings);

        Settings loaded = settingsDAO.loadSettings();

        assertNotNull(loaded, "Settings should not be null");
        assertEquals(settings.Theme, loaded.Theme);
        assertEquals(settings.Volume, loaded.Volume);
        assertEquals(settings.Type, loaded.Type);
        assertEquals(settings.Language, loaded.Language);
    }
}
