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
import java.sql.Timestamp;
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
import model.bean.Anotacao;
import model.bean.Paciente;
import model.bean.Telefone;
import model.bean.Vw_Anotacoes_Paciente;
import model.bean.Vw_TelefonesPacientes;
import model.dao.AnotacaoDAO;
import model.dao.PacienteDAO;
import model.dao.TelefoneDAO;
import model.dao.ViewsDAO;
import util.Util;
import Validacoes.Validar;
import java.awt.Toolkit;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.bean.Anotacao;
import model.bean.Consulta;
import model.bean.Psicologo;
import model.bean.Vw_Anamnese_Paciente;
import model.bean.Vw_Anotacoes_Paciente;
import model.dao.AnotacaoDAO;
import model.dao.ConsultaDAO;
import model.dao.PsicologoDAO;
import model.dao.ViewsDAO;
import static view_adm.MenuPrincipal.VisuPsicologoADM.codpsicologo;

/**
 *
 * @author guimu
 */
public class ExibirAnotacoes extends javax.swing.JFrame {

    public static int codigoanotacao;
    public boolean existe;

    //Paginacao
    int PAGE_SIZE = 5;
    double tableRowCount;
    int totalPages = 1;
    int currentPage = 1;
    int startRow = 0;

    public void getCount() {
        ViewsDAO dao = new ViewsDAO();
        tableRowCount = dao.getRowCountTableExibirAnotacoes(Main.cod);
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);
          

        }
          currentPage = 1;
    }

    public void getCountBusca(String Busca) {
        ViewsDAO dao = new ViewsDAO();

        tableRowCount = dao.getRowCountTableExibirAnotacoesBusca(Main.cod,Busca);
        //System.out.println(tableRowCount);
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);
            currentPage = 1;

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

    public ExibirAnotacoes() {
        this.getCount();
        initComponents();
        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        p = dao.ReadPsicologo(Main.cod);
        jLabel11.setText(p.getNome_completo());
        String str= getFirstWord(jLabel11.getText());
        jLabel11.setText(str);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));
        BtnExibirAnotacoes.setEnabled(false);
        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        BtnAlterarAnotacao.setEnabled(false);
        BtnExcluirAnotacao.setEnabled(false);
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTAnotacoes.getModel();
        TableColumnModel cmod = JTAnotacoes.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTAnotacoes.setRowSorter(new TableRowSorter(dtmPacientes));
        this.getPageData(1);
        SpinnerNumPaginas.setValue((int) currentPage);
        LabelQtdePaginas.setText("de " + totalPages);
        SpinnerLimite.setValue((int) PAGE_SIZE);
        System.out.println(totalPages);
    }
