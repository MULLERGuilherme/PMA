/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_adm.MenuPrincipal;

import view.*;
import Validacoes.Deletar;
import Validacoes.Validar;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.bean.Paciente;
import model.bean.Telefone;
import model.bean.Vw_Consultas;
import model.bean.Vw_TelefonesPacientes;
import model.dao.ADMDAO;
import model.dao.PacienteDAO;
import model.dao.TelefoneDAO;
import model.dao.ViewsDAO;
import util.Util;

/**
 *
 * @author guimu
 */
public class ManterConsultasAdm extends javax.swing.JFrame {
    LocalDate dfim;
    LocalDate dinicio;
    //Paginacao
    int PAGE_SIZE = 5;
    double tableRowCount;
    int totalPages = 1;
    int currentPage = 1;
    int startRow = 0;

    public void getCount() {
        ADMDAO dao = new ADMDAO();
        boolean deletada;
        if (JCBdeletadas.getSelectedIndex() == 0) {
            deletada = false;
        } else {
            deletada = true;
        }
        tableRowCount = dao.getRowCountConsultasADM(deletada, dinicio, dfim);
        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);

        }
        currentPage = 1;

    }

    public void getCountInit() {
        ADMDAO dao = new ADMDAO();

        tableRowCount = dao.getRowCountConsultasADM(false, dinicio, dfim);

        if (tableRowCount > 0) {
            totalPages = (int) Math.ceil(tableRowCount / PAGE_SIZE);

        }
        currentPage = 1;

    }

    public void getCountBusca(String Busca) {
        ADMDAO dao = new ADMDAO();
        boolean deletada;
        if (JCBdeletadas.getSelectedIndex() == 0) {
            deletada = false;
        } else {
            deletada = true;
        }
        tableRowCount = dao.getRowCountTableConsultasADMBusca(Busca, deletada, dinicio, dfim);
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

        ReadJTablePagBusca(startRow, PAGE_SIZE, Busca);

    }

    public ManterConsultasAdm() {
        
        LocalDate agora = LocalDate.now();
        java.util.Date date1 = java.util.Date.from(agora.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(Calendar.MONTH, (cal.get(Calendar.MONTH) - 6));
        dinicio = cal.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        cal2.setTime(date1);
        cal2.set(Calendar.MONTH, (cal2.get(Calendar.MONTH) +6));
        dfim = cal2.getTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        this.getCountInit();
        initComponents();

        //System.out.println(localDate);  
        DataFim.setDate(cal2.getTime());

        DataInicio.setDate(cal.getTime());

        BtnVoltarPouco.setEnabled(false);
        BtnVoltarBastante.setEnabled(false);
        if (totalPages == 1) {
            BtnAvancarPouco.setEnabled(false);
            BtnAvancarBastante.setEnabled(false);
        }
        BtnManterConsulta.setEnabled(false);
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));

        DefaultTableModel dtmConsultas = (DefaultTableModel) JTConsultas.getModel();
        TableColumnModel cmod = JTConsultas.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTConsultas.setRowSorter(new TableRowSorter(dtmConsultas));

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

        ModalHelp = new javax.swing.JDialog();
        jPanel4 = new JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new JPanel();
        PainelMeusDados = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jEImagePanel1 = new LIB.JEImagePanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTConsultas = new javax.swing.JTable();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
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
        JCBdeletadas = new javax.swing.JComboBox<>();
        DataInicio = new com.toedter.calendar.JDateChooser();
        DataFim = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        PainelMenu = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        BtnManterPaciente = new javax.swing.JButton();
        BtnManterConsulta = new javax.swing.JButton();
        BtnManterPsicologo = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        PainelMeusDados.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Administrador");
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout PainelMeusDadosLayout = new javax.swing.GroupLayout(PainelMeusDados);
        PainelMeusDados.setLayout(PainelMeusDadosLayout);
        PainelMeusDadosLayout.setHorizontalGroup(
            PainelMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelMeusDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );
        PainelMeusDadosLayout.setVerticalGroup(
            PainelMeusDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMeusDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CONSULTAS");
        jLabel4.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 981, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PainelMeusDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        jLabel6.setText("Buscar");

        JTConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Paciente", "Psicologo", "Data da Consulta", "Status", "Pagamento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTConsultasMouseClicked(evt);
            }
        });
        JTConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTConsultasKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(JTConsultas);

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
        BtnHelp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHelpActionPerformed(evt);
            }
        });

        JCBdeletadas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Exibindo Ativos", "Exibindo Deletados" }));
        JCBdeletadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBdeletadasActionPerformed(evt);
            }
        });

        DataInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DataInicioPropertyChange(evt);
            }
        });

        DataFim.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DataFimPropertyChange(evt);
            }
        });

        jLabel3.setText("até");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Mostrar Consultas de");

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addGap(111, 111, 111)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jEImagePanel1Layout.createSequentialGroup()
                                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jEImagePanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel2)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(DataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(DataFim, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jEImagePanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtBusca)))
                                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jEImagePanel1Layout.createSequentialGroup()
                                            .addGap(199, 199, 199)
                                            .addComponent(jButton1))
                                        .addGroup(jEImagePanel1Layout.createSequentialGroup()
                                            .addGap(27, 27, 27)
                                            .addComponent(JCBdeletadas, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 82, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(BtnHelp, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jEImagePanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(DataFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(DataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCBdeletadas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)))
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelPaginacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        getContentPane().add(jEImagePanel1, java.awt.BorderLayout.CENTER);

        PainelMenu.setBackground(new java.awt.Color(102, 102, 102));
        PainelMenu.setForeground(new java.awt.Color(102, 102, 102));

        BtnInicio.setText("Início");
        BtnInicio.setBackground(new java.awt.Color(102, 102, 102));
        BtnInicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnInicio.setForeground(new java.awt.Color(255, 255, 255));
        BtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioActionPerformed(evt);
            }
        });

        BtnManterPaciente.setText("Pacientes");
        BtnManterPaciente.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPaciente.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPacienteActionPerformed(evt);
            }
        });

        BtnManterConsulta.setText("Consultas");
        BtnManterConsulta.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterConsulta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterConsulta.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterConsultaActionPerformed(evt);
            }
        });

        BtnManterPsicologo.setText("Psicólogos");
        BtnManterPsicologo.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPsicologo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPsicologo.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPsicologoActionPerformed(evt);
            }
        });

        BtnSair.setText("Sair");
        BtnSair.setBackground(new java.awt.Color(102, 102, 102));
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
            .addComponent(BtnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnManterPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnManterConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnSair, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );
        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addComponent(BtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 323, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

//    public void ReadJTable() {
//
//        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();
//
//        model.setNumRows(0);
//        ViewsDAO vwdao = new ViewsDAO();
//
//        for (Vw_Consultas c : vwdao.ReadALLConsultas()) {
//            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
//            model.addRow(new Object[]{
//                c.getCodConsulta(),
//                c.getPaciente().getNome_Completo(),
//                c.getPsicologo().getNome_completo(),
//                Validar.fDatetime((Timestamp) c.getDataConsulta()),
//                c.getStatus(),});
//        }
//    }
//    public void ReadJTable() {
//
//        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();
//
//        model.setNumRows(0);
//        ViewsDAO vwdao = new ViewsDAO();
//
//        for (Vw_Consultas c : vwdao.ReadALLConsultas()) {
//            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
//            model.addRow(new Object[]{
//                c.getCodConsulta(),
//                c.getPaciente().getNome_Completo(),
//                c.getPsicologo().getNome_completo(),
//                Validar.fDatetime((Timestamp) c.getDataConsulta()),
//                c.getStatus(),});
//        }
//    }
    public void ReadJTablePag(int start, int size) {

        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();
        boolean deletada;
        if (JCBdeletadas.getSelectedIndex() == 0) {
            deletada = false;
        } else {
            deletada = true;
        }
        model.setNumRows(0);
        ADMDAO vwdao = new ADMDAO();

        for (Vw_Consultas c : vwdao.fetchBySizeConsultasADM(start, size, deletada, dinicio, dfim)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                c.getCodConsulta(),
                c.getPaciente().getNome_Completo(),
                c.getPsicologo().getNome_completo(),
                Validar.fDatetime((Timestamp) c.getDataConsulta()),
                c.getStatus(),
                c.getPagamento()
            });
        }
    }

