/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Validacoes.Validar;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.bean.Anamnese;
import model.bean.Consulta;
import model.bean.Paciente;
import model.bean.Psicologo;
import model.bean.Telefone;
import model.bean.Vw_Anamnese_Paciente;
import model.dao.AnamneseDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.PsicologoDAO;
import model.dao.TelefoneDAO;
import model.dao.ViewsDAO;

import util.Util;
import static view.Main.cod;
import static view.ManterPsicologo.codpsicologo;

/**
 *
 * @author guimu
 */
public class ExibirAnamneses2 extends javax.swing.JFrame {

    public static int codpaciente;
    public static int codigoanamnese;
    public boolean existe;

    //Paginacao
    int PAGE_SIZE = 15;
    double tableRowCount;
    int totalPages = 1;
    int currentPage = 1;
    int startRow = 0;
    private int codigopsicologo;

    public void getCount() {
        ViewsDAO dao = new ViewsDAO();
        tableRowCount = dao.getRowCountTableExibirAnamnesesPaciente(Main.cod, this.codpaciente);
//        System.out.println("linhas");
//        System.out.println(tableRowCount);
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);

        }
        currentPage = 1;

    }

    public void getCountBusca(String Busca) {
        ViewsDAO dao = new ViewsDAO();

        tableRowCount = dao.getRowCountTableExibirAnamnesesPacienteBusca(Main.cod, this.codpaciente, Busca);
        //System.out.println(tableRowCount);
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);

        }
        currentPage = 1;

    }

    public void getPageData(int pageNo) {

        currentPage = pageNo;

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

    /**
     * Creates new form ExibirAnamneses2
     */
    public ExibirAnamneses2(int cod) {

        this.codpaciente = cod;
        this.getCount();

        initComponents();

        ModalAnamnese2.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalMeusDados.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));

        Psicologo ps = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        ps = dao.ReadPsicologo(Main.cod);
        jLabel11.setText(ps.getNome_completo());
        String str = getFirstWord(jLabel11.getText());
        jLabel11.setText(str);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        BtnPacientes.setEnabled(false);
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if (totalPages == 1) {
            BtnAvancarPouco.setEnabled(false);
            BtnAvancarBastante.setEnabled(false);
        }
        btnalterar.setEnabled(false);
        btnExcluir.setEnabled(false);

        PacienteDAO pdao = new PacienteDAO();
        Paciente p;
        p = pdao.ReadPaciente(codpaciente);
        lNome.setText(p.getNome_Completo());
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTAnamneses.getModel();
        TableColumnModel cmod = JTAnamneses.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        cmod.removeColumn(cmod.getColumn(0));
        JTAnamneses.setRowSorter(new TableRowSorter(dtmPacientes));

        SpinnerNumPaginas.setValue((int) currentPage);
        LabelQtdePaginas.setText("de " + totalPages);
        SpinnerLimite.setValue((int) PAGE_SIZE);
        System.out.println(totalPages);
        this.getPageData(1);
    }

    private String getFirstWord(String text) {

        int index = text.indexOf(' ');

        if (index > -1) { // Check if there is more than one word.

            return text.substring(0, index).trim(); // Extract first word.

        } else {

            return text; // Text is the first word itself.
        }
    }

    public void readpsicologo() {
        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        p = dao.ReadPsicologo(Main.cod);
        codpsicologo = p.getCodPsicologo();
        List<Telefone> t = new ArrayList<>();
        TelefoneDAO tdao = new TelefoneDAO();
        t = tdao.ReadTPsicologo(p.getCodPsicologo());
        txtNome3.setText(p.getNome_completo());
        

        fcrp.setText(p.getCRP());
        txtEmail2.setText(p.getEmail());
        TxtTelefone6.setText(t.get(0).getNumero());

        if (t.size() == 2) {
            TxtTelefone7.setVisible(true);
            labeltelefone2.setVisible(true);
            TxtTelefone7.setText(t.get(1).getNumero());

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
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        PainelDadosPaciente4 = new javax.swing.JPanel();
        PainelIdentificacaoPessoal4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        BtnSalvarAlteracoes5 = new javax.swing.JButton();
        BtnCancelar5 = new javax.swing.JButton();
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
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        LabelNomePaciente = new javax.swing.JLabel();
        labelnomepaciente = new javax.swing.JLabel();
        ModalAlterarSenha = new javax.swing.JDialog();
        jPanel8 = new JPanel();
        jLabel43 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        txtSenha1 = new javax.swing.JPasswordField();
        jLabel45 = new javax.swing.JLabel();
        txtConfirmarSenha1 = new javax.swing.JPasswordField();
        chckMostrarSenha1 = new javax.swing.JCheckBox();
        BtnAlterar1 = new javax.swing.JButton();
        BtnCancelar8 = new javax.swing.JButton();
        ModalHelp = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        ModalMeusDados = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        txtNome3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        labeltelefone = new javax.swing.JLabel();
        TxtTelefone6 = new javax.swing.JTextField();
        labeltelefone2 = new javax.swing.JLabel();
        TxtTelefone7 = new javax.swing.JTextField();
        PainelIdentificacaoPessoal6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        BtnAlterar = new javax.swing.JButton();
        LabelEsqueciSenha = new javax.swing.JLabel();
        fcrp = new javax.swing.JFormattedTextField();
        BtnCancelar9 = new javax.swing.JButton();
        jPanel1 = new JPanel();
        jLabel2 = new javax.swing.JLabel();
        PainelMeusDados = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        btnMeusDados = new javax.swing.JButton();
        jEImagePanel1 = new LIB.JEImagePanel();
        btnalterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        lNome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAnamneses = new javax.swing.JTable();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
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
        BtnVoltarPaciente = new javax.swing.JButton();
        BtnHelp = new javax.swing.JButton();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnPacientes = new javax.swing.JButton();
        BtnExibirAnamneses = new javax.swing.JButton();
        BtnExibirAnotacoes = new javax.swing.JButton();
        BtnExibirAnotacoes1 = new javax.swing.JButton();

        ModalAnamnese2.setResizable(false);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        PainelDadosPaciente4.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente4.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal4.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal4Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal4);
        PainelIdentificacaoPessoal4.setLayout(PainelIdentificacaoPessoal4Layout);
        PainelIdentificacaoPessoal4Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal4Layout.setVerticalGroup(
            PainelIdentificacaoPessoal4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel18.setText("* Campos Obrigatórios");
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes5.setText("Salvar Alterações");
        BtnSalvarAlteracoes5.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes5.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes5ActionPerformed(evt);
            }
        });

        BtnCancelar5.setText("Cancelar");
        BtnCancelar5.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar5.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar5ActionPerformed(evt);
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

        LabelNomePaciente.setText("Nome do Paciente :");
        LabelNomePaciente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelnomepaciente.setText(" Variável Nome do Paciente");
        labelnomepaciente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelDadosPaciente4Layout = new javax.swing.GroupLayout(PainelDadosPaciente4);
        PainelDadosPaciente4.setLayout(PainelDadosPaciente4Layout);
        PainelDadosPaciente4Layout.setHorizontalGroup(
            PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar5)
                .addGap(42, 42, 42)
                .addComponent(BtnSalvarAlteracoes5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel18))
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel22))
                                .addGap(19, 19, 19)
                                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LabelNome5)
                                            .addComponent(jLabel57))
                                        .addGap(34, 34, 34)
                                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                                .addComponent(DataInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 393, Short.MAX_VALUE))
                                            .addComponent(txtQueixaPrincipal2)))
                                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                        .addComponent(LabelNomePaciente)
                                        .addGap(18, 18, 18)
                                        .addComponent(labelnomepaciente))))
                            .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnCancelar7)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnSalvarAlteracoes7))
                                .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(jLabel28))
                                .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                    .addComponent(jLabel25)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
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
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel54)
                                                .addComponent(jLabel55)
                                                .addComponent(jLabel53))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtComoComecou2)
                                                .addComponent(txtDiagnostico2)
                                                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtSintomas2)
                                                    .addComponent(txtMedicamentosUtilizados2)
                                                    .addComponent(txtOqueMudou2)
                                                    .addComponent(txtDoencasConhecidas2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtEncaminhamento2)
                                                .addComponent(txtHistoricoFamiliar2)))
                                        .addComponent(labelInicioQueixa5)
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(LabelEmail3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtQueixaSecundaria2))
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
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
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addComponent(jLabel39)
                                            .addGap(32, 32, 32)
                                            .addComponent(SubitaOuProgressiva2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(jLabel26)
                                            .addGap(6, 6, 6)
                                            .addComponent(labelInicioQueixa4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(JCBPsicomotricidade2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelDadosPaciente4Layout.setVerticalGroup(
            PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEImagePanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNomePaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelnomepaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQueixaPrincipal2)
                    .addComponent(LabelNome5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel57)
                    .addComponent(DataInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmail3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQueixaSecundaria2))
                .addGap(18, 18, 18)
                .addComponent(labelInicioQueixa5)
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addComponent(jLabel28)
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckBoxIntegridadeSensorial1)
                    .addComponent(CheckBoxPercepcao1)
                    .addComponent(CheckBoxAtencao1)
                    .addComponent(CheckBoxMemoria1))
                .addGap(19, 19, 19)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(SubitaOuProgressiva2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInicioQueixa4)
                    .addComponent(JCBPsicomotricidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtComoComecou2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtDiagnostico2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtEncaminhamento2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtHistoricoFamiliar2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(txtDoencasConhecidas2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtSintomas2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMedicamentosUtilizados2)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOqueMudou2)
                    .addComponent(jLabel55))
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCancelar7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSalvarAlteracoes7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel30))))
                .addGap(371, 371, 371)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoes5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelDadosPaciente4, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1081, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(PainelDadosPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout ModalAnamnese2Layout = new javax.swing.GroupLayout(ModalAnamnese2.getContentPane());
        ModalAnamnese2.getContentPane().setLayout(ModalAnamnese2Layout);
        ModalAnamnese2Layout.setHorizontalGroup(
            ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
        );
        ModalAnamnese2Layout.setVerticalGroup(
            ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1081, Short.MAX_VALUE)
        );

        ModalAlterarSenha.setResizable(false);

        jPanel8.setBackground(new java.awt.Color(59, 131, 117));
        jPanel8.setForeground(new java.awt.Color(59, 131, 117));
        jPanel8.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Alterar Senha");
        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel43, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
        );

        jLabel44.setText("Nova Senha");
        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtSenha1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel45.setText("Confirme a Nova Senha");
        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtConfirmarSenha1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        chckMostrarSenha1.setText("MostrarSenha");
        chckMostrarSenha1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chckMostrarSenha1MouseClicked(evt);
            }
        });

        BtnAlterar1.setText("Alterar");
        BtnAlterar1.setBackground(new java.awt.Color(0, 112, 186));
        BtnAlterar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnAlterar1.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterar1ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 37, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel45)
                    .addComponent(jLabel44)
                    .addComponent(txtSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(chckMostrarSenha1)
                        .addComponent(txtConfirmarSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnCancelar8, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                    .addComponent(BtnAlterar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel45)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtConfirmarSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chckMostrarSenha1)
                .addGap(18, 18, 18)
                .addComponent(BtnAlterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnCancelar8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ModalAlterarSenhaLayout = new javax.swing.GroupLayout(ModalAlterarSenha.getContentPane());
        ModalAlterarSenha.getContentPane().setLayout(ModalAlterarSenhaLayout);
        ModalAlterarSenhaLayout.setHorizontalGroup(
            ModalAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
        );
        ModalAlterarSenhaLayout.setVerticalGroup(
            ModalAlterarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModalAlterarSenhaLayout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ModalHelp.setResizable(false);

        jPanel12.setBackground(new java.awt.Color(59, 131, 117));

        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel83.setText("AJUDA");
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel87.setText("Como Visualizar ou Editar uma Anamnese?");
        jLabel87.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel88.setText("Selecionando uma anamnese na tabela e clicando duas vezes ou apertando o botão \"Alterar\", será aberta uma ");

        jLabel89.setText("janela para que se possa visualizar e editar os dados de uma anamnese, para que os dados da anamnese sejam");

        jLabel90.setText("atualizados é necessário apenas clicar no botão \"Salvar Alterações\"");

        jLabel92.setText("Selecionando uma anamnese na tabela e clicando duas vezes ou apertando o botão \"Excluirr\", será aberta uma ja");

        jLabel93.setText("nela pedindo a confirmação da exclusão da anamnese, caso a exclusão seja confirmada a anamnese é deletada");

        jLabel95.setText("Como Excluir uma Anamnese?");
        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel87)
                            .addComponent(jLabel88)
                            .addComponent(jLabel89)
                            .addComponent(jLabel90)
                            .addComponent(jLabel92)
                            .addComponent(jLabel93))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel95)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel93)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel11Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 547, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                    .addContainerGap(63, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(361, 361, 361)))
        );

        ModalHelp.getContentPane().add(jPanel11, java.awt.BorderLayout.PAGE_START);

        ModalMeusDados.setResizable(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Nome:");

        txtNome3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("CRP:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("E-mail:");

        txtEmail2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        labeltelefone.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labeltelefone.setText("Telefone:");

        TxtTelefone6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TxtTelefone6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTelefone6KeyTyped(evt);
            }
        });

        labeltelefone2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labeltelefone2.setText("Telefone 2:");

        TxtTelefone7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TxtTelefone7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtTelefone7KeyTyped(evt);
            }
        });

        PainelIdentificacaoPessoal6.setBackground(new java.awt.Color(59, 131, 117));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("DADOS DO PSICÓLOGO");

        javax.swing.GroupLayout PainelIdentificacaoPessoal6Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal6);
        PainelIdentificacaoPessoal6.setLayout(PainelIdentificacaoPessoal6Layout);
        PainelIdentificacaoPessoal6Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        PainelIdentificacaoPessoal6Layout.setVerticalGroup(
            PainelIdentificacaoPessoal6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
        );

        BtnAlterar.setBackground(new java.awt.Color(0, 112, 186));
        BtnAlterar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar.setText("Alterar");
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        LabelEsqueciSenha.setText("Alterar Senha");
        LabelEsqueciSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LabelEsqueciSenhaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                LabelEsqueciSenhaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                LabelEsqueciSenhaMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                LabelEsqueciSenhaMouseReleased(evt);
            }
        });
        LabelEsqueciSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LabelEsqueciSenhaKeyPressed(evt);
            }
        });

        try {
            fcrp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/######")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        fcrp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        fcrp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fcrpActionPerformed(evt);
            }
        });

        BtnCancelar9.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar9.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar9.setText("Cancelar");
        BtnCancelar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ModalMeusDadosLayout = new javax.swing.GroupLayout(ModalMeusDados.getContentPane());
        ModalMeusDados.getContentPane().setLayout(ModalMeusDadosLayout);
        ModalMeusDadosLayout.setHorizontalGroup(
            ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModalMeusDadosLayout.createSequentialGroup()
                .addGroup(ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelIdentificacaoPessoal6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ModalMeusDadosLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(LabelEsqueciSenha)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labeltelefone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labeltelefone2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fcrp, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmail2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtTelefone6, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtTelefone7, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNome3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE))))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModalMeusDadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar9, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        ModalMeusDadosLayout.setVerticalGroup(
            ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModalMeusDadosLayout.createSequentialGroup()
                .addComponent(PainelIdentificacaoPessoal6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fcrp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEmail2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labeltelefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtTelefone6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labeltelefone2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtTelefone7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelEsqueciSenha)
                .addGap(18, 18, 18)
                .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BtnCancelar9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("EXIBIR ANAMNESES");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        PainelMeusDados.setBackground(new java.awt.Color(102, 102, 102));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));

        btnMeusDados.setBackground(new java.awt.Color(102, 102, 102));
        btnMeusDados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/UserIconBranco_66x66.png"))); // NOI18N
        btnMeusDados.setBorder(null);
        btnMeusDados.setContentAreaFilled(false);
        btnMeusDados.setFocusPainted(false);
        btnMeusDados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMeusDadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMeusDadosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMeusDadosMouseExited(evt);
            }
        });
        btnMeusDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMeusDadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelMeusDadosLayout = new javax.swing.GroupLayout(PainelMeusDados);
        PainelMeusDados.setLayout(PainelMeusDadosLayout);
        PainelMeusDadosLayout.setHorizontalGroup(
            PainelMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMeusDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        PainelMeusDadosLayout.setVerticalGroup(
            PainelMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelMeusDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(PainelMeusDadosLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 990, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(PainelMeusDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        btnalterar.setText("Alterar");
        btnalterar.setBackground(new java.awt.Color(204, 204, 204));
        btnalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.setBackground(new java.awt.Color(204, 204, 204));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel1.setText("Exibindo as Anamneses do Paciente ");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        lNome.setText("jLabel6");
        lNome.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        JTAnamneses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Anamnese", "Paciente", "Diagnostico", "Data da Consulta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTAnamneses.getTableHeader().setReorderingAllowed(false);
        JTAnamneses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTAnamnesesMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JTAnamnesesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(JTAnamneses);
        if (JTAnamneses.getColumnModel().getColumnCount() > 0) {
            JTAnamneses.getColumnModel().getColumn(0).setResizable(false);
            JTAnamneses.getColumnModel().getColumn(1).setResizable(false);
            JTAnamneses.getColumnModel().getColumn(2).setResizable(false);
            JTAnamneses.getColumnModel().getColumn(3).setResizable(false);
        }

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

        jLabel14.setText("Buscar:");

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

        BtnVoltarPaciente.setText("Voltar");
        BtnVoltarPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarPacienteActionPerformed(evt);
            }
        });

        BtnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/SimboloHelp.png"))); // NOI18N
        BtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnVoltarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnalterar, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lNome)
                        .addComponent(BtnVoltarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(btnalterar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
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
                .addContainerGap(292, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public boolean readcampos(int cod) {
        Anamnese a;
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
            CheckBoxHumor1.setSelected(a.isQAEHumor());
            CheckBoxDesanimo1.setSelected(a.isQAEDesanimo());

            JCBPsicomotricidade2.setSelectedItem(a.getPsicomotricidade());
            return true;
        }
        return false;
    }

    private void Alterar(int cod) {
        Anamnese a = new Anamnese();
        AnamneseDAO dao = new AnamneseDAO();
        Anamnese a2;
        AnamneseDAO dao2 = new AnamneseDAO();
        a2 = dao2.ReadAnamnese(cod);
        a.setCodAnamnese(a2.getCodAnamnese());
        if (!Validar.vCamposVaziosAnm(null, txtQueixaPrincipal2, DataInicio2)) {
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
                a.setQAEHumor(CheckBoxHumor1.isSelected());
                a.setQAECulpa(CheckBoxCulpa1.isSelected());
                a.setQAERaiva(CheckBoxRaiva1.isSelected());
                a.setQAELuto(CheckBoxLuto1.isSelected());
                a.setQAEDesanimo(CheckBoxDesanimo1.isSelected());
                a.setPsicomotricidade((String) JCBPsicomotricidade2.getSelectedItem());

                boolean sucesso = dao.Update(a);
                if (sucesso) {
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
                    ModalAnamnese2.dispose();
                }
            }
        }
    }
//    private void ReadJTable() {
//        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
//        model.setNumRows(0);
//        ViewsDAO vwdao = new ViewsDAO();
//        //ConsultaDAO cdao = new ConsultaDAO();
//        //PacienteDAO pdao = new PacienteDAO();
//        //Paciente p = new Paciente();
//
//        for (Vw_Anamnese_Paciente v : vwdao.ReadAnamnesePaciente(Main.cod, this.codpaciente)) {
//            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
//            model.addRow(new Object[]{
//                v.getAnamnese().getCodAnamnese(),
//                v.getPaciente().getNome_Completo(),
//                v.getAnamnese().getDiagnostico(),
//                Validar.fDatetime((Timestamp) v.getConsulta().getDataConsulta())
//
//            });
//        }
//    }

    private void ReadJTablePag(int start, int size) {
        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
        model.setNumRows(0);
        ViewsDAO vwdao = new ViewsDAO();
        //ConsultaDAO cdao = new ConsultaDAO();
        //PacienteDAO pdao = new PacienteDAO();
        //Paciente p = new Paciente();

        for (Vw_Anamnese_Paciente v : vwdao.fetchBySizeExibirAnamnesesPaciente(Main.cod, this.codpaciente, start, size)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
                v.getAnamnese().getDiagnostico(),
                Validar.fDatetime((Timestamp) v.getConsulta().getDataConsulta())

            });
        }
    }