// public void ReadJTable() {
//        DefaultTableModel model = (DefaultTableModel) JTAnotacoes.getModel();
//        model.setNumRows(0);
//        ViewsDAO vwdao = new ViewsDAO();
//        //ConsultaDAO cdao = new ConsultaDAO();
//        //PacienteDAO pdao = new PacienteDAO();
//        //Paciente p = new Paciente();
//
//        for (Vw_Anotacoes_Paciente v : vwdao.ReadAnotacoesPaciente(Main.cod)) {
//            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
//            model.addRow(new Object[]{
//                v.getAnotacao().getCodAnotacao(),
//                v.getPaciente().getNome_Completo(),
//                v.getAnotacao().getAssunto(),
//                Validar.fDatetime((Timestamp)  v.getAnotacao().getDataAnotacao())
//
//            });
//        }
//    }
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


    public void ReadJTablePag(int start, int size) {
        DefaultTableModel model = (DefaultTableModel) JTAnotacoes.getModel();
        model.setNumRows(0);
        ViewsDAO vwdao = new ViewsDAO();
        //ConsultaDAO cdao = new ConsultaDAO();
        //PacienteDAO pdao = new PacienteDAO();
        //Paciente p = new Paciente();

        for (Vw_Anotacoes_Paciente v : vwdao.fetchBySizeExibirAnotacoes(Main.cod, start, size)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                v.getAnotacao().getCodAnotacao(),
                v.getPaciente().getNome_Completo(),
                v.getAnotacao().getAssunto(),
                Validar.fDatetime((Timestamp) v.getAnotacao().getDataAnotacao())

            });
        }
    }

    public void ReadJTableBuscaPag(String Busca, int start, int size) {

        DefaultTableModel model = (DefaultTableModel) JTAnotacoes.getModel();
        model.setNumRows(0);
        ViewsDAO vwdao = new ViewsDAO();

        for (Vw_Anotacoes_Paciente v : vwdao.fetchBySizeExibirAnotacoesBusca(Main.cod, start, size, Busca)) {

            model.addRow(new Object[]{
                v.getAnotacao().getCodAnotacao(),
                v.getPaciente().getNome_Completo(),
                v.getAnotacao().getAssunto(),
                Validar.fDatetime((Timestamp) v.getAnotacao().getDataAnotacao())

            });
        }
    }

    public boolean readcampos(int cod) {
        Anotacao a = new Anotacao();
        AnotacaoDAO dao = new AnotacaoDAO();
        a = dao.ReadAnotacao(cod);
        int codanotacao = a.getCodAnotacao();
        if (codanotacao != 0) {
            txtAssunto.setText(a.getAssunto());
            txtTexto.setText(a.getTexto());
            return true;
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ModalAnotacao = new javax.swing.JDialog();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jEImagePanel4 = new LIB.JEImagePanel();
        PainelIdentificacaoPessoal3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        LabelNomePaciente = new javax.swing.JLabel();
        LabelNome5 = new javax.swing.JLabel();
        LabelAssunto = new javax.swing.JLabel();
        txtAssunto = new javax.swing.JTextField();
        LabelAssunto1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtTexto = new javax.swing.JTextArea();
        BtnCancelarAnotacao = new javax.swing.JButton();
        BtnSalvarAlteracoesAnotacao = new javax.swing.JButton();
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
        jPanel1 = new JPanel();
        PainelMeusDados = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jEImagePanel1 = new LIB.JEImagePanel();
        BtnAlterarAnotacao = new javax.swing.JButton();
        BtnExcluirAnotacao = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAnotacoes = new javax.swing.JTable();
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
        btnBuscar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnPacientes = new javax.swing.JButton();
        BtnExibirAnamneses = new javax.swing.JButton();
        BtnExibirAnotacoes = new javax.swing.JButton();
        BtnExibirAnotacoes1 = new javax.swing.JButton();

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

        jLabel7.setText("Anotações da Consulta");
        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(59, 131, 117));

        LabelNomePaciente.setText("Nome do Paciente :");
        LabelNomePaciente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelNome5.setText(" Variável Nome do Paciente");
        LabelNome5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelAssunto.setText("Assunto:");
        LabelAssunto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtAssunto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelAssunto1.setText("Texto:");
        LabelAssunto1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtTexto.setColumns(20);
        txtTexto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTexto.setLineWrap(true);
        txtTexto.setRows(5);
        txtTexto.setWrapStyleWord(true);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelNomePaciente)
                                    .addComponent(LabelAssunto))
                                .addGap(18, 18, 18)
                                .addComponent(LabelNome5))
                            .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(BtnCancelarAnotacao)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnSalvarAlteracoesAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelAssunto1)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addComponent(PainelIdentificacaoPessoal3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNomePaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LabelNome5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LabelAssunto)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LabelAssunto1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnCancelarAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoesAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel2);

        javax.swing.GroupLayout ModalAnotacaoLayout = new javax.swing.GroupLayout(ModalAnotacao.getContentPane());
        ModalAnotacao.getContentPane().setLayout(ModalAnotacaoLayout);
        ModalAnotacaoLayout.setHorizontalGroup(
            ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        ModalAnotacaoLayout.setVerticalGroup(
            ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

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

        BtnAlterar.setBackground(new java.awt.Color(59, 131, 117));
        BtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar.setText("Alterar");
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
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
                    .addComponent(txtNome3))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        PainelMeusDados.setBackground(new java.awt.Color(102, 102, 102));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/UserIconBranco90x90.png"))); // NOI18N
        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
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
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        PainelMeusDadosLayout.setVerticalGroup(
            PainelMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelMeusDadosLayout.createSequentialGroup()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(PainelMeusDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("EXIBIR ANOTAÇÕES");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1061, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        BtnAlterarAnotacao.setText("Alterar");
        BtnAlterarAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarAnotacaoActionPerformed(evt);
            }
        });

        BtnExcluirAnotacao.setText("Excluir");
        BtnExcluirAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirAnotacaoActionPerformed(evt);
            }
        });

        JTAnotacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Anotação", "Paciente", "Assunto", "Data da Anotação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTAnotacoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JTAnotacoesMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(JTAnotacoes);

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

        BtnHelp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/SimboloHelp.png"))); // NOI18N
        BtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHelpActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createSequentialGroup()
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE)
                            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnExcluirAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAlterarAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(BtnAlterarAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BtnExcluirAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        getContentPane().add(jEImagePanel1, java.awt.BorderLayout.CENTER);

        PainelMenu.setBackground(new java.awt.Color(102, 102, 102));
        PainelMenu.setForeground(new java.awt.Color(102, 102, 102));

        BtnVoltar.setBackground(new java.awt.Color(102, 102, 102));
        BtnVoltar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        BtnVoltar.setText("Início");
        BtnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarActionPerformed(evt);
            }
        });

        BtnPacientes.setBackground(new java.awt.Color(102, 102, 102));
        BtnPacientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnPacientes.setForeground(new java.awt.Color(255, 255, 255));
        BtnPacientes.setText("Pacientes");
        BtnPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPacientesActionPerformed(evt);
            }
        });

        BtnExibirAnamneses.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnamneses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnamneses.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnamneses.setText("Exibir Anamneses");
        BtnExibirAnamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirAnamnesesActionPerformed(evt);
            }
        });

        BtnExibirAnotacoes.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnotacoes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnotacoes.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnotacoes.setText("Exibir Anotações");
        BtnExibirAnotacoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirAnotacoesActionPerformed(evt);
            }
        });

        BtnExibirAnotacoes1.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnotacoes1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnotacoes1.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnotacoes1.setText("Sair");
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
                .addContainerGap(276, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnCancelarAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelarAnotacaoActionPerformed
        ModalAnotacao.dispose();
    }//GEN-LAST:event_BtnCancelarAnotacaoActionPerformed

    private void BtnSalvarAlteracoesAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoesAnotacaoActionPerformed
        AnotacaoDAO dao = new AnotacaoDAO();
        Anotacao a = new Anotacao();
        if (!Validar.vCamposVaziosAnt(this, txtAssunto, txtTexto)) {
            a.setAssunto(txtAssunto.getText());
            a.setTexto(txtTexto.getText());

            a.setCodAnotacao(codigoanotacao);
            boolean sucesso = dao.Update(a);
            if (sucesso) {
                JOptionPane.showMessageDialog(null, "Anotação Atualizada  com Sucesso!");
            }
        }
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
    }//GEN-LAST:event_BtnSalvarAlteracoesAnotacaoActionPerformed

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

    private void BtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHelpActionPerformed
        ModalHelp.setSize(540, 620);
        ModalHelp.setModal(true);
        ModalHelp.setVisible(false);
        ModalHelp.setVisible(false);
        ModalHelp.setLocationRelativeTo(null);
        ModalHelp.setVisible(true);
    }//GEN-LAST:event_BtnHelpActionPerformed

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
                    BtnAlterarAnotacao.setEnabled(false);
                    BtnExcluirAnotacao.setEnabled(false);
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
                        BtnAlterarAnotacao.setEnabled(false);
                        BtnExcluirAnotacao.setEnabled(false);
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

    private void BtnAvancarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAvancarPoucoActionPerformed
        if (currentPage < totalPages) {
            if (txtBusca.getText() != "") {
                getPageDataBusca(currentPage + 1, txtBusca.getText());
                BtnAlterarAnotacao.setEnabled(false);
                BtnExcluirAnotacao.setEnabled(false);
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

    private void SpinnerNumPaginasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_SpinnerNumPaginasStateChanged
        // TODO add your handling code here:
        if (txtBusca.getText() != "") {
            int pag1 = currentPage;
            getPageDataBusca((int) SpinnerNumPaginas.getValue(), txtBusca.getText());
            int pag2 = currentPage;
            if(pag2<pag1){
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);
                    if (currentPage == 1) {
                        BtnVoltarPouco.setEnabled(false);
                        BtnVoltarBastante.setEnabled(false);  
                }
                    }
                    else{
                    {
                        BtnVoltarPouco.setEnabled(true);
                        BtnVoltarBastante.setEnabled(true);
                        if (currentPage == totalPages) {
                            BtnAvancarPouco.setEnabled(false);
                            BtnAvancarBastante.setEnabled(false);  
                }
                    }       
            }
             BtnAlterarAnotacao.setEnabled(false);
             BtnExcluirAnotacao.setEnabled(false);

        } else {

            getPageData((int) SpinnerNumPaginas.getValue());
        }
        //
    }//GEN-LAST:event_SpinnerNumPaginasStateChanged

    private void BtnVoltarPoucoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarPoucoActionPerformed
        // TODO add your handling code here:
        if (currentPage != 1) { //diferente da 1 pagina
            if (txtBusca.getText() != "") {
                getPageDataBusca(currentPage - 1, txtBusca.getText());
                 BtnAlterarAnotacao.setEnabled(false);
                 BtnExcluirAnotacao.setEnabled(false);
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

    private void BtnVoltarBastanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarBastanteActionPerformed
        if (currentPage != 1) {
            if (txtBusca.getText() != "") {
                if (currentPage - 5 < 1) {
                    getPageDataBusca(1, txtBusca.getText());
                    BtnAvancarPouco.setEnabled(true);
                    BtnAvancarBastante.setEnabled(true);
                    
                    
                } else {
                    getPageDataBusca(currentPage - 5, txtBusca.getText());
                     BtnAlterarAnotacao.setEnabled(false);
                     BtnExcluirAnotacao.setEnabled(false);
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
                    BtnAlterarAnotacao.setEnabled(false);
                    BtnExcluirAnotacao.setEnabled(false);
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
             if(totalPages==1){
            BtnAvancarPouco.setEnabled(false);
            BtnAvancarBastante.setEnabled(false);
            
        }
             else{
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
             if(totalPages==1){
            BtnAvancarPouco.setEnabled(false);
            BtnAvancarBastante.setEnabled(false);
            
        }
             else{
                 BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);
             }
        }
    }//GEN-LAST:event_SpinnerLimiteStateChanged

    private void JTAnotacoesMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTAnotacoesMousePressed
        BtnAlterarAnotacao.setEnabled(true);
        BtnExcluirAnotacao.setEnabled(true);
        if (evt.getClickCount() == 2) {
            if (JTAnotacoes.getSelectedRow() != -1) {
                Anotacao a = new Anotacao();
                AnotacaoDAO dao = new AnotacaoDAO();
                int modelRow = JTAnotacoes.convertRowIndexToModel(JTAnotacoes.getSelectedRow());
                int value = (Integer) JTAnotacoes.getModel().getValueAt(modelRow, 0);
                this.codigoanotacao = value;
                String nome = (String) JTAnotacoes.getModel().getValueAt(modelRow, 1);
                //a2 = dao2.ReadAnamneseConsulta(codconsulta);
                //codanamnese = a2.getCodAnamnese();
                existe = readcampos(codigoanotacao);
                ModalAnotacao.setSize(862, 870);
                LabelNome5.setText(nome);
                ModalAnotacao.setModal(true);
                ModalAnotacao.setLocationRelativeTo(null);
                ModalAnotacao.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma anotacao para alterar");
            }

        }
    }//GEN-LAST:event_JTAnotacoesMousePressed

    private void BtnExcluirAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirAnotacaoActionPerformed
        // TODO add your handling code here:
        if (JTAnotacoes.getSelectedRow() != -1) {

            Anotacao a = new Anotacao();
            AnotacaoDAO dao = new AnotacaoDAO();
            int modelRow = JTAnotacoes.convertRowIndexToModel(JTAnotacoes.getSelectedRow());
            int value = (Integer) JTAnotacoes.getModel().getValueAt(modelRow, 0);
            a.setCodAnotacao(value);
            dao.Delete(a);
            //limpar a tela

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
            JOptionPane.showMessageDialog(null, "Selecione um paciente para excluir");
        }
    }//GEN-LAST:event_BtnExcluirAnotacaoActionPerformed

    private void BtnAlterarAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarAnotacaoActionPerformed
        // TODO add your handling code here:
        //      if (JTAnotacoes.getSelectedRow() != -1) {
            //             int modelRow = JTAnotacoes.convertRowIndexToModel(JTAnotacoes.getSelectedRow());
            //            int value = (Integer)JTAnotacoes.getModel().getValueAt(modelRow,0);
            //            AlterarAnotacaoPacienteMenu.codanotacao = value;
            //            AlterarAnotacaoPacienteMenu cp = new AlterarAnotacaoPacienteMenu();
            //            cp.setVisible(true);
            //            this.dispose();
            //
            //        } else {
            //            JOptionPane.showMessageDialog(this, "Selecione uma anotacao para alterar");
            //        }
        if (JTAnotacoes.getSelectedRow() != -1) {
            Anotacao a = new Anotacao();
            AnotacaoDAO dao = new AnotacaoDAO();
            int modelRow = JTAnotacoes.convertRowIndexToModel(JTAnotacoes.getSelectedRow());
            int value = (Integer) JTAnotacoes.getModel().getValueAt(modelRow, 0);
            this.codigoanotacao = value;
            String nome = (String) JTAnotacoes.getModel().getValueAt(modelRow, 1);
            //a2 = dao2.ReadAnamneseConsulta(codconsulta);
            //codanamnese = a2.getCodAnamnese();
            existe = readcampos(codigoanotacao);
            ModalAnotacao.setSize(890, 600);
            jScrollPane3.getVerticalScrollBar().setUnitIncrement(15);
            LabelNome5.setText(nome);
            ModalAnotacao.setModal(true);
            ModalAnotacao.setLocationRelativeTo(null);
            ModalAnotacao.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma anotacao para alterar");
        }
    }//GEN-LAST:event_BtnAlterarAnotacaoActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        ModalMeusDados.setSize(540, 620);
        ModalMeusDados.setModal(true);
        TxtTelefone7.setVisible(false);
        labeltelefone2.setVisible(false);
        readpsicologo();
        ModalMeusDados.setLocationRelativeTo(null);
        ModalMeusDados.setVisible(true);
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

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
         if (TxtTelefone6.getText().length()==11) {
            evt.consume();
        }
    }//GEN-LAST:event_TxtTelefone6KeyTyped

    private void TxtTelefone7KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTelefone7KeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
         if (TxtTelefone7.getText().length()==11) {
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
                        String str= getFirstWord(jLabel11.getText());
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
            java.util.logging.Logger.getLogger(ExibirAnotacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExibirAnotacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExibirAnotacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExibirAnotacoes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExibirAnotacoes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnAlterarAnotacao;
    private javax.swing.JButton BtnAvancarBastante;
    private javax.swing.JButton BtnAvancarPouco;
    private javax.swing.JButton BtnCancelarAnotacao;
    private javax.swing.JButton BtnExcluirAnotacao;
    private javax.swing.JButton BtnExibirAnamneses;
    private javax.swing.JButton BtnExibirAnotacoes;
    private javax.swing.JButton BtnExibirAnotacoes1;
    private javax.swing.JButton BtnHelp;
    private javax.swing.JButton BtnPacientes;
    private javax.swing.JButton BtnSalvarAlteracoesAnotacao;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JButton BtnVoltarBastante;
    private javax.swing.JButton BtnVoltarPouco;
    private javax.swing.JTable JTAnotacoes;
    private javax.swing.JLabel LabelAssunto;
    private javax.swing.JLabel LabelAssunto1;
    private javax.swing.JLabel LabelLimite;
    private javax.swing.JLabel LabelNome5;
    private javax.swing.JLabel LabelNomePaciente;
    private javax.swing.JLabel LabelPagina;
    private javax.swing.JLabel LabelQtdePaginas;
    private javax.swing.JDialog ModalAnotacao;
    private javax.swing.JDialog ModalHelp;
    private javax.swing.JDialog ModalMeusDados;
    private javax.swing.JPanel PainelIdentificacaoPessoal3;
    private javax.swing.JPanel PainelIdentificacaoPessoal6;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelMeusDados;
    private javax.swing.JPanel PainelPaginacao;
    private javax.swing.JSpinner SpinnerLimite;
    private javax.swing.JSpinner SpinnerNumPaginas;
    private javax.swing.JTextField TxtTelefone6;
    private javax.swing.JTextField TxtTelefone7;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton3;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel labeltelefone;
    private javax.swing.JLabel labeltelefone2;
    private javax.swing.JTextField txtAssunto;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtCRP;
    private javax.swing.JTextField txtEmail2;
    private javax.swing.JTextField txtNome3;
    private javax.swing.JTextArea txtTexto;
    // End of variables declaration//GEN-END:variables
}
