package com.company;

public class Lehrer {

    private int lehrerID;
    private String TC;
    private String name;
    private String nachName;
    private String geschlect;
    private String adresse;
    private String geburtsDatum;
    private String fachGebiet;
    private String unterrichtID;


    public Lehrer() {
    }


    public int getLehrerID() {
        return lehrerID;
    }

    public void setLehrerID(int lehrerID) {
        this.lehrerID = lehrerID;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNachName() {
        return nachName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public String getGeschlect() {
        return geschlect;
    }

    public void setGeschlect(String geschlect) {
        this.geschlect = geschlect;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(String geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public String getFachGebiet() {
        return fachGebiet;
    }

    public void setFachGebiet(String fachGebiet) {
        this.fachGebiet = fachGebiet;
    }

    public String getUnterrichtID() {
        return unterrichtID;
    }

    public void setUnterrichtID(String unterrichtID) {
        this.unterrichtID = unterrichtID;
    }
}
