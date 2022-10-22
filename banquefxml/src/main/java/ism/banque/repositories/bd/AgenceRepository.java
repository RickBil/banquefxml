package  ism.banque.repositories.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import  ism.banque.entities.Agence;
import  ism.banque.repositories.IAgenceRepository;

public class AgenceRepository implements IAgenceRepository {

    @Override
    public List<Agence> findAll() {
        List<Agence> agences=new ArrayList<>();
       //1-Charger le Driver
       try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2-Ouvrir la Connexion
         Connection conn= DriverManager.getConnection(
          "jdbc:mysql://localhost:8889/java_l3_ism_2022", 
           "root", "root");
         //3-Executer la Requete
            Statement stm=conn.createStatement();
       //4-Recuperer les Resultats
          ResultSet rs=   stm.executeQuery("select * from agence");
          while (rs.next()){
              
               //rs une ligne de la table ou un enregistement ou un tuple =>Relationnel(BD)
                  //colonne 1(int) => rs.getInt(1)  ou rs.getInt("id")
                  //colonne 2(varchar) => rs.getString(2) ou rs.getString("numero")
                  Agence ag=new Agence(rs.getInt("id"), 
                                       rs.getString("numero"),
                                       rs.getString("adresse"), 
                                       rs.getString("tel"));
                 agences.add(ag)  ;                  

              //Requete Select :Relation(BD) vers un Objet(JAVA)
          }
       //5-Fermer la Connexion
          conn.close();
          
      } catch(SQLException e){
           e.printStackTrace();
      }
      catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
          e.printStackTrace();
      }
       

        return agences;
    }

    @Override
    public Agence insert(Agence agence) {
        String sql="INSERT INTO `agence` ( `numero`, `adresse`, `tel`) VALUES (?,?,?)";
        try {
          Class.forName("com.mysql.cj.jdbc.Driver");
          //2-Ouvrir la Connexion
           Connection conn= DriverManager.getConnection(
            "jdbc:mysql://localhost:8889/java_l3_ism_2022", 
             "root", "root");
           //3-Executer la Requete
              PreparedStatement pstm=conn.prepareStatement(sql);
              //Remplacer les parametres de Requetes par leur valeurs
              pstm.setString(1, agence.getNumero() );
              pstm.setString(2, agence.getAdresse() );
              pstm.setString(3, agence.getTel() );
          //4-Recuperer les Resultats
              int nbreLigne=pstm.executeUpdate();
            
         //5-Fermer la Connexion
            conn.close();
            
        } catch(SQLException e){
             e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
          // TODO Auto-generated catch block
            e.printStackTrace();
        }
           return agence;
    }

    @Override
    public Agence findByNumero(String num) {
         Agence agence=null;
      try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2-Ouvrir la Connexion
         Connection conn= DriverManager.getConnection(
          "jdbc:mysql://localhost:8889/java_l3_ism_2022", 
           "root", "root");
         //3-Executer la Requete
            Statement stm=conn.createStatement();
       //4-Recuperer les Resultats
          ResultSet rs=   stm.executeQuery("select * from agence where numero='"+num+"'");
          if (rs.next()){
            agence=new Agence(rs.getInt("id"), 
            rs.getString("numero"),
            rs.getString("adresse"), 
            rs.getString("tel"));
              //Requete Select :Relation(BD) vers un Objet(JAVA)
          }
       //5-Fermer la Connexion
          conn.close();
          
      } catch(SQLException e){
           e.printStackTrace();
      }
      catch (ClassNotFoundException e) {
        // TODO Auto-generated catch block
          e.printStackTrace();
      }
       
        return agence;
    }
    
}
