package com.company;

public class Student {

    private int studentID;
    private String TC;
    private String name;
    private String nachName;
    private String geschlect;
    private String adresse;
    private String geburtsDatum;
    private String unterrichtID;


    public Student() {
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
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

    public String getUnterrichtID() {
        return unterrichtID;
    }

    public void setUnterrichtID(String unterrichtID) {
        this.unterrichtID = unterrichtID;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }
}
