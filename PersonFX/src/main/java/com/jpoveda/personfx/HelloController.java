package com.jpoveda.personfx;

import com.jpoveda.personfx.model.DataSourceException;
import com.jpoveda.personfx.model.IPersonDataSource;
import com.jpoveda.personfx.model.PersonFileDataSource;
import com.jpoveda.personfx.model.person.Person;
import com.jpoveda.personfx.model.person.PersonType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class HelloController {
    private List<Person> persons;
    private IPersonDataSource dataSource = new PersonFileDataSource("persons.txt");
    @FXML
    private TableColumn<Person, String> colPersonAge;
    @FXML
    private TableColumn<Person, String> colPersonLastName;
    @FXML
    private TableColumn<Person, String> colPersonName;
    @FXML
    private TableColumn<Person, String> colPersonType;
    @FXML
    private TableView<Person> tableView;

    public void initialize(){
        dataSource = new PersonFileDataSource("persons.txt");
        try {
            persons = dataSource.getList();
            colPersonName.setCellValueFactory(new PropertyValueFactory("name"));
            colPersonLastName.setCellValueFactory(new PropertyValueFactory("lastName"));
            colPersonAge.setCellValueFactory(new PropertyValueFactory("age"));
            colPersonType.setCellValueFactory(new PropertyValueFactory("type"));

            tableView.setItems(FXCollections.observableArrayList(persons));
           /*System.out.println(persons);

           persons.add(new Person("Jose","Company",39, PersonType.Vago));
           dataSource.save(persons);

           dataSource.delete(persons.size()-1);
            */
        } catch (DataSourceException e) {
            throw new RuntimeException(e);
        }
    }
    public void onDelete(ActionEvent event) throws DataSourceException {
        // Returns the index of the selected item
        int position = tableView.getSelectionModel().getSelectedIndex();
        // Returns the selected item(object) in the list
        //tableView.getSelectionModel().getSelectedItem();
        // Delete de item
        dataSource.delete(position);
        persons = dataSource.getList();
        tableView.setItems(FXCollections.observableArrayList(persons));
    }
}