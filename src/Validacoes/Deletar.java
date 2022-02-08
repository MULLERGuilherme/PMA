/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validacoes;

import model.bean.Consulta;
import model.bean.Paciente;
import model.bean.Psicologo;
import model.dao.AnamneseDAO;
import model.dao.AnotacaoDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.PsicologoDAO;
import model.dao.TelefoneDAO;

/**
 *
 * @author User
 */
public class Deletar {

    public static boolean DPaciente(Paciente p) {
        boolean status;
        
        PacienteDAO dao = new PacienteDAO();
        TelefoneDAO tdao = new TelefoneDAO();
        ConsultaDAO cdao = new ConsultaDAO();
        AnamneseDAO adao = new AnamneseDAO();
        AnotacaoDAO antdao = new AnotacaoDAO();
        
        
        status = tdao.DeleteTPaciente(p);
        System.out.println("telefone excluido com sucesso");
        ;
        if (status) {
            
        
            for(Consulta c : cdao.Read(p)){
                status = adao.Delete(c);
                status = antdao.Delete(c);
                
        }
            cdao.DeleteConsultas(p);
            status = dao.Delete(p);
            
        }
        return status;
    }
    
     public static boolean DPsicologo(Psicologo p) {
        boolean status;
        
        PsicologoDAO dao = new PsicologoDAO();
        TelefoneDAO tdao = new TelefoneDAO();
        ConsultaDAO cdao = new ConsultaDAO();
        AnamneseDAO adao = new AnamneseDAO();
        AnotacaoDAO antdao = new AnotacaoDAO();
        
        
        status = tdao.DeleteTPsicologo(p);
       
        if (status) {
            
        
            for(Consulta c : cdao.Read(p)){
                status = adao.Delete(c);
                status = antdao.Delete(c);
                
        }
            cdao.DeleteConsultas(p);
            status = dao.Delete(p);
            
        }
        return status;
    }
     
      public static boolean DConsulta(Consulta c) {
        boolean status;
        
        
        ConsultaDAO cdao = new ConsultaDAO();
        AnamneseDAO adao = new AnamneseDAO();
        AnotacaoDAO antdao = new AnotacaoDAO();
        
        
        
            
        
           
                status = adao.Delete(c);
                status = antdao.Delete(c);
                
        
           status =  cdao.Delete(c);
            
            
       
        return status;
    }
     
}

