module org.example.pazduolingo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.pazduolingo to javafx.fxml;
    exports org.example.pazduolingo;
}