//    public void ReadJTableBusca(String Atributo, String Busca) {
//
//        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();
//
//        model.setNumRows(0);
//        if (Atributo.equals("Data/Hora Consulta")) {
//            Atributo = "DataConsulta";
//        }
//        ViewsDAO vwdao = new ViewsDAO();
//        for (Vw_Consultas c : vwdao.BuscaALLConsultas(Atributo, Busca)) {
//            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
//            model.addRow(new Object[]{
//                c.getCodConsulta(),
//                c.getPaciente().getNome_Completo(),
//                c.getPsicologo().getNome_completo(),
//                Validar.fDatetime((Timestamp) c.getDataConsulta()),
//                c.getStatus(),});
//        }
//
//    }
    public void ReadJTablePagBusca(int start, int size, String Busca) {

        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();

        model.setNumRows(0);
        boolean deletada;
        if (JCBdeletadas.getSelectedIndex() == 0) {
            deletada = false;
        } else {
            deletada = true;
        }
        ADMDAO vwdao = new ADMDAO();
        for (Vw_Consultas c : vwdao.fetchBySizeConsultasAdmBusca(start, size, Busca, deletada, dinicio, dfim)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                c.getCodConsulta(),
                c.getPaciente().getNome_Completo(),
                c.getPsicologo().getNome_completo(),
                Validar.fDatetime((Timestamp) c.getDataConsulta()),
                c.getStatus(),
                c.getPagamento()
            });
        }

    }

    private void JTConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTConsultasMouseClicked
        // TODO add your handling code here:
        if (JTConsultas.getSelectedRow() != -1) {
            int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
            int value = (Integer) JTConsultas.getModel().getValueAt(modelRow, 0);

        }
    }//GEN-LAST:event_JTConsultasMouseClicked

    private void JTConsultasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTConsultasKeyReleased
        // TODO add your handling code here:
        if (JTConsultas.getSelectedRow() != -1) {
            int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
            int value = (Integer) JTConsultas.getModel().getValueAt(modelRow, 0);

        }
    }//GEN-LAST:event_JTConsultasKeyReleased

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
            int npag2 = totalPages;
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

    private void BtnHelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHelpActionPerformed
        ModalHelp.setSize(540, 620);
        ModalHelp.setModal(true);
        ModalHelp.setVisible(false);
        ModalHelp.setVisible(false);
        ModalHelp.setLocationRelativeTo(null);
        ModalHelp.setVisible(true);
    }//GEN-LAST:event_BtnHelpActionPerformed

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
        TelaPrincipalAdm mp1 = new TelaPrincipalAdm();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnInicioActionPerformed

    private void BtnManterPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPacienteActionPerformed
        M mp1 = new M();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnManterPacienteActionPerformed

    private void BtnManterConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterConsultaActionPerformed
        ManterConsultasAdm mc = new ManterConsultasAdm();
        Util.SizeJanela(mc);
        this.dispose();
    }//GEN-LAST:event_BtnManterConsultaActionPerformed

    private void BtnManterPsicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPsicologoActionPerformed
        ManterPsiAdm mp = new ManterPsiAdm();
        mp.setVisible(true);
        Util.SizeJanela(mp);
        this.dispose();
    }//GEN-LAST:event_BtnManterPsicologoActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void JCBdeletadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBdeletadasActionPerformed
        // TODO add your handling code here:

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
    }//GEN-LAST:event_JCBdeletadasActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int npag1 = totalPages;
        if (txtBusca.getText() != "") {
            PAGE_SIZE = (int) SpinnerLimite.getValue();
            getCountBusca(txtBusca.getText());
            SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
            SpinnerNumPaginas.setValue((int) currentPage);

            LabelQtdePaginas.setText("de " + totalPages);
            getPageDataBusca(1, txtBusca.getText());
            int npag2 = totalPages;
            if (totalPages == 1) {
                BtnAvancarPouco.setEnabled(false);
                BtnAvancarBastante.setEnabled(false);

            }
            if (npag1 < npag2) {
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);

            }
            if (npag1 > npag2) {
                BtnVoltarPouco.setEnabled(true);
                BtnVoltarBastante.setEnabled(true);

            }
        } else {
            PAGE_SIZE = (int) SpinnerLimite.getValue();
            getCount();

            SpinnerNumPaginas.setModel(new javax.swing.SpinnerNumberModel(1, 1, totalPages, 1));
            SpinnerNumPaginas.setValue((int) currentPage);

            LabelQtdePaginas.setText("de " + totalPages);
            getPageData(1);
            int npag2 = totalPages;
            if (totalPages == 1) {
                BtnAvancarPouco.setEnabled(false);
                BtnAvancarBastante.setEnabled(false);

            }
            if (npag1 < npag2) {
                BtnAvancarPouco.setEnabled(true);
                BtnAvancarBastante.setEnabled(true);

            }
            if (npag1 > npag2) {
                BtnVoltarPouco.setEnabled(true);
                BtnVoltarBastante.setEnabled(true);

            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DataInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DataInicioPropertyChange
        // TODO add your handling code here:
         if(DataInicio.getDate() != null){
            java.util.Date date = DataInicio.getDate();
            dinicio = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
         }
    }//GEN-LAST:event_DataInicioPropertyChange

    private void DataFimPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DataFimPropertyChange
        // TODO add your handling code here:
         if(DataFim.getDate() != null){
            java.util.Date date = DataFim.getDate();
            dfim = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
         }
    }//GEN-LAST:event_DataFimPropertyChange

    public void clear() {
        //limpar a tela

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
            java.util.logging.Logger.getLogger(ManterConsultasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterConsultasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterConsultasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterConsultasAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new ManterConsultasAdm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAvancarBastante;
    private javax.swing.JButton BtnAvancarPouco;
    private javax.swing.JButton BtnHelp;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnVoltarBastante;
    private javax.swing.JButton BtnVoltarPouco;
    private com.toedter.calendar.JDateChooser DataFim;
    private com.toedter.calendar.JDateChooser DataInicio;
    private javax.swing.JComboBox<String> JCBdeletadas;
    private javax.swing.JTable JTConsultas;
    private javax.swing.JLabel LabelLimite;
    private javax.swing.JLabel LabelPagina;
    private javax.swing.JLabel LabelQtdePaginas;
    private javax.swing.JDialog ModalHelp;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JPanel PainelMeusDados;
    private javax.swing.JPanel PainelPaginacao;
    private javax.swing.JSpinner SpinnerLimite;
    private javax.swing.JSpinner SpinnerNumPaginas;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton jButton1;
    private LIB.JEImagePanel jEImagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
