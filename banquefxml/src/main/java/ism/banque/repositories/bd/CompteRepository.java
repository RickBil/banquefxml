package  ism.banque.repositories.bd;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import ism.banque.core.Config;
import  ism.banque.core.MysqlDb;
import  ism.banque.entities.Cheque;
import  ism.banque.entities.Client;
import  ism.banque.entities.Compte;
import  ism.banque.entities.Epargne;
import  ism.banque.entities.TypeCompte;
import ism.banque.repositories.IClientRepository;
import  ism.banque.repositories.ICompteRepository;

public class CompteRepository extends MysqlDb implements ICompteRepository {
    private final String  SQL_INSERT="INSERT INTO `compte` (`numero`, `solde`, `type`, `taux`, `frais`, `client_id`, `agence_id`) VALUES (?,?,?,?,?,?,?);";
    private final String  SQL_SELECT_BY_TEL="SELECT c.* FROM `compte` c,user u WHERE c.client_id = u.id and u.tel like ?";

    //Couplage Faible
    IClientRepository clientRepository;
    //Injection de Dependance
    public CompteRepository(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Compte insert(Compte compte) {
  
        this.ouvrirConnexionBD();
        try {
               String numero = Config.getPrefixCompte()+""+Config.getSeqAgence();
                ps=conn.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1,numero);
                ps.setDouble(2,compte.getSolde() );
                ps.setString(3,compte.getTypeCompte().name() );
                if(compte.getTypeCompte()==TypeCompte.Cheque){
                    ps.setNull(4,java.sql.Types.FLOAT);
                    ps.setDouble(5, ((Cheque)compte).getFrais());
                }else{
                    ps.setDouble(4,((Epargne)compte).getTaux());
                    ps.setNull(5,java.sql.Types.FLOAT);
                }
               
                ps.setInt(6,compte.getClient().getId() );
                ps.setInt(7,compte.getAgence().getId() );
                ps.executeUpdate();
                 ResultSet rs=ps.getGeneratedKeys();
                if(rs.next()){
                    
                    compte.setId(rs.getInt(1));
                }

        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return compte;
       
    }

    @Override
    public List<Compte> findByClient(String tel) {
        List<Compte>  comptes=new ArrayList<>();
        this.ouvrirConnexionBD();
        try {
              ps= conn.prepareStatement(SQL_SELECT_BY_TEL) ;
              ps.setString(1, tel); 
              ResultSet rs=ps.executeQuery();
              Compte compte;
              Client client=null ;
              while(rs.next()){
               if(rs.getString("type").compareTo("Cheque")==0){
                 compte=new Cheque(rs.getInt("id"),
                        rs.getString("numero"), 
                        rs.getDouble("solde"), 
                        rs.getDouble("frais"));
                    
               }else{
                    compte =new Epargne(rs.getInt("id"),
                    rs.getString("numero"), 
                    rs.getDouble("solde"), 
                    rs.getDouble("taux"));
               }
                
                  if(client!=null){
                    client =clientRepository.findById(rs.getInt("client_id"));
                  }
                    compte.setClient(client);
                    comptes.add(compte);
                
              }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.fermerConnexionBD();
        return comptes;
        
    }
    
}
