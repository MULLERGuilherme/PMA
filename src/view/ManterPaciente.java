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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
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
public class ManterPaciente extends javax.swing.JFrame {

    private boolean telefones = false;
    private boolean fone2 = false;

    /**
     * Creates new form ManterPaciente
     */
    public ManterPaciente() {
        initComponents();
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTPacientes.getModel();
        JTPacientes.setRowSorter(new TableRowSorter(dtmPacientes));
        TxtTelefone2.setVisible(false);
        LabelTelefone2.setVisible(false);
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

//    public void ReadJTable() {
//        int anterior = -1;
//        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();
//        model.setNumRows(0);
//        ViewsDAO vwdao = new ViewsDAO();
//        Object[] linha = null;
//        for (Vw_TelefonesPacientes vw : vwdao.ReadTelefonesPacientes()) {
//            if (anterior != vw.getPaciente().getCodPaciente()) {
//                if(anterior != -1) model.addRow(linha);
//                linha = new Object[]{
//                    vw.getPaciente().getCodPaciente(),
//                    vw.getPaciente().getNome_Completo(),
//                    vw.getPaciente().getEmail(),
//                    vw.getTelefone().getNumero(),
//                    null
//                };
//                anterior = vw.getPaciente().getCodPaciente();
//                
//            }else
//            {
//                linha[4] = vw.getTelefone().getNumero();
//                model.addRow(linha);
//               
//            }
//        }
//        if(linha != null)  model.addRow(linha);
//    }
    
     public void ReadJTableBusca(String Atributo, String Busca) {

        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();
        model.setNumRows(0);
        if(Atributo.equals("Nome Completo")) Atributo = "Paciente";
        if(Atributo.equals("Telefone")) Atributo = "numero";
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
//    public void ReadJTableBusca(String Atributo, String Busca) {
//
//        DefaultTableModel model = (DefaultTableModel) JTPacientes.getModel();
//        model.setNumRows(0);
//        PacienteDAO pdao = new PacienteDAO();
//        TelefoneDAO tdao = new TelefoneDAO();
//        for (Paciente p : pdao.Busca(Atributo, Busca)) {
//            List<Telefone> telefones = new ArrayList<>();
//            telefones = (List<Telefone>) tdao.Read(p.getCodPaciente());
//            if (telefones.size() == 1) {
//
//                model.addRow(new Object[]{
//                    p.getCodPaciente(),
//                    p.getNome_Completo(),
//                    p.getCPF(),
//                    p.getEmail(),
//                    p.getEstadoCivil(),
//                    Validar.fDataNascBD((Date) p.getDataNasc()),
//                    p.getSexo(),
//                    p.getProfissao(),
//                    p.getReligiao(),
//                    p.getEscolaridade(),
//                    p.getEndereco(),
//                    p.getCidade(),
//                    telefones.get(0).getNumero(),});
//            } else if (telefones.size() == 2) {
//                model.addRow(new Object[]{
//                    p.getCodPaciente(),
//                    p.getNome_Completo(),
//                    p.getCPF(),
//                    p.getEmail(),
//                    p.getEstadoCivil(),
//                    p.getDataNasc(),
//                    p.getSexo(),
//                    p.getProfissao(),
//                    p.getReligiao(),
//                    p.getEscolaridade(),
//                    p.getEndereco(),
//                    p.getCidade(),
//                    telefones.get(0).getNumero(),
//                    telefones.get(1).getNumero(),});
//            }
//        }
//    }

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

    public class JPanelGradient2 extends JPanel {

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int largura = getWidth();
            int altura = getHeight();
            Color cor1 = new Color(1, 112, 186);
            Color cor2 = new Color(22, 218, 218);
            GradientPaint gp = new GradientPaint(0, 0, cor1, 180, altura, cor2);
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, largura, altura);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        DadosPaciente = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        PainelNorte = new javax.swing.JPanel();
        jPanel3 = new JPanelGradient2();
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
        EstadoCivil = new javax.swing.JPanel();
        Nome = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        estadocivil = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TxtProfissao = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtReligiao = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        TxtEscolaridade = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TxtCidade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TxtTelefone = new javax.swing.JTextField();
        Sexo = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        TxtEndereco = new javax.swing.JTextField();
        DataNasc = new com.github.lgooddatepicker.components.DatePicker();
        TxtTelefone2 = new javax.swing.JTextField();
        LabelTelefone2 = new javax.swing.JLabel();
        BtnCadastrarPacientes = new javax.swing.JButton();
        BtnAdicionarTelefone = new javax.swing.JButton();
        BtnExcluir = new javax.swing.JButton();
        BtnAlterar = new javax.swing.JButton();
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
        BtnDialog = new javax.swing.JButton();

        DadosPaciente.setLocationRelativeTo(null);

        jLabel1.setText("Dados Paciente");

        javax.swing.GroupLayout DadosPacienteLayout = new javax.swing.GroupLayout(DadosPaciente.getContentPane());
        DadosPaciente.getContentPane().setLayout(DadosPacienteLayout);
        DadosPacienteLayout.setHorizontalGroup(
            DadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DadosPacienteLayout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(339, Short.MAX_VALUE))
        );
        DadosPacienteLayout.setVerticalGroup(
            DadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DadosPacienteLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(950, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PainelNorte.setPreferredSize(new java.awt.Dimension(1080, 89));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
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

        Nome.setText("Nome");

        jLabel2.setText("CPF");

        jLabel3.setText("Email");

        jLabel4.setText("Estado Civil");

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        txtCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCPFActionPerformed(evt);
            }
        });

        estadocivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solteiro", "Casado", "Separado", "Divorciado","Viuvo" }));

        jLabel7.setText("DataNasc");

        jLabel8.setText("Sexo");

        jLabel9.setText("Profissão");

        jLabel10.setText("Religião");

        jLabel12.setText("Escolaridade");

        jLabel13.setText("Cidade");

        jLabel5.setText("Telefone");

        Sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino","Não Definido"}));

        jLabel14.setText("Endereço");

        TxtTelefone2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTelefone2ActionPerformed(evt);
            }
        });

        LabelTelefone2.setText("Telefone(2)");

        BtnCadastrarPacientes.setText("Cadastrar");
        BtnCadastrarPacientes.setBackground(new java.awt.Color(0, 112, 186));
        BtnCadastrarPacientes.setForeground(new java.awt.Color(255, 255, 255));
        BtnCadastrarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCadastrarPacientesActionPerformed(evt);
            }
        });

        BtnAdicionarTelefone.setText("Adicionar Telefone");
        BtnAdicionarTelefone.setBackground(new java.awt.Color(204, 204, 204));
        BtnAdicionarTelefone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnAdicionarTelefoneMouseClicked(evt);
            }
        });
        BtnAdicionarTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAdicionarTelefoneActionPerformed(evt);
            }
        });

        BtnExcluir.setText("Excluir");
        BtnExcluir.setBackground(new java.awt.Color(204, 204, 204));
        BtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirActionPerformed(evt);
            }
        });

        BtnAlterar.setText("Alterar");
        BtnAlterar.setBackground(new java.awt.Color(204, 204, 204));
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
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

        BtnDialog.setText("Modal");
        BtnDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BtnDialogMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout EstadoCivilLayout = new javax.swing.GroupLayout(EstadoCivil);
        EstadoCivil.setLayout(EstadoCivilLayout);
        EstadoCivilLayout.setHorizontalGroup(
            EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstadoCivilLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addGap(18, 18, 18)
                        .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brnVisuConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisuAnamneses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisuAnotacoes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EstadoCivilLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(320, 320, 320))
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel15)
                        .addGap(40, 40, 40)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnDialog)
                            .addComponent(BtnBuscar))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(EstadoCivilLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(EstadoCivilLayout.createSequentialGroup()
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EstadoCivilLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(Nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EstadoCivilLayout.createSequentialGroup()
                                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(EstadoCivilLayout.createSequentialGroup()
                                    .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13)
                                        .addGroup(EstadoCivilLayout.createSequentialGroup()
                                            .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(TxtProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(20, 20, 20)
                                            .addComponent(jLabel10)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(TxtReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(BtnAdicionarTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnCadastrarPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(EstadoCivilLayout.createSequentialGroup()
                                .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addComponent(jLabel11)))
                .addGap(95, 95, 95)
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EstadoCivilLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TxtEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EstadoCivilLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EstadoCivilLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EstadoCivilLayout.createSequentialGroup()
                        .addComponent(LabelTelefone2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EstadoCivilLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
        );
        EstadoCivilLayout.setVerticalGroup(
            EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstadoCivilLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCPF, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(EstadoCivilLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(Nome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(EstadoCivilLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtNome))
                            .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)))
                        .addGap(1, 1, 1)))
                .addGap(22, 22, 22)
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(TxtEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(BtnAdicionarTelefone)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCadastrarPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnDialog))
                        .addGap(50, 50, 50))
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelTelefone2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBuscar))
                .addGap(18, 18, 18)
                .addGroup(EstadoCivilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addComponent(brnVisuConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnVisuAnamneses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(btnVisuAnotacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(EstadoCivilLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout PainelCentroLayout = new javax.swing.GroupLayout(PainelCentro);
        PainelCentro.setLayout(PainelCentroLayout);
        PainelCentroLayout.setHorizontalGroup(
            PainelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 849, Short.MAX_VALUE)
            .addGroup(PainelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelCentroLayout.createSequentialGroup()
                    .addGap(17, 17, 17)
                    .addComponent(EstadoCivil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        PainelCentroLayout.setVerticalGroup(
            PainelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 663, Short.MAX_VALUE)
            .addGroup(PainelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PainelCentroLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(EstadoCivil, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
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
        ManterPaciente mp1 = new ManterPaciente();
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

    private void txtCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCPFActionPerformed

    private void TxtTelefone2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTelefone2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTelefone2ActionPerformed

    private void BtnCadastrarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCadastrarPacientesActionPerformed
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
            System.out.println("era aki");
            System.out.println(param);
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
    }//GEN-LAST:event_BtnCadastrarPacientesActionPerformed

    private void BtnAdicionarTelefoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnAdicionarTelefoneMouseClicked

        if (BtnAdicionarTelefone.getText() == "Adicionar Telefone") {
            fone2 = true;
            BtnAdicionarTelefone.setText("Remover Telefone");
            TxtTelefone2.setVisible(true);
            LabelTelefone2.setVisible(true);
            // fone2 = true;
        } else {
            fone2 = false;
            BtnAdicionarTelefone.setText("Adicionar Telefone");
            TxtTelefone2.setVisible(false);
            TxtTelefone2.setText(null);
            LabelTelefone2.setVisible(false);
            // fone2 = false;
        }
    }//GEN-LAST:event_BtnAdicionarTelefoneMouseClicked

    private void BtnAdicionarTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAdicionarTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAdicionarTelefoneActionPerformed

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

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            boolean dadosvalidos = true;

            Paciente p = new Paciente();
            PacienteDAO dao = new PacienteDAO();
            Telefone tf = new Telefone();
            Telefone tf2 = new Telefone();
            TelefoneDAO tfdao = new TelefoneDAO();
            String msg = "Existem campos com formatos Inválidos\n\nFavor Verificar os campos:";

            if (!Validar.vCamposVazios(this, txtNome, txtEmail, txtCPF, DataNasc, TxtTelefone)) {
                p.setCodPaciente((int) JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 0));
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
                p.setSexo((String) Sexo.getSelectedItem());
                p.setProfissao(TxtProfissao.getText());
                p.setReligiao(TxtReligiao.getText());
                p.setEscolaridade(TxtEscolaridade.getText());
                p.setEndereco(TxtEndereco.getText());
                p.setCidade(TxtCidade.getText());

                //java.util.Date date = new java.util.Date();
                Object param = DataNasc.getDate();

                p.setDataNasc(param);

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

                    if (dao.Update(p)) {

                        p = dao.ReadPaciente(p.getCPF());

                        tf.setPaciente(p);
                        List<Telefone> t = new ArrayList<>();
                        t = tfdao.Read(p.getCodPaciente());
                        t.get(0).setNumero(TxtTelefone.getText());
                        if (tfdao.UpdateTPaciente(t.get(0))) {
                            if (t.size() == 2) {
                                t.get(1).setNumero(TxtTelefone2.getText());
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

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para alterar");
        }
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void JTPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTPacientesMouseClicked
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            txtNome.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 1).toString());
            txtEmail.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 3).toString());
            txtCPF.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 2).toString());
            estadocivil.setSelectedItem(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 4).toString());

            String data = (String) JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 5);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(data, formatter);
            DataNasc.setDate(date);
            Sexo.setSelectedItem(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 6).toString());

            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 7) == null) {
                TxtProfissao.setText(null);
            } else {
                TxtProfissao.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 7).toString());
            }

            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 8) == null) {
                TxtReligiao.setText(null);
            } else {
                TxtReligiao.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 8).toString());
            }
            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 9) == null) {
                TxtEscolaridade.setText(null);
            } else {
                TxtEscolaridade.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 9).toString());
            }
            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 10) == null) {
                TxtEndereco.setText(null);
            } else {
                TxtEndereco.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 10).toString());
            }
            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 11) == null) {
                TxtCidade.setText(null);
            } else {
                TxtCidade.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 11).toString());
            }

            TxtTelefone.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 12).toString());
            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 13) == null) {
                TxtTelefone2.setText(null);
                TxtTelefone2.setVisible(false);
                LabelTelefone2.setVisible(false);
                TxtTelefone2.setText(null);

            } else {
                TxtTelefone2.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 13).toString());
                TxtTelefone2.setVisible(true);
                LabelTelefone2.setVisible(true);

            }
        }
    }//GEN-LAST:event_JTPacientesMouseClicked

    private void JTPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPacientesKeyReleased
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            txtNome.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 1).toString());
            txtEmail.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 3).toString());
            txtCPF.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 2).toString());
            estadocivil.setSelectedItem(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 4).toString());

            String data = (String) JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 5);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(data, formatter);
            DataNasc.setDate(date);
            Sexo.setSelectedItem(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 6).toString());

            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 7) == null) {
                TxtProfissao.setText(null);
            } else {
                TxtProfissao.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 7).toString());
            }

            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 8) == null) {
                TxtReligiao.setText(null);
            } else {
                TxtReligiao.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 8).toString());
            }
            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 9) == null) {
                TxtEscolaridade.setText(null);
            } else {
                TxtEscolaridade.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 9).toString());
            }
            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 10) == null) {
                TxtEndereco.setText(null);
            } else {
                TxtEndereco.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 10).toString());
            }
            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 11) == null) {
                TxtCidade.setText(null);
            } else {
                TxtCidade.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 11).toString());
            }

            TxtTelefone.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 12).toString());
            if (JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 13) == null) {
                TxtTelefone2.setText(null);
                TxtTelefone2.setVisible(false);
                LabelTelefone2.setVisible(false);
                TxtTelefone2.setText(null);

            } else {
                TxtTelefone2.setText(JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 13).toString());
                TxtTelefone2.setVisible(true);
                LabelTelefone2.setVisible(true);
            }
        }
    }//GEN-LAST:event_JTPacientesKeyReleased

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        // TODO add your handling code here:
        //System.out.println(JCBAtributo.getSelectedIndex());
        this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void btnVisuAnotacoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisuAnotacoesActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            boolean dadosvalidos = true;
            ExibirAnotacoesPaciente.codpaciente = ((int) JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 0));
            ExibirAnotacoesPaciente ap = new ExibirAnotacoesPaciente();
            ap.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_btnVisuAnotacoesActionPerformed

    private void btnVisuAnamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisuAnamnesesActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            boolean dadosvalidos = true;
            ExibirAnamnesesPaciente.codpaciente = ((int) JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 0));
            ExibirAnamnesesPaciente ap = new ExibirAnamnesesPaciente();
            ap.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_btnVisuAnamnesesActionPerformed

    private void brnVisuConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brnVisuConsultasActionPerformed
        // TODO add your handling code here:
        if (JTPacientes.getSelectedRow() != -1) {

            ExibirConsultasPaciente.codpaciente = ((int) JTPacientes.getValueAt(JTPacientes.getSelectedRow(), 0));
            ExibirConsultasPaciente cp = new ExibirConsultasPaciente();
            cp.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione um paciente para Vizualizar Informações");
        }
    }//GEN-LAST:event_brnVisuConsultasActionPerformed

    private void BtnDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnDialogMouseClicked
            ModalPaciente MP= new ModalPaciente();
            MP.setVisible(true);
    }//GEN-LAST:event_BtnDialogMouseClicked

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
            java.util.logging.Logger.getLogger(ManterPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ManterPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdicionarTelefone;
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCadastrarPacientes;
    private javax.swing.JButton BtnDialog;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnManterAnotacao;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnVoltar1;
    private javax.swing.JDialog DadosPaciente;
    private com.github.lgooddatepicker.components.DatePicker DataNasc;
    private javax.swing.JPanel EstadoCivil;
    private javax.swing.JComboBox<String> JCBAtributo;
    private javax.swing.JTable JTPacientes;
    private javax.swing.JLabel LabelTelefone2;
    private javax.swing.JLabel Nome;
    private javax.swing.JPanel PainelCentro;
    private javax.swing.JPanel PainelEsquerda;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelNorte;
    private javax.swing.JComboBox<String> Sexo;
    private javax.swing.JTextField TxtCidade;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtEscolaridade;
    private javax.swing.JTextField TxtProfissao;
    private javax.swing.JTextField TxtReligiao;
    private javax.swing.JTextField TxtTelefone;
    private javax.swing.JTextField TxtTelefone2;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
