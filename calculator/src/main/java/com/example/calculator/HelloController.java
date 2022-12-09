package com.example.calculator;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private ChoiceBox<String> choiceBoxId;
    @FXML
    private Label label;
    @FXML
    private TextField num1;
    @FXML
    private TextField num2;
    @FXML
    private TextField textResult;



    public void initialize(){
        choiceBoxId.setItems(FXCollections.observableArrayList("+","-","x","/"));
    }

    @FXML
    private void handleButton(ActionEvent event){
        if (num1.getText() != null || num2.getText() != null)
        {
            int numero1 = Integer.parseInt(num1.getText());
            int numero2 = Integer.parseInt(num2.getText());
            switch (choiceBoxId.getValue()) {
                case "x" -> textResult.setText(String.valueOf(numero1 * numero2));
                case "+" -> textResult.setText(String.valueOf(numero1 + numero2));
                case "-" -> textResult.setText(String.valueOf(numero1 - numero2));
                case "/" -> textResult.setText(String.valueOf(numero1 / numero2));
            }
        }else textResult.setText("Rellena los campos");
    }

    @FXML
    private void onMouseEntered(MouseEvent event){
        label.setStyle("-fx-background-color:green;");
    }

    @FXML
    private void onMouseExcited(MouseEvent event){
        label.setStyle("-fx-background-color:none;");
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}