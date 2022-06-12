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
    protected void studentClicked() throws IOException {

        prMethods.changeScene("adminStudentPage.fxml",studentButton);

    }

    protected void lehrerClicked(){




    }
    protected void unterrichtClicked(){




    }
    @FXML
    protected void logoutLogoClicked() throws IOException {

        System.out.println("logo Clicked");

        prMethods.changeScene("hello-view.fxml",outLogo);

    }

}
