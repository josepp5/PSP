package com.jpoveda.flightsfx.utils;

import com.jpoveda.flightsfx.model.Flight.Flight;

import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.jpoveda.flightsfx.HelloController.formatterDeparture;
import static com.jpoveda.flightsfx.HelloController.formatterDuration;

public class FileUtils {
    public static List<Flight> loadFlights(){
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
    };
    public static void saveFlights(List<Flight> flight){
        try (PrintWriter pw = new PrintWriter("flights.txt")) {
            flight.stream().forEach(f -> pw.println(f.toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    };
}
