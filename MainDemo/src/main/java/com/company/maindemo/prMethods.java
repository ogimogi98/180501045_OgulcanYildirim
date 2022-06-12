package com.company.maindemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class prMethods {

    public static void changeScene (String fxmlFile, Button event) throws IOException {

        Parent root = FXMLLoader.load(prMethods.class.getResource(fxmlFile));
        Stage window = (Stage) event.getScene().getWindow();
        window.setScene(new Scene(root));


    }

    public static void giveAlert(Alert.AlertType alertType, String alertContent){


        Alert alert = new Alert(alertType);
        alert.setContentText(alertContent);
        alert.show();

    }




}
