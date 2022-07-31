package com.company.maindemo;

public class anmeldenSetter {
    private String UID;
    private String UNAME;
    private String ECTS;
    private String lehID;
    private String lehName;
    private String studID;

    public anmeldenSetter(String UID,String UNAME,String ECTS,String lehID, String lehName,String studID){

            setUID(UID);
            setUNAME(UNAME);
            setECTS(ECTS);
            setLehID(lehID);
            setLehName(lehName);
            setStudID(studID);

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

    public String getLehID() {
        return lehID;
    }

    public void setLehID(String lehID) {
        this.lehID = lehID;
    }

    public String getLehName() {
        return lehName;
    }

    public void setLehName(String lehName) {
        this.lehName = lehName;
    }

    public String getStudID() {
        return studID;
    }

    public void setStudID(String studID) {
        this.studID = studID;
    }
}
