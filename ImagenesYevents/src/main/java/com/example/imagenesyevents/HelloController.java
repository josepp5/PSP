package com.example.imagenesyevents;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Optional;

public class HelloController {

    @FXML
    private ImageView image;

    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(ActionEvent event) {

    }

    public void initialize(){
        image.setImage(new Image("file:src/main/resources/images/descarga.jpg"));
    }

    public void setOnCloseListener(Stage stage) {
        stage.setOnCloseRequest(e -> {
            Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
            dialog.setTitle("Confirmation");
            dialog.setHeaderText("");
            dialog.setContentText("Are you sure you want to close the application?");
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.get() == ButtonType.OK){
                stage.close();
            } else {
                // Code for "Cancel"
                e.consume();
            }


             // This way we can prevent window from closing!
        });
    }
}