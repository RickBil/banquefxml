package ism.banque.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ism.banque.App;
import ism.banque.core.Fabrique;
import ism.banque.core.Validator;
import ism.banque.entities.User;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ConnexionController implements Initializable {

   
     @FXML
     Text lblError,lblErrorLogin,lblErrorPassword;
     @FXML
     TextField txtLogin;
     @FXML
     PasswordField txtPassword;
     @FXML
     Button btnConnexion;

     //Observable
     SimpleBooleanProperty smpl=new SimpleBooleanProperty(false);

     public  static  User user;


 

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblError.setVisible(false);      
        lblErrorLogin.setVisible(false);  
        lblErrorPassword.setVisible(false);
        txtLogin.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                lblErrorLogin.setVisible(true);
            }else{
                if(!Validator.isEmail(newV)){
                    lblErrorLogin.setText("veuillez saisir un email");
                    lblErrorLogin.setVisible(true);
                }else{
                    smpl.set(txtPassword.getText().isEmpty() );
                    lblErrorLogin.setVisible(false);
                }
              
            }
        });

        txtPassword.textProperty().addListener((obv,old,newV)->{
            if(newV.isEmpty()){
                lblErrorPassword.setVisible(true);
            }else{
                  smpl.set(!Validator.isEmail(txtLogin.getText()));
                 lblErrorPassword.setVisible(false);  
            }
        });
        
           btnConnexion.disableProperty().bind(smpl);
        
    }

    public void handleConnexion() throws IOException{
        String login =txtLogin.getText().trim(); 
        String password =txtPassword.getText().trim();
         user=Fabrique.getService().seConnecter(login, password)  ; 
        if(user==null){
            lblError.setVisible(true);  
        }else{
            lblError.setVisible(false);
            App.setRoot("home");
        }
    }

}