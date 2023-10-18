package com.example.proiect_agenda;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HelloController {

    private ArrayList<Person> personList = new ArrayList<>();
    public void AddPersonList(Person person) {
        personList.add(person);
    }
    @FXML
    private TextField cauta_textbox;

    @FXML
    private TableColumn<Person, String> col_nr_telefon;

    @FXML
    private Button cauta_btn;

    @FXML
    private Button update_btn;

    @FXML
    private Button delete_btn;

    @FXML
    private Pane menu;

    @FXML
    private TableView<Person> tabel;

    @FXML
    private TableColumn<Person, String> col_adresa;

    @FXML
    private TableColumn<Person, String> col_prenume;

    @FXML
    private TableColumn<Person, Void> col_actiune;


    @FXML
    private TableColumn<Person, String> col_nume;

    @FXML
    private Button adauga_btn;
    private Connect connect;

    public HelloController() {
        connect = new Connect();
    }
    @FXML
    public void initialize() {

        col_nume.setCellValueFactory(data -> data.getValue().numeProperty());
        col_prenume.setCellValueFactory(data -> data.getValue().prenumeProperty());
        col_adresa.setCellValueFactory(data -> data.getValue().adresaProperty());
        col_nr_telefon.setCellValueFactory(data -> data.getValue().nr_telefonProperty());

        ResultSet resultSet = connect.executeQuery("SELECT nume, prenume, adresa, nr_telefon FROM persoane");
        populateTable(resultSet);


    }

    private void populateTable(ResultSet resultSet) {
        try {

            personList.clear();
            while (resultSet.next()) {
                String nume = resultSet.getString("nume");
                String prenume = resultSet.getString("prenume");
                String adresa = resultSet.getString("adresa");
                String nrTelefon = resultSet.getString("nr_telefon");

                Person person = new Person(nume, prenume, adresa, nrTelefon);
                tabel.getItems().add(person);
                personList.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTableView() {

        tabel.getItems().clear();

        ResultSet resultSet = connect.executeQuery("SELECT nume, prenume, adresa, nr_telefon FROM persoane");
        populateTable(resultSet);

    }

    @FXML
    private void onDeleteButtonClicked() {
        Person selectedPerson = tabel.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {

            tabel.getItems().remove(selectedPerson);

            connect.deletePerson(selectedPerson);

        }
    }

    @FXML
    private void onAdaugaButtonClick() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Adauga.fxml"));
        Parent root;
        try {
            root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));

            AdaugaController adaugaController = loader.getController();
            adaugaController.setHelloController(this);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onUpdateButtonClick() {
        Person selectedPerson = tabel.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Update.fxml"));
            Parent root;
            try {
                root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));

                UpdateController updateController = loader.getController();
                 updateController.setMainController(this);
                updateController.setSelectedPerson(selectedPerson);

                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void onCautaButtonClicked() {
        String searchText = cauta_textbox.getText().trim();

        if (!searchText.isEmpty()) {

            List<Person> filteredList = personList.stream()
                    .filter(person -> {
                        String nume = person.getNume().toLowerCase();
                        String prenume = person.getPrenume().toLowerCase();
                        String adresa = person.getAdresa().toLowerCase();
                        String nrTelefon = person.getNr_telefon().toLowerCase();

                        return nume.contains(searchText.toLowerCase()) ||
                                prenume.contains(searchText.toLowerCase()) ||
                                adresa.contains(searchText.toLowerCase()) ||
                                nrTelefon.contains(searchText.toLowerCase());
                    })
                    .collect(Collectors.toList());



            tabel.getItems().clear();
            tabel.getItems().addAll(filteredList);
        } else {

            tabel.getItems().clear();
            tabel.getItems().addAll(personList);
        }
    }


}




