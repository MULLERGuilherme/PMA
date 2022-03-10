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
import model.bean.Consulta;
import model.bean.Vw_Anamnese_Paciente;
import model.bean.Vw_Anotacoes_Paciente;
import model.bean.Vw_Consultas;
import model.bean.Vw_TelefonesPacientes;

/**
 *
 * @author Eliézer
 */
public class ViewsDAO {
    
    
    public List<Vw_Consultas> ReadConsultas(Object data, int codpsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Consultas> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Consultas Where DataConsulta Like ? And CodigoPsicologo = ? ORDER BY DataConsulta");
            stmt.setObject(1, data+"%");
            stmt.setInt(2, codpsicologo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Consultas c = new Vw_Consultas();

                c.setCodConsulta(rs.getInt("CodigoConsulta"));
                c.getPaciente().setNome_Completo(rs.getString("Paciente"));
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
    
       public List<Vw_Consultas> ReadALLConsultas() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Consultas> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Consultas ORDER BY DataConsulta");
            
    
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Consultas c = new Vw_Consultas();

                c.setCodConsulta(rs.getInt("CodigoConsulta"));
                c.getPaciente().setNome_Completo(rs.getString("Paciente"));
                //c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.getPsicologo().setNome_completo(rs.getString("Psicologo"));
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
    public List<Vw_Consultas> BuscaALLConsultas(String Atributo, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Consultas> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoConsulta, Paciente, Psicologo, DataConsulta, Status FROM vw_Consultas WHERE "+Atributo+ " Like '%"+Busca+"%' Group By DataConsulta;";
            stmt = con.prepareStatement(sql);
            
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Consultas c = new Vw_Consultas();

                c.setCodConsulta(rs.getInt("CodigoConsulta"));
                c.getPaciente().setNome_Completo(rs.getString("Paciente"));
                //c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.getPsicologo().setNome_completo(rs.getString("Psicologo"));
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
    
    public List<Vw_TelefonesPacientes> ReadTelefonesPacientes() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonesPacientes Group By CodigoPaciente") ;
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPaciente().setEmail(rs.getString("Email"));
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
    
      public Vw_TelefonesPacientes ReadTelefonesPacientes(int codigopaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();
        try {
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonesPacientes where CodigoPaciente = ? Group By CodigoPaciente") ;
            stmt.setInt(1, codigopaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPaciente().setEmail(rs.getString("Email"));
                v.getTelefone().setNumero(rs.getString("Numero"));
   

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return v;
    }
    
    
     public List<Vw_Anamnese_Paciente> ReadAnamnesePaciente(int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anamnese_Paciente Where CodigoPsicologo = ?");
            stmt.setInt(1, codPsicologo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Anamnese_Paciente v = new Vw_Anamnese_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
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
     
    public List<Vw_Anamnese_Paciente> ReadAnamnesePaciente(int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anamnese_Paciente Where CodigoPsicologo = ? and CodigoPaciente = ?");
            stmt.setInt(1, codPsicologo);
            stmt.setInt(2, codPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Anamnese_Paciente v = new Vw_Anamnese_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
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
     
     
     
     public List<Vw_Anotacoes_Paciente> ReadAnotacoesPaciente(int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anotacoes_Paciente Where CodigoPsicologo = ?");
            stmt.setInt(1, codPsicologo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Anotacoes_Paciente v = new Vw_Anotacoes_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getAnotacao().setDataAnotacao(rs.getTimestamp("DataAnotacao"));
                v.getAnotacao().setAssunto(rs.getString("Assunto"));
                v.getAnotacao().setTexto(rs.getString("Texto"));
                v.getAnotacao().setCodAnotacao(rs.getInt("CodAnotacao"));
                
                vw.add(v);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    public List<Vw_Anotacoes_Paciente> ReadAnotacoesPaciente(int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anotacoes_Paciente Where CodigoPsicologo = ? and CodigoPaciente = ?");
            stmt.setInt(1, codPsicologo);
            stmt.setInt(2, codPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Anotacoes_Paciente v = new Vw_Anotacoes_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getAnotacao().setDataAnotacao(rs.getTimestamp("DataAnotacao"));
                v.getAnotacao().setAssunto(rs.getString("Assunto"));
                v.getAnotacao().setTexto(rs.getString("Texto"));
                v.getAnotacao().setCodAnotacao(rs.getInt("CodAnotacao"));
                
                vw.add(v);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    public List<Vw_TelefonesPacientes> BuscaManterPaciente( String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Telefone FROM vw_TelefonesPacientes WHERE (Paciente Like '%"+Busca+"%') OR (Email Like '%"+Busca+"%') OR (numero Like '%"+Busca+"%') Group By CodigoPaciente;";
            stmt = con.prepareStatement(sql);
            
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();
                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPaciente().setEmail(rs.getString("Email"));
                v.getTelefone().setNumero(rs.getString("Telefone"));
                
                vw.add(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
     public List<Vw_TelefonesPacientes> BuscaManterPacienteOA( String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Telefone FROM vw_TelefonesPacientes WHERE (Paciente Like '%"+Busca+"%') OR (Email Like '%"+Busca+"%') OR (numero Like '%"+Busca+"%') Group By Paciente;";
            stmt = con.prepareStatement(sql);
            
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();
                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPaciente().setEmail(rs.getString("Email"));
                v.getTelefone().setNumero(rs.getString("Telefone"));
                
                vw.add(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    
      public List<Vw_Anamnese_Paciente> BuscaExibirAnamneses( String Busca, int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE ((Paciente Like '%"+Busca+"%') OR (DataConsulta Like '%"+Busca+"%') OR (Diagnostico Like '%"+Busca+"%') ) and CodigoPsicologo = ? Group By CodigoPaciente;";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codPsicologo);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
             
                Vw_Anamnese_Paciente v = new Vw_Anamnese_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
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
      
      public List<Vw_Anamnese_Paciente> BuscaExibirAnamnesesOA( String Busca, int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE ((Paciente Like '%"+Busca+"%') OR (DataConsulta Like '%"+Busca+"%') OR (Diagnostico Like '%"+Busca+"%') ) and CodigoPsicologo = ? Group By Paciente;";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codPsicologo);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
             
                Vw_Anamnese_Paciente v = new Vw_Anamnese_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
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
      
    public List<Vw_Anamnese_Paciente> BuscaExibirAnamneses(String Atributo, String Busca, int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE "+Atributo+ " Like '%"+Busca+"%' and CodigoPsicologo = ? and CodigoPaciente = ? Group By CodigoPaciente;";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codPsicologo);
             stmt.setInt(2, codPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
             
                Vw_Anamnese_Paciente v = new Vw_Anamnese_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
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
      
    public List<Vw_Anotacoes_Paciente> BuscaExibirAnotacoes(String Atributo, String Busca, int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Assunto, DataAnotacao, CodAnotacao, texto FROM vw_Anotacoes_Paciente WHERE "+Atributo+ " Like '%"+Busca+"%' and CodigoPsicologo = ? Group By CodigoPaciente;";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codPsicologo);
            
            rs = stmt.executeQuery();

            while (rs.next()) {
             
                Vw_Anotacoes_Paciente v = new Vw_Anotacoes_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getAnotacao().setDataAnotacao(rs.getTimestamp("DataAnotacao"));
                v.getAnotacao().setAssunto(rs.getString("Assunto"));
                v.getAnotacao().setTexto(rs.getString("texto"));
                v.getAnotacao().setCodAnotacao(rs.getInt("CodAnotacao"));
                
                vw.add(v);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    
    
    public List<Vw_Anotacoes_Paciente> BuscaExibirAnotacoes(String Atributo, String Busca, int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Assunto, DataAnotacao, CodAnotacao, texto FROM vw_Anotacoes_Paciente WHERE "+Atributo+ " Like '%"+Busca+"%' and CodigoPsicologo = ? and CodigoPaciente = ? Group By CodigoPaciente;";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, codPsicologo);
            stmt.setInt(2, codPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
             
                Vw_Anotacoes_Paciente v = new Vw_Anotacoes_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getAnotacao().setDataAnotacao(rs.getTimestamp("DataAnotacao"));
                v.getAnotacao().setAssunto(rs.getString("Assunto"));
                v.getAnotacao().setTexto(rs.getString("texto"));
                v.getAnotacao().setCodAnotacao(rs.getInt("CodAnotacao"));
                
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
