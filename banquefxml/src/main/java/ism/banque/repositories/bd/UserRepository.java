package ism.banque.repositories.bd;

import java.sql.ResultSet;
import java.sql.SQLException;

import ism.banque.core.MysqlDb;
import ism.banque.entities.Agence;
import ism.banque.entities.Role;
import ism.banque.entities.User;
import ism.banque.repositories.IUserRepository;

public class UserRepository extends MysqlDb implements IUserRepository {
  private final String SQL_CONNECT="SELECT * FROM `user` WHERE `login` LIKE ? AND `password` LIKE ?";

    @Override
    public User findUserByLoginAndPassword(String login, String password) {
        User user=null;
        this.ouvrirConnexionBD();
        try {
              ps= conn.prepareStatement(SQL_CONNECT) ;
              ps.setString(1, login); 
              ps.setString(2, password); 
              ResultSet rs=ps.executeQuery();
              if(rs.next()){
                user=new User(
                    rs.getInt("id"), 
                    rs.getString("role").compareTo("Client")==0? Role.Client:Role.Gestionnaire, 
                    rs.getString("login"),
                    rs.getString("password") ,
                    rs.getString("nom_complet")
                   
                  );
                  if(rs.getString("role").compareTo("Client")!=0){
                       Agence agence =new Agence(rs.getInt("agence_id"));
                       user.setAgence(agence);
                  }
              }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return user;
    
    }
    
}
