module com.example.layout_and_events {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.layout_and_events to javafx.fxml;
    exports com.example.layout_and_events;
}