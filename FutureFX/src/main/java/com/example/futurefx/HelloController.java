package com.example.futurefx;

import com.example.futurefx.model.WebPage;
import com.example.futurefx.services.WebPageScheduledService;
import com.example.futurefx.services.WebPageService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class HelloController {
    private WebPageService service;
    @FXML
    private Label lblProcessed;
    @FXML
    private Label lblTotalLinks;
    @FXML
    private Button processedText;
    private WebPageScheduledService scheduledService;

    public void initialize(){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);

        service = new WebPageService(executor);
        scheduledService = new WebPageScheduledService(executor);

        service.setOnSucceeded(e -> {
            List<WebPage> l = service.getValue();
            System.out.println(l);
            //service.reset();
        });
        service.setOnCancelled(e -> {
            service.reset();
        });
        service.setOnFailed(e -> {
            service.reset();
        });
        lblTotalLinks.textProperty().bind(service.messageProperty());

        scheduledService.setOnSucceeded(s ->{
            if (scheduledService.getValue()){
                scheduledService.cancel();
                service.cancel();
            }
            lblProcessed.setText(scheduledService.getMessage());
        });
        scheduledService.setPeriod(Duration.millis(500));
    }

    @FXML
    protected void onHelloButtonClick() {
        service.reset();
        service.start();

        scheduledService.reset();
        scheduledService.start();
    }
}