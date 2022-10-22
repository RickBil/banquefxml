package ism.banque.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import ism.banque.core.Fabrique;
import ism.banque.entities.Agence;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class AgenceController implements Initializable {
   
    @FXML
    TableView<Agence> tblvAgence=new TableView<>();
    @FXML
    TableColumn<Agence,String> tblcId=new TableColumn<>();
    @FXML
    TableColumn<Agence,String> tblcNumero=new TableColumn<>();
    @FXML
    TableColumn<Agence,String> tblcTelephone=new TableColumn<>();
    @FXML
    TableColumn<Agence,String> tblcAdresse=new TableColumn<>();

    @FXML
     TextField txtTel,txtAdresse;

     private  ObservableList   obAgences=FXCollections.observableList(Fabrique.getService().listerAgence());
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblcId.setCellValueFactory(new PropertyValueFactory<>("id") );
        tblcNumero.setCellValueFactory(new PropertyValueFactory<>("numero") );
        tblcTelephone.setCellValueFactory(new PropertyValueFactory<>("tel") );
        tblcAdresse.setCellValueFactory(new PropertyValueFactory<>("adresse") );
       //Abonnement
        tblvAgence.setItems(obAgences);
    }

    public void handleCreerAgence(){
        String tel=txtTel.getText().trim();
        String adresse=txtAdresse.getText().trim();
        Agence agence = Fabrique.getService().ajouterAgence(new Agence(adresse, tel));
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Gestion Bancaire");
        alert.setContentText("Une agence a ete cree avec succes");
        alert.show();
        obAgences.add(agence );
        txtAdresse.clear();
        txtTel.clear();

    }
}
