package com.example.healthymenu;

import com.example.healthymenu.model.Food;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    ObservableList<Food> food;
    @FXML
    private Button btnAnyadir;
    @FXML
    private Button btnChart;
    @FXML
    private Button btnDelete;
    @FXML
    private ChoiceBox<String> choiceCategoria;
    @FXML
    private TableColumn<String, Food> colFoodCategory;
    @FXML
    private TableColumn<String, Food> colFoodName;
    @FXML
    private TableColumn<String, Food> colWeightG;
    @FXML
    private TableColumn<?, Food> colWeightOz;
    @FXML
    private TableView<Food> tableFood;
    @FXML
    private TextField textGramos;
    @FXML
    private TextField textNombre;
    @FXML
    public List<Food> getFood() {
        return food;
    }
    @FXML
    public void initialize(){
        choiceCategoria.setItems(
                FXCollections.observableArrayList(
                        "Fruits and vegetables", "Meat and fish", "Milk derivatives", "Other"
                )
        );
        // Se carga la lista con los elementos del .txt
        food = FXCollections.observableArrayList(
                readFile()
        );

        colFoodName.setCellValueFactory(new PropertyValueFactory("name"));
        colFoodCategory.setCellValueFactory(new PropertyValueFactory("category"));
        colWeightG.setCellValueFactory(new PropertyValueFactory("weight"));
        colWeightOz.setCellValueFactory(new PropertyValueFactory("weightInOz"));
        tableFood.setItems(food);
    }
    @FXML
    private void DeleteFood(ActionEvent event){

    }

    @FXML
    private void goToChartView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChartView.fxml"));
        Parent view1 = loader.load();
        Scene view1Scene = new Scene(view1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();
    }

    @FXML
    private static List<Food> readFile() {
        try {
            return Files.lines(Paths.get("food.txt"))
                    .map(line -> new Food(line.split(";")[0],
                            line.split(";")[1], Integer.parseInt(line.split(";")[2])))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @FXML
    private static void saveFile(List<Food> food) {
        try (PrintWriter pw = new PrintWriter("food.txt")) {
            food.stream().forEach(f -> pw.println(f.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addFood(ActionEvent event) {
        if (textNombre.getText().equals("") ||
                choiceCategoria.getSelectionModel().getSelectedIndex() < 0 ||
                textGramos.getText().equals("")) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error adding data");
            dialog.setContentText("No field can be empty");
            dialog.showAndWait();
        } else {
            food.add(
                    new Food(
                            textNombre.getText(),
                            choiceCategoria.getSelectionModel().getSelectedItem(),
                            Integer.parseInt(textGramos.getText())
                    )
            );
            saveFile(food);
        }
    }
}
