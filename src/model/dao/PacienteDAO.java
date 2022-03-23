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
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Paciente;

/**
 *
 * @author User
 */
public class PacienteDAO {

    public boolean Create(Paciente p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO paciente (CPF, Nome_Completo, Email, EstadoCivil,DataNasc, Sexo, Profissao, Religiao, Escolaridade, Endereco, Cidade, UF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ");
            if (p.getCPF().isEmpty()) {
                stmt.setNull(1, Types.NULL);
            } else {
                stmt.setString(1, p.getCPF());
            }

            stmt.setString(2, p.getNome_Completo());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getEstadoCivil());
//            Mudanças

            stmt.setObject(5, p.getDataNasc());
            stmt.setString(6, p.getSexo());

            if (p.getProfissao().isEmpty()) {
                stmt.setNull(7, Types.NULL);
            } else {
                stmt.setString(7, p.getProfissao());
            }

            if (p.getReligiao().isEmpty()) {
                stmt.setNull(8, Types.NULL);
            } else {
                stmt.setString(8, p.getReligiao());
            }
            
            if (p.getEscolaridade().isEmpty()) {
                stmt.setNull(9, Types.NULL);
            } else {
                stmt.setString(9, p.getEscolaridade());
            }
              if (p.getEndereco().isEmpty()) {
                stmt.setNull(10, Types.NULL);
            } else {
                stmt.setString(10, p.getEndereco());
            }
              if (p.getCidade().isEmpty()) {
                stmt.setString(11, "Ponta Grossa");
            } else {
                stmt.setString(11, p.getCidade());
            }
            if (p.getUF().isEmpty()) {
                stmt.setString(12, "PR");
            } else {
                stmt.setString(12, p.getUF());
            }

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);

        }
        return status;
    }

    public Paciente ReadPaciente(String CPF) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Paciente p = new Paciente();
        try {
            stmt = con.prepareStatement("SELECT * FROM paciente WHERE CPF =?");
            stmt.setString(1, CPF);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    p.setCodPaciente(rs.getInt("CodPaciente"));
                    p.setNome_Completo(rs.getString("Nome_Completo"));
                    p.setCPF(rs.getString("CPF"));
                    p.setEmail(rs.getString("Email"));
                    p.setEstadoCivil(rs.getString("EstadoCivil"));
                    p.setDataNasc(rs.getObject("DataNasc"));
                    p.setSexo(rs.getString("Sexo"));
                    p.setProfissao(rs.getString("Profissao"));
                    p.setReligiao(rs.getString("Religiao"));
                    p.setEscolaridade(rs.getString("Escolaridade"));
                    p.setEndereco(rs.getString("Endereco"));
                    p.setCidade(rs.getString("Cidade"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return p;
    }
    
     public Paciente ReadPaciente(int CodPaciente) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Paciente p = new Paciente();
        try {
            stmt = con.prepareStatement("SELECT * FROM paciente WHERE CodPaciente =?");
            stmt.setInt(1, CodPaciente);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    p.setCodPaciente(rs.getInt("CodPaciente"));
                    p.setNome_Completo(rs.getString("Nome_Completo"));
                    p.setCPF(rs.getString("CPF"));
                    p.setEmail(rs.getString("Email"));
                    p.setEstadoCivil(rs.getString("EstadoCivil"));
                    p.setDataNasc(rs.getObject("DataNasc"));
                    p.setSexo(rs.getString("Sexo"));
                    p.setProfissao(rs.getString("Profissao"));
                    p.setReligiao(rs.getString("Religiao"));
                    p.setEscolaridade(rs.getString("Escolaridade"));
                    p.setEndereco(rs.getString("Endereco"));
                    p.setCidade(rs.getString("Cidade"));
                    p.setDeletado(rs.getBoolean("Deletado"));
                    p.setUF(rs.getString("UF"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return p;
    }

    public boolean Update(Paciente p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
//            mudança
            stmt = con.prepareStatement("Update  paciente SET CPF=?, Nome_Completo=?, Email=?, EstadoCivil=?, DataNasc=?, Sexo=?, Profissao=?, Religiao=?, Escolaridade=?, Endereco=?, Cidade=?, Deletado=?, UF=?  WHERE CodPaciente =? ");

            //sem CPF pq nao faz sentido
            //stmt.setString(1,  p.getCPF());
            if (p.getCPF().isEmpty()) {
                stmt.setNull(1, Types.NULL);
            } else {
                stmt.setString(1, p.getCPF());
            }

            stmt.setString(2, p.getNome_Completo());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getEstadoCivil());
//            Mudanças

            stmt.setObject(5, p.getDataNasc());
            stmt.setString(6, p.getSexo());

            if (p.getProfissao().isEmpty()) {
                stmt.setNull(7, Types.NULL);
            } else {
                stmt.setString(7, p.getProfissao());
            }

            if (p.getReligiao().isEmpty()) {
                stmt.setNull(8, Types.NULL);
            } else {
                stmt.setString(8, p.getReligiao());
            }
            
            if (p.getEscolaridade().isEmpty()) {
                stmt.setNull(9, Types.NULL);
            } else {
                stmt.setString(9, p.getEscolaridade());
            }
              if (p.getEndereco().isEmpty()) {
                stmt.setNull(10, Types.NULL);
            } else {
                stmt.setString(10, p.getEndereco());
            }
              if (p.getCidade().isEmpty()) {
                stmt.setString(11, "Ponta Grossa");
            } else {
                stmt.setString(11, p.getCidade());
            }
            stmt.setBoolean(12, p.isDeletado());
            
              if (p.getUF().isEmpty()) {
                stmt.setString(13, "PR");
            } else {
                stmt.setString(13, p.getUF());
            }
            stmt.setInt(14, p.getCodPaciente());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar :" +ex.getMessage());
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    public boolean SoftUpdate(Paciente p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
//            mudança
            stmt = con.prepareStatement("Update  paciente set Deletado=?  WHERE CodPaciente =? ");

          
            stmt.setBoolean(1, p.isDeletado());
            stmt.setInt(2, p.getCodPaciente());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar :" +ex.getMessage());
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    
    
    public boolean RestaurarPaciente(int CodPaciente) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
//            mudança
            stmt = con.prepareStatement("Update  paciente set Deletado= false  WHERE CodPaciente =? ");

          
           
            stmt.setInt(1, CodPaciente);
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar :" +ex.getMessage());
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public List<Paciente> Read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM paciente");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setCodPaciente(rs.getInt("CodPaciente"));
                p.setNome_Completo(rs.getString("Nome_Completo"));
                p.setCPF(rs.getString("CPF"));
                p.setEmail(rs.getString("Email"));
                p.setEstadoCivil(rs.getString("EstadoCivil"));
                p.setDataNasc(rs.getObject("DataNasc"));
                p.setSexo(rs.getString("Sexo"));
                p.setProfissao(rs.getString("Profissao"));
                p.setReligiao(rs.getString("Religiao"));
                p.setEscolaridade(rs.getString("Escolaridade"));
                p.setEndereco(rs.getString("Endereco"));
                p.setCidade(rs.getString("Cidade"));
                pacientes.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pacientes;
    }
    
     public List<Paciente> Busca(String Atributo, String Busca) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Paciente> pacientes = new ArrayList<>();
        try {
            String sql = "SELECT * FROM paciente WHERE "+Atributo+ " Like '%"+Busca+"%';";
            stmt = con.prepareStatement(sql);
            
            
            rs = stmt.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setCodPaciente(rs.getInt("CodPaciente"));
                p.setNome_Completo(rs.getString("Nome_Completo"));
                p.setCPF(rs.getString("CPF"));
                p.setEmail(rs.getString("Email"));
                p.setEstadoCivil(rs.getString("EstadoCivil"));
                p.setDataNasc(rs.getObject("DataNasc"));
                p.setSexo(rs.getString("Sexo"));
                p.setProfissao(rs.getString("Profissao"));
                p.setReligiao(rs.getString("Religiao"));
                p.setEscolaridade(rs.getString("Escolaridade"));
                p.setEndereco(rs.getString("Endereco"));
                p.setCidade(rs.getString("Cidade"));
                pacientes.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return pacientes;
    }

    public boolean Delete(Paciente p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update paciente set Deletado = true WHERE CodPaciente =? ");

            stmt.setInt(1, p.getCodPaciente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            status = false;
                  JOptionPane.showMessageDialog(null, "Erro ao Excluir :" +ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    
    public boolean SoftDelete(Paciente p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update paciente set Deletado = true WHERE CodPaciente =? ");

            stmt.setInt(1, p.getCodPaciente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            status = false;
                  JOptionPane.showMessageDialog(null, "Erro ao Excluir :" +ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

}
