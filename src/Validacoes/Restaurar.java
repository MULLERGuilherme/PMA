package Validacoes;

import javax.swing.JOptionPane;
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
}
