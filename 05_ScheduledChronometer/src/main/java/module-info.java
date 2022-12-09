module com.example.scheduledchronometer {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.scheduledchronometer to javafx.fxml;
    exports com.example.scheduledchronometer;
}