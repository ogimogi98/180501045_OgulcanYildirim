package com.company.maindemo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class HelloController{

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



                preparedStatement = prMethods.connection.prepareStatement("select username, password from admin where username = ?");
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




            x = 3;}

        else if (x==1) {




                preparedStatement = prMethods.connection.prepareStatement("select studentID, password from student where studentID = ?");
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

                        String username = ıdText.getText();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("studentPage.fxml"));
                        Parent root = loader.load();
                        studentController StudentController = loader.getController();
                        StudentController.helloMessage(username);

                        Stage window = (Stage) loginButton.getScene().getWindow();
                        Scene scene = new Scene(root);
                        window.setScene(scene);
                        window.show();



                    }
                    else   {

                        prMethods.giveAlert(Alert.AlertType.WARNING,"Bitte Kontrollieren Sie Benutzername oder Password !");
                        x=1;

                    }
                }



            x = 3;


        }

        else if (x==2) {


                preparedStatement = prMethods.connection.prepareStatement("select LehrerID , password from lehrer where LehrerID = ?");
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

                        String username = ıdText.getText();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("teacherPage.fxml"));
                        Parent root = loader.load();
                        teacherController teacherController = loader.getController();
                        teacherController.helloMessage(username);

                        Stage window = (Stage) loginButton.getScene().getWindow();
                        Scene scene = new Scene(root);
                        window.setScene(scene);
                        window.show();

                    }
                    else   {

                        prMethods.giveAlert(Alert.AlertType.WARNING,"Bitte Kontrollieren Sie Benutzername oder Password !");
                        x=2;

                    }
                }

            x = 3;
        }
        else {


            prMethods.giveAlert(Alert.AlertType.ERROR,"Bitte wählen Sie Benutzertyp !");

        }


    }

}
