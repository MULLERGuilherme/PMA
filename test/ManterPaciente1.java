/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Validacoes.Deletar;
import Validacoes.Validar;
import connection.ConnectionFactory;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.bean.Paciente;
import model.bean.Telefone;
import model.bean.Vw_TelefonesPacientes;
import model.dao.PacienteDAO;
import model.dao.TelefoneDAO;
import model.dao.ViewsDAO;
import util.Util;

/**
 *
 * @author User
 */
public class ManterPaciente1 extends javax.swing.JFrame {

    private boolean telefones = false;
    private boolean fone2 = false;
    private int codigopaciente = -1;

    /**
     * Creates new form ManterPaciente
     */
    public ManterPaciente1() {
        initComponents();
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTPacientes.getModel();
        TableColumnModel cmod = JTPacientes.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTPacientes.setRowSorter(new TableRowSorter(dtmPacientes));

        ReadJTable();
    }

    public void ReadJTable() {

        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();
       
        model.setNumRows(0);
        ViewsDAO vwdao = new ViewsDAO();
        Object[] linha = null;
        String fones = null;
        String[] fones2 = null;
        for (Vw_TelefonesPacientes vw : vwdao.ReadTelefonesPacientes()) {
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

    public void ReadJTableBusca(String Atributo, String Busca) {

        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();
        
        
        
        model.setNumRows(0);
        if (Atributo.equals("Nome Completo")) {
            Atributo = "Paciente";
        }
        if (Atributo.equals("Telefone")) {
            Atributo = "numero";
        }
        ViewsDAO vwdao = new ViewsDAO();
        Object[] linha = null;
        String fones = null;
        String[] fones2 = null;
        for (Vw_TelefonesPacientes vw : vwdao.BuscaManterPaciente(Atributo, Busca)) {
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

    public class JPanelGradient extends JPanel {

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int largura = getWidth();
            int altura = getHeight();
            Color cor1 = new Color(80, 80, 80);
            Color cor2 = new Color(102, 102, 102);
            GradientPaint gp = new GradientPaint(0, 0, cor1, 180, altura, cor2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, largura, altura);
        }
    }

//    public class JPanelGradient2 extends JPanel {
//
//        protected void paintComponent(Graphics g) {
//            Graphics2D g2d = (Graphics2D) g;
//            int largura = getWidth();
//            int altura = getHeight();
//            Color cor1 = new Color(1, 112, 186);
//            Color cor2 = new Color(22, 218, 218);
//            GradientPaint gp = new GradientPaint(0, 0, cor1, 180, altura, cor2);
//            g2d.setPaint(gp);
//            g2d.fillRect(0, 0, largura, altura);
//        }
//    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        ModalNovo = new javax.swing.JDialog();
        PainelDadosPaciente = new javax.swing.JPanel();
        PainelIdentificacaoPessoal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BtnSalvarAlteracoes = new javax.swing.JButton();
        BtnCancelar = new javax.swing.JButton();
        jEImagePanel4 = new LIB.JEImagePanel();
        jLabel7 = new javax.swing.JLabel();
        LabelCidade3 = new javax.swing.JLabel();
        TxtTelefone = new javax.swing.JTextField();
        TxtTelefone2 = new javax.swing.JTextField();
        LabelCidade2 = new javax.swing.JLabel();
        LabelEscolaridade = new javax.swing.JLabel();
        TxtEscolaridade = new javax.swing.JTextField();
        TxtReligiao = new javax.swing.JTextField();
        LabelReligiao = new javax.swing.JLabel();
        LabelProfissao = new javax.swing.JLabel();
        TxtProfissao = new javax.swing.JTextField();
        TxtEndereco = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        LabelCidade = new javax.swing.JLabel();
        TxtCidade = new javax.swing.JTextField();
        LabelCPF = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        DataNasc = new com.github.lgooddatepicker.components.DatePicker();
        jLabel18 = new javax.swing.JLabel();
        Sexo = new javax.swing.JComboBox<>();
        LabelEstadoCivil = new javax.swing.JLabel();
        estadocivil = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        LabelEmail = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        LabelNome = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        ModalAlterarPaciente = new javax.swing.JDialog();
        PainelDadosPaciente1 = new javax.swing.JPanel();
        PainelIdentificacaoPessoal1 = new javax.swing.JPanel();
        PainelData1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        DataNasc1 = new com.github.lgooddatepicker.components.DatePicker();
        jLabel22 = new javax.swing.JLabel();
        Sexo1 = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        estadocivil1 = new javax.swing.JComboBox<>();
        PainelEndereco1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        TxtEndereco1 = new javax.swing.JTextField();
        PainelNome1 = new javax.swing.JPanel();
        LabelNome1 = new javax.swing.JLabel();
        txtNome1 = new javax.swing.JTextField();
        PainelCidade1 = new javax.swing.JPanel();
        LabelCidade4 = new javax.swing.JLabel();
        LabelCidade5 = new javax.swing.JLabel();
        txtCPF1 = new javax.swing.JTextField();
        TxtCidade1 = new javax.swing.JTextField();
        PainelProfissao1 = new javax.swing.JPanel();
        TxtProfissao1 = new javax.swing.JTextField();
        LabelProfissao1 = new javax.swing.JLabel();
        PainelReligiao1 = new javax.swing.JPanel();
        TxtReligiao1 = new javax.swing.JTextField();
        LabelReligiao1 = new javax.swing.JLabel();
        PainelEscolaridade1 = new javax.swing.JPanel();
        TxtEscolaridade1 = new javax.swing.JTextField();
        LabelEscolaridade1 = new javax.swing.JLabel();
        PainelTelefone1 = new javax.swing.JPanel();
        LabelCidade6 = new javax.swing.JLabel();
        LabelCidade7 = new javax.swing.JLabel();
        TxtTelefone21 = new javax.swing.JTextField();
        TxtTelefone1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BtnSalvarAlteracoes1 = new javax.swing.JButton();
        BtnCancelar1 = new javax.swing.JButton();
        PainelEmail2 = new javax.swing.JPanel();
        LabelNome2 = new javax.swing.JLabel();
        txtEmail1 = new javax.swing.JTextField();
        ModalAnamnese = new javax.swing.JDialog();
        PainelDadosPaciente2 = new javax.swing.JPanel();
        PainelIdentificacaoPessoal2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BtnSalvarAlteracoes2 = new javax.swing.JButton();
        BtnCancelar2 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        JCBPsicomotricidade1 = new javax.swing.JComboBox<>();
        labelInicioQueixa1 = new javax.swing.JLabel();
        JCBPsicomotricidade = new javax.swing.JComboBox<>();
        labelInicioQueixa = new javax.swing.JLabel();
        JCBQueixasAfetivoEmocionais = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        JCBQueixasCognitivas = new javax.swing.JComboBox<>();
        LabelEmail1 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        LabelNome3 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtEmail3 = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtEmail4 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtEmail5 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtEmail6 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtEmail7 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtEmail8 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtEmail9 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtEmail10 = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        txtEmail11 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        DataNasc2 = new com.github.lgooddatepicker.components.DatePicker();
        BtnCancelar3 = new javax.swing.JButton();
        BtnSalvarAlteracoes3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtEmail12 = new javax.swing.JTextField();
        jEImagePanel2 = new LIB.JEImagePanel();
        jLabel5 = new javax.swing.JLabel();
        PainelNorte = new javax.swing.JPanel();
        jPanel3 = new JPanel();
        PainelEsquerda = new javax.swing.JPanel();
        PainelMenu = new JPanelGradient();
        BtnManterAnotacao = new javax.swing.JButton();
        BtnManterPaciente = new javax.swing.JButton();
        BtnManterPsicologo = new javax.swing.JButton();
        BtnManterConsulta = new javax.swing.JButton();
        BtnExibirAnotacao = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        BtnVoltar1 = new javax.swing.JButton();
        PainelCentro = new javax.swing.JPanel();
        Painel = new javax.swing.JPanel();
        BtnExcluir = new javax.swing.JButton();
        BtnVisuAlterarDados = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        JCBAtributo = new javax.swing.JComboBox<>();
        txtBusca = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTPacientes = new javax.swing.JTable();
        BtnBuscar = new javax.swing.JButton();
        btnVisuAnotacoes = new javax.swing.JButton();
        btnVisuAnamneses = new javax.swing.JButton();
        brnVisuConsultas = new javax.swing.JButton();
        BtnNovo = new javax.swing.JButton();
        BtnModalAnamnese = new javax.swing.JButton();

        ModalNovo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ModalNovo.getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        PainelDadosPaciente.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoalLayout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal);
        PainelIdentificacaoPessoal.setLayout(PainelIdentificacaoPessoalLayout);
        PainelIdentificacaoPessoalLayout.setHorizontalGroup(
            PainelIdentificacaoPessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoalLayout.setVerticalGroup(
            PainelIdentificacaoPessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel1.setText("Campos Obrigatórios");
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes.setText("Salvar Alterações");
        BtnSalvarAlteracoes.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoesActionPerformed(evt);
            }
        });

        BtnCancelar.setText("Cancelar");
        BtnCancelar.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        jEImagePanel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/simboloma.png"))); // NOI18N

        javax.swing.GroupLayout jEImagePanel4Layout = new javax.swing.GroupLayout(jEImagePanel4);
        jEImagePanel4.setLayout(jEImagePanel4Layout);
        jEImagePanel4Layout.setHorizontalGroup(
            jEImagePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jEImagePanel4Layout.setVerticalGroup(
            jEImagePanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 150, Short.MAX_VALUE)
        );

        jLabel7.setText("Cadastrar Paciente");
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(59, 131, 117));

