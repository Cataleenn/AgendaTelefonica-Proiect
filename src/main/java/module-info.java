module com.example.proiect_agenda {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.proiect_agenda to javafx.fxml;
    exports com.example.proiect_agenda;
}