package com.company.maindemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class adminLehrerPageController {

    @FXML
    TableView<Lehrer> lehrerTableview;

    @FXML
    ChoiceBox fachChoicebox;
    @FXML
    CheckBox fachCheckBox;


    @FXML
    TextField ausweisIDTextF;
    @FXML
    TextField nameTextF;
    @FXML
    TextField nachnameTextF;
    @FXML
    TextField adresseTextF;
    @FXML
    TextField emailTextF;
    @FXML
    TextField lehrerIDTextF;
    @FXML
    TextField passwordTextF;
    @FXML
    TextField handyTextF;
    @FXML
    ChoiceBox geschlechtChoiceBox;
    @FXML
    DatePicker geburtsDatumPick;
    @FXML
    TextField fachgebietTextF;
    @FXML
    ChoiceBox abkurzungChoicebox;
    @FXML
    TextField kapazitatTextF;
    @FXML
    Button hinzufugenButton;
    @FXML
    Button anmeldeButton;
    @FXML
    Button generateButton;
    @FXML
    AnchorPane hinzufugenAnchorPane;
    @FXML SplitPane auflistenSplitPane;
    @FXML TableColumn<Lehrer,String> col_lehrerID;
    @FXML TableColumn<Lehrer,String> col_TC;
    @FXML TableColumn<Lehrer,String> col_name;
    @FXML TableColumn<Lehrer,String> col_nachname;
    @FXML TableColumn<Lehrer,String> col_email;
    @FXML TableColumn<Lehrer,String> col_handy;
    @FXML TableColumn<Lehrer,String> col_abkurzung;
    @FXML TableColumn<Lehrer,String> col_fachgebiet;
    @FXML TableColumn<Lehrer,String> col_fachınfo;
    @FXML TextArea infoTextArea;

    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    ObservableList<String> geschlectArten = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity");

    ObservableList<String> unterrichtabkurzung = FXCollections.observableArrayList();
    ObservableList<Lehrer> oblist = FXCollections.observableArrayList();

    private void adminLehrerPageController() throws SQLException{




    }




    @FXML
    protected void hinzufugenClicked() throws Exception{

        geschlechtChoiceBox.setItems(geschlectArten);
        System.out.println(unterrichtabkurzung.size());
        abkurzungChoicebox.setItems(unterrichtabkurzung);
        auflistenSplitPane.setVisible(false);

        if(hinzufugenAnchorPane.isVisible()==false) {

            hinzufugenAnchorPane.setVisible(true);
            ausweisIDTextF.setText(null);
            nameTextF.setText(null);
            nachnameTextF.setText(null);
            geburtsDatumPick.setValue(LocalDate.now());
            geschlechtChoiceBox.setValue(null);
            adresseTextF.setText(null);
            emailTextF.setText(null);
            lehrerIDTextF.setText(null);
            passwordTextF.setText(null);
            handyTextF.setText(null);
            kapazitatTextF.setText(null);
            abkurzungChoicebox.setValue(null);
            fachgebietTextF.setText(null);
            lehrerIDTextF.setPromptText("Klicken Sie zum Generate oder Schreiben ID zu Suchen.");
            passwordTextF.setPromptText("Bitte Klicken Sie Generate.");


        }
        else {


            a.setContentText("Die Daten von Lehrer ist zu verloren. Sind sie Sicher?");
            a.showAndWait();

            if (a.getResult() == ButtonType.OK){

                ausweisIDTextF.setText(null);
                nameTextF.setText(null);
                nachnameTextF.setText(null);
                geburtsDatumPick.setValue(LocalDate.now());
                geschlechtChoiceBox.setValue(null);
                adresseTextF.setText(null);
                emailTextF.setText(null);
                lehrerIDTextF.setText(null);
                passwordTextF.setText(null);
                handyTextF.setText(null);
                kapazitatTextF.setText(null);
                abkurzungChoicebox.setValue(null);
                fachgebietTextF.setText(null);
                lehrerIDTextF.setPromptText("Bitte Klicken Sie Generate.");
                passwordTextF.setPromptText("Bitte Klicken Sie Generate.");

            }

        }
    }

    @FXML protected void generateButtonClicked() throws Exception{

        passwordTextF.setText(prMethods.generatePassword(8).toString());
        lehrerIDTextF.setText(prMethods.generateID(2));
        infoTextArea.setText("Lehrer ID & Password wird generiert.");

    }

    @FXML protected void anmeldeButtonClicked() throws SQLException {

        if (prMethods.kontrolleID(ausweisIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), "lehrer", "TC")) {
            if (prMethods.kontrolleID(lehrerIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), "lehrer", "LehrerID")) {
                String date = String.valueOf(geburtsDatumPick.getValue());
                prMethods.insertLehrer(lehrerIDTextF.getText(), passwordTextF.getText(), ausweisIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), geschlechtChoiceBox.getValue().toString(), adresseTextF.getText(),
                        date, handyTextF.getText(), fachgebietTextF.getText(), String.valueOf(abkurzungChoicebox.getValue()), emailTextF.getText());
                infoTextArea.setText(nameTextF +"mit Lehrer ID"+lehrerIDTextF.getText() + "wird angemeldet.");
            }
        }

    }

    @FXML protected void auflistenButtonClicked(){

        unterrichtabkurzung.add("Alles");
        System.out.println(unterrichtabkurzung.size());
        fachChoicebox.setItems(unterrichtabkurzung);
        hinzufugenAnchorPane.setVisible(false);


        if(auflistenSplitPane.isVisible()==false) {

            auflistenSplitPane.setVisible(true);
            fachChoicebox.setValue("Alles");


        }
        else {


            fachChoicebox.setValue("null");
            fachCheckBox.setSelected(false);


        }

    }


    @FXML protected void listenButtonClicked() throws SQLException {

        oblist.clear();
        String fachgebiet = fachChoicebox.getValue().toString();

        System.out.println(fachgebiet);
        if (fachgebiet == "Alles"){
            fachgebiet = "";
            System.out.println(fachgebiet + " seçilmedi");
        } else {
            String bolum;
            bolum = fachChoicebox.getValue().toString();
            fachgebiet = " where geschlect = "+"'"+bolum+"'";
            System.out.println(fachgebiet + " seçildi");
        }

        ResultSet rs = prMethods.listTeacher(fachgebiet);

        while (rs.next()) {



            oblist.add(new Lehrer(rs.getString("lehrerID"),null,rs.getString("TC"),rs.getString("Name"),
                    rs.getString("Nachname"),null,null,null, rs.getString("lehrerHandy"),rs.getString("Fachgebiet"),
                    rs.getString("UnterrichtID"),rs.getString("email")));


        }


        col_lehrerID.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("lehrerID"));
        col_TC.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("TC"));
        col_name.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("name"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("Nachname"));
        col_email.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("email"));
        col_handy.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("lehrerHandy"));
        col_abkurzung.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("UnterrichtID"));
        col_fachgebiet.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("Fachgebiet"));

        if (fachCheckBox.isSelected()){

            col_fachınfo.setVisible(true);
            lehrerTableview.setItems(oblist);
            infoTextArea.setText("Anzal der Lehrer ist " + oblist.size()+".\n"+ "Lehrer wird gelistet.");

        }
        else {

            col_fachınfo.setVisible(false);
            lehrerTableview.setItems(oblist);
            infoTextArea.setText("Anzal der Lehrer ist " + oblist.size()+".\n"+ "Lehrer wird gelistet.");


        }




    }

    @FXML protected void sucheButtonClicked() throws SQLException{


        ResultSet rs = prMethods.findTeacher(lehrerIDTextF.getText());

        if (!rs.isBeforeFirst()){

            infoTextArea.setText("Lehrer wird nicht gefunden.");


        }

        while (rs.next()){

            infoTextArea.setText("Lehrer wird gefunden.");

            lehrerIDTextF.setText(rs.getString("LehrerID"));
            passwordTextF.setText(rs.getString("password"));
            ausweisIDTextF.setText(rs.getString("TC"));
            nameTextF.setText(rs.getString("Name"));
            nachnameTextF.setText(rs.getString("Nachname"));
            adresseTextF.setText(rs.getString("Adresse"));
            handyTextF.setText(rs.getString("lehrerHandy"));
            fachgebietTextF.setText(rs.getString("Fachgebiet"));

            abkurzungChoicebox.setValue(rs.getObject("UnterrichtID"));
            emailTextF.setText(rs.getString("email"));
            geschlechtChoiceBox.setValue(rs.getObject("geschlect"));
            geburtsDatumPick.setValue(rs.getDate("geburtsDatum").toLocalDate());
            rs.close();
        }



    }
    @FXML protected void loschenButtonClicked() throws SQLException{

        a.setContentText("Die Daten von Lehrer ist zu löschen. Sind sie Sicher?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK){

            prMethods.deleteTeacher(lehrerIDTextF.getText());
            infoTextArea.setText("Lehrer wurde gelöscht.");
            lehrerIDTextF.setText(null);
            passwordTextF.setText(null);
            ausweisIDTextF.setText(null);
            nameTextF.setText(null);
            nachnameTextF.setText(null);
            adresseTextF.setText(null);
            handyTextF.setText(null);
            fachgebietTextF.setText(null);
            abkurzungChoicebox.setValue(null);
            kapazitatTextF.setText(null);
            emailTextF.setText(null);
            geschlechtChoiceBox.setValue(null);
            geburtsDatumPick.setValue(null);
        }

    }
    @FXML protected void aktualisierenButtonClicked() throws SQLException{


        /*Student aktualisierteStudent = new Student(lehrerIDTextF.getText(), passwordTextF.getText(), ausweisIDTextF.getText(), nameTextF.getText(),
                nachnameTextF.getText(), emailTextF.getText(), handyTextF.getText(), elternNameTextF.getText(), elternHandyTextF.getText(), elternartTextF.getText(),
                java.sql.Date.valueOf(geburtsDatumPick.getValue()), adresseTextF.getText(),geschlectlistenChoicebox.getValue().toString(),null );

        prMethods.updateStudent(aktualisierteStudent);*/




    }
}
