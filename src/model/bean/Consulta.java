/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.sql.Date;

/**
 *
 * @author User
 */
public class Consulta {

    private int CodConsulta;
    private Object DataConsulta;
    private String Status;
    private Paciente paciente;
    private Psicologo psicologo;
    
    public Consulta(){
    
    this.paciente = new Paciente();
    this.psicologo = new Psicologo();
}

    public int getCodConsulta() {
        return CodConsulta;
    }

    public void setCodConsulta(int CodConsulta) {
        this.CodConsulta = CodConsulta;
    }

    public Object getDataConsulta() {
        return DataConsulta;
    }

    public void setDataConsulta(Object DataConsulta) {
        this.DataConsulta = DataConsulta;
    }


    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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
