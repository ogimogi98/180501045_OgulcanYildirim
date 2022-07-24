package com.company.maindemo;

import java.sql.Date;

public class Student {

    private String studentID;
    private String TC;
    private String password;
    private String name;
    private String nachName;
    private String geschlect;
    private String adresse;
    private Date geburtsDatum;
    private String unterrichtID;

    private String studentHandy;

    private  String elternName;

    private  String elternHandy;

    private  String elternTyp;

    private  String email;



    public  Student(String studentID,String password ,String tc, String name, String nachname, String email, String handy, String Eltern, String Enummer,String Elterntyp ,Date geburtsDatum, String adresse, String geschlect,String unterrichtID) {

        this.studentID=studentID;
        this.password = password;
        this.TC=tc;
        this.name=name;
        this.nachName=nachname;
        this.email = email;
        this.studentHandy = handy;
        this.elternName = Eltern;
        this.elternHandy = Enummer;
        this.elternTyp = Elterntyp;
        this.geburtsDatum = geburtsDatum;
        this.adresse=adresse;
        this.geschlect = geschlect;
        this.unterrichtID = unterrichtID;


    }








    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
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

    public Date getGeburtsDatum() {
        return geburtsDatum;
    }

    public void setGeburtsDatum(String geburtsDatum) {
        this.setGeburtsDatum(Date.valueOf(geburtsDatum));
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


    public String getStudentHandy() {
        return studentHandy;
    }

    public String getElternName() {
        return elternName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getElternHandy() {
        return elternHandy;
    }

    public void setElternHandy(String elternHandy) {
        this.elternHandy = elternHandy;
    }

    public void setGeburtsDatum(Date geburtsDatum) {
        this.geburtsDatum = geburtsDatum;
    }

    public void setStudentHandy(String studentHandy) {
        this.studentHandy = studentHandy;
    }

    public void setElternName(String elternName) {
        this.elternName = elternName;
    }

    public String getElternTyp() {
        return elternTyp;
    }

    public void setElternTyp(String elternTyp) {
        this.elternTyp = elternTyp;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


