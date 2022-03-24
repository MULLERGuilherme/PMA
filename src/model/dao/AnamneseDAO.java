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
import model.bean.Psicologo;

/**
 *
 * @author User
 */
public class AnamneseDAO {

    public boolean Create(Anamnese a) {

        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO anamnese (QueixaPrincipal, SubitaOuProgressiva, InicioDaQueixa, QueixasSecundarias, HistoricoFamiliar, Diagnostico, Encaminhamento, DoencasConhecidas, MedicamentosUtilizados, CodConsulta, OqueMudou, Sintomas, ComoComecou,QCIntegridadeSensorial,QCPercepcao,QCAtencao,QCMemoria,QAEVolicao,QAEAfeto,QAEAnsiedade,QAEMedo,QAECulpa,QAERaiva,QAELuto,QAEDesanimo , Psicomotricidade,QAEHumor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?) ");
            stmt.setString(1, a.getQueixaPrincipal());
            stmt.setString(2, a.getSubitaOuProgressiva());
            stmt.setObject(3, a.getInicioDaQueixa());
            stmt.setString(4, a.getQueixasSecundarias());
            stmt.setString(5, a.getHistoricoFamiliar());
            stmt.setString(6, a.getDiagnostico());
            stmt.setString(7, a.getEncaminhamento());
            stmt.setString(8, a.getDoencasConhecidas());
            stmt.setString(9, a.getMedicamentosUtilizados());
            stmt.setInt(10, a.getConsulta().getCodConsulta());
//            Mudancas
            stmt.setString(11, a.getOqueMudou());
            stmt.setString(12, a.getSintomas());
            stmt.setString(13, a.getComoComecou());
            stmt.setBoolean(14, a.isQCIntegridadeSensorial());
            stmt.setBoolean(15, a.isQCPercepcao());
            stmt.setBoolean(16, a.isQCAtencao());
            stmt.setBoolean(17, a.isQCMemoria());
            stmt.setBoolean(18, a.isQAEVolicao());
            stmt.setBoolean(19, a.isQAEAfeto());
            stmt.setBoolean(20, a.isQAEAnsiedade());
            stmt.setBoolean(21, a.isQAEMedo());
            stmt.setBoolean(22, a.isQAECulpa());
            stmt.setBoolean(23, a.isQAERaiva());
            stmt.setBoolean(24, a.isQAELuto());
            stmt.setBoolean(25, a.isQAEDesanimo());
            stmt.setString(26, a.getPsicomotricidade());
            stmt.setBoolean(27, a.isQAEHumor());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;
    }

    public List<Anamnese> Read() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Anamnese> anamneses = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM anamnese");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Anamnese a = new Anamnese();
                a.setQueixaPrincipal(rs.getString("QueixaPrincipal"));
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
                a.setQCIntegridadeSensorial(rs.getBoolean("QCIntegridadeSensorial"));
                a.setQCPercepcao(rs.getBoolean("QCPercepcao"));
                a.setQCAtencao(rs.getBoolean("QCAtencao"));
                a.setQCMemoria(rs.getBoolean("QCMemoria"));
                a.setQAEVolicao(rs.getBoolean("QAEVolicao"));
                a.setQAEAfeto(rs.getBoolean("QAEAfeto"));
                a.setQAEAnsiedade(rs.getBoolean("QAEAnsiedade"));
                a.setQAEMedo(rs.getBoolean("QAEMedo"));
                a.setQAECulpa(rs.getBoolean("QAECulpa"));
                a.setQAERaiva(rs.getBoolean("QAERaiva"));
                a.setQAELuto(rs.getBoolean("QAELuto"));
                a.setQAEDesanimo(rs.getBoolean("QAEDesanimo"));
                a.setQAEHumor(rs.getBoolean("QAEHumor"));
                a.setPsicomotricidade(rs.getString("Psicomotricidade"));
                a.setDataEmissao(rs.getString("DataEmissao"));
                

