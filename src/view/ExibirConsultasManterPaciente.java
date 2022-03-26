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
import java.awt.Toolkit;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.bean.Anamnese;
import model.bean.Anotacao;
import model.bean.Consulta;
import model.bean.Paciente;
import model.bean.Psicologo;
import model.bean.Telefone;
import model.bean.Vw_Consultas;
import model.dao.AnamneseDAO;
import model.dao.AnotacaoDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.PsicologoDAO;
import model.dao.TelefoneDAO;
import model.dao.ViewsDAO;
import util.Util;
import static view.ManterPsicologo.codpsicologo;
import static view.TelaPrincipal.codconsulta;

/**
 *
 * @author guimu
 */
public class ExibirConsultasManterPaciente extends javax.swing.JFrame {

    LocalDate dfim;
    LocalDate dinicio;
    public static int codpaciente;
    private int codigoconsulta = -1;
    public static int codconsulta;
    public static boolean existe;

    //Paginacao
    int PAGE_SIZE = 15;
    double tableRowCount;
    int totalPages = 1;
    int currentPage = 1;
    int startRow = 0;
    private int codigopsicologo;

    public void getCount() {
        ViewsDAO dao = new ViewsDAO();
        tableRowCount = dao.getRowCountTableExibirConsultasPaciente(Main.cod, this.codpaciente, dinicio, dfim);
//        System.out.println("linhas");
//        System.out.println(tableRowCount);
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

    /**
     * Creates new form ExibirConsultasManterPaciente
     */
    public ExibirConsultasManterPaciente(int cod) {
        LocalDate agora = LocalDate.now();
        java.util.Date date1 = java.util.Date.from(agora.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - 6));
        dinicio = cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        cal2.setTime(date1);
        cal2.set(Calendar.MONTH, (cal2.get(Calendar.MONTH) + 6));
        dfim = cal2.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        this.codpaciente = cod;
        this.getCount();
        initComponents();

        DataFim.setDate(cal2.getTime());
        DataInicio.setDate(cal.getTime());

        ModalAlterarConsulta.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalMeusDados.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalAnotacao.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalAnamnese3.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));

        Psicologo psi = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        psi = dao.ReadPsicologo(Main.cod);
        jLabel11.setText(psi.getNome_completo());
        String str = getFirstWord(jLabel11.getText());
        jLabel11.setText(str);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        BtnPacientes.setEnabled(false);
        btnalterarconsulta.setEnabled(false);
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
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

    public void clearAnotacao() {
        txtAssunto.setText(null);
        txtTexto.setText(null);
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

        for (Vw_Consultas v : vw.fetchBySizeExibirConsultasPaciente(codpaciente, Main.cod, start, size, dinicio, dfim)) {

            model.addRow(new Object[]{
                v.getCodConsulta(),
                Validar.fDatetime((Timestamp) v.getDataConsulta()),
                v.getStatus(),
                v.getPagamento()});
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

        ModalAnamnese3 = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        PainelDadosPaciente5 = new javax.swing.JPanel();
        PainelIdentificacaoPessoal5 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        BtnSalvarAlteracoes6 = new javax.swing.JButton();
        BtnCancelar6 = new javax.swing.JButton();
        jLabel39 = new javax.swing.JLabel();
        SubitaOuProgressiva2 = new javax.swing.JComboBox<>();
        labelInicioQueixa4 = new javax.swing.JLabel();
        JCBPsicomotricidade2 = new javax.swing.JComboBox<>();
        labelInicioQueixa5 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        LabelEmail3 = new javax.swing.JLabel();
        txtComoComecou2 = new javax.swing.JTextField();
        LabelNome6 = new javax.swing.JLabel();
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
        jEImagePanel5 = new LIB.JEImagePanel();
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
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        LabelNomePaciente1 = new javax.swing.JLabel();
        labelnomeanamnese = new javax.swing.JLabel();
        ModalAnotacao = new javax.swing.JDialog();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jEImagePanel4 = new LIB.JEImagePanel();
        PainelIdentificacaoPessoal3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        LabelNomePaciente = new javax.swing.JLabel();
        labelnomeanotacao = new javax.swing.JLabel();
        LabelAssunto = new javax.swing.JLabel();
        txtAssunto = new javax.swing.JTextField();
        LabelAssunto1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        BtnCancelarAnotacao = new javax.swing.JButton();
        BtnSalvarAlteracoesAnotacao = new javax.swing.JButton();
        ModalMeusDados = new javax.swing.JDialog();
        jLabel3 = new javax.swing.JLabel();
        txtNome3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCRP = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEmail2 = new javax.swing.JTextField();
        labeltelefone = new javax.swing.JLabel();
        TxtTelefone6 = new javax.swing.JTextField();
        labeltelefone2 = new javax.swing.JLabel();
        TxtTelefone7 = new javax.swing.JTextField();
        PainelIdentificacaoPessoal6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        BtnAlterar = new javax.swing.JButton();
        LabelEsqueciSenha = new javax.swing.JLabel();
        ModalAlterarConsulta = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        datepicker = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        status = new javax.swing.JComboBox<>();
        BtnAlterarConsulta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        JCBPagamento = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        labelpaciente = new javax.swing.JLabel();
        jPanel2 = new JPanel();
        jLabel4 = new javax.swing.JLabel();
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
        jPanel1 = new JPanel();
        PainelMeusDados = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
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
        BtnVoltarPaciente = new javax.swing.JButton();
        DataInicio = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        DataFim = new com.toedter.calendar.JDateChooser();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnPacientes = new javax.swing.JButton();
        BtnExibirAnamneses = new javax.swing.JButton();
        BtnExibirAnotacoes = new javax.swing.JButton();
        BtnExibirAnotacoes1 = new javax.swing.JButton();

        ModalAnamnese3.setResizable(false);

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        PainelDadosPaciente5.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente5.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal5.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal5Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal5);
        PainelIdentificacaoPessoal5.setLayout(PainelIdentificacaoPessoal5Layout);
        PainelIdentificacaoPessoal5Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal5Layout.setVerticalGroup(
            PainelIdentificacaoPessoal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel18.setText("* Campos Obrigatórios");
        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes6.setText("Salvar Alterações");
        BtnSalvarAlteracoes6.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes6.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes6ActionPerformed(evt);
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

        LabelNome6.setText("Queixa Principal:");
        LabelNome6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        jEImagePanel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/simboloma.png"))); // NOI18N

        javax.swing.GroupLayout jEImagePanel5Layout = new javax.swing.GroupLayout(jEImagePanel5);
        jEImagePanel5.setLayout(jEImagePanel5Layout);
        jEImagePanel5Layout.setHorizontalGroup(
            jEImagePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jEImagePanel5Layout.setVerticalGroup(
            jEImagePanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        jLabel29.setText("*");
        jLabel29.setBackground(new java.awt.Color(255, 0, 0));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 0));

        jLabel30.setText("*");
        jLabel30.setBackground(new java.awt.Color(255, 0, 0));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 0, 0));

        LabelNomePaciente1.setText("Nome do Paciente :");
        LabelNomePaciente1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelnomeanamnese.setText(" Variável Nome do Paciente");
        labelnomeanamnese.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelDadosPaciente5Layout = new javax.swing.GroupLayout(PainelDadosPaciente5);
        PainelDadosPaciente5.setLayout(PainelDadosPaciente5Layout);
        PainelDadosPaciente5Layout.setHorizontalGroup(
            PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar6)
                .addGap(42, 42, 42)
                .addComponent(BtnSalvarAlteracoes6, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                .addComponent(jEImagePanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel18))
                    .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel22))
                                .addGap(19, 19, 19)
                                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                        .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LabelNome6)
                                            .addComponent(jLabel57))
                                        .addGap(34, 34, 34)
                                        .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                                .addComponent(DataInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 393, Short.MAX_VALUE))
                                            .addComponent(txtQueixaPrincipal2)))
                                    .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                        .addComponent(LabelNomePaciente1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(labelnomeanamnese))))
                            .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                    .addComponent(jLabel30)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel20)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(BtnCancelar7)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnSalvarAlteracoes7))
                                .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                    .addGap(19, 19, 19)
                                    .addComponent(jLabel28))
                                .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                    .addGap(21, 21, 21)
                                    .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
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
                                        .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                            .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel54)
                                                .addComponent(jLabel55)
                                                .addComponent(jLabel53))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtComoComecou2)
                                                .addComponent(txtDiagnostico2)
                                                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtSintomas2)
                                                    .addComponent(txtMedicamentosUtilizados2)
                                                    .addComponent(txtOqueMudou2)
                                                    .addComponent(txtDoencasConhecidas2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(txtEncaminhamento2)
                                                .addComponent(txtHistoricoFamiliar2)))
                                        .addComponent(labelInicioQueixa5)
                                        .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(LabelEmail3)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtQueixaSecundaria2))
                                        .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
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
                                        .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                                            .addComponent(jLabel39)
                                            .addGap(32, 32, 32)
                                            .addComponent(SubitaOuProgressiva2, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(35, 35, 35)
                                            .addComponent(labelInicioQueixa4)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(JCBPsicomotricidade2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelDadosPaciente5Layout.setVerticalGroup(
            PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEImagePanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNomePaciente1)
                    .addComponent(labelnomeanamnese))
                .addGap(38, 38, 38)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQueixaPrincipal2)
                    .addComponent(LabelNome6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel22))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jLabel57)
                    .addComponent(DataInicio2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmail3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQueixaSecundaria2))
                .addGap(18, 18, 18)
                .addComponent(labelInicioQueixa5)
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CheckBoxIntegridadeSensorial1)
                    .addComponent(CheckBoxPercepcao1)
                    .addComponent(CheckBoxAtencao1)
                    .addComponent(CheckBoxMemoria1))
                .addGap(19, 19, 19)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(SubitaOuProgressiva2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInicioQueixa4)
                    .addComponent(JCBPsicomotricidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(txtComoComecou2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel50)
                    .addComponent(txtDiagnostico2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(txtEncaminhamento2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel51)
                    .addComponent(txtHistoricoFamiliar2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(txtDoencasConhecidas2))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel53)
                    .addComponent(txtSintomas2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMedicamentosUtilizados2)
                    .addComponent(jLabel54))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOqueMudou2)
                    .addComponent(jLabel55))
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnCancelar7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnSalvarAlteracoes7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(PainelDadosPaciente5Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jLabel30))))
                .addGap(371, 371, 371)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDadosPaciente5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoes6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 917, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelDadosPaciente5, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1081, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addComponent(PainelDadosPaciente5, javax.swing.GroupLayout.PREFERRED_SIZE, 1081, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jScrollPane3.setViewportView(jPanel6);

        javax.swing.GroupLayout ModalAnamnese3Layout = new javax.swing.GroupLayout(ModalAnamnese3.getContentPane());
        ModalAnamnese3.getContentPane().setLayout(ModalAnamnese3Layout);
        ModalAnamnese3Layout.setHorizontalGroup(
            ModalAnamnese3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
        );
        ModalAnamnese3Layout.setVerticalGroup(
            ModalAnamnese3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1081, Short.MAX_VALUE)
        );

        ModalAnotacao.setResizable(false);

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

        PainelIdentificacaoPessoal3.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal3Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal3);
        PainelIdentificacaoPessoal3.setLayout(PainelIdentificacaoPessoal3Layout);
        PainelIdentificacaoPessoal3Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal3Layout.setVerticalGroup(
            PainelIdentificacaoPessoal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel19.setText("Anotações da Consulta");
        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(59, 131, 117));

        LabelNomePaciente.setText("Nome do Paciente :");
        LabelNomePaciente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelnomeanotacao.setText(" Variável Nome do Paciente");
        labelnomeanotacao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelAssunto.setText("Assunto:");
        LabelAssunto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtAssunto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelAssunto1.setText("Texto:");
        LabelAssunto1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtTexto.setColumns(20);
        txtTexto.setRows(5);
        txtTexto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jScrollPane2.setViewportView(txtTexto);

        BtnCancelarAnotacao.setText("Cancelar");
        BtnCancelarAnotacao.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelarAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelarAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelarAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarAnotacaoActionPerformed(evt);
            }
        });

        BtnSalvarAlteracoesAnotacao.setText("Salvar Alterações");
        BtnSalvarAlteracoesAnotacao.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoesAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoesAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoesAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoesAnotacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelNomePaciente)
                                    .addComponent(LabelAssunto))
                                .addGap(18, 18, 18)
                                .addComponent(labelnomeanotacao))
                            .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel7Layout.createSequentialGroup()
                                    .addComponent(BtnCancelarAnotacao)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnSalvarAlteracoesAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelAssunto1)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addComponent(PainelIdentificacaoPessoal3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNomePaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelnomeanotacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelAssunto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelAssunto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnCancelarAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoesAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane4.setViewportView(jPanel7);

        javax.swing.GroupLayout ModalAnotacaoLayout = new javax.swing.GroupLayout(ModalAnotacao.getContentPane());
        ModalAnotacao.getContentPane().setLayout(ModalAnotacaoLayout);
        ModalAnotacaoLayout.setHorizontalGroup(
            ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        ModalAnotacaoLayout.setVerticalGroup(
            ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING)
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

        jLabel6.setText("CRP:");
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        jLabel8.setText("E-mail:");
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        PainelIdentificacaoPessoal6.setBackground(new java.awt.Color(59, 131, 117));

        jLabel15.setText("DADOS DO PSICÓLOGO");
        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));

        jLabel35.setText("NOME");
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PainelIdentificacaoPessoal6Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal6);
        PainelIdentificacaoPessoal6.setLayout(PainelIdentificacaoPessoal6Layout);
        PainelIdentificacaoPessoal6Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelIdentificacaoPessoal6Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel35)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        PainelIdentificacaoPessoal6Layout.setVerticalGroup(
            PainelIdentificacaoPessoal6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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

        javax.swing.GroupLayout ModalMeusDadosLayout = new javax.swing.GroupLayout(ModalMeusDados.getContentPane());
        ModalMeusDados.getContentPane().setLayout(ModalMeusDadosLayout);
        ModalMeusDadosLayout.setHorizontalGroup(
            ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ModalMeusDadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
            .addGroup(ModalMeusDadosLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LabelEsqueciSenha)
                    .addGroup(ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel6)
                        .addComponent(jLabel8)
                        .addComponent(labeltelefone)
                        .addComponent(labeltelefone2)
                        .addComponent(jLabel3)
                        .addComponent(TxtTelefone6, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                        .addComponent(TxtTelefone7)
                        .addComponent(txtEmail2)
                        .addComponent(txtCRP)
                        .addComponent(txtNome3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ModalMeusDadosLayout.setVerticalGroup(
            ModalMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModalMeusDadosLayout.createSequentialGroup()
                .addComponent(PainelIdentificacaoPessoal6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtNome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(txtCRP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
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
                .addGap(18, 18, 18)
                .addComponent(LabelEsqueciSenha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        ModalAlterarConsulta.setResizable(false);

        jLabel5.setText("Data");

        jLabel9.setText("Status da Consulta:");

        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A Confirmar","Confirmada","Cancelada", "Realizada" }));

        BtnAlterarConsulta.setText("Salvar Alterações");
        BtnAlterarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarConsultaActionPerformed(evt);
            }
        });

        jButton1.setText("Editar Anamnese");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Nova Anotação");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Situação do Pagamento:");

        JCBPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pendente", "Efetuado"}));

        jLabel12.setText("Paciente:");

        labelpaciente.setText("jLabel10");

        jPanel2.setBackground(new java.awt.Color(59, 131, 117));
        jPanel2.setForeground(new java.awt.Color(59, 131, 117));
        jPanel2.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Alterar Consulta");
        jLabel4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelpaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(datepicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JCBPagamento, 0, 154, Short.MAX_VALUE)
                                    .addComponent(status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BtnAlterarConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(labelpaciente))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(datepicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(BtnAlterarConsulta))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(JCBPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        ModalAlterarConsulta.getContentPane().add(jPanel3, java.awt.BorderLayout.CENTER);

        ModalAlterarSenha.setResizable(false);

        jPanel8.setBackground(new java.awt.Color(59, 131, 117));
        jPanel8.setForeground(new java.awt.Color(59, 131, 117));
        jPanel8.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel43.setText("Alterar Senha");

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

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel44.setText("Nova Senha");

        txtSenha1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel45.setText("Confirme a Nova Senha");

        txtConfirmarSenha1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        chckMostrarSenha1.setText("MostrarSenha");
        chckMostrarSenha1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                chckMostrarSenha1MouseClicked(evt);
            }
        });

        BtnAlterar1.setBackground(new java.awt.Color(0, 112, 186));
        BtnAlterar1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnAlterar1.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar1.setText("Alterar");
        BtnAlterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterar1ActionPerformed(evt);
            }
        });

        BtnCancelar8.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar8.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar8.setText("Cancelar");
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

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("EXIBIR CONSULTAS");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1082, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
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
                {null, null, null, null}
            },
            new String [] {
                "CodConsulta", "Horário", "Status", "Pagamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTConsultas.getTableHeader().setReorderingAllowed(false);
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

        DataInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DataInicioPropertyChange(evt);
            }
        });

        jLabel7.setText("Mostrar Consultas de");

        jLabel13.setText("até");

        DataFim.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DataFimPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(BtnVoltarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lNome))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 716, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(DataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnalterarconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lNome)
                    .addComponent(BtnVoltarPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jEImagePanel1Layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addComponent(DataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel13)))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(DataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(14, 14, 14)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(btnalterarconsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
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
                .addContainerGap(327, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnalterarconsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnalterarconsultaActionPerformed
        // TODO add your handling code here:
        if (JTConsultas.getSelectedRow() != -1) {
            int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
            int value = (Integer) JTConsultas.getModel().getValueAt(modelRow, 0);
            codconsulta = value;
            readatributos();

            ModalAlterarConsulta.setSize(520, 305);
            ModalAlterarConsulta.setModal(true);
            ModalAlterarConsulta.setResizable(false);
            ModalAlterarConsulta.setLocationRelativeTo(null);
            ModalAlterarConsulta.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para alterar");
        }
        BtnVoltarBastante.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if(totalPages==1){
            BtnAvancarBastante.setEnabled(false);
            BtnAvancarBastante.setEnabled(false);
        }
        else{
            BtnAvancarBastante.setEnabled(true);
            BtnAvancarBastante.setEnabled(true);
        }
    }//GEN-LAST:event_btnalterarconsultaActionPerformed
    public void readatributos() {
        Consulta c = new Consulta();
        ConsultaDAO cdao = new ConsultaDAO();
        PacienteDAO dao = new PacienteDAO();
        c = cdao.ReadConsulta(codconsulta);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String string;
        string = dateFormat.format(c.getDataConsulta());
        //System.out.println(string);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(string, formatter);
        //System.out.println(Validar.fDatetime(c.getDataConsulta()));
        datepicker.setDateTimePermissive(dateTime);

        status.setSelectedItem(c.getStatus());
        JCBPagamento.setSelectedItem(c.getPagamento());
        Paciente p = dao.ReadPaciente(c.getPaciente().getCodPaciente());
        labelpaciente.setText(p.getNome_Completo());
        labelnomeanotacao.setText(p.getNome_Completo());
        labelnomeanamnese.setText(p.getNome_Completo());
    }
    private void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed
        // TODO add your handling code here:
        if (JTConsultas.getSelectedRow() != -1) {

            Consulta c = new Consulta();

            int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
            int value = (Integer) JTConsultas.getModel().getValueAt(modelRow, 0);
            int result = JOptionPane.showConfirmDialog(null, "Você tem certeza que deseja excluir esta Consulta?\nA exclusão é permanente e não poderá ser recuperada. Prosseguir?", "Confirmar Exclusão?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (result == 0) {
                c.setCodConsulta(value);
                boolean sucesso = Deletar.DConsulta(c);

                if (!sucesso) {
                    JOptionPane.showMessageDialog(null, "Falha ao apagar a Consulta, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);

                }
                getCount();
                SpinnerNumPaginas.setValue(currentPage);
                LabelQtdePaginas.setText("de " + totalPages);
                getPageData(currentPage);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma consulta para apagar", "ERRO", JOptionPane.ERROR_MESSAGE);
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
    
    private void JTConsultasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTConsultasMousePressed
        btnalterarconsulta.setEnabled(true);
        BtnExcluir.setEnabled(true);
        if (evt.getClickCount() == 2) {
            if (JTConsultas.getSelectedRow() != -1) {
                int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
                int value = (Integer) JTConsultas.getModel().getValueAt(modelRow, 0);
                codconsulta = value;
                readatributos();

                ModalAlterarConsulta.setSize(520, 305);
                ModalAlterarConsulta.setModal(true);
                ModalAlterarConsulta.setLocationRelativeTo(null);
                ModalAlterarConsulta.setVisible(true);

            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma consulta para alterar");
            }
        }
    }//GEN-LAST:event_JTConsultasMousePressed

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
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
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
//        }
    }//GEN-LAST:event_SpinnerLimiteStateChanged

    private void BtnVoltarBastanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarBastanteActionPerformed
        if (currentPage != 1) {

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
        SpinnerNumPaginas.setValue((int) currentPage);
        if (currentPage == 1) {
            BtnVoltarPouco.setEnabled(false);
            BtnVoltarBastante.setEnabled(false);
        }
    }//GEN-LAST:event_BtnVoltarBastanteActionPerformed

    private void BtnVoltarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarPoucoActionPerformed
        // TODO add your handling code here:
        if (currentPage != 1) { //diferente da 1 pagina

            getPageData(currentPage - 1);
            BtnAvancarPouco.setEnabled(true);
            BtnAvancarBastante.setEnabled(true);

        }
        SpinnerNumPaginas.setValue((int) currentPage);
        if (currentPage == 1) {
            BtnVoltarPouco.setEnabled(false);
            BtnVoltarBastante.setEnabled(false);
        }
    }//GEN-LAST:event_BtnVoltarPoucoActionPerformed

    private void SpinnerNumPaginasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerNumPaginasStateChanged
        // TODO add your handling code here:
        int pag1 = currentPage;
        getPageData((int) SpinnerNumPaginas.getValue());
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
        btnalterarconsulta.setEnabled(false);
        BtnExcluir.setEnabled(false);
    }//GEN-LAST:event_SpinnerNumPaginasStateChanged

    private void BtnAvancarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvancarPoucoActionPerformed
        if (currentPage < totalPages) {

            getPageData(currentPage + 1);
            BtnVoltarPouco.setEnabled(true);
            BtnVoltarBastante.setEnabled(true);
            if (currentPage == totalPages) {
                BtnAvancarPouco.setEnabled(false);
                BtnAvancarBastante.setEnabled(false);
            }

        }
        SpinnerNumPaginas.setValue((int) currentPage);
    }//GEN-LAST:event_BtnAvancarPoucoActionPerformed

    private void BtnAvancarBastanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvancarBastanteActionPerformed
        // TODO add your handling code here:
        if (currentPage < totalPages) { //se tem pagina e é menor que a ultima

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
        SpinnerNumPaginas.setValue((int) currentPage);
    }//GEN-LAST:event_BtnAvancarBastanteActionPerformed
    public boolean readcampos() {
        Anamnese a = new Anamnese();
        AnamneseDAO dao = new AnamneseDAO();
        a = dao.ReadAnamneseConsulta(codconsulta);
        int codanamnese = a.getCodAnamnese();
        if (codanamnese != 0) {

            txtQueixaPrincipal2.setText(a.getQueixaPrincipal());
            DataInicio2.setText((String) Validar.fDataNascBD((java.sql.Date) a.getInicioDaQueixa()));
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
            CheckBoxHumor1.setSelected(a.isQAEHumor());
            JCBPsicomotricidade2.setSelectedItem(a.getPsicomotricidade());
            return true;
        }
        return false;
    }
    private void BtnSalvarAlteracoes6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalvarAlteracoes6ActionPerformed

    private void BtnCancelar6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCancelar6ActionPerformed

    private void BtnCancelar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar7ActionPerformed
        ModalAnamnese3.dispose();
    }//GEN-LAST:event_BtnCancelar7ActionPerformed

    private void BtnSalvarAlteracoes7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes7ActionPerformed

        if (existe) {
            Alterar();
            //            LabelModalAnamnese.setText("Lendo dados da Anamnese Cadastrada na consulta");
        } else {
            Cadastrar();
            //            LabelModalAnamnese.setText(" Cadastrar anamnese na consulta");
        }
    }//GEN-LAST:event_BtnSalvarAlteracoes7ActionPerformed
    private void Cadastrar() {
        Anamnese a = new Anamnese();
        AnamneseDAO dao = new AnamneseDAO();
        a = dao.ReadAnamneseConsulta(codconsulta);
        int codanamnese = a.getCodAnamnese();
        if (!Validar.vCamposVaziosAnm(this, txtQueixaPrincipal2, DataInicio2)) {
            if (codanamnese == 0) {

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
                a.getConsulta().setCodConsulta(codconsulta);
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
                boolean sucesso = dao.Create(a);
                if (sucesso) {
                    //JOptionPane.showMessageDialog(ModalAnamnese3, "Anamnese Inserida Com Sucesso");
                    a = dao.ReadAnamneseConsulta(codconsulta);
                    codanamnese = a.getCodAnamnese();
                    ModalAnamnese3.dispose();

                }
            }

        }

    }

    private void Alterar() {
        Anamnese a = new Anamnese();
        AnamneseDAO dao = new AnamneseDAO();
        Anamnese a2 = new Anamnese();
        AnamneseDAO dao2 = new AnamneseDAO();
        a2 = dao2.ReadAnamneseConsulta(codconsulta);
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
                a.getConsulta().setCodConsulta(codconsulta);
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
                    //JOptionPane.showMessageDialog(this, "Anamnese Alterada Com Sucesso");
                    ModalAnamnese3.dispose();
                }
            }
        }
    }
    private void BtnCancelarAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarAnotacaoActionPerformed
        clearAnotacao();
        ModalAnotacao.dispose();
    }//GEN-LAST:event_BtnCancelarAnotacaoActionPerformed

    private void BtnSalvarAlteracoesAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoesAnotacaoActionPerformed
        AnotacaoDAO dao = new AnotacaoDAO();
        Anotacao a = new Anotacao();
        if (!Validar.vCamposVaziosAnt(null, txtAssunto, txtTexto)) {
            a.setAssunto(txtAssunto.getText());
            a.setTexto(txtTexto.getText());

            a.getConsulta().setCodConsulta(codconsulta);

            boolean sucesso = dao.Create(a);
            clearAnotacao();
            ModalAnotacao.dispose();
        }
    }//GEN-LAST:event_BtnSalvarAlteracoesAnotacaoActionPerformed

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

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        ModalMeusDados.setSize(540, 620);
        ModalMeusDados.setModal(true);
        TxtTelefone7.setVisible(false);
        labeltelefone2.setVisible(false);
        readpsicologo();
        ModalMeusDados.setLocationRelativeTo(null);
        ModalMeusDados.setVisible(true);
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtNome3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNome3ActionPerformed

    private void txtNome3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNome3KeyTyped
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtNome3KeyTyped

    private void txtCRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCRPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCRPActionPerformed

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
                        ModalMeusDados.dispose();
                        jLabel11.setText(p.getNome_completo());
                        String str = getFirstWord(jLabel11.getText());
                        jLabel11.setText(str);
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

    private void BtnAlterarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarConsultaActionPerformed
        if (datepicker.getDateTimeStrict() == null) {

            JOptionPane.showMessageDialog(null, "Por favor Insira Data e Horário Válidos", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {
            Consulta c = new Consulta();
            ConsultaDAO cdao = new ConsultaDAO();

            boolean sucesso = false;
            c.setCodConsulta(codconsulta);
            c.setDataConsulta(datepicker.getDateTimePermissive());
            c.setPagamento((String) JCBPagamento.getSelectedItem());
            c.setStatus((String) status.getSelectedItem());
            sucesso = cdao.Update(c);
            if (sucesso) {
                this.getCount();
                SpinnerNumPaginas.setValue(currentPage);
                LabelQtdePaginas.setText("de " + totalPages);
                this.getPageData(1);
                ModalAlterarConsulta.dispose();
                //JOptionPane.showMessageDialog(this, "Consulta Salva com sucesso");
            }

        }

        // TODO add your handling code here:
    }//GEN-LAST:event_BtnAlterarConsultaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Anamnese a2 = new Anamnese();
        AnamneseDAO dao2 = new AnamneseDAO();
        //a2 = dao2.ReadAnamneseConsulta(codconsulta);
        //codanamnese = a2.getCodAnamnese();
        existe = readcampos();
        if (existe) {

        } else {

        }

        ModalAnamnese3.setSize(1039, 600);
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(15);
        ModalAnamnese3.setModal(true);
        ModalAnamnese3.setResizable(false);
        ModalAnamnese3.setLocationRelativeTo(null);
        clearAnamnese();
        ModalAnamnese3.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        CadastrarAnotacaoPaciente tp = new CadastrarAnotacaoPaciente(codconsulta);
        CadastrarAnotacaoPaciente.codconsulta = codconsulta;
        ModalAnotacao.setSize(890, 600);
        jScrollPane4.getVerticalScrollBar().setUnitIncrement(15);
        ModalAnotacao.setModal(true);
        ModalAnotacao.setResizable(false);
        ModalAnotacao.setLocationRelativeTo(null);
        ModalAnotacao.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void DataInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DataInicioPropertyChange
        // TODO add your handling code here:
        if (DataInicio.getDate() != null) {
            java.util.Date date = DataInicio.getDate();
            dinicio = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            BtnVoltarPouco.setEnabled(false);
            BtnVoltarBastante.setEnabled(false);

            //PAGE_SIZE = (int) SpinnerLimite.getValue();
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
    }//GEN-LAST:event_DataInicioPropertyChange

    private void DataFimPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DataFimPropertyChange
        // TODO add your handling code here:
        if (DataFim.getDate() != null) {
            java.util.Date date = DataFim.getDate();
            dfim = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            BtnVoltarPouco.setEnabled(false);
            BtnVoltarBastante.setEnabled(false);

            //PAGE_SIZE = (int) SpinnerLimite.getValue();
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
    }//GEN-LAST:event_DataFimPropertyChange

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
                p.setCodPsicologo(this.codigopsicologo);
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
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnAlterar1;
    private javax.swing.JButton BtnAlterarConsulta;
    private javax.swing.JButton BtnAvancarBastante;
    private javax.swing.JButton BtnAvancarPouco;
    private javax.swing.JButton BtnCancelar6;
    private javax.swing.JButton BtnCancelar7;
    private javax.swing.JButton BtnCancelar8;
    private javax.swing.JButton BtnCancelarAnotacao;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnExibirAnamneses;
    private javax.swing.JButton BtnExibirAnotacoes;
    private javax.swing.JButton BtnExibirAnotacoes1;
    private javax.swing.JButton BtnPacientes;
    private javax.swing.JButton BtnSalvarAlteracoes6;
    private javax.swing.JButton BtnSalvarAlteracoes7;
    private javax.swing.JButton BtnSalvarAlteracoesAnotacao;
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
    private com.toedter.calendar.JDateChooser DataFim;
    private com.toedter.calendar.JDateChooser DataInicio;
    private com.github.lgooddatepicker.components.DatePicker DataInicio2;
    private javax.swing.JComboBox<String> JCBPagamento;
    private javax.swing.JComboBox<String> JCBPsicomotricidade2;
    private javax.swing.JTable JTConsultas;
    private javax.swing.JLabel LabelAssunto;
    private javax.swing.JLabel LabelAssunto1;
    private javax.swing.JLabel LabelEmail3;
    private javax.swing.JLabel LabelEsqueciSenha;
    private javax.swing.JLabel LabelLimite;
    private javax.swing.JLabel LabelNome6;
    private javax.swing.JLabel LabelNomePaciente;
    private javax.swing.JLabel LabelNomePaciente1;
    private javax.swing.JLabel LabelPagina;
    private javax.swing.JLabel LabelQtdePaginas;
    private javax.swing.JDialog ModalAlterarConsulta;
    private javax.swing.JDialog ModalAlterarSenha;
    private javax.swing.JDialog ModalAnamnese3;
    private javax.swing.JDialog ModalAnotacao;
    private javax.swing.JDialog ModalMeusDados;
    private javax.swing.JPanel PainelDadosPaciente5;
    private javax.swing.JPanel PainelIdentificacaoPessoal3;
    private javax.swing.JPanel PainelIdentificacaoPessoal5;
    private javax.swing.JPanel PainelIdentificacaoPessoal6;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelMeusDados;
    private javax.swing.JPanel PainelPaginacao;
    private javax.swing.JSpinner SpinnerLimite;
    private javax.swing.JSpinner SpinnerNumPaginas;
    private javax.swing.JComboBox<String> SubitaOuProgressiva2;
    private javax.swing.JTextField TxtTelefone6;
    private javax.swing.JTextField TxtTelefone7;
    private javax.swing.JButton btnalterarconsulta;
    private javax.swing.JCheckBox chckMostrarSenha1;
    private com.github.lgooddatepicker.components.DateTimePicker datepicker;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel4;
    private LIB.JEImagePanel jEImagePanel5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JLabel lNome;
    private javax.swing.JLabel labelInicioQueixa4;
    private javax.swing.JLabel labelInicioQueixa5;
    private javax.swing.JLabel labelnomeanamnese;
    private javax.swing.JLabel labelnomeanotacao;
    private javax.swing.JLabel labelpaciente;
    private javax.swing.JLabel labeltelefone;
    private javax.swing.JLabel labeltelefone2;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JTextField txtAssunto;
    private javax.swing.JTextField txtCRP;
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
    private javax.swing.JTextArea txtTexto;
    // End of variables declaration//GEN-END:variables
}
