package com.jpoveda.flightsfx;

import com.jpoveda.flightsfx.model.Flight.Flight;
import com.jpoveda.flightsfx.utils.FileUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {
    ObservableList<Flight> flight;
    ObservableList<Flight> flightFilter;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnFilter;
    @FXML
    private Button btnChart;
    @FXML
    private ChoiceBox<String> choiceFilter;
    @FXML
    private TableColumn<LocalDateTime, Flight> colDeparture;
    @FXML
    private TableColumn<String, Flight> colDestination;
    @FXML
    private TableColumn<LocalTime, Flight> colDuration;
    @FXML
    private TableColumn<String, Flight> colFlightNumber;
    @FXML
    private TableView<Flight> tableFlight;
    @FXML
    private TextField textFDeparture;
    @FXML
    private TextField textFDestination;
    @FXML
    private TextField textFDuration;
    @FXML
    private TextField textFNumber;

    @FXML
    public List<Flight> getFlight() {
        return flight;
    }

    public static DateTimeFormatter formatterDeparture = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static DateTimeFormatter formatterDuration = DateTimeFormatter.ofPattern("HH:mm");

    @FXML
    public void initialize() {
        choiceFilter.setItems(
                FXCollections.observableArrayList(
                        "Show all flights",
                        "Show flights to currently selected city",
                        "Show long flights",
                        "Show next 5 flights",
                        "Show flight duration average"
                )
        );
        // Se carga la lista con los elementos del .txt
        flight = FXCollections.observableArrayList(
                FileUtils.loadFlights()
        );
        colFlightNumber.setCellValueFactory(new PropertyValueFactory("flightNum"));
        colDestination.setCellValueFactory(new PropertyValueFactory("destination"));
        colDeparture.setCellValueFactory(new PropertyValueFactory("formattedDeparture"));
        colDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        tableFlight.setItems(flight);
        
        btnDelete.setDisable(false);
        if (tableFlight.getSelectionModel() == null){
            btnDelete.setDisable(true);
        }
    }

    @FXML
    void goToChartView(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChartView.fxml"));
        Parent view1 = loader.load();
        Scene view1Scene = new Scene(view1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(view1Scene);
        stage.show();
    }

    @FXML
    void onFilter(ActionEvent event) {
        var choiceItem = choiceFilter.getValue();
        List<Flight> filterFlightList = null;
        flightFilter = FXCollections.observableArrayList(FileUtils.loadFlights());
        flightFilter.clear();

        switch (choiceItem) {
            case "Show all flights" -> {
                tableFlight.setItems(flight);
            }
            case "Show flights to currently selected city" -> {

                if (tableFlight.getSelectionModel().getSelectedIndex() != -1){
                    var destination = tableFlight.getSelectionModel().getSelectedItem().getDestination();
                    flightFilter.clear();
                    filterFlightList = flight.stream().filter(t -> t.getDestination().equals(destination)).collect(Collectors.toList());
                    for (Flight t : filterFlightList) {
                        flightFilter.add(t);
                    }
                    tableFlight.setItems(flightFilter);
                }
            }
            case "Show long flights" -> {
                flightFilter.clear();
                filterFlightList = flight.stream().filter(t -> t.getDuration().getHour() >= 3).collect(Collectors.toList());
                for (Flight t : filterFlightList) {
                    flightFilter.add(t);
                }
                tableFlight.setItems(flightFilter);
            }
            case "Show next 5 flights" -> {
                flightFilter.clear();
                filterFlightList = flight.stream().filter(t -> t.getDeparture().isAfter(LocalDateTime.now())).sorted(
                        Comparator.comparing(Flight::getDeparture)).limit(5).collect(Collectors.toList());
                for (Flight t : filterFlightList) {
                    flightFilter.add(t);
                }
                tableFlight.setItems(flightFilter);
            }
            case "Show flight duration average" -> {
                DateTimeFormatter dt = DateTimeFormatter.ofPattern("H:mm");
                LocalTime lt = LocalTime.ofNanoOfDay((long) flight.stream().mapToDouble
                        (f -> f.getDuration().toNanoOfDay()).average().getAsDouble());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Vuelos ");
                alert.setContentText("Average duration : " + dt.format(lt));
                alert.show();
                break;
            }
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        int position = tableFlight.getSelectionModel().getSelectedIndex();
        if (position != -1){
            flight.remove(position);
            FileUtils.saveFlights(flight);
        } else {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error deleting data");
            dialog.setContentText("There is not item selected");
            dialog.showAndWait();
        }
    }

    @FXML
    private void addFlight(ActionEvent event) {
        if (textFNumber.getText().equals("") ||
                textFDeparture.getText().equals("") ||
                textFDestination.getText().equals("") ||
                textFDuration.getText().equals("")) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error adding data");
            dialog.setContentText("No field can be empty");
            dialog.showAndWait();
        } else {
            flight.add(
                    new Flight(
                            textFNumber.getText(),
                            textFDestination.getText(),
                            LocalDateTime.parse(textFDeparture.getText(), formatterDeparture),
                            LocalTime.parse(textFDuration.getText(), formatterDuration)
                    )
            );
            FileUtils.saveFlights(flight);
        }
    }
}