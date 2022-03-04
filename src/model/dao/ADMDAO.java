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
import model.bean.Vw_Anamnese_Paciente;
import model.bean.Vw_Consultas;
import model.bean.Vw_TelefonesPsicologos;

/**
 *
 * @author Eliézer
 */
public class ADMDAO {
    public List<Vw_TelefonesPsicologos> ReadTelefonesPsicologos() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPsicologos> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPsicologo, Psicologo, Email,CRP, GROUP_CONCAT(numero) as Numero FROM vw_TelefonePsicologos Group By CodigoPsicologo") ;
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPsicologos v = new Vw_TelefonesPsicologos();

                v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getPsicologo().setEmail(rs.getString("Email"));
                v.getPsicologo().setCRP(rs.getString("CRP"));
                v.getTelefone().setNumero(rs.getString("Numero"));
                
                vw.add(v);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    
     public List<Vw_TelefonesPsicologos> BuscaManterPsicologo(String Atributo, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPsicologos> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPsicologo, Psicologo, Email,CRP, GROUP_CONCAT(numero) as Telefone FROM vw_TelefonePsicologos WHERE "+Atributo+ " Like '%"+Busca+"%' Group By CodigoPsicologo;";
            stmt = con.prepareStatement(sql);
            
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPsicologos v = new Vw_TelefonesPsicologos();
                 v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getPsicologo().setEmail(rs.getString("Email"));
                v.getPsicologo().setCRP(rs.getString("CRP"));
                v.getTelefone().setNumero(rs.getString("Numero"));
                
                vw.add(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
     
       public List<Vw_Consultas> ReadConsultas(Object data) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Consultas> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Consultas Where DataConsulta Like ? ORDER BY DataConsulta");
            stmt.setObject(1, data+"%");
          
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Consultas c = new Vw_Consultas();

                c.setCodConsulta(rs.getInt("CodigoConsulta"));
                c.getPaciente().setNome_Completo(rs.getString("Paciente"));
                c.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                //c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.setDataConsulta(rs.getObject("DataConsulta"));
                c.setStatus(rs.getString("Status"));

                vw.add(c);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
       
       
            public List<Vw_Anamnese_Paciente> ReadALLAnamneses() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anamnese_Paciente");
         
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Anamnese_Paciente v = new Vw_Anamnese_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getConsulta().setDataConsulta(rs.getTimestamp("DataConsulta"));
                
                v.getAnamnese().setCodAnamnese(rs.getInt("CodAnamnese"));
                 v.getAnamnese().setDiagnostico(rs.getString("Diagnostico"));
                vw.add(v);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
            
            
        public List<Vw_Anamnese_Paciente> BuscaALLAnamneses(String Atributo, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE "+Atributo+ " Like '%"+Busca+"%'  Group By CodigoPaciente;";
            stmt = con.prepareStatement(sql);
           
            
            rs = stmt.executeQuery();

            while (rs.next()) {
             
                Vw_Anamnese_Paciente v = new Vw_Anamnese_Paciente();

            
                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getConsulta().setDataConsulta(rs.getTimestamp("DataConsulta"));
                
                v.getAnamnese().setCodAnamnese(rs.getInt("CodAnamnese"));
                 v.getAnamnese().setDiagnostico(rs.getString("Diagnostico"));
                vw.add(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
        }
}