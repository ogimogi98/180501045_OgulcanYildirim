package com.company.maindemo;

public class querySetter {

    private String UID;
    private String UNAME;
    private String ECTS;
    private String lehrerID;
    private String lehrerName;
    private int counter;


    public querySetter(String UID,String UNAME,String ECTS,String lehrerID,String lehrerName,int counter){

        setUID(UID);
        setUNAME(UNAME);
        setECTS(ECTS);
        setLehrerID(lehrerID);
        setLehrerName(lehrerName);
        setCounter(counter);

    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getUNAME() {
        return UNAME;
    }

    public void setUNAME(String UNAME) {
        this.UNAME = UNAME;
    }

    public String getECTS() {
        return ECTS;
    }

    public void setECTS(String ECTS) {
        this.ECTS = ECTS;
    }

    public String getLehrerID() {
        return lehrerID;
    }

    public void setLehrerID(String lehrerID) {
        this.lehrerID = lehrerID;
    }

    public String getLehrerName() {
        return lehrerName;
    }

    public void setLehrerName(String lehrerName) {
        this.lehrerName = lehrerName;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
