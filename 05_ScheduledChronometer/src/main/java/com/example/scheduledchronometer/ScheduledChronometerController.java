package com.example.scheduledchronometer;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;


public class ScheduledChronometerController {

    @FXML
    private TextField secondsText;
    @FXML
    private Label secondsLabel;
    @FXML
    private Button startBtn;
    @FXML
    private Button pauseBtn;

    private ScheduledService<Integer> schedServ;
    private int seconds = 0;

    public void initialize() {
        pauseBtn.setDisable(true);

        schedServ = new ScheduledService<Integer>() {
            @Override
            protected Task<Integer> createTask() {
                return new Task<Integer>() {
                    @Override
                    protected Integer call() throws Exception {
                        return --seconds;
                    }
                };
            }
        };

        schedServ.setDelay(Duration.seconds(1)); // It will start after 1s
        schedServ.setPeriod(Duration.seconds(1)); // Execute every second after

        schedServ.setOnSucceeded(e -> {
            int val = schedServ.getValue();
            if (val <= 0) { // finished
                schedServ.cancel(); // Cancel service (won't run anymore).
                pauseBtn.setDisable(true);
                startBtn.setDisable(false);
            }
            secondsLabel.setText(String.valueOf(val));
        });
    }

    @FXML
    private void startCount(ActionEvent event) {
        seconds = Integer.parseInt(secondsText.getText());
        secondsLabel.setText(String.valueOf(seconds));
        startBtn.setDisable(true);
        pauseBtn.setDisable(false);
        pauseBtn.setText("Pause");
        schedServ.restart();
    }

    @FXML
    private void pauseCount(ActionEvent event) {
        if (schedServ.isRunning()) {
            pauseBtn.setText("Resume");
            schedServ.cancel();
            schedServ.reset();
        } else {
            startBtn.setDisable(true);
            pauseBtn.setDisable(false);
            pauseBtn.setText("Pause");
            schedServ.restart();
        }
    }

}
