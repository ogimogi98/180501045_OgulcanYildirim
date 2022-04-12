package com.company;

public class Unterricht {

    private String UnterrichtID;
    private String unterrichtName;
    private String unterrichtInfo;
    private int lehrerID;
    private int studentID;


    public Unterricht() {
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

    public int getLehrerID() {
        return lehrerID;
    }

    public void setLehrerID(int lehrerID) {
        this.lehrerID = lehrerID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }
}
