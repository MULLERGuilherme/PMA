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
import model.bean.Anotacao;
import model.bean.Consulta;
import model.bean.Psicologo;


/**
 *
 * @author User
 */
public class AnotacaoDAO {
    
    
     public boolean Create(Anotacao a){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO anotacao (Assunto, Texto, CodConsulta) VALUES (?, ?, ?) ");
            stmt.setString(1,  a.getAssunto());
            stmt.setString(2,  a.getTexto());
            stmt.setInt(3,  a.getConsulta().getCodConsulta());

            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            status = false;
             JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
     
     public List<Anotacao> Read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Anotacao> anotacoes = new ArrayList<>();
        try { 
            stmt  = con.prepareStatement("SELECT * FROM anotacao");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                   Anotacao a = new Anotacao();
                   a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));
                   a.setAssunto(rs.getString("Assunto"));
                   a.setTexto(rs.getString("Texto"));
                   a.setCodAnotacao(rs.getInt("CodAnotacao"));
                   a.setDataAnotacao(rs.getString("DataAnotacao"));
                   anotacoes.add(a);
                   
            }
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }finally{
             ConnectionFactory.closeConnection(con,stmt,rs);
        }
       
        return anotacoes;
    }
     
     public List<Anotacao> Read(Consulta c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Anotacao> anotacoes = new ArrayList<>();
        try { 
            stmt  = con.prepareStatement("SELECT * FROM anotacao WHERE Codconsulta = ?");
            stmt.setInt(1, c.getCodConsulta());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                   Anotacao a = new Anotacao();
                   a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));
                   a.setAssunto(rs.getString("Assunto"));
                   a.setTexto(rs.getString("Texto"));
                   a.setCodAnotacao(rs.getInt("CodAnotacao"));
                   a.setDataAnotacao(rs.getString("DataAnotacao"));
                   anotacoes.add(a);
                   
            }
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }finally{
             ConnectionFactory.closeConnection(con,stmt,rs);
        }
       
        return anotacoes;
    }
     
     public Anotacao ReadAnotacao(int Codanotacao){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       Anotacao a = new Anotacao();
        try { 
            stmt  = con.prepareStatement("SELECT * FROM anotacao WHERE CodAnotacao = ?");
            stmt.setInt(1, Codanotacao);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                   
                   a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));
                   a.setAssunto(rs.getString("Assunto"));
                   a.setTexto(rs.getString("Texto"));
                   a.setCodAnotacao(rs.getInt("CodAnotacao"));
                   a.setDataAnotacao(rs.getString("DataAnotacao"));
                 
                   
            }
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }finally{
             ConnectionFactory.closeConnection(con,stmt,rs);
        }
       
        return a;
    }
     
     public boolean Update(Anotacao a){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
        
            stmt = con.prepareStatement("Update anotacao SET Assunto=?, Texto=? WHERE CodAnotacao =? ");
            stmt.setString(1,  a.getAssunto());
            stmt.setString(2,  a.getTexto());
            stmt.setInt(3,  a.getCodAnotacao());
            stmt.executeUpdate();
            
         
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
           status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
    return status;
    }
     
     
      public boolean Delete(Anotacao a){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement("Delete FROM anotacao WHERE CodAnotacao =? ");
           
            stmt.setInt(1, a.getCodAnotacao());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }    
      
      public boolean Delete(Consulta c){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement("Delete FROM anotacao WHERE CodConsulta =? ");
           
            stmt.setInt(1, c.getCodConsulta());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }    
      
      public boolean SoftDelete(Consulta c){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement("Update anotacao set Deletada = true WHERE CodConsulta =? ");
           
            stmt.setInt(1, c.getCodConsulta());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }    
      
    public boolean SoftDelete(Psicologo p){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement("Update anotacao set Deletada = true WHERE CodPsicologo =? ");
           
            stmt.setInt(1, p.getCodPsicologo());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }    
    
    public boolean RestaurarAnotacoes(int CodPsicologo){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement("Update anotacao set Deletada = false WHERE CodPsicologo =? ");
           
            stmt.setInt(1, CodPsicologo);
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }    
    
    public boolean RestaurarAnotacoes(Consulta c){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement("Update anotacao set Deletada = false WHERE CodConsulta =? ");
           
            stmt.setInt(1, c.getCodConsulta());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }    
}
