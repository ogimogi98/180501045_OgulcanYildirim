package com.company.maindemo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import javax.security.auth.callback.Callback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.*;

public class adminStudentPageController {

    @FXML TableView studentTableview;
    @FXML ChoiceBox sortingChoiceBox;
    @FXML ChoiceBox geschlectlistenChoicebox;
    @FXML CheckBox elternCheckBox;
    @FXML CheckBox adresseCheckBox;
    @FXML CheckBox unaktivCheckBox;

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
    @FXML ObservableList<ObservableList> data;

    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
    ObservableList<String> geschlectArten = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity");
    ObservableList<String> sortArten = FXCollections.observableArrayList("studentID DESC","studentID ASC","TC DESC","TC ASC");
    ObservableList<String> geschlectArten2 = FXCollections.observableArrayList("Mannlich","Weiblich","Diversity","Alles");


    @FXML
    protected void hinzufugenClicked() throws Exception{

        geschlechtChoiceBox.setItems(geschlectArten);

        if(hinzufugenAnchorPane.isVisible()==false) {

        hinzufugenAnchorPane.setVisible(true);


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

        }

        @FXML protected void anmeldeButtonClicked() throws SQLException {

            if (prMethods.kontrolleID(ausweisIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), "student", "TC")) {
                if (prMethods.kontrolleID(studentIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), "student", "studentID")) {
                    String date = String.valueOf(geburtsDatumPick.getValue());
                    prMethods.insertStudent(studentIDTextF.getText(), passwordTextF.getText(), ausweisIDTextF.getText(), nameTextF.getText(), nachnameTextF.getText(), geschlechtChoiceBox.getValue().toString(), adresseTextF.getText(),
                        date, handyTextF.getText(), elternNameTextF.getText(), elternHandyTextF.getText(), elternartTextF.getText(), emailTextF.getText());
                    }
                }

            }

        @FXML protected void auflistenButtonClicked(){

            geschlectlistenChoicebox.setItems(geschlectArten2);
            sortingChoiceBox.setItems(sortArten);

            if(auflistenSplitPane.isVisible()==false) {

                auflistenSplitPane.setVisible(true);
                geschlectlistenChoicebox.setValue("Alles");
                sortingChoiceBox.setValue("studentID DESC");

            }
            else {

            sortingChoiceBox.setValue("studentID DESC");
            geschlectlistenChoicebox.setValue("null");
            elternCheckBox.setSelected(false);
            adresseCheckBox.setSelected(false);
            unaktivCheckBox.setSelected(false);

            }

        }
        @FXML protected void listenButtonClicked() throws SQLException {
                String sort = " ORDER BY " + sortingChoiceBox.getValue().toString();
                String geschlect = geschlectlistenChoicebox.getValue().toString();
                System.out.println(sort);
                System.out.println(geschlect);
                if (geschlect == "Alles"){
                   geschlect = "";
                   System.out.println(geschlect + " seçilmedi");
                } else {
                    geschlect = " where " + geschlectlistenChoicebox.getValue().toString();
                    System.out.println(geschlect + " seçildi");
                }

                String query = "Select studentID, TC, Name, Nachname, from student"+geschlect+sort;
                System.out.println(query);
                PreparedStatement preparedStatement = prMethods.connection.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();


            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));

                col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });


                studentTableview.getColumns().addAll(col);
                System.out.println("Column ["+i+"] ");
            }

            /********************************
             * Data added to ObservableList *
             ********************************/
            while(rs.next()){
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added "+row );
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            studentTableview.setItems(data);
        }catch(Exception e){
        e.printStackTrace();
        System.out.println("Error on Building Data");
    }

        }


    }






