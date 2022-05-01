package com.company.maindemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class studentController {

    Button zurückButton;

    public void  zurückButtonClicked() throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("studentPage.fxml"));
        Stage window = (Stage) zurückButton.getScene().getWindow();
        window.setScene(new Scene(root,800,500));
    }
}
