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
    
    
    public List<Vw_TelefonesPacientes> ReadTelefonesPacientes() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Vw_TelefonesPacientes> vw = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM vw_Telefonepacientes");
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
}
