/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.util.Arrays;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.bean.Psicologo;
import model.dao.PsicologoDAO;
import util.Util;
import view_adm.MenuPrincipal.TelaPrincipalAdm;

/**
 *
 * @author User
 */
public class TelaLogin extends javax.swing.JFrame {

    /**
     * Creates new form TelaLogin
     */
    public TelaLogin() {

        initComponents();
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("pmaiconemenor.png")));

    }

    public class JPanelGradient extends JPanel {

        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            int largura = getWidth();
            int altura = getHeight();
            Color cor1 = new Color(59, 131, 117);
            Color cor2 = new Color(140, 164, 138);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtLogin = new javax.swing.JTextField();
        BtnLogin = new javax.swing.JButton();
        txtSenha = new javax.swing.JPasswordField();
        PainelGradiente =  new JPanelGradient();
        jLabel4 = new javax.swing.JLabel();
        ChckMostrarSenha = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Usuário");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Senha");

        txtLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLoginActionPerformed(evt);
            }
        });
        txtLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtLoginKeyPressed(evt);
            }
        });

        BtnLogin.setBackground(new java.awt.Color(59, 131, 117));
        BtnLogin.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnLogin.setForeground(new java.awt.Color(255, 255, 255));
        BtnLogin.setText("Login");
        BtnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLoginActionPerformed(evt);
            }
        });

        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Acesso ao Sistema PMA");

        javax.swing.GroupLayout PainelGradienteLayout = new javax.swing.GroupLayout(PainelGradiente);
        PainelGradiente.setLayout(PainelGradienteLayout);
        PainelGradienteLayout.setHorizontalGroup(
            PainelGradienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelGradienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(62, 62, 62))
        );
        PainelGradienteLayout.setVerticalGroup(
            PainelGradienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelGradienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                .addContainerGap())
        );

        ChckMostrarSenha.setText("Mostrar Senha");
        ChckMostrarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChckMostrarSenhaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelGradiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ChckMostrarSenha, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelGradiente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(7, 7, 7)
                .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ChckMostrarSenha)
                .addGap(28, 28, 28)
                .addComponent(BtnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLoginActionPerformed
        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        JOptionPane optionPane = new JOptionPane();
        String msg;
        p.setLogin(txtLogin.getText());
        String senha = new String(txtSenha.getPassword());

        p.setSenha(senha);
        boolean valido = dao.ValidarLogin(p, this);

        if (valido && p.getLogin().equals("Admin")) {
            Main.cod = p.getCodPsicologo();
            TelaPrincipalAdm mp1 = new TelaPrincipalAdm();
            Util.SizeJanela(mp1);

            this.dispose();
        } else if (valido) {
            p = dao.ReadPsicologoLS(p.getLogin(), p.getSenha());
            if (p.isDeletado()) {
                msg = "Você está tentando acessar o sistema com um Psicólogo deletado, por favor entre em contato com o Admin para reativar sua conta!";
                optionPane.setMessage(msg);
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog(this, "Falha no Login");
                dialog.setVisible(true);
            } else {
                Main.cod = p.getCodPsicologo();
                msg = "Olá " + p.getNome_completo() + ", Seja Bem Vindo(a)!";
                //optionPane.setMessage(msg);
                //optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE); 
                //JDialog dialog = optionPane.createDialog(this, "Bem Vindo(a)!");
                //dialog.setVisible(true);

                TelaPrincipal tp = new TelaPrincipal();
                Util.SizeJanela(tp);
                tp.setVisible(true);
                tp.setVisible(true);
                this.dispose();
            }

        } else {
            msg = "Usuário ou Senha Inválidos! Por Favor Verifique os campos e tente novamente!";
            optionPane.setMessage(msg);
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
            JDialog dialog = optionPane.createDialog(this, "Falha no Login");
            dialog.setVisible(true);
        }


    }//GEN-LAST:event_BtnLoginActionPerformed

    private void ChckMostrarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ChckMostrarSenhaMouseClicked
        if (ChckMostrarSenha.isSelected()) {
            txtSenha.setEchoChar((char) 0);
        } else {
            txtSenha.setEchoChar('*');
        }
    }//GEN-LAST:event_ChckMostrarSenhaMouseClicked

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Psicologo p = new Psicologo();
            PsicologoDAO dao = new PsicologoDAO();
            JOptionPane optionPane = new JOptionPane();
            String msg;
            p.setLogin(txtLogin.getText());
            String senha = new String(txtSenha.getPassword());

            p.setSenha(senha);
            boolean valido = dao.ValidarLogin(p, this);
            if (valido && p.getLogin().equals("Admin")) {
                Main.cod = p.getCodPsicologo();
                TelaPrincipalAdm mp1 = new TelaPrincipalAdm();
                Util.SizeJanela(mp1);
                this.dispose();
            } else if (valido) {
                p = dao.ReadPsicologoLS(p.getLogin(), p.getSenha());
                if (p.isDeletado()) {
                    msg = "Você está tentando acessar o sistema com um Psicólogo deletado, por favor entre em contato com o Admin para reativar sua conta!";
                    optionPane.setMessage(msg);
                    optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog(this, "Falha no Login");
                    dialog.setVisible(true);
                } else {
                    Main.cod = p.getCodPsicologo();
                    msg = "Olá " + p.getNome_completo() + ", Seja Bem Vindo(a)!";
                    //optionPane.setMessage(msg);
                    //optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE); 
                    //JDialog dialog = optionPane.createDialog(this, "Bem Vindo(a)!");
                    //dialog.setVisible(true);

                    TelaPrincipal tp = new TelaPrincipal();
                    Util.SizeJanela(tp);
                    tp.setVisible(true);
                    tp.setVisible(true);
                    this.dispose();
                }

            } else {
                msg = "Usuário ou Senha Inválidos! Por Favor Verifique os campos e tente novamente!";
                optionPane.setMessage(msg);
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog(this, "Falha no Login");
                dialog.setVisible(true);
            }

        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void txtLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Psicologo p = new Psicologo();
            PsicologoDAO dao = new PsicologoDAO();
            JOptionPane optionPane = new JOptionPane();
            String msg;
            p.setLogin(txtLogin.getText());
            String senha = new String(txtSenha.getPassword());

            p.setSenha(senha);
            boolean valido = dao.ValidarLogin(p, this);
            if (valido && p.getLogin().equals("Admin")) {
                Main.cod = p.getCodPsicologo();
                TelaPrincipalAdm mp1 = new TelaPrincipalAdm();
                Util.SizeJanela(mp1);
                this.dispose();
            } else if (valido) {
                p = dao.ReadPsicologoLS(p.getLogin(), p.getSenha());
                if (p.isDeletado()) {
                    msg = "Você está tentando acessar o sistema com um Psicólogo deletado, por favor entre em contato com o Admin para reativar sua conta!";
                    optionPane.setMessage(msg);
                    optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                    JDialog dialog = optionPane.createDialog(this, "Falha no Login");
                    dialog.setVisible(true);
                } else {
                    Main.cod = p.getCodPsicologo();
                    msg = "Olá " + p.getNome_completo() + ", Seja Bem Vindo(a)!";
                    //optionPane.setMessage(msg);
                    //optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE); 
                    //JDialog dialog = optionPane.createDialog(this, "Bem Vindo(a)!");
                    //dialog.setVisible(true);

                    TelaPrincipal tp = new TelaPrincipal();
                    Util.SizeJanela(tp);
                    tp.setVisible(true);
                    tp.setVisible(true);
                    this.dispose();
                }
            } else {
                msg = "Usuário ou Senha Inválidos! Por Favor Verifique os campos e tente novamente!";
                optionPane.setMessage(msg);
                optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
                JDialog dialog = optionPane.createDialog(this, "Falha no Login");
                dialog.setVisible(true);
            }

        }
    }//GEN-LAST:event_txtLoginKeyPressed

    private void txtLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLoginActionPerformed

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
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnLogin;
    private javax.swing.JCheckBox ChckMostrarSenha;
    private javax.swing.JPanel PainelGradiente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JPasswordField txtSenha;
    // End of variables declaration//GEN-END:variables
}
