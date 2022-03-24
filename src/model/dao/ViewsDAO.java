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
            stmt.setObject(1, data + "%");
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
            String sql = "SELECT CodigoConsulta, Paciente, Psicologo, DataConsulta, Status FROM vw_Consultas WHERE " + Atributo + " Like '%" + Busca + "%' Group By DataConsulta;";
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonesPacientes  WHERE PacienteDeletado = false Group By CodigoPaciente");
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    
        public List<Vw_TelefonesPacientes> ReadTelefonesPacientesADM() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero, PacienteDeletado FROM vw_TelefonesPacientes   Group By CodigoPaciente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPaciente().setEmail(rs.getString("Email"));
                v.getTelefone().setNumero(rs.getString("Numero"));
                v.getPaciente().setDeletado(rs.getBoolean("PacienteDeletado"));
                vw.add(v);

            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_TelefonesPacientes> fetchBySizeMP(int start, int size) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonesPacientes WHERE PacienteDeletado = false Group By CodigoPaciente order by Paciente Limit " + start + "," + size);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_TelefonesPacientes> fetchBySizeBuscaMP(int start, int size, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonesPacientes WHERE ((Paciente Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (Numero Like '%" + Busca + "%')) AND PacienteDeletado = false  Group By CodigoPaciente order by Paciente Limit " + size + " OFFSET " + start);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public double getRowCountTableManterPacientes() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoPaciente) from vw_TelefonesPacientes  WHERE PacienteDeletado = false ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoPaciente)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }
    
    

    public double getRowCountTableManterPacientesBusca(String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoPaciente) from vw_TelefonesPacientes WHERE ((Paciente Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (numero Like '%" + Busca + "%')) and PacienteDeletado = false ");

            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoPaciente)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }
    
    //Paginacao ManterPaciente ADM deletados
     public List<Vw_TelefonesPacientes> fetchBySizeMPAdm(int start, int size) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonesPacientes WHERE PacienteDeletado = true Group By CodigoPaciente order by Paciente Limit " + start + "," + size);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_TelefonesPacientes> fetchBySizeBuscaMPAdm(int start, int size, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonesPacientes WHERE ((Paciente Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (Numero Like '%" + Busca + "%')) AND PacienteDeletado = true  Group By CodigoPaciente order by Paciente Limit " + size + " OFFSET " + start);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public double getRowCountTableManterPacientesAdm() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoPaciente) from vw_TelefonesPacientes  WHERE PacienteDeletado = true ");
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoPaciente)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public double getRowCountTableManterPacientesAdmBusca(String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoPaciente) from vw_TelefonesPacientes WHERE ((Paciente Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (numero Like '%" + Busca + "%')) and PacienteDeletado = true ");

            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodigoPaciente)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public Vw_TelefonesPacientes ReadTelefonesPacientes(int codigopaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();
        try {
            stmt = con.prepareStatement("SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Numero FROM vw_TelefonesPacientes where CodigoPaciente = ? Group By CodigoPaciente");
            stmt.setInt(1, codigopaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {

                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPaciente().setEmail(rs.getString("Email"));
                v.getTelefone().setNumero(rs.getString("Numero"));

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
          JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_TelefonesPacientes> BuscaManterPaciente(String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Telefone FROM vw_TelefonesPacientes WHERE (Paciente Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (numero Like '%" + Busca + "%') Group By CodigoPaciente;";
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
        public List<Vw_TelefonesPacientes> BuscaManterPacienteADMOA(String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Telefone, PacienteDeletado FROM vw_TelefonesPacientes WHERE (Paciente Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (numero Like '%" + Busca + "%') Group By Paciente;";
            stmt = con.prepareStatement(sql);

            rs = stmt.executeQuery();

            while (rs.next()) {
                Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();
                v.getPaciente().setCodPaciente(rs.getInt("CodigoPaciente"));
                v.getPaciente().setNome_Completo(rs.getString("Paciente"));
                v.getPaciente().setEmail(rs.getString("Email"));
                v.getTelefone().setNumero(rs.getString("Telefone"));
                v.getPaciente().setDeletado(rs.getBoolean("PacienteDeletado"));

                vw.add(v);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    public List<Vw_TelefonesPacientes> BuscaManterPacienteOA(String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Email, GROUP_CONCAT(numero) as Telefone FROM vw_TelefonesPacientes WHERE (Paciente Like '%" + Busca + "%') OR (Email Like '%" + Busca + "%') OR (numero Like '%" + Busca + "%') Group By Paciente;";
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anamnese_Paciente> BuscaExibirAnamneses(String Busca, int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE ((Paciente Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%') OR (Diagnostico Like '%" + Busca + "%') ) and CodigoPsicologo = ? Group By CodigoPaciente;";
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anamnese_Paciente> BuscaExibirAnamnesesOA(String Busca, int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE ((Paciente Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%') OR (Diagnostico Like '%" + Busca + "%') ) and CodigoPsicologo = ? Group By Paciente;";
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anamnese_Paciente> BuscaExibirAnamneses(String Busca, int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE ((Paciente Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%') OR (Diagnostico Like '%" + Busca + "%') ) and CodigoPsicologo = ? and CodigoPaciente = ? Group By CodigoPaciente;";
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anamnese_Paciente> BuscaExibirAnamnesesOA(String Busca, int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Diagnostico, DataConsulta, CodAnamnese FROM vw_Anamnese_Paciente WHERE ((Paciente Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%') OR (Diagnostico Like '%" + Busca + "%') ) and CodigoPsicologo = ? and CodigoPaciente = ? Group By Paciente;";
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anotacoes_Paciente> BuscaExibirAnotacoes(String Busca, int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Assunto, DataAnotacao, CodAnotacao, texto FROM vw_Anotacoes_Paciente WHERE ( Paciente Like '%" + Busca + "%' OR DataAnotacao Like '%" + Busca + "%' OR Assunto Like '%" + Busca + "%') and CodigoPsicologo = ? Group By CodigoPaciente;";
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anotacoes_Paciente> BuscaExibirAnotacoesOA(String Busca, int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            String sql = "SELECT CodigoPaciente, Paciente, Assunto, DataAnotacao, CodAnotacao, texto FROM vw_Anotacoes_Paciente WHERE ( Paciente Like '%" + Busca + "%' OR DataAnotacao Like '%" + Busca + "%' OR Assunto Like '%" + Busca + "%') and CodigoPsicologo = ? Group By CodigoPaciente;";
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
            String sql = "SELECT CodigoPaciente, Paciente, Assunto, DataAnotacao, CodAnotacao, texto FROM vw_Anotacoes_Paciente WHERE " + Atributo + " Like '%" + Busca + "%' and CodigoPsicologo = ? and CodigoPaciente = ? Group By CodigoPaciente;";
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    //Paginacao Exibir Anamneses
    public double getRowCountTableExibirAnamneses(int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodAnamnese) FROM vw_Anamnese_Paciente Where CodigoPsicologo = ? AND PacienteDeletado = false");
            stmt.setInt(1, codPsicologo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodAnamnese)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public double getRowCountTableExibirAnamnesesBusca(int codPsicologo, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodAnamnese) FROM vw_Anamnese_Paciente WHERE CodigoPsicologo = ? AND PacienteDeletado = false AND ((Paciente Like '%" + Busca + "%') OR (Diagnostico Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%'))");
            stmt.setInt(1, codPsicologo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodAnamnese)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public List<Vw_Anamnese_Paciente> fetchBySizeExibirAnamneses(int codPsicologo, int start, int size) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anamnese_Paciente Where CodigoPsicologo = ? AND PacienteDeletado = false  Group By CodAnamnese Limit " + size + " OFFSET " + start);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anamnese_Paciente> fetchBySizeExibirAnamnesesBusca(int codPsicologo, int start, int size, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anamnese_Paciente Where CodigoPsicologo = ? AND PacienteDeletado = false And ((Paciente Like '%" + Busca + "%') OR (Diagnostico Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%')) Group By CodAnamnese Limit " + size + " OFFSET " + start);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    //Paginacao Exibir Anotacoes
    public double getRowCountTableExibirAnotacoes(int codPsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodAnotacao) FROM vw_Anotacoes_Paciente Where CodigoPsicologo = ? AND PacienteDeletado = false ");
            stmt.setInt(1, codPsicologo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodAnotacao)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public double getRowCountTableExibirAnotacoesBusca(int codPsicologo, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodAnotacao) FROM vw_Anotacoes_Paciente WHERE CodigoPsicologo = ? AND PacienteDeletado = false  AND ((Paciente Like '%" + Busca + "%') OR (Assunto Like '%" + Busca + "%') OR (DataAnotacao Like '%" + Busca + "%'))");
            stmt.setInt(1, codPsicologo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodAnotacao)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public List<Vw_Anotacoes_Paciente> fetchBySizeExibirAnotacoes(int codPsicologo, int start, int size) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anotacoes_Paciente Where CodigoPsicologo = ? AND PacienteDeletado = false  Group By CodAnotacao Limit " + size + " OFFSET " + start);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anotacoes_Paciente> fetchBySizeExibirAnotacoesBusca(int codPsicologo, int start, int size, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anotacoes_Paciente Where CodigoPsicologo = ? AND PacienteDeletado = false  AND ((Paciente Like '%" + Busca + "%') OR (Assunto Like '%" + Busca + "%') OR (DataAnotacao Like '%" + Busca + "%')) Group By CodAnotacao Limit " + size + " OFFSET " + start);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    //Paginacao Exibir Anamneses através de Pacientes
    public double getRowCountTableExibirAnamnesesPaciente(int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodAnamnese) FROM vw_Anamnese_Paciente Where CodigoPsicologo = ? AND CodigoPaciente=?");
            stmt.setInt(1, codPsicologo);
            stmt.setInt(2, codPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodAnamnese)");

            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public double getRowCountTableExibirAnamnesesPacienteBusca(int codPsicologo, int codPaciente, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodAnamnese) FROM vw_Anamnese_Paciente WHERE CodigoPsicologo = ? AND CodigoPaciente = ? AND ((Paciente Like '%" + Busca + "%') OR (Diagnostico Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%'))");
            stmt.setInt(1, codPsicologo);
            stmt.setInt(2, codPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodAnamnese)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public List<Vw_Anamnese_Paciente> fetchBySizeExibirAnamnesesPaciente(int codPsicologo, int codPaciente, int start, int size) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anamnese_Paciente Where CodigoPsicologo = ? AND CodigoPaciente = ? Group By CodAnamnese Limit " + size + " OFFSET " + start);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anamnese_Paciente> fetchBySizeExibirAnamnesesPacienteBusca(int codPsicologo,int codPaciente, int start, int size, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anamnese_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anamnese_Paciente Where CodigoPsicologo = ? AND CodigoPaciente = ? And ((Paciente Like '%" + Busca + "%') OR (Diagnostico Like '%" + Busca + "%') OR (DataConsulta Like '%" + Busca + "%')) Group By CodAnamnese Limit " + size + " OFFSET " + start);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    
    //Paginacao Visualizar Consultas Paciente
    
    public double getRowCountTableExibirConsultasPaciente(int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodigoConsulta) FROM vw_Consultas Where CodigoPsicologo = ? AND CodigoPaciente=?");
            stmt.setInt(1, codPsicologo);
            stmt.setInt(2, codPaciente);
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

   
    public List<Vw_Consultas> fetchBySizeExibirConsultasPaciente(int codpaciente, int codpsicologo, int start, int size) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Consultas> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Consultas where CodigoPsicologo = ? and CodigoPaciente = ? ORDER BY DataConsulta LIMIT "+size+" OFFSET "+start );
            stmt.setInt(1, codpsicologo);
            stmt.setInt(2, codpaciente);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
    
    
    
    public double getRowCountTableVisualizarAnotacoes(int codPsicologo, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodAnotacao) FROM vw_Anotacoes_Paciente Where CodigoPsicologo = ? AND PacienteDeletado = false AND CodigoPaciente = ?");
            stmt.setInt(1, codPsicologo);
            stmt.setInt(2, codPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodAnotacao)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public double getRowCountTableVisualizarAnotacoesBusca(int codPsicologo, String Busca, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        long count = 0;
        try {
            stmt = con.prepareStatement("SELECT count(Distinct CodAnotacao) FROM vw_Anotacoes_Paciente WHERE CodigoPsicologo = ? AND CodigoPaciente = ? AND PacienteDeletado = false  AND ((Paciente Like '%" + Busca + "%') OR (Assunto Like '%" + Busca + "%') OR (DataAnotacao Like '%" + Busca + "%'))");
            stmt.setInt(1, codPsicologo);
            stmt.setInt(2, codPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                count = rs.getLong("count(Distinct CodAnotacao)");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return count;
    }

    public List<Vw_Anotacoes_Paciente> fetchBySizeVisualizarAnotacoes(int codPsicologo, int start, int size, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anotacoes_Paciente Where CodigoPsicologo = ? AND CodigoPaciente = ? AND PacienteDeletado = false  Group By CodAnotacao Limit " + size + " OFFSET " + start);
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
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }

    public List<Vw_Anotacoes_Paciente> fetchBySizeVisualizarAnotacoesBusca(int codPsicologo, int start, int size, String Busca, int codPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_Anotacoes_Paciente> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Anotacoes_Paciente Where CodigoPsicologo = ? AND CodigoPaciente = ? AND PacienteDeletado = false  AND ((Paciente Like '%" + Busca + "%') OR (Assunto Like '%" + Busca + "%') OR (DataAnotacao Like '%" + Busca + "%')) Group By CodAnotacao Limit " + size + " OFFSET " + start);
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
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return vw;
    }
}
