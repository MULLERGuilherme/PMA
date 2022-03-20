/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Validacoes.Deletar;
import Validacoes.Validar;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings.TimeIncrement;
import java.sql.Timestamp;
import java.time.LocalTime;
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
import model.bean.Vw_Consultas;
import model.dao.AnamneseDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.ViewsDAO;
import util.Util;

/**
 *
 * @author guimu
 */
public class ExibirConsultasManterPaciente extends javax.swing.JFrame {

    public static int codpaciente;
    private int codigoconsulta = -1;

    //Paginacao
    int PAGE_SIZE = 1;
    double tableRowCount;
    int totalPages = 1;
    int currentPage = 1;
    int startRow = 0;

    public void getCount() {
        ViewsDAO dao = new ViewsDAO();
        tableRowCount = dao.getRowCountTableExibirConsultasPaciente(Main.cod, this.codpaciente);
//        System.out.println("linhas");
//        System.out.println(tableRowCount);
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);

        }
        currentPage = 1;

    }

    public void getCountBusca(String Busca) {
//        ViewsDAO dao = new ViewsDAO();
//
//        tableRowCount = dao.getRowCountTableExibirAPacienteBusca(Main.cod, this.codpaciente, Busca);
//        //System.out.println(tableRowCount);
//        if (tableRowCount > 0) {
//            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);
//            
//
//        }
//         currentPage = 1;

    }

    public void getPageData(int pageNo) {

        currentPage = pageNo;

        //calculate starting row for pagination
        startRow = PAGE_SIZE * (pageNo - 1);

        ReadJTablePag(startRow, PAGE_SIZE);

    }

    public void getPageDataBusca(int pageNo, String Busca) {

//        currentPage = pageNo;
//
//        //calculate starting row for pagination
//        startRow = PAGE_SIZE * (pageNo - 1);
//
//        ReadJTableBuscaPag(Busca, startRow, PAGE_SIZE);
    }

    /**
     * Creates new form ExibirConsultasManterPaciente
     */
    public ExibirConsultasManterPaciente(int cod) {
        this.codpaciente = cod;
        this.getCount();
        initComponents();
        btnalterarconsulta.setEnabled(false);
        BtnExcluir.setEnabled(false);
        PacienteDAO pdao = new PacienteDAO();
        Paciente p;
        p = pdao.ReadPaciente(codpaciente);
        lNome.setText(p.getNome_Completo());

        DefaultTableModel dtmPacientes = (DefaultTableModel) JTConsultas.getModel();
        TableColumnModel cmod = JTConsultas.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTConsultas.setRowSorter(new TableRowSorter(dtmPacientes));

        SpinnerNumPaginas.setValue((int) currentPage);
        LabelQtdePaginas.setText("de " + totalPages);
        SpinnerLimite.setValue((int) PAGE_SIZE);
        //System.out.println(totalPages);
        this.getPageData(1);
    }

