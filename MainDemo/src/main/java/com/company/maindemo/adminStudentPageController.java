package com.company.maindemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class adminStudentPageController {

    @FXML TableView<Student>  studentTableview;

    @FXML ChoiceBox geschlectlistenChoicebox;
    @FXML CheckBox elternCheckBox;


    @FXML
    TextField ausweisIDTextF;
    @FXML
    TextField nameTextF;
    @FXML
    TextField nachnameTextF;
    @FXML
    TextField adresseTextF;
    @FXML
    TextField emailTextF;
    @FXML
    TextField studentIDTextF;
    @FXML
    TextField passwordTextF;
    @FXML
    TextField handyTextF;
    @FXML
    ChoiceBox geschlechtChoiceBox;
    @FXML
    DatePicker geburtsDatumPick;
    @FXML
    TextField elternNameTextF;
    @FXML
    TextField elternHandyTextF;
    @FXML
    TextField elternartTextF;
    @FXML
    Button hinzufugenButton;
    @FXML
    Button anmeldeButton;
    @FXML
    Button generateButton;
    @FXML
    AnchorPane hinzufugenAnchorPane;
    @FXML SplitPane auflistenSplitPane;
    @FXML TableColumn<Student,String> col_stdID;
    @FXML TableColumn<Student,String> col_TC;
    @FXML TableColumn<Student,String> col_name;
    @FXML TableColumn<Student,String> col_nachname;
    @FXML TableColumn<Student,String> col_email;
    @FXML TableColumn<Student,String> col_handy;
    @FXML TableColumn<Student,String> col_elternname;
    @FXML TableColumn<Student,String> col_elternhandy;
    @FXML TableColumn<Student,String> col_elternınfo;
    @FXML TextArea infoTextArea;

    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    ObservableList<String> geschlectArten = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity");

    ObservableList<String> geschlectArten2 = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity","Alles");
    ObservableList<Student> oblist = FXCollections.observableArrayList();

    @FXML
    protected void hinzufugenClicked() throws Exception{

        geschlechtChoiceBox.setItems(geschlectArten);
        auflistenSplitPane.setVisible(false);

        if(hinzufugenAnchorPane.isVisible()==false) {

        hinzufugenAnchorPane.setVisible(true);
            ausweisIDTextF.setText(null);
            nameTextF.setText(null);
            nachnameTextF.setText(null);
            geburtsDatumPick.setValue(LocalDate.now());
            geschlechtChoiceBox.setValue(null);
            adresseTextF.setText(null);
            emailTextF.setText(null);
            studentIDTextF.setText(null);
            passwordTextF.setText(null);
            handyTextF.setText(null);
            elternartTextF.setText(null);
            elternHandyTextF.setText(null);
            elternNameTextF.setText(null);
            studentIDTextF.setPromptText("Klicken Sie zum Generate oder Schreiben ID zu Suchen.");
            passwordTextF.setPromptText("Bitte Klicken Sie Generate.");


        }
        else {


            a.setContentText("Die Daten von Student ist zu verloren. Sind sie Sicher?");
            a.showAndWait();

            if (a.getResult() == ButtonType.OK){

                ausweisIDTextF.setText(null);
                nameTextF.setText(null);
                nachnameTextF.setText(null);
                geburtsDatumPick.setValue(LocalDate.now());
                geschlechtChoiceBox.setValue(null);
                adresseTextF.setText(null);
                emailTextF.setText(null);
                studentIDTextF.setText(null);
                passwordTextF.setText(null);
                handyTextF.setText(null);
                elternartTextF.setText(null);
                elternHandyTextF.setText(null);
                elternNameTextF.setText(null);
                studentIDTextF.setPromptText("Bitte Klicken Sie Generate.");
                passwordTextF.setPromptText("Bitte Klicken Sie Generate.");

            }

        }
        }

        @FXML protected void generateButtonClicked() throws Exception{

            passwordTextF.setText(prMethods.generatePassword(8).toString());
            studentIDTextF.setText(prMethods.generateID(1));
            infoTextArea.setText("Student ID & Password wird generiert.");

        }

        @FXML protected void anmeldeButtonClicked() throws SQLException {

            if (prMethods.kontrolleID(ausweisIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), "student", "TC")) {
                if (prMethods.kontrolleID(studentIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), "student", "studentID")) {
                    String date = String.valueOf(geburtsDatumPick.getValue());
                    prMethods.insertStudent(studentIDTextF.getText(), passwordTextF.getText(), ausweisIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), geschlechtChoiceBox.getValue().toString(), adresseTextF.getText(),
                        date, handyTextF.getText(), elternNameTextF.getText(), elternHandyTextF.getText(), elternartTextF.getText(), emailTextF.getText());
                    infoTextArea.setText(nameTextF.getText() +"mit Student ID"+studentIDTextF.getText() + "wird angemeldet.");
                    }
                }

            }

        @FXML protected void auflistenButtonClicked(){

            geschlectlistenChoicebox.setItems(geschlectArten2);
            hinzufugenAnchorPane.setVisible(false);


            if(auflistenSplitPane.isVisible()==false) {

                auflistenSplitPane.setVisible(true);
                geschlectlistenChoicebox.setValue("Alles");


            }
            else {


            geschlectlistenChoicebox.setValue("null");
            elternCheckBox.setSelected(false);


            }

        }


        @FXML protected void listenButtonClicked() throws SQLException {

                oblist.clear();
                String geschlect = geschlectlistenChoicebox.getValue().toString();

                System.out.println(geschlect);
                if (geschlect == "Alles"){
                   geschlect = "";
                   System.out.println(geschlect + " seçilmedi");
                } else {
                    String cinsiyet;
                    cinsiyet = geschlectlistenChoicebox.getValue().toString();
                    geschlect = " where geschlect = "+"'"+cinsiyet+"'";
                    System.out.println(geschlect + " seçildi");
                }

                String query = "Select studentID, TC, Name, Nachname, email, studentHandy, elternName, elternHandy  from student"+geschlect+" ORDER BY studentID DESC";
                System.out.println(query);
                PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {


                    oblist.add(new Student(rs.getString("studentID"),null ,rs.getString("TC"), rs.getString("Name"),
                            rs.getString("Nachname"), rs.getString("email"), rs.getString("studentHandy"),
                            rs.getString("elternName"),rs.getString("elternHandy"),null,null,null,null,null));


                }


            col_stdID.setCellValueFactory(new PropertyValueFactory<Student,String>("studentID"));
            col_TC.setCellValueFactory(new PropertyValueFactory<Student,String>("TC"));
            col_name.setCellValueFactory(new PropertyValueFactory<Student,String>("name"));
            col_nachname.setCellValueFactory(new PropertyValueFactory<Student,String>("nachName"));
            col_email.setCellValueFactory(new PropertyValueFactory<Student,String>("email"));
            col_handy.setCellValueFactory(new PropertyValueFactory<Student,String>("studentHandy"));
            col_elternname.setCellValueFactory(new PropertyValueFactory<Student,String>("elternName"));
            col_elternhandy.setCellValueFactory(new PropertyValueFactory<Student,String>("elternHandy"));

            if (elternCheckBox.isSelected()){

                col_elternınfo.setVisible(true);
                studentTableview.setItems(oblist);
                infoTextArea.setText("Anzal der Studenten ist " + oblist.size()+".\n"+ "Studenten wird gelistet.");

            }
            else {

                col_elternınfo.setVisible(false);
                studentTableview.setItems(oblist);
                infoTextArea.setText("Anzal der Studenten ist " + oblist.size()+".\n"+ "Studenten wird gelistet.");


            }




    }

    @FXML protected void sucheButtonClicked() throws SQLException{

        String query = "Select *  from student where studentID= '"+studentIDTextF.getText()+"'";
        System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();



            if (!rs.isBeforeFirst()){

            infoTextArea.setText("Studenten wird nicht gefunden.");


            }

            while (rs.next()){

                infoTextArea.setText("Studenten wird gefunden.");

                studentIDTextF.setText(rs.getString("studentID"));
                passwordTextF.setText(rs.getString("password"));
                ausweisIDTextF.setText(rs.getString("TC"));
                nameTextF.setText(rs.getString("Name"));
                nachnameTextF.setText(rs.getString("Nachname"));
                adresseTextF.setText(rs.getString("Adresse"));
                handyTextF.setText(rs.getString("studentHandy"));
                elternNameTextF.setText(rs.getString("elternName"));
                elternHandyTextF.setText(rs.getString("elternHandy"));
                elternartTextF.setText(rs.getString("elternTyp"));
                emailTextF.setText(rs.getString("email"));
                geschlechtChoiceBox.setValue(rs.getObject("geschlect"));
                geburtsDatumPick.setValue(rs.getDate("geburtsDatum").toLocalDate());
                System.out.println(geschlectlistenChoicebox.getValue().toString());
                System.out.println(geschlectlistenChoicebox.getValue());

                rs.close();
            }



    }
    @FXML protected void loschenButtonClicked() throws SQLException{

        a.setContentText("Die Daten von Student ist zu löschen. Sind sie Sicher?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK){

        String query = "DELETE FROM student WHERE  studentID = '"+studentIDTextF.getText()+"'";
        System.out.println(query);
        PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
        preparedStatement.executeUpdate();
            infoTextArea.setText("Studenten wurde gelöscht.");
            studentIDTextF.setText(null);
            passwordTextF.setText(null);
            ausweisIDTextF.setText(null);
            nameTextF.setText(null);
            nachnameTextF.setText(null);
            adresseTextF.setText(null);
            handyTextF.setText(null);
            elternNameTextF.setText(null);
            elternHandyTextF.setText(null);
            elternartTextF.setText(null);
            emailTextF.setText(null);
            geschlechtChoiceBox.setValue(null);
            geburtsDatumPick.setValue(null);
        }

        }
    @FXML protected void aktualisierenButtonClicked() throws SQLException{


        Student aktualisierteStudent = new Student(studentIDTextF.getText(), passwordTextF.getText(), ausweisIDTextF.getText(), nameTextF.getText(),
                nachnameTextF.getText(), emailTextF.getText(), handyTextF.getText(), elternNameTextF.getText(), elternHandyTextF.getText(), elternartTextF.getText(),
                java.sql.Date.valueOf(geburtsDatumPick.getValue()), adresseTextF.getText(),geschlechtChoiceBox.getValue().toString(),null );

        prMethods.updateStudent(aktualisierteStudent);
        infoTextArea.setText("Studenten wird aktualisiert.");

    }

        }









