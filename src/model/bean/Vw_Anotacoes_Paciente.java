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
public class Vw_Anotacoes_Paciente {
    private Paciente paciente;
    private Anotacao anotacao;
    
    public Vw_Anotacoes_Paciente() {
        this.paciente = new Paciente();
        this.anotacao = new Anotacao();
    }
    
    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Anotacao getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(Anotacao anotacao) {
        this.anotacao = anotacao;
    }
    
}
