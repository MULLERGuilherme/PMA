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
import connection.ConnectionFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import model.bean.Anamnese;
import model.bean.Consulta;
import model.bean.Paciente;
import model.bean.Psicologo;
import model.bean.Telefone;
import model.bean.Vw_TelefonesPacientes;
import model.dao.AnamneseDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.PsicologoDAO;
import model.dao.TelefoneDAO;
import model.dao.ViewsDAO;
import util.Util;
import static view.ManterPsicologo.codpsicologo;

/**
 *
 * @author guimu
 */
public class ManterPaciente1 extends javax.swing.JFrame {

    private boolean telefones = false;
    private boolean fone2 = false;
    private int codigopaciente = -1;
    private int codigoconsulta = -1;

    //Paginacao
    int PAGE_SIZE = 15;
    double tableRowCount;
    int totalPages = 1;
    int currentPage = 1;
    int startRow = 0;

    public void getCount() {
        ViewsDAO dao = new ViewsDAO();
        tableRowCount = dao.getRowCountTableManterPacientes();
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);

        }
        currentPage = 1;

    }

    public void getCountBusca(String Busca) {
        ViewsDAO dao = new ViewsDAO();

        tableRowCount = dao.getRowCountTableManterPacientesBusca(Busca);
        //System.out.println(tableRowCount);
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);

        }
        currentPage = 1;
    }

    public void getPageData(int pageNo) {

        currentPage = pageNo;
        ViewsDAO dao = new ViewsDAO();
        //calculate starting row for pagination
        startRow = PAGE_SIZE * (pageNo - 1);

        ReadJTablePag(startRow, PAGE_SIZE);

    }

    public void getPageDataBusca(int pageNo, String Busca) {

        currentPage = pageNo;

        //calculate starting row for pagination
        startRow = PAGE_SIZE * (pageNo - 1);

        ReadJTableBuscaPag(Busca, startRow, PAGE_SIZE);

    }

    public ManterPaciente1() {
        this.getCount();

        initComponents();

        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        p = dao.ReadPsicologo(Main.cod);
        jLabel11.setText(p.getNome_completo());
        String str = getFirstWord(jLabel11.getText());
        jLabel11.setText(str);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalNovoResolucaoMenor.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalMeusDados.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalAlterarResolucaoMenor.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalHelp.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalAlterarConsulta.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        BtnPacientes.setEnabled(false);
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTPacientes.getModel();
        BtnVisuAlterarDados.setEnabled(false);
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if (totalPages == 1) {
            BtnAvancarPouco.setEnabled(false);
            BtnAvancarBastante.setEnabled(false);
        }
        BtnCadastro.setEnabled(false);
        btnVisuAnamneses.setEnabled(false);
        btnVisuConsultas.setEnabled(false);
        btnVisuAnotacoes.setEnabled(false);
        TableColumnModel cmod = JTPacientes.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTPacientes.setRowSorter(new TableRowSorter(dtmPacientes));

        this.getPageData(1);
        SpinnerNumPaginas.setValue((int) currentPage);
        LabelQtdePaginas.setText("de " + totalPages);
        SpinnerLimite.setValue((int) PAGE_SIZE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ModalNovoResolucaoMenor = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        txtNome1 = new javax.swing.JTextField();
        LabelNome1 = new javax.swing.JLabel();
        PainelIdentificacaoPessoal1 = new javax.swing.JPanel();
        BtnSalvarAlteracoesNovo1 = new javax.swing.JButton();
        BtnCancelar1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        LabelEmail1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JTextField();
        LabelCPF2 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        LabelEstadoCivil2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        estadocivil1 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        Sexo1 = new javax.swing.JComboBox<>();
        jEImagePanel6 = new LIB.JEImagePanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        DataNasc2 = new com.github.lgooddatepicker.components.DatePicker();
        LabelCidade4 = new javax.swing.JLabel();
        TxtCidade1 = new javax.swing.JTextField();
        TxtEndereco1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        TxtProfissao1 = new javax.swing.JTextField();
        LabelReligiao1 = new javax.swing.JLabel();
        TxtReligiao1 = new javax.swing.JTextField();
        LabelEscolaridade1 = new javax.swing.JLabel();
        TxtEscolaridade1 = new javax.swing.JTextField();
        LabelCidade5 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        TxtTelefone1 = new javax.swing.JTextField();
        LabelCidade6 = new javax.swing.JLabel();
        TxtTelefone5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        PainelIdentificacaoPessoal2 = new javax.swing.JPanel();
        txtCPF1 = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        JCBUF = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        Profissão = new javax.swing.JLabel();
        ModalAnamnese2 = new javax.swing.JDialog();
        PainelDadosPaciente4 = new javax.swing.JPanel();
        PainelIdentificacaoPessoal4 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        BtnSalvarAlteracoes5 = new javax.swing.JButton();
        BtnCancelar6 = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        SubitaOuProgressiva1 = new javax.swing.JComboBox<>();
        labelInicioQueixa2 = new javax.swing.JLabel();
        JCBPsicomotricidade1 = new javax.swing.JComboBox<>();
        labelInicioQueixa3 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        LabelEmail4 = new javax.swing.JLabel();
        txtComoComecou1 = new javax.swing.JTextField();
        LabelNome5 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        txtQueixaSecundaria1 = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        txtDiagnostico1 = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        txtHistoricoFamiliar1 = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        txtDoencasConhecidas1 = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        txtSintomas1 = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        txtMedicamentosUtilizados1 = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtOqueMudou1 = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtEncaminhamento1 = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        DataInicio1 = new com.github.lgooddatepicker.components.DatePicker();
        BtnCancelar5 = new javax.swing.JButton();
        BtnSalvarAlteracoes4 = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        txtQueixaPrincipal1 = new javax.swing.JTextField();
        jEImagePanel3 = new LIB.JEImagePanel();
        jLabel75 = new javax.swing.JLabel();
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
        jLabel76 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        LabelModalAnamnese = new javax.swing.JLabel();
        ModalMeusDados = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        txtNome3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCRP = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        labeltelefone = new javax.swing.JLabel();
        TxtTelefone6 = new javax.swing.JTextField();
        labeltelefone2 = new javax.swing.JLabel();
        TxtTelefone7 = new javax.swing.JTextField();
        PainelIdentificacaoPessoal5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        BtnAlterar = new javax.swing.JButton();
        ModalHelp = new javax.swing.JDialog();
        jPanel4 = new JPanel();
        jPanel5 = new javax.swing.JPanel();
        ModalAlterarResolucaoMenor = new javax.swing.JDialog();
        jPanel6 = new javax.swing.JPanel();
        txtNome2 = new javax.swing.JTextField();
        LabelNome2 = new javax.swing.JLabel();
        PainelIdentificacaoPessoal3 = new javax.swing.JPanel();
        BtnSalvarAlteracoesAlterar2 = new javax.swing.JButton();
        BtnCancelar2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        LabelEmail2 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtEmail3 = new javax.swing.JTextField();
        LabelCPF3 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        LabelEstadoCivil3 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        estadocivil2 = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        Sexo2 = new javax.swing.JComboBox<>();
        jEImagePanel7 = new LIB.JEImagePanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        DataNasc3 = new com.github.lgooddatepicker.components.DatePicker();
        LabelCidade7 = new javax.swing.JLabel();
        TxtCidade2 = new javax.swing.JTextField();
        TxtEndereco2 = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        TxtProfissao2 = new javax.swing.JTextField();
        LabelReligiao2 = new javax.swing.JLabel();
        TxtReligiao2 = new javax.swing.JTextField();
        LabelEscolaridade2 = new javax.swing.JLabel();
        TxtEscolaridade2 = new javax.swing.JTextField();
        LabelCidade8 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        TxtTelefone2 = new javax.swing.JTextField();
        LabelCidade9 = new javax.swing.JLabel();
        TxtTelefone8 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        PainelIdentificacaoPessoal6 = new javax.swing.JPanel();
        txtCPF2 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        JCBUF1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        Profissão1 = new javax.swing.JLabel();
        ModalAlterarConsulta = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        TimePickerSettings tps = new TimePickerSettings();
        tps.use24HourClockFormat();
        tps.generatePotentialMenuTimes(TimeIncrement.OneHour,  LocalTime.of(8,0),  LocalTime.of(20,0));
        data = new com.github.lgooddatepicker.components.DateTimePicker(null,tps);
        BtnCadastrarConsulta = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelpaciente = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        JCBPagamento = new javax.swing.JComboBox<>();
        jPanel1 = new JPanel();
        PainelMeusDados = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnPacientes = new javax.swing.JButton();
        BtnExibirAnamneses = new javax.swing.JButton();
        BtnExibirAnotacoes = new javax.swing.JButton();
        BtnExibirAnotacoes1 = new javax.swing.JButton();
        jEImagePanel1 = new LIB.JEImagePanel();
        BtnNovo = new javax.swing.JButton();
        BtnVisuAlterarDados = new javax.swing.JButton();
        btnVisuAnamneses = new javax.swing.JButton();
        btnVisuConsultas = new javax.swing.JButton();
        btnVisuAnotacoes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTPacientes = new javax.swing.JTable();
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
        BtnHelp = new javax.swing.JButton();
        txtBusca = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        BtnCadastro = new javax.swing.JButton();

        ModalNovoResolucaoMenor.setResizable(false);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtNome1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNome1ActionPerformed(evt);
            }
        });
        txtNome1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNome1KeyTyped(evt);
            }
        });

        LabelNome1.setText("Nome:");
        LabelNome1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        PainelIdentificacaoPessoal1.setBackground(new java.awt.Color(59, 131, 117));

        BtnSalvarAlteracoesNovo1.setText("Salvar Alterações");
        BtnSalvarAlteracoesNovo1.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoesNovo1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoesNovo1.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoesNovo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoesNovo1ActionPerformed(evt);
            }
        });

        BtnCancelar1.setText("Cancelar");
        BtnCancelar1.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar1.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelIdentificacaoPessoal1Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal1);
        PainelIdentificacaoPessoal1.setLayout(PainelIdentificacaoPessoal1Layout);
        PainelIdentificacaoPessoal1Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelIdentificacaoPessoal1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar1)
                .addGap(18, 18, 18)
                .addComponent(BtnSalvarAlteracoesNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PainelIdentificacaoPessoal1Layout.setVerticalGroup(
            PainelIdentificacaoPessoal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelIdentificacaoPessoal1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelIdentificacaoPessoal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSalvarAlteracoesNovo1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel21.setText("*");
        jLabel21.setBackground(new java.awt.Color(255, 0, 0));
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 0, 0));

        LabelEmail1.setText("E-mail:");
        LabelEmail1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel22.setText("*");
        jLabel22.setBackground(new java.awt.Color(255, 0, 0));
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 0, 0));

        txtEmail1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCPF2.setText("CPF:");
        LabelCPF2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel23.setText("*");
        jLabel23.setBackground(new java.awt.Color(255, 0, 0));
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 0, 0));

        LabelEstadoCivil2.setText("Estado Civil:");
        LabelEstadoCivil2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel24.setText("*");
        jLabel24.setBackground(new java.awt.Color(255, 0, 0));
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 0, 0));

        estadocivil1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Casado","Divorciado","Separado","Solteiro" ,"Viuvo" }));
        estadocivil1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        estadocivil1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadocivil1ActionPerformed(evt);
            }
        });

        jLabel26.setText("Sexo:");
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Sexo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Feminino", "Masculino", "Não Definido"}));
        Sexo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Sexo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sexo1ActionPerformed(evt);
            }
        });

        jEImagePanel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/simboloma.png"))); // NOI18N

        javax.swing.GroupLayout jEImagePanel6Layout = new javax.swing.GroupLayout(jEImagePanel6);
        jEImagePanel6.setLayout(jEImagePanel6Layout);
        jEImagePanel6Layout.setHorizontalGroup(
            jEImagePanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jEImagePanel6Layout.setVerticalGroup(
            jEImagePanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jLabel29.setText("Data de Nascimento: ");
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel30.setText("*");
        jLabel30.setBackground(new java.awt.Color(255, 0, 0));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 0));

        DataNasc2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataNasc2.setPreferredSize(new java.awt.Dimension(160, 17));

        LabelCidade4.setText("Cidade:");
        LabelCidade4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtCidade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtEndereco1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtEndereco1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtEndereco1ActionPerformed(evt);
            }
        });

        jLabel32.setText("Cadastrar Paciente");
        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(59, 131, 117));

        TxtProfissao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelReligiao1.setText("Religião:");
        LabelReligiao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtReligiao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEscolaridade1.setText("Escolaridade:");
        LabelEscolaridade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtEscolaridade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtEscolaridade1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtEscolaridade1ActionPerformed(evt);
            }
        });

        LabelCidade5.setText("Telefone1:");
        LabelCidade5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel33.setText("*");
        jLabel33.setBackground(new java.awt.Color(255, 0, 0));
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 0, 0));

        TxtTelefone1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtTelefone1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTelefone1ActionPerformed(evt);
            }
        });
        TxtTelefone1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTelefone1KeyTyped(evt);
            }
        });

        LabelCidade6.setText("Telefone2:");
        LabelCidade6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtTelefone5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTelefone5KeyTyped(evt);
            }
        });

        jLabel2.setText("Campos Obrigatórios");
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel34.setText("*");
        jLabel34.setBackground(new java.awt.Color(255, 0, 0));
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 0, 0));

        PainelIdentificacaoPessoal2.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal2Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal2);
        PainelIdentificacaoPessoal2.setLayout(PainelIdentificacaoPessoal2Layout);
        PainelIdentificacaoPessoal2Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal2Layout.setVerticalGroup(
            PainelIdentificacaoPessoal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        try {
            txtCPF1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCPF1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCPF1KeyTyped(evt);
            }
        });

        jLabel1.setText("UF");
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC","AL", "AM", "AP","BA","CE","DF", "ES", "GO", "MA","MT","MS", "MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO", "Outro"}));

        jLabel6.setText("Endereço:");
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Profissão.setText("Profissão:");
        Profissão.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jEImagePanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel32)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 38, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22)
                            .addComponent(jLabel24)
                            .addComponent(jLabel30)
                            .addComponent(jLabel33))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel29)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(DataNasc2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(LabelCidade4)
                                    .addGap(18, 18, 18)
                                    .addComponent(TxtCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelEmail1)
                                    .addComponent(LabelNome1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel26)
                                            .addComponent(LabelCPF2))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JCBUF, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(Sexo1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(LabelEstadoCivil2)
                                .addGap(18, 18, 18)
                                .addComponent(estadocivil1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(Profissão))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TxtProfissao1)
                                        .addComponent(TxtEndereco1)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LabelEscolaridade1)
                                        .addComponent(LabelCidade5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TxtTelefone1)
                                        .addComponent(TxtEscolaridade1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LabelCidade6)
                                        .addComponent(LabelReligiao1))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TxtReligiao1)
                                        .addComponent(TxtTelefone5, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(37, 37, 37))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelIdentificacaoPessoal2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jEImagePanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNome1)
                    .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelEmail1)
                        .addComponent(jLabel22)
                        .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelCPF2)
                        .addComponent(jLabel23))
                    .addComponent(txtCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEstadoCivil2)
                    .addComponent(jLabel24)
                    .addComponent(estadocivil1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(Sexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(DataNasc2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(LabelCidade4)
                    .addComponent(TxtCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtEndereco1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtProfissao1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Profissão))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelReligiao1)
                    .addComponent(TxtReligiao1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEscolaridade1)
                    .addComponent(TxtEscolaridade1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCidade5, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel33)
                    .addComponent(TxtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelCidade6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtTelefone5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel34))
                .addGap(18, 18, 18)
                .addComponent(PainelIdentificacaoPessoal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(168, 168, 168)
                    .addComponent(PainelIdentificacaoPessoal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(411, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout ModalNovoResolucaoMenorLayout = new javax.swing.GroupLayout(ModalNovoResolucaoMenor.getContentPane());
        ModalNovoResolucaoMenor.getContentPane().setLayout(ModalNovoResolucaoMenorLayout);
        ModalNovoResolucaoMenorLayout.setHorizontalGroup(
            ModalNovoResolucaoMenorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ModalNovoResolucaoMenorLayout.setVerticalGroup(
            ModalNovoResolucaoMenorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

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

        jLabel63.setText("* Campos Obrigatórios");
        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes5.setText("Salvar Alterações");
        BtnSalvarAlteracoes5.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes5.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes5ActionPerformed(evt);
            }
        });

        BtnCancelar6.setText("Cancelar");
        BtnCancelar6.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar6.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar6ActionPerformed(evt);
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

        jLabel64.setText("Queixas Cognitivas:");
        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEmail4.setText("Queixa Secundária:");
        LabelEmail4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtComoComecou1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelNome5.setText("Queixa Principal:");
        LabelNome5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel65.setText("Como começou:");
        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtQueixaSecundaria1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel66.setText("Diagnóstico:");
        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtDiagnostico1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel67.setText("Histórico Familiar:");
        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtHistoricoFamiliar1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel68.setText("Doenças Conhecidas:");
        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtDoencasConhecidas1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel69.setText("Sintomas:");
        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtSintomas1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel70.setText("Medicamentos Utilizados:");
        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtMedicamentosUtilizados1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel71.setText("O que mudou:");
        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtOqueMudou1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel72.setText("Encaminhamento:");
        jLabel72.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEncaminhamento1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel73.setText("Data de Início:");
        jLabel73.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        jLabel74.setText("Campos Obrigatórios");
        jLabel74.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        jLabel75.setText("Anamnese Psicológica");
        jLabel75.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(59, 131, 117));

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

        jLabel76.setText("*");
        jLabel76.setBackground(new java.awt.Color(255, 0, 0));
        jLabel76.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 0, 0));

        jLabel79.setText("*");
        jLabel79.setBackground(new java.awt.Color(255, 0, 0));
        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 0, 0));

        jLabel80.setText("*");
        jLabel80.setBackground(new java.awt.Color(255, 0, 0));
        jLabel80.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 0, 0));

        jLabel81.setText("*");
        jLabel81.setBackground(new java.awt.Color(255, 0, 0));
        jLabel81.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 0, 0));

        jLabel82.setText("*");
        jLabel82.setBackground(new java.awt.Color(255, 0, 0));
        jLabel82.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 0, 0));

        LabelModalAnamnese.setText("jLabel3");

        javax.swing.GroupLayout PainelDadosPaciente4Layout = new javax.swing.GroupLayout(PainelDadosPaciente4);
        PainelDadosPaciente4.setLayout(PainelDadosPaciente4Layout);
        PainelDadosPaciente4Layout.setHorizontalGroup(
            PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar6)
                .addGap(42, 42, 42)
                .addComponent(BtnSalvarAlteracoes5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addComponent(jEImagePanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel63))
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel81)
                            .addComponent(jLabel76))
                        .addGap(19, 19, 19)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelNome5)
                            .addComponent(jLabel73))
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
                                .addComponent(jLabel82)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel74)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(BtnCancelar5)
                                .addGap(18, 18, 18)
                                .addComponent(BtnSalvarAlteracoes4))
                            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jLabel64))
                            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                .addComponent(jLabel79)
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
                                        .addComponent(jLabel65)
                                        .addComponent(jLabel66)
                                        .addComponent(jLabel72)
                                        .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel68)
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel70)
                                                .addComponent(jLabel71)
                                                .addComponent(jLabel69))
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
                                            .addComponent(LabelEmail4)
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
                                            .addComponent(jLabel80)
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
                    .addComponent(jLabel75, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(LabelModalAnamnese)
                .addGap(28, 28, 28)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQueixaPrincipal1)
                    .addComponent(LabelNome5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel76))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(jLabel73)
                    .addComponent(DataInicio1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmail4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQueixaSecundaria1))
                .addGap(18, 18, 18)
                .addComponent(labelInicioQueixa3)
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
                .addComponent(jLabel64)
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
                    .addComponent(jLabel79)
                    .addComponent(jLabel80))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel65)
                    .addComponent(txtComoComecou1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(txtDiagnostico1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel72)
                    .addComponent(txtEncaminhamento1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(txtHistoricoFamiliar1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(txtDoencasConhecidas1))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel69)
                    .addComponent(txtSintomas1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMedicamentosUtilizados1)
                    .addComponent(jLabel70))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOqueMudou1)
                    .addComponent(jLabel71))
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCancelar5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSalvarAlteracoes4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel74)
                            .addComponent(jLabel82))))
                .addGap(371, 371, 371)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        ModalMeusDados.setResizable(false);

        jLabel3.setText("Nome:");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtNome3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNome3ActionPerformed(evt);
            }
        });
        txtNome3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNome3KeyTyped(evt);
            }
        });

        jLabel4.setText("CRP:");
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtCRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCRPActionPerformed(evt);
            }
        });
        txtCRP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCRPKeyTyped(evt);
            }
        });

        jLabel5.setText("E-mail:");
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labeltelefone.setText("Telefone:");
        labeltelefone.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTelefone6KeyTyped(evt);
            }
        });

        labeltelefone2.setText("Telefone 2:");
        labeltelefone2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTelefone7KeyTyped(evt);
            }
        });

        PainelIdentificacaoPessoal5.setBackground(new java.awt.Color(59, 131, 117));

        jLabel15.setText("DADOS DO PSICÓLOGO");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));

        jLabel35.setText("NOME");
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PainelIdentificacaoPessoal5Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal5);
        PainelIdentificacaoPessoal5.setLayout(PainelIdentificacaoPessoal5Layout);
        PainelIdentificacaoPessoal5Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelIdentificacaoPessoal5Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        PainelIdentificacaoPessoal5Layout.setVerticalGroup(
            PainelIdentificacaoPessoal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        BtnAlterar.setText("Alterar");
        BtnAlterar.setBackground(new java.awt.Color(59, 131, 117));
        BtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ModalMeusDadosLayout = new javax.swing.GroupLayout(ModalMeusDados.getContentPane());
        ModalMeusDados.getContentPane().setLayout(ModalMeusDadosLayout);
        ModalMeusDadosLayout.setHorizontalGroup(
            ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModalMeusDadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
            .addGroup(ModalMeusDadosLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(labeltelefone)
                    .addComponent(labeltelefone2)
                    .addComponent(jLabel3)
                    .addComponent(TxtTelefone6, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(TxtTelefone7)
                    .addComponent(txtEmail2)
                    .addComponent(txtCRP)
                    .addComponent(txtNome3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ModalMeusDadosLayout.setVerticalGroup(
            ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModalMeusDadosLayout.createSequentialGroup()
                .addComponent(PainelIdentificacaoPessoal5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtNome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtCRP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(24, 24, 24)
                .addComponent(txtEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labeltelefone)
                .addGap(18, 18, 18)
                .addComponent(TxtTelefone6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labeltelefone2)
                .addGap(18, 18, 18)
                .addComponent(TxtTelefone7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ModalHelp.setResizable(false);

        jPanel4.setBackground(new java.awt.Color(59, 131, 117));
        jPanel4.setForeground(new java.awt.Color(59, 131, 117));
        jPanel4.setPreferredSize(new java.awt.Dimension(1080, 89));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        ModalHelp.getContentPane().add(jPanel4, java.awt.BorderLayout.NORTH);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );

        ModalHelp.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        ModalAlterarResolucaoMenor.setResizable(false);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtNome2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNome2ActionPerformed(evt);
            }
        });
        txtNome2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNome2KeyTyped(evt);
            }
        });

        LabelNome2.setText("Nome:");
        LabelNome2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        PainelIdentificacaoPessoal3.setBackground(new java.awt.Color(59, 131, 117));

        BtnSalvarAlteracoesAlterar2.setText("Salvar Alterações");
        BtnSalvarAlteracoesAlterar2.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoesAlterar2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoesAlterar2.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoesAlterar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoesAlterar2ActionPerformed(evt);
            }
        });

        BtnCancelar2.setText("Cancelar");
        BtnCancelar2.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar2.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelIdentificacaoPessoal3Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal3);
        PainelIdentificacaoPessoal3.setLayout(PainelIdentificacaoPessoal3Layout);
        PainelIdentificacaoPessoal3Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelIdentificacaoPessoal3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar2)
                .addGap(18, 18, 18)
                .addComponent(BtnSalvarAlteracoesAlterar2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PainelIdentificacaoPessoal3Layout.setVerticalGroup(
            PainelIdentificacaoPessoal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelIdentificacaoPessoal3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelIdentificacaoPessoal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnSalvarAlteracoesAlterar2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel25.setText("*");
        jLabel25.setBackground(new java.awt.Color(255, 0, 0));
        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 0, 0));

        LabelEmail2.setText("E-mail:");
        LabelEmail2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel27.setText("*");
        jLabel27.setBackground(new java.awt.Color(255, 0, 0));
        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 0, 0));

        txtEmail3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCPF3.setText("CPF:");
        LabelCPF3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel28.setText("*");
        jLabel28.setBackground(new java.awt.Color(255, 0, 0));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 0, 0));

        LabelEstadoCivil3.setText("Estado Civil:");
        LabelEstadoCivil3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel31.setText("*");
        jLabel31.setBackground(new java.awt.Color(255, 0, 0));
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 0, 0));

        estadocivil2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Casado","Divorciado","Separado","Solteiro" ,"Viuvo" }));
        estadocivil2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        estadocivil2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estadocivil2ActionPerformed(evt);
            }
        });

        jLabel36.setText("Sexo:");
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Sexo2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Feminino", "Masculino", "Não Definido"}));
        Sexo2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Sexo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Sexo2ActionPerformed(evt);
            }
        });

        jEImagePanel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/simboloma.png"))); // NOI18N

        javax.swing.GroupLayout jEImagePanel7Layout = new javax.swing.GroupLayout(jEImagePanel7);
        jEImagePanel7.setLayout(jEImagePanel7Layout);
        jEImagePanel7Layout.setHorizontalGroup(
            jEImagePanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jEImagePanel7Layout.setVerticalGroup(
            jEImagePanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jLabel37.setText("Data de Nascimento: ");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel39.setText("*");
        jLabel39.setBackground(new java.awt.Color(255, 0, 0));
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 0, 0));

        DataNasc3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataNasc3.setPreferredSize(new java.awt.Dimension(160, 17));

        LabelCidade7.setText("Cidade:");
        LabelCidade7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtCidade2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtEndereco2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtEndereco2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtEndereco2ActionPerformed(evt);
            }
        });

        jLabel40.setText("Dados do Paciente");
        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(59, 131, 117));

        TxtProfissao2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelReligiao2.setText("Religião:");
        LabelReligiao2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtReligiao2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEscolaridade2.setText("Escolaridade:");
        LabelEscolaridade2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtEscolaridade2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtEscolaridade2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtEscolaridade2ActionPerformed(evt);
            }
        });

        LabelCidade8.setText("Telefone1:");
        LabelCidade8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel41.setText("*");
        jLabel41.setBackground(new java.awt.Color(255, 0, 0));
        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 0, 0));

        TxtTelefone2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtTelefone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTelefone2ActionPerformed(evt);
            }
        });
        TxtTelefone2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTelefone2KeyTyped(evt);
            }
        });

        LabelCidade9.setText("Telefone2:");
        LabelCidade9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtTelefone8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTelefone8KeyTyped(evt);
            }
        });

        jLabel7.setText("Campos Obrigatórios");
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel42.setText("*");
        jLabel42.setBackground(new java.awt.Color(255, 0, 0));
        jLabel42.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 0, 0));

        PainelIdentificacaoPessoal6.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal6Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal6);
        PainelIdentificacaoPessoal6.setLayout(PainelIdentificacaoPessoal6Layout);
        PainelIdentificacaoPessoal6Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 811, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal6Layout.setVerticalGroup(
            PainelIdentificacaoPessoal6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        try {
            txtCPF2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCPF2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtCPF2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCPF2KeyTyped(evt);
            }
        });

        jLabel8.setText("UF");
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBUF1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC","AL", "AM", "AP","BA","CE","DF", "ES", "GO", "MA","MT","MS", "MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO", "Outro"}));

        jLabel9.setText("Endereço:");
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Profissão1.setText("Profissão:");
        Profissão1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jEImagePanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(jLabel40)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel42)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel25)
                            .addComponent(jLabel27)
                            .addComponent(jLabel31)
                            .addComponent(jLabel39)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                                    .addComponent(jLabel37)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(DataNasc3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addComponent(LabelCidade7)
                                    .addGap(18, 18, 18)
                                    .addComponent(TxtCidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelEmail2)
                                    .addComponent(LabelNome2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel28)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel36)
                                            .addComponent(LabelCPF3))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel6Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JCBUF1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(Sexo2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCPF2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(LabelEstadoCivil3)
                                .addGap(18, 18, 18)
                                .addComponent(estadocivil2, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel9)
                                        .addComponent(Profissão1))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TxtProfissao2)
                                        .addComponent(TxtEndereco2)))
                                .addGroup(jPanel6Layout.createSequentialGroup()
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LabelEscolaridade2)
                                        .addComponent(LabelCidade8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TxtTelefone2)
                                        .addComponent(TxtEscolaridade2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(LabelCidade9)
                                        .addComponent(LabelReligiao2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(TxtReligiao2)
                                        .addComponent(TxtTelefone8, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(40, 40, 40))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelIdentificacaoPessoal6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jEImagePanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNome2)
                    .addComponent(txtNome2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelEmail2)
                        .addComponent(jLabel27)
                        .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LabelCPF3)
                        .addComponent(jLabel28))
                    .addComponent(txtCPF2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEstadoCivil3)
                    .addComponent(jLabel31)
                    .addComponent(estadocivil2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(Sexo2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jLabel39)
                    .addComponent(DataNasc3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBUF1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(LabelCidade7)
                    .addComponent(TxtCidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtEndereco2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtProfissao2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Profissão1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelReligiao2)
                    .addComponent(TxtReligiao2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEscolaridade2)
                    .addComponent(TxtEscolaridade2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCidade8, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(jLabel41)
                    .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelCidade9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtTelefone8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addComponent(PainelIdentificacaoPessoal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(168, 168, 168)
                    .addComponent(PainelIdentificacaoPessoal6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(411, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout ModalAlterarResolucaoMenorLayout = new javax.swing.GroupLayout(ModalAlterarResolucaoMenor.getContentPane());
        ModalAlterarResolucaoMenor.getContentPane().setLayout(ModalAlterarResolucaoMenorLayout);
        ModalAlterarResolucaoMenorLayout.setHorizontalGroup(
            ModalAlterarResolucaoMenorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ModalAlterarResolucaoMenorLayout.setVerticalGroup(
            ModalAlterarResolucaoMenorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        ModalAlterarConsulta.setResizable(false);

        BtnCadastrarConsulta.setText("Cadastrar");
        BtnCadastrarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCadastrarConsultaActionPerformed(evt);
            }
        });

        jLabel10.setText("Data:");

        jPanel7.setBackground(new java.awt.Color(59, 131, 117));
        jPanel7.setForeground(new java.awt.Color(59, 131, 117));
        jPanel7.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Cadastrar Consulta");
        jLabel12.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );

        jLabel13.setText("Paciente:");

        labelpaciente.setText("Nome do Paciente");

        jLabel17.setText("Pagamento:");

        JCBPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Efetuado" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(BtnCadastrarConsulta))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelpaciente))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel17)
                                    .addGap(18, 18, 18)
                                    .addComponent(JCBPagamento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(labelpaciente))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(JCBPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(BtnCadastrarConsulta)
                .addGap(26, 26, 26))
        );

        ModalAlterarConsulta.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        PainelMeusDados.setBackground(new java.awt.Color(102, 102, 102));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/UserIconBranco90x90.png"))); // NOI18N
        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PainelMeusDadosLayout = new javax.swing.GroupLayout(PainelMeusDados);
        PainelMeusDados.setLayout(PainelMeusDadosLayout);
        PainelMeusDadosLayout.setHorizontalGroup(
            PainelMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelMeusDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelMeusDadosLayout.setVerticalGroup(
            PainelMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelMeusDadosLayout.createSequentialGroup()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PainelMeusDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("PACIENTES");
        jLabel16.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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

        BtnPacientes.setText("Pacientes");
        BtnPacientes.setBackground(new java.awt.Color(102, 102, 102));
        BtnPacientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnPacientes.setForeground(new java.awt.Color(255, 255, 255));
        BtnPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPacientesActionPerformed(evt);
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
            .addComponent(BtnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnExibirAnamneses, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacoes1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addComponent(BtnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnamneses, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacoes1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(368, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        BtnNovo.setText("Novo");
        BtnNovo.setBackground(new java.awt.Color(204, 204, 204));
        BtnNovo.setToolTipText("Criar um Novo Paciente");
        BtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoActionPerformed(evt);
            }
        });

        BtnVisuAlterarDados.setText(" Dados");
        BtnVisuAlterarDados.setBackground(new java.awt.Color(204, 204, 204));
        BtnVisuAlterarDados.setToolTipText("Exibe os Dados de um Paciente selecionado");
        BtnVisuAlterarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVisuAlterarDadosActionPerformed(evt);
            }
        });

        btnVisuAnamneses.setText("Anamneses");
        btnVisuAnamneses.setBackground(new java.awt.Color(204, 204, 204));
        btnVisuAnamneses.setToolTipText("Exibe as Anamneses de um Paciente selecionado");
        btnVisuAnamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisuAnamnesesActionPerformed(evt);
            }
        });

        btnVisuConsultas.setText("Consultas");
        btnVisuConsultas.setBackground(new java.awt.Color(204, 204, 204));
        btnVisuConsultas.setToolTipText("Exibe as Consultas de um Paciente selecionado");
        btnVisuConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisuConsultasActionPerformed(evt);
            }
        });

        btnVisuAnotacoes.setText("Anotaçoes");
        btnVisuAnotacoes.setBackground(new java.awt.Color(204, 204, 204));
        btnVisuAnotacoes.setToolTipText("Exibe as Anotações de um Paciente selecionado");
        btnVisuAnotacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisuAnotacoesActionPerformed(evt);
            }
        });

        JTPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome Completo", "Email", "Telefone 1", "Telefone 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        JTPacientes.getTableHeader().setReorderingAllowed(false);
        JTPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTPacientesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JTPacientesMousePressed(evt);
            }
        });
        JTPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTPacientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(JTPacientes);

        PainelPaginacao.setOpaque(false);

        LabelLimite.setText("Limite");
        LabelLimite.setBackground(new java.awt.Color(204, 204, 204));

        SpinnerLimite.setModel(new javax.swing.SpinnerNumberModel(1, 1, 15, 1));
        SpinnerLimite.setToolTipText("Alterar Limite de Pacientes que podem aparecer na Tabela");
        SpinnerLimite.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerLimiteStateChanged(evt);
            }
        });

        BtnVoltarBastante.setText("<<");
        BtnVoltarBastante.setToolTipText("Voltar 5 páginas");
        BtnVoltarBastante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarBastanteActionPerformed(evt);
            }
        });

        BtnVoltarPouco.setText("<");
        BtnVoltarPouco.setToolTipText("Voltar 1 página");
        BtnVoltarPouco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarPoucoActionPerformed(evt);
            }
        });

        LabelPagina.setText("Página");
        LabelPagina.setBackground(new java.awt.Color(204, 204, 204));

        SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
        SpinnerNumPaginas.setToolTipText("Selecionar uma Página");
        SpinnerNumPaginas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                SpinnerNumPaginasStateChanged(evt);
            }
        });

        LabelQtdePaginas.setText("de X");
        LabelQtdePaginas.setBackground(new java.awt.Color(204, 204, 204));

        BtnAvancarPouco.setText(">");
        BtnAvancarPouco.setToolTipText("Avançar 1 página");
        BtnAvancarPouco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAvancarPoucoActionPerformed(evt);
            }
        });

        BtnAvancarBastante.setText(">>");
        BtnAvancarBastante.setOpaque(false);
        BtnAvancarBastante.setToolTipText("Avançar 5 páginas");
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

        BtnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/SimboloHelp.png"))); // NOI18N
        BtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHelpActionPerformed(evt);
            }
        });

        txtBusca.setToolTipText("");
        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
        });

        jLabel14.setText("Buscar:");

        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("Realiza a Busca de um Paciente");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        BtnCadastro.setText("Cadastrar Consulta");
        BtnCadastro.setBackground(new java.awt.Color(204, 204, 204));
        BtnCadastro.setToolTipText("Cadastra uma nova Consulta para o Paciente selecionado");
        BtnCadastro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCadastroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createSequentialGroup()
                                .addGap(0, 203, Short.MAX_VALUE)
                                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVisuAnotacoes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVisuAnamneses, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnVisuAlterarDados, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(BtnHelp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisuConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createSequentialGroup()
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jEImagePanel1Layout.createSequentialGroup()
                            .addGap(50, 50, 50)
                            .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jEImagePanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(BtnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(BtnNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnVisuAlterarDados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisuAnamneses, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisuConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisuAnotacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        BtnNovo.getAccessibleContext().setAccessibleDescription("");

        getContentPane().add(jEImagePanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public void ReadJTable() {
//
//        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();
//
//        model.setNumRows(0);
//        ViewsDAO vwdao = new ViewsDAO();
//        Object[] linha = null;
//        String fones = null;
//        String[] fones2 = null;
//        for (Vw_TelefonesPacientes vw : vwdao.ReadTelefonesPacientes()) {
//            fones = vw.getTelefone().getNumero();
//            if (fones.contains(",")) {
//
//                fones2 = fones.split(",");
//                linha = new Object[]{
//                    vw.getPaciente().getCodPaciente(),
//                    vw.getPaciente().getNome_Completo(),
//                    vw.getPaciente().getEmail(),
//                    fones2[0],
//                    fones2[1]
//                };
//            } else {
//                linha = new Object[]{
//                    vw.getPaciente().getCodPaciente(),
//                    vw.getPaciente().getNome_Completo(),
//                    vw.getPaciente().getEmail(),
//                    vw.getTelefone().getNumero(),
//                    null
//                };
//
//            }
//            model.addRow(linha);
//            fones = null;
//            fones2 = null;
//
//        }
//    }
    public void ReadJTablePag(int start, int size) {

        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();

        model.setNumRows(0);
        ViewsDAO vwdao = new ViewsDAO();
        Object[] linha = null;
        String fones = null;
        String[] fones2 = null;
        for (Vw_TelefonesPacientes vw : vwdao.fetchBySizeMP(start, size)) {
            fones = vw.getTelefone().getNumero();
            if (fones.contains(",")) {

                fones2 = fones.split(",");
                linha = new Object[]{
                    vw.getPaciente().getCodPaciente(),
                    vw.getPaciente().getNome_Completo(),
                    vw.getPaciente().getEmail(),
                    fones2[0],
                    fones2[1]
                };
            } else {
                linha = new Object[]{
                    vw.getPaciente().getCodPaciente(),
                    vw.getPaciente().getNome_Completo(),
                    vw.getPaciente().getEmail(),
                    vw.getTelefone().getNumero(),
                    null
                };

            }
            model.addRow(linha);
            fones = null;
            fones2 = null;

        }
    }

//    public void ReadJTableBusca(String Busca) {
//
//        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();
//
//        model.setNumRows(0);
//
//        ViewsDAO vwdao = new ViewsDAO();
//        Object[] linha = null;
//        String fones = null;
//        String[] fones2 = null;
//        for (Vw_TelefonesPacientes vw : vwdao.BuscaManterPacienteOA(Busca)) {
//            fones = vw.getTelefone().getNumero();
//            if (fones.contains(",")) {
//
//                fones2 = fones.split(",");
//                linha = new Object[]{
//                    vw.getPaciente().getCodPaciente(),
//                    vw.getPaciente().getNome_Completo(),
//                    vw.getPaciente().getEmail(),
//                    fones2[0],
//                    fones2[1]
//                };
//            } else {
//                linha = new Object[]{
//                    vw.getPaciente().getCodPaciente(),
//                    vw.getPaciente().getNome_Completo(),
//                    vw.getPaciente().getEmail(),
//                    vw.getTelefone().getNumero(),
//                    null
//                };
//
//            }
//            model.addRow(linha);
//            fones = null;
//            fones2 = null;
//
//        }
//    }
    public void ReadJTableBuscaPag(String Busca, int startrow, int size) {

        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();

        model.setNumRows(0);

        ViewsDAO vwdao = new ViewsDAO();
        Object[] linha = null;
        String fones = null;
        String[] fones2 = null;
        for (Vw_TelefonesPacientes vw : vwdao.fetchBySizeBuscaMP(startrow, size, Busca)) {
            fones = vw.getTelefone().getNumero();
            if (fones.contains(",")) {

                fones2 = fones.split(",");
                linha = new Object[]{
                    vw.getPaciente().getCodPaciente(),
                    vw.getPaciente().getNome_Completo(),
                    vw.getPaciente().getEmail(),
                    fones2[0],
                    fones2[1]
                };
            } else {
                linha = new Object[]{
                    vw.getPaciente().getCodPaciente(),
                    vw.getPaciente().getNome_Completo(),
                    vw.getPaciente().getEmail(),
                    vw.getTelefone().getNumero(),
                    null
                };

            }
            model.addRow(linha);
            fones = null;
            fones2 = null;

        }
    }
    private void BtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoActionPerformed
        // TODO add your handling code here:
//        ModalNovo.setSize(950, 950);
//        DataNasc.setFont(new Font("Tahoma", Font.BOLD, 18));
//        ModalNovo.setModal(true);
//        ModalNovo.setLocationRelativeTo(null);
//        ModalNovo.setVisible(true);
//        MaskFormatter formatter = new MaskFormatter();
//        try {
//            formatter = new MaskFormatter("###.###.###-##");
//            formatter.setPlaceholderCharacter('_');
//        } catch (ParseException ex) {
//            Logger.getLogger(ManterPaciente1.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        txtCPF1.setFormatterFactory(new DefaultFormatterFactory(formatter));
        ModalNovoResolucaoMenor.setSize(840, 660);
        DataNasc2.setFont(new Font("Tahoma", Font.BOLD, 18));
        ModalNovoResolucaoMenor.setResizable(false);
        JCBUF.setSelectedItem("PR");
        ModalNovoResolucaoMenor.setModal(true);
        ModalNovoResolucaoMenor.setLocationRelativeTo(null);
        ModalNovoResolucaoMenor.setVisible(true);

    }//GEN-LAST:event_BtnNovoActionPerformed

    private void BtnVisuAlterarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVisuAlterarDadosActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer) JTPacientes.getModel().getValueAt(modelRow, 0);
            this.codigopaciente = value;
            PacienteDAO dao = new PacienteDAO();
            ViewsDAO vwdao = new ViewsDAO();
            Paciente p = dao.ReadPaciente(codigopaciente);
            Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();
            v = vwdao.ReadTelefonesPacientes(codigopaciente);
            txtNome2.setText(p.getNome_Completo());
            Date date = (Date) p.getDataNasc();

            //LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
            DataNasc3.setDate(date.toLocalDate());
            //DataNasc1.setDate((LocalDate) p.getDataNasc());
            Sexo2.setSelectedItem(p.getSexo());
            estadocivil2.setSelectedItem(p.getEstadoCivil());
            TxtCidade2.setText(p.getCidade());
            txtCPF2.setText(p.getCPF());
            TxtEndereco2.setText(p.getEndereco());
            TxtProfissao2.setText(p.getProfissao());
            TxtReligiao2.setText(p.getReligiao());
            TxtEscolaridade2.setText(p.getEscolaridade());
            String fones = null;
            String[] fones2 = null;
            fones = v.getTelefone().getNumero();
            if (fones.contains(",")) {
                fones2 = fones.split(",");
                TxtTelefone2.setText(fones2[0]);
                TxtTelefone8.setText(fones2[1]);
            } else {
                TxtTelefone2.setText(fones);
                TxtTelefone8.setText("");
            }
            JCBUF1.setSelectedItem(p.getUF());
            txtEmail3.setText(p.getEmail());

            DataNasc3.setFont(new Font("Tahoma", Font.BOLD, 18));
            ModalAlterarResolucaoMenor.setSize(826, 660);
            ModalAlterarResolucaoMenor.setModal(true);
            ModalAlterarResolucaoMenor.setLocationRelativeTo(null);
            ModalAlterarResolucaoMenor.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para alterar");
        }
    }//GEN-LAST:event_BtnVisuAlterarDadosActionPerformed

    private void btnVisuAnamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisuAnamnesesActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            boolean dadosvalidos = true;
            //ExibirAnamnesesPaciente.codpaciente = ((int) JTPacientes.getModel().getValueAt(JTPacientes.getSelectedRow(),0));

            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer) JTPacientes.getModel().getValueAt(modelRow, 0);
            this.codigopaciente = value;

            ExibirAnamneses2 ea = new ExibirAnamneses2(codigopaciente);
            Util.SizeJanela(ea);
            ea.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_btnVisuAnamnesesActionPerformed

    private void btnVisuConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisuConsultasActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer) JTPacientes.getModel().getValueAt(modelRow, 0);
            ExibirConsultasManterPaciente.codpaciente = value;

            ExibirConsultasManterPaciente cp = new ExibirConsultasManterPaciente(value);
            Util.SizeJanela(cp);
            cp.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_btnVisuConsultasActionPerformed

    private void btnVisuAnotacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisuAnotacoesActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            boolean dadosvalidos = true;
            //ExibirAnotacoesPaciente.codpaciente = ((int) JTPacientes.getModel().getValueAt(JTPacientes.getSelectedRow(),0));
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer) JTPacientes.getModel().getValueAt(modelRow, 0);
            VisualizarAnotacoes ea = new VisualizarAnotacoes(value);
            Util.SizeJanela(ea);
            ea.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_btnVisuAnotacoesActionPerformed

    private void JTPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTPacientesMouseClicked
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer) JTPacientes.getModel().getValueAt(modelRow, 0);
            this.codigopaciente = value;
        }
    }//GEN-LAST:event_JTPacientesMouseClicked

    private void JTPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPacientesKeyReleased
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer) JTPacientes.getModel().getValueAt(modelRow, 0);
            this.codigopaciente = value;

        }
    }//GEN-LAST:event_JTPacientesKeyReleased

    public void clearNovoR() {
        //limpar a tela
        txtNome1.setText(null);
        txtEmail1.setText(null);
        txtCPF1.setText(null);
        estadocivil1.setSelectedIndex(0);
        DataNasc2.clear();
        Sexo1.setSelectedIndex(0);
        TxtProfissao1.setText(null);
        TxtReligiao1.setText(null);
        TxtEscolaridade1.setText(null);
        TxtEndereco1.setText(null);
        TxtCidade1.setText(null);
        TxtTelefone1.setText(null);
        TxtTelefone5.setText(null);
    }

    public void clearAlterarR() {
        //limpar a tela
        txtNome3.setText(null);
        txtEmail2.setText(null);
        txtCPF2.setText(null);
        estadocivil2.setSelectedIndex(0);
        DataNasc3.clear();
        Sexo2.setSelectedIndex(0);
        TxtProfissao2.setText(null);
        TxtReligiao2.setText(null);
        TxtEscolaridade2.setText(null);
        TxtEndereco2.setText(null);
        TxtCidade2.setText(null);
        TxtTelefone2.setText(null);
        TxtTelefone8.setText(null);
    }

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        TelaPrincipal mp1 = new TelaPrincipal();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void txtNome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNome1ActionPerformed

    private void estadocivil1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadocivil1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadocivil1ActionPerformed

    private void Sexo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sexo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sexo1ActionPerformed

    private void TxtEscolaridade1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtEscolaridade1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtEscolaridade1ActionPerformed

    private void TxtTelefone1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTelefone1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTelefone1ActionPerformed

    private void BtnSalvarAlteracoesNovo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoesNovo1ActionPerformed
        boolean dadosvalidos = true;

        Paciente p = new Paciente();
        PacienteDAO dao = new PacienteDAO();
        Telefone tf = new Telefone();
        Telefone tf2 = new Telefone();
        TelefoneDAO tfdao = new TelefoneDAO();
        String msg = "Existem campos com formatos Inválidos\n\nFavor Verificar os campos:";
        //System.out.println((String) txtCPF1.getValue());
        String cpf = null;
        if ((String) txtCPF1.getValue() != null) {
            cpf = (String) txtCPF1.getValue();
            cpf = cpf.replace(".", "").replace("-", "");
        }
        if (!Validar.vCamposVazios(null, txtNome1, txtEmail1, cpf, DataNasc2, TxtTelefone1)) {
            if (Validar.vNome(txtNome1.getText())) {
                p.setNome_Completo(txtNome1.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNome Invalido: " + txtNome1.getText();
            }

            if (Validar.vEmail(txtEmail1.getText())) {
                p.setEmail(txtEmail1.getText());
            } else {
                dadosvalidos = false;
                msg += "\nEmail Invalido: " + txtEmail1.getText();
            }

            if (Validar.vCPF(cpf)) {
                p.setCPF(cpf);
            } else {
                dadosvalidos = false;
                msg += "\nCPF Invalido: " + txtCPF1.getText();
            }

            p.setEstadoCivil((String) estadocivil1.getSelectedItem());

            // System.out.println("data "+ date_time);
            //java.util.Date date = new java.util.Date();
            Object param = DataNasc2.getDate();
            //System.out.println("era aki");
            //System.out.println(param);
            p.setDataNasc(param);
            p.setSexo((String) Sexo1.getSelectedItem());
            p.setProfissao(TxtProfissao1.getText());
            p.setReligiao(TxtReligiao1.getText());
            p.setEscolaridade(TxtEscolaridade1.getText());
            p.setEndereco(TxtEndereco1.getText());
            p.setCidade(TxtCidade1.getText());
            p.setUF((String) JCBUF.getSelectedItem());

            if (Validar.vTelefone(TxtTelefone1.getText())) {
                tf.setNumero(TxtTelefone1.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNúmero de Telefone Invalido: " + TxtTelefone1.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
            }
            if (!TxtTelefone5.getText().isEmpty()) {
                if (Validar.vTelefone(TxtTelefone5.getText())) {
                    tf2.setNumero(TxtTelefone5.getText());
                } else {
                    dadosvalidos = false;
                    msg += "\nNúmero de Telefone 2 Invalido: " + TxtTelefone5.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
                }
            }
            if (dadosvalidos) {
                if (dao.Create(p)) {

                    p = dao.ReadPaciente(p.getCPF());

                    tf.setPaciente(p);
                    if (tfdao.CreatePc(tf)) {
                        if (!TxtTelefone5.getText().isEmpty()) {
                            tf2.setPaciente(p);
                            if (tfdao.CreatePc(tf2)) {
                                //System.out.println("cai aki");
                                if (txtBusca.getText() != "") {
                                    getCountBusca(txtBusca.getText());
                                    SpinnerNumPaginas.setValue(currentPage);
                                    LabelQtdePaginas.setText("de " + totalPages);
                                    getPageDataBusca(currentPage, txtBusca.getText());
                                } else {
                                    getCount();
                                    SpinnerNumPaginas.setValue(currentPage);
                                    LabelQtdePaginas.setText("de " + totalPages);
                                    getPageData(currentPage);
                                }
                                this.clearNovoR();
                                ModalNovoResolucaoMenor.dispose();
                            } else {
                                //System.out.println("cai no else");
                                tfdao.HardDeleteTelefone(p);
                                dao.HardDelete(p);
                            }
                        } else {
                            if (txtBusca.getText() != "") {
                                getCountBusca(txtBusca.getText());
                                SpinnerNumPaginas.setValue(currentPage);
                                LabelQtdePaginas.setText("de " + totalPages);
                                getPageDataBusca(currentPage, txtBusca.getText());
                            } else {
                                getCount();
                                SpinnerNumPaginas.setValue(currentPage);
                                LabelQtdePaginas.setText("de " + totalPages);
                                getPageData(currentPage);
                            }
                            this.clearNovoR();
                            ModalNovoResolucaoMenor.dispose();
                        }

                    } else {
                        dao.HardDelete(p);
                    }

                }

            } else {
                JOptionPane.showMessageDialog(null, msg, "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (totalPages > 1) {
            if (currentPage < totalPages) {
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);
            }
        }
    }//GEN-LAST:event_BtnSalvarAlteracoesNovo1ActionPerformed

    private void BtnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar1ActionPerformed
        ModalNovoResolucaoMenor.dispose();
    }//GEN-LAST:event_BtnCancelar1ActionPerformed

    private void BtnSalvarAlteracoes5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalvarAlteracoes5ActionPerformed

    private void BtnCancelar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCancelar6ActionPerformed

    private void BtnCancelar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar5ActionPerformed
        ModalAnamnese2.dispose();
    }//GEN-LAST:event_BtnCancelar5ActionPerformed

    private void BtnSalvarAlteracoes4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes4ActionPerformed

        if (codigoconsulta != -1) {
            Alterar(codigoconsulta);
        }
        //LabelModalAnamnese.setText("Lendo dados da Anamnese Cadastrada na consulta");

    }//GEN-LAST:event_BtnSalvarAlteracoes4ActionPerformed

    private void JTPacientesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTPacientesMousePressed
        BtnVisuAlterarDados.setEnabled(true);
        BtnCadastro.setEnabled(true);
        btnVisuAnamneses.setEnabled(true);
        btnVisuConsultas.setEnabled(true);
        btnVisuAnotacoes.setEnabled(true);
        if (evt.getClickCount() == 2) {
            if (JTPacientes.getSelectedRow() != -1) {
                int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
                int value = (Integer) JTPacientes.getModel().getValueAt(modelRow, 0);
                this.codigopaciente = value;
                PacienteDAO dao = new PacienteDAO();
                ViewsDAO vwdao = new ViewsDAO();
                Paciente p = dao.ReadPaciente(codigopaciente);
                Vw_TelefonesPacientes v = new Vw_TelefonesPacientes();
                v = vwdao.ReadTelefonesPacientes(codigopaciente);
                txtNome2.setText(p.getNome_Completo());
                Date date = (Date) p.getDataNasc();

                //LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
                DataNasc3.setDate(date.toLocalDate());
                //DataNasc1.setDate((LocalDate) p.getDataNasc());
                Sexo2.setSelectedItem(p.getSexo());
                estadocivil2.setSelectedItem(p.getEstadoCivil());
                TxtCidade2.setText(p.getCidade());
                txtCPF2.setText(p.getCPF());
                TxtEndereco2.setText(p.getEndereco());
                TxtProfissao2.setText(p.getProfissao());
                TxtReligiao2.setText(p.getReligiao());
                TxtEscolaridade2.setText(p.getEscolaridade());
                String fones = null;
                String[] fones2 = null;
                fones = v.getTelefone().getNumero();
                if (fones.contains(",")) {
                    fones2 = fones.split(",");
                    TxtTelefone2.setText(fones2[0]);
                    TxtTelefone8.setText(fones2[1]);
                } else {
                    TxtTelefone2.setText(fones);
                    TxtTelefone8.setText("");
                }
                JCBUF1.setSelectedItem(p.getUF());

                txtEmail3.setText(p.getEmail());

                DataNasc3.setFont(new Font("Tahoma", Font.BOLD, 18));
                ModalAlterarResolucaoMenor.setSize(826, 660);
                ModalAlterarResolucaoMenor.setModal(true);
                ModalAlterarResolucaoMenor.setLocationRelativeTo(null);
                ModalAlterarResolucaoMenor.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Selecione um paciente para alterar");
            }
        }
    }//GEN-LAST:event_JTPacientesMousePressed

    private void txtNome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNome3ActionPerformed

    private void txtCRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCRPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCRPActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        ModalMeusDados.setSize(540, 620);
        ModalMeusDados.setModal(true);
        TxtTelefone7.setVisible(false);
        labeltelefone2.setVisible(false);
        readpsicologo();
        ModalMeusDados.setLocationRelativeTo(null);
        ModalMeusDados.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked
    public void readpsicologo() {
        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        p = dao.ReadPsicologo(Main.cod);
        codpsicologo = p.getCodPsicologo();
        List<Telefone> t = new ArrayList<>();
        TelefoneDAO tdao = new TelefoneDAO();
        t = tdao.ReadTPsicologo(p.getCodPsicologo());
        txtNome3.setText(p.getNome_completo());
        jLabel35.setText(p.getNome_completo());

        txtCRP.setText(p.getCRP());
        txtEmail2.setText(p.getEmail());
        TxtTelefone6.setText(t.get(0).getNumero());

        if (t.size() == 2) {
            TxtTelefone7.setVisible(true);
            labeltelefone2.setVisible(true);
            TxtTelefone7.setText(t.get(1).getNumero());

        }
    }
    private void BtnVoltarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarPoucoActionPerformed
        // TODO add your handling code here:
        if (currentPage != 1) { //diferente da 1 pagina
            if (txtBusca.getText() != "") {
                getPageDataBusca(currentPage - 1, txtBusca.getText());
                BtnVisuAlterarDados.setEnabled(false);
                BtnCadastro.setEnabled(false);
                btnVisuAnamneses.setEnabled(false);
                btnVisuConsultas.setEnabled(false);
                btnVisuAnotacoes.setEnabled(false);
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);

            } else {
                getPageData(currentPage - 1);
                BtnVisuAlterarDados.setEnabled(false);
                BtnCadastro.setEnabled(false);
                btnVisuAnamneses.setEnabled(false);
                btnVisuConsultas.setEnabled(false);
                btnVisuAnotacoes.setEnabled(false);
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);
            }

        }
        SpinnerNumPaginas.setValue((int) currentPage);
        if (currentPage == 1) {
            BtnVoltarPouco.setEnabled(false);
            BtnVoltarBastante.setEnabled(false);
        }
    }//GEN-LAST:event_BtnVoltarPoucoActionPerformed

    private void BtnAvancarBastanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvancarBastanteActionPerformed
        // TODO add your handling code here:
        if (currentPage < totalPages) { //se tem pagina e é menor que a ultima
            if (txtBusca.getText() != "") {
                if (currentPage + 5 > totalPages) {
                    getPageDataBusca(totalPages, txtBusca.getText());
                    BtnVoltarPouco.setEnabled(true);
                    BtnVoltarBastante.setEnabled(true);
                    if (currentPage == totalPages) {
                        BtnAvancarPouco.setEnabled(false);
                        BtnAvancarBastante.setEnabled(false);
                    }
                } else {
                    getPageDataBusca(currentPage + 5, txtBusca.getText());
                    BtnVoltarPouco.setEnabled(true);
                    BtnVoltarBastante.setEnabled(true);
                    if (currentPage == totalPages) {
                        BtnAvancarPouco.setEnabled(false);
                        BtnAvancarBastante.setEnabled(false);
                    }
                }

            } else {
                if (currentPage + 5 > totalPages) {
                    getPageData(totalPages);
                    BtnVoltarPouco.setEnabled(true);
                    BtnVoltarBastante.setEnabled(true);
                    if (currentPage == totalPages) {
                        BtnAvancarPouco.setEnabled(false);
                        BtnAvancarBastante.setEnabled(false);
                    }
                } else {
                    getPageData(currentPage + 5);
                    BtnVoltarPouco.setEnabled(true);
                    BtnVoltarBastante.setEnabled(true);
                    if (currentPage == totalPages) {
                        BtnAvancarPouco.setEnabled(false);
                        BtnAvancarBastante.setEnabled(false);
                    }
                }
            }

        }
        SpinnerNumPaginas.setValue((int) currentPage);
    }//GEN-LAST:event_BtnAvancarBastanteActionPerformed

    private void BtnVoltarBastanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarBastanteActionPerformed
        if (currentPage != 1) {
            if (txtBusca.getText() != "") {
                if (currentPage - 5 < 1) {
                    getPageDataBusca(1, txtBusca.getText());
                    BtnVisuAlterarDados.setEnabled(false);
                    BtnCadastro.setEnabled(false);
                    btnVisuAnamneses.setEnabled(false);
                    btnVisuConsultas.setEnabled(false);
                    btnVisuAnotacoes.setEnabled(false);
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);

                } else {
                    getPageDataBusca(currentPage - 5, txtBusca.getText());
                    BtnVisuAlterarDados.setEnabled(false);
                    BtnCadastro.setEnabled(false);
                    btnVisuAnamneses.setEnabled(false);
                    btnVisuConsultas.setEnabled(false);
                    btnVisuAnotacoes.setEnabled(false);
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);
                    if (currentPage == 1) {
                        BtnVoltarPouco.setEnabled(false);
                        BtnVoltarBastante.setEnabled(false);
                    }
                }

            } else {
                if (currentPage - 5 < 1) {
                    getPageData(1);
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);
                    if (currentPage == 1) {
                        BtnVoltarPouco.setEnabled(false);
                        BtnVoltarBastante.setEnabled(false);
                    }
                } else {
                    getPageData(currentPage - 5);
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);
                    if (currentPage == 1) {
                        BtnVoltarPouco.setEnabled(false);
                        BtnVoltarBastante.setEnabled(false);
                    }
                }
            }

        }

        SpinnerNumPaginas.setValue((int) currentPage);
        if (currentPage == 1) {
            BtnVoltarPouco.setEnabled(false);
            BtnVoltarBastante.setEnabled(false);
        }
    }//GEN-LAST:event_BtnVoltarBastanteActionPerformed

    private void BtnAvancarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvancarPoucoActionPerformed
        if (currentPage < totalPages) {
            if (txtBusca.getText() != "") {
                getPageDataBusca(currentPage + 1, txtBusca.getText());
                BtnVisuAlterarDados.setEnabled(false);
                BtnCadastro.setEnabled(false);
                btnVisuAnamneses.setEnabled(false);
                btnVisuConsultas.setEnabled(false);
                btnVisuAnotacoes.setEnabled(false);
                BtnVoltarPouco.setEnabled(true);
                BtnVoltarBastante.setEnabled(true);
                if (currentPage == totalPages) {
                    BtnAvancarPouco.setEnabled(false);
                    BtnAvancarBastante.setEnabled(false);
                }
            } else {
                getPageData(currentPage + 1);
                BtnVoltarPouco.setEnabled(true);
                BtnVoltarBastante.setEnabled(true);
                if (currentPage == totalPages) {
                    BtnAvancarPouco.setEnabled(false);
                    BtnAvancarBastante.setEnabled(false);
                }
            }

        }
        SpinnerNumPaginas.setValue((int) currentPage);
    }//GEN-LAST:event_BtnAvancarPoucoActionPerformed

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        // TODO add your handling code here:
        boolean dadosvalidos = true;

        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        Telefone tf = new Telefone();
        Telefone tf2 = new Telefone();
        TelefoneDAO tfdao = new TelefoneDAO();
        String msg = "Existem campos com formatos Inválidos\n\nFavor Verificar os campos:";

        if (!Validar.vCamposVaziosManterPSI(this, txtNome3, txtEmail2, txtCRP, TxtTelefone6)) {
            if (Validar.vNome(txtNome3.getText())) {
                p.setNome_completo(txtNome3.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNome Invalido: " + txtNome3.getText();
            }

            if (Validar.vEmail(txtEmail2.getText())) {
                p.setEmail(txtEmail2.getText());
            } else {
                dadosvalidos = false;
                msg += "\nEmail Invalido: " + txtEmail2.getText();
            }

            if (Validar.vCRP(txtCRP.getText())) {
                p.setCRP(txtCRP.getText());
            } else {
                dadosvalidos = false;
                msg += "\nCPF Invalido: " + txtCRP.getText();
            }

            p.setCodPsicologo(codpsicologo);

            if (Validar.vTelefone(TxtTelefone6.getText())) {
                tf.setNumero(TxtTelefone6.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNúmero de Telefone Invalido: " + TxtTelefone6.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
            }

            if (!TxtTelefone7.getText().isEmpty()) {
                if (Validar.vTelefone(TxtTelefone7.getText())) {
                    tf2.setNumero(TxtTelefone7.getText());
                } else {
                    dadosvalidos = false;
                    msg += "\nNúmero de Telefone 2 Invalido: " + TxtTelefone7.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
                }
            }
            if (!TxtTelefone7.getText().isEmpty()) {
                if (Validar.vTelefone(TxtTelefone7.getText())) {
                    tf.setNumero(TxtTelefone7.getText());
                } else {
                    dadosvalidos = false;
                    msg += "\nNúmero de Telefone 2 Invalido: " + TxtTelefone7.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
                }
            }
            if (dadosvalidos) {
                if (dao.UpdatePsicologSemLogin(p)) {

                    p = dao.ReadPsicologo(p.getCRP());

                    List<Telefone> t = tfdao.ReadTPsicologo(p.getCodPsicologo());
                    t.get(0).setNumero(TxtTelefone6.getText());
                    if (tfdao.UpdateTPsicologo(t.get(0))) {

                        if (t.size() == 2) {
                            t.get(1).setNumero(TxtTelefone7.getText());
                            tfdao.UpdateTPsicologo(t.get(1));

                        }
//                        JOptionPane.showMessageDialog(this, "Psicologo: " + p.getNome_completo() + " Salvo com sucesso");
                        jLabel11.setText(p.getNome_completo());
                        String str = getFirstWord(jLabel11.getText());
                        jLabel11.setText(str);
                        p = dao.ReadPsicologo(Main.cod);

                        ModalMeusDados.dispose();

                        // this.clear();
                    }

                }

                /*   if(telefones){
                    Telefone tf = new Telefone();
                    TelefoneDAO tfdao = new TelefoneDAO();
                    tf.setNumero(TxtTelefone.getText());
                    p = dao.ReadPaciente(p.getCPF());
                    tf.setPaciente(p);
                    tfdao.CreatePc(tf);
                }
                 */
                //mostrar mensagem de sucesso
                // JOptionPane.showMessageDialog(null,"Paciente Cadastrado com Sucesso!");
                // ReadJTable();
            } else {
                JOptionPane.showMessageDialog(this, msg);
            }
        }
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void SpinnerLimiteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerLimiteStateChanged
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if (txtBusca.getText() != "") {

            PAGE_SIZE = (int) SpinnerLimite.getValue();

            getCountBusca(txtBusca.getText());
            SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
            SpinnerNumPaginas.setValue((int) currentPage);
            BtnVisuAlterarDados.setEnabled(false);
            btnVisuAnamneses.setEnabled(false);
            btnVisuConsultas.setEnabled(false);
            btnVisuAnotacoes.setEnabled(false);
            BtnCadastro.setEnabled(false);

            LabelQtdePaginas.setText("de " + totalPages);
            getPageDataBusca(1, txtBusca.getText());

            if (totalPages == 1) {
                BtnAvancarPouco.setEnabled(false);
                BtnAvancarBastante.setEnabled(false);

            } else {
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);
            }

        } else {
            PAGE_SIZE = (int) SpinnerLimite.getValue();
            getCount();

            SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
            SpinnerNumPaginas.setValue((int) currentPage);

            LabelQtdePaginas.setText("de " + totalPages);
            getPageData(1);

            if (totalPages == 1) {
                BtnAvancarPouco.setEnabled(false);
                BtnAvancarBastante.setEnabled(false);

            } else {
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);
            }
        }

    }//GEN-LAST:event_SpinnerLimiteStateChanged

    private void SpinnerNumPaginasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerNumPaginasStateChanged
        // TODO add your handling code here:
        if (txtBusca.getText() != "") {
            int pag1 = currentPage;
            getPageDataBusca((int) SpinnerNumPaginas.getValue(), txtBusca.getText());
            int pag2 = currentPage;
            if (pag2 < pag1) {
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);
                if (currentPage == 1) {
                    BtnVoltarPouco.setEnabled(false);
                    BtnVoltarBastante.setEnabled(false);
                }
            } else {
                {
                    BtnVoltarPouco.setEnabled(true);
                    BtnVoltarBastante.setEnabled(true);
                    if (currentPage == totalPages) {
                        BtnAvancarPouco.setEnabled(false);
                        BtnAvancarBastante.setEnabled(false);
                    }
                }
            }
            BtnVisuAlterarDados.setEnabled(false);
            BtnCadastro.setEnabled(false);
            btnVisuAnamneses.setEnabled(false);
            btnVisuConsultas.setEnabled(false);
            btnVisuAnotacoes.setEnabled(false);

        } else {

            getPageData((int) SpinnerNumPaginas.getValue());

        }
