package com.company.maindemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.sql.ResultSet;
import java.sql.SQLException;

public class studentController {
    @FXML
    AnchorPane studentinfoAnchorPane;
    @FXML
    AnchorPane studentHausaufgabeAnchorPane;
    @FXML
    AnchorPane studentAnmeldenAnchorPane;
    @FXML
    Button studentInfoButton;
    @FXML
    Button studentHausaufgabeButton;
    @FXML
    Button studentAnmeldenButton;

    @FXML
    Button StudentaktualisierenButton;
    @FXML
    Button StudentsucheButton;

    @FXML
    TextField StudentausweisIDTextF;
    @FXML
    TextField StudentnameTextF;
    @FXML
    TextField StudentnachnameTextF;
    @FXML
    TextField StudentadresseTextF;
    @FXML
    TextField StudentemailTextF;
    @FXML
    TextField StudentstudentIDTextF;
    @FXML
    TextField StudentpasswordTextF;
    @FXML
    TextField StudenthandyTextF;
    @FXML
    ChoiceBox StudentgeschlechtChoiceBox;
    @FXML
    DatePicker StudentgeburtsDatumPick;
    @FXML
    TextField StudentelternNameTextF;
    @FXML
    TextField StudentelternHandyTextF;
    @FXML
    TextField StudentelternartTextF;
    @FXML
    Text stdID;
    @FXML
    TextArea infoTextAreaStudent;

    ObservableList<String> geschlectArten = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity");
    public void helloMessage(String username){

        System.out.println(username);
        infoTextAreaStudent.setText("Willkommen " + username);
        stdID.setText(username);

    }
    @FXML protected void studentInfoButtonClicked() throws SQLException{

        StudentgeschlechtChoiceBox.setItems(geschlectArten);
        studentinfoAnchorPane.setVisible(true);
        ResultSet rs = prMethods.findStudent(stdID.getText());
        if (!rs.isBeforeFirst()){

           infoTextAreaStudent.setText("Studenten wird nicht gefunden.");


        }

        while (rs.next()){

            infoTextAreaStudent.setText("Studenten wird gefunden.");

            StudentstudentIDTextF.setText(rs.getString("studentID"));
            StudentpasswordTextF.setText(rs.getString("password"));
            StudentausweisIDTextF.setText(rs.getString("TC"));
            StudentnameTextF.setText(rs.getString("Name"));
            StudentnachnameTextF.setText(rs.getString("Nachname"));
            StudentadresseTextF.setText(rs.getString("Adresse"));
            StudenthandyTextF.setText(rs.getString("studentHandy"));
            StudentelternNameTextF.setText(rs.getString("elternName"));
            StudentelternHandyTextF.setText(rs.getString("elternHandy"));
            StudentelternartTextF.setText(rs.getString("elternTyp"));
            StudentemailTextF.setText(rs.getString("email"));
            StudentgeschlechtChoiceBox.setValue(rs.getObject("geschlect"));
            StudentgeburtsDatumPick.setValue(rs.getDate("geburtsDatum").toLocalDate());


            rs.close();
        }


    }
    @FXML protected void studentHausaufgabeButtonClicked() throws SQLException{



    }
    @FXML protected void  studentAnmeldenButtonClicked() throws SQLException{



    }

    @FXML protected void aktualisierenButtonClicked() throws SQLException {


        Student aktualisierteStudent = new Student(StudentstudentIDTextF.getText(), StudentpasswordTextF.getText(), StudentausweisIDTextF.getText(), StudentnameTextF.getText(),
                StudentnachnameTextF.getText(), StudentemailTextF.getText(), StudenthandyTextF.getText(), StudentelternNameTextF.getText(), StudentelternHandyTextF.getText(), StudentelternartTextF.getText(),
                java.sql.Date.valueOf(StudentgeburtsDatumPick.getValue()), StudentadresseTextF.getText(),StudentgeschlechtChoiceBox.getValue().toString(),null );

        prMethods.updateStudent(aktualisierteStudent);
        infoTextAreaStudent.setText("Studenten wird aktualisiert.");

    }
    @FXML protected void sucheButtonClicked() throws SQLException{}





}
