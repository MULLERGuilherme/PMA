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
import model.dao.PacienteDAO;
import model.dao.TelefoneDAO;
import model.dao.ViewsDAO;
import util.Util;

/**
 *
 * @author guimu
 */
public class ManterConsultasAdm extends javax.swing.JFrame {



    public ManterConsultasAdm() {
        initComponents();
        DefaultTableModel dtmConsultas = (DefaultTableModel) JTConsultas.getModel();
        TableColumnModel cmod = JTConsultas.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTConsultas.setRowSorter(new TableRowSorter(dtmConsultas));

        ReadJTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnManterPaciente = new javax.swing.JButton();
        BtnManterConsulta = new javax.swing.JButton();
        BtnManterPsicologo = new javax.swing.JButton();
        BtnExibiranamneses = new javax.swing.JButton();
        BtnExibirAnotacao = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        jEImagePanel1 = new LIB.JEImagePanel();
        jLabel6 = new javax.swing.JLabel();
        JCBAtributo = new javax.swing.JComboBox<>();
        txtBusca = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        JTConsultas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
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

        BtnManterPaciente.setText("Pacientes");
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

        BtnManterPsicologo.setText("Psicólogos");
        BtnManterPsicologo.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPsicologo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPsicologo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPsicologo.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPsicologoActionPerformed(evt);
            }
        });

        BtnExibiranamneses.setText("Exibir Todas as Anamneses");
        BtnExibiranamneses.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibiranamneses.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnExibiranamneses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibiranamneses.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibiranamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibiranamnesesActionPerformed(evt);
            }
        });

        BtnExibirAnotacao.setText("Exibir Todas as Anotações");
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

        javax.swing.GroupLayout PainelMenuLayout = new javax.swing.GroupLayout(PainelMenu);
        PainelMenu.setLayout(PainelMenuLayout);
        PainelMenuLayout.setHorizontalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BtnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnManterPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnManterConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnExibiranamneses, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BtnSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addComponent(BtnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnManterPsicologo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibiranamneses, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        jLabel6.setText("Buscar Consulta  por");

        JCBAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Paciente", "Psicologo", "Data/Hora Consulta", "Status" }));

        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        JTConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Paciente", "Psicologo", "Data da Consulta", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnBuscar)
                        .addContainerGap(973, Short.MAX_VALUE))
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 1405, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnBuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(445, Short.MAX_VALUE))
        );

        getContentPane().add(jEImagePanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void ReadJTable() {

        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();

        model.setNumRows(0);
        ViewsDAO vwdao = new ViewsDAO();
     
  for (Vw_Consultas c : vwdao.ReadALLConsultas()) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
               c.getCodConsulta(),
               c.getPaciente().getNome_Completo(),
               c.getPsicologo().getNome_completo(),
               Validar.fDatetime((Timestamp) c.getDataConsulta()),
               c.getStatus(),
            });
        }
    }

    public void ReadJTableBusca(String Atributo, String Busca) {

        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();

        model.setNumRows(0);
         if (Atributo.equals("Data/Hora Consulta")) {
            Atributo = "DataConsulta";
        }
        ViewsDAO vwdao = new ViewsDAO();
          for (Vw_Consultas c : vwdao.BuscaALLConsultas(Atributo, Busca)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
               c.getCodConsulta(),
               c.getPaciente().getNome_Completo(),
               c.getPsicologo().getNome_completo(),
               Validar.fDatetime((Timestamp) c.getDataConsulta()),
               c.getStatus(),
            });
        }
    
    }

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        // TODO add your handling code here:
        //System.out.println(JCBAtributo.getSelectedIndex());
     
        this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
    }//GEN-LAST:event_BtnBuscarActionPerformed

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

    public void clear() {
        //limpar a tela
  
    }

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        // TODO add your handling code here:
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void BtnExibirAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacaoActionPerformed
        // TODO add your handling code here:
        ExibirAnotacoesAdm ea = new ExibirAnotacoesAdm();
        Util.SizeJanela(ea);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacaoActionPerformed

    private void BtnExibiranamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibiranamnesesActionPerformed
        // TODO add your handling code here:
        ExibirAnamnesesAdm ma = new ExibirAnamnesesAdm();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnExibiranamnesesActionPerformed

    private void BtnManterPsicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPsicologoActionPerformed
        // TODO add your handling code here:
        ManterPsicologoAdm mp = new ManterPsicologoAdm();
        //Util.SizeJanela(mp);
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnManterPsicologoActionPerformed

    private void BtnManterConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterConsultaActionPerformed
        // TODO add your handling code here:
//        CadastrarConsulta2 mc = new CadastrarConsulta2();
//        Util.SizeJanela(mc);
//        this.dispose();
    }//GEN-LAST:event_BtnManterConsultaActionPerformed

    private void BtnManterPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPacienteActionPerformed
        // TODO add your handling code here:
        ManterPacienteAdm mp1 = new ManterPacienteAdm();
        Util.SizeJanela(mp1);
        this.dispose();
     
    }//GEN-LAST:event_BtnManterPacienteActionPerformed

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        TelaPrincipalAdm mp1 = new TelaPrincipalAdm();
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
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnExibiranamneses;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JComboBox<String> JCBAtributo;
    private javax.swing.JTable JTConsultas;
    private javax.swing.JPanel PainelMenu;
    private LIB.JEImagePanel jEImagePanel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField txtBusca;
    // End of variables declaration//GEN-END:variables
}
