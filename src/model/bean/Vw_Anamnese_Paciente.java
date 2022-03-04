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
public class Vw_Anamnese_Paciente {

    private Paciente paciente;
    private Consulta consulta;
    private Anamnese anamnese;
    private Psicologo psicologo;
    public Vw_Anamnese_Paciente() {
        this.paciente = new Paciente();
        this.consulta = new Consulta();
        this.anamnese = new Anamnese();
        this.psicologo = new Psicologo();
    }

    public Psicologo getPsicologo() {
        return psicologo;
    }

    public void setPsicologo(Psicologo psicologo) {
        this.psicologo = psicologo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Anamnese getAnamnese() {
        return anamnese;
    }

    public void setAnamnese(Anamnese anamnese) {
        this.anamnese = anamnese;
    }

}
