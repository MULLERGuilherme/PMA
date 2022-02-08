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
public class Anamnese {

private int CodAnamnese;
private String QueixaPrincipal;
private String SubitaOuProgressiva;
private Object InicioDaQueixa;
private String QueixasSecundarias;
private String HistoricoFamiliar;
private String Diagnostico;
private String Encaminhamento;
private String DoencasConhecidas;
private String MedicamentosUtilizados;
private String DataEmissao;
private Consulta consulta;

//Mudanças
private String OqueMudou;
private String Sintomas;
private String ComoComecou;
private String QueixasCognitivas;
private String QueixasAfetivoEmocionais;
private String Psicomotricidade;

public Anamnese(){
   
    this.QueixasSecundarias = null;
    this.HistoricoFamiliar = null;
    this.Diagnostico = null;
    this.Encaminhamento = null;
    this.DoencasConhecidas = null;
    this.MedicamentosUtilizados = null;
    this.DataEmissao = null;
    this.QueixasCognitivas = null;
    this.QueixasAfetivoEmocionais = null;
    this.Psicomotricidade = "Normal";
    this.consulta = new Consulta();
    
    
}

    public Object getInicioDaQueixa() {
        return InicioDaQueixa;
    }

    public void setInicioDaQueixa(Object InicioDaQueixa) {
        this.InicioDaQueixa = InicioDaQueixa;
    }


    public int getCodAnamnese() {
        return CodAnamnese;
    }

    public void setCodAnamnese(int CodAnamnese) {
        this.CodAnamnese = CodAnamnese;
    }

    public String getQueixaPrincipal() {
        return QueixaPrincipal;
    }

    public void setQueixaPrincipal(String QueixaPrincipal) {
        this.QueixaPrincipal = QueixaPrincipal;
    }

    public String getSubitaOuProgressiva() {
        return SubitaOuProgressiva;
    }

    public void setSubitaOuProgressiva(String SubitaOuProgressiva) {
        this.SubitaOuProgressiva = SubitaOuProgressiva;
    }

 

    public String getQueixasSecundarias() {
        return QueixasSecundarias;
    }

    public void setQueixasSecundarias(String QueixasSecundarias) {
        this.QueixasSecundarias = QueixasSecundarias;
    }

    public String getHistoricoFamiliar() {
        return HistoricoFamiliar;
    }

    public void setHistoricoFamiliar(String HistoricoFamiliar) {
        this.HistoricoFamiliar = HistoricoFamiliar;
    }

    public String getDiagnostico() {
        return Diagnostico;
    }

    public void setDiagnostico(String Diagnostico) {
        this.Diagnostico = Diagnostico;
    }

    public String getEncaminhamento() {
        return Encaminhamento;
    }

    public void setEncaminhamento(String Encaminhamento) {
        this.Encaminhamento = Encaminhamento;
    }

    public String getDoencasConhecidas() {
        return DoencasConhecidas;
    }

    public void setDoencasConhecidas(String DoencasConhecidas) {
        this.DoencasConhecidas = DoencasConhecidas;
    }

    public String getMedicamentosUtilizados() {
        return MedicamentosUtilizados;
    }

    public void setMedicamentosUtilizados(String MedicamentosUtilizados) {
        this.MedicamentosUtilizados = MedicamentosUtilizados;
    }

    public String getDataEmissao() {
        return DataEmissao;
    }

    public void setDataEmissao(String DataEmissao) {
        this.DataEmissao = DataEmissao;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getOqueMudou() {
        return OqueMudou;
    }

    public void setOqueMudou(String OqueMudou) {
        this.OqueMudou = OqueMudou;
    }

    public String getSintomas() {
        return Sintomas;
    }

    public void setSintomas(String Sintomas) {
        this.Sintomas = Sintomas;
    }

    public String getComoComecou() {
        return ComoComecou;
    }

    public void setComoComecou(String ComoComecou) {
        this.ComoComecou = ComoComecou;
    }

    public String getQueixasCognitivas() {
        return QueixasCognitivas;
    }

    public void setQueixasCognitivas(String QueixasCognitivas) {
        this.QueixasCognitivas = QueixasCognitivas;
    }

    public String getQueixasAfetivoEmocionais() {
        return QueixasAfetivoEmocionais;
    }

    public void setQueixasAfetivoEmocionais(String QueixasAfetivoEmocionais) {
        this.QueixasAfetivoEmocionais = QueixasAfetivoEmocionais;
    }

    public String getPsicomotricidade() {
        return Psicomotricidade;
    }

    public void setPsicomotricidade(String Psicomotricidade) {
        this.Psicomotricidade = Psicomotricidade;
    }



}