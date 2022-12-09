module com.jpoveda.personfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.jpoveda.personfx to javafx.fxml;
    exports com.jpoveda.personfx;
    exports com.jpoveda.personfx.model.person;
}