/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class ConnectionFactory {
    
   private static final String DRIVER  = "com.mysql.jdbc.Driver";
    public static String URL = "jdbc:mysql://pmabanconovo.mysql.uhserver.com/pmabanconovo";
    public static String USER = "pmabanconovo";
    public static String PASSWORD = "Muller1@";
    
    public static Connection getConnection(){
         try {
            Class.forName(DRIVER);    
            return DriverManager.getConnection(URL,USER,PASSWORD);                                
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na Conexão, Banco não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Erro na Conexão, Banco não encontrado: ",ex);
            //throw new RuntimeException(null, "Erro na Conexão, Banco não encontrado", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }
     public static Connection getConnection(JFrame jframe){
         try {
            Class.forName(DRIVER);    
            return DriverManager.getConnection(URL,USER,PASSWORD);                                
        } catch (ClassNotFoundException | SQLException ex) {
             JOptionPane.showMessageDialog(jframe, "Erro na Conexão. Não foi possível se conectar ao servidor Banco de Dados\nVerifique sua Conexão e tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
              throw new RuntimeException( null ,ex);
        }
    }
    
    public static void closeConnection( Connection con){
        try {
            if(con!= null){
           
                con.close();
            }
        } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
     public static void closeConnection( Connection con, PreparedStatement stmt){
           
         closeConnection(con); 
         try {
             if(stmt != null){
                 stmt.close();
             }
         } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
     
     public static void closeConnection( Connection con, PreparedStatement stmt, ResultSet rs){
           
         closeConnection(con,stmt); 
         try {
             if(rs != null){
                 rs.close();
             }
         } catch (SQLException ex) {
                Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
}
