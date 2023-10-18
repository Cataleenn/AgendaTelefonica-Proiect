package com.example.proiect_agenda;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
    private final StringProperty nume;
    private final StringProperty prenume;
    private final StringProperty adresa;
    private final StringProperty nr_telefon;

    public Person(String nume, String prenume, String adresa, String nr_telefon) {
        this.nume = new SimpleStringProperty(nume);
        this.prenume = new SimpleStringProperty(prenume);
        this.adresa = new SimpleStringProperty(adresa);
        this.nr_telefon = new SimpleStringProperty(nr_telefon);
    }

    public String getNume() {
        return nume.get();
    }

    public StringProperty numeProperty() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume.set(nume);
    }

    public String getPrenume() {
        return prenume.get();
    }

    public StringProperty prenumeProperty() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume.set(prenume);
    }

    public String getAdresa() {
        return adresa.get();
    }

    public StringProperty adresaProperty() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa.set(adresa);
    }

    public String getNr_telefon() {
        return nr_telefon.get();
    }

    public StringProperty nr_telefonProperty() {
        return nr_telefon;
    }

    public void setNr_telefon(String nr_telefon) {
        this.nr_telefon.set(nr_telefon);
    }
}

