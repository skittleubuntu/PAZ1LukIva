module org.example.pazduolingo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.pazduolingo to javafx.fxml;
    exports org.example.pazduolingo;
}