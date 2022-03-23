package Validacoes;

import javax.swing.JOptionPane;
import model.bean.Consulta;
import model.bean.Paciente;
import model.bean.Psicologo;
import model.dao.AnamneseDAO;
import model.dao.AnotacaoDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.PsicologoDAO;
import model.dao.TelefoneDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eliézer
 */
public class Restaurar {

    public static boolean RestaurarPaciente(int codpaciente) {
        TelefoneDAO tdao = new TelefoneDAO();
        PacienteDAO dao = new PacienteDAO();
        ConsultaDAO cdao = new ConsultaDAO();
        AnamneseDAO adao = new AnamneseDAO();
        AnotacaoDAO antdao = new AnotacaoDAO();
        Paciente p = new Paciente();
        p.setCodPaciente(codpaciente);

        boolean status = tdao.RestaurarTPaciente(codpaciente);
        if (status) {
            status = cdao.RestaurarConsultasPaciente(codpaciente);
            if (status) {
                for (Consulta c : cdao.Read(p)) {
                    if (status) {
                        status = adao.RestaurarAnamneses(c);
                        if (status) {
                            status = antdao.RestaurarAnotacoes(c);
                        }

                    } else {
                        return status;
                    }
                }

                status = dao.RestaurarPaciente(codpaciente);

            } else {
                return status;
            }

        }
        return status;

    }

    public static boolean RestaurarPsicologos(int codpsicologo) {
        TelefoneDAO tdao = new TelefoneDAO();
        PsicologoDAO dao = new PsicologoDAO();
        ConsultaDAO cdao = new ConsultaDAO();
        AnamneseDAO adao = new AnamneseDAO();
        AnotacaoDAO antdao = new AnotacaoDAO();
        Psicologo p = new Psicologo();
        p.setCodPsicologo(codpsicologo);
        //;
        boolean status = tdao.RestaurarTPsicologo(codpsicologo);
        if (status) {
            status = cdao.RestaurarConsultas(codpsicologo);
            if (status) {
                for (Consulta c : cdao.Read(p)) {
                    if (status) {
                        status = adao.RestaurarAnamneses(c);
                        if (status) {
                            status = antdao.RestaurarAnotacoes(c);
                        }

                    } else {
                        return status;
                    }
                }

                status = dao.RestaurarPsicologo(codpsicologo);

            } else {
                return status;
            }

        }
        return status;

    }
}
