/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Paciente;
import model.bean.Psicologo;
import model.bean.Telefone;

/**
 *
 * @author User
 */
public class TelefoneDAO {
    
    public boolean CreatePc(Telefone t){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO telefone  (Numero, CodPaciente) VALUES (?, ?) ");
            stmt.setString(1,  t.getNumero());
            stmt.setInt(2,  t.getPaciente().getCodPaciente());
           
            
            
            stmt.executeUpdate();
            
           
        } catch (SQLException ex) {
            status = false;
             JOptionPane.showMessageDialog(null, "Erro ao inserir :" +ex);
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    
       public boolean CreatePsi(Telefone t){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO telefone  (Numero, CodPsicologo) VALUES (?, ?) ");
            stmt.setString(1,  t.getNumero());
            stmt.setInt(2,  t.getPsicologo().getCodPsicologo());
           
            
            
            stmt.executeUpdate();
            
          
        } catch (SQLException ex) {
            
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    
    
     public boolean UpdateTPsicologo(Telefone t){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("Update  telefone SET Numero=? WHERE CodTelefone =? ");
            
            //sem CPF pq nao faz sentido
            //stmt.setString(1,  p.getCPF());
            stmt.setString(1,  t.getNumero());
            stmt.setInt(2, t.getCodTelefone());
            
            stmt.executeUpdate();
            
          
        } catch (SQLException ex) {
          status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
     
       public boolean UpdateTPaciente(Telefone t){
        
         boolean status = true; 
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("Update  telefone SET Numero=? WHERE CodTelefone =? ");
            
            //sem CPF pq nao faz sentido
            //stmt.setString(1,  p.getCPF());
            stmt.setString(1,  t.getNumero());
            stmt.setInt(2, t.getCodTelefone());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar :" +ex);
            status = false;
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
     public List<Telefone> Read(int CodPaciente){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Telefone> telefones = new ArrayList<>();
        try { 
            stmt  = con.prepareStatement("SELECT * FROM telefone WHERE CodPaciente =?");
            stmt.setInt(1, CodPaciente);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                   Telefone t = new Telefone();
                   t.setNumero(rs.getString("Numero"));
                   t.setCodTelefone(rs.getInt("CodTelefone"));
                   t.getPaciente().setCodPaciente(rs.getInt("CodTelefone"));
                   telefones.add(t);
                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
             ConnectionFactory.closeConnection(con,stmt,rs);
        }
       
        return telefones;
    }
     public List<Telefone> ReadTPsicologo(int CodPsicologo){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Telefone> telefones = new ArrayList<>();
        try { 
            stmt  = con.prepareStatement("SELECT * FROM telefone WHERE CodPsicologo =?");
            stmt.setInt(1, CodPsicologo);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                   Telefone t = new Telefone();
                   t.setCodTelefone(rs.getInt("CodTelefone"));
                   t.setNumero(rs.getString("Numero"));
                   t.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                   
                   telefones.add(t);
                   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }finally{
             ConnectionFactory.closeConnection(con,stmt,rs);
        }
       
        return telefones;
    }
      
     public Paciente ReadCodPaciente(String CPF) throws SQLException{
     
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Paciente pc = new Paciente();
        stmt  = con.prepareStatement("SELECT * From paciente where CPF = ?");
        stmt.setString(1, CPF);
        System.out.println("ate aki foi");
        rs = stmt.executeQuery();
        System.out.println("ate aki foi 2");
        pc.setCodPaciente(rs.getInt("CodPaciente"));
              ConnectionFactory.closeConnection(con,stmt,rs);
            return pc;
            
           
                   
            }
     
     public boolean DeleteTPaciente(Paciente p){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Delete FROM telefone WHERE CodPaciente =? ");

            stmt.setInt(1, p.getCodPaciente());

            stmt.executeUpdate();

          
        } catch (SQLException ex) {
          Logger.getLogger(TelefoneDAO.class.getName()).log(Level.SEVERE, null, ex);
          status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
     }
     
     
     public boolean DeleteTPsicologo(Psicologo p){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Delete FROM telefone WHERE CodPsicologo =? ");

            stmt.setInt(1, p.getCodPsicologo());

            stmt.executeUpdate();

          
        } catch (SQLException ex) {
          status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
     }
            
    
    
}
