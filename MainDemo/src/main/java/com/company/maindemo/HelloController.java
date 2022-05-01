package com.company.maindemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;


public class HelloController{

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
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet= null;

            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/180501045_ogulcan_yildirim","root", "Ogi.1234");
                preparedStatement = connection.prepareStatement("select username, password from admin where username = ?");
                preparedStatement.setString(1,ıdText.getText());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                   String retrievetUser = resultSet.getString("username");
                   String retrievedPass = resultSet.getString("password");
                    if (retrievetUser.equals(ıdText.getText()) && retrievedPass.equals(passText.getText()) ){
                    Parent root = FXMLLoader.load(getClass().getResource("adminPage.fxml"));
                    Stage window = (Stage) loginButton.getScene().getWindow();
                    window.setScene(new Scene(root,800,500));
                }
                    else   {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Bitte kontrollieren Sie Username oder Password!");
                        alert.show();

                    }
                }

            } catch (SQLException e){

                e.printStackTrace();
            }


            x = 3;
        }
        else if (x==1) {

            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet= null;

            try{
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/180501045_ogulcan_yildirim","root", "Ogi.1234");
                preparedStatement = connection.prepareStatement("select studentID , password from student where studentID = ?");
                preparedStatement.setString(1,ıdText.getText());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    String retrievetUser = resultSet.getString("studentID");
                    String retrievedPass = resultSet.getString("password");
                    if ( retrievetUser.equals(ıdText.getText()) &&retrievedPass.equals(passText.getText()) ){
                        Parent root = FXMLLoader.load(getClass().getResource("studentPage.fxml"));
                        Stage window = (Stage) loginButton.getScene().getWindow();
                        window.setScene(new Scene(root,800,500));
                    }
                    else   {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setContentText("Bitte kontrollieren Sie Username oder Password!");
                        alert.show();

                    }
                }

            } catch (SQLException e){

                e.printStackTrace();
            }

        }
        else if (x==2) {

            Parent root = FXMLLoader.load(getClass().getResource("teacherPage.fxml"));
            Stage window = (Stage) loginButton.getScene().getWindow();
            window.setScene(new Scene(root,800,500));
            x = 3;
        }
        else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Bitte wählen Sie Benutzertyp!");
            alert.show();

        }


    }

}
