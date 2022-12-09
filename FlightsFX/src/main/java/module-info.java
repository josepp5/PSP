module com.jpoveda.flightsfx {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;

    opens com.jpoveda.flightsfx to javafx.fxml;
    exports com.jpoveda.flightsfx;
    exports com.jpoveda.flightsfx.model.Flight;
    opens com.jpoveda.flightsfx.model.Flight to javafx.fxml;
}