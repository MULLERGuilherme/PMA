/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import util.Util;
import Validacoes.Validar;
import java.awt.event.KeyEvent;
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
import model.dao.AnamneseDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.ViewsDAO;


/**
 *
 * @author guimu
 */
public class ExibirAnamneses extends javax.swing.JFrame {

    public boolean existe = false;
    public static int codigoanamnese;

    public ExibirAnamneses() {
        initComponents();
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

            txtQueixaPrincipal1.setText(a.getQueixaPrincipal());
            DataInicio1.setText((String) Validar.fDataNascBD((Date) a.getInicioDaQueixa()));
            SubitaOuProgressiva1.setSelectedItem(a.getSubitaOuProgressiva());
            txtQueixaSecundaria1.setText(a.getQueixasSecundarias());
            txtHistoricoFamiliar1.setText(a.getHistoricoFamiliar());
            txtDiagnostico1.setText(a.getDiagnostico());
            txtEncaminhamento1.setText(a.getEncaminhamento());
            txtDoencasConhecidas1.setText(a.getDoencasConhecidas());
            txtMedicamentosUtilizados1.setText(a.getMedicamentosUtilizados());
            txtOqueMudou1.setText(a.getOqueMudou());
            txtSintomas1.setText(a.getSintomas());
            txtComoComecou1.setText(a.getComoComecou());
            //Checkbox
            CheckBoxIntegridadeSensorial.setSelected(a.isQCIntegridadeSensorial());
            CheckBoxPercepcao.setSelected(a.isQCPercepcao());
            CheckBoxAtencao.setSelected(a.isQCAtencao());
            CheckBoxMemoria.setSelected(a.isQCMemoria());
            CheckBoxVolicao.setSelected(a.isQAEVolicao());
            CheckBoxAfeto.setSelected(a.isQAEAfeto());
            CheckBoxAnsiedade.setSelected(a.isQAEAnsiedade());
            CheckBoxMedo.setSelected(a.isQAEMedo());
            CheckBoxCulpa.setSelected(a.isQAECulpa());
            CheckBoxRaiva.setSelected(a.isQAERaiva());
            CheckBoxLuto.setSelected(a.isQAELuto());
            CheckBoxDesanimo.setSelected(a.isQAEDesanimo());

            JCBPsicomotricidade1.setSelectedItem(a.getPsicomotricidade());
            return true;
        }
        return false;
    }

    private void ReadJTable() {
        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
        model.setNumRows(0);
        ViewsDAO vwdao = new ViewsDAO();
        //ConsultaDAO cdao = new ConsultaDAO();
        //PacienteDAO pdao = new PacienteDAO();
        //Paciente p = new Paciente();

        for (Vw_Anamnese_Paciente v : vwdao.ReadAnamnesePaciente(Main.cod)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
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
        ViewsDAO vwdao = new ViewsDAO();

        for (Vw_Anamnese_Paciente v : vwdao.BuscaExibirAnamneses(Atributo, Busca, Main.cod)) {

            model.addRow(new Object[]{
                v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
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
            a.setQueixaPrincipal(txtQueixaPrincipal1.getText());
            a.setSubitaOuProgressiva((String) SubitaOuProgressiva1.getSelectedItem());

            //java.util.Date date = new java.util.Date();
            Object param = DataInicio1.getDate();
            a.setInicioDaQueixa(param);
            a.setQueixasSecundarias(txtQueixaSecundaria1.getText());
            a.setHistoricoFamiliar(txtHistoricoFamiliar1.getText());
            a.setDiagnostico(txtDiagnostico1.getText());
            a.setEncaminhamento(txtEncaminhamento1.getText());
            a.setDoencasConhecidas(txtDoencasConhecidas1.getText());
            a.setMedicamentosUtilizados(txtMedicamentosUtilizados1.getText());
            a.getConsulta().setCodConsulta(a2.getConsulta().getCodConsulta());
            a.setOqueMudou(txtOqueMudou1.getText());
            a.setSintomas(txtSintomas1.getText());
            a.setComoComecou(txtComoComecou1.getText());
            a.setQCIntegridadeSensorial(CheckBoxIntegridadeSensorial.isSelected());
            a.setQCPercepcao(CheckBoxPercepcao.isSelected());
            a.setQCAtencao(CheckBoxAtencao.isSelected());
            a.setQCMemoria(CheckBoxMemoria.isSelected());
            a.setQAEVolicao(CheckBoxVolicao.isSelected());
            a.setQAEAfeto(CheckBoxAfeto.isSelected());
            a.setQAEAnsiedade(CheckBoxAnsiedade.isSelected());
            a.setQAEMedo(CheckBoxMedo.isSelected());
            a.setQAECulpa(CheckBoxCulpa.isSelected());
            a.setQAERaiva(CheckBoxRaiva.isSelected());
            a.setQAELuto(CheckBoxLuto.isSelected());
            a.setQAEDesanimo(CheckBoxDesanimo.isSelected());
            a.setPsicomotricidade((String) JCBPsicomotricidade1.getSelectedItem());

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

        ModalAnamnese2 = new javax.swing.JDialog();
        PainelDadosPaciente4 = new javax.swing.JPanel();
        PainelIdentificacaoPessoal4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        BtnSalvarAlteracoes5 = new javax.swing.JButton();
        BtnCancelar4 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        SubitaOuProgressiva1 = new javax.swing.JComboBox<>();
        labelInicioQueixa2 = new javax.swing.JLabel();
        JCBPsicomotricidade1 = new javax.swing.JComboBox<>();
        labelInicioQueixa3 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        LabelEmail2 = new javax.swing.JLabel();
        txtComoComecou1 = new javax.swing.JTextField();
        LabelNome4 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtQueixaSecundaria1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtDiagnostico1 = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtHistoricoFamiliar1 = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtDoencasConhecidas1 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtSintomas1 = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        txtMedicamentosUtilizados1 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        txtOqueMudou1 = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtEncaminhamento1 = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        DataInicio1 = new com.github.lgooddatepicker.components.DatePicker();
        BtnCancelar5 = new javax.swing.JButton();
        BtnSalvarAlteracoes4 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtQueixaPrincipal1 = new javax.swing.JTextField();
        jEImagePanel3 = new LIB.JEImagePanel();
        jLabel10 = new javax.swing.JLabel();
        CheckBoxVolicao = new javax.swing.JCheckBox();
        CheckBoxAfeto = new javax.swing.JCheckBox();
        CheckBoxHumor = new javax.swing.JCheckBox();
        CheckBoxAnsiedade = new javax.swing.JCheckBox();
        CheckBoxMedo = new javax.swing.JCheckBox();
        CheckBoxCulpa = new javax.swing.JCheckBox();
        CheckBoxRaiva = new javax.swing.JCheckBox();
        CheckBoxLuto = new javax.swing.JCheckBox();
        CheckBoxDesanimo = new javax.swing.JCheckBox();
        CheckBoxIntegridadeSensorial = new javax.swing.JCheckBox();
        CheckBoxPercepcao = new javax.swing.JCheckBox();
        CheckBoxAtencao = new javax.swing.JCheckBox();
        CheckBoxMemoria = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        LabelModalAnamnese = new javax.swing.JLabel();
        jPanel1 = new JPanel();
        jLabel1 = new javax.swing.JLabel();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnManterPaciente = new javax.swing.JButton();
        BtnManterConsulta = new javax.swing.JButton();
        BtnManterPsicologo = new javax.swing.JButton();
        BtnExibiranamneses = new javax.swing.JButton();
        BtnExibirAnotacao = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        jEImagePanel1 = new LIB.JEImagePanel();
        btnalterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JCBAtributo = new javax.swing.JComboBox<>();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAnamneses = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();

        ModalAnamnese2.setResizable(false);

        PainelDadosPaciente4.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente4.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal4.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal4Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal4);
        PainelIdentificacaoPessoal4.setLayout(PainelIdentificacaoPessoal4Layout);
        PainelIdentificacaoPessoal4Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal4Layout.setVerticalGroup(
            PainelIdentificacaoPessoal4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel8.setText("* Campos Obrigatórios");
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes5.setText("Salvar Alterações");
        BtnSalvarAlteracoes5.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes5.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes5ActionPerformed(evt);
            }
        });

        BtnCancelar4.setText("Cancelar");
        BtnCancelar4.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar4.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar4ActionPerformed(evt);
            }
        });

        jLabel38.setText("Subita ou Progressiva:");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        SubitaOuProgressiva1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subita", "Progressiva"}));
        SubitaOuProgressiva1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelInicioQueixa2.setText("Psicomotricidade: ");
        labelInicioQueixa2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBPsicomotricidade1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Lento", "Agitado"}));
        JCBPsicomotricidade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelInicioQueixa3.setText("Queixas Afetivo-emocionais:");
        labelInicioQueixa3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel27.setText("Queixas Cognitivas:");
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEmail2.setText("Queixa Secundária:");
        LabelEmail2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtComoComecou1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelNome4.setText("Queixa Principal:");
        LabelNome4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel40.setText("Como começou:");
        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtQueixaSecundaria1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel41.setText("Diagnóstico:");
        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtDiagnostico1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel42.setText("Histórico Familiar:");
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtHistoricoFamiliar1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel43.setText("Doenças Conhecidas:");
        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtDoencasConhecidas1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel44.setText("Sintomas:");
        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtSintomas1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel45.setText("Medicamentos Utilizados:");
        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtMedicamentosUtilizados1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel46.setText("O que mudou:");
        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtOqueMudou1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel47.setText("Encaminhamento:");
        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEncaminhamento1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel48.setText("Data de Início:");
        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        DataInicio1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataInicio1.setPreferredSize(new java.awt.Dimension(160, 17));

        BtnCancelar5.setText("Cancelar");
        BtnCancelar5.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar5.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar5ActionPerformed(evt);
            }
        });

        BtnSalvarAlteracoes4.setText("Salvar Alterações");
        BtnSalvarAlteracoes4.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes4.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes4ActionPerformed(evt);
            }
        });

        jLabel9.setText("Campos Obrigatórios");
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtQueixaPrincipal1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jEImagePanel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/simboloma.png"))); // NOI18N

        javax.swing.GroupLayout jEImagePanel3Layout = new javax.swing.GroupLayout(jEImagePanel3);
        jEImagePanel3.setLayout(jEImagePanel3Layout);
        jEImagePanel3Layout.setHorizontalGroup(
            jEImagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jEImagePanel3Layout.setVerticalGroup(
            jEImagePanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel10.setText("Anamnese Psicológica");
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(59, 131, 117));

        CheckBoxVolicao.setText("Volição");
        CheckBoxVolicao.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxVolicao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxAfeto.setText("Afeto");
        CheckBoxAfeto.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxAfeto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxHumor.setText("Humor");
        CheckBoxHumor.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxHumor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxAnsiedade.setText("Ansiedade");
        CheckBoxAnsiedade.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxAnsiedade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxMedo.setText("Medo");
        CheckBoxMedo.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxMedo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxCulpa.setText("Culpa");
        CheckBoxCulpa.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxCulpa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxRaiva.setText("Raiva");
        CheckBoxRaiva.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxRaiva.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxLuto.setText("Luto");
        CheckBoxLuto.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxLuto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxDesanimo.setText("Desânimo");
        CheckBoxDesanimo.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxDesanimo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxIntegridadeSensorial.setText("Integridade Sensorial");
        CheckBoxIntegridadeSensorial.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxIntegridadeSensorial.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxPercepcao.setText("Percepção");
        CheckBoxPercepcao.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxPercepcao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxAtencao.setText("Atenção");
        CheckBoxAtencao.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxAtencao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        CheckBoxMemoria.setText("Memória");
        CheckBoxMemoria.setBackground(new java.awt.Color(255, 255, 255));
        CheckBoxMemoria.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel11.setText("*");
        jLabel11.setBackground(new java.awt.Color(255, 0, 0));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 0, 0));

        jLabel12.setText("*");
        jLabel12.setBackground(new java.awt.Color(255, 0, 0));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));

        jLabel13.setText("*");
        jLabel13.setBackground(new java.awt.Color(255, 0, 0));
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 0));

        jLabel15.setText("*");
        jLabel15.setBackground(new java.awt.Color(255, 0, 0));
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));

        jLabel16.setText("*");
        jLabel16.setBackground(new java.awt.Color(255, 0, 0));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));

        jLabel17.setText("*");
        jLabel17.setBackground(new java.awt.Color(255, 0, 0));
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 0, 0));

        jLabel18.setText("*");
        jLabel18.setBackground(new java.awt.Color(255, 0, 0));
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));

        LabelModalAnamnese.setText("jLabel3");

        javax.swing.GroupLayout PainelDadosPaciente4Layout = new javax.swing.GroupLayout(PainelDadosPaciente4);
        PainelDadosPaciente4.setLayout(PainelDadosPaciente4Layout);
        PainelDadosPaciente4Layout.setHorizontalGroup(
            PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar4)
                .addGap(42, 42, 42)
                .addComponent(BtnSalvarAlteracoes5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addComponent(jEImagePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel8))
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17)
                            .addComponent(jLabel11))
                        .addGap(19, 19, 19)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelNome4)
                            .addComponent(jLabel48))
                        .addGap(34, 34, 34)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                .addComponent(DataInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtQueixaPrincipal1, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)))
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnCancelar5)
                                .addGap(18, 18, 18)
                                .addComponent(BtnSalvarAlteracoes4))
                            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27))
                            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelModalAnamnese)
                                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addComponent(CheckBoxIntegridadeSensorial)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxPercepcao)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxAtencao)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxMemoria))
                                        .addComponent(jLabel40)
                                        .addComponent(jLabel41)
                                        .addComponent(jLabel47)
                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel43)
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel45)
                                                .addComponent(jLabel46)
                                                .addComponent(jLabel44))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtComoComecou1)
                                                .addComponent(txtDiagnostico1)
                                                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtSintomas1)
                                                    .addComponent(txtMedicamentosUtilizados1)
                                                    .addComponent(txtOqueMudou1)
                                                    .addComponent(txtDoencasConhecidas1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtEncaminhamento1)
                                                .addComponent(txtHistoricoFamiliar1)))
                                        .addComponent(labelInicioQueixa3)
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(LabelEmail2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtQueixaSecundaria1))
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addComponent(CheckBoxVolicao)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxAfeto)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxHumor)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxAnsiedade)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxMedo)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxCulpa)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxRaiva)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxLuto)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(CheckBoxDesanimo))
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addComponent(jLabel38)
                                            .addGap(32, 32, 32)
                                            .addComponent(SubitaOuProgressiva1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel16)
                                            .addGap(6, 6, 6)
                                            .addComponent(labelInicioQueixa2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(JCBPsicomotricidade1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        PainelDadosPaciente4Layout.setVerticalGroup(
            PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEImagePanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(LabelModalAnamnese)
                .addGap(28, 28, 28)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQueixaPrincipal1)
                    .addComponent(LabelNome4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel48)
                    .addComponent(DataInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmail2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQueixaSecundaria1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInicioQueixa3)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckBoxVolicao)
                    .addComponent(CheckBoxAfeto)
                    .addComponent(CheckBoxHumor)
                    .addComponent(CheckBoxAnsiedade)
                    .addComponent(CheckBoxMedo)
                    .addComponent(CheckBoxCulpa)
                    .addComponent(CheckBoxRaiva)
                    .addComponent(CheckBoxLuto)
                    .addComponent(CheckBoxDesanimo))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckBoxIntegridadeSensorial)
                    .addComponent(CheckBoxPercepcao)
                    .addComponent(CheckBoxAtencao)
                    .addComponent(CheckBoxMemoria))
                .addGap(19, 19, 19)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel38)
                    .addComponent(SubitaOuProgressiva1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInicioQueixa2)
                    .addComponent(JCBPsicomotricidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(txtComoComecou1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(txtDiagnostico1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(txtEncaminhamento1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(txtHistoricoFamiliar1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtDoencasConhecidas1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel44)
                    .addComponent(txtSintomas1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMedicamentosUtilizados1)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOqueMudou1)
                    .addComponent(jLabel46))
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCancelar5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSalvarAlteracoes4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel18))))
                .addGap(371, 371, 371)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoes5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ModalAnamnese2Layout = new javax.swing.GroupLayout(ModalAnamnese2.getContentPane());
        ModalAnamnese2.getContentPane().setLayout(ModalAnamnese2Layout);
        ModalAnamnese2Layout.setHorizontalGroup(
            ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 949, Short.MAX_VALUE)
            .addGroup(ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ModalAnamnese2Layout.createSequentialGroup()
                    .addComponent(PainelDadosPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 949, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ModalAnamnese2Layout.setVerticalGroup(
            ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1081, Short.MAX_VALUE)
            .addGroup(ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ModalAnamnese2Layout.createSequentialGroup()
                    .addComponent(PainelDadosPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EXIBIR ANAMNESES");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1368, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 359, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        PainelMenu.setBackground(new java.awt.Color(102, 102, 102));
        PainelMenu.setForeground(new java.awt.Color(102, 102, 102));

        BtnVoltar.setText("Início");
        BtnVoltar.setBackground(new java.awt.Color(102, 102, 102));
        BtnVoltar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        BtnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarActionPerformed(evt);
            }
        });

        BtnManterPaciente.setText("Manter Pacientes");
        BtnManterPaciente.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPaciente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPaciente.setFocusPainted(false);
        BtnManterPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPaciente.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPacienteActionPerformed(evt);
            }
        });

        BtnManterConsulta.setText("Cadastrar Consulta");
        BtnManterConsulta.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterConsulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterConsulta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterConsulta.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterConsultaActionPerformed(evt);
            }
        });

        BtnManterPsicologo.setText("Meus Dados");
        BtnManterPsicologo.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPsicologo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPsicologo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPsicologo.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPsicologoActionPerformed(evt);
            }
        });

        BtnExibiranamneses.setText("Exibir Anamneses");
        BtnExibiranamneses.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibiranamneses.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnExibiranamneses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibiranamneses.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibiranamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibiranamnesesActionPerformed(evt);
            }
        });

        BtnExibirAnotacao.setText("Exibir Anotações");
        BtnExibirAnotacao.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnotacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnExibirAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirAnotacaoActionPerformed(evt);
            }
        });

        BtnSair.setText("Sair");
        BtnSair.setBackground(new java.awt.Color(102, 102, 102));
        BtnSair.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
            .addComponent(BtnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnManterPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnManterConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnExibiranamneses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addComponent(BtnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibiranamneses, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

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

        jLabel2.setText("Buscar Anamnese Por:");

        JCBAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome do Paciente", "Diagnóstico", "Data da Consulta" }));

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
        });

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
                "ID Anamnese", "Paciente", "Diagnostico", "Data da Consulta"
            }
        ));
        jScrollPane1.setViewportView(JTAnamneses);

        jLabel14.setText("Buscar:");

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(JCBAtributo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnalterar, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnalterar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE))
                .addContainerGap())
        );

        getContentPane().add(jEImagePanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        TelaPrincipal mp1 = new TelaPrincipal();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void BtnManterPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPacienteActionPerformed
        // TODO add your handling code here:
        ManterPaciente1 mp1 = new ManterPaciente1();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnManterPacienteActionPerformed

    private void BtnManterConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterConsultaActionPerformed
        // TODO add your handling code here:
        CadastrarConsulta2 mc = new CadastrarConsulta2();
        Util.SizeJanela(mc);
        this.dispose();
    }//GEN-LAST:event_BtnManterConsultaActionPerformed

    private void BtnManterPsicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPsicologoActionPerformed

        ManterPsicologo mp = new ManterPsicologo();
        Util.SizeJanela(mp);
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnManterPsicologoActionPerformed

    private void BtnExibiranamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibiranamnesesActionPerformed
        // TODO add your handling code here:
        ExibirAnamneses ma = new ExibirAnamneses();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnExibiranamnesesActionPerformed

    private void BtnExibirAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacaoActionPerformed
        // TODO add your handling code here:
        ExibirAnotacoes ea = new ExibirAnotacoes();
        Util.SizeJanela(ea);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacaoActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        // TODO add your handling code here:
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

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
                //LabelModalAnamnese.setText("Lendo dados da Anamnese Cadastrada na consulta");
            } else {
                //LabelModalAnamnese.setText(" Cadastrar anamnese na consulta");
            }

            ModalAnamnese2.setSize(1039, 967);
            ModalAnamnese2.setModal(true);
            ModalAnamnese2.setLocationRelativeTo(null);
            ModalAnamnese2.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma anamnese para alterar");
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

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Anamnese Apagada com Sucesso");

            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma anamnese para excluir");
        }
        ReadJTable();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        //System.out.println(JCBAtributo.getSelectedIndex());
        this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
        }
    }//GEN-LAST:event_txtBuscaKeyPressed

    private void BtnSalvarAlteracoes5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalvarAlteracoes5ActionPerformed

    private void BtnCancelar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCancelar4ActionPerformed

    private void BtnCancelar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar5ActionPerformed
        ModalAnamnese2.dispose();
    }//GEN-LAST:event_BtnCancelar5ActionPerformed

    private void BtnSalvarAlteracoes4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes4ActionPerformed

        if (existe) {
            //Alterar(codigoanamnese);
            //LabelModalAnamnese.setText("Lendo dados da Anamnese Cadastrada na consulta");
        }
    }//GEN-LAST:event_BtnSalvarAlteracoes4ActionPerformed

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
            java.util.logging.Logger.getLogger(ExibirAnamneses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamneses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamneses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamneses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExibirAnamneses().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar4;
    private javax.swing.JButton BtnCancelar5;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnExibiranamneses;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSalvarAlteracoes4;
    private javax.swing.JButton BtnSalvarAlteracoes5;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JCheckBox CheckBoxAfeto;
    private javax.swing.JCheckBox CheckBoxAnsiedade;
    private javax.swing.JCheckBox CheckBoxAtencao;
    private javax.swing.JCheckBox CheckBoxCulpa;
    private javax.swing.JCheckBox CheckBoxDesanimo;
    private javax.swing.JCheckBox CheckBoxHumor;
    private javax.swing.JCheckBox CheckBoxIntegridadeSensorial;
    private javax.swing.JCheckBox CheckBoxLuto;
    private javax.swing.JCheckBox CheckBoxMedo;
    private javax.swing.JCheckBox CheckBoxMemoria;
    private javax.swing.JCheckBox CheckBoxPercepcao;
    private javax.swing.JCheckBox CheckBoxRaiva;
    private javax.swing.JCheckBox CheckBoxVolicao;
    private com.github.lgooddatepicker.components.DatePicker DataInicio1;
    private javax.swing.JComboBox<String> JCBAtributo;
    private javax.swing.JComboBox<String> JCBPsicomotricidade1;
    private javax.swing.JTable JTAnamneses;
    private javax.swing.JLabel LabelEmail2;
    private javax.swing.JLabel LabelModalAnamnese;
    private javax.swing.JLabel LabelNome4;
    private javax.swing.JDialog ModalAnamnese2;
    private javax.swing.JPanel PainelDadosPaciente4;
    private javax.swing.JPanel PainelIdentificacaoPessoal4;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JComboBox<String> SubitaOuProgressiva1;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnalterar;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelInicioQueixa2;
    private javax.swing.JLabel labelInicioQueixa3;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtComoComecou1;
    private javax.swing.JTextField txtDiagnostico1;
    private javax.swing.JTextField txtDoencasConhecidas1;
    private javax.swing.JTextField txtEncaminhamento1;
    private javax.swing.JTextField txtHistoricoFamiliar1;
    private javax.swing.JTextField txtMedicamentosUtilizados1;
    private javax.swing.JTextField txtOqueMudou1;
    private javax.swing.JTextField txtQueixaPrincipal1;
    private javax.swing.JTextField txtQueixaSecundaria1;
    private javax.swing.JTextField txtSintomas1;
    // End of variables declaration//GEN-END:variables
}
