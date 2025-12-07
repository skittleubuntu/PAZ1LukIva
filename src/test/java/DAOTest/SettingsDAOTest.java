package DAOTest;

import org.example.hearo.DateAO.SettingsDAO;
import org.example.hearo.DateAO.SqlDAO;
import org.example.hearo.Settings.Settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingsDAOTest {

    @BeforeEach
    void clean() {
        SqlDAO.dropTables();
    }

    @Test
    void saveAndLoadSettingsTest() {



        Settings settings = new Settings();
        settings.Theme = "Light";
        settings.Volume = 100;
        settings.Type = "#";
        settings.Language = "English";




        SettingsDAO.saveSettings(settings);


        Settings loaded = SettingsDAO.loadSettings();



        assertNotNull(loaded, "Got setting dont must be null");


            assertEquals(settings.Theme, loaded.Theme);
            assertEquals(settings.Volume, loaded.Volume);
            assertEquals(settings.Type, loaded.Type);
            assertEquals(settings.Language, loaded.Language);




    }
}
