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
import model.bean.Vw_Anamnese_Paciente;
import model.bean.Vw_Anotacoes_Paciente;
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
            stmt = con.prepareStatement("SELECT CodigoPsicologo, Psicologo, Email,CRP, GROUP_CONCAT(numero) as Telefone, PsicologoDeletado FROM vw_TelefonePsicologos Group By CodigoPsicologo");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPsicologos v = new Vw_TelefonesPsicologos();

                v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getPsicologo().setEmail(rs.getString("Email"));
                v.getPsicologo().setCRP(rs.getString("CRP"));
                v.getTelefone().setNumero(rs.getString("Telefone"));
                v.getPsicologo().setDeletado(rs.getBoolean("PsicologoDeletado"));
                vw.add(v);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_TelefonesPsicologos> BuscaManterPsicologo( String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPsicologos> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPsicologo, Psicologo, Email,CRP, GROUP_CONCAT(numero) as Telefone, PsicologoDeletado FROM vw_TelefonePsicologos WHERE ((Psicologo Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (Telefone Like '%" + Busca + "%')) Group By CodigoPsicologo;";
            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPsicologos v = new Vw_TelefonesPsicologos();
                v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getPsicologo().setEmail(rs.getString("Email"));
                v.getPsicologo().setCRP(rs.getString("CRP"));
                v.getTelefone().setNumero(rs.getString("Telefone"));
                v.getPsicologo().setDeletado(rs.getBoolean("PsicologoDeletado"));

                vw.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
 public List<Vw_TelefonesPsicologos> BuscaManterPsicologoOA( String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPsicologos> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPsicologo, Psicologo, Email,CRP, GROUP_CONCAT(numero) as Telefone FROM vw_TelefonePsicologos WHERE ((Psicologo Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (numero Like '%" + Busca + "%')) Group By CodigoPsicologo;";
            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPsicologos v = new Vw_TelefonesPsicologos();
                v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getPsicologo().setEmail(rs.getString("Email"));
                v.getPsicologo().setCRP(rs.getString("CRP"));
                v.getTelefone().setNumero(rs.getString("Telefone"));

                vw.add(v);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
            stmt.setObject(1, data + "%");

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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE " + Atributo + " Like '%" + Busca + "%'  Group By CodigoPaciente;";
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anotacoes_Paciente> ReadALLAnotacoes() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anotacoes_Paciente");

            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Anotacoes_Paciente v = new Vw_Anotacoes_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getAnotacao().setDataAnotacao(rs.getTimestamp("DataAnotacao"));
                v.getAnotacao().setAssunto(rs.getString("Assunto"));
                v.getAnotacao().setTexto(rs.getString("Texto"));
                v.getAnotacao().setCodAnotacao(rs.getInt("CodAnotacao"));

                vw.add(v);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anotacoes_Paciente> BuscaALLAnotacoes(String Atributo, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente,CodigoPsicologo, Psicologo, Paciente, Assunto, DataAnotacao, CodAnotacao, texto FROM vw_Anotacoes_Paciente WHERE " + Atributo + " Like '%" + Busca + "%'  Group By CodigoPaciente;";
            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {

                Vw_Anotacoes_Paciente v = new Vw_Anotacoes_Paciente();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPsicologo().setCodPsicologo(rs.getInt("CodigoPsicologo"));
                v.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                v.getAnotacao().setDataAnotacao(rs.getTimestamp("DataAnotacao"));
                v.getAnotacao().setAssunto(rs.getString("Assunto"));
                v.getAnotacao().setTexto(rs.getString("texto"));
                v.getAnotacao().setCodAnotacao(rs.getInt("CodAnotacao"));

                vw.add(v);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    
    
    
      //Paginacao ManterPsicologos ADM deletados
     public List<Vw_TelefonesPsicologos> fetchBySizeMPsiAdm(int start, int size) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPsicologos> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPsicologo, Psicologo, CRP, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonePsicologos WHERE PsicologoDeletado = true Group By CodigoPsicologo order by Psicologo Limit " + start + "," + size);
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

    public List<Vw_TelefonesPsicologos> fetchBySizeBuscaMPsiAdm(int start, int size, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPsicologos> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPsicologo, Psicologo, CRP, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonePsicologos WHERE ((Psicologo Like '%" + Busca + "%') OR (CRP Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (Numero Like '%" + Busca + "%')) AND PsicologoDeletado = true  Group By CodigoPsicologo order by Psicologo Limit " + size + " OFFSET " + start);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public double getRowCountTableManterPsiAdm() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoPsicologo) from vw_TelefonePsicologos  WHERE PsicologoDeletado = true ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoPsicologo)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public double getRowCountTableManterPsiAdmBusca(String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoPsicologo) from vw_TelefonePsicologos WHERE ((Psicologo Like '%" + Busca + "%') OR (CRP Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (numero Like '%" + Busca + "%')) and PsicologoDeletado = true ");

            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoPsicologo)");

            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }
    
    
    
          //Paginacao ManterPsicologos ADM ativos
     public List<Vw_TelefonesPsicologos> fetchBySizeMPsi(int start, int size) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPsicologos> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPsicologo, Psicologo, CRP, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonePsicologos WHERE PsicologoDeletado = false Group By CodigoPsicologo order by Psicologo Limit " + start + "," + size);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_TelefonesPsicologos> fetchBySizeBuscaMPsi(int start, int size, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPsicologos> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPsicologo, Psicologo, CRP, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonePsicologos WHERE ((Psicologo Like '%" + Busca + "%') OR (CRP Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (Numero Like '%" + Busca + "%')) AND PsicologoDeletado = false  Group By CodigoPsicologo order by Psicologo Limit " + size + " OFFSET " + start);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public double getRowCountTableManterPsi() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoPsicologo) from vw_TelefonePsicologos  WHERE PsicologoDeletado = false ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoPsicologo)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public double getRowCountTableManterPsiAdm(String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoPsicologo) from vw_TelefonePsicologos WHERE ((Psicologo Like '%" + Busca + "%') OR (CRP Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (numero Like '%" + Busca + "%')) and PsicologoDeletado = false ");

            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoPsicologo)");

            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }
    
    
    
    //Paginacao Consultas ADM
    
     public List<Vw_Consultas> fetchBySizeConsultasADM(int start, int size, boolean deletada, Object dinicio, Object dfim) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Consultas> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Consultas WHERE Deletada = ? AND (DataConsulta Between ? AND ?) ORDER BY DataConsulta Limit " + start + "," + size);
            stmt.setBoolean(1, deletada);
            stmt.setObject(2, dinicio);
            stmt.setObject(3, dfim);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Consultas c = new Vw_Consultas();

                c.setCodConsulta(rs.getInt("CodigoConsulta"));
                c.getPaciente().setNome_Completo(rs.getString("Paciente"));
                //c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                c.setDataConsulta(rs.getObject("DataConsulta"));
                c.setStatus(rs.getString("Status"));
                c.setPagamento(rs.getString("Pagamento"));

                vw.add(c);

            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Consultas> fetchBySizeConsultasAdmBusca(int start, int size, String Busca, boolean deletada, Object dinicio, Object dfim) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Consultas> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Consultas WHERE Deletada =? AND (DataConsulta Between ? AND ?) AND ((Paciente Like '%" + Busca + "%') OR (Psicologo Like '%" + Busca + "%')  OR (DataConsulta Like '%" + Busca + "%') OR (Status Like '%" + Busca + "%')) Order By DataConsulta Limit " + size + " OFFSET " + start);
            stmt.setBoolean(1, deletada);
             stmt.setObject(2, dinicio);
            stmt.setObject(3, dfim);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_Consultas c = new Vw_Consultas();

                c.setCodConsulta(rs.getInt("CodigoConsulta"));
                c.getPaciente().setNome_Completo(rs.getString("Paciente"));
                //c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.getPsicologo().setNome_completo(rs.getString("Psicologo"));
                c.setDataConsulta(rs.getObject("DataConsulta"));
                c.setStatus(rs.getString("Status"));
                c.setPagamento(rs.getString("Pagamento"));

                vw.add(c);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public double getRowCountConsultasADM(boolean deletada, Object dinicio, Object dfim) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoConsulta) FROM vw_Consultas  WHERE Deletada = ? AND (DataConsulta Between ? AND ?)");
            stmt.setBoolean(1, deletada);
            stmt.setObject(2, dinicio);
            stmt.setObject(3, dfim);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoConsulta)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public double getRowCountTableConsultasADMBusca(String Busca, boolean deletada, Object dinicio, Object dfim) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoConsulta) from vw_Consultas WHERE Deletada = ? AND (DataConsulta Between ? AND ?) AND ((Paciente Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%') OR (Psicologo Like '%" + Busca + "%') OR (Status Like '%" + Busca + "%')) ");
            stmt.setBoolean(1, deletada);
            stmt.setObject(2, dinicio);
            stmt.setObject(3, dfim);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoConsulta)");

            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }
}
