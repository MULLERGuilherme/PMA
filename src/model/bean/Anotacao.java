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
public class Anotacao {
private int CodAnotacao;
private String Assunto;
private String Texto;
private String DataAnotacao;
private Consulta consulta;

    public Anotacao(){
    this.consulta = new Consulta();
}

    public int getCodAnotacao() {
        return CodAnotacao;
    }

    public void setCodAnotacao(int CodAnotacao) {
        this.CodAnotacao = CodAnotacao;
    }

    public String getAssunto() {
        return Assunto;
    }

    public void setAssunto(String Assunto) {
        this.Assunto = Assunto;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String Texto) {
        this.Texto = Texto;
    }

    public String getDataAnotacao() {
        return DataAnotacao;
    }

    public void setDataAnotacao(String DataAnotacao) {
        this.DataAnotacao = DataAnotacao;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }



  

}
