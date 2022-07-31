package com.company.maindemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class teacherController implements Initializable {

    @FXML
    AnchorPane studentinfoAnchorPane;
    @FXML
    AnchorPane studentHausaufgabeAnchorPane;
    @FXML
    AnchorPane studentAnmeldenAnchorPane;

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
    TextField kapazitatTextF;
    @FXML
    ChoiceBox abkurzungChoicebox;
    @FXML
    Text stdID;
    ObservableList<String> geschlectArten = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity");
    @FXML
    TextArea infoTextArea;
    @FXML Button aktualisierenButton;
    public void helloMessage(String username){

        System.out.println(username);
        infoTextArea.setText("Willkommen " + username);
        stdID.setText(username);

    }
    ObservableList<String> unterrichtabkurzung = FXCollections.observableArrayList();
    public void fillCombo(){

        try {
            ResultSet rs = prMethods.selectUnterricht();
            while (rs.next()){
                unterrichtabkurzung.add(rs.getString(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML protected void InfoButtonClicked() throws SQLException{
        studentAnmeldenAnchorPane.setVisible(false);
        studentHausaufgabeAnchorPane.setVisible(false);
        studentinfoAnchorPane.setVisible(true);
        geschlechtChoiceBox.setItems(geschlectArten);
        unterrichtabkurzung.clear();
        System.out.println("Önce: "+unterrichtabkurzung.size());
        fillCombo();
        System.out.println("Sonra: "+unterrichtabkurzung.size());
        abkurzungChoicebox.setItems(unterrichtabkurzung);

        geschlechtChoiceBox.setItems(geschlectArten);

        ResultSet rs = prMethods.findTeacher(stdID.getText());
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
            geschlechtChoiceBox.setValue(rs.getObject("geschlect"));
            emailTextF.setText(rs.getString("email"));
            fachgebietTextF.setText(rs.getString("Fachgebiet"));
            geburtsDatumPick.setValue(rs.getDate("geburtsDatum").toLocalDate());
            abkurzungChoicebox.getSelectionModel().select(rs.getObject("UnterrichtID"));




            rs.close();
        }
    }


        @FXML protected void aktualisierenButtonClicked() throws SQLException {



        Lehrer aktualisierteLehrer = new Lehrer(lehrerIDTextF.getText(), passwordTextF.getText(), ausweisIDTextF.getText(), nameTextF.getText(),
                nachnameTextF.getText(),geschlechtChoiceBox.getValue().toString(), adresseTextF.getText(),java.sql.Date.valueOf(geburtsDatumPick.getValue()),
                handyTextF.getText(), fachgebietTextF.getText(),abkurzungChoicebox.getValue().toString(), emailTextF.getText());

        prMethods.updateLehrer(aktualisierteLehrer);

        infoTextArea.setText("Lehrer Aktualisiert.");
        System.out.println("lehrer Aktualisiert");

    }
    @FXML protected void HausaufgabeButtonClicked() throws SQLException{

        studentAnmeldenAnchorPane.setVisible(false);
        studentHausaufgabeAnchorPane.setVisible(true);
        studentinfoAnchorPane.setVisible(false);

    }

    @FXML protected void StudListButtonClicked() throws SQLException{
        studentAnmeldenAnchorPane.setVisible(true);
        studentHausaufgabeAnchorPane.setVisible(false);
        studentinfoAnchorPane.setVisible(false);
    }

    @FXML protected void aufgabenSucheButtonClicked() throws SQLException{


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        abkurzungChoicebox.setOnAction(Action -> {

            String abkurzung = abkurzungChoicebox.getSelectionModel().getSelectedItem().toString();
            try {
                ResultSet rs = prMethods.selectUnterrichtWhere(abkurzung);
                while (rs.next()){

                    fachgebietTextF.setText(rs.getString("fachgebiet"));
                    kapazitatTextF.setText(rs.getString("Unterricht_info"));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        });
    }
    @FXML
    Button outLogo;
    @FXML
    protected void logoutLogoClicked() throws IOException {

        System.out.println("logo Clicked");

        prMethods.changeScene("hello-view.fxml",outLogo);

    }
}