        LabelCidade3.setText("Telefone1:");
        LabelCidade3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCidade2.setText("Telefone2:");
        LabelCidade2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEscolaridade.setText("Escolaridade:");
        LabelEscolaridade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtEscolaridade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtReligiao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelReligiao.setText("Religião:");
        LabelReligiao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelProfissao.setText("Profissão:");
        LabelProfissao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtProfissao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtEndereco.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel20.setText("Endereço:");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCidade.setText("Cidade:");
        LabelCidade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtCidade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCPF.setText("CPF:");
        LabelCPF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtCPF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel17.setText("Data de Nascimento: ");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        DataNasc.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataNasc.setPreferredSize(new java.awt.Dimension(160, 17));

        jLabel18.setText("Sexo:");
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino","Não Definido"}));
        Sexo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEstadoCivil.setText("Estado Civil:");
        LabelEstadoCivil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        estadocivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solteiro", "Casado", "Separado", "Divorciado","Viuvo" }));
        estadocivil.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEmail.setText("E-mail:");
        LabelEmail.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtNome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        LabelNome.setText("Nome:");
        LabelNome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel8.setText("*");
        jLabel8.setBackground(new java.awt.Color(255, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));

        jLabel9.setText("*");
        jLabel9.setBackground(new java.awt.Color(255, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));

