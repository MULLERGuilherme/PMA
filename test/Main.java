/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import model.bean.Consulta;
import model.bean.Paciente;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;

/**
 *
 * @author User
 */
public class Main {
    
    public static void main(String [] args) {
        Consulta c = new Consulta();
        ConsultaDAO cdao = new ConsultaDAO();
        c = cdao.ReadConsulta(1);
         System.out.println(c.getDataConsulta());        
        
    }
}
