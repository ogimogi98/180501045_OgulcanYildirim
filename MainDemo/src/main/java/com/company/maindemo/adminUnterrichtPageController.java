package com.company.maindemo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminUnterrichtPageController /*implements Initializable*/ {

    @FXML
    TableView<querySetter> lehrerTableview;


    @FXML
    ChoiceBox fachChoicebox;
    @FXML
    CheckBox fachCheckBox;
    @FXML
    Button hinzufugenButton;
    @FXML
    Button anmeldeButton;
    @FXML
    Button generateButton;
    @FXML
    AnchorPane hinzufugenAnchorPane;
    @FXML SplitPane auflistenSplitPane;

    @FXML TableColumn<querySetter,String> col_UID;
    @FXML TableColumn<querySetter,String> col_UNAME;
    @FXML TableColumn<querySetter,String> col_ects;
    @FXML TableColumn<querySetter,String> col_lehrerID;
    @FXML TableColumn<querySetter,String> col_lehrername;
    @FXML TableColumn<querySetter,Integer> col_counter;

    @FXML TextArea infoTextArea;

    @FXML
    TextField UnterrichtIDTextF;
    @FXML
    TextField uNameTextF;
    @FXML
    TextField ectsTextF;
    @FXML
    TextField modulTextF;
    @FXML RadioButton StudentenListRadioButton;
    @FXML RadioButton lehrerlistRadioButton;
    @FXML   TableView  HinzufügenTableview;
    @FXML TableColumn  col_ID;
    @FXML TableColumn col_Name;

    Alert a = new Alert(Alert.AlertType.CONFIRMATION);



    ObservableList<querySetter> unterrichtlisten = FXCollections.observableArrayList();
    ObservableList<Student> oblistStudent = FXCollections.observableArrayList();
    ObservableList<Lehrer> oblistLehrer = FXCollections.observableArrayList();




    public String setFachgebiet(String fachgebiet){


        System.out.println(fachgebiet);


            fachgebiet = " where UnterrichtID = "+"'"+fachgebiet+"'";
            System.out.println(fachgebiet + " seçildi");
        return fachgebiet;
        }





    @FXML
    protected void hinzufugenClicked() throws Exception{



        auflistenSplitPane.setVisible(false);

        if(hinzufugenAnchorPane.isVisible()==false) {

            hinzufugenAnchorPane.setVisible(true);
            ectsTextF.setText(null);
            UnterrichtIDTextF.setText(null);
            modulTextF.setText(null);
            uNameTextF.setText(null);
            UnterrichtIDTextF.setPromptText("Klicken Sie zum Generate oder Schreiben ID zu Suchen.");



        }
        else {


            a.setContentText("Die Daten von Lehrer ist zu verloren. Sind sie Sicher?");
            a.showAndWait();

            if (a.getResult() == ButtonType.OK){

                ectsTextF.setText(null);
                UnterrichtIDTextF.setText(null);
                modulTextF.setText(null);
                uNameTextF.setText(null);
                UnterrichtIDTextF.setPromptText("Klicken Sie zum Generate oder Schreiben ID zu Suchen.");

            }

        }
    }

    @FXML protected void generateButtonClicked() throws Exception{


        UnterrichtIDTextF.setText(prMethods.generateID(3));
        infoTextArea.setText("Unterricht ID  wird generiert.");

    }

    @FXML protected void anmeldeButtonClicked() throws SQLException {


                                prMethods.insertUnterricht(UnterrichtIDTextF.getText(),uNameTextF.getText(),ectsTextF.getText(),modulTextF.getText());
                infoTextArea.setText(uNameTextF.getText() +" mit Unterricht ID "+UnterrichtIDTextF.getText() + " wird angemeldet.");


    }

    @FXML protected void auflistenButtonClicked(){


        hinzufugenAnchorPane.setVisible(false);




            auflistenSplitPane.setVisible(true);






    }


    @FXML protected void listenButtonClicked() throws SQLException {

        unterrichtlisten.clear();




        ResultSet rs = prMethods.listUnterricht();

        while (rs.next()) {



            unterrichtlisten.add(new querySetter(rs.getString(1),rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getInt(6) ));


        }


        col_lehrerID.setCellValueFactory(new PropertyValueFactory<querySetter,String>("lehrerID"));
        col_UID.setCellValueFactory(new PropertyValueFactory<querySetter,String>("UID"));
        col_UNAME.setCellValueFactory(new PropertyValueFactory<querySetter,String>("UNAME"));
        col_ects.setCellValueFactory(new PropertyValueFactory<querySetter,String>("ECTS"));
        col_lehrername.setCellValueFactory(new PropertyValueFactory<querySetter,String>("lehrerName"));
        col_counter.setCellValueFactory(new PropertyValueFactory<querySetter,Integer>("counter"));




            lehrerTableview.setItems(unterrichtlisten);
            infoTextArea.setText(unterrichtlisten.size()+" Unterricteh wird gelistet.");







    }

    @FXML protected void sucheButtonClicked() throws SQLException{
        oblistLehrer.clear();
        oblistStudent.clear();


        ResultSet rs = prMethods.findUnterricht(UnterrichtIDTextF.getText());


        if (!rs.isBeforeFirst()){

            infoTextArea.setText("Unterricht wird nicht gefunden.");


        }

        while (rs.next()) {

            infoTextArea.setText("Unterricht wird gefunden.");

            UnterrichtIDTextF.setText(rs.getString("unterrichtID"));
            uNameTextF.setText(rs.getString("fachgebiet"));
            ectsTextF.setText(rs.getString("Unterricht_info"));
            modulTextF.setText(rs.getString("modulhandbuch"));

        }

        ResultSet rs2 = prMethods.listTeacher(setFachgebiet(UnterrichtIDTextF.getText()));
        ResultSet rs3 = prMethods.listStudent(setFachgebiet(UnterrichtIDTextF.getText()));
            if(lehrerlistRadioButton.isSelected()){

                if (!rs2.isBeforeFirst()){

                    oblistLehrer.add(new Lehrer("Kein Lehrer", null, null, "ist angemeldet." ,
                            null, null, null, null, null, null,
                            null, null));


                }

                while (rs2.next()) {
                    String tamAd = rs2.getString("Name")+" "+rs2.getString("Nachname");
                    System.out.println(tamAd);

                    oblistLehrer.add(new Lehrer(rs2.getString("lehrerID"), null, null, tamAd ,
                            null, null, null, null, null, null,
                            null, null));
                                    }

                col_ID.setText("Lehrer ID");
                col_Name.setText("Name Nachname");
                col_ID.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("lehrerID"));
                col_Name.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("lehrerName"));
                HinzufügenTableview.setItems(oblistLehrer);

            }

            else if (StudentenListRadioButton.isSelected()) {
                if (!rs3.isBeforeFirst()){

                    oblistStudent.add(new Student("Kein Student", null, null, "ist angemeldet." ,
                            null, null, null, null, null, null,
                            null, null,null,null));

                }

                while (rs3.next()) {

                    String tamAd = rs3.getString("studentName")+" "+rs3.getString("studentNachname");
                    System.out.println(tamAd);
                    oblistStudent.add(new Student(rs3.getString("studentID"), null, null, tamAd ,
                        null, null, null, null, null, null,
                        null, null,null,null));
            }

        col_ID.setText("Student ID");
        col_Name.setText("Name Nachname");
        col_ID.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("studentID"));
        col_Name.setCellValueFactory(new PropertyValueFactory<Lehrer,String>("Name"));
        HinzufügenTableview.setItems(oblistStudent);

            }
    }




    @FXML protected void loschenButtonClicked() throws SQLException{

        a.setContentText("Die Daten von Unterricht ist zu löschen. Sind sie Sicher?");
        a.showAndWait();

        if (a.getResult() == ButtonType.OK){

            prMethods.deleteUnterricht(UnterrichtIDTextF.getText());
            infoTextArea.setText("Unterricht wurde gelöscht.");
            UnterrichtIDTextF.setText(null);
            uNameTextF.setText(null);
            modulTextF.setText(null);
            ectsTextF.setText(null);

        }

    }
    @FXML protected void aktualisierenButtonClicked() throws SQLException{


       Unterricht aktualisierteUnterricht = new Unterricht(UnterrichtIDTextF.getText(),uNameTextF.getText(),ectsTextF.getText(),modulTextF.getText());

        prMethods.updateUnterricht(aktualisierteUnterricht);

        infoTextArea.setText("Unterricht Aktualisiert.");
        System.out.println("Unterricht Aktualisiert");




    }


    @FXML
    Button outLogo;
    @FXML
    protected void logoutLogoClicked() throws IOException {

        System.out.println("logo Clicked");

        prMethods.changeScene("adminPage.fxml",outLogo);

    }
    @FXML protected void lehrerlistRadioButtonClicked(){
        StudentenListRadioButton.setSelected(false);
    }
    @FXML protected void StudentlistRadioButtonClicked(){
        lehrerlistRadioButton.setSelected(false);
    }
}
