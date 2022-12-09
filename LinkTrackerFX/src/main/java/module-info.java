module com.example.linktrackerfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.linktrackerfx to javafx.fxml;
    exports com.example.linktrackerfx;
}