package com.example.proiect_agenda;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateController {
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

    private HelloController mainController;
    private Connect connect;
    private Person selectedPerson;

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
    }

    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
        fillFieldsWithPersonData();
    }

    @FXML
    private void initialize() {
        connect = new Connect(); // Instantiate the Connect class
    }

    private void fillFieldsWithPersonData() {
        if (selectedPerson != null) {
            nume_textbox.setText(selectedPerson.getNume());
            prenume_textbox.setText(selectedPerson.getPrenume());
            adresa_textbox.setText(selectedPerson.getAdresa());
            nr_telefon_textbox.setText(selectedPerson.getNr_telefon());
        }
    }

    @FXML
    private void onOKButtonClick() {
        if (selectedPerson != null) {
            // Retrieve the updated data from the input fields
            String nume = nume_textbox.getText();
            String prenume = prenume_textbox.getText();
            String adresa = adresa_textbox.getText();
            String nrTelefon = nr_telefon_textbox.getText();

            // Update the selected person's data
            selectedPerson.setNume(nume);
            selectedPerson.setPrenume(prenume);
            selectedPerson.setAdresa(adresa);
            selectedPerson.setNr_telefon(nrTelefon);

            // Perform the update operation in the database
            connect.updatePerson(selectedPerson);

            // Update the main table view in the main controller
            mainController.updateTableView();

            // Close the update person window
            Stage updatePersonStage = (Stage) ok_btn.getScene().getWindow();
            updatePersonStage.close();

        }
    }
}
