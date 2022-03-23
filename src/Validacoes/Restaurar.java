package Validacoes;

import javax.swing.JOptionPane;
import model.dao.AnamneseDAO;
import model.dao.AnotacaoDAO;
import model.dao.ConsultaDAO;
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

    public static boolean RestaurarTelefonesPaciente(int codpaciente) {
        TelefoneDAO tdao = new TelefoneDAO();

        boolean status = tdao.RestaurarTPaciente(codpaciente);
        return status;
    }

    public static boolean RestaurarPsicologos(int codpsicologo) {
        TelefoneDAO tdao = new TelefoneDAO();
        PsicologoDAO dao = new PsicologoDAO();
        ConsultaDAO cdao = new ConsultaDAO();
        AnamneseDAO adao = new AnamneseDAO();
        AnotacaoDAO antdao = new AnotacaoDAO();
        //;
        boolean status = tdao.RestaurarTPsicologo(codpsicologo);
        status = cdao.RestaurarConsultas(codpsicologo);
        status = adao.RestaurarAnamneses(codpsicologo);
        status = antdao.RestaurarAnotacoes(codpsicologo);
        status = dao.RestaurarPsicologo(codpsicologo);
        return status;
    }
}
