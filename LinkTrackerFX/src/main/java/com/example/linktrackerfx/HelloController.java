package com.example.linktrackerfx;

import com.example.linktrackerfx.model.WebPage;
import com.example.linktrackerfx.services.WebPageService;
import com.example.linktrackerfx.utils.LinkReader;
import javafx.collections.FXCollections;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.File;
import java.util.List;

import static com.example.linktrackerfx.utils.FileUtils.loadPages;


public class HelloController {
    @FXML
    private ListView<String> nameListView;
    @FXML
    private ListView<String> urlListView;
    @FXML
    private Label lblTotalPages;
    @FXML
    private MenuItem loadFileMenuItem;
    @FXML
    private MenuItem startMenuItem;
    @FXML
    private MenuItem clearMenuItem;
    @FXML
    private Label lblTotalLinks;
    @FXML
    private Label lblProcessedLinks;
    @FXML
    private MenuItem ExitMenuItem;

    List<WebPage> list;
    private WebPageService service;
    private ScheduledService<Integer> schedServ;

    @FXML
    void onExitMenuItemClicked(ActionEvent event) {
        // Finish the program
        System.exit(0);
    }

    @FXML
    void clearMenuItemClicked(ActionEvent event) {
        nameListView.getItems().clear();
        urlListView.getItems().clear();
        lblTotalLinks.setText("Total Links");
        lblProcessedLinks.setText("Processed");
        lblTotalPages.setText("Total Pages");
    }

    @FXML
    void onListItemClicked(MouseEvent event) {
        var selectedIndex = nameListView.getSelectionModel().getSelectedIndex();
        var links = list.get(selectedIndex).getLinks();
        if (links != null){
            urlListView.getItems().clear();
            for (String link : links) urlListView.getItems().add(link);


        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Process Erorr");
            alert.setContentText("No URL list loaded");
            alert.show();
        }
    }

    @FXML
    void loadFileMenuClicked(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        File f = fileChooser.showOpenDialog(null);
        list = loadPages(f.toPath());
        FXCollections.observableArrayList(list);

        for (WebPage webPage : list) nameListView.getItems().add(webPage.getName());
        lblTotalPages.setText("Total Pages : " +list.size());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("File loaded");
        alert.setContentText(list.size() + " pages found");
        alert.show();
    }
    @FXML
    void startMenuClicked(ActionEvent event) {
        if (list != null){
            EventHandler<WorkerStateEvent> completionEvent = new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(final WorkerStateEvent workerStateEvent) {
                    lblProcessedLinks.setText("Processed: " + workerStateEvent.getSource().getValue());
                }
            };

            service = new WebPageService(list.stream().toList(), completionEvent);
            service.setOnSucceeded(e -> {
                list = service.getValue();
                lblTotalLinks.setText(service.getMessage());
            });
            service.setOnCancelled(e -> {
                service.reset();
            });
            service.setOnFailed(e -> {
                service.reset();
            });
            service.start();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Process error");
            alert.setContentText("No URL list loaded");
            alert.show();
        }
    }
}