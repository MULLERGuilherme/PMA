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
public class Vw_TelefonesPsicologos {
    private Psicologo psicologo;
    private Telefone telefone;

    @Override
    public String toString() {
        return "Vw_TelefonesPsicologos{" + "psicologo=" + psicologo.getEmail() + ", telefone=" + telefone + '}';
    }
 
    
    
public Vw_TelefonesPsicologos() {
        this.psicologo = new Psicologo();
        this.telefone = new Telefone();
       
    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

 

  


}
