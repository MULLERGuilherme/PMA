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
import model.bean.Paciente;
import model.bean.Psicologo;

/**
 *
 * @author User
 */
public class ConsultaDAO {

    public boolean Create(Consulta c) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO consulta (DataConsulta, Status, CodPaciente, CodPsicologo, Pagamento) VALUES (?, ?, ?, ?,?) ");
            stmt.setObject(1, c.getDataConsulta());
            stmt.setString(2, c.getStatus());
            stmt.setInt(3, c.getPaciente().getCodPaciente());
            stmt.setInt(4, c.getPsicologo().getCodPsicologo());
            stmt.setString(5, c.getPagamento());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public List<Consulta> Read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> consultas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM consulta");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();

                c.setCodConsulta(rs.getInt("CodConsulta"));
                c.getPaciente().setCodPaciente(rs.getInt("CodPaciente"));
                c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.setDataConsulta(rs.getObject("DataConsulta"));
                c.setStatus(rs.getString("Status"));

                consultas.add(c);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return consultas;
    }
    
    public Consulta ReadConsulta(int codconsulta) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       Consulta c = new Consulta();
        try {
            stmt = con.prepareStatement("SELECT * FROM consulta Where CodConsulta = ?");
            stmt.setInt(1, codconsulta);
            rs = stmt.executeQuery();

            while (rs.next()) {
                

                c.setCodConsulta(rs.getInt("CodConsulta"));
                c.getPaciente().setCodPaciente(rs.getInt("CodPaciente"));
                c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.setDataConsulta(rs.getTimestamp("DataConsulta"));
                c.setStatus(rs.getString("Status"));



            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return c;
    }
      public List<Consulta> ReadConsultas(Object data, int codpsicologo) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
       List<Consulta> consultas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM consulta Where DataConsulta Like ? And CodPsicologo = ? ORDER BY DataConsulta");
            stmt.setObject(1, data+"%");
            stmt.setInt(2, codpsicologo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();

                c.setCodConsulta(rs.getInt("CodConsulta"));
                c.getPaciente().setCodPaciente(rs.getInt("CodPaciente"));
                c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.setDataConsulta(rs.getObject("DataConsulta"));
                c.setStatus(rs.getString("Status"));

                consultas.add(c);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return consultas;
    }
      
    public List<Consulta> Read(Paciente p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> consultas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM consulta WHERE CodPaciente =?");
            stmt.setInt(1, p.getCodPaciente());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();

                c.setCodConsulta(rs.getInt("CodConsulta"));
                c.getPaciente().setCodPaciente(rs.getInt("CodPaciente"));
                c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.setDataConsulta(rs.getObject("DataConsulta"));
                c.setStatus(rs.getString("Status"));

                consultas.add(c);

            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return consultas;
    }
    
    

    public List<Consulta> Read(int CodPaciente) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> consultas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM consulta WHERE CodPaciente =?");
            stmt.setInt(1, CodPaciente);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();

                c.setCodConsulta(rs.getInt("CodConsulta"));
                c.getPaciente().setCodPaciente(rs.getInt("CodPaciente"));
                c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.setDataConsulta(rs.getObject("DataConsulta"));
                c.setStatus(rs.getString("Status"));

                consultas.add(c);

            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return consultas;
    }

    public List<Consulta> Read(Psicologo p) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Consulta> consultas = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM consulta WHERE CodPsicologo =?");
            stmt.setInt(1, p.getCodPsicologo());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();

                c.setCodConsulta(rs.getInt("CodConsulta"));
                c.getPaciente().setCodPaciente(rs.getInt("CodPaciente"));
                c.getPsicologo().setCodPsicologo(rs.getInt("CodPsicologo"));
                c.setDataConsulta(rs.getDate("DataConsulta"));
                c.setStatus(rs.getString("Status"));

                consultas.add(c);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return consultas;
    }

    public boolean Update(Consulta c) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("Update  consulta SET DataConsulta=?, Status=?, Pagamento=? WHERE CodConsulta =? ");

            //sem CPF pq nao faz sentido
            //stmt.setString(1,  p.getCPF());
            stmt.setObject(1, c.getDataConsulta());
            stmt.setString(2, c.getStatus());
            stmt.setString(3, c.getPagamento());
            stmt.setInt(4, c.getCodConsulta());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public boolean Delete(Consulta c) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Delete FROM consulta WHERE CodConsulta =? ");

            stmt.setInt(1, c.getCodConsulta());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public boolean DeleteConsultas(Paciente p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Delete FROM consulta WHERE CodPaciente =? ");

            stmt.setInt(1, p.getCodPaciente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    
     public boolean SoftDeleteConsultas(Paciente p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update consulta set Deletada = true WHERE CodPaciente =? ");

            stmt.setInt(1, p.getCodPaciente());

            stmt.executeUpdate();

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public boolean DeleteConsultas(Psicologo p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Delete FROM consulta WHERE CodPsicologo =? ");

            stmt.setInt(1, p.getCodPsicologo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    public boolean SoftDeleteConsultas(Psicologo p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update consulta set Deletada = true WHERE CodPsicologo =? ");

            stmt.setInt(1, p.getCodPsicologo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    
    public boolean RestaurarConsultas(int CodPsicologo) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update consulta set Deletada = false WHERE CodPsicologo =? ");

            stmt.setInt(1, CodPsicologo);

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    
    public boolean RestaurarConsultasPaciente(int CodPaciente) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update consulta set Deletada = false WHERE CodPaciente =? ");

            stmt.setInt(1, CodPaciente);

            stmt.executeUpdate();

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
}