                anamneses.add(a);

            }

        } catch (SQLException ex) {

           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return anamneses;
    }

    public List<Anamnese> Read(Consulta c) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Anamnese> anamneses = new ArrayList<>();
        try {
            stmt = con.prepareStatement("SELECT * FROM anamnese Where CodConsulta = ?");
            stmt.setInt(1, c.getCodConsulta());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Anamnese a = new Anamnese();
                a.setQueixaPrincipal(rs.getString("QueixaPrincipal"));
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
                a.setQCIntegridadeSensorial(rs.getBoolean("QCIntegridadeSensorial"));
                a.setQCPercepcao(rs.getBoolean("QCPercepcao"));
                a.setQCAtencao(rs.getBoolean("QCAtencao"));
                a.setQCMemoria(rs.getBoolean("QCMemoria"));
                a.setQAEVolicao(rs.getBoolean("QAEVolicao"));
                a.setQAEAfeto(rs.getBoolean("QAEAfeto"));
                a.setQAEAnsiedade(rs.getBoolean("QAEAnsiedade"));
                a.setQAEMedo(rs.getBoolean("QAEMedo"));
                a.setQAECulpa(rs.getBoolean("QAECulpa"));
                a.setQAERaiva(rs.getBoolean("QAERaiva"));
                a.setQAELuto(rs.getBoolean("QAELuto"));
                a.setQAEHumor(rs.getBoolean("QAEHumor"));
                a.setQAEDesanimo(rs.getBoolean("QAEDesanimo"));
                a.setPsicomotricidade(rs.getString("Psicomotricidade"));
                a.setDataEmissao(rs.getString("DataEmissao"));
                a.setCodAnamnese(rs.getInt("CodAnamnese"));

                anamneses.add(a);

            }

        } catch (SQLException ex) {

           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return anamneses;
    }

    public boolean Update(Anamnese a) {

        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {

            stmt = con.prepareStatement("UPDATE anamnese SET QueixaPrincipal=?, SubitaOuProgressiva=?, InicioDaQueixa=?, QueixasSecundarias=?, HistoricoFamiliar=?, Diagnostico=?, Encaminhamento=?, DoencasConhecidas=?, MedicamentosUtilizados=?, OqueMudou=?, Sintomas=?,ComoComecou=?, QCIntegridadeSensorial=?, QCPercepcao=?, QCAtencao=?, QCMemoria=?, QAEVolicao =?, QAEAfeto=?,QAEAnsiedade=?, QAEMedo=?, QAECulpa=?, QAERaiva=?, QAELuto =?, QAEDesanimo=?, Psicomotricidade=?, QAEHumor=?  WHERE CodAnamnese = ? ");
            stmt.setString(1, a.getQueixaPrincipal());
            stmt.setString(2, a.getSubitaOuProgressiva());
            stmt.setObject(3, a.getInicioDaQueixa());
            stmt.setString(4, a.getQueixasSecundarias());
            stmt.setString(5, a.getHistoricoFamiliar());
            stmt.setString(6, a.getDiagnostico());
            stmt.setString(7, a.getEncaminhamento());
            stmt.setString(8, a.getDoencasConhecidas());
            stmt.setString(9, a.getMedicamentosUtilizados());
            //Mudancas
            stmt.setString(10, a.getOqueMudou());
            stmt.setString(11, a.getSintomas());
            stmt.setString(12, a.getComoComecou());
            stmt.setBoolean(13, a.isQCIntegridadeSensorial());
            stmt.setBoolean(14, a.isQCPercepcao());
            stmt.setBoolean(15, a.isQCAtencao());
            stmt.setBoolean(16, a.isQCMemoria());
            stmt.setBoolean(17, a.isQAEVolicao());
            stmt.setBoolean(18, a.isQAEAfeto());
            stmt.setBoolean(19, a.isQAEAnsiedade());
            stmt.setBoolean(20, a.isQAEMedo());
            stmt.setBoolean(21, a.isQAECulpa());
            stmt.setBoolean(22, a.isQAERaiva());
            stmt.setBoolean(23, a.isQAELuto());
            stmt.setBoolean(24, a.isQAEDesanimo());
            stmt.setString(25, a.getPsicomotricidade());
            stmt.setBoolean(26, a.isQAEHumor());
            stmt.setInt(27, a.getCodAnamnese());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            status = false;

        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
        return status;

    }

    public boolean Delete(Anamnese a) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Delete FROM anamnese WHERE CodAnamnese =? ");

            stmt.setInt(1, a.getCodAnamnese());

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
            stmt = con.prepareStatement("Delete FROM anamnese WHERE CodConsulta =? ");

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
   
    
     public boolean SoftDelete(Consulta c) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update anamnese set Deletada = true WHERE CodConsulta =? ");

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
     
     
    
    public boolean RestaurarAnamneses(int CodPsicologo) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update anamnese set Deletada = false WHERE CodPsicologo =? ");

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
    
    public boolean RestaurarAnamneses(Consulta c) {
        boolean status = true;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("Update anamnese set Deletada = false WHERE CodConsulta =? ");

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
                    a.setQueixaPrincipal(rs.getString("QueixaPrincipal"));
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
                    a.setQCIntegridadeSensorial(rs.getBoolean("QCIntegridadeSensorial"));
                    a.setQCPercepcao(rs.getBoolean("QCPercepcao"));
                    a.setQCAtencao(rs.getBoolean("QCAtencao"));
                    a.setQCMemoria(rs.getBoolean("QCMemoria"));
                    a.setQAEVolicao(rs.getBoolean("QAEVolicao"));
                    a.setQAEAfeto(rs.getBoolean("QAEAfeto"));
                    a.setQAEAnsiedade(rs.getBoolean("QAEAnsiedade"));
                    a.setQAEMedo(rs.getBoolean("QAEMedo"));
                    a.setQAECulpa(rs.getBoolean("QAECulpa"));
                    a.setQAERaiva(rs.getBoolean("QAERaiva"));
                    a.setQAELuto(rs.getBoolean("QAELuto"));
                    a.setQAEHumor(rs.getBoolean("QAEHumor"));
                    a.setQAEDesanimo(rs.getBoolean("QAEDesanimo"));
                    a.setPsicomotricidade(rs.getString("Psicomotricidade"));
                    a.setCodAnamnese(rs.getInt("codanamnese"));
                    a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));

                }
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return a;
    }

    public Anamnese ReadAnamnese(int CodAnamnese) {

        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Anamnese a = new Anamnese();
        try {
            stmt = con.prepareStatement("SELECT * FROM anamnese WHERE CodAnamnese =?");
            stmt.setInt(1, CodAnamnese);
            rs = stmt.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    a.setQueixaPrincipal(rs.getString("QueixaPrincipal"));
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
                    a.setQCIntegridadeSensorial(rs.getBoolean("QCIntegridadeSensorial"));
                    a.setQCPercepcao(rs.getBoolean("QCPercepcao"));
                    a.setQCAtencao(rs.getBoolean("QCAtencao"));
                    a.setQCMemoria(rs.getBoolean("QCMemoria"));
                    a.setQAEVolicao(rs.getBoolean("QAEVolicao"));
                    a.setQAEAfeto(rs.getBoolean("QAEAfeto"));
                    a.setQAEAnsiedade(rs.getBoolean("QAEAnsiedade"));
                    a.setQAEMedo(rs.getBoolean("QAEMedo"));
                    a.setQAECulpa(rs.getBoolean("QAECulpa"));
                    a.setQAERaiva(rs.getBoolean("QAERaiva"));
                    a.setQAELuto(rs.getBoolean("QAELuto"));
                    a.setQAEHumor(rs.getBoolean("QAEHumor"));
                    a.setQAEDesanimo(rs.getBoolean("QAEDesanimo"));
                    a.setPsicomotricidade(rs.getString("Psicomotricidade"));
                    a.setCodAnamnese(rs.getInt("codanamnese"));
                    a.getConsulta().setCodConsulta(rs.getInt("CodConsulta"));

                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return a;
    }
}
