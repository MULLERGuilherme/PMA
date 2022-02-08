/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import model.bean.Psicologo;

/**
 *
 * @author User
 */
public class PsicologoDAO {
    
    public boolean Create(Psicologo p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO psicologo (CRP, Nome_Completo, Email, Login, Senha) VALUES (?, ?, ?, ?,MD5(?)) ");
            stmt.setString(1, p.getCRP());
            stmt.setString(2, p.getNome_completo());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getLogin());
            stmt.setString(5, p.getSenha());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao inserir :" + ex);
            status = false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public List<Psicologo> Read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Psicologo> psicologos = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM psicologo");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Psicologo p = new Psicologo();
                p.setCodPsicologo(rs.getInt("CodPsicologo"));
                p.setNome_completo(rs.getString("Nome_completo"));
                p.setCRP(rs.getString("CRP"));
                p.setEmail(rs.getString("Email"));
                p.setLogin(rs.getString("Login"));
                p.setSenha(rs.getString("Senha"));
                psicologos.add(p);

            }

        } catch (SQLException ex) {
            Logger.getLogger(PsicologoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return psicologos;
    }

    public boolean Update(Psicologo p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("Update  psicologo SET Nome_completo=?, Email=?, Login=?, Senha = MD5(?) WHERE CodPsicologo =? ");

            //sem CPF pq nao faz sentido
            //stmt.setString(1,  p.getCPF());
            stmt.setString(1, p.getNome_completo());
            stmt.setString(2, p.getEmail());
            stmt.setString(3, p.getLogin());
            stmt.setString(4, p.getSenha());
            stmt.setInt(5, p.getCodPsicologo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            status = false;
            JOptionPane.showMessageDialog(null, "Erro ao alterar :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    public boolean UpdateMP(Psicologo p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("Update  psicologo SET CRP = ?, Nome_completo=?, Email=?, Login=? WHERE CodPsicologo =? ");

            //sem CPF pq nao faz sentido
            stmt.setString(1,  p.getCRP());
            stmt.setString(2, p.getNome_completo());
            stmt.setString(3, p.getEmail());
            stmt.setString(4, p.getLogin());
           
            stmt.setInt(5, p.getCodPsicologo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            status = false;
            JOptionPane.showMessageDialog(null, "Erro ao alterar :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }
    public boolean UpdateUserSenha(Psicologo p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("Update  psicologo SET  Login=?, Senha = MD5(?) WHERE CodPsicologo =? ");

            //sem CPF pq nao faz sentido
            //stmt.setString(1,  p.getCPF());
       
            stmt.setString(1, p.getLogin());
            stmt.setString(2, p.getSenha());
            stmt.setInt(3, p.getCodPsicologo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            status = false;
            JOptionPane.showMessageDialog(null, "Erro ao alterar :" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public boolean Delete(Psicologo p) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Delete FROM psicologo WHERE CodPsicologo =? ");

            stmt.setInt(1, p.getCodPsicologo());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Excluir :" + ex);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public boolean ValidarLogin(Psicologo p, JFrame jframe) {

        Connection con = ConnectionFactory.getConnection(jframe);
        CallableStatement cstmt = null;
        ResultSet rs = null;
        boolean valido = false;
        try {

            cstmt = con.prepareCall("{? = call ValidarLogin(? , ?)}");

            cstmt.registerOutParameter(1, Types.BOOLEAN);
            cstmt.setString(2, p.getLogin());
            cstmt.setString(3, p.getSenha());
            cstmt.execute();

            valido = cstmt.getBoolean(1);

        } catch (SQLException ex) {
            Logger.getLogger(PsicologoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            ConnectionFactory.closeConnection(con, cstmt, rs);
        }

        return valido;
    }

    public Psicologo ReadPsicologoLS(String Login, String Senha) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Psicologo p = new Psicologo();
        try {
            stmt = con.prepareStatement("SELECT * FROM psicologo WHERE Login =? AND Senha =MD5(?)");
            stmt.setString(1, Login);
            stmt.setString(2, Senha);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    p.setNome_completo(rs.getString("Nome_completo"));
                    p.setCRP(rs.getString("CRP"));
                    p.setEmail(rs.getString("Email"));
                    p.setCodPsicologo(rs.getInt("CodPsicologo"));
                    p.setLogin(Login);
                    p.setSenha(Senha);

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PsicologoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return p;
    }

    public Psicologo ReadPsicologo(String CRP) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Psicologo p = new Psicologo();
        try {
            stmt = con.prepareStatement("SELECT * FROM psicologo WHERE CRP =?");
            stmt.setString(1, CRP);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    p.setCodPsicologo(rs.getInt("CodPsicologo"));
                    p.setNome_completo(rs.getString("Nome_completo"));
                    p.setCRP(rs.getString("CRP"));
                    p.setEmail(rs.getString("Email"));
                    p.setLogin(rs.getString("Login"));
                    p.setSenha(rs.getString("Senha"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PsicologoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return p;
    }
    
     
    public Psicologo ReadPsicologo(int codpsicologo  ) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Psicologo p = new Psicologo();
        try {
            stmt = con.prepareStatement("SELECT * FROM psicologo WHERE CodPsicologo = ?");
            stmt.setInt(1, codpsicologo);
            
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    p.setCodPsicologo(rs.getInt("CodPsicologo"));
                    p.setNome_completo(rs.getString("Nome_completo"));
                    p.setCRP(rs.getString("CRP"));
                    p.setEmail(rs.getString("Email"));
                    p.setLogin(rs.getString("Login"));
                   
                } 
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(PsicologoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return p;
    }
public Psicologo ReadPsicologo(String CRP, String Email) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Psicologo p = new Psicologo();
        try {
            stmt = con.prepareStatement("SELECT * FROM psicologo WHERE CRP =? and Email = ?");
            stmt.setString(1, CRP);
            stmt.setString(2, Email);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    p.setCodPsicologo(rs.getInt("CodPsicologo"));
                    p.setNome_completo(rs.getString("Nome_completo"));
                    p.setCRP(rs.getString("CRP"));
                    p.setEmail(rs.getString("Email"));
                    p.setLogin(rs.getString("Login"));
                    p.setSenha(rs.getString("Senha"));
                } 
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(PsicologoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return p;
    }
}
