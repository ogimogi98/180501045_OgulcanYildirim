package com.company.maindemo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class adminPageController {


    @FXML
    Button studentButton;
    @FXML
    Button lehrerButton;
    @FXML
    Button unterrichtButton;

    @FXML
    protected void studentClicked() throws IOException {

        prMethods.changeScene("adminStudentPage.fxml",studentButton);

    }
    @FXML
    protected void lehrerButtonClicked() throws IOException {


        prMethods.changeScene("adminTeacherPage.fxml",lehrerButton);

    }
    @FXML
    protected void unterrichtButtonClicked() throws IOException{

        prMethods.changeScene("adminUnterrichtPage.fxml",unterrichtButton);


    }
    @FXML
    Button outLogo;
    @FXML
    protected void logoutLogoClicked() throws IOException {

        System.out.println("logo Clicked");

        prMethods.changeScene("hello-view.fxml",outLogo);

    }

}
