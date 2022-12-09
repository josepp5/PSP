package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button buttonAccept;
    @FXML
    private ListView<String> lista;

    public void initialize(){
        //// Para la lista ///////////
        lista.setItems(FXCollections.observableArrayList("Jose", "Antonio", "Jesus","Maria"));
        lista.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //// Para la tabla ///////////
        table.setPlaceholder(new Label("No existe ningun dato"));
        colIsbn.setCellValueFactory(new PropertyValueFactory("isbn"));
        colAuthor.setCellValueFactory(new PropertyValueFactory("author"));
        colTitle.setCellValueFactory(new PropertyValueFactory("title"));

        ObservableList<Book> books = FXCollections.observableArrayList(
                new Book("1","Caperucita","Richar Witman"),
                new Book("2","Blancanieves","Richar Witman"),
                new Book("3","Pinocho","Richar Witman"),
                new Book("4","Dumbo","Richar Witman"),
                new Book("5","Aladin","Richar Witman")

        );

        table.setItems((books));
    }

    @FXML
    private TableColumn<Book,String> colIsbn;
    @FXML
    private TableColumn<Book,String> colAuthor;
    @FXML
    private TableColumn<Book,String> colTitle;
    @FXML
    private TableView<Book> table;
    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Pareces un lili!");
    }
}