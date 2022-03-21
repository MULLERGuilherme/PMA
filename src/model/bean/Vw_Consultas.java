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
public class Vw_Consultas {

    private int CodConsulta;
    private Object DataConsulta;
    private String Status;
    private String Pagamento;
    private Paciente paciente;
    private Psicologo psicologo;

    public String getPagamento() {
        return Pagamento;
    }

    public void setPagamento(String Pagamento) {
        this.Pagamento = Pagamento;
    }
    
    public Vw_Consultas() {
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
