/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import util.Util;
import Validacoes.Validar;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
import static view_adm.MenuPrincipal.VisuPsicologoADM.codpsicologo;

/**
 *
 * @author guimu
 */
public class ExibirAnamneses extends javax.swing.JFrame {

    public boolean existe = false;
    public static int codigoanamnese;

    //Paginacao
    int PAGE_SIZE = 15;
    double tableRowCount;
    int totalPages = 1;
    int currentPage = 1;
    int startRow = 0;
    private int codigopsicologo;

    public void getCount() {
        ViewsDAO dao = new ViewsDAO();
        tableRowCount = dao.getRowCountTableExibirAnamneses(Main.cod);
//        System.out.println("linhas");
//        System.out.println(tableRowCount);
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);

        }
        currentPage = 1;
    }

    public void getCountBusca(String Busca) {
        ViewsDAO dao = new ViewsDAO();

        tableRowCount = dao.getRowCountTableExibirAnamnesesBusca(Main.cod, Busca);
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

    public ExibirAnamneses() {
        this.getCount();
        initComponents();

        ModalAnamnese2.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalHelp.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        ModalMeusDados.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));

        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        p = dao.ReadPsicologo(Main.cod);
        jLabel32.setText(p.getNome_completo());
        String str = getFirstWord(jLabel32.getText());
        jLabel32.setText(str);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        BtnExibirAnamneses.setEnabled(false);
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        btnalterar.setEnabled(false);
        btnExcluir.setEnabled(false);
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTAnamneses.getModel();
        TableColumnModel cmod = JTAnamneses.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTAnamneses.setRowSorter(new TableRowSorter(dtmPacientes));
        this.getPageData(1);
        SpinnerNumPaginas.setValue((int) currentPage);
        LabelQtdePaginas.setText("de " + totalPages);
        SpinnerLimite.setValue((int) PAGE_SIZE);
        //System.out.println(totalPages);

        //ReadJTable();
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

//    private void ReadJTable() {
//        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
//        model.setNumRows(0);
//        ViewsDAO vwdao = new ViewsDAO();
//        //ConsultaDAO cdao = new ConsultaDAO();
//        //PacienteDAO pdao = new PacienteDAO();
//        //Paciente p = new Paciente();
//
//        for (Vw_Anamnese_Paciente v : vwdao.ReadAnamnesePaciente(Main.cod)) {
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

        for (Vw_Anamnese_Paciente v : vwdao.fetchBySizeExibirAnamneses(Main.cod, start, size)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
                v.getAnamnese().getDiagnostico(),
                Validar.fDatetime((Timestamp) v.getConsulta().getDataConsulta())

            });
        }
    }

