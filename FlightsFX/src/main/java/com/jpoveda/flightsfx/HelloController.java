package com.jpoveda.flightsfx;

import com.jpoveda.flightsfx.model.Flight.Flight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    static DateTimeFormatter formatterDeparture = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    static DateTimeFormatter formatterDuration = DateTimeFormatter.ofPattern("HH:mm");

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
                getList()
        );
        colFlightNumber.setCellValueFactory(new PropertyValueFactory("flightNum"));
        colDestination.setCellValueFactory(new PropertyValueFactory("destination"));
        colDeparture.setCellValueFactory(new PropertyValueFactory("departure"));
        colDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        tableFlight.setItems(flight);


        //if (tableFlight.getSelectionModel() != null) btnDelete.isVisible(); else btnDelete.isDisable();
    }

    @FXML
    void onFilter(ActionEvent event) {
        var choiceItem = choiceFilter.getValue();
        List<Flight> filterFlightList = null;
        flightFilter = FXCollections.observableArrayList(getList());
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
        flight.remove(position);
        saveFile(flight);
    }

    @FXML
    private static void saveFile(List<Flight> flight) {
        try (PrintWriter pw = new PrintWriter("flights.txt")) {
            flight.stream().forEach(f -> pw.println(f.toString()));
        } catch (Exception e) {
            e.printStackTrace();
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
            saveFile(flight);
        }
    }

    private static List<Flight> getList() {
        try {
            return Files.lines(Paths.get("flights.txt"))
                    .map(line -> new Flight(line.split(";")[0],
                            line.split(";")[1],
                            LocalDateTime.parse(line.split(";")[2], formatterDeparture),
                            LocalTime.parse(line.split(";")[3], formatterDuration)))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}