package com.company.maindemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
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
    Button aufgabenSucheButton;
    @FXML Button anmeldenButton;
    @FXML Button abmeldenButton;

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

    @FXML TableView <anmeldenSetter> tableviewAnmelden;
    @FXML TableColumn<anmeldenSetter,String> col_unterrichtIDAnmelden;
    @FXML TableColumn <anmeldenSetter,String> col_unterrichtAnmelden;
    @FXML TableColumn<anmeldenSetter,String> col_ectsAnmelde;
    @FXML TableColumn <anmeldenSetter,String> col_lehrerIDAnmelde;
    @FXML TableColumn <anmeldenSetter,String> col_lehrerNameAnmelde;
    @FXML TableView <anmeldenSetter> tableviewAbmelden;
    @FXML TableColumn <anmeldenSetter,String> col_unterrichtIDAbmelden;
    @FXML TableColumn <anmeldenSetter,String> col_unterrichtAbmelden;
    @FXML TableColumn <anmeldenSetter,String> col_ectsAbmelde;
    @FXML TableColumn <anmeldenSetter,String> col_lehrerIDAbmelde;
    @FXML TableColumn <anmeldenSetter,String> col_lehrerNameAbmelde;
    ObservableList<String> geschlectArten = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity");
    ObservableList<anmeldenSetter> anmeldenSetters = FXCollections.observableArrayList();
    ObservableList<anmeldenSetter> abmeldenSetters = FXCollections.observableArrayList();
    public void helloMessage(String username){

        System.out.println(username);
        infoTextAreaStudent.setText("Willkommen " + username);
        stdID.setText(username);

    }
    public void anmeldenaktualisieren() throws SQLException{
        ResultSet anmeldeRS = prMethods.listFreiUnterrichten(stdID.getText());
        ResultSet abmeldeRS = prMethods.listanmeldeteUnterrichten(stdID.getText());
        anmeldenSetters.clear();
        abmeldenSetters.clear();

        while (anmeldeRS.next()){
            anmeldenSetters.add(new anmeldenSetter(anmeldeRS.getString(1),anmeldeRS.getString(2),anmeldeRS.getString(4),
                    anmeldeRS.getString("lehrerID"),anmeldeRS.getString("HocaIsimSoyisim"),stdID.getText()));
        }
        anmeldeRS.close();
        while (abmeldeRS.next()){

            abmeldenSetters.add(new anmeldenSetter(abmeldeRS.getString(1),abmeldeRS.getString(2),abmeldeRS.getString("ects"),
                    abmeldeRS.getString("lehrerID"),abmeldeRS.getString("HocaIsimSoyisim"),stdID.getText()));
        }
        col_unterrichtIDAnmelden.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("UID"));
        col_unterrichtAnmelden.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("UNAME"));
        col_ectsAnmelde.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("ECTS"));
        col_lehrerIDAnmelde.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("lehID"));
        col_lehrerNameAnmelde.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("lehName"));
        tableviewAnmelden.setItems(anmeldenSetters);

        col_unterrichtIDAbmelden.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("UID"));
        col_unterrichtAbmelden.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("UNAME"));
        col_ectsAbmelde.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("ECTS"));
        col_lehrerIDAbmelde.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("lehID"));
        col_lehrerNameAbmelde.setCellValueFactory(new PropertyValueFactory<anmeldenSetter,String>("lehName"));
        tableviewAbmelden.setItems(abmeldenSetters);
    }
    @FXML protected void studentInfoButtonClicked() throws SQLException{
        studentAnmeldenAnchorPane.setVisible(false);
        studentHausaufgabeAnchorPane.setVisible(false);
        studentinfoAnchorPane.setVisible(true);

        StudentgeschlechtChoiceBox.setItems(geschlectArten);

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

        studentAnmeldenAnchorPane.setVisible(false);
        studentHausaufgabeAnchorPane.setVisible(true);
        studentinfoAnchorPane.setVisible(false);

    }
    @FXML protected void  studentAnmeldenButtonClicked() throws SQLException{
        studentAnmeldenAnchorPane.setVisible(true);
        studentHausaufgabeAnchorPane.setVisible(false);
        studentinfoAnchorPane.setVisible(false);
        anmeldenaktualisieren();


    }



    @FXML protected void aktualisierenButtonClicked() throws SQLException {


        Student aktualisierteStudent = new Student(StudentstudentIDTextF.getText(), StudentpasswordTextF.getText(), StudentausweisIDTextF.getText(), StudentnameTextF.getText(),
                StudentnachnameTextF.getText(), StudentemailTextF.getText(), StudenthandyTextF.getText(), StudentelternNameTextF.getText(), StudentelternHandyTextF.getText(), StudentelternartTextF.getText(),
                java.sql.Date.valueOf(StudentgeburtsDatumPick.getValue()), StudentadresseTextF.getText(),StudentgeschlechtChoiceBox.getValue().toString(),null );

        prMethods.updateStudent(aktualisierteStudent);
        infoTextAreaStudent.setText("Studenten wird aktualisiert.");

    }
    @FXML protected void aufgabenSucheButtonClicked() throws SQLException{


    }

    @FXML protected void anmeldenButtonClicked() throws SQLException{

        anmeldenSetter anmeldensetter = tableviewAnmelden.getSelectionModel().getSelectedItem();
        anmeldensetter.setStudID(stdID.getText());
        System.out.println(anmeldensetter.getUID()+" "+ anmeldensetter.getLehID()+" "+ anmeldensetter.getStudID());
        anmeldensetter.setStudID(stdID.getText());
        prMethods.anmeldenUnterricht(anmeldensetter.getStudID(),anmeldensetter.getLehID(),anmeldensetter.getUID());
        prMethods.anmeldenUpdateUnterricht(anmeldensetter.getStudID(),anmeldensetter.getLehID(),anmeldensetter.getUID());
        anmeldenaktualisieren();


    }
    @FXML protected void abmeldenButtonClicked() throws SQLException{

        anmeldenSetter abmeldensetter = tableviewAbmelden.getSelectionModel().getSelectedItem();
        abmeldensetter.setStudID(stdID.getText());
        System.out.println(abmeldensetter.getUID()+" "+ abmeldensetter.getLehID()+" "+ abmeldensetter.getStudID());
        abmeldensetter.setStudID(stdID.getText());
        prMethods.abmeldenUnterricht(abmeldensetter.getStudID(),abmeldensetter.getUID());

        anmeldenaktualisieren();

    }

    @FXML
    Button outLogo;
    @FXML
    protected void logoutLogoClicked() throws IOException {

        System.out.println("logo Clicked");

        prMethods.changeScene("hello-view.fxml",outLogo);

    }

}
