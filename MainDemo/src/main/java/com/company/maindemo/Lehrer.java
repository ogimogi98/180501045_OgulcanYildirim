package com.company.maindemo;

import java.util.Date;

public class Lehrer {

    private String lehrerID;

    private String TC;
    private String lehrerName;
    private String lehrernachName;
    private String geschlect;
    private String adresse;
    private Date geburtsDatum;
    private String lehrerHandy;
    private String lehrerEmail;
    private String lehrerpassword;
    private String fachGebiet;
    private String unterrichtID;


    public Lehrer(String lehrerID, String password, String TC, String Name, String Nachname,
                  String geschlect , String Adresse, Date geburtsDatum, String lehrerHandy,
                  String Fachgebiet, String UnterrichtID, String email) {

        this.lehrerID=lehrerID;
        this.lehrerpassword = password;
        this.TC=TC;
        this.lehrerName=Name;
        this.lehrernachName=Nachname;
        this.lehrerEmail = email;
        this.lehrerHandy = lehrerHandy;
        this.fachGebiet = Fachgebiet;
        this.unterrichtID = UnterrichtID;
        this.geburtsDatum = geburtsDatum;
        this.adresse=Adresse;
        this.geschlect = geschlect;


    }


    public String getLehrerID() {
        return lehrerID;
    }

    public void setLehrerID(String lehrerID) {
        this.lehrerID = lehrerID;
    }

    public String getTC() {
        return TC;
    }

    public void setTC(String TC) {
        this.TC = TC;
    }

    public String getlehrerName() {
        return getLehrerName();
    }

    public void setlehrerName(String name) {
        this.setLehrerName(name);
    }

    public String getlehrernachName() {
        return getLehrernachName();
    }

    public void setlehrernachName(String lehrernachName) {
        this.setLehrernachName(lehrernachName);
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

    public void setGeburtsDatum(Date geburtsDatum) {
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

    public String getLehrerName() {
        return lehrerName;
    }

    public void setLehrerName(String lehrerName) {
        this.lehrerName = lehrerName;
    }

    public String getLehrernachName() {
        return lehrernachName;
    }

    public void setLehrernachName(String lehrernachName) {
        this.lehrernachName = lehrernachName;
    }

    public String getLehrerHandy() {
        return lehrerHandy;
    }

    public void setLehrerHandy(String lehrerHandy) {
        this.lehrerHandy = lehrerHandy;
    }

    public String getLehrerEmail() {
        return lehrerEmail;
    }

    public void setLehrerEmail(String lehrerEmail) {
        this.lehrerEmail = lehrerEmail;
    }

    public String getLehrerpassword() {
        return lehrerpassword;
    }

    public void setLehrerpassword(String lehrerpassword) {
        this.lehrerpassword = lehrerpassword;
    }
}
