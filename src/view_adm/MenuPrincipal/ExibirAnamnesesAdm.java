/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_adm.MenuPrincipal;

import view.*;
import util.Util;
import Validacoes.Validar;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.bean.Anamnese;
import model.bean.Consulta;
import model.bean.Paciente;
import model.bean.Psicologo;
import model.bean.Vw_Anamnese_Paciente;
import model.dao.ADMDAO;
import model.dao.AnamneseDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.ViewsDAO;


/**
 *
 * @author guimu
 */
public class ExibirAnamnesesAdm extends javax.swing.JFrame {

    public boolean existe = false;
    public static int codigoanamnese;

    public ExibirAnamnesesAdm() {
        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        BtnExibirTodasAnamneses.setEnabled(false);
        btnalterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTAnamneses.getModel();
        TableColumnModel cmod = JTAnamneses.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTAnamneses.setRowSorter(new TableRowSorter(dtmPacientes));
        ReadJTable();
    }

 public boolean readcampos(int cod) {
        Anamnese a = new Anamnese();
        AnamneseDAO dao = new AnamneseDAO();
        a = dao.ReadAnamnese(cod);
        int codanamnese = a.getCodAnamnese();
        if (codanamnese != 0) {

            txtQueixaPrincipal2.setText(a.getQueixaPrincipal());
            DataInicio2.setText((String) Validar.fDataNascBD((Date) a.getInicioDaQueixa()));
            SubitaOuProgressiva2.setSelectedItem(a.getSubitaOuProgressiva());
            txtQueixaSecundaria2.setText(a.getQueixasSecundarias());
            txtHistoricoFamiliar2.setText(a.getHistoricoFamiliar());
            txtDiagnostico2.setText(a.getDiagnostico());
            txtEncaminhamento2.setText(a.getEncaminhamento());
            txtDoencasConhecidas2.setText(a.getDoencasConhecidas());
            txtMedicamentosUtilizados2.setText(a.getMedicamentosUtilizados());
            txtOqueMudou2.setText(a.getOqueMudou());
            txtSintomas2.setText(a.getSintomas());
            txtComoComecou2.setText(a.getComoComecou());
            //Checkbox
            CheckBoxIntegridadeSensorial1.setSelected(a.isQCIntegridadeSensorial());
            CheckBoxPercepcao1.setSelected(a.isQCPercepcao());
            CheckBoxAtencao1.setSelected(a.isQCAtencao());
            CheckBoxMemoria1.setSelected(a.isQCMemoria());
            CheckBoxVolicao1.setSelected(a.isQAEVolicao());
            CheckBoxAfeto1.setSelected(a.isQAEAfeto());
            CheckBoxAnsiedade1.setSelected(a.isQAEAnsiedade());
            CheckBoxMedo1.setSelected(a.isQAEMedo());
            CheckBoxCulpa1.setSelected(a.isQAECulpa());
            CheckBoxRaiva1.setSelected(a.isQAERaiva());
            CheckBoxLuto1.setSelected(a.isQAELuto());
            CheckBoxDesanimo1.setSelected(a.isQAEDesanimo());

            JCBPsicomotricidade2.setSelectedItem(a.getPsicomotricidade());
            return true;
        }
        return false;
    }
    private void ReadJTable() {
        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
        model.setNumRows(0);
        ADMDAO vwdao = new ADMDAO();
        //ConsultaDAO cdao = new ConsultaDAO();
        //PacienteDAO pdao = new PacienteDAO();
        //Paciente p = new Paciente();

        for (Vw_Anamnese_Paciente v : vwdao.ReadALLAnamneses()) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
                v.getPsicologo().getNome_completo(),
                v.getAnamnese().getDiagnostico(),
                Validar.fDatetime((Timestamp) v.getConsulta().getDataConsulta())

            });
        }
    }

    public void ReadJTableBusca(String Atributo, String Busca) {

        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
        model.setNumRows(0);
        if (Atributo.equals("Nome do Paciente")) {
            Atributo = "Paciente";
        }
        if (Atributo.equals("Diagnóstico")) {
            Atributo = "Diagnostico";
        }
        if (Atributo.equals("Data da Consulta")) {
            Atributo = "DataConsulta";
        }
        ADMDAO vwdao = new ADMDAO();

        for (Vw_Anamnese_Paciente v : vwdao.BuscaALLAnamneses(Atributo, Busca)) {

            model.addRow(new Object[]{
                v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
                v.getPsicologo().getNome_completo(),
                v.getAnamnese().getDiagnostico(),
                Validar.fDatetime((Timestamp) v.getConsulta().getDataConsulta())

            });
        }
    }

    private void Alterar(int cod) {
        Anamnese a = new Anamnese();
        AnamneseDAO dao = new AnamneseDAO();
        Anamnese a2 = new Anamnese();
        AnamneseDAO dao2 = new AnamneseDAO();
        a2 = dao2.ReadAnamnese(cod);
        a.setCodAnamnese(a2.getCodAnamnese());
        if (a.getCodAnamnese() != 0) {
            a.setQueixaPrincipal(txtQueixaPrincipal2.getText());
            a.setSubitaOuProgressiva((String) SubitaOuProgressiva2.getSelectedItem());

            //java.util.Date date = new java.util.Date();
            Object param = DataInicio2.getDate();
            a.setInicioDaQueixa(param);
            a.setQueixasSecundarias(txtQueixaSecundaria2.getText());
            a.setHistoricoFamiliar(txtHistoricoFamiliar2.getText());
            a.setDiagnostico(txtDiagnostico2.getText());
            a.setEncaminhamento(txtEncaminhamento2.getText());
            a.setDoencasConhecidas(txtDoencasConhecidas2.getText());
            a.setMedicamentosUtilizados(txtMedicamentosUtilizados2.getText());
            a.getConsulta().setCodConsulta(a2.getConsulta().getCodConsulta());
            a.setOqueMudou(txtOqueMudou2.getText());
            a.setSintomas(txtSintomas2.getText());
            a.setComoComecou(txtComoComecou2.getText());
            a.setQCIntegridadeSensorial(CheckBoxIntegridadeSensorial1.isSelected());
            a.setQCPercepcao(CheckBoxPercepcao1.isSelected());
            a.setQCAtencao(CheckBoxAtencao1.isSelected());
            a.setQCMemoria(CheckBoxMemoria1.isSelected());
            a.setQAEVolicao(CheckBoxVolicao1.isSelected());
            a.setQAEAfeto(CheckBoxAfeto1.isSelected());
            a.setQAEAnsiedade(CheckBoxAnsiedade1.isSelected());
            a.setQAEMedo(CheckBoxMedo1.isSelected());
            a.setQAECulpa(CheckBoxCulpa1.isSelected());
            a.setQAERaiva(CheckBoxRaiva1.isSelected());
            a.setQAELuto(CheckBoxLuto1.isSelected());
            a.setQAEDesanimo(CheckBoxDesanimo1.isSelected());
            a.setPsicomotricidade((String) JCBPsicomotricidade2.getSelectedItem());

            boolean sucesso = dao.Update(a);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Anamnese Alterada Com Sucesso");
            }
        }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ModalAnamnese4 = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        PainelDadosPaciente6 = new javax.swing.JPanel();
        PainelIdentificacaoPessoal6 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        BtnSalvarAlteracoes8 = new javax.swing.JButton();
        BtnCancelar8 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        SubitaOuProgressiva2 = new javax.swing.JComboBox<>();
        labelInicioQueixa4 = new javax.swing.JLabel();
        JCBPsicomotricidade2 = new javax.swing.JComboBox<>();
        labelInicioQueixa5 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        LabelEmail3 = new javax.swing.JLabel();
        txtComoComecou2 = new javax.swing.JTextField();
        LabelNome5 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        txtQueixaSecundaria2 = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtDiagnostico2 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        txtHistoricoFamiliar2 = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        txtDoencasConhecidas2 = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        txtSintomas2 = new javax.swing.JTextField();
        jLabel54 = new javax.swing.JLabel();
        txtMedicamentosUtilizados2 = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        txtOqueMudou2 = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtEncaminhamento2 = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        DataInicio2 = new com.github.lgooddatepicker.components.DatePicker();
        BtnCancelar7 = new javax.swing.JButton();
        BtnSalvarAlteracoes7 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        txtQueixaPrincipal2 = new javax.swing.JTextField();
        jEImagePanel4 = new LIB.JEImagePanel();
        jLabel21 = new javax.swing.JLabel();
        CheckBoxVolicao1 = new javax.swing.JCheckBox();
        CheckBoxAfeto1 = new javax.swing.JCheckBox();
        CheckBoxHumor1 = new javax.swing.JCheckBox();
        CheckBoxAnsiedade1 = new javax.swing.JCheckBox();
        CheckBoxMedo1 = new javax.swing.JCheckBox();
        CheckBoxCulpa1 = new javax.swing.JCheckBox();
        CheckBoxRaiva1 = new javax.swing.JCheckBox();
        CheckBoxLuto1 = new javax.swing.JCheckBox();
        CheckBoxDesanimo1 = new javax.swing.JCheckBox();
        CheckBoxIntegridadeSensorial1 = new javax.swing.JCheckBox();
        CheckBoxPercepcao1 = new javax.swing.JCheckBox();
        CheckBoxAtencao1 = new javax.swing.JCheckBox();
        CheckBoxMemoria1 = new javax.swing.JCheckBox();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel1 = new JPanel();
        jLabel1 = new javax.swing.JLabel();
        jEImagePanel1 = new LIB.JEImagePanel();
        btnalterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JCBAtributo = new javax.swing.JComboBox<>();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAnamneses = new javax.swing.JTable();
        PainelMenu = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        BtnManterPaciente = new javax.swing.JButton();
        BtnManterConsulta = new javax.swing.JButton();
        BtnManterPsicologo = new javax.swing.JButton();
        BtnExibirTodasAnamneses = new javax.swing.JButton();
        BtnExibirTodasAnotacoes = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        PainelDadosPaciente6.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente6.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal6.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal6Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal6);
        PainelIdentificacaoPessoal6.setLayout(PainelIdentificacaoPessoal6Layout);
        PainelIdentificacaoPessoal6Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal6Layout.setVerticalGroup(
            PainelIdentificacaoPessoal6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel31.setText("* Campos Obrigatórios");
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes8.setText("Salvar Alterações");
        BtnSalvarAlteracoes8.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes8.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes8ActionPerformed(evt);
            }
        });

        BtnCancelar8.setText("Cancelar");
        BtnCancelar8.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar8.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar8ActionPerformed(evt);
            }
        });

        jLabel39.setText("Subita ou Progressiva:");
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        SubitaOuProgressiva2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subita", "Progressiva"}));
        SubitaOuProgressiva2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelInicioQueixa4.setText("Psicomotricidade: ");
        labelInicioQueixa4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBPsicomotricidade2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Lento", "Agitado"}));
        JCBPsicomotricidade2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelInicioQueixa5.setText("Queixas Afetivo-emocionais:");
        labelInicioQueixa5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel28.setText("Queixas Cognitivas:");
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEmail3.setText("Queixa Secundária:");
        LabelEmail3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtComoComecou2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelNome5.setText("Queixa Principal:");
        LabelNome5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel49.setText("Como começou:");
        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtQueixaSecundaria2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel50.setText("Diagnóstico:");
        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtDiagnostico2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel51.setText("Histórico Familiar:");
        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtHistoricoFamiliar2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel52.setText("Doenças Conhecidas:");
        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtDoencasConhecidas2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel53.setText("Sintomas:");
        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtSintomas2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel54.setText("Medicamentos Utilizados:");
        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtMedicamentosUtilizados2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel55.setText("O que mudou:");
        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtOqueMudou2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel56.setText("Encaminhamento:");
        jLabel56.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEncaminhamento2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel57.setText("Data de Início:");
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        DataInicio2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataInicio2.setPreferredSize(new java.awt.Dimension(160, 17));

        BtnCancelar7.setText("Cancelar");
        BtnCancelar7.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar7.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar7ActionPerformed(evt);
            }
        });

        BtnSalvarAlteracoes7.setText("Salvar Alterações");
        BtnSalvarAlteracoes7.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes7.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes7ActionPerformed(evt);
            }
        });

        jLabel20.setText("Campos Obrigatórios");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtQueixaPrincipal2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jEImagePanel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/simboloma.png"))); // NOI18N

        javax.swing.GroupLayout jEImagePanel4Layout = new javax.swing.GroupLayout(jEImagePanel4);
        jEImagePanel4.setLayout(jEImagePanel4Layout);
        jEImagePanel4Layout.setHorizontalGroup(
            jEImagePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jEImagePanel4Layout.setVerticalGroup(
            jEImagePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel21.setText("Anamnese Psicológica");
        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(59, 131, 117));

        CheckBoxVolicao1.setText("Volição");
        CheckBoxVolicao1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxVolicao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxAfeto1.setText("Afeto");
        CheckBoxAfeto1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxAfeto1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxHumor1.setText("Humor");
        CheckBoxHumor1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxHumor1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxAnsiedade1.setText("Ansiedade");
        CheckBoxAnsiedade1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxAnsiedade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxMedo1.setText("Medo");
        CheckBoxMedo1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxMedo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxCulpa1.setText("Culpa");
        CheckBoxCulpa1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxCulpa1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxRaiva1.setText("Raiva");
        CheckBoxRaiva1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxRaiva1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxLuto1.setText("Luto");
        CheckBoxLuto1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxLuto1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxDesanimo1.setText("Desânimo");
        CheckBoxDesanimo1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxDesanimo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxIntegridadeSensorial1.setText("Integridade Sensorial");
        CheckBoxIntegridadeSensorial1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxIntegridadeSensorial1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxPercepcao1.setText("Percepção");
        CheckBoxPercepcao1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxPercepcao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxAtencao1.setText("Atenção");
        CheckBoxAtencao1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxAtencao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxMemoria1.setText("Memória");
        CheckBoxMemoria1.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxMemoria1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel22.setText("*");
        jLabel22.setBackground(new java.awt.Color(255, 0, 0));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));

        jLabel23.setText("*");
        jLabel23.setBackground(new java.awt.Color(255, 0, 0));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));

        jLabel24.setText("*");
        jLabel24.setBackground(new java.awt.Color(255, 0, 0));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 0));

        jLabel25.setText("*");
        jLabel25.setBackground(new java.awt.Color(255, 0, 0));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 0, 0));

        jLabel26.setText("*");
        jLabel26.setBackground(new java.awt.Color(255, 0, 0));
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 0, 0));

        jLabel29.setText("*");
        jLabel29.setBackground(new java.awt.Color(255, 0, 0));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 0));

        jLabel30.setText("*");
        jLabel30.setBackground(new java.awt.Color(255, 0, 0));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout PainelDadosPaciente6Layout = new javax.swing.GroupLayout(PainelDadosPaciente6);
        PainelDadosPaciente6.setLayout(PainelDadosPaciente6Layout);
        PainelDadosPaciente6Layout.setHorizontalGroup(
            PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar8)
                .addGap(42, 42, 42)
                .addComponent(BtnSalvarAlteracoes8, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel31))
                    .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel22))
                                .addGap(19, 19, 19)
                                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelNome5)
                                    .addComponent(jLabel57))
                                .addGap(34, 34, 34)
                                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                        .addComponent(DataInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 393, Short.MAX_VALUE))
                                    .addComponent(txtQueixaPrincipal2)))
                            .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnCancelar7)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnSalvarAlteracoes7))
                                .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(jLabel24)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel28))
                                .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                    .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel23)
                                        .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                            .addComponent(CheckBoxIntegridadeSensorial1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxPercepcao1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxAtencao1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxMemoria1))
                                        .addComponent(jLabel49)
                                        .addComponent(jLabel50)
                                        .addComponent(jLabel56)
                                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel52)
                                        .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                            .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel54)
                                                .addComponent(jLabel55)
                                                .addComponent(jLabel53))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtComoComecou2)
                                                .addComponent(txtDiagnostico2)
                                                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtSintomas2)
                                                    .addComponent(txtMedicamentosUtilizados2)
                                                    .addComponent(txtOqueMudou2)
                                                    .addComponent(txtDoencasConhecidas2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtEncaminhamento2)
                                                .addComponent(txtHistoricoFamiliar2)))
                                        .addComponent(labelInicioQueixa5)
                                        .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(LabelEmail3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtQueixaSecundaria2))
                                        .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                            .addComponent(CheckBoxVolicao1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxAfeto1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxHumor1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxAnsiedade1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxMedo1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxCulpa1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxRaiva1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxLuto1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxDesanimo1))
                                        .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                                            .addComponent(jLabel39)
                                            .addGap(32, 32, 32)
                                            .addComponent(SubitaOuProgressiva2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel26)
                                            .addGap(6, 6, 6)
                                            .addComponent(labelInicioQueixa4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(JCBPsicomotricidade2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        PainelDadosPaciente6Layout.setVerticalGroup(
            PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEImagePanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQueixaPrincipal2)
                    .addComponent(LabelNome5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel57)
                    .addComponent(DataInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmail3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQueixaSecundaria2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInicioQueixa5)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckBoxVolicao1)
                    .addComponent(CheckBoxAfeto1)
                    .addComponent(CheckBoxHumor1)
                    .addComponent(CheckBoxAnsiedade1)
                    .addComponent(CheckBoxMedo1)
                    .addComponent(CheckBoxCulpa1)
                    .addComponent(CheckBoxRaiva1)
                    .addComponent(CheckBoxLuto1)
                    .addComponent(CheckBoxDesanimo1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckBoxIntegridadeSensorial1)
                    .addComponent(CheckBoxPercepcao1)
                    .addComponent(CheckBoxAtencao1)
                    .addComponent(CheckBoxMemoria1))
                .addGap(19, 19, 19)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(SubitaOuProgressiva2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInicioQueixa4)
                    .addComponent(JCBPsicomotricidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtComoComecou2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtDiagnostico2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtEncaminhamento2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtHistoricoFamiliar2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(txtDoencasConhecidas2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtSintomas2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMedicamentosUtilizados2)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOqueMudou2)
                    .addComponent(jLabel55))
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCancelar7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSalvarAlteracoes7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelDadosPaciente6Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel30))))
                .addGap(371, 371, 371)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDadosPaciente6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoes8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelDadosPaciente6, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1081, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(PainelDadosPaciente6, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout ModalAnamnese4Layout = new javax.swing.GroupLayout(ModalAnamnese4.getContentPane());
        ModalAnamnese4.getContentPane().setLayout(ModalAnamnese4Layout);
        ModalAnamnese4Layout.setHorizontalGroup(
            ModalAnamnese4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
        );
        ModalAnamnese4Layout.setVerticalGroup(
            ModalAnamnese4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1081, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel1.setText("Exibir Anamneses");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(580, 580, 580)
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel1)
                    .addContainerGap(30, Short.MAX_VALUE)))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        btnalterar.setText("Alterar");
        btnalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar Anamnese Por");

        JCBAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome do Paciente", "Diagnóstico", "Data da Consulta" }));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        JTAnamneses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Anamnese", "Paciente", "Psicólogo", "Diagnostico", "Data da Consulta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTAnamneses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTAnamnesesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTAnamneses);

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(btnalterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnalterar)
                    .addComponent(btnExcluir)
                    .addComponent(jLabel2)
                    .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jEImagePanel1, java.awt.BorderLayout.CENTER);

        PainelMenu.setBackground(new java.awt.Color(102, 102, 102));
        PainelMenu.setForeground(new java.awt.Color(102, 102, 102));

        BtnInicio.setText("Início");
        BtnInicio.setBackground(new java.awt.Color(102, 102, 102));
        BtnInicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnInicio.setForeground(new java.awt.Color(255, 255, 255));
        BtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioActionPerformed(evt);
            }
        });

        BtnManterPaciente.setText("Pacientes");
        BtnManterPaciente.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPaciente.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPacienteActionPerformed(evt);
            }
        });

        BtnManterConsulta.setText("Cadastrar Consulta");
        BtnManterConsulta.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterConsulta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterConsulta.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterConsultaActionPerformed(evt);
            }
        });

        BtnManterPsicologo.setText("Psicólogos");
        BtnManterPsicologo.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPsicologo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPsicologo.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPsicologoActionPerformed(evt);
            }
        });

        BtnExibirTodasAnamneses.setText("Exibir Todas as Anamneses");
        BtnExibirTodasAnamneses.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirTodasAnamneses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirTodasAnamneses.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirTodasAnamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirTodasAnamnesesActionPerformed(evt);
            }
        });

        BtnExibirTodasAnotacoes.setText("Exibir Todas as Anotações");
        BtnExibirTodasAnotacoes.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirTodasAnotacoes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirTodasAnotacoes.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirTodasAnotacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirTodasAnotacoesActionPerformed(evt);
            }
        });

        BtnSair.setText("Sair");
        BtnSair.setBackground(new java.awt.Color(102, 102, 102));
        BtnSair.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSair.setForeground(new java.awt.Color(255, 255, 255));
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelMenuLayout = new javax.swing.GroupLayout(PainelMenu);
        PainelMenu.setLayout(PainelMenuLayout);
        PainelMenuLayout.setHorizontalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnManterPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnManterConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirTodasAnamneses, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirTodasAnotacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnSair, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );
        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addComponent(BtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirTodasAnamneses, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirTodasAnotacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 134, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnalterarActionPerformed
        // TODO add your handling code here:
        //        if (JTAnamneses.getSelectedRow() != -1) {
        //            int modelRow = JTAnamneses.convertRowIndexToModel(JTAnamneses.getSelectedRow());
        //            int value = (Integer) JTAnamneses.getModel().getValueAt(modelRow, 0);
        //            AlterarAnamnesePacienteMenu.codanamnese = value;
        //            AlterarAnamnesePacienteMenu cp = new AlterarAnamnesePacienteMenu();
        //            cp.setVisible(true);
        //            this.dispose();
        //
        //        } else {
        //            JOptionPane.showMessageDialog(this, "Selecione uma consulta para alterar");
        //        }

        if (JTAnamneses.getSelectedRow() != -1) {
            Anamnese a2 = new Anamnese();
            AnamneseDAO dao2 = new AnamneseDAO();
            int modelRow = JTAnamneses.convertRowIndexToModel(JTAnamneses.getSelectedRow());
            int value = (Integer) JTAnamneses.getModel().getValueAt(modelRow, 0);
            this.codigoanamnese = value;
            //a2 = dao2.ReadAnamneseConsulta(codconsulta);
            //codanamnese = a2.getCodAnamnese();
            existe = readcampos(codigoanamnese);
            if (existe) {
//                LabelModalAnamnese.setText("Lendo dados da Anamnese Cadastrada na consulta");
            } else {
//                LabelModalAnamnese.setText(" Cadastrar anamnese na consulta");
            }

            ModalAnamnese4.setSize(1039, 600);
            jScrollPane3.getVerticalScrollBar().setUnitIncrement(15);
            ModalAnamnese4.setModal(true);
            ModalAnamnese4.setLocationRelativeTo(null);
            ModalAnamnese4.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma Anamnese para alterar" ,"ERRO!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnalterarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (JTAnamneses.getSelectedRow() != -1) {

            Anamnese a = new Anamnese();
            AnamneseDAO adao = new AnamneseDAO();
            int modelRow = JTAnamneses.convertRowIndexToModel(JTAnamneses.getSelectedRow());
            int value = (Integer) JTAnamneses.getModel().getValueAt(modelRow, 0);
            a.setCodAnamnese(value);
            boolean sucesso = adao.Delete(a);

            if (!sucesso) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao excluir a Anamnese, tente novamente mais tarde!" ,"ERRO!", JOptionPane.ERROR_MESSAGE);

            }

        } else {
             JOptionPane.showMessageDialog(this, "Selecione uma Anamnese para excluir" ,"ERRO!", JOptionPane.ERROR_MESSAGE);
        }
        ReadJTable();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        //System.out.println(JCBAtributo.getSelectedIndex());
        this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void JTAnamnesesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTAnamnesesMouseClicked
        btnalterar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }//GEN-LAST:event_JTAnamnesesMouseClicked

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
         TelaPrincipalAdm mp1 = new TelaPrincipalAdm();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnInicioActionPerformed

    private void BtnManterPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPacienteActionPerformed
        ManterPacienteAdm mp1 = new ManterPacienteAdm();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnManterPacienteActionPerformed

    private void BtnManterConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterConsultaActionPerformed
        ManterConsultasAdm mc = new ManterConsultasAdm();
        Util.SizeJanela(mc);
        this.dispose();
    }//GEN-LAST:event_BtnManterConsultaActionPerformed

    private void BtnManterPsicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPsicologoActionPerformed
        ManterPsicologoAdm mp = new ManterPsicologoAdm();
        //Util.SizeJanela(mp);
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnManterPsicologoActionPerformed

    private void BtnExibirTodasAnamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirTodasAnamnesesActionPerformed
        ExibirAnamnesesAdm  ma = new ExibirAnamnesesAdm();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnExibirTodasAnamnesesActionPerformed

    private void BtnExibirTodasAnotacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirTodasAnotacoesActionPerformed
        ExibirAnotacoesAdm ea = new ExibirAnotacoesAdm();
        Util.SizeJanela(ea);
        this.dispose();
    }//GEN-LAST:event_BtnExibirTodasAnotacoesActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        TelaLogin  tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void BtnSalvarAlteracoes8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalvarAlteracoes8ActionPerformed

    private void BtnCancelar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCancelar8ActionPerformed

    private void BtnCancelar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar7ActionPerformed
        ModalAnamnese4.dispose();
    }//GEN-LAST:event_BtnCancelar7ActionPerformed

    private void BtnSalvarAlteracoes7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes7ActionPerformed

        Alterar(codigoanamnese);
    }//GEN-LAST:event_BtnSalvarAlteracoes7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamnesesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamnesesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamnesesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamnesesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExibirAnamnesesAdm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar7;
    private javax.swing.JButton BtnCancelar8;
    private javax.swing.JButton BtnExibirTodasAnamneses;
    private javax.swing.JButton BtnExibirTodasAnotacoes;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSalvarAlteracoes7;
    private javax.swing.JButton BtnSalvarAlteracoes8;
    private javax.swing.JCheckBox CheckBoxAfeto1;
    private javax.swing.JCheckBox CheckBoxAnsiedade1;
    private javax.swing.JCheckBox CheckBoxAtencao1;
    private javax.swing.JCheckBox CheckBoxCulpa1;
    private javax.swing.JCheckBox CheckBoxDesanimo1;
    private javax.swing.JCheckBox CheckBoxHumor1;
    private javax.swing.JCheckBox CheckBoxIntegridadeSensorial1;
    private javax.swing.JCheckBox CheckBoxLuto1;
    private javax.swing.JCheckBox CheckBoxMedo1;
    private javax.swing.JCheckBox CheckBoxMemoria1;
    private javax.swing.JCheckBox CheckBoxPercepcao1;
    private javax.swing.JCheckBox CheckBoxRaiva1;
    private javax.swing.JCheckBox CheckBoxVolicao1;
    private com.github.lgooddatepicker.components.DatePicker DataInicio2;
    private javax.swing.JComboBox<String> JCBAtributo;
    private javax.swing.JComboBox<String> JCBPsicomotricidade2;
    private javax.swing.JTable JTAnamneses;
    private javax.swing.JLabel LabelEmail3;
    private javax.swing.JLabel LabelNome5;
    private javax.swing.JDialog ModalAnamnese4;
    private javax.swing.JPanel PainelDadosPaciente6;
    private javax.swing.JPanel PainelIdentificacaoPessoal6;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JComboBox<String> SubitaOuProgressiva2;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnalterar;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelInicioQueixa4;
    private javax.swing.JLabel labelInicioQueixa5;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtComoComecou2;
    private javax.swing.JTextField txtDiagnostico2;
    private javax.swing.JTextField txtDoencasConhecidas2;
    private javax.swing.JTextField txtEncaminhamento2;
    private javax.swing.JTextField txtHistoricoFamiliar2;
    private javax.swing.JTextField txtMedicamentosUtilizados2;
    private javax.swing.JTextField txtOqueMudou2;
    private javax.swing.JTextField txtQueixaPrincipal2;
    private javax.swing.JTextField txtQueixaSecundaria2;
    private javax.swing.JTextField txtSintomas2;
    // End of variables declaration//GEN-END:variables
}
