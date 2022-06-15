package com.company.maindemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class adminStudentPageController {

    @FXML TableView studentTableview;
    @FXML ChoiceBox sortingChoiceBox;
    @FXML ChoiceBox geschlectlistenChoicebox;
    @FXML CheckBox elternCheckBox;
    @FXML CheckBox adresseCheckBox;
    @FXML CheckBox unaktivCheckBox;

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
    TextField studentIDTextF;
    @FXML
    TextField passwordTextF;
    @FXML
    TextField handyTextF;
    @FXML
    ChoiceBox geschlechtChoiceBox;
    @FXML
    DatePicker geburtsDatumPick;
    @FXML
    TextField elternNameTextF;
    @FXML
    TextField elternHandyTextF;
    @FXML
    TextField elternartTextF;
    @FXML
    Button hinzufugenButton;
    @FXML
    Button anmeldeButton;
    @FXML
    Button generateButton;
    @FXML
    AnchorPane hinzufugenAnchorPane;
    @FXML SplitPane auflistenSplitPane;

    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    ObservableList<String> geschlectArten = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity");
    ObservableList<String> sortArten = FXCollections.observableArrayList("studentID DESC","studentID ASC","TC DESC","TC ASC");
    ObservableList<String> geschlectArten2 = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity","Alles");


    @FXML
    protected void hinzufugenClicked() throws Exception{

        geschlechtChoiceBox.setItems(geschlectArten);

        if(hinzufugenAnchorPane.isVisible()==false) {

        hinzufugenAnchorPane.setVisible(true);


        }
        else {


            a.setContentText("Die Daten von Student ist zu verloren. Sind sie Sicher?");
            a.showAndWait();

            if (a.getResult() == ButtonType.OK){

                ausweisIDTextF.setText(null);
                nameTextF.setText(null);
                nachnameTextF.setText(null);
                geburtsDatumPick.setValue(LocalDate.now());
                geschlechtChoiceBox.setValue(null);
                adresseTextF.setText(null);
                emailTextF.setText(null);
                studentIDTextF.setText(null);
                passwordTextF.setText(null);
                handyTextF.setText(null);
                elternartTextF.setText(null);
                elternHandyTextF.setText(null);
                elternNameTextF.setText(null);
                studentIDTextF.setPromptText("Bitte Klicken Sie Generate.");
                passwordTextF.setPromptText("Bitte Klicken Sie Generate.");

            }

        }
        }

        @FXML protected void generateButtonClicked() throws Exception{

            passwordTextF.setText(prMethods.generatePassword(8).toString());
            studentIDTextF.setText(prMethods.generateID(1));

        }

        @FXML protected void anmeldeButtonClicked() throws SQLException {

            if (prMethods.kontrolleID(ausweisIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), "student", "TC")) {
                if (prMethods.kontrolleID(studentIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), "student", "studentID")) {
                    String date = String.valueOf(geburtsDatumPick.getValue());
                    prMethods.insertStudent(studentIDTextF.getText(), passwordTextF.getText(), ausweisIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), geschlechtChoiceBox.getValue().toString(), adresseTextF.getText(),
                        date, handyTextF.getText(), elternNameTextF.getText(), elternHandyTextF.getText(), elternartTextF.getText(), emailTextF.getText());
                    }
                }

            }

        @FXML protected void auflistenButtonClicked(){

            geschlectlistenChoicebox.setItems(geschlectArten2);
            sortingChoiceBox.setItems(sortArten);

            if(auflistenSplitPane.isVisible()==false) {

                auflistenSplitPane.setVisible(true);
                geschlectlistenChoicebox.setValue("Alles");
                sortingChoiceBox.setValue("studentID DESC");

            }
            else {

            sortingChoiceBox.setValue("studentID DESC");
            geschlectlistenChoicebox.setValue("null");
            elternCheckBox.setSelected(false);
            adresseCheckBox.setSelected(false);
            unaktivCheckBox.setSelected(false);

            }

        }
        @FXML protected void listenButtonClicked() throws SQLException {
                String sort = " ORDER BY " + sortingChoiceBox.getValue().toString();
                String geschlect = geschlectlistenChoicebox.getValue().toString();
                System.out.println(sort);
                System.out.println(geschlect);
                if (geschlect == "Alles"){
                   geschlect = "";
                   System.out.println(geschlect + " seçilmedi");
                } else {
                    geschlect = " where " + geschlectlistenChoicebox.getValue().toString();
                    System.out.println(geschlect + " seçildi");
                }

                String query = "Select studentID, TC, Name, Nachname, from student"+geschlect+sort;
                System.out.println(query);
                PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

        }


    }