//    private void ReadJTable() {
//        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();
//        model.setNumRows(0);
//
//        ConsultaDAO cdao = new ConsultaDAO();
//
//        for (Consulta c : cdao.Read(codpaciente)) {
//
//            model.addRow(new Object[]{
//                c.getCodConsulta(),
//                Validar.fDatetime((Timestamp) c.getDataConsulta()),
//                c.getStatus(),});
//        }
//    }
    private void ReadJTablePag(int start, int size) {
        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();
        model.setNumRows(0);

        ViewsDAO vw = new ViewsDAO();

        for (Vw_Consultas v : vw.fetchBySizeExibirConsultasPaciente(codpaciente, Main.cod, start, size)) {

            model.addRow(new Object[]{
                v.getCodConsulta(),
                Validar.fDatetime((Timestamp) v.getDataConsulta()),
                v.getStatus(),});
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

        ModalAlterarConsulta = new javax.swing.JDialog();
        jPanel2 = new JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        BtnAlterarConsulta = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TimePickerSettings tps = new TimePickerSettings();
        tps.use24HourClockFormat();

        tps.generatePotentialMenuTimes(TimeIncrement.OneHour, LocalTime.of(8,0), LocalTime.of(20, 0));
        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker(null,tps);
        jComboBox1 = new javax.swing.JComboBox<>();
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
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        LabelModalAnamnese = new javax.swing.JLabel();
        jPanel1 = new JPanel();
        jEImagePanel1 = new LIB.JEImagePanel();
        jLabel1 = new javax.swing.JLabel();
        lNome = new javax.swing.JLabel();
        btnalterarconsulta = new javax.swing.JButton();
        BtnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTConsultas = new javax.swing.JTable();
        PainelPaginacao = new javax.swing.JPanel();
        LabelLimite = new javax.swing.JLabel();
        SpinnerLimite = new javax.swing.JSpinner();
        BtnVoltarBastante = new javax.swing.JButton();
        BtnVoltarPouco = new javax.swing.JButton();
        LabelPagina = new javax.swing.JLabel();
        SpinnerNumPaginas = new javax.swing.JSpinner();
        LabelQtdePaginas = new javax.swing.JLabel();
        BtnAvancarPouco = new javax.swing.JButton();
        BtnAvancarBastante = new javax.swing.JButton();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnPacientes = new javax.swing.JButton();
        BtnConsultas = new javax.swing.JButton();
        BtnExibirAnamneses = new javax.swing.JButton();
        BtnExibirAnotacoes = new javax.swing.JButton();
        BtnExibirAnotacoes1 = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(59, 131, 117));
        jPanel2.setForeground(new java.awt.Color(59, 131, 117));
        jPanel2.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(" ALTERAR CONSULTA");
        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );

        ModalAlterarConsulta.getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        BtnAlterarConsulta.setText("Alterar Consulta");

        jButton2.setText("Manter Anamnese");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Manter Antoação");

        jLabel2.setText("Data:");

        jLabel6.setText("Status");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(BtnAlterarConsulta)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(dateTimePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(BtnAlterarConsulta))
                .addGap(45, 45, 45))
        );

        ModalAlterarConsulta.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

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

        jLabel14.setText("*");
        jLabel14.setBackground(new java.awt.Color(255, 0, 0));
        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 0, 0));

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
                            .addComponent(jLabel16)
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
                                .addComponent(jLabel17)
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
                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))
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
                                            .addComponent(jLabel15)
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
                    .addComponent(jLabel16)
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
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
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
                            .addComponent(jLabel17))))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1454, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        jLabel1.setText("Exibindo Consultas Do Paciente");
        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        lNome.setText("jLabel2");
        lNome.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N

        btnalterarconsulta.setText("Alterar");
        btnalterarconsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalterarconsultaActionPerformed(evt);
            }
        });

        BtnExcluir.setText("Excluir");
        BtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirActionPerformed(evt);
            }
        });

        JTConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "CodConsulta", "Horário", "Status"
            }
        ));
        JTConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JTConsultasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(JTConsultas);

        PainelPaginacao.setOpaque(false);

        LabelLimite.setText("Limite");
        LabelLimite.setBackground(new java.awt.Color(204, 204, 204));

        SpinnerLimite.setModel(new javax.swing.SpinnerNumberModel(1, 1, 15, 1));
        SpinnerLimite.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerLimiteStateChanged(evt);
            }
        });

        BtnVoltarBastante.setText("<<");
        BtnVoltarBastante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarBastanteActionPerformed(evt);
            }
        });

        BtnVoltarPouco.setText("<");
        BtnVoltarPouco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarPoucoActionPerformed(evt);
            }
        });

        LabelPagina.setText("Página");
        LabelPagina.setBackground(new java.awt.Color(204, 204, 204));

        SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
        SpinnerNumPaginas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerNumPaginasStateChanged(evt);
            }
        });

        LabelQtdePaginas.setText("de X");
        LabelQtdePaginas.setBackground(new java.awt.Color(204, 204, 204));

        BtnAvancarPouco.setText(">");
        BtnAvancarPouco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAvancarPoucoActionPerformed(evt);
            }
        });

        BtnAvancarBastante.setText(">>");
        BtnAvancarBastante.setOpaque(false);
        BtnAvancarBastante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAvancarBastanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelPaginacaoLayout = new javax.swing.GroupLayout(PainelPaginacao);
        PainelPaginacao.setLayout(PainelPaginacaoLayout);
        PainelPaginacaoLayout.setHorizontalGroup(
            PainelPaginacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPaginacaoLayout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(LabelLimite)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SpinnerLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(BtnVoltarBastante)
                .addGap(18, 18, 18)
                .addComponent(BtnVoltarPouco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelPagina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SpinnerNumPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelQtdePaginas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnAvancarPouco)
                .addGap(18, 18, 18)
                .addComponent(BtnAvancarBastante)
                .addGap(8, 8, 8))
        );
        PainelPaginacaoLayout.setVerticalGroup(
            PainelPaginacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelPaginacaoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PainelPaginacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SpinnerNumPaginas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelQtdePaginas, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAvancarPouco)
                    .addComponent(BtnAvancarBastante)
                    .addComponent(BtnVoltarPouco)
                    .addComponent(BtnVoltarBastante)
                    .addComponent(SpinnerLimite, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lNome))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnalterarconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lNome))
                .addGap(48, 48, 48)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(btnalterarconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );

        getContentPane().add(jEImagePanel1, java.awt.BorderLayout.CENTER);

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

        BtnPacientes.setText("Pacientes");
        BtnPacientes.setBackground(new java.awt.Color(102, 102, 102));
        BtnPacientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnPacientes.setForeground(new java.awt.Color(255, 255, 255));
        BtnPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPacientesActionPerformed(evt);
            }
        });

        BtnConsultas.setText("Cadastrar Consultas");
        BtnConsultas.setBackground(new java.awt.Color(102, 102, 102));
        BtnConsultas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnConsultas.setForeground(new java.awt.Color(255, 255, 255));
        BtnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultasActionPerformed(evt);
            }
        });

        BtnExibirAnamneses.setText("Exibir Anamneses");
        BtnExibirAnamneses.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnamneses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnamneses.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirAnamnesesActionPerformed(evt);
            }
        });

        BtnExibirAnotacoes.setText("Exibir Anotações");
        BtnExibirAnotacoes.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnotacoes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnotacoes.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnotacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirAnotacoesActionPerformed(evt);
            }
        });

        BtnExibirAnotacoes1.setText("Sair");
        BtnExibirAnotacoes1.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnotacoes1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnotacoes1.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnotacoes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirAnotacoes1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelMenuLayout = new javax.swing.GroupLayout(PainelMenu);
        PainelMenu.setLayout(PainelMenuLayout);
        PainelMenuLayout.setHorizontalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirAnamneses, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacoes1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );
        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addComponent(BtnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnamneses, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacoes1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(256, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnalterarconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnalterarconsultaActionPerformed
        // TODO add your handling code here:
        if (JTConsultas.getSelectedRow() != -1) {

            int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
            int value = (Integer) JTConsultas.getModel().getValueAt(modelRow, 0);

            AlterarConsulta.codconsulta = value;
            AlterarConsulta.codpaciente = this.codpaciente;
            AlterarConsulta cp = new AlterarConsulta(this);
            cp.setLocationRelativeTo(null);
            cp.setResizable(false);
            cp.setVisible(true);

            // this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para alterar");
        }
    }//GEN-LAST:event_btnalterarconsultaActionPerformed

    private void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed
        // TODO add your handling code here:
        if (JTConsultas.getSelectedRow() != -1) {

            Consulta c = new Consulta();

            int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
            int value = (Integer) JTConsultas.getModel().getValueAt(modelRow, 0);

            c.setCodConsulta(value);
            boolean sucesso = Deletar.DConsulta(c);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Consulta Apagada com Sucesso");

            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para apagar");
        }
//        if (txtBusca.getText() == "") {
//            getCountBusca(txtBusca.getText());
//            SpinnerNumPaginas.setValue(currentPage);
//            LabelQtdePaginas.setText("de " + totalPages);
//            getPageDataBusca(currentPage, txtBusca.getText());
//        } else {
        getCount();
        SpinnerNumPaginas.setValue(currentPage);
        LabelQtdePaginas.setText("de " + totalPages);
        getPageData(currentPage);
//        }
    }//GEN-LAST:event_BtnExcluirActionPerformed

    private void JTConsultasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTConsultasMousePressed
        btnalterarconsulta.setEnabled(true);
        BtnExcluir.setEnabled(true);
        if (evt.getClickCount() == 2) {
            if (JTConsultas.getSelectedRow() != -1) {

                int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
                int value = (Integer) JTConsultas.getModel().getValueAt(modelRow, 0);

                AlterarConsulta.codconsulta = value;
                AlterarConsulta.codpaciente = this.codpaciente;
                AlterarConsulta cp = new AlterarConsulta(this);
                cp.setLocationRelativeTo(null);
                cp.setResizable(false);
                cp.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma consulta para alterar");
            }
        }
    }//GEN-LAST:event_JTConsultasMousePressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ModalAnamnese2.setSize(1039, 967);
        ModalAnamnese2.setModal(true);
        ModalAnamnese2.setLocationRelativeTo(null);
        ModalAnamnese2.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

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

        if (codigoconsulta != -1) {
            Alterar(codigoconsulta);
        }
        //Alterar(codigoanamnese);
        //LabelModalAnamnese.setText("Lendo dados da Anamnese Cadastrada na consulta");

    }//GEN-LAST:event_BtnSalvarAlteracoes4ActionPerformed

    private void SpinnerLimiteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerLimiteStateChanged
        // TODO add your handling code here:
