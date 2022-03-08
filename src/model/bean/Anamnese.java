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
    private String Psicomotricidade;

//Mudancas 2.0
//private String QueixasCognitivas;
    public void setQAEDesanimo(boolean QAEDesanimo) {
        this.QAEDesanimo = QAEDesanimo;
    }
    private boolean QCIntegridadeSensorial;
    private boolean QCPercepcao;
    private boolean QCAtencao;
    private boolean QCMemoria;

//private String QueixasAfetivoEmocionais;
    private boolean QAEVolicao;
    private boolean QAEAfeto;
    private boolean QAEAnsiedade;
    private boolean QAEMedo;
    private boolean QAECulpa;
    private boolean QAERaiva;
    private boolean QAELuto;
    private boolean QAEDesanimo;

    public Anamnese() {

        this.QueixasSecundarias = null;
        this.HistoricoFamiliar = null;
        this.Diagnostico = null;
        this.Encaminhamento = null;
        this.DoencasConhecidas = null;
        this.MedicamentosUtilizados = null;
        this.DataEmissao = null;

        this.QCIntegridadeSensorial = false;
        this.QCPercepcao = false;
        this.QCAtencao = false;
        this.QCMemoria = false;

        this.QAEVolicao = false;
        this.QAEAfeto = false;
        this.QAEAnsiedade = false;
        this.QAEMedo = false;
        this.QAECulpa = false;
        this.QAERaiva = false;
        this.QAELuto = false;
        this.QAEDesanimo = false;

        this.Psicomotricidade = "Normal";

        this.consulta = new Consulta();

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

    public Object getInicioDaQueixa() {
        return InicioDaQueixa;
    }

    public void setInicioDaQueixa(Object InicioDaQueixa) {
        this.InicioDaQueixa = InicioDaQueixa;
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

    public String getPsicomotricidade() {
        return Psicomotricidade;
    }

    public void setPsicomotricidade(String Psicomotricidade) {
        this.Psicomotricidade = Psicomotricidade;
    }

    public boolean isQCIntegridadeSensorial() {
        return QCIntegridadeSensorial;
    }

    public void setQCIntegridadeSensorial(boolean QCIntegridadeSensorial) {
        this.QCIntegridadeSensorial = QCIntegridadeSensorial;
    }

    public boolean isQCPercepcao() {
        return QCPercepcao;
    }

    public void setQCPercepcao(boolean QCPercepcao) {
        this.QCPercepcao = QCPercepcao;
    }

    public boolean isQCAtencao() {
        return QCAtencao;
    }

    public void setQCAtencao(boolean QCAtencao) {
        this.QCAtencao = QCAtencao;
    }

    public boolean isQCMemoria() {
        return QCMemoria;
    }

    public void setQCMemoria(boolean QCMemoria) {
        this.QCMemoria = QCMemoria;
    }

    public boolean isQAEVolicao() {
        return QAEVolicao;
    }

    public void setQAEVolicao(boolean QAEVolicao) {
        this.QAEVolicao = QAEVolicao;
    }

    public boolean isQAEAfeto() {
        return QAEAfeto;
    }

    public void setQAEAfeto(boolean QAEAfeto) {
        this.QAEAfeto = QAEAfeto;
    }

    public boolean isQAEAnsiedade() {
        return QAEAnsiedade;
    }

    public void setQAEAnsiedade(boolean QAEAnsiedade) {
        this.QAEAnsiedade = QAEAnsiedade;
    }

    public boolean isQAEMedo() {
        return QAEMedo;
    }

    public void setQAEMedo(boolean QAEMedo) {
        this.QAEMedo = QAEMedo;
    }

    public boolean isQAECulpa() {
        return QAECulpa;
    }

    public void setQAECulpa(boolean QAECulpa) {
        this.QAECulpa = QAECulpa;
    }

    public boolean isQAERaiva() {
        return QAERaiva;
    }

    public void setQAERaiva(boolean QAERaiva) {
        this.QAERaiva = QAERaiva;
    }

    public boolean isQAELuto() {
        return QAELuto;
    }

    public void setQAELuto(boolean QAELuto) {
        this.QAELuto = QAELuto;
    }

    public boolean isQAEDesanimo() {
        return QAEDesanimo;
    }

}