        jLabel10.setText("*");
        jLabel10.setBackground(new java.awt.Color(255, 0, 0));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));

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

        jLabel16.setText("*");
        jLabel16.setBackground(new java.awt.Color(255, 0, 0));
        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout PainelDadosPacienteLayout = new javax.swing.GroupLayout(PainelDadosPaciente);
        PainelDadosPaciente.setLayout(PainelDadosPacienteLayout);
        PainelDadosPacienteLayout.setHorizontalGroup(
            PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                        .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(224, 224, 224)
                        .addComponent(jLabel7))
                    .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(LabelEmail)
                                .addComponent(LabelNome))
                            .addGap(25, 25, 25)
                            .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                    .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(estadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel18)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                            .addComponent(jLabel10)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(LabelCPF))))
                                .addComponent(txtNome)))
                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                            .addGap(30, 30, 30)
                            .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                    .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel1)
                                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                            .addComponent(LabelEscolaridade)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TxtEscolaridade))
                                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                            .addComponent(LabelReligiao)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TxtReligiao))
                                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                            .addComponent(jLabel20)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TxtEndereco))
                                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                            .addComponent(LabelProfissao)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TxtProfissao))
                                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                            .addComponent(LabelCidade3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(11, 11, 11)
                                            .addComponent(LabelCidade2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                                    .addComponent(BtnCancelar)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                                                    .addComponent(BtnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(TxtTelefone2)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPacienteLayout.createSequentialGroup()
                                            .addComponent(LabelCidade)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(jLabel9))))
                        .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                            .addGap(36, 36, 36)
                            .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel17)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(LabelEstadoCivil))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPacienteLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(38, Short.MAX_VALUE))
            .addComponent(PainelIdentificacaoPessoal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PainelDadosPacienteLayout.setVerticalGroup(
            PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPacienteLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNome)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelEmail)
                    .addComponent(LabelCPF)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(estadocivil, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(LabelEstadoCivil)
                        .addComponent(jLabel12)
                        .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18)))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel11)
                    .addComponent(LabelCidade)
                    .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelProfissao)
                    .addComponent(TxtProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelReligiao)
                    .addComponent(TxtReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEscolaridade)
                    .addComponent(TxtEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCidade3, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelCidade2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(144, 144, 144))
        );

        ModalNovo.getContentPane().add(PainelDadosPaciente);

        ModalAlterarPaciente.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ModalAlterarPaciente.getContentPane().setLayout(new java.awt.GridBagLayout());

        PainelDadosPaciente1.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente1.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal1.setBackground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout PainelIdentificacaoPessoal1Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal1);
        PainelIdentificacaoPessoal1.setLayout(PainelIdentificacaoPessoal1Layout);
        PainelIdentificacaoPessoal1Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal1Layout.setVerticalGroup(
            PainelIdentificacaoPessoal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        PainelData1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel21.setText("   *  Data de Nascimento: ");
        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        DataNasc1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataNasc1.setPreferredSize(new java.awt.Dimension(160, 17));

        jLabel22.setText("*  Sexo:");
        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Sexo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino","Não Definido"}));
        Sexo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel23.setText("*  Estado Civil:");
        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        estadocivil1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solteiro", "Casado", "Separado", "Divorciado","Viuvo" }));
        estadocivil1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelData1Layout = new javax.swing.GroupLayout(PainelData1);
        PainelData1.setLayout(PainelData1Layout);
        PainelData1Layout.setHorizontalGroup(
            PainelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelData1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataNasc1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(estadocivil1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelData1Layout.setVerticalGroup(
            PainelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelData1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Sexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel23)
                        .addComponent(estadocivil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22))
                    .addGroup(PainelData1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(DataNasc1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        PainelEndereco1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setText("   Endereço:");
        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtEndereco1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelEndereco1Layout = new javax.swing.GroupLayout(PainelEndereco1);
        PainelEndereco1.setLayout(PainelEndereco1Layout);
        PainelEndereco1Layout.setHorizontalGroup(
            PainelEndereco1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEndereco1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtEndereco1, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelEndereco1Layout.setVerticalGroup(
            PainelEndereco1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEndereco1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(PainelEndereco1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtEndereco1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
        );

        PainelNome1.setBackground(new java.awt.Color(255, 255, 255));

        LabelNome1.setText("*  Nome:");
        LabelNome1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtNome1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNome1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNome1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelNome1Layout = new javax.swing.GroupLayout(PainelNome1);
        PainelNome1.setLayout(PainelNome1Layout);
        PainelNome1Layout.setHorizontalGroup(
            PainelNome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelNome1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(LabelNome1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNome1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
        );
        PainelNome1Layout.setVerticalGroup(
            PainelNome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelNome1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(LabelNome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(txtNome1, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        PainelCidade1.setBackground(new java.awt.Color(255, 255, 255));

        LabelCidade4.setText("*  CPF:");
        LabelCidade4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCidade5.setText("    Cidade:");
        LabelCidade5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtCPF1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtCidade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelCidade1Layout = new javax.swing.GroupLayout(PainelCidade1);
        PainelCidade1.setLayout(PainelCidade1Layout);
        PainelCidade1Layout.setHorizontalGroup(
            PainelCidade1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCidade1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(LabelCidade5)
                .addGap(32, 32, 32)
                .addComponent(TxtCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelCidade4)
                .addGap(18, 18, 18)
                .addComponent(txtCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelCidade1Layout.setVerticalGroup(
            PainelCidade1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCidade1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LabelCidade4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PainelCidade1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LabelCidade5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TxtCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PainelProfissao1.setBackground(new java.awt.Color(255, 255, 255));

        TxtProfissao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelProfissao1.setText("        Profissão:");
        LabelProfissao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelProfissao1Layout = new javax.swing.GroupLayout(PainelProfissao1);
        PainelProfissao1.setLayout(PainelProfissao1Layout);
        PainelProfissao1Layout.setHorizontalGroup(
            PainelProfissao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelProfissao1Layout.createSequentialGroup()
                .addComponent(LabelProfissao1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtProfissao1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelProfissao1Layout.setVerticalGroup(
            PainelProfissao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelProfissao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(TxtProfissao1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addComponent(LabelProfissao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PainelReligiao1.setBackground(new java.awt.Color(255, 255, 255));

        TxtReligiao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        TxtReligiao1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtReligiao1ActionPerformed(evt);
            }
        });

        LabelReligiao1.setText("        Religião:");
        LabelReligiao1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelReligiao1Layout = new javax.swing.GroupLayout(PainelReligiao1);
        PainelReligiao1.setLayout(PainelReligiao1Layout);
        PainelReligiao1Layout.setHorizontalGroup(
            PainelReligiao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelReligiao1Layout.createSequentialGroup()
                .addComponent(LabelReligiao1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtReligiao1, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelReligiao1Layout.setVerticalGroup(
            PainelReligiao1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelReligiao1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TxtReligiao1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        PainelEscolaridade1.setBackground(new java.awt.Color(255, 255, 255));

        TxtEscolaridade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEscolaridade1.setText("        Escolaridade:");
        LabelEscolaridade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelEscolaridade1Layout = new javax.swing.GroupLayout(PainelEscolaridade1);
        PainelEscolaridade1.setLayout(PainelEscolaridade1Layout);
        PainelEscolaridade1Layout.setHorizontalGroup(
            PainelEscolaridade1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEscolaridade1Layout.createSequentialGroup()
                .addComponent(LabelEscolaridade1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtEscolaridade1, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelEscolaridade1Layout.setVerticalGroup(
            PainelEscolaridade1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelEscolaridade1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TxtEscolaridade1, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        PainelTelefone1.setBackground(new java.awt.Color(255, 255, 255));

        LabelCidade6.setText("Telefone2:");
        LabelCidade6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCidade7.setText("*  Telefone1:");
        LabelCidade7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone21.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelTelefone1Layout = new javax.swing.GroupLayout(PainelTelefone1);
        PainelTelefone1.setLayout(PainelTelefone1Layout);
        PainelTelefone1Layout.setHorizontalGroup(
            PainelTelefone1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTelefone1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(LabelCidade7)
                .addGap(31, 31, 31)
                .addComponent(TxtTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelCidade6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtTelefone21, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelTelefone1Layout.setVerticalGroup(
            PainelTelefone1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTelefone1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelTelefone1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelTelefone1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelCidade7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LabelCidade6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtTelefone21, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtTelefone1)))
        );

        jLabel3.setText("* Campos Obrigatórios");
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes1.setText("Salvar Alterações");
        BtnSalvarAlteracoes1.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes1.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes1ActionPerformed(evt);
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

        PainelEmail2.setBackground(new java.awt.Color(255, 255, 255));

        LabelNome2.setText("*E-mail:");
        LabelNome2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelEmail2Layout = new javax.swing.GroupLayout(PainelEmail2);
        PainelEmail2.setLayout(PainelEmail2Layout);
        PainelEmail2Layout.setHorizontalGroup(
            PainelEmail2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEmail2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(LabelNome2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 787, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelEmail2Layout.setVerticalGroup(
            PainelEmail2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEmail2Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(PainelEmail2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNome2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout PainelDadosPaciente1Layout = new javax.swing.GroupLayout(PainelDadosPaciente1);
        PainelDadosPaciente1.setLayout(PainelDadosPaciente1Layout);
        PainelDadosPaciente1Layout.setHorizontalGroup(
            PainelDadosPaciente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PainelDadosPaciente1Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(PainelDadosPaciente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PainelTelefone1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelEscolaridade1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelReligiao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelProfissao1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelEndereco1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelCidade1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelData1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelNome1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelEmail2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PainelDadosPaciente1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar1)
                .addGap(18, 18, 18)
                .addComponent(BtnSalvarAlteracoes1)
                .addGap(49, 49, 49))
        );
        PainelDadosPaciente1Layout.setVerticalGroup(
            PainelDadosPaciente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPaciente1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(PainelIdentificacaoPessoal1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelNome1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelData1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelCidade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelEndereco1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelProfissao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelReligiao1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelEscolaridade1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelTelefone1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoes1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(238, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 225;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        ModalAlterarPaciente.getContentPane().add(PainelDadosPaciente1, gridBagConstraints);

        ModalAnamnese.setResizable(false);
        ModalAnamnese.getContentPane().setLayout(new java.awt.GridBagLayout());

        PainelDadosPaciente2.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente2.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal2.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal2Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal2);
        PainelIdentificacaoPessoal2.setLayout(PainelIdentificacaoPessoal2Layout);
        PainelIdentificacaoPessoal2Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal2Layout.setVerticalGroup(
            PainelIdentificacaoPessoal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel2.setText("* Campos Obrigatórios");
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes2.setText("Salvar Alterações");
        BtnSalvarAlteracoes2.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes2.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes2ActionPerformed(evt);
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

        jLabel29.setText("*Repentina ou Gradual:");
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBPsicomotricidade1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Lento", "Agitado"}));
        JCBPsicomotricidade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelInicioQueixa1.setText("*  Psicomotricidade: ");
        labelInicioQueixa1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBPsicomotricidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Lento", "Agitado"}));
        JCBPsicomotricidade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelInicioQueixa.setText("*Queixas Afetivo-emocionais: ");
        labelInicioQueixa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBQueixasAfetivoEmocionais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nenhuma","Volição", "Afeto", "Ansiedade", "Medo", "Culpa", "Raiva", "Luto", "Desanimo" }));
        JCBQueixasAfetivoEmocionais.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel26.setText("*  Queixas Cognitivas:");
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBQueixasCognitivas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Nenhuma", "Integridade Sensorial","Percepção", "Atenção", "Memória" }));
        JCBQueixasCognitivas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEmail1.setText("  Queixa Secundária:");
        LabelEmail1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelNome3.setText("*Queixa Principal:");
        LabelNome3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel30.setText("  Como começou:");
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel31.setText("  Diagnóstico:");
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel32.setText("  Histórico Familiar:");
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel33.setText("  Doenças Conhecidas:");
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel34.setText("  Sintomas:");
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel35.setText("  Medicamentos Utilizados:");
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel36.setText("  O que mudou:");
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel37.setText("  Encaminhamento:");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel38.setText("  Como começou:");
        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail11.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel39.setText("  Data de Início:");
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        DataNasc2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataNasc2.setPreferredSize(new java.awt.Dimension(160, 17));

        BtnCancelar3.setText("Cancelar");
        BtnCancelar3.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar3.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar3ActionPerformed(evt);
            }
        });

        BtnSalvarAlteracoes3.setText("Salvar Alterações");
        BtnSalvarAlteracoes3.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes3.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes3ActionPerformed(evt);
            }
        });

        jLabel4.setText("* Campos Obrigatórios");
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEmail12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jEImagePanel2Layout = new javax.swing.GroupLayout(jEImagePanel2);
        jEImagePanel2.setLayout(jEImagePanel2Layout);
        jEImagePanel2Layout.setHorizontalGroup(
            jEImagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jEImagePanel2Layout.setVerticalGroup(
            jEImagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel5.setText("Anamnese Psicológica");
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelDadosPaciente2Layout = new javax.swing.GroupLayout(PainelDadosPaciente2);
        PainelDadosPaciente2.setLayout(PainelDadosPaciente2Layout);
        PainelDadosPaciente2Layout.setHorizontalGroup(
            PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar2)
                .addGap(42, 42, 42)
                .addComponent(BtnSalvarAlteracoes2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel2))
                    .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(LabelNome3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtEmail12, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                                    .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelInicioQueixa)
                                        .addComponent(jLabel29))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                                            .addComponent(JCBQueixasAfetivoEmocionais, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel26))
                                        .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                                            .addComponent(JCBPsicomotricidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(labelInicioQueixa1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(JCBPsicomotricidade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JCBQueixasCognitivas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                                        .addComponent(LabelEmail1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEmail3, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                                    .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel31)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel33)
                                        .addComponent(jLabel34)
                                        .addComponent(jLabel35)
                                        .addComponent(jLabel36)
                                        .addComponent(jLabel38)
                                        .addComponent(jLabel39)
                                        .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel4))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtEmail2)
                                        .addComponent(txtEmail4)
                                        .addComponent(txtEmail5)
                                        .addComponent(txtEmail6)
                                        .addComponent(txtEmail7)
                                        .addComponent(txtEmail8)
                                        .addComponent(txtEmail9)
                                        .addComponent(txtEmail10)
                                        .addComponent(txtEmail11)
                                        .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                                            .addComponent(DataNasc2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente2Layout.createSequentialGroup()
                                    .addComponent(BtnCancelar3)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnSalvarAlteracoes3))))))
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                .addComponent(jEImagePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        PainelDadosPaciente2Layout.setVerticalGroup(
            PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPaciente2Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEImagePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNome3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail12))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtEmail3))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JCBQueixasAfetivoEmocionais, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelInicioQueixa)
                        .addComponent(jLabel26)
                        .addComponent(JCBQueixasCognitivas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(JCBPsicomotricidade1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInicioQueixa1)
                    .addComponent(JCBPsicomotricidade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtEmail2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail4)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail10)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail5)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail6)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail7)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail8)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail9)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmail11)
                    .addComponent(jLabel38))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(DataNasc2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnSalvarAlteracoes3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(480, 480, 480)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDadosPaciente2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoes2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = -300;
        gridBagConstraints.ipady = -1000;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        ModalAnamnese.getContentPane().add(PainelDadosPaciente2, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PainelNorte.setPreferredSize(new java.awt.Dimension(1080, 89));

        jPanel3.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1242, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout PainelNorteLayout = new javax.swing.GroupLayout(PainelNorte);
        PainelNorte.setLayout(PainelNorteLayout);
        PainelNorteLayout.setHorizontalGroup(
            PainelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PainelNorteLayout.setVerticalGroup(
            PainelNorteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelNorteLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(PainelNorte, java.awt.BorderLayout.PAGE_START);

        BtnManterAnotacao.setText("Exibir Anamneses");
        BtnManterAnotacao.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterAnotacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterAnotacaoActionPerformed(evt);
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

        BtnVoltar1.setText("Início");
        BtnVoltar1.setBackground(new java.awt.Color(102, 102, 102));
        BtnVoltar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnVoltar1.setForeground(new java.awt.Color(255, 255, 255));
        BtnVoltar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelMenuLayout = new javax.swing.GroupLayout(PainelMenu);
        PainelMenu.setLayout(PainelMenuLayout);
        PainelMenuLayout.setHorizontalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnManterPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnManterConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnManterAnotacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnVoltar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
        );
        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addComponent(BtnVoltar1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PainelEsquerdaLayout = new javax.swing.GroupLayout(PainelEsquerda);
        PainelEsquerda.setLayout(PainelEsquerdaLayout);
        PainelEsquerdaLayout.setHorizontalGroup(
            PainelEsquerdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEsquerdaLayout.createSequentialGroup()
                .addComponent(PainelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        PainelEsquerdaLayout.setVerticalGroup(
            PainelEsquerdaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(PainelEsquerda, java.awt.BorderLayout.LINE_START);

        BtnExcluir.setText("Excluir");
        BtnExcluir.setBackground(new java.awt.Color(204, 204, 204));
        BtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirActionPerformed(evt);
            }
        });

        BtnVisuAlterarDados.setText("Visualizar/ Alterar Dados");
        BtnVisuAlterarDados.setBackground(new java.awt.Color(204, 204, 204));
        BtnVisuAlterarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVisuAlterarDadosActionPerformed(evt);
            }
        });

        jLabel6.setText("Buscar Paciente por");

        JCBAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome Completo", "Email", "Telefone" }));

        jLabel15.setText("Contendo");

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
                return canEdit [columnIndex];
            }
        });
        JTPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTPacientesMouseClicked(evt);
            }
        });
        JTPacientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTPacientesKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(JTPacientes);
        if (JTPacientes.getColumnModel().getColumnCount() > 0) {
            JTPacientes.getColumnModel().getColumn(0).setResizable(false);
            JTPacientes.getColumnModel().getColumn(0).setPreferredWidth(1);
            JTPacientes.getColumnModel().getColumn(3).setResizable(false);
            JTPacientes.getColumnModel().getColumn(4).setResizable(false);
        }

        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        btnVisuAnotacoes.setText("Visualizar Anotaçoes");
        btnVisuAnotacoes.setBackground(new java.awt.Color(204, 204, 204));
        btnVisuAnotacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisuAnotacoesActionPerformed(evt);
            }
        });

        btnVisuAnamneses.setText("Visualizar Anamneses");
        btnVisuAnamneses.setBackground(new java.awt.Color(204, 204, 204));
        btnVisuAnamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisuAnamnesesActionPerformed(evt);
            }
        });

        brnVisuConsultas.setText("Visualizar Consultas");
        brnVisuConsultas.setBackground(new java.awt.Color(204, 204, 204));
        brnVisuConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brnVisuConsultasActionPerformed(evt);
            }
        });

        BtnNovo.setText("Novo");
        BtnNovo.setBackground(new java.awt.Color(204, 204, 204));
        BtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoActionPerformed(evt);
            }
        });

        BtnModalAnamnese.setText("Modal Anamnese");
        BtnModalAnamnese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnModalAnamneseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelLayout = new javax.swing.GroupLayout(Painel);
        Painel.setLayout(PainelLayout);
        PainelLayout.setHorizontalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brnVisuConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisuAnotacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(BtnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisuAnamneses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnVisuAlterarDados, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(BtnModalAnamnese, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PainelLayout.setVerticalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(0, 9, Short.MAX_VALUE)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JCBAtributo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(BtnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnVisuAlterarDados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisuAnamneses)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brnVisuConsultas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisuAnotacoes)
                        .addGap(75, 75, 75)
                        .addComponent(BtnModalAnamnese)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout PainelCentroLayout = new javax.swing.GroupLayout(PainelCentro);
        PainelCentro.setLayout(PainelCentroLayout);
        PainelCentroLayout.setHorizontalGroup(
            PainelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCentroLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PainelCentroLayout.setVerticalGroup(
            PainelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCentroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Painel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(PainelCentro, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnManterAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterAnotacaoActionPerformed
        // TODO add your handling code here:
        ExibirAnamneses ma = new ExibirAnamneses();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnManterAnotacaoActionPerformed

    private void BtnManterPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPacienteActionPerformed
        // TODO add your handling code here:
        ManterPaciente1 mp1 = new ManterPaciente1();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnManterPacienteActionPerformed

    private void BtnManterPsicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPsicologoActionPerformed
        // TODO add your handling code here:
        ManterPsicologo mp = new ManterPsicologo();
        Util.SizeJanela(mp);
        this.dispose();
    }//GEN-LAST:event_BtnManterPsicologoActionPerformed

    private void BtnManterConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterConsultaActionPerformed
        // TODO add your handling code here:
        ManterConsulta1 mc = new ManterConsulta1();
        Util.SizeJanela(mc);
        this.dispose();
    }//GEN-LAST:event_BtnManterConsultaActionPerformed

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

    private void BtnVoltar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltar1ActionPerformed
        // TODO add your handling code here:
        TelaPrincipal tp = new TelaPrincipal();
        Util.SizeJanela(tp);
        this.dispose();
    }//GEN-LAST:event_BtnVoltar1ActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void BtnSalvarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoesActionPerformed
        boolean dadosvalidos = true;

        Paciente p = new Paciente();
        PacienteDAO dao = new PacienteDAO();
        Telefone tf = new Telefone();
        Telefone tf2 = new Telefone();
        TelefoneDAO tfdao = new TelefoneDAO();
        String msg = "Existem campos com formatos Inválidos\n\nFavor Verificar os campos:";

        if (!Validar.vCamposVazios(this, txtNome, txtEmail, txtCPF, DataNasc, TxtTelefone)) {
            if (Validar.vNome(txtNome.getText())) {
                p.setNome_Completo(txtNome.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNome Invalido: " + txtNome.getText();
            }

            if (Validar.vEmail(txtEmail.getText())) {
                p.setEmail(txtEmail.getText());
            } else {
                dadosvalidos = false;
                msg += "\nEmail Invalido: " + txtEmail.getText();
            }

            if (Validar.vCPF(txtCPF.getText())) {
                p.setCPF(txtCPF.getText());
            } else {
                dadosvalidos = false;
                msg += "\nCPF Invalido: " + txtCPF.getText();
            }

            p.setEstadoCivil((String) estadocivil.getSelectedItem());

            // System.out.println("data "+ date_time);
            //java.util.Date date = new java.util.Date();
            Object param = DataNasc.getDate();
            //System.out.println("era aki");
            //System.out.println(param);
            p.setDataNasc(param);
            p.setSexo((String) Sexo.getSelectedItem());
            p.setProfissao(TxtProfissao.getText());
            p.setReligiao(TxtReligiao.getText());
            p.setEscolaridade(TxtEscolaridade.getText());
            p.setEndereco(TxtEndereco.getText());
            p.setCidade(TxtCidade.getText());

            if (Validar.vTelefone(TxtTelefone.getText())) {
                tf.setNumero(TxtTelefone.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNúmero de Telefone Invalido: " + TxtTelefone.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
            }
            if (!TxtTelefone2.getText().isEmpty()) {
                if (Validar.vTelefone(TxtTelefone2.getText())) {
                    tf2.setNumero(TxtTelefone2.getText());
                } else {
                    dadosvalidos = false;
                    msg += "\nNúmero de Telefone 2 Invalido: " + TxtTelefone2.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
                }
            }
            if (dadosvalidos) {
                if (dao.Create(p)) {

                    p = dao.ReadPaciente(p.getCPF());

                    tf.setPaciente(p);
                    if (tfdao.CreatePc(tf)) {
                        if (fone2) {
                            tf2.setPaciente(p);
                            tfdao.CreatePc(tf2);
                        }
                        JOptionPane.showMessageDialog(this, "Paciente " + p.getNome_Completo() + " Salvo com sucesso");
                        this.clear();
                    } else {
                        dao.Delete(p);
                    }

                }
                ReadJTable();
            } else {
                JOptionPane.showMessageDialog(this, msg);
            }
        }
        ModalNovo.dispose();
    }//GEN-LAST:event_BtnSalvarAlteracoesActionPerformed

    private void brnVisuConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnVisuConsultasActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {
            
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer)JTPacientes.getModel().getValueAt(modelRow,0);
            ExibirConsultasPaciente.codpaciente =  value;
           
            ExibirConsultasPaciente cp = new ExibirConsultasPaciente();
            cp.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_brnVisuConsultasActionPerformed

    private void btnVisuAnamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisuAnamnesesActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {
            
            boolean dadosvalidos = true;
            //ExibirAnamnesesPaciente.codpaciente = ((int) JTPacientes.getModel().getValueAt(JTPacientes.getSelectedRow(),0));
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer)JTPacientes.getModel().getValueAt(modelRow,0);
          
            ExibirAnamneses1 ea = new ExibirAnamneses1(value);
            ea.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_btnVisuAnamnesesActionPerformed

    private void btnVisuAnotacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisuAnotacoesActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            boolean dadosvalidos = true;
            //ExibirAnotacoesPaciente.codpaciente = ((int) JTPacientes.getModel().getValueAt(JTPacientes.getSelectedRow(),0));
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer)JTPacientes.getModel().getValueAt(modelRow,0);
             ExibirAnotacoes1 ea = new ExibirAnotacoes1(value);
            ea.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_btnVisuAnotacoesActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        // TODO add your handling code here:
        //System.out.println(JCBAtributo.getSelectedIndex());
        this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void JTPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPacientesKeyReleased
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer)JTPacientes.getModel().getValueAt(modelRow,0);
            this.codigopaciente = value;
        
        }
    }//GEN-LAST:event_JTPacientesKeyReleased

    private void JTPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTPacientesMouseClicked
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {
           int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer)JTPacientes.getModel().getValueAt(modelRow,0);
            this.codigopaciente = value;
        }

    }//GEN-LAST:event_JTPacientesMouseClicked

    private void BtnVisuAlterarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVisuAlterarDadosActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {
            int modelRow = JTPacientes.convertRowIndexToModel(JTPacientes.getSelectedRow());
            int value = (Integer)JTPacientes.getModel().getValueAt(modelRow,0);
            this.codigopaciente = value;
            PacienteDAO dao = new PacienteDAO();
            ViewsDAO vwdao = new ViewsDAO();
            Paciente p = dao.ReadPaciente(codigopaciente);
            Vw_TelefonesPacientes  v = new Vw_TelefonesPacientes();
            v = vwdao.ReadTelefonesPacientes(codigopaciente);
            txtNome1.setText(p.getNome_Completo());
            Date date = (Date) p.getDataNasc();

            //LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();

    

            

            DataNasc1.setDate(date.toLocalDate());
            //DataNasc1.setDate((LocalDate) p.getDataNasc());
            Sexo1.setSelectedItem(p.getSexo());
            estadocivil1.setSelectedItem(p.getEstadoCivil());
            TxtCidade1.setText(p.getCidade());
            txtCPF1.setText(p.getCPF());
            TxtEndereco1.setText(p.getEndereco());
            TxtProfissao1.setText(p.getProfissao());
            TxtReligiao1.setText(p.getReligiao());
            TxtEscolaridade1.setText(p.getEscolaridade());
            String fones = null;
            String[] fones2 = null;
            fones = v.getTelefone().getNumero();
            if (fones.contains(",")){
                fones2 = fones.split(",");
                TxtTelefone1.setText(fones2[0]);
                TxtTelefone21.setText(fones2[1]);
            }else 
            {
                TxtTelefone1.setText(fones);
                TxtTelefone21.setText("");
            }
            
            txtEmail1.setText(p.getEmail());
            
            ModalAlterarPaciente.setSize(1039, 967);
            ModalAlterarPaciente.setModal(true);
            ModalAlterarPaciente.setLocationRelativeTo(null);
            ModalAlterarPaciente.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para alterar");
        }
    }//GEN-LAST:event_BtnVisuAlterarDadosActionPerformed

    private void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            Paciente p = new Paciente();

            p.setCodPaciente((int) JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 0));
            boolean status = Deletar.DPaciente(p);
            //limpar a tela
            if (status) {
                JOptionPane.showMessageDialog(this, "Todos os Dados do Paciente foram deletados com Sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao Excluir");
            }
            this.clear();
            //mostrar mensagem de sucesso
            // JOptionPane.showMessageDialog(null,"Paciente Cadastrado com Sucesso!");
            ReadJTable();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para excluir");
        }
    }//GEN-LAST:event_BtnExcluirActionPerformed

    private void BtnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnNovoActionPerformed
        // TODO add your handling code here:
        ModalNovo.setSize(950,950);
        DataNasc1.setFont(new Font("Tahoma", Font.BOLD, 18));
        ModalNovo.setModal(true);
        ModalNovo.setLocationRelativeTo(null);
        ModalNovo.setVisible(true);
        
    }//GEN-LAST:event_BtnNovoActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        // TODO add your handling code here:
        ModalNovo.dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

    private void txtNome1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNome1ActionPerformed

    private void BtnSalvarAlteracoes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes1ActionPerformed
        boolean dadosvalidos = true;

            Paciente p = new Paciente();
            PacienteDAO dao = new PacienteDAO();
            Telefone tf = new Telefone();
            Telefone tf2 = new Telefone();
            TelefoneDAO tfdao = new TelefoneDAO();
            String msg = "Existem campos com formatos Inválidos\n\nFavor Verificar os campos:";

            if (!Validar.vCamposVazios(this, txtNome1, txtEmail1, txtCPF1, DataNasc1, TxtTelefone1)) {
                p.setCodPaciente(codigopaciente);
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

                if (Validar.vCPF(txtCPF1.getText())) {
                    p.setCPF(txtCPF1.getText());
                } else {
                    dadosvalidos = false;
                    msg += "\nCPF Invalido: " + txtCPF1.getText();
                }

                p.setEstadoCivil((String) estadocivil1.getSelectedItem());
                p.setSexo((String) Sexo1.getSelectedItem());
                p.setProfissao(TxtProfissao1.getText());
                p.setReligiao(TxtReligiao1.getText());
                p.setEscolaridade(TxtEscolaridade1.getText());
                p.setEndereco(TxtEndereco1.getText());
                p.setCidade(TxtCidade1.getText());

                //java.util.Date date = new java.util.Date();
                Object param = DataNasc1.getDate();

                p.setDataNasc(param);

                if (Validar.vTelefone(TxtTelefone1.getText())) {
                    tf.setNumero(TxtTelefone1.getText());
                } else {
                    dadosvalidos = false;
                    msg += "\nNúmero de Telefone Invalido: " + TxtTelefone1.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
                }
                if (!TxtTelefone21.getText().isEmpty()) {
                    if (Validar.vTelefone(TxtTelefone21.getText())) {
                        tf2.setNumero(TxtTelefone21.getText());
                    } else {
                        dadosvalidos = false;
                        msg += "\nNúmero de Telefone 2 Invalido: " + TxtTelefone21.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
                    }
                }
                if (dadosvalidos) {

                    if (dao.Update(p)) {

                        p = dao.ReadPaciente(p.getCPF());

                        tf.setPaciente(p);
                        List<Telefone> t = new ArrayList<>();
                        t = tfdao.Read(p.getCodPaciente());
                        t.get(0).setNumero(TxtTelefone1.getText());
                        if (tfdao.UpdateTPaciente(t.get(0))) {
                            if (t.size() == 2) {
                                t.get(1).setNumero(TxtTelefone21.getText());
                                tfdao.UpdateTPaciente(t.get(1));
                            }
                            JOptionPane.showMessageDialog(this, "Paciente " + p.getNome_Completo() + " Atualizado com sucesso");
                            this.clear();
                        }
                    };

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
                    ReadJTable();
                } else {
                    JOptionPane.showMessageDialog(this, msg);
                }
            }
            ModalAlterarPaciente.dispose();
    }//GEN-LAST:event_BtnSalvarAlteracoes1ActionPerformed

    private void BtnCancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar1ActionPerformed
        // TODO add your handling code here:
        ModalAlterarPaciente.dispose();
    }//GEN-LAST:event_BtnCancelar1ActionPerformed

    private void TxtReligiao1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtReligiao1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtReligiao1ActionPerformed

    private void BtnSalvarAlteracoes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalvarAlteracoes2ActionPerformed

    private void BtnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCancelar2ActionPerformed

    private void BtnModalAnamneseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnModalAnamneseActionPerformed
        ModalAnamnese.setSize(1039, 967);
        ModalAnamnese.setModal(true);
        ModalAnamnese.setLocationRelativeTo(null);
        ModalAnamnese.setVisible(true);
    }//GEN-LAST:event_BtnModalAnamneseActionPerformed

    private void BtnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar3ActionPerformed
       ModalAnamnese.dispose();
    }//GEN-LAST:event_BtnCancelar3ActionPerformed

    private void BtnSalvarAlteracoes3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes3ActionPerformed
        ModalAnamnese.dispose();
    }//GEN-LAST:event_BtnSalvarAlteracoes3ActionPerformed

    public void clear() {
        //limpar a tela
        txtNome.setText(null);
        txtEmail.setText(null);
        txtCPF.setText(null);
        estadocivil.setSelectedIndex(0);
        DataNasc.clear();
        Sexo.setSelectedIndex(0);
        TxtProfissao.setText(null);
        TxtReligiao.setText(null);
        TxtEscolaridade.setText(null);
        TxtEndereco.setText(null);
        TxtCidade.setText(null);
        TxtTelefone.setText(null);
        TxtTelefone2.setText(null);
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
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnCancelar1;
    private javax.swing.JButton BtnCancelar2;
    private javax.swing.JButton BtnCancelar3;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnManterAnotacao;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnModalAnamnese;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSalvarAlteracoes;
    private javax.swing.JButton BtnSalvarAlteracoes1;
    private javax.swing.JButton BtnSalvarAlteracoes2;
    private javax.swing.JButton BtnSalvarAlteracoes3;
    private javax.swing.JButton BtnVisuAlterarDados;
    private javax.swing.JButton BtnVoltar1;
    private com.github.lgooddatepicker.components.DatePicker DataNasc;
    private com.github.lgooddatepicker.components.DatePicker DataNasc1;
    private com.github.lgooddatepicker.components.DatePicker DataNasc2;
    private javax.swing.JComboBox<String> JCBAtributo;
    private javax.swing.JComboBox<String> JCBPsicomotricidade;
    private javax.swing.JComboBox<String> JCBPsicomotricidade1;
    private javax.swing.JComboBox<String> JCBQueixasAfetivoEmocionais;
    private javax.swing.JComboBox<String> JCBQueixasCognitivas;
    private javax.swing.JTable JTPacientes;
    private javax.swing.JLabel LabelCPF;
    private javax.swing.JLabel LabelCidade;
    private javax.swing.JLabel LabelCidade2;
    private javax.swing.JLabel LabelCidade3;
    private javax.swing.JLabel LabelCidade4;
    private javax.swing.JLabel LabelCidade5;
    private javax.swing.JLabel LabelCidade6;
    private javax.swing.JLabel LabelCidade7;
    private javax.swing.JLabel LabelEmail;
    private javax.swing.JLabel LabelEmail1;
    private javax.swing.JLabel LabelEscolaridade;
    private javax.swing.JLabel LabelEscolaridade1;
    private javax.swing.JLabel LabelEstadoCivil;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelNome1;
    private javax.swing.JLabel LabelNome2;
    private javax.swing.JLabel LabelNome3;
    private javax.swing.JLabel LabelProfissao;
    private javax.swing.JLabel LabelProfissao1;
    private javax.swing.JLabel LabelReligiao;
    private javax.swing.JLabel LabelReligiao1;
    private javax.swing.JDialog ModalAlterarPaciente;
    private javax.swing.JDialog ModalAnamnese;
    private javax.swing.JDialog ModalNovo;
    private javax.swing.JPanel Painel;
    private javax.swing.JPanel PainelCentro;
    private javax.swing.JPanel PainelCidade1;
    private javax.swing.JPanel PainelDadosPaciente;
    private javax.swing.JPanel PainelDadosPaciente1;
    private javax.swing.JPanel PainelDadosPaciente2;
    private javax.swing.JPanel PainelData1;
    private javax.swing.JPanel PainelEmail2;
    private javax.swing.JPanel PainelEndereco1;
    private javax.swing.JPanel PainelEscolaridade1;
    private javax.swing.JPanel PainelEsquerda;
    private javax.swing.JPanel PainelIdentificacaoPessoal;
    private javax.swing.JPanel PainelIdentificacaoPessoal1;
    private javax.swing.JPanel PainelIdentificacaoPessoal2;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelNome1;
    private javax.swing.JPanel PainelNorte;
    private javax.swing.JPanel PainelProfissao1;
    private javax.swing.JPanel PainelReligiao1;
    private javax.swing.JPanel PainelTelefone1;
    private javax.swing.JComboBox<String> Sexo;
    private javax.swing.JComboBox<String> Sexo1;
    private javax.swing.JTextField TxtCidade;
    private javax.swing.JTextField TxtCidade1;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtEndereco1;
    private javax.swing.JTextField TxtEscolaridade;
    private javax.swing.JTextField TxtEscolaridade1;
    private javax.swing.JTextField TxtProfissao;
    private javax.swing.JTextField TxtProfissao1;
    private javax.swing.JTextField TxtReligiao;
    private javax.swing.JTextField TxtReligiao1;
    private javax.swing.JTextField TxtTelefone;
    private javax.swing.JTextField TxtTelefone1;
    private javax.swing.JTextField TxtTelefone2;
    private javax.swing.JTextField TxtTelefone21;
    private javax.swing.JButton brnVisuConsultas;
    private javax.swing.JButton btnVisuAnamneses;
    private javax.swing.JButton btnVisuAnotacoes;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JComboBox<String> estadocivil;
    private javax.swing.JComboBox<String> estadocivil1;
    private LIB.JEImagePanel jEImagePanel2;
    private LIB.JEImagePanel jEImagePanel4;
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
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel labelInicioQueixa;
    private javax.swing.JLabel labelInicioQueixa1;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCPF1;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JTextField txtEmail10;
    private javax.swing.JTextField txtEmail11;
    private javax.swing.JTextField txtEmail12;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEmail3;
    private javax.swing.JTextField txtEmail4;
    private javax.swing.JTextField txtEmail5;
    private javax.swing.JTextField txtEmail6;
    private javax.swing.JTextField txtEmail7;
    private javax.swing.JTextField txtEmail8;
    private javax.swing.JTextField txtEmail9;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtNome1;
    // End of variables declaration//GEN-END:variables
}
