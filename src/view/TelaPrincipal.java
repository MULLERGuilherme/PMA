/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import Validacoes.Deletar;
import Validacoes.Validar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.bean.Consulta;
import model.bean.Paciente;
import model.bean.Vw_Consultas;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.ViewsDAO;
import util.Util;

public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal4
     */
    public TelaPrincipal() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        btnAlterar.setEnabled(false);
        BtnExcluir.setEnabled(false);
       
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTConsultas.getModel();
        TableColumnModel cmod = JTConsultas.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTConsultas.setRowSorter(new TableRowSorter(dtmPacientes));
        
        LocalDate localDate = LocalDate.now();
        //System.out.println(localDate);  
        ReadJTable(localDate);
        Date date1 = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        DataChooser.setDate(date1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
          

        jLabel2.setText(dtf.format(localDate));
    }
private void ReadJTable(LocalDate data) {
        DefaultTableModel model = (DefaultTableModel) JTConsultas.getModel();
       
        model.setNumRows(0);
        ViewsDAO vwdao = new ViewsDAO();
        //ConsultaDAO cdao = new ConsultaDAO();
        //PacienteDAO pdao = new PacienteDAO();
        //Paciente p = new Paciente();
        
        
        for (Vw_Consultas c : vwdao.ReadConsultas(data, Main.cod)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
               c.getCodConsulta(),
               c.getPaciente().getNome_Completo(),
               Validar.ftime((Timestamp) c.getDataConsulta()),
               c.getStatus(),
            });
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

        jPanel1 = new JPanel();
        jEImagePanel1 = new LIB.JEImagePanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        DataChooser = new com.toedter.calendar.JDateChooser();
        btnAlterar = new javax.swing.JButton();
        BtnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTConsultas = new javax.swing.JTable();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnPacientes = new javax.swing.JButton();
        BtnConsultas = new javax.swing.JButton();
        BtnExibirAnamneses = new javax.swing.JButton();
        BtnExibirAnotacoes = new javax.swing.JButton();
        BtnExibirAnotacoes1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1386, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Exibindo as Consultas do dia");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        jLabel2.setText("jLabel2");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        jLabel3.setText("Selecionar dia");
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        DataChooser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataChooser.setToolTipText("Seleciona uma Data em que desejam ser vistas as consultas");
        DataChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DataChooserPropertyChange(evt);
            }
        });

        btnAlterar.setText("Alterar");
        btnAlterar.setBackground(new java.awt.Color(204, 204, 204));
        btnAlterar.setToolTipText("Altera dados de uma consulta selecionada");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        BtnExcluir.setText("Excluir");
        BtnExcluir.setBackground(new java.awt.Color(204, 204, 204));
        BtnExcluir.setToolTipText("Exclui uma consulta selecionada");
        BtnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirActionPerformed(evt);
            }
        });

        JTConsultas.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        JTConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CodConsulta", "Paciente", "Horário da Consulta", "Status"
            }
        ));
        JTConsultas.setMaximumSize(new java.awt.Dimension(1080, 200));
        JTConsultas.setToolTipText("Exibe as consultas de uma determinada data");
        JTConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTConsultasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTConsultas);

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(DataChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(btnAlterar)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcluir))
                    .addComponent(jLabel3)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addContainerGap(258, Short.MAX_VALUE))
            .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jEImagePanel1Layout.createSequentialGroup()
                    .addGap(143, 143, 143)
                    .addComponent(jScrollPane1)
                    .addGap(144, 144, 144)))
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 452, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAlterar)
                        .addComponent(BtnExcluir))
                    .addComponent(DataChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43))
            .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jEImagePanel1Layout.createSequentialGroup()
                    .addGap(104, 104, 104)
                    .addComponent(jScrollPane1)
                    .addGap(104, 104, 104)))
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

        BtnPacientes.setBackground(new java.awt.Color(102, 102, 102));
        BtnPacientes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnPacientes.setForeground(new java.awt.Color(255, 255, 255));
        BtnPacientes.setText("Pacientes");
        BtnPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPacientesActionPerformed(evt);
            }
        });

        BtnConsultas.setBackground(new java.awt.Color(102, 102, 102));
        BtnConsultas.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnConsultas.setForeground(new java.awt.Color(255, 255, 255));
        BtnConsultas.setText("Cadastrar Consultas");
        BtnConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnConsultasActionPerformed(evt);
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
            .addComponent(BtnVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnPacientes, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnConsultas, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirAnamneses, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacoes, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
            .addComponent(BtnExibirAnotacoes1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
        );
        PainelMenuLayout.setVerticalGroup(
            PainelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelMenuLayout.createSequentialGroup()
                .addComponent(BtnVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnPacientes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnamneses, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacoes, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacoes1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // TODO add your handling code here:
        if (JTConsultas.getSelectedRow() != -1) {
            int modelRow = JTConsultas.convertRowIndexToModel(JTConsultas.getSelectedRow());
            int value = (Integer)JTConsultas.getModel().getValueAt(modelRow,0);
            AlterarConsultapelomenu.codconsulta = value;

            AlterarConsultapelomenu cp = new AlterarConsultapelomenu();
            cp.setVisible(true);
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma consulta para alterar");
        }
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void BtnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnExcluirActionPerformed

    private void JTConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTConsultasMouseClicked
        btnAlterar.setEnabled(true);
        BtnExcluir.setEnabled(true);
    }//GEN-LAST:event_JTConsultasMouseClicked

    private void DataChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DataChooserPropertyChange
         if(DataChooser.getDate() != null){
            Date date = DataChooser.getDate();
            LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
           
            ReadJTable(localDate);
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            jLabel2.setText(dtf.format(localDate));
        }      
    }//GEN-LAST:event_DataChooserPropertyChange

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

    private void BtnConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnConsultasActionPerformed
        CadastrarConsulta2 mc = new CadastrarConsulta2();
        Util.SizeJanela(mc);
        this.dispose();
    }//GEN-LAST:event_BtnConsultasActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnConsultas;
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnExibirAnamneses;
    private javax.swing.JButton BtnExibirAnotacoes;
    private javax.swing.JButton BtnExibirAnotacoes1;
    private javax.swing.JButton BtnPacientes;
    private javax.swing.JButton BtnVoltar;
    private com.toedter.calendar.JDateChooser DataChooser;
    private javax.swing.JTable JTConsultas;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JButton btnAlterar;
    private LIB.JEImagePanel jEImagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
