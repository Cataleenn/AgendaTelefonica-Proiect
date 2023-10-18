package com.example.proiect_agenda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class AdaugaController {
    @FXML
    private TextField nume_textbox;

    @FXML
    private TextField prenume_textbox;

    @FXML
    private TextField adresa_textbox;

    @FXML
    private TextField nr_telefon_textbox;

    @FXML
    private Button ok_btn;

    public HelloController mainController;
    private Connect connect;


    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
    }


    @FXML
    private void initialize() {
        connect = new Connect(); // Instantiate the Connect class
    }
    public void setHelloController(HelloController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void onOKButtonClick() {

        String nume = nume_textbox.getText();
        String prenume = prenume_textbox.getText();
        String adresa = adresa_textbox.getText();
        String nrTelefon = nr_telefon_textbox.getText();


        Person newPerson = new Person(nume, prenume, adresa, nrTelefon);

        mainController.AddPersonList(newPerson);


        connect.insertPerson(newPerson);


        Stage addPersonStage = (Stage) ok_btn.getScene().getWindow();
        addPersonStage.close();
        mainController.updateTableView();
    }




}