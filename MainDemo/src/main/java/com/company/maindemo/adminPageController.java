package com.company.maindemo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class adminPageController {

    @FXML
    Button outLogo;
    @FXML
    Button studentButton;
    @FXML
    Button lehrerButton;

    @FXML
    protected void studentClicked() throws IOException {

        prMethods.changeScene("adminStudentPage.fxml",studentButton);

    }
    @FXML
    protected void lehrerButtonClicked() throws IOException {


        prMethods.changeScene("adminTeacherPage.fxml",lehrerButton);

    }
    protected void unterrichtClicked(){




    }
    @FXML
    protected void logoutLogoClicked() throws IOException {

        System.out.println("logo Clicked");

        prMethods.changeScene("hello-view.fxml",outLogo);

    }

}
