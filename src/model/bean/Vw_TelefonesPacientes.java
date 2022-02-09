/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author Eliézer
 */
public class Vw_TelefonesPacientes {
    private Paciente paciente;
    private Telefone telefone;
 
    
    
public Vw_TelefonesPacientes() {
        this.paciente = new Paciente();
        this.telefone = new Telefone();
       
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

  


}