//        
    }//GEN-LAST:event_SpinnerNumPaginasStateChanged

    private void txtNome1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNome1KeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNome1KeyTyped

    private void txtCPF1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPF1KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCPF1KeyTyped

    private void TxtTelefone1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefone1KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
        if (TxtTelefone1.getText().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtTelefone1KeyTyped

    private void TxtTelefone5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefone5KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
        if (TxtTelefone5.getText().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtTelefone5KeyTyped

    private void txtNome3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNome3KeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNome3KeyTyped

    private void txtCRPKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCRPKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCRPKeyTyped

    private void TxtTelefone6KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefone6KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
        if (TxtTelefone6.getText().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtTelefone6KeyTyped

    private void TxtTelefone7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefone7KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
        if (TxtTelefone7.getText().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtTelefone7KeyTyped

    private void BtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHelpActionPerformed
        ModalHelp.setSize(540, 620);
        ModalHelp.setModal(true);
        ModalHelp.setVisible(false);
        ModalHelp.setVisible(false);
        ModalHelp.setLocationRelativeTo(null);
        ModalHelp.setVisible(true);
    }//GEN-LAST:event_BtnHelpActionPerformed

    private void BtnPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPacientesActionPerformed
        ManterPaciente1 mp1 = new ManterPaciente1();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnPacientesActionPerformed

    private void BtnExibirAnamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnamnesesActionPerformed
        ExibirAnamneses ma = new ExibirAnamneses();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnamnesesActionPerformed

    private void BtnExibirAnotacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacoesActionPerformed
        ExibirAnotacoes ea = new ExibirAnotacoes();
        Util.SizeJanela(ea);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacoesActionPerformed

    private void BtnExibirAnotacoes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacoes1ActionPerformed
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacoes1ActionPerformed

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
        //        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        //            this.ReadJTableBusca(txtBusca.getText());
        //        }
    }//GEN-LAST:event_txtBuscaKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        //System.out.println(JCBAtributo.getSelectedIndex());
        getCountBusca(txtBusca.getText());
        SpinnerNumPaginas.setValue(1);
        LabelQtdePaginas.setText("de " + totalPages);
        getPageDataBusca(1, txtBusca.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TxtEndereco1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtEndereco1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtEndereco1ActionPerformed

    private void txtNome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNome2ActionPerformed

    private void txtNome2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNome2KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNome2KeyTyped

    private void BtnSalvarAlteracoesAlterar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoesAlterar2ActionPerformed
        // TODO add your handling code here:
        boolean dadosvalidos = true;

        Paciente p = new Paciente();
        PacienteDAO dao = new PacienteDAO();
        Telefone tf = new Telefone();
        Telefone tf2 = new Telefone();
        TelefoneDAO tfdao = new TelefoneDAO();
        String msg = "Existem campos com formatos Inválidos\n\nFavor Verificar os campos:";
        String cpf = "";
        try {
            txtCPF2.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(ManterPaciente1.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ((String) txtCPF2.getValue() != null) {
            cpf = (String) txtCPF2.getValue();
            cpf = cpf.replace(".", "").replace("-", "");
        }
        if (!Validar.vCamposVazios(this, txtNome2, txtEmail3, cpf, DataNasc3, TxtTelefone2)) {
            p.setCodPaciente(codigopaciente);
            if (Validar.vNome(txtNome2.getText())) {
                p.setNome_Completo(txtNome2.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNome Invalido: " + txtNome2.getText();
            }

            if (Validar.vEmail(txtEmail3.getText())) {
                p.setEmail(txtEmail3.getText());
            } else {
                dadosvalidos = false;
                msg += "\nEmail Invalido: " + txtEmail3.getText();
            }

            if (Validar.vCPF(cpf)) {
                p.setCPF(cpf);
            } else {
                dadosvalidos = false;
                msg += "\nCPF Invalido: " + cpf;
            }

            p.setEstadoCivil((String) estadocivil2.getSelectedItem());
            p.setSexo((String) Sexo2.getSelectedItem());
            p.setProfissao(TxtProfissao2.getText());
            p.setReligiao(TxtReligiao2.getText());
            p.setEscolaridade(TxtEscolaridade2.getText());
            p.setEndereco(TxtEndereco2.getText());
            p.setCidade(TxtCidade2.getText());

            //java.util.Date date = new java.util.Date();
            Object param = DataNasc3.getDate();

            p.setDataNasc(param);
            p.setUF((String) JCBUF1.getSelectedItem());
            if (Validar.vTelefone(TxtTelefone2.getText())) {
                tf.setNumero(TxtTelefone2.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNúmero de Telefone Invalido: " + TxtTelefone2.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
            }
            if (!TxtTelefone8.getText().isEmpty()) {
                if (Validar.vTelefone(TxtTelefone8.getText())) {
                    tf2.setNumero(TxtTelefone8.getText());
                } else {
                    dadosvalidos = false;
                    msg += "\nNúmero de Telefone 2 Invalido: " + TxtTelefone8.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
                }
            }
            if (dadosvalidos) {

                if (dao.Update(p)) {

                    p = dao.ReadPaciente(p.getCPF());

                    tf.setPaciente(p);
                    List<Telefone> t = new ArrayList<>();
                    t = tfdao.Read(p.getCodPaciente());
                    t.get(0).setNumero(TxtTelefone2.getText());
                    if (tfdao.UpdateTPaciente(t.get(0))) {
                        if (t.size() == 2) {
                            if (TxtTelefone8.getText().isEmpty()) {
                                tfdao.HardDeleteTelefone(t.get(1));
                            } else {
                                t.get(1).setNumero(TxtTelefone8.getText());
                                tfdao.UpdateTPaciente(t.get(1));
                            }

                        }
                        if (!TxtTelefone8.getText().isEmpty() && t.size() == 1) {
                            tf2.setPaciente(p);
                            tf2.setNumero(TxtTelefone8.getText());
                            tfdao.CreatePc(tf2);

                        }
                        //JOptionPane.showMessageDialog(this, "Paciente " + p.getNome_Completo() + " Atualizado com sucesso");
                        //this.clear();
                        String str = "Paciente " + p.getNome_Completo() + " Atualizado com sucesso";
                        clearAlterarR();
                        ModalAlterarResolucaoMenor.dispose();
                    }

                }

                //mostrar mensagem de sucesso
                // JOptionPane.showMessageDialog(null,"Paciente Cadastrado com Sucesso!");
                if (txtBusca.getText() == "") {
                    getCountBusca(txtBusca.getText());
                    SpinnerNumPaginas.setValue(currentPage);
                    LabelQtdePaginas.setText("de " + totalPages);
                    getPageDataBusca(currentPage, txtBusca.getText());
                } else {
                    getCount();
                    SpinnerNumPaginas.setValue(currentPage);
                    LabelQtdePaginas.setText("de " + totalPages);
                    getPageDataBusca(currentPage, txtBusca.getText());
                }

            } else {
                JOptionPane.showMessageDialog(this, msg, "ERRO!", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_BtnSalvarAlteracoesAlterar2ActionPerformed

    private void BtnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar2ActionPerformed
        // TODO add your handling code here:
        ModalAlterarResolucaoMenor.dispose();
    }//GEN-LAST:event_BtnCancelar2ActionPerformed

    private void estadocivil2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estadocivil2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_estadocivil2ActionPerformed

    private void Sexo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Sexo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Sexo2ActionPerformed

    private void TxtEndereco2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtEndereco2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtEndereco2ActionPerformed

    private void TxtEscolaridade2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtEscolaridade2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtEscolaridade2ActionPerformed

    private void TxtTelefone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTelefone2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTelefone2ActionPerformed

    private void TxtTelefone2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefone2KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
        if (TxtTelefone2.getText().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtTelefone2KeyTyped

    private void TxtTelefone8KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefone8KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
        if (TxtTelefone8.getText().length() == 11) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtTelefone8KeyTyped

    private void txtCPF2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCPF2KeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCPF2KeyTyped

    private void BtnCadastrarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCadastrarConsultaActionPerformed
        if (JTPacientes.getSelectedRow() != -1) {
            if (data.getDateTimeStrict() == null) {

                JOptionPane.showMessageDialog(null, "Por favor Insira Data e Horário Válidos", "ERRO", JOptionPane.ERROR_MESSAGE);
            } else {
                Consulta c = new Consulta();
                ConsultaDAO cdao = new ConsultaDAO();
                Paciente p = new Paciente();
                int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
                int value = (Integer) JTPacientes.getModel().getValueAt(modelRow, 0);
                c.getPaciente().setCodPaciente(value);
                c.getPsicologo().setCodPsicologo(Main.cod);

                //java.util.Date date = new java.util.Date();
                Object param;
                LocalDateTime dateTime = (data.getDateTimePermissive());
                //  System.out.println("Data formatada"+param);
                c.setDataConsulta(dateTime);
                c.setStatus("A confirmar");
                c.setPagamento((String) JCBPagamento.getSelectedItem());
                boolean sucesso = cdao.Create(c);
                if (sucesso) {
//                JOptionPane.showMessageDialog(this, "Consulta Salva com sucesso");
                    JCBPagamento.setSelectedIndex(0);
                    ModalAlterarConsulta.dispose();
                    data.clear();
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um paciente para cadastrar uma consulta", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCadastrarConsultaActionPerformed

    private void BtnCadastroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCadastroActionPerformed
        int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
        String text = (String) JTPacientes.getModel().getValueAt(modelRow, 1);
        labelpaciente.setText(text);
        ModalAlterarConsulta.setSize(462, 320);
        ModalAlterarConsulta.setModal(true);
        ModalAlterarConsulta.setLocationRelativeTo(null);
        ModalAlterarConsulta.setVisible(true);
    }//GEN-LAST:event_BtnCadastroActionPerformed
    private String getFirstWord(String text) {

        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
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
            java.util.logging.Logger.getLogger(ManterPaciente1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterPaciente1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterPaciente1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterPaciente1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ManterPaciente1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnAvancarBastante;
    private javax.swing.JButton BtnAvancarPouco;
    private javax.swing.JButton BtnCadastrarConsulta;
    private javax.swing.JButton BtnCadastro;
    private javax.swing.JButton BtnCancelar1;
    private javax.swing.JButton BtnCancelar2;
    private javax.swing.JButton BtnCancelar5;
    private javax.swing.JButton BtnCancelar6;
    private javax.swing.JButton BtnExibirAnamneses;
    private javax.swing.JButton BtnExibirAnotacoes;
    private javax.swing.JButton BtnExibirAnotacoes1;
    private javax.swing.JButton BtnHelp;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JButton BtnPacientes;
    private javax.swing.JButton BtnSalvarAlteracoes4;
    private javax.swing.JButton BtnSalvarAlteracoes5;
    private javax.swing.JButton BtnSalvarAlteracoesAlterar2;
    private javax.swing.JButton BtnSalvarAlteracoesNovo1;
    private javax.swing.JButton BtnVisuAlterarDados;
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
    private com.github.lgooddatepicker.components.DatePicker DataNasc2;
    private com.github.lgooddatepicker.components.DatePicker DataNasc3;
    private javax.swing.JComboBox<String> JCBPagamento;
    private javax.swing.JComboBox<String> JCBPsicomotricidade1;
    private javax.swing.JComboBox<String> JCBUF;
    private javax.swing.JComboBox<String> JCBUF1;
    private javax.swing.JTable JTPacientes;
    private javax.swing.JLabel LabelCPF2;
    private javax.swing.JLabel LabelCPF3;
    private javax.swing.JLabel LabelCidade4;
    private javax.swing.JLabel LabelCidade5;
    private javax.swing.JLabel LabelCidade6;
    private javax.swing.JLabel LabelCidade7;
    private javax.swing.JLabel LabelCidade8;
    private javax.swing.JLabel LabelCidade9;
    private javax.swing.JLabel LabelEmail1;
    private javax.swing.JLabel LabelEmail2;
    private javax.swing.JLabel LabelEmail4;
    private javax.swing.JLabel LabelEscolaridade1;
    private javax.swing.JLabel LabelEscolaridade2;
    private javax.swing.JLabel LabelEstadoCivil2;
    private javax.swing.JLabel LabelEstadoCivil3;
    private javax.swing.JLabel LabelLimite;
    private javax.swing.JLabel LabelModalAnamnese;
    private javax.swing.JLabel LabelNome1;
    private javax.swing.JLabel LabelNome2;
    private javax.swing.JLabel LabelNome5;
    private javax.swing.JLabel LabelPagina;
    private javax.swing.JLabel LabelQtdePaginas;
    private javax.swing.JLabel LabelReligiao1;
    private javax.swing.JLabel LabelReligiao2;
    private javax.swing.JDialog ModalAlterarConsulta;
    private javax.swing.JDialog ModalAlterarResolucaoMenor;
    private javax.swing.JDialog ModalAnamnese2;
    private javax.swing.JDialog ModalHelp;
    private javax.swing.JDialog ModalMeusDados;
    private javax.swing.JDialog ModalNovoResolucaoMenor;
    private javax.swing.JPanel PainelDadosPaciente4;
    private javax.swing.JPanel PainelIdentificacaoPessoal1;
    private javax.swing.JPanel PainelIdentificacaoPessoal2;
    private javax.swing.JPanel PainelIdentificacaoPessoal3;
    private javax.swing.JPanel PainelIdentificacaoPessoal4;
    private javax.swing.JPanel PainelIdentificacaoPessoal5;
    private javax.swing.JPanel PainelIdentificacaoPessoal6;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelMeusDados;
    private javax.swing.JPanel PainelPaginacao;
    private javax.swing.JLabel Profissão;
    private javax.swing.JLabel Profissão1;
    private javax.swing.JComboBox<String> Sexo1;
    private javax.swing.JComboBox<String> Sexo2;
    private javax.swing.JSpinner SpinnerLimite;
    private javax.swing.JSpinner SpinnerNumPaginas;
    private javax.swing.JComboBox<String> SubitaOuProgressiva1;
    private javax.swing.JTextField TxtCidade1;
    private javax.swing.JTextField TxtCidade2;
    private javax.swing.JTextField TxtEndereco1;
    private javax.swing.JTextField TxtEndereco2;
    private javax.swing.JTextField TxtEscolaridade1;
    private javax.swing.JTextField TxtEscolaridade2;
    private javax.swing.JTextField TxtProfissao1;
    private javax.swing.JTextField TxtProfissao2;
    private javax.swing.JTextField TxtReligiao1;
    private javax.swing.JTextField TxtReligiao2;
    private javax.swing.JTextField TxtTelefone1;
    private javax.swing.JTextField TxtTelefone2;
    private javax.swing.JTextField TxtTelefone5;
    private javax.swing.JTextField TxtTelefone6;
    private javax.swing.JTextField TxtTelefone7;
    private javax.swing.JTextField TxtTelefone8;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnVisuAnamneses;
    private javax.swing.JButton btnVisuAnotacoes;
    private javax.swing.JButton btnVisuConsultas;
    private com.github.lgooddatepicker.components.DateTimePicker data;
    private javax.swing.JComboBox<String> estadocivil1;
    private javax.swing.JComboBox<String> estadocivil2;
    private javax.swing.JButton jButton2;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel3;
    private LIB.JEImagePanel jEImagePanel6;
    private LIB.JEImagePanel jEImagePanel7;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelInicioQueixa2;
    private javax.swing.JLabel labelInicioQueixa3;
    private javax.swing.JLabel labelpaciente;
    private javax.swing.JLabel labeltelefone;
    private javax.swing.JLabel labeltelefone2;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JFormattedTextField txtCPF1;
    private javax.swing.JFormattedTextField txtCPF2;
    private javax.swing.JTextField txtCRP;
    private javax.swing.JTextField txtComoComecou1;
    private javax.swing.JTextField txtDiagnostico1;
    private javax.swing.JTextField txtDoencasConhecidas1;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEmail3;
    private javax.swing.JTextField txtEncaminhamento1;
    private javax.swing.JTextField txtHistoricoFamiliar1;
    private javax.swing.JTextField txtMedicamentosUtilizados1;
    private javax.swing.JTextField txtNome1;
    private javax.swing.JTextField txtNome2;
    private javax.swing.JTextField txtNome3;
    private javax.swing.JTextField txtOqueMudou1;
    private javax.swing.JTextField txtQueixaPrincipal1;
    private javax.swing.JTextField txtQueixaSecundaria1;
    private javax.swing.JTextField txtSintomas1;
    // End of variables declaration//GEN-END:variables
}
