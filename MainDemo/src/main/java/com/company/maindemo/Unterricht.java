package com.company.maindemo;

public class Unterricht {

    private String UnterrichtID;
    private String unterrichtName;
    private String unterrichtInfo;
    private String modul;



    public Unterricht(String UnterrichtID,String unterrichtName,String unterrichtInfo,String modul) {

        this.UnterrichtID=UnterrichtID;
        this.unterrichtName=unterrichtName;
        this.unterrichtInfo=unterrichtInfo;
        this.setModul(modul);

    }


    public String getUnterrichtID() {
        return UnterrichtID;
    }

    public void setUnterrichtID(String unterrichtID) {
        UnterrichtID = unterrichtID;
    }

    public String getUnterrichtName() {
        return unterrichtName;
    }

    public void setUnterrichtName(String unterrichtName) {
        this.unterrichtName = unterrichtName;
    }

    public String getUnterrichtInfo() {
        return unterrichtInfo;
    }

    public void setUnterrichtInfo(String unterrichtInfo) {
        this.unterrichtInfo = unterrichtInfo;
    }

    public String getModul() {
        return modul;
    }

    public void setModul(String modul) {
        this.modul = modul;
    }
}
