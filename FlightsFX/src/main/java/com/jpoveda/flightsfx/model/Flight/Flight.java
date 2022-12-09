package com.jpoveda.flightsfx.model.Flight;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Flight {
    String flightNum;
    String destination;
    LocalDateTime departure;
    LocalTime duration;

    public Flight(String flightNum) {
        this.flightNum = flightNum;
    }

    public Flight(String flightNum, String destination, LocalDateTime departure, LocalTime duration) {
        this.flightNum = flightNum;
        this.destination = destination;
        this.departure = departure;
        this.duration = duration;
    }

    public String getFlightNum() {
        return flightNum;
    }

    public void setFlightNum(String flightNum) {
        this.flightNum = flightNum;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFormattedDeparture() {
        return departure.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public LocalDateTime getDeparture() {return departure;}

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public String getFormattedDuration() {
        return duration.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return flightNum + ";" + destination + ";" + getFormattedDeparture() + ";" + getFormattedDuration();
    }
}
