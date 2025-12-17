module org.example.pazduolingo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
    requires org.xerial.sqlitejdbc;

    exports org.example.pazduolingo.QuizClass;
    opens org.example.pazduolingo.QuizClass to javafx.fxml;
    exports org.example.pazduolingo.DateAO;
    opens org.example.pazduolingo.DateAO to javafx.fxml;
    exports org.example.pazduolingo.Main;
    opens org.example.pazduolingo.Main to javafx.fxml;
    exports org.example.pazduolingo.Training;
    opens org.example.pazduolingo.Training to javafx.fxml;
    exports org.example.pazduolingo.Utilites;
    opens org.example.pazduolingo.Utilites to javafx.fxml;

    opens org.example.pazduolingo.Settings to javafx.fxml;

    exports org.example.pazduolingo.QuizEditor;
    opens org.example.pazduolingo.QuizEditor to javafx.fxml;

    opens org.example.pazduolingo.Stats to javafx.fxml, javafx.base;
    exports org.example.pazduolingo;
    opens org.example.pazduolingo to javafx.fxml;

}