package com.company.maindemo;

public class Aufgabe {

    private int aufgabeID;
    private String aufgabeName;
    private Float endAbgabeZeit;
    private Float note;
    private String unterrichtID;


    public int getAufgabeID() {
        return aufgabeID;
    }

    public void setAufgabeID(int aufgabeID) {
        this.aufgabeID = aufgabeID;
    }

    public String getAufgabeName() {
        return aufgabeName;
    }

    public void setAufgabeName(String aufgabeName) {
        this.aufgabeName = aufgabeName;
    }

    public Float getEndAbgabeZeit() {
        return endAbgabeZeit;
    }

    public void setEndAbgabeZeit(Float endAbgabeZeit) {
        this.endAbgabeZeit = endAbgabeZeit;
    }

    public Float getNote() {
        return note;
    }

    public void setNote(Float note) {
        this.note = note;
    }

    public String getUnterrichtID() {
        return unterrichtID;
    }

    public void setUnterrichtID(String unterrichtID) {
        this.unterrichtID = unterrichtID;
    }
}
