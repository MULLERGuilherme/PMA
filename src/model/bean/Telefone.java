/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

/**
 *
 * @author User
 */
public class Telefone {

    private int CodTelefone;
    private String Numero;
    private Paciente paciente;
    private Psicologo psicologo;
    private boolean Deletado = false;

    public boolean isDeletado() {
        return Deletado;
    }

    public void setDeletado(boolean Deletado) {
        this.Deletado = Deletado;
    }

    public Telefone() {
        this.Numero = null;
        this.psicologo = new Psicologo();
        this.paciente = new Paciente();
        this.Deletado = false;
    }

    public int getCodTelefone() {
        return CodTelefone;
    }

    public void setCodTelefone(int CodTelefone) {
        this.CodTelefone = CodTelefone;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String Numero) {
        this.Numero = Numero;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

}
