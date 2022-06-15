package com.company.maindemo;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.Random;

public class prMethods {

    private static String studentID;
    private static String password;
    private static String tc;
    private static String name;
    private static String nachname;
    private static String geschlect;
    private static String adresse;
    private static Date geburtsDatum;
    private static String studentHandy;
    private static String elternName;
    private static String elternHandy;
    private static String elternTyp;
    private static String email;

    public static void changeScene (String fxmlFile, Button event) throws IOException {

        Parent root = FXMLLoader.load(prMethods.class.getResource(fxmlFile));
        Stage window = (Stage) event.getScene().getWindow();
        window.setScene(new Scene(root));


    }

    public  static void giveAlert(Alert.AlertType alertType, String alertContent){


            Alert alert = new Alert(alertType);
            alert.setContentText(alertContent);
            alert.show();

        }
    public static char[] generatePassword(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for(int i = 4; i< length ; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return password;
    }

    static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/180501045_ogulcan_yildirim", "root", "Ogi.1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateID(int anmeldeArt ) throws SQLException {

//Generating ID for Student
        if (anmeldeArt == 1){

        PreparedStatement stmt = prMethods.connection.prepareStatement("SELECT * FROM student ORDER by studentID desc");

        ResultSet rs = stmt.executeQuery();


         while (rs.next()) {

             String rsID = rs.getString("studentID");
             String ıd = rsID.substring(1);
             int intid = Integer.valueOf(ıd) + 1;
             String sonID = "S" + String.valueOf(intid);
             System.out.println(sonID);
             rs.close();

             return sonID;
         }


        return ("Die Student ID ist nicht zu erstellen.");

        }

//Generating ID for Teacher
        if (anmeldeArt == 2){

            PreparedStatement stmt = prMethods.connection.prepareStatement("SELECT * FROM lehrer ORDER by lehrerID desc");

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {



                String rsID = rs.getString("lehrerID");
                String ıd = rsID.substring(1);
                int intid = Integer.valueOf(ıd) + 1;
                String sonID = "L" + String.valueOf(intid);
                System.out.println(sonID);



                rs.close();

                return sonID;
            }

            return ("Die Lehrer ID ist nicht zu erstellen.");}

        return null;
    }

    public static void insertStudent(
            String studentID, String password, String TC, String Name,String Nachname, String geschlect ,String Adresse, String geburtsDatum, String studentHandy, String elternName, String elternHandy, String elternTyp,String email
    ) throws SQLException {


        String insertData = "INSERT INTO student (`studentID`,`password`,`TC`,`Name`,`Nachname`,`geschlect`,`Adresse`,`geburtsDatum`,`studentHandy`,`elternName`,`elternHandy`,`elternTyp`,`email`)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = prMethods.connection.prepareStatement(insertData);
        pstmt.setString(1,studentID);
        pstmt.setString(2,password);
        pstmt.setString(3,TC);
        pstmt.setString(4,Name);
        pstmt.setString(5,Nachname);
        pstmt.setString(6,geschlect);
        pstmt.setString(7,Adresse);
        pstmt.setString(8,geburtsDatum);
        pstmt.setString(9,studentHandy);
        pstmt.setString(10,elternName);
        pstmt.setString(11,elternHandy);
        pstmt.setString(12,elternTyp);
        pstmt.setString(13,email);
        pstmt.executeUpdate();


    }

    public static boolean kontrolleID(String ID, String Name, String Nachname, String Table, String IDart) throws SQLException{

        String SQL = "SELECT "+IDart+", Name, Nachname from "+Table+" where "+ IDart +" = ?";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(SQL);
        preparedStatement.setString(1,ID);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){

            String name = rs.getString("Name");
            String nachname = rs.getString("Nachname");
            String id = rs.getString(IDart);
            System.out.println(IDart);
            System.out.println(Name);
            System.out.println(Nachname);
            if (id.equals(ID)){
                System.out.println(id);
                System.out.println(ID);
                giveAlert(Alert.AlertType.ERROR,"Bitte Kontrollieren Sie "+ IDart);
                return false;
            }


        }

        return true;
    }

    }








