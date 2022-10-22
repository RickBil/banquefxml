package  ism.banque.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MysqlDb implements IDatabase {

    protected Connection conn;
    protected PreparedStatement ps;
    @Override
    public void ouvrirConnexionBD() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2-Ouvrir la Connexion
            try {
                conn= DriverManager.getConnection(
                  "jdbc:mysql://localhost:8889/bank",
                   "root", "root");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
          catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
              e.printStackTrace();
          }
        
    }
    @Override
    public void fermerConnexionBD() {
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

   

   
}
