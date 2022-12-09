module com.example.healthymenu {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.healthymenu to javafx.fxml;
    exports com.example.healthymenu;
    exports com.example.healthymenu.model;
}