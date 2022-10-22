package  ism.banque.repositories.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import  ism.banque.core.MysqlDb;
import  ism.banque.entities.Client;
import  ism.banque.entities.Role;
import  ism.banque.repositories.IClientRepository;

public class ClientRepository extends MysqlDb implements IClientRepository {

    private final String  SQL_INSERT="INSERT INTO `user` (`role`, `login`, `password`, `nom_complet`, `tel`) VALUES (?,?,?,?, ?)";
    private final String  SQL_SELECT_BY_TEL="SELECT * FROM `user` WHERE `tel` LIKE ? ";
    private final String  SQL_SELECT_BY_ID="SELECT * FROM `user` WHERE `id` =? ";
    
    @Override
    public Client insert(Client client) {
        this.ouvrirConnexionBD();
        try {
                ps=conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,client.getRole().name() );
                ps.setString(2,client.getLogin() );
                ps.setString(3,client.getPassword() );
                ps.setString(4,client.getNomComplet() );
                ps.setString(5,client.getTel() );
                ps.executeUpdate();
              ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    client.setId(rs.getInt(1));
                }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return client;
    }

    @Override
    public Client findByTel(String tel) {
        Client client=null;
        this.ouvrirConnexionBD();
        try {
              ps= conn.prepareStatement(SQL_SELECT_BY_TEL) ;
              ps.setString(1, tel); 
              ResultSet rs=ps.executeQuery();
              if(rs.next()){
                client=new Client(
                    rs.getInt("id"), 
                    rs.getString("role").compareTo("ROLE_CLIENT")==0? Role.Client:Role.Gestionnaire, 
                    rs.getString("login"),
                    rs.getString("password") ,
                    rs.getString("nom_complet"),
                    rs.getString("tel")  
                  );
              }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return client;
    }

    @Override
    public Client findById(int id) {
        Client client=null;
        this.ouvrirConnexionBD();
        try {
              ps= conn.prepareStatement(SQL_SELECT_BY_ID) ;
              ps.setInt(1, id); 
              ResultSet rs=ps.executeQuery();
              if(rs.next()){
                client=new Client(
                    rs.getInt("id"), 
                    rs.getString("role").compareTo("ROLE_CLIENT")==0? Role.Client:Role.Gestionnaire, 
                    rs.getString("login"),
                    rs.getString("password") ,
                    rs.getString("nom_complet"),
                    rs.getString("tel")  
                  );
              }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return client;
    }

    
}
