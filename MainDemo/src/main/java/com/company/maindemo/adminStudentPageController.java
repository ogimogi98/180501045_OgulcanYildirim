package com.company.maindemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;

public class adminStudentPageController {

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

    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    ObservableList<String> geschlectArten = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity");



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


        passwordTextF.setText(prMethods.generatePassword().toString());




        }



    }






