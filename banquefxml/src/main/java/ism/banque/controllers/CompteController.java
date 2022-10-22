package ism.banque.controllers;



import java.net.URL;
import java.util.ResourceBundle;

import ism.banque.core.Fabrique;
import ism.banque.core.Validator;
import ism.banque.entities.Cheque;
import ism.banque.entities.Client;
import ism.banque.entities.Compte;
import ism.banque.entities.Epargne;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CompteController implements Initializable {
    

    @FXML
    TextField txtTel,txtNomPrenom,txtLogin,txtTaux,txtFrais,txtSolde;
    @FXML
    Button btnSearch;
    @FXML
    Text lblErrorTel;

    @FXML
    PasswordField txtPassword;
    @FXML
    Pane pnlClient;

    @FXML
    RadioButton radCheque,radEpargne;


   @FXML
    TableView<Compte> tblvCompte;

    @FXML
   TableColumn<Compte,String> tblcNumero;
   @FXML
   TableColumn<Compte,String> tblcType;
   @FXML
   TableColumn<Compte,String> tblcSolde;
   
   ObservableList obsListCompte ;

   private void loadTableCompte(String tel){
      //Conversion List-> ObservableList
      obsListCompte=FXCollections.observableList(Fabrique.getService().lesComptesUnClient(tel));
      //Abonnement
      tblvCompte.setItems(obsListCompte);
      //Construction des colonnes
      tblcNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
      tblcType.setCellValueFactory(new PropertyValueFactory<>("type"));
      tblcSolde.setCellValueFactory(new PropertyValueFactory<>("solde"));


   }

    Client client;
   public void handleRechercheClient(){
    String tel =txtTel.getText().trim();
        client =Fabrique.getService().rechercherClientParTel(tel);
       if(client!=null){
          pnlClient.setDisable(true);
          txtNomPrenom.setText(client.getNomComplet());
          txtLogin.setText(client.getLogin());
          loadTableCompte(tel);
       }else{
          pnlClient.setDisable(false);
       }
   }

   public void handleActiveTypeCompte(){
        txtTaux.setDisable(!radEpargne.isSelected());
        txtFrais.setDisable(!radCheque.isSelected());
   }

   public void handleCreerCompte(){
         if(client==null){
            String tel =txtTel.getText().trim();
            String login =txtLogin.getText().trim();
            String password =txtPassword.getText().trim();
            String nomComplet =txtNomPrenom.getText().trim();
            client=  Fabrique.getService().creerClient(new Client(login, password, nomComplet, tel));
         }

         Compte cpt;
         double solde =Double.parseDouble(txtSolde.getText().trim());
         if(radCheque.isSelected()){
            double frais =Double.parseDouble(txtFrais.getText().trim());
            cpt=new Cheque(solde, frais);
         }else{
            double taux =Double.parseDouble(txtTaux.getText().trim());
            cpt=new Epargne(solde, taux);
         }
         cpt.setClient(client);
         cpt.setAgence(ConnexionController.user.getAgence());
         Fabrique.getService().creerCompte(cpt) ;
         Alert alert=new Alert(AlertType.INFORMATION);
         alert.setTitle("Gestion Bancaire");
         alert.setContentText("compte cree avec success");
         alert.showAndWait();
         clearFields();

   }


   private void clearFields(){
      txtSolde.clear();
      txtFrais.clear();
      txtTaux.clear();
      radEpargne.setSelected(true);
      txtTaux.setDisable(false);
      pnlClient.setDisable(true);
   }

   @Override
   public void initialize(URL location, ResourceBundle resources) {
      // TODO Auto-generated method stub

      btnSearch.setDisable(true);
      lblErrorTel.setVisible(false);

      txtTel.setTextFormatter(new  TextFormatter<>(change->{
               String input=change.getControlNewText();
               lblErrorTel.setVisible(true);
               if(Validator.isNumber(input)){
                  lblErrorTel.setVisible(false);
                  return change;
               }
               return null;
                
      }));

       
      txtTel.textProperty().addListener((obv,oldV,newV)->{
         if(!txtTel.getText().isEmpty() && txtTel.getText().length()==9){
            btnSearch.setDisable(false);
         }else{
            btnSearch.setDisable(true);
         }
     });

      
   }
}
