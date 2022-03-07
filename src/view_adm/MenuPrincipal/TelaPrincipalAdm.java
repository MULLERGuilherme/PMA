/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_adm.MenuPrincipal;
import view.*;
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
import model.dao.ADMDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.ViewsDAO;
import util.Util;
import static view.ExibirConsultasPaciente.codpaciente;
public class TelaPrincipalAdm extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal4
     */
    public TelaPrincipalAdm() {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       
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
        ADMDAO vwdao = new ADMDAO();
        //ConsultaDAO cdao = new ConsultaDAO();
        //PacienteDAO pdao = new PacienteDAO();
        //Paciente p = new Paciente();
        
        
        for (Vw_Consultas c : vwdao.ReadConsultas(data)) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
               c.getCodConsulta(),
               c.getPaciente().getNome_Completo(),
               c.getPsicologo().getNome_completo(),
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
        PainelMenu = new javax.swing.JPanel();
        BtnInicio = new javax.swing.JButton();
        BtnManterPaciente = new javax.swing.JButton();
        BtnManterConsulta = new javax.swing.JButton();
        BtnManterPsicologo = new javax.swing.JButton();
        BtnExibiranamneses = new javax.swing.JButton();
        BtnExibirAnotacao = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        jEImagePanel1 = new LIB.JEImagePanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnOk = new javax.swing.JButton();
        DataChooser = new com.toedter.calendar.JDateChooser();
        btnAlterar = new javax.swing.JButton();
        BtnExcluir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTConsultas = new javax.swing.JTable();

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

        PainelMenu.setBackground(new java.awt.Color(102, 102, 102));
        PainelMenu.setForeground(new java.awt.Color(102, 102, 102));

        BtnInicio.setBackground(new java.awt.Color(102, 102, 102));
        BtnInicio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnInicio.setForeground(new java.awt.Color(255, 255, 255));
        BtnInicio.setText("Início");
        BtnInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnInicioActionPerformed(evt);
            }
        });

        BtnManterPaciente.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPaciente.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPaciente.setText("Pacientes");
        BtnManterPaciente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPaciente.setFocusPainted(false);
        BtnManterPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPacienteActionPerformed(evt);
            }
        });

        BtnManterConsulta.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterConsulta.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterConsulta.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterConsulta.setText("Cadastrar Consulta");
        BtnManterConsulta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterConsultaActionPerformed(evt);
            }
        });

        BtnManterPsicologo.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPsicologo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPsicologo.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPsicologo.setText("Psicólogos");
        BtnManterPsicologo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPsicologoActionPerformed(evt);
            }
        });

        BtnExibiranamneses.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibiranamneses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibiranamneses.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibiranamneses.setText("Exibir Todas as Anamneses");
        BtnExibiranamneses.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnExibiranamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibiranamnesesActionPerformed(evt);
            }
        });

        BtnExibirAnotacao.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnotacao.setText("Exibir Todas as Anotações");
        BtnExibirAnotacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
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
            .addComponent(BtnInicio, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
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
                .addComponent(BtnInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addGap(0, 148, Short.MAX_VALUE))
        );

        getContentPane().add(PainelMenu, java.awt.BorderLayout.LINE_START);

        jEImagePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/spring-floral-watercolor-background-vector-green-with-leaf-illustration_53876-126350.jpg"))); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel1.setText("Exibindo as Consultas do dia");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        jLabel2.setText("jLabel2");
        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        jLabel3.setText("Selecionar dia");
        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        btnOk.setBackground(new java.awt.Color(204, 204, 204));
        btnOk.setText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        DataChooser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        btnAlterar.setBackground(new java.awt.Color(204, 204, 204));
        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        BtnExcluir.setBackground(new java.awt.Color(204, 204, 204));
        BtnExcluir.setText("Excluir");
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
                "CodConsulta", "Paciente", "Psicologo", "Horário da Consulta", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTConsultas.setMaximumSize(new java.awt.Dimension(1080, 200));
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
                        .addGap(41, 41, 41)
                        .addComponent(btnOk)
                        .addGap(18, 18, 18)
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
                        .addComponent(btnOk)
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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnManterPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPacienteActionPerformed
        // TODO add your handling code here:
        ManterPacienteAdm mp1 = new ManterPacienteAdm();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnManterPacienteActionPerformed

    private void BtnManterConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterConsultaActionPerformed
        // TODO add your handling code here:
        ManterConsultasAdm mc = new ManterConsultasAdm();
        Util.SizeJanela(mc);
        this.dispose();
//        CadastrarConsulta2 mc = new CadastrarConsulta2();
//        Util.SizeJanela(mc);
//        this.dispose();
    }//GEN-LAST:event_BtnManterConsultaActionPerformed

    private void BtnManterPsicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPsicologoActionPerformed
        // TODO add your handling code here:
        ManterPsicologoAdm mp = new ManterPsicologoAdm();
        //Util.SizeJanela(mp);
        mp.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnManterPsicologoActionPerformed

    private void BtnExibiranamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibiranamnesesActionPerformed
        // TODO add your handling code here:
        ExibirAnamnesesAdm  ma = new ExibirAnamnesesAdm();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnExibiranamnesesActionPerformed

    private void BtnExibirAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacaoActionPerformed
        // TODO add your handling code here:
        ExibirAnotacoesAdm ea = new ExibirAnotacoesAdm();
        Util.SizeJanela(ea);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacaoActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        // TODO add your handling code here:
        TelaLogin  tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

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

    }//GEN-LAST:event_JTConsultasMouseClicked

    private void BtnInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnInicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnInicioActionPerformed

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
   if(DataChooser.getDate() != null){
            Date date = DataChooser.getDate();
            LocalDate localDate = date.toInstant().atZone( ZoneId.systemDefault() ).toLocalDate();
           
            ReadJTable(localDate);
              DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            jLabel2.setText(dtf.format(localDate));
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnOkActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipalAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipalAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipalAdm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnExcluir;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnExibiranamneses;
    private javax.swing.JButton BtnInicio;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private com.toedter.calendar.JDateChooser DataChooser;
    private javax.swing.JTable JTConsultas;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnOk;
    private LIB.JEImagePanel jEImagePanel1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
