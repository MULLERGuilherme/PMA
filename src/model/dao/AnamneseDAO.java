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
import model.bean.Anamnese;
import model.bean.Consulta;



/**
 *
 * @author User
 */
public class AnamneseDAO {
    
    public boolean Create(Anamnese a){
        
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO anamnese (QueixaPrincipal, SubitaOuProgressiva, InicioDaQueixa, QueixasSecundarias, HistoricoFamiliar, Diagnostico, Encaminhamento, DoencasConhecidas, MedicamentosUtilizados, CodConsulta, OqueMudou, Sintomas, ComoComecou, QueixasCognitivas, QueixasAfetivoEmocionais, Psicomotricidade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
            stmt.setString(1,  a.getQueixaPrincipal());
            stmt.setString(2,  a.getSubitaOuProgressiva());
            stmt.setObject(3,  a.getInicioDaQueixa());
            stmt.setString(4,  a.getQueixasSecundarias());
            stmt.setString(5,  a.getHistoricoFamiliar());
            stmt.setString(6,  a.getDiagnostico());
            stmt.setString(7,  a.getEncaminhamento());
            stmt.setString(8,  a.getDoencasConhecidas());
            stmt.setString(9,  a.getMedicamentosUtilizados());            
            stmt.setInt(10,  a.getConsulta().getCodConsulta());
//            Mudancas
            stmt.setString(11,  a.getOqueMudou());
            stmt.setString(12,  a.getSintomas());
            stmt.setString(13,  a.getComoComecou());
            stmt.setString(14,  a.getQueixasCognitivas());
            stmt.setString(15,  a.getQueixasAfetivoEmocionais());
            stmt.setString(16,  a.getPsicomotricidade());
            
            stmt.executeUpdate();         
           
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao inserir :" +ex);
           status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    
    
    public List<Anamnese> Read(){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Anamnese> anamneses = new ArrayList<>();
        try { 
            stmt  = con.prepareStatement("SELECT * FROM anamnese");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                   Anamnese a = new Anamnese();
                  a.setQueixaPrincipal( rs.getString("QueixaPrincipal"));
                  a.setSubitaOuProgressiva(rs.getString("SubitaOuProgressiva"));
                  a.setInicioDaQueixa(rs.getString("InicioDaQueixa"));
                  a.setQueixasSecundarias(rs.getString("QueixasSecundarias"));
                  a.setHistoricoFamiliar(rs.getString("HistoricoFamiliar"));
                  a.setDiagnostico(rs.getString("Diagnostico"));
                  a.setEncaminhamento(rs.getString("Encaminhamento"));
                  a.setDoencasConhecidas(rs.getString("DoencasConhecidas"));
                  a.setMedicamentosUtilizados(rs.getString("MedicamentosUtilizados"));
                  a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));
                    a.setOqueMudou(rs.getString("OqueMudou"));
                    a.setSintomas(rs.getString("Sintomas"));
                    a.setComoComecou(rs.getString("ComoComecou"));
                    a.setQueixasCognitivas(rs.getString("QueixasCognitivas"));
                    a.setQueixasAfetivoEmocionais(rs.getString("QueixasAfetivoEmocionais"));
                    a.setPsicomotricidade(rs.getString("Psicomotricidade"));
                     a.setDataEmissao(rs.getString("DataEmissao"));
                   
                   anamneses.add(a);
                   
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(AnamneseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             ConnectionFactory.closeConnection(con,stmt,rs);
        }
       
        return anamneses;
    }
    public List<Anamnese> Read(Consulta c){
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Anamnese> anamneses = new ArrayList<>();
        try { 
            stmt  = con.prepareStatement("SELECT * FROM anamnese Where CodConsulta = ?");
            stmt.setInt(1, c.getCodConsulta());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                   Anamnese a = new Anamnese();
                  a.setQueixaPrincipal( rs.getString("QueixaPrincipal"));
                  a.setSubitaOuProgressiva(rs.getString("SubitaOuProgressiva"));
                  a.setInicioDaQueixa(rs.getString("InicioDaQueixa"));
                  a.setQueixasSecundarias(rs.getString("QueixasSecundarias"));
                  a.setHistoricoFamiliar(rs.getString("HistoricoFamiliar"));
                  a.setDiagnostico(rs.getString("Diagnostico"));
                  a.setEncaminhamento(rs.getString("Encaminhamento"));
                  a.setDoencasConhecidas(rs.getString("DoencasConhecidas"));
                  a.setMedicamentosUtilizados(rs.getString("MedicamentosUtilizados"));
                  a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));
                    a.setOqueMudou(rs.getString("OqueMudou"));
                    a.setSintomas(rs.getString("Sintomas"));
                    a.setComoComecou(rs.getString("ComoComecou"));
                    a.setQueixasCognitivas(rs.getString("QueixasCognitivas"));
                    a.setQueixasAfetivoEmocionais(rs.getString("QueixasAfetivoEmocionais"));
                    a.setPsicomotricidade(rs.getString("Psicomotricidade"));
                    a.setDataEmissao(rs.getString("DataEmissao"));
                    a.setCodAnamnese(rs.getInt("CodAnamnese"));
                   
                   anamneses.add(a);
                   
            }
            
        } catch (SQLException ex) {
            
            Logger.getLogger(AnamneseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
             ConnectionFactory.closeConnection(con,stmt,rs);
        }
       
        return anamneses;
    }
    
    public boolean Update(Anamnese a){
        
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
        
            stmt = con.prepareStatement("UPDATE anamnese SET QueixaPrincipal=?, SubitaOuProgressiva=?, InicioDaQueixa=?, QueixasSecundarias=?, HistoricoFamiliar=?, Diagnostico=?, Encaminhamento=?, DoencasConhecidas=?, MedicamentosUtilizados=?, OqueMudou=?, Sintomas=?,ComoComecou=?, QueixasCognitivas=?, QueixasAfetivoEmocionais=?, Psicomotricidade=?  WHERE CodAnamnese = ? ");
            stmt.setString(1,  a.getQueixaPrincipal());
            stmt.setString(2,  a.getSubitaOuProgressiva());
            stmt.setObject(3,  a.getInicioDaQueixa());
            stmt.setString(4,  a.getQueixasSecundarias());
            stmt.setString(5,  a.getHistoricoFamiliar());
            stmt.setString(6,  a.getDiagnostico());
            stmt.setString(7,  a.getEncaminhamento());
            stmt.setString(8,  a.getDoencasConhecidas());
            stmt.setString(9,  a.getMedicamentosUtilizados());
            //Mudancas
            stmt.setString(10,  a.getOqueMudou());
            stmt.setString(11,  a.getSintomas());
            stmt.setString(12,  a.getComoComecou());
            stmt.setString(13,  a.getQueixasCognitivas());
            stmt.setString(14,  a.getQueixasAfetivoEmocionais());
            stmt.setString(15,  a.getPsicomotricidade());
            
            stmt.setInt(16,  a.getCodAnamnese());
            

            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Erro ao alterar :" +ex);
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    
    }
    
   
    
     public boolean Delete(Anamnese a){
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
                
        try {
            stmt = con.prepareStatement("Delete FROM anamnese WHERE CodAnamnese =? ");
           
            stmt.setInt(1, a.getCodAnamnese());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao Excluir :" +ex);
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
            stmt = con.prepareStatement("Delete FROM anamnese WHERE CodConsulta =? ");
           
            stmt.setInt(1, c.getCodConsulta());
            
            stmt.executeUpdate();
            
            
        } catch (SQLException ex) {
           
            status = false;
            
        } finally{
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
     
     public Anamnese ReadAnamneseConsulta(int CodConsulta) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Anamnese a = new Anamnese();
        try {
            stmt = con.prepareStatement("SELECT * FROM anamnese WHERE CodConsulta =?");
            stmt.setInt(1, CodConsulta);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                  a.setQueixaPrincipal( rs.getString("QueixaPrincipal"));
                  a.setSubitaOuProgressiva(rs.getString("SubitaOuProgressiva"));
                  a.setInicioDaQueixa(rs.getObject("InicioDaQueixa"));
                  a.setQueixasSecundarias(rs.getString("QueixasSecundarias"));
                  a.setHistoricoFamiliar(rs.getString("HistoricoFamiliar"));
                  a.setDiagnostico(rs.getString("Diagnostico"));
                  a.setEncaminhamento(rs.getString("Encaminhamento"));
                  a.setDoencasConhecidas(rs.getString("DoencasConhecidas"));
                  a.setMedicamentosUtilizados(rs.getString("MedicamentosUtilizados"));
                  a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));
                    a.setOqueMudou(rs.getString("OqueMudou"));
                    a.setSintomas(rs.getString("Sintomas"));
                    a.setComoComecou(rs.getString("ComoComecou"));
                    a.setQueixasCognitivas(rs.getString("QueixasCognitivas"));
                    a.setQueixasAfetivoEmocionais(rs.getString("QueixasAfetivoEmocionais"));
                    a.setPsicomotricidade(rs.getString("Psicomotricidade"));
                    a.setCodAnamnese(rs.getInt("codanamnese"));
                    a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));

                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return a;
    }
}
