package com.company.maindemo;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;


public class HelloController{
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet= null;
    String retrievetUser;
    String retrievedPass;
    public int x = 3;
    @FXML
    TextField ıdText;
    @FXML
    TextField passText;

    @FXML
    Button loginButton;

    @FXML
    protected void teacherButtonClicked(){
        ıdText.setText("");
        ıdText.setPromptText("Teacher ID");
        x=2;
    }

    @FXML
    protected void studentButtonClicked(){
        ıdText.setText("");
        ıdText.setPromptText("Student ID");
        x=1;

    }

    @FXML
    protected void adminButtonClicked(){

        ıdText.setText("admin");
        x=0;



    }

    @FXML
    protected void loginButtonClicked() throws Exception{

        if(x==0){

            System.out.println(x);

            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/180501045_ogulcan_yildirim","root", "Ogi.1234");
                preparedStatement = connection.prepareStatement("select username, password from admin where username = ?");
                preparedStatement.setString(1,ıdText.getText());
                resultSet = preparedStatement.executeQuery();

                if (ıdText.getText().isBlank()){

                    prMethods.giveAlert(Alert.AlertType.WARNING,"Bitte Schreiben Sie Benutzername !");
                    x=2;

                }



                while (resultSet.next()){

                    retrievetUser = resultSet.getString("username");
                    retrievedPass = resultSet.getString("password");
                    if (retrievetUser.equals(ıdText.getText()) && retrievedPass.equals(passText.getText()) ){

                        prMethods.changeScene("adminPage.fxml",loginButton);

                }
                    else   {

                        prMethods.giveAlert(Alert.AlertType.WARNING,"Bitte Kontrollieren Sie Benutzername oder Password !");
                        x=0;

                    }
                }

            } catch (SQLException e){

                e.printStackTrace();
            }


            x = 3;
        }
        else if (x==1) {

            try{

                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/180501045_ogulcan_yildirim","root", "Ogi.1234");
                preparedStatement = connection.prepareStatement("select studentID, password from student where studentID = ?");
                preparedStatement.setString(1,ıdText.getText());
                resultSet = preparedStatement.executeQuery();

                if (ıdText.getText().isBlank()){

                    prMethods.giveAlert(Alert.AlertType.WARNING,"Bitte Schreiben Sie Benutzername !");
                    x=2;

                }

                while (resultSet.next()){
                    retrievetUser = resultSet.getString("studentID");
                    retrievedPass = resultSet.getString("password");

                    if (retrievetUser.equals(ıdText.getText()) && retrievedPass.equals(passText.getText()) ){
                        prMethods.changeScene("studentPage.fxml",loginButton);

                    }
                    else   {

                        prMethods.giveAlert(Alert.AlertType.WARNING,"Bitte Kontrollieren Sie Benutzername oder Password !");
                        x=1;

                    }
                }

            } catch (SQLException e){

                e.printStackTrace();
            }

            x = 3;


        }

        else if (x==2) {

            try{

                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/180501045_ogulcan_yildirim","root", "Ogi.1234");
                preparedStatement = connection.prepareStatement("select LehrerID , password from lehrer where LehrerID = ?");
                preparedStatement.setString(1,ıdText.getText());

                if (ıdText.getText().isBlank()){

                    prMethods.giveAlert(Alert.AlertType.WARNING,"Bitte Schreiben Sie Benutzername !");
                    x=2;

                }

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()){

                    retrievetUser = resultSet.getString("LehrerID");
                    retrievedPass = resultSet.getString("password");

                    if (retrievetUser.equals(ıdText.getText()) && retrievedPass.equals(passText.getText()) ){

                        prMethods.changeScene("teacherPage.fxml",loginButton);

                    }
                    else   {

                        prMethods.giveAlert(Alert.AlertType.WARNING,"Bitte Kontrollieren Sie Benutzername oder Password !");
                        x=2;

                    }
                }

            } catch (SQLException e){

                e.printStackTrace();
            }

            x = 3;
        }
        else {


            prMethods.giveAlert(Alert.AlertType.ERROR,"Bitte wählen Sie Benutzertyp !");

        }


    }

}
