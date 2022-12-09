package com.example.layout_and_events;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label label;

    @FXML
    private TextField textField;


    /// Evento click
    @FXML
    private void handleButton(ActionEvent event){
        label.setText("Hello world");
    }

    /// Evento pasar el mouse por encima
    @FXML
    private void onMouseEntered(MouseEvent event){
        label.setStyle("-fx-background-color:green;");
    }

    /// Evento sacar el mouse del label
    @FXML
    private void onMouseExcited(MouseEvent event){
        label.setStyle("-fx-background-color:none;");
    }

    /// Evento al escribir en un textfield
    @FXML
    private  void onKeyListener(KeyEvent event){
        label.setText(textField.getText());
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}