//    public void ReadJTableBusca(String Busca) {
//
//        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
//        model.setNumRows(0);
//
//        ViewsDAO vwdao = new ViewsDAO();
//
//        for (Vw_Anamnese_Paciente v : vwdao.BuscaExibirAnamnesesOA(Busca, Main.cod, this.codpaciente)) {
//
//            model.addRow(new Object[]{
//                v.getAnamnese().getCodAnamnese(),
//                v.getPaciente().getNome_Completo(),
//                v.getAnamnese().getDiagnostico(),
//                Validar.fDatetime((Timestamp) v.getConsulta().getDataConsulta())
//
//            });
//        }
//    }
    public void ReadJTableBuscaPag(String Busca, int start, int size) {

        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
        model.setNumRows(0);

        ViewsDAO vwdao = new ViewsDAO();

        for (Vw_Anamnese_Paciente v : vwdao.fetchBySizeExibirAnamnesesPacienteBusca(Main.cod, this.codpaciente, start, size, Busca)) {

            model.addRow(new Object[]{
                v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
                v.getAnamnese().getDiagnostico(),
                Validar.fDatetime((Timestamp) v.getConsulta().getDataConsulta())

            });
        }
    }
    private void BtnSalvarAlteracoes5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalvarAlteracoes5ActionPerformed

    private void BtnCancelar5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCancelar5ActionPerformed

    private void BtnCancelar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar7ActionPerformed
        ModalAnamnese2.dispose();
    }//GEN-LAST:event_BtnCancelar7ActionPerformed

    private void BtnSalvarAlteracoes7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes7ActionPerformed

        Alterar(codigoanamnese);

    }//GEN-LAST:event_BtnSalvarAlteracoes7ActionPerformed

    private void JTAnamnesesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTAnamnesesMouseClicked
        btnalterar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }//GEN-LAST:event_JTAnamnesesMouseClicked

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // TODO add your handling code here:
        if (JTAnamneses.getSelectedRow() != -1) {

            Anamnese a = new Anamnese();
            AnamneseDAO adao = new AnamneseDAO();
            int modelRow = JTAnamneses.convertRowIndexToModel(JTAnamneses.getSelectedRow());
            int value = (Integer) JTAnamneses.getModel().getValueAt(modelRow, 0);
            a.setCodAnamnese(value);
            int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir esta Anamnese?\nA exclusão é permanente e não poderá ser recuperada. Prosseguir?", "Confirmar Exclusão?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (result == 0) {
                boolean sucesso = adao.Delete(a);

           
            }
            

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma anamnese para excluir");
        }
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
    }//GEN-LAST:event_btnExcluirActionPerformed
    
       public void clearAnamnese(){
        txtQueixaPrincipal2.setText("");
        txtQueixaSecundaria2.setText("");
        DataInicio2.clear();
        txtComoComecou2.setText("");
        txtDiagnostico2.setText("");
        txtEncaminhamento2.setText("");
        txtHistoricoFamiliar2.setText("");
        txtDoencasConhecidas2.setText("");
        txtSintomas2.setText("");
        txtMedicamentosUtilizados2.setText("");
        txtOqueMudou2.setText("");
        SubitaOuProgressiva2.setSelectedIndex(0);
        JCBPsicomotricidade2.setSelectedIndex(0);
        CheckBoxVolicao1.setSelected(false);
        CheckBoxAfeto1.setSelected(false);
        CheckBoxHumor1.setSelected(false);
        CheckBoxAnsiedade1.setSelected(false);
        CheckBoxMedo1.setSelected(false);
        CheckBoxCulpa1.setSelected(false);
        CheckBoxRaiva1.setSelected(false);
        CheckBoxLuto1.setSelected(false);
      
        CheckBoxDesanimo1.setSelected(false);
        CheckBoxIntegridadeSensorial1.setSelected(false);
        CheckBoxPercepcao1.setSelected(false);
        CheckBoxAtencao1.setSelected(false);
        CheckBoxMemoria1.setSelected(false);
    }
       
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

            int modelRow = JTAnamneses.convertRowIndexToModel(JTAnamneses.getSelectedRow());
            int value = (Integer) JTAnamneses.getModel().getValueAt(modelRow, 0);
            this.codigoanamnese = value;
            labelnomepaciente.setText((String) JTAnamneses.getModel().getValueAt(modelRow, 1));
            //a2 = dao2.ReadAnamneseConsulta(codconsulta);
            //codanamnese = a2.getCodAnamnese();
            clearAnamnese();
            existe = readcampos(codigoanamnese);

            if (existe) {
                ModalAnamnese2.setSize(1039, 600);
                jScrollPane3.getVerticalScrollBar().setUnitIncrement(15);
                ModalAnamnese2.setModal(true);
                ModalAnamnese2.setResizable(false);
                ModalAnamnese2.setLocationRelativeTo(null);
                ModalAnamnese2.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(this, "Houve um problema ao ler a anamnese selecionada", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma anamnese para alterar", "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnalterarActionPerformed

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    btnalterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        getCountBusca(txtBusca.getText());
        SpinnerNumPaginas.setValue(1);
        LabelQtdePaginas.setText("de " + totalPages);
        getPageDataBusca(1, txtBusca.getText());
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if(totalPages==1){
            BtnAvancarPouco.setEnabled(false);
            BtnAvancarBastante.setEnabled(false);
        }
        else{
            BtnAvancarPouco.setEnabled(true);
            BtnAvancarBastante.setEnabled(true);
        }
                }
    }//GEN-LAST:event_txtBuscaKeyPressed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        btnalterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        getCountBusca(txtBusca.getText());
        SpinnerNumPaginas.setValue(1);
        LabelQtdePaginas.setText("de " + totalPages);
        getPageDataBusca(1, txtBusca.getText());
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if(totalPages==1){
            BtnAvancarPouco.setEnabled(false);
            BtnAvancarBastante.setEnabled(false);
        }
        else{
            BtnAvancarPouco.setEnabled(true);
            BtnAvancarBastante.setEnabled(true);
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void SpinnerLimiteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerLimiteStateChanged
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if (txtBusca.getText() != "") {
            PAGE_SIZE = (int) SpinnerLimite.getValue();
            getCountBusca(txtBusca.getText());
            SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
            SpinnerNumPaginas.setValue((int) currentPage);
            btnalterar.setEnabled(false);
            btnExcluir.setEnabled(false);
            BtnVoltarPouco.setEnabled(false);
            BtnVoltarBastante.setEnabled(false);
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

    private void BtnVoltarBastanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarBastanteActionPerformed
        if (currentPage != 1) {
            if (txtBusca.getText() != "") {
                if (currentPage - 5 < 1) {
                    getPageDataBusca(1, txtBusca.getText());
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);

                } else {
                    getPageDataBusca(currentPage - 5, txtBusca.getText());
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);

                }

            } else {
                if (currentPage - 5 < 1) {
                    getPageData(1);
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);

                } else {
                    getPageData(currentPage - 5);
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);

                }
            }

        }
        SpinnerNumPaginas.setValue((int) currentPage);
        if (currentPage == 1) {
            BtnVoltarPouco.setEnabled(false);
            BtnVoltarBastante.setEnabled(false);
        }
    }//GEN-LAST:event_BtnVoltarBastanteActionPerformed

    private void BtnVoltarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarPoucoActionPerformed
        // TODO add your handling code here:
        if (currentPage != 1) { //diferente da 1 pagina
            if (txtBusca.getText() != "") {
                getPageDataBusca(currentPage - 1, txtBusca.getText());
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);

            } else {
                getPageData(currentPage - 1);
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
            btnalterar.setEnabled(false);
            btnExcluir.setEnabled(false);

        } else {

            getPageData((int) SpinnerNumPaginas.getValue());
        }
        //
    }//GEN-LAST:event_SpinnerNumPaginasStateChanged

    private void BtnAvancarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvancarPoucoActionPerformed
        if (currentPage < totalPages) {
            if (txtBusca.getText() != "") {
                getPageDataBusca(currentPage + 1, txtBusca.getText());
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

    private void JTAnamnesesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTAnamnesesMousePressed
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (JTAnamneses.getSelectedRow() != -1) {

                int modelRow = JTAnamneses.convertRowIndexToModel(JTAnamneses.getSelectedRow());
                int value = (Integer) JTAnamneses.getModel().getValueAt(modelRow, 0);
                this.codigoanamnese = value;
                //a2 = dao2.ReadAnamneseConsulta(codconsulta);
                //codanamnese = a2.getCodAnamnese();
                clearAnamnese();
                existe = readcampos(codigoanamnese);
                labelnomepaciente.setText((String) JTAnamneses.getModel().getValueAt(modelRow, 1));
                if (existe) {
                    ModalAnamnese2.setSize(1039, 600);
                    jScrollPane3.getVerticalScrollBar().setUnitIncrement(15);
                    ModalAnamnese2.setModal(true);
                    ModalAnamnese2.setLocationRelativeTo(null);
                    ModalAnamnese2.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Houve um problema ao ler a anamnese selecionada", "ERRO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma anamnese para alterar");
            }
        }
    }//GEN-LAST:event_JTAnamnesesMousePressed

    private void BtnVoltarPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarPacienteActionPerformed
        ManterPaciente1 mp1 = new ManterPaciente1();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnVoltarPacienteActionPerformed

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        TelaPrincipal mp1 = new TelaPrincipal();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnVoltarActionPerformed

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

    private void chckMostrarSenha1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_chckMostrarSenha1MouseClicked
        if (chckMostrarSenha1.isSelected()) {
            txtSenha1.setEchoChar((char) 0);
            txtConfirmarSenha1.setEchoChar((char) 0);
        } else {
            txtSenha1.setEchoChar('*');
            txtConfirmarSenha1.setEchoChar('*');
        }
    }//GEN-LAST:event_chckMostrarSenha1MouseClicked

    private void BtnAlterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterar1ActionPerformed

        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        if (!Validar.vCamposVaziosSenha(this, txtSenha1, txtConfirmarSenha1)) {
            String senha = new String(txtSenha1.getPassword());
            String csenha = new String(txtConfirmarSenha1.getPassword());
            if (senha.equals(csenha)) {
                p.setSenha(senha);
                p.setCodPsicologo(Main.cod);
                UIManager.put("OptionPane.yesButtonText", "Sim");
                UIManager.put("OptionPane.noButtonText", "Não");

                int result = JOptionPane.showConfirmDialog(this, "Alterar Usuário Senha??", "Confirmar Alteração", JOptionPane.YES_NO_OPTION);

                if (result == 0) {
                    dao.UpdateSenha(p);
                    //JOptionPane.showMessageDialog(this, "Usuário e Senha Atualizado com Sucesso!");
                    //TelaLogin tl = new TelaLogin();
                    //tl.setVisible(true);
                    ModalAlterarSenha.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(this, "As senhas inseridas são diferentes!");

            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAlterar1ActionPerformed

    private void BtnCancelar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar8ActionPerformed
        ModalAlterarSenha.dispose();
    }//GEN-LAST:event_BtnCancelar8ActionPerformed

    private void BtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHelpActionPerformed
        ModalHelp.setSize(700, 300);
        ModalHelp.setModal(true);
        ModalHelp.setVisible(false);
        ModalHelp.setVisible(false);
        ModalHelp.setLocationRelativeTo(null);
        ModalHelp.setVisible(true);
    }//GEN-LAST:event_BtnHelpActionPerformed

    private void txtNome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNome3ActionPerformed

    private void txtNome3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNome3KeyTyped

    }//GEN-LAST:event_txtNome3KeyTyped

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

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        // TODO add your handling code here:
        boolean dadosvalidos = true;

        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        Telefone tf = new Telefone();
        Telefone tf2 = new Telefone();
        TelefoneDAO tfdao = new TelefoneDAO();
        String msg = "Existem campos com formatos Inválidos\n\nFavor Verificar os campos:";
        String crp = "";
        try {
            fcrp.commitEdit();
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ((String) fcrp.getValue() != null) {
            crp = (String) fcrp.getValue();
            crp = crp.replace("/", "");
        }
        if (!Validar.vCamposVaziosManterPSI(this, txtNome3, txtEmail2, crp, TxtTelefone6)) {
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

            if (Validar.vCRP(crp)) {
                p.setCRP(crp);
            } else {
                dadosvalidos = false;
                msg += "\nCRP Invalido: " + crp;
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
                            if (TxtTelefone7.getText().isEmpty()) {
                                tfdao.HardDeleteTelefone(t.get(1));
                            } else {
                                t.get(1).setNumero(TxtTelefone7.getText());
                                tfdao.UpdateTPsicologo(t.get(1));
                            }

                        }
                        if (!TxtTelefone7.getText().isEmpty() && t.size() == 1) {
                            tf2.setPsicologo(p);
                            tf2.setNumero(TxtTelefone7.getText());
                            if (tfdao.CreatePsi(tf2)) {

                            }
                        }

                        //                        JOptionPane.showMessageDialog(this, "Psicologo: " + p.getNome_completo() + " Salvo com sucesso");

                        // this.clear();
                    }
                    jLabel11.setText(p.getNome_completo());
                    String str = getFirstWord(jLabel11.getText());
                    jLabel11.setText(str);
                    ModalMeusDados.dispose();
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
                JOptionPane.showMessageDialog(null, msg, "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_BtnAlterarActionPerformed

    private void LabelEsqueciSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelEsqueciSenhaMouseClicked
        // TODO add your handling code here:
        ModalAlterarSenha.setSize(367, 475);
        ModalAlterarSenha.setModal(true);
        //            readpsicologo();
        ModalAlterarSenha.setLocationRelativeTo(null);
        ModalAlterarSenha.setVisible(true);
    }//GEN-LAST:event_LabelEsqueciSenhaMouseClicked

    private void LabelEsqueciSenhaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelEsqueciSenhaMouseEntered
        LabelEsqueciSenha.setForeground(new java.awt.Color(255, 0, 255));
    }//GEN-LAST:event_LabelEsqueciSenhaMouseEntered

    private void LabelEsqueciSenhaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelEsqueciSenhaMouseExited
        LabelEsqueciSenha.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_LabelEsqueciSenhaMouseExited

    private void LabelEsqueciSenhaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LabelEsqueciSenhaMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_LabelEsqueciSenhaMouseReleased

    private void LabelEsqueciSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LabelEsqueciSenhaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_LabelEsqueciSenhaKeyPressed

    private void fcrpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fcrpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fcrpActionPerformed

    private void BtnCancelar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar9ActionPerformed
        ModalMeusDados.dispose();
    }//GEN-LAST:event_BtnCancelar9ActionPerformed

    private void btnMeusDadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMeusDadosMouseClicked
        ModalMeusDados.setSize(360, 588);
        ModalMeusDados.setModal(true);
        //        TxtTelefone7.setVisible(false);
        //        labeltelefone2.setVisible(false);
        readpsicologo();
        ModalMeusDados.setLocationRelativeTo(null);
        ModalMeusDados.setVisible(true);
    }//GEN-LAST:event_btnMeusDadosMouseClicked

    private void btnMeusDadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMeusDadosMouseEntered
        btnMeusDados.setBorderPainted(true);
        btnMeusDados.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
    }//GEN-LAST:event_btnMeusDadosMouseEntered

    private void btnMeusDadosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMeusDadosMouseExited
        btnMeusDados.setBorderPainted(false);
    }//GEN-LAST:event_btnMeusDadosMouseExited

    private void btnMeusDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMeusDadosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMeusDadosActionPerformed

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
            java.util.logging.Logger.getLogger(ExibirAnamneses2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamneses2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamneses2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamneses2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExibirAnamneses2(codpaciente).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnAlterar1;
    private javax.swing.JButton BtnAvancarBastante;
    private javax.swing.JButton BtnAvancarPouco;
    private javax.swing.JButton BtnCancelar5;
    private javax.swing.JButton BtnCancelar7;
    private javax.swing.JButton BtnCancelar8;
    private javax.swing.JButton BtnCancelar9;
    private javax.swing.JButton BtnExibirAnamneses;
    private javax.swing.JButton BtnExibirAnotacoes;
    private javax.swing.JButton BtnExibirAnotacoes1;
    private javax.swing.JButton BtnHelp;
    private javax.swing.JButton BtnPacientes;
    private javax.swing.JButton BtnSalvarAlteracoes5;
    private javax.swing.JButton BtnSalvarAlteracoes7;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JButton BtnVoltarBastante;
    private javax.swing.JButton BtnVoltarPaciente;
    private javax.swing.JButton BtnVoltarPouco;
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
    private javax.swing.JComboBox<String> JCBPsicomotricidade2;
    private javax.swing.JTable JTAnamneses;
    private javax.swing.JLabel LabelEmail3;
    private javax.swing.JLabel LabelEsqueciSenha;
    private javax.swing.JLabel LabelLimite;
    private javax.swing.JLabel LabelNome5;
    private javax.swing.JLabel LabelNomePaciente;
    private javax.swing.JLabel LabelPagina;
    private javax.swing.JLabel LabelQtdePaginas;
    private javax.swing.JDialog ModalAlterarSenha;
    private javax.swing.JDialog ModalAnamnese2;
    private javax.swing.JDialog ModalHelp;
    private javax.swing.JDialog ModalMeusDados;
    private javax.swing.JPanel PainelDadosPaciente4;
    private javax.swing.JPanel PainelIdentificacaoPessoal4;
    private javax.swing.JPanel PainelIdentificacaoPessoal6;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelMeusDados;
    private javax.swing.JPanel PainelPaginacao;
    private javax.swing.JSpinner SpinnerLimite;
    private javax.swing.JSpinner SpinnerNumPaginas;
    private javax.swing.JComboBox<String> SubitaOuProgressiva2;
    private javax.swing.JTextField TxtTelefone6;
    private javax.swing.JTextField TxtTelefone7;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnMeusDados;
    private javax.swing.JButton btnalterar;
    private javax.swing.JCheckBox chckMostrarSenha1;
    private javax.swing.JFormattedTextField fcrp;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lNome;
    private javax.swing.JLabel labelInicioQueixa4;
    private javax.swing.JLabel labelInicioQueixa5;
    private javax.swing.JLabel labelnomepaciente;
    private javax.swing.JLabel labeltelefone;
    private javax.swing.JLabel labeltelefone2;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtComoComecou2;
    private javax.swing.JPasswordField txtConfirmarSenha1;
    private javax.swing.JTextField txtDiagnostico2;
    private javax.swing.JTextField txtDoencasConhecidas2;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtEncaminhamento2;
    private javax.swing.JTextField txtHistoricoFamiliar2;
    private javax.swing.JTextField txtMedicamentosUtilizados2;
    private javax.swing.JTextField txtNome3;
    private javax.swing.JTextField txtOqueMudou2;
    private javax.swing.JTextField txtQueixaPrincipal2;
    private javax.swing.JTextField txtQueixaSecundaria2;
    private javax.swing.JPasswordField txtSenha1;
    private javax.swing.JTextField txtSintomas2;
    // End of variables declaration//GEN-END:variables
}
