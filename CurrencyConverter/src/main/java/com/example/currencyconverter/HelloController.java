package com.example.currencyconverter;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.text.DecimalFormat;

public class HelloController {
    @FXML
    private ToggleGroup currencyGroup;
    @FXML
    private TextField textCurrency;
    @FXML
    private Label lblSymbol;
    @FXML
    private Label lblResult;
    @FXML
    private MenuBar menuCurrency;
    @FXML
    void textChanged(KeyEvent event) {


    }

    public void initialize() {
        textCurrency.setOnKeyTyped(e -> {
            if (textCurrency.getText() != "") {
                float num = toDecimal(Float.parseFloat(textCurrency.getText()));

                RadioMenuItem item = (RadioMenuItem) currencyGroup.getSelectedToggle();

                switch (item.getId()) {
                    case "mi1":
                        lblResult.setText("Result : " + toDecimal(num * 1.10f) + " $");
                        lblSymbol.setText("€");
                        break;
                    case "mi2":
                        lblResult.setText("Result : " + toDecimal(num * 0.8f) + " GBP");
                        lblSymbol.setText("€");
                        break;
                    case "mi3":
                        lblResult.setText("Result : " + toDecimal(num / 1.10f) + " €");
                        lblSymbol.setText("$");
                        break;
                    case "mi4":
                        lblResult.setText("Result : " + toDecimal(num * 0.7f) + " GBP");
                        lblSymbol.setText("$");
                        break;
                    case "mi5":
                        lblResult.setText("Result : " + toDecimal(num / 0.8f) + " €");
                        lblSymbol.setText("GBP");
                        break;
                    case "mi6":
                        lblResult.setText("Result : " + toDecimal(num / 0.7f) + " $");
                        lblSymbol.setText("GBP");
                        break;
                }
            } else {
                lblResult.setText("Write a quantity");
            }
        });
    }
    @FXML
    void reset(ActionEvent event) {
        RadioMenuItem item = (RadioMenuItem) currencyGroup.getSelectedToggle();
        switch (item.getId()) {
            case "mi1":
                lblSymbol.setText("€");
                break;
            case "mi2":
                lblSymbol.setText("€");
                break;
            case "mi3":
                lblSymbol.setText("$");
                break;
            case "mi4":
                lblSymbol.setText("$");
                break;
            case "mi5":
                lblSymbol.setText("GBP");
                break;
            case "mi6":
                lblSymbol.setText("GBP");
                break;
        }
        lblResult.setText("Result");
        textCurrency.setText("");
    }

    public float toDecimal(float num) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        df.format(num);
        return num;
    }
}