package ism.banque.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ism.banque.App;
import ism.banque.entities.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class HomeController implements Initializable{

    @FXML 
    AnchorPane anchorContent;

    @FXML 
   Button btnMenuClient,btnMenuCompte;

    public void handleDeconnexion() throws IOException{
        App.setRoot("connexion");
    }

    public void handleLoadViewAgence() throws IOException{
        this.loadView("agence");
     
    }

    public void handleLoadViewCompte() throws IOException{
        this.loadView("compte");
     
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        try {
           if(ConnexionController.user.getRole()==Role.Gestionnaire) {
                 isGestionnaire();
           }
            
            this.loadView("agence");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }


    private void  loadView(String fxml) throws IOException{
        AnchorPane root =(AnchorPane) App.loadFXML(fxml);
        anchorContent.getChildren().clear();
        anchorContent.getChildren().add(root);
    }

 

    private void isGestionnaire(){
      btnMenuClient.setDisable(false);
      btnMenuCompte.setDisable(false);
    }

    private void isClient(){
        
    }
}