//    public void ReadJTableBusca( String Busca) {
//
//        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
//        model.setNumRows(0);
//      
//        ViewsDAO vwdao = new ViewsDAO();
//
//        for (Vw_Anamnese_Paciente v : vwdao.BuscaExibirAnamnesesOA( Busca, Main.cod)) {
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

        for (Vw_Anamnese_Paciente v : vwdao.fetchBySizeExibirAnamnesesBusca(Main.cod, start, size, Busca)) {

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
                a.setQAECulpa(CheckBoxCulpa1.isSelected());
                a.setQAERaiva(CheckBoxRaiva1.isSelected());
                a.setQAELuto(CheckBoxLuto1.isSelected());
                a.setQAEHumor(CheckBoxHumor1.isSelected());
                a.setQAEDesanimo(CheckBoxDesanimo1.isSelected());
                a.setPsicomotricidade((String) JCBPsicomotricidade2.getSelectedItem());

                boolean sucesso = dao.Update(a);
                if (sucesso) {
                    //JOptionPane.showMessageDialog(null, "Anamnese Alterada Com Sucesso");
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
        labelnomepaciente = new javax.swing.JLabel();
        LabelNomePaciente = new javax.swing.JLabel();
        ModalHelp = new javax.swing.JDialog();
        jPanel4 = new JPanel();
        jPanel5 = new javax.swing.JPanel();
        ModalMeusDados = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        txtNome3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtCRP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
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
        jLabel1 = new javax.swing.JLabel();
        PainelMeusDados6 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jEImagePanel1 = new LIB.JEImagePanel();
        btnalterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTAnamneses = new javax.swing.JTable();
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

        labelnomepaciente.setText(" Variável Nome do Paciente");
        labelnomepaciente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelNomePaciente.setText("Nome do Paciente :");
        LabelNomePaciente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        ModalMeusDados.setResizable(false);

        jLabel10.setText("Nome:");
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        jLabel12.setText("CRP:");
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        jLabel13.setText("E-mail:");
        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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
                        .addComponent(jLabel12)
                        .addComponent(jLabel13)
                        .addComponent(labeltelefone)
                        .addComponent(labeltelefone2)
                        .addComponent(jLabel10)
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
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(txtNome3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addComponent(txtCRP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EXIBIR ANAMNESES");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        PainelMeusDados6.setBackground(new java.awt.Color(102, 102, 102));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/UserIconBranco90x90.png"))); // NOI18N
        jButton9.setBackground(new java.awt.Color(102, 102, 102));
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PainelMeusDados6Layout = new javax.swing.GroupLayout(PainelMeusDados6);
        PainelMeusDados6.setLayout(PainelMeusDados6Layout);
        PainelMeusDados6Layout.setHorizontalGroup(
            PainelMeusDados6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelMeusDados6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelMeusDados6Layout.setVerticalGroup(
            PainelMeusDados6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelMeusDados6Layout.createSequentialGroup()
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PainelMeusDados6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 996, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        btnalterar.setText("Alterar");
        btnalterar.setBackground(new java.awt.Color(204, 204, 204));
        btnalterar.setToolTipText("Altera os Dados de uma Anamnese Selecionada");
        btnalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.setBackground(new java.awt.Color(204, 204, 204));
        btnExcluir.setToolTipText("Excluir uma Anamnese Selecionada");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        txtBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscaKeyPressed(evt);
            }
        });

        btnBuscar.setText("Buscar");
        btnBuscar.setToolTipText("Realiza a busca de um Paciente");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel14.setText("Buscar:");

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
        jScrollPane2.setViewportView(JTAnamneses);
        if (JTAnamneses.getColumnModel().getColumnCount() > 0) {
            JTAnamneses.getColumnModel().getColumn(0).setResizable(false);
            JTAnamneses.getColumnModel().getColumn(1).setResizable(false);
            JTAnamneses.getColumnModel().getColumn(2).setResizable(false);
            JTAnamneses.getColumnModel().getColumn(3).setResizable(false);
        }

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

        BtnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/SimboloHelp.png"))); // NOI18N
        BtnHelp.setToolTipText("Ajuda");
        BtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createSequentialGroup()
                .addGap(111, 111, 111)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addComponent(btnalterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(BtnHelp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel14))))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BtnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(btnalterar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
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
                .addContainerGap(283, Short.MAX_VALUE))
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
    }//GEN-LAST:event_btnalterarActionPerformed

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

                if (!sucesso) {
                    JOptionPane.showMessageDialog(null, "Falha ao apagar a Anamnese, tente novamente!", "ERRO", JOptionPane.ERROR_MESSAGE);
                    //JOptionPane.showMessageDialog(this, "Anamnese Apagada com Sucesso");

                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma anamnese para excluir", "ERRO", JOptionPane.ERROR_MESSAGE);
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

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        //System.out.println(JCBAtributo.getSelectedIndex());
        getCountBusca(txtBusca.getText());
        SpinnerNumPaginas.setValue(1);
        LabelQtdePaginas.setText("de " + totalPages);
        getPageDataBusca(1, txtBusca.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscaKeyPressed
//        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
//            this.ReadJTableBusca(txtBusca.getText());
//        }
    }//GEN-LAST:event_txtBuscaKeyPressed

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

    private void JTAnamnesesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTAnamnesesMousePressed
        if (evt.getClickCount() == 2) {
            if (JTAnamneses.getSelectedRow() != -1) {
                Anamnese a2 = new Anamnese();
                AnamneseDAO dao2 = new AnamneseDAO();
                int modelRow = JTAnamneses.convertRowIndexToModel(JTAnamneses.getSelectedRow());
                int value = (Integer) JTAnamneses.getModel().getValueAt(modelRow, 0);
                this.codigoanamnese = value;
                labelnomepaciente.setText((String) JTAnamneses.getModel().getValueAt(modelRow, 1));
                //a2 = dao2.ReadAnamneseConsulta(codconsulta);
                //codanamnese = a2.getCodAnamnese();
                existe = readcampos(codigoanamnese);
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
                JOptionPane.showMessageDialog(this, "Selecione uma anamnese para alterar", "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_JTAnamnesesMousePressed

    private void JTAnamnesesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTAnamnesesMouseClicked
        btnalterar.setEnabled(true);
        btnExcluir.setEnabled(true);
    }//GEN-LAST:event_JTAnamnesesMouseClicked

    private void SpinnerLimiteStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerLimiteStateChanged
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if (txtBusca.getText() != "") {
            PAGE_SIZE = (int) SpinnerLimite.getValue();
            getCountBusca(txtBusca.getText());
            SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
            SpinnerNumPaginas.setValue((int) currentPage);

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
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);

                } else {
                    getPageDataBusca(currentPage - 5, txtBusca.getText());
                    btnalterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
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
                    btnalterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
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
                btnalterar.setEnabled(false);
                btnExcluir.setEnabled(false);
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
                btnalterar.setEnabled(false);
                btnExcluir.setEnabled(false);
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
                    btnalterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
                    BtnVoltarPouco.setEnabled(true);
                    BtnVoltarBastante.setEnabled(true);
                    if (currentPage == totalPages) {
                        BtnAvancarPouco.setEnabled(false);
                        BtnAvancarBastante.setEnabled(false);
                    }
                } else {
                    getPageDataBusca(currentPage + 5, txtBusca.getText());
                    btnalterar.setEnabled(false);
                    btnExcluir.setEnabled(false);
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

    private void BtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHelpActionPerformed
        ModalHelp.setSize(540, 620);
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
                        jLabel32.setText(p.getNome_completo());
                        String str = getFirstWord(jLabel32.getText());
                        jLabel32.setText(str);
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

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked
        ModalMeusDados.setSize(540, 620);
        ModalMeusDados.setModal(true);
        TxtTelefone7.setVisible(false);
        labeltelefone2.setVisible(false);
        readpsicologo();
        ModalMeusDados.setLocationRelativeTo(null);
        ModalMeusDados.setVisible(true);
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

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
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnAlterar1;
    private javax.swing.JButton BtnAvancarBastante;
    private javax.swing.JButton BtnAvancarPouco;
    private javax.swing.JButton BtnCancelar5;
    private javax.swing.JButton BtnCancelar7;
    private javax.swing.JButton BtnCancelar8;
    private javax.swing.JButton BtnExibirAnamneses;
    private javax.swing.JButton BtnExibirAnotacoes;
    private javax.swing.JButton BtnExibirAnotacoes1;
    private javax.swing.JButton BtnHelp;
    private javax.swing.JButton BtnPacientes;
    private javax.swing.JButton BtnSalvarAlteracoes5;
    private javax.swing.JButton BtnSalvarAlteracoes7;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JButton BtnVoltarBastante;
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
    private javax.swing.JPanel PainelMeusDados6;
    private javax.swing.JPanel PainelPaginacao;
    private javax.swing.JSpinner SpinnerLimite;
    private javax.swing.JSpinner SpinnerNumPaginas;
    private javax.swing.JComboBox<String> SubitaOuProgressiva2;
    private javax.swing.JTextField TxtTelefone6;
    private javax.swing.JTextField TxtTelefone7;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnalterar;
    private javax.swing.JCheckBox chckMostrarSenha1;
    private javax.swing.JButton jButton9;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel35;
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labelInicioQueixa4;
    private javax.swing.JLabel labelInicioQueixa5;
    private javax.swing.JLabel labelnomepaciente;
    private javax.swing.JLabel labeltelefone;
    private javax.swing.JLabel labeltelefone2;
    private javax.swing.JTextField txtBusca;
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
    // End of variables declaration//GEN-END:variables
}
