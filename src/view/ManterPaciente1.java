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
public class ManterPaciente1 extends javax.swing.JFrame {

    private boolean telefones = false;
    private boolean fone2 = false;

    /**
     * Creates new form ManterPaciente
     */
    public ManterPaciente1() {
        initComponents();
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTPacientes.getModel();
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
        java.awt.GridBagConstraints gridBagConstraints;

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        Dialog = new javax.swing.JDialog();
        PainelDadosPaciente = new javax.swing.JPanel();
        PainelIdentificacaoPessoal = new javax.swing.JPanel();
        PainelData = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        DataNasc = new com.github.lgooddatepicker.components.DatePicker();
        jLabel18 = new javax.swing.JLabel();
        Sexo = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        estadocivil = new javax.swing.JComboBox<>();
        PainelEndereco = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        TxtEndereco = new javax.swing.JTextField();
        PainelNome = new javax.swing.JPanel();
        LabelNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        PainelCidade = new javax.swing.JPanel();
        LabelCidade = new javax.swing.JLabel();
        LabelCidade1 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        TxtCidade = new javax.swing.JTextField();
        PainelProfissao = new javax.swing.JPanel();
        TxtProfissao = new javax.swing.JTextField();
        LabelProfissao = new javax.swing.JLabel();
        PainelReligiao = new javax.swing.JPanel();
        TxtReligiao = new javax.swing.JTextField();
        LabelReligiao = new javax.swing.JLabel();
        PainelEscolaridade = new javax.swing.JPanel();
        TxtEscolaridade = new javax.swing.JTextField();
        LabelEscolaridade = new javax.swing.JLabel();
        PainelTelefone = new javax.swing.JPanel();
        LabelCidade2 = new javax.swing.JLabel();
        LabelCidade3 = new javax.swing.JLabel();
        TxtTelefone2 = new javax.swing.JTextField();
        TxtTelefone = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BtnSalvarAlteracoes = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        BtnCancelar = new javax.swing.JButton();
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
        Painel = new javax.swing.JPanel();
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
        BtnNovo = new javax.swing.JButton();

        Dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        Dialog.getContentPane().setLayout(new java.awt.GridBagLayout());

        PainelDadosPaciente.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal.setBackground(new java.awt.Color(0, 255, 0));

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

        PainelData.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setText("   *  Data de Nascimento: ");
        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        DataNasc.setPreferredSize(new java.awt.Dimension(160, 17));

        jLabel18.setText("*  Sexo:");
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        Sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino","Não Definido"}));

        jLabel19.setText("*  Estado Civil:");
        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        estadocivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solteiro", "Casado", "Separado", "Divorciado","Viuvo" }));

        javax.swing.GroupLayout PainelDataLayout = new javax.swing.GroupLayout(PainelData);
        PainelData.setLayout(PainelDataLayout);
        PainelDataLayout.setHorizontalGroup(
            PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(estadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelDataLayout.setVerticalGroup(
            PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(estadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel18))
                    .addGroup(PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel17)
                        .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        PainelEndereco.setBackground(new java.awt.Color(255, 255, 255));

        jLabel20.setText("   Endereço:");
        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtEndereco.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelEnderecoLayout = new javax.swing.GroupLayout(PainelEndereco);
        PainelEndereco.setLayout(PainelEnderecoLayout);
        PainelEnderecoLayout.setHorizontalGroup(
            PainelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEnderecoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 756, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelEnderecoLayout.setVerticalGroup(
            PainelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEnderecoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(PainelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
        );

        PainelNome.setBackground(new java.awt.Color(255, 255, 255));

        LabelNome.setText("*  Nome:");
        LabelNome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtNome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelNomeLayout = new javax.swing.GroupLayout(PainelNome);
        PainelNome.setLayout(PainelNomeLayout);
        PainelNomeLayout.setHorizontalGroup(
            PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelNomeLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(LabelNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
        );
        PainelNomeLayout.setVerticalGroup(
            PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelNomeLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(LabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        PainelCidade.setBackground(new java.awt.Color(255, 255, 255));

        LabelCidade.setText("*  CPF:");
        LabelCidade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCidade1.setText("    Cidade:");
        LabelCidade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtCPF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelCidadeLayout = new javax.swing.GroupLayout(PainelCidade);
        PainelCidade.setLayout(PainelCidadeLayout);
        PainelCidadeLayout.setHorizontalGroup(
            PainelCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCidadeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(LabelCidade1)
                .addGap(32, 32, 32)
                .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 426, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LabelCidade)
                .addGap(18, 18, 18)
                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelCidadeLayout.setVerticalGroup(
            PainelCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LabelCidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(PainelCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(LabelCidade1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(TxtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        PainelProfissao.setBackground(new java.awt.Color(255, 255, 255));

        TxtProfissao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelProfissao.setText("        Profissão:");
        LabelProfissao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelProfissaoLayout = new javax.swing.GroupLayout(PainelProfissao);
        PainelProfissao.setLayout(PainelProfissaoLayout);
        PainelProfissaoLayout.setHorizontalGroup(
            PainelProfissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelProfissaoLayout.createSequentialGroup()
                .addComponent(LabelProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelProfissaoLayout.setVerticalGroup(
            PainelProfissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelProfissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(TxtProfissao, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addComponent(LabelProfissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PainelReligiao.setBackground(new java.awt.Color(255, 255, 255));

        TxtReligiao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelReligiao.setText("        Religião:");
        LabelReligiao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelReligiaoLayout = new javax.swing.GroupLayout(PainelReligiao);
        PainelReligiao.setLayout(PainelReligiaoLayout);
        PainelReligiaoLayout.setHorizontalGroup(
            PainelReligiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelReligiaoLayout.createSequentialGroup()
                .addComponent(LabelReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelReligiaoLayout.setVerticalGroup(
            PainelReligiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelReligiao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TxtReligiao, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        PainelEscolaridade.setBackground(new java.awt.Color(255, 255, 255));

        TxtEscolaridade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEscolaridade.setText("        Escolaridade:");
        LabelEscolaridade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelEscolaridadeLayout = new javax.swing.GroupLayout(PainelEscolaridade);
        PainelEscolaridade.setLayout(PainelEscolaridadeLayout);
        PainelEscolaridadeLayout.setHorizontalGroup(
            PainelEscolaridadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEscolaridadeLayout.createSequentialGroup()
                .addComponent(LabelEscolaridade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelEscolaridadeLayout.setVerticalGroup(
            PainelEscolaridadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelEscolaridade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TxtEscolaridade, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        PainelTelefone.setBackground(new java.awt.Color(255, 255, 255));

        LabelCidade2.setText("Telefone2:");
        LabelCidade2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelCidade3.setText("*  Telefone1:");
        LabelCidade3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        TxtTelefone2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelTelefoneLayout = new javax.swing.GroupLayout(PainelTelefone);
        PainelTelefone.setLayout(PainelTelefoneLayout);
        PainelTelefoneLayout.setHorizontalGroup(
            PainelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTelefoneLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(LabelCidade3)
                .addGap(31, 31, 31)
                .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelCidade2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelTelefoneLayout.setVerticalGroup(
            PainelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTelefoneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelCidade3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LabelCidade2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(TxtTelefone)))
        );

        jLabel1.setText("* Campos Obrigatórios");
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

        jLabel2.setText("Email");

        BtnCancelar.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar.setText("Cancelar");
        BtnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelDadosPacienteLayout = new javax.swing.GroupLayout(PainelDadosPaciente);
        PainelDadosPaciente.setLayout(PainelDadosPacienteLayout);
        PainelDadosPacienteLayout.setHorizontalGroup(
            PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PainelTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelEscolaridade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelReligiao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelProfissao, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelEndereco, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelCidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PainelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(60, 60, 60))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPacienteLayout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnSalvarAlteracoes)
                .addGap(54, 54, 54))
        );
        PainelDadosPacienteLayout.setVerticalGroup(
            PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(PainelIdentificacaoPessoal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipady = 51;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        Dialog.getContentPane().add(PainelDadosPaciente, gridBagConstraints);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PainelNorte.setPreferredSize(new java.awt.Dimension(1080, 89));

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

        BtnNovo.setBackground(new java.awt.Color(204, 204, 204));
        BtnNovo.setText("Novo");
        BtnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnNovoActionPerformed(evt);
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
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brnVisuConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisuAnotacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                            .addComponent(BtnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisuAnamneses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnNovo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        PainelLayout.setVerticalGroup(
            PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelLayout.createSequentialGroup()
                .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addGroup(PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JCBAtributo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(BtnBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51))
                    .addGroup(PainelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(BtnNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(brnVisuConsultas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisuAnamneses)
                        .addGap(35, 35, 35)
                        .addComponent(btnVisuAnotacoes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
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
                ReadJTable();
            } else {
                JOptionPane.showMessageDialog(this, msg);
            }
        }
        Dialog.dispose();
    }//GEN-LAST:event_BtnSalvarAlteracoesActionPerformed

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

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        // TODO add your handling code here:
        //System.out.println(JCBAtributo.getSelectedIndex());
        this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void JTPacientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTPacientesKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JTPacientesKeyReleased

    private void JTPacientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTPacientesMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_JTPacientesMouseClicked

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
        Dialog.setSize(1039, 967);
        Dialog.setModal(true);
        Dialog.setLocationRelativeTo(null);
        Dialog.setVisible(true);
    }//GEN-LAST:event_BtnNovoActionPerformed

    private void BtnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarActionPerformed
        // TODO add your handling code here:
        Dialog.dispose();
    }//GEN-LAST:event_BtnCancelarActionPerformed

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
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnCancelar;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnManterAnotacao;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnNovo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSalvarAlteracoes;
    private javax.swing.JButton BtnVoltar1;
    private com.github.lgooddatepicker.components.DatePicker DataNasc;
    private javax.swing.JDialog Dialog;
    private javax.swing.JComboBox<String> JCBAtributo;
    private javax.swing.JTable JTPacientes;
    private javax.swing.JLabel LabelCidade;
    private javax.swing.JLabel LabelCidade1;
    private javax.swing.JLabel LabelCidade2;
    private javax.swing.JLabel LabelCidade3;
    private javax.swing.JLabel LabelEscolaridade;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelProfissao;
    private javax.swing.JLabel LabelReligiao;
    private javax.swing.JPanel Painel;
    private javax.swing.JPanel PainelCentro;
    private javax.swing.JPanel PainelCidade;
    private javax.swing.JPanel PainelDadosPaciente;
    private javax.swing.JPanel PainelData;
    private javax.swing.JPanel PainelEndereco;
    private javax.swing.JPanel PainelEscolaridade;
    private javax.swing.JPanel PainelEsquerda;
    private javax.swing.JPanel PainelIdentificacaoPessoal;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelNome;
    private javax.swing.JPanel PainelNorte;
    private javax.swing.JPanel PainelProfissao;
    private javax.swing.JPanel PainelReligiao;
    private javax.swing.JPanel PainelTelefone;
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
    private javax.swing.JLabel btnnewuser1;
    private javax.swing.JLabel btnnewuser2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.JComboBox<String> estadocivil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
