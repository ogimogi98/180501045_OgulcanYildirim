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

        if (anmeldeArt == 3){

            PreparedStatement stmt = prMethods.connection.prepareStatement("SELECT * FROM unterricht ORDER by unterrichtID desc");

            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {



                String rsID = rs.getString("unterrichtID");
                String ıd = rsID.substring(1);
                int intid = Integer.valueOf(ıd) + 1;
                String sonID = "U" + String.valueOf(intid);
                System.out.println(sonID);



                rs.close();

                return sonID;
            }

            return ("Die Unterricht ID ist nicht zu erstellen.");}
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

    public static void insertLehrer(
            String lehrerID, String password, String TC, String Name,String Nachname, String geschlect ,String Adresse, String geburtsDatum, String lehrerHandy, String Fachgebiet, String UnterrichtID,String email
    ) throws SQLException {


        String insertData = "INSERT INTO unterricht (`LehrerID`,`password`,`TC`,`Name`,`Nachname`,`geschlect`,`Adresse`,`geburtsDatum`,`lehrerHandy`,`Fachgebiet`,`UnterrichtID`,`email`)"+"VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = prMethods.connection.prepareStatement(insertData);
        pstmt.setString(1,lehrerID);
        pstmt.setString(2,password);
        pstmt.setString(3,TC);
        pstmt.setString(4,Name);
        pstmt.setString(5,Nachname);
        pstmt.setString(6,geschlect);
        pstmt.setString(7,Adresse);
        pstmt.setString(8,geburtsDatum);
        pstmt.setString(9,lehrerHandy);
        pstmt.setString(10,Fachgebiet);
        pstmt.setString(11,UnterrichtID);
        pstmt.setString(12,email);
        pstmt.executeUpdate();


    }

    public static void insertUnterricht(
            String unterrichtID, String unterrichtName, String ects, String modulHandbuch) throws SQLException {


        String insertData = "INSERT INTO unterricht (`unterrichtID`,`fachgebiet`,`Unterricht_info`,`modulhandbuch`)"+"VALUES(?,?,?,?)";
        PreparedStatement pstmt = prMethods.connection.prepareStatement(insertData);
        pstmt.setString(1,unterrichtID);
        pstmt.setString(2,unterrichtName);
        pstmt.setString(3,ects);
        pstmt.setString(4,modulHandbuch);


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

    public static void updateStudent(Student student) throws SQLException {

        String query = "UPDATE student SET `studentID` = ? ,`password` = ?,`TC` = ?, `Name` = ? ,`Nachname` = ?, `geschlect` = ?, `studentHandy` = ?,`email` = ?, `Adresse` = ?,`geburtsDatum` = ?," +
                "`elternName` = ?,`elternHandy` = ?,`elternTyp` = ?  " +
                "WHERE `studentID` = ?";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.setString(1,student.getStudentID());
        preparedStatement.setString(2,student.getPassword());
        preparedStatement.setString(3,student.getTC());
        preparedStatement.setString(4,student.getName());
        preparedStatement.setString(5,student.getNachName());
        preparedStatement.setString(6,student.getGeschlect());
        preparedStatement.setString(7,student.getStudentHandy());
        preparedStatement.setString(8,student.getEmail());
        preparedStatement.setString(9,student.getAdresse());
        String date = student.getGeburtsDatum().toString();
        preparedStatement.setString(10,date);
        preparedStatement.setString(11,student.getElternName());
        preparedStatement.setString(12,student.getElternTyp());
        preparedStatement.setString(13,student.getElternHandy());
        preparedStatement.setString(14,student.getStudentID());
        String query2 = preparedStatement.toString();
        System.out.println(query);
        System.out.println(query2);
        preparedStatement.executeUpdate();
        System.out.println("ÖĞRENCİ GÜNCELLENDİ.");




    }
    public static void updateLehrer (Lehrer lehrer) throws SQLException {

        String query = "UPDATE lehrer SET `LehrerID` = ? ,`password` = ?,`TC` = ?, `Name` = ? ,`Nachname` = ?, " +
                "`geschlect` = ?, `lehrerHandy` = ?,`email` = ?, `Adresse` = ?,`geburtsDatum` = ?," +
                "`UnterrichtID` = ?,`Fachgebiet` = ?  WHERE `LehrerID` = ?";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.setString(1,lehrer.getLehrerID());
        preparedStatement.setString(2,lehrer.getLehrerpassword());
        preparedStatement.setString(3,lehrer.getTC());
        preparedStatement.setString(4,lehrer.getLehrerName());
        preparedStatement.setString(5,lehrer.getLehrernachName());
        preparedStatement.setString(6,lehrer.getGeschlect());
        preparedStatement.setString(7,lehrer.getLehrerHandy());
        preparedStatement.setString(8,lehrer.getLehrerEmail());
        preparedStatement.setString(9,lehrer.getAdresse());
        String date = lehrer.getGeburtsDatum().toString();
        preparedStatement.setString(10,date);
        preparedStatement.setString(11,lehrer.getUnterrichtID());
        preparedStatement.setString(12,lehrer.getFachGebiet());
        preparedStatement.setString(13,lehrer.getLehrerID());
        String query2 = preparedStatement.toString();
        System.out.println(query);
        System.out.println(query2);
        preparedStatement.executeUpdate();
        System.out.println("ÖĞRETMEN GÜNCELLENDİ.");




    }
    public static void updateUnterricht (Unterricht unterricht) throws SQLException {

        String query = "UPDATE unterricht SET `unterrichtID` = ? ,`fachgebiet`=?,`Unterricht_info`=?,`modulhandbuch` = ?,WHERE `unterrichtID` = ?";
        PreparedStatement pstmt = prMethods.connection.prepareStatement(query);
        pstmt.setString(1,unterricht.getUnterrichtID());
        pstmt.setString(2,unterricht.getUnterrichtName());
        pstmt.setString(3,unterricht.getUnterrichtInfo());
        pstmt.setString(4,unterricht.getModul());
        pstmt.setString(5,unterricht.getUnterrichtID());
        String query2 = pstmt.toString();
        System.out.println(query);
        System.out.println(query2);
        pstmt.executeUpdate();
        System.out.println("ÖĞRETMEN GÜNCELLENDİ.");




    }
    public static ResultSet findStudent(String username) throws SQLException{

    PreparedStatement preparedStatement = prMethods.connection.prepareStatement("select * from student where studentID = ?");
    preparedStatement.setString(1,username);
    ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }
    public static ResultSet findTeacher(String lehrerID)throws SQLException{

        String query = "Select *  from lehrer where lehrerID= '"+lehrerID+"'";
                System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        return rs;
    }

    public static ResultSet findUnterricht(String unterrichtID)throws SQLException{

        String query = "Select *  from unterricht where unterrichtID= '"+unterrichtID+"'";
        System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        return rs;
    }


    public static void deleteStudent(String studentID) throws SQLException {
        String query = "DELETE FROM student WHERE  studentID = '"+studentID+"'";
        System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.executeUpdate();

    }
    public static void deleteTeacher(String teacherID) throws SQLException {
        String query = "DELETE FROM lehrer WHERE  lehrerID = '"+teacherID+"'";
        System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.executeUpdate();

    }

    public static void deleteUnterricht(String unterrichtID) throws SQLException {
        String query = "DELETE FROM unterricht WHERE  unterrichtID = '"+unterrichtID+"'";
        System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.executeUpdate();

    }

    public static ResultSet listTeacher(String fachgebiet) throws SQLException{

        String query = "Select LehrerID, TC, Name, Nachname, email, lehrerHandy, UnterrichtID, Fachgebiet   from lehrer"+fachgebiet+" ORDER BY LehrerID DESC";
        System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        return  rs;

    }

    public static ResultSet listStudent (String fachgebiet) throws SQLException{

        String query = "Select *  from anmelden_unterricht"+fachgebiet+" ORDER BY studentID DESC";
        System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        return  rs;

    }
    public static  ResultSet selectUnterricht()throws SQLException{
        String query = "Select * from unterricht";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }
    public static  ResultSet selectUnterrichtWhere(String abkurzung) throws SQLException{

        String query = "Select * from unterricht where unterrichtID = '"+abkurzung+"'";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    public static  ResultSet listUnterricht() throws SQLException {

        String query = "Select unterricht.unterrichtID,unterricht.Fachgebiet ,Unterricht_info ," +
                "lehrer.lehrerID ,lehrer.Name , count(anmelden_unterricht.studentID) as counter " +
                "From unterricht " +
                "left join anmelden_unterricht on unterricht.unterrichtID=anmelden_unterricht.unterrichtID " +
                "left join lehrer on unterricht.unterrichtID = lehrer.UnterrichtID Group BY unterricht.unterrichtID";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static ResultSet listFreiUnterrichten(String studentID)throws SQLException{
        String query = "SELECT unterricht.UnterrichtID,unterricht.fachgebiet,lehrer.lehrerID," +
                "unterricht.Unterricht_info as ects,CONCAT(lehrer.Name,CONCAT(' ',lehrer.Nachname)) as HocaIsimSoyisim " +
                "FROM unterricht left join lehrer on unterricht.UnterrichtID=lehrer.UnterrichtID WHERE unterricht.UnterrichtID  " +
                "NOT IN ( SELECT unterrichtID FROM anmelden_unterricht WHERE studentID = ?)";

        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.setString(1,studentID);
        System.out.println(preparedStatement.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

          }
    public static ResultSet listanmeldeteUnterrichten(String studentID)throws SQLException{
        String query = "SELECT unterricht.UnterrichtID,unterricht.fachgebiet," +
                "unterricht.Unterricht_info as ects,lehrer.lehrerID,CONCAT(lehrer.Name,CONCAT(' ',lehrer.Nachname)) AS HocaIsimSoyisim " +
                "FROM anmelden_unterricht " +
                "left join lehrer on lehrer.lehrerID=anmelden_unterricht.lehrerID " +
                "left join unterricht on anmelden_unterricht.unterrichtID = unterricht.unterrichtID " +
                "WHERE studentID = ?";

        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.setString(1,studentID);
        System.out.println(preparedStatement.toString());
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;

    }

    public static void anmeldenUnterricht(String studentID, String lehrerID, String UID) throws SQLException{

        String query = "INSERT INTO anmelden_unterricht " +
                " (`unterrichtID`,`lehrerID`,`studentID`) " +
                " VALUES" +
                " (?,?,?); ";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.setString(1,UID);
        preparedStatement.setString(2,lehrerID);
        preparedStatement.setString(3,studentID);

        System.out.println(preparedStatement.toString());
        preparedStatement.executeUpdate();


    }
    public static void anmeldenUpdateUnterricht(String studentID, String lehrerID, String UID) throws SQLException{

        String query = " UPDATE anmelden_unterricht as A " +
                " Left Outer JOIN student as B on A.studentID = B.studentID " +
                " Left Outer JOIN lehrer as C on A.lehrerID = C.LehrerID " +
                " Left Outer JOIN unterricht D on A.unterrichtID = D.unterrichtID " +
                " SET a.`unterrichtName` = D.fachgebiet, a.`studentName` = B.Name, " +
                " a.`studentNachname` = B.Nachname, a.`lehrerName` = C.Name,a.`lehrerNachname` = C.Nachname" +
                " WHERE a.`unterrichtID` = ? AND a.`lehrerID` = ? AND a.`studentID` = ?;";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.setString(1,UID);
        preparedStatement.setString(2,lehrerID);
        preparedStatement.setString(3,studentID);

        System.out.println(preparedStatement.toString());
        preparedStatement.executeUpdate();
    }

    public static void abmeldenUnterricht(String studentID,String UID) throws SQLException{
        String query = "DELETE FROM anmelden_unterricht WHERE unterrichtID = ? and studentID = ?";
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.setString(1,UID);

        preparedStatement.setString(2,studentID);

        System.out.println(preparedStatement.toString());
        preparedStatement.executeUpdate();
    }
}








