module com.example.imagenesyevents {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.imagenesyevents to javafx.fxml;
    exports com.example.imagenesyevents;
}