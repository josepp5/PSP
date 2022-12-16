package com.jpoveda.flightsfx;

import com.jpoveda.flightsfx.model.Flight.Flight;
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
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("flights_view.fxml"));
        Parent view1 = loader.load();
        Scene view1Scene = new Scene(view1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();
    }

    public void initialize() {
        // Get the controller object to get to the food list
        FXMLLoader loader = new FXMLLoader(getClass().getResource("flights_view.fxml"));
        try {
            Parent root = (Parent) loader.load();
        } catch (Exception e) {}
        HelloController controller = (HelloController) loader.getController();
        List<Flight> flight = controller.getFlight();
        // Update the pie chart data from the food list data
        pieChart.getData().clear();
        // We get a map with all the categories and the sum of its weights
        Map<String, Integer> result;
        result = flight.stream()
                .collect(Collectors.groupingBy(
                        f -> f.getDestination(),
                        Collectors.summingInt(f -> f.getDestination().length())
                ));
        // We add an entry for the pie chart with each category and its sum
        result.forEach((cat, sum) -> {
            pieChart.getData().add(new PieChart.Data(cat, sum));
        });
    }
}
