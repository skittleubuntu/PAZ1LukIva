module org.example.hearo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires org.xerial.sqlitejdbc;


    opens org.example.hearo to javafx.fxml;

    exports org.example.hearo.QuizClass;
    opens org.example.hearo.QuizClass to javafx.fxml;
    exports org.example.hearo.DateAO;
    opens org.example.hearo.DateAO to javafx.fxml;
    exports org.example.hearo.Main;
    opens org.example.hearo.Main to javafx.fxml;
    exports org.example.hearo.Training;
    opens org.example.hearo.Training to javafx.fxml;
    exports org.example.hearo.Utilites;
    opens org.example.hearo.Utilites to javafx.fxml;
    exports org.example.hearo;

    opens org.example.hearo.Quiz to javafx.fxml;
    opens org.example.hearo.Settings to javafx.fxml;

    exports org.example.hearo.QuizEditor;
    opens org.example.hearo.QuizEditor to javafx.fxml;

    opens  org.example.hearo.Stats to javafx.fxml;

}