//        if (txtBusca.getText() != "") {
//            PAGE_SIZE = (int) SpinnerLimite.getValue();
//            getCountBusca(txtBusca.getText());
//            SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
//            SpinnerNumPaginas.setValue((int) currentPage);
//
//            LabelQtdePaginas.setText("de " + totalPages);
//            getPageDataBusca(1, txtBusca.getText());
//        } else {
        PAGE_SIZE = (int) SpinnerLimite.getValue();
        getCount();

        SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
        SpinnerNumPaginas.setValue((int) currentPage);

        LabelQtdePaginas.setText("de " + totalPages);
        getPageData(1);
//        }
    }//GEN-LAST:event_SpinnerLimiteStateChanged

    private void BtnVoltarBastanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarBastanteActionPerformed
        if (currentPage != 1) {

            if (currentPage - 5 < 1) {
                getPageData(1);
            } else {
                getPageData(currentPage - 5);
            }

        }
        SpinnerNumPaginas.setValue((int) currentPage);
    }//GEN-LAST:event_BtnVoltarBastanteActionPerformed

    private void BtnVoltarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarPoucoActionPerformed
        // TODO add your handling code here:
        if (currentPage != 1) { //diferente da 1 pagina

            getPageData(currentPage - 1);

        }
        SpinnerNumPaginas.setValue((int) currentPage);
    }//GEN-LAST:event_BtnVoltarPoucoActionPerformed

    private void SpinnerNumPaginasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerNumPaginasStateChanged
        // TODO add your handling code here:

        getPageData((int) SpinnerNumPaginas.getValue());

        //
    }//GEN-LAST:event_SpinnerNumPaginasStateChanged

    private void BtnAvancarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvancarPoucoActionPerformed
        if (currentPage < totalPages) {

            getPageData(currentPage + 1);

        }
        SpinnerNumPaginas.setValue((int) currentPage);
    }//GEN-LAST:event_BtnAvancarPoucoActionPerformed

    private void BtnAvancarBastanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvancarBastanteActionPerformed
        // TODO add your handling code here:
        if (currentPage < totalPages) { //se tem pagina e é menor que a ultima

            if (currentPage + 5 > totalPages) {
                getPageData(totalPages);
            } else {
                getPageData(currentPage + 5);
            }

        }
        SpinnerNumPaginas.setValue((int) currentPage);
    }//GEN-LAST:event_BtnAvancarBastanteActionPerformed

    private void BtnExibirAnotacoes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacoes1ActionPerformed
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacoes1ActionPerformed

    private void BtnExibirAnotacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacoesActionPerformed
        ExibirAnotacoes ea = new ExibirAnotacoes();
        Util.SizeJanela(ea);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacoesActionPerformed

    private void BtnExibirAnamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnamnesesActionPerformed
        ExibirAnamneses ma = new ExibirAnamneses();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnamnesesActionPerformed

    private void BtnConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultasActionPerformed
        CadastrarConsulta2 mc = new CadastrarConsulta2();
        Util.SizeJanela(mc);
        this.dispose();
    }//GEN-LAST:event_BtnConsultasActionPerformed

    private void BtnPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPacientesActionPerformed
        ManterPaciente1 mp1 = new ManterPaciente1();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnPacientesActionPerformed

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        TelaPrincipal mp1 = new TelaPrincipal();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnVoltarActionPerformed

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
            java.util.logging.Logger.getLogger(ExibirConsultasManterPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExibirConsultasManterPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExibirConsultasManterPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExibirConsultasManterPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExibirConsultasManterPaciente(codpaciente).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterarConsulta;
    private javax.swing.JButton BtnAvancarBastante;
    private javax.swing.JButton BtnAvancarPouco;
    private javax.swing.JButton BtnCancelar4;
    private javax.swing.JButton BtnCancelar5;
    private javax.swing.JButton BtnConsultas;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnExibirAnamneses;
    private javax.swing.JButton BtnExibirAnotacoes;
    private javax.swing.JButton BtnExibirAnotacoes1;
    private javax.swing.JButton BtnPacientes;
    private javax.swing.JButton BtnSalvarAlteracoes4;
    private javax.swing.JButton BtnSalvarAlteracoes5;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JButton BtnVoltarBastante;
    private javax.swing.JButton BtnVoltarPouco;
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
    private javax.swing.JComboBox<String> JCBPsicomotricidade1;
    private javax.swing.JTable JTConsultas;
    private javax.swing.JLabel LabelEmail2;
    private javax.swing.JLabel LabelLimite;
    private javax.swing.JLabel LabelModalAnamnese;
    private javax.swing.JLabel LabelNome4;
    private javax.swing.JLabel LabelPagina;
    private javax.swing.JLabel LabelQtdePaginas;
    private javax.swing.JDialog ModalAlterarConsulta;
    private javax.swing.JDialog ModalAnamnese2;
    private javax.swing.JPanel PainelDadosPaciente4;
    private javax.swing.JPanel PainelIdentificacaoPessoal4;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelPaginacao;
    private javax.swing.JSpinner SpinnerLimite;
    private javax.swing.JSpinner SpinnerNumPaginas;
    private javax.swing.JComboBox<String> SubitaOuProgressiva1;
    private javax.swing.JButton btnalterarconsulta;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lNome;
    private javax.swing.JLabel labelInicioQueixa2;
    private javax.swing.JLabel labelInicioQueixa3;
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
