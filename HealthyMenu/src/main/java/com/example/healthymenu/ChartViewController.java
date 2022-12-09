package com.example.healthymenu;

import com.example.healthymenu.model.Food;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartViewController {
    @FXML
    private Button btnBack;
    @FXML
    private PieChart pieChart;
    @FXML
    void back(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("HealthyMenu-view.fxml"));
        Parent view1 = loader.load();
        Scene view1Scene = new Scene(view1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();
    }

    public void initialize() {
        // Get the controller object to get to the food list
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthyMenu-view.fxml"));
        try {
            Parent root = (Parent) loader.load();
        } catch (Exception e) {}
        HelloController controller = (HelloController) loader.getController();
        List<Food> food = controller.getFood();
        // Update the pie chart data from the food list data
        pieChart.getData().clear();
        // We get a map with all the categories and the sum of its weights
        Map<String, Integer> result;
        result = food.stream()
                .collect(Collectors.groupingBy(
                        f -> f.getCategory(),
                        Collectors.summingInt(f -> f.getWeight())
                ));
        // We add an entry for the pie chart with each category and its sum
        result.forEach((cat, sum) -> {
            pieChart.getData().add(new PieChart.Data(cat, sum));
        });
    }
}
