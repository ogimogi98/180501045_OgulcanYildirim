package com.company.maindemo;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;



public class HelloController{

    @FXML
    TextField ıdText;

    @FXML
    protected void teacherButtonClicked(){
        ıdText.setText("");
        ıdText.setPromptText("Teacher ID");
    }

    @FXML
    protected void studentButtonClicked(){
        ıdText.setText("");
        ıdText.setPromptText("Student ID");
    }

    @FXML
    protected void adminButtonClicked(){
        ıdText.setText("admin");
    }


}
