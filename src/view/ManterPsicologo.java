/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import Validacoes.Deletar;
import Validacoes.Validar;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import model.bean.Psicologo;
import model.bean.Telefone;
import model.dao.PsicologoDAO;
import model.dao.TelefoneDAO;
import util.Util;

/**
 *
 * @author User
 */
public class ManterPsicologo extends javax.swing.JFrame {
    public static int codpsicologo;
    /**
     * Creates new form ManterPsicologo
     */
    public ManterPsicologo() {
        initComponents();
        TxtTelefone2.setVisible(false);
        labeltelefone2.setVisible(false);
        readpsicologo();
    }
    public void readpsicologo(){
        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        p = dao.ReadPsicologo(Main.cod);
        codpsicologo = p.getCodPsicologo();
        List<Telefone> t = new ArrayList<>();
        TelefoneDAO tdao = new TelefoneDAO();
        t = tdao.ReadTPsicologo(p.getCodPsicologo());
        txtNome.setText(p.getNome_completo());
        txtCRP.setText(p.getCRP());
        txtEmail.setText(p.getEmail());
        txtLogin.setText(p.getLogin());
        TxtTelefone.setText(t.get(0).getNumero());

        if(t.size() == 2){
            TxtTelefone2.setVisible(true);
            labeltelefone2.setVisible(true);
            TxtTelefone2.setText(t.get(1).getNumero());
            
        }
    }
public class JPanelGradient extends JPanel{
        protected void paintComponent(Graphics g){
            Graphics2D g2d= (Graphics2D) g;
            int largura= getWidth();
            int altura= getHeight();
            Color cor1=new Color(80,80,80);
            Color cor2=new Color(102,102,102);
            GradientPaint gp= new GradientPaint(0,0,cor1,180,altura,cor2);
            g2d.setPaint(gp);
            g2d.fillRect(0,0,largura,altura);
        }
    }
public class JPanelGradient2 extends JPanel{
        protected void paintComponent(Graphics g){
            Graphics2D g2d= (Graphics2D) g;
            int largura= getWidth();
            int altura= getHeight();
            Color cor1=new Color(1,112,186);
            Color cor2=new Color(22,218,218);
            GradientPaint gp= new GradientPaint(0,0,cor1,180,altura,cor2);
            g2d.setPaint(gp);
            g2d.fillRect(0,0,largura,altura);
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

        PainelMenu = new JPanelGradient();
        BtnManterAnotacao = new javax.swing.JButton();
        BtnManterPaciente = new javax.swing.JButton();
        BtnManterPsicologo = new javax.swing.JButton();
        BtnManterConsulta = new javax.swing.JButton();
        BtnExibirAnotacao = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        BtnVoltar = new javax.swing.JButton();
        jPanel3 = new JPanelGradient2();
        jLabel2 = new javax.swing.JLabel();
        BtnAlterar = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtCRP = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtLogin = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        BtnExcluirPsicologo = new javax.swing.JButton();
        labeltelefone = new javax.swing.JLabel();
        TxtTelefone = new javax.swing.JTextField();
        labeltelefone2 = new javax.swing.JLabel();
        TxtTelefone2 = new javax.swing.JTextField();
        BrnRedefinir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BtnManterAnotacao.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterAnotacao.setText("Exibir Anamneses");
        BtnManterAnotacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterAnotacaoActionPerformed(evt);
            }
        });

        BtnManterPaciente.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPaciente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPaciente.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPaciente.setText("Manter Pacientes");
        BtnManterPaciente.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPaciente.setFocusPainted(false);
        BtnManterPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPacienteActionPerformed(evt);
            }
        });

        BtnManterPsicologo.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPsicologo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPsicologo.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPsicologo.setText("Meus Dados");
        BtnManterPsicologo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPsicologoActionPerformed(evt);
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

        BtnExibirAnotacao.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibirAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibirAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibirAnotacao.setText("Exibir Anotações");
        BtnExibirAnotacao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnExibirAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibirAnotacaoActionPerformed(evt);
            }
        });

        BtnSair.setBackground(new java.awt.Color(102, 102, 102));
        BtnSair.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSair.setForeground(new java.awt.Color(255, 255, 255));
        BtnSair.setText("Sair");
        BtnSair.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSairActionPerformed(evt);
            }
        });

        BtnVoltar.setBackground(new java.awt.Color(102, 102, 102));
        BtnVoltar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnVoltar.setForeground(new java.awt.Color(255, 255, 255));
        BtnVoltar.setText("Início");
        BtnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnVoltarActionPerformed(evt);
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
            .addComponent(BtnVoltar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
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
                .addComponent(BtnManterAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnExibirAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(BtnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 133, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 95, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Nome Completo");

        BtnAlterar.setBackground(new java.awt.Color(0, 112, 186));
        BtnAlterar.setForeground(new java.awt.Color(255, 255, 255));
        BtnAlterar.setText("Alterar");
        BtnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAlterarActionPerformed(evt);
            }
        });

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Email");

        txtCRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCRPActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Login");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("CRP");

        BtnExcluirPsicologo.setBackground(new java.awt.Color(204, 204, 204));
        BtnExcluirPsicologo.setText("Excluir");
        BtnExcluirPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExcluirPsicologoActionPerformed(evt);
            }
        });

        labeltelefone.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labeltelefone.setText("Telefone");

        labeltelefone2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labeltelefone2.setText("Telefone 2");

        BrnRedefinir.setBackground(new java.awt.Color(204, 204, 204));
        BrnRedefinir.setText("Alterar Login/Senha");
        BrnRedefinir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrnRedefinirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PainelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail)
                                .addComponent(jLabel3)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2)
                            .addComponent(labeltelefone)
                            .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labeltelefone2)
                            .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCRP, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(251, 251, 251)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnExcluirPsicologo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(BtnAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                .addComponent(BrnRedefinir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(94, Short.MAX_VALUE))
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(PainelMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCRP)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labeltelefone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labeltelefone2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTelefone2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(84, 84, 84)
                        .addComponent(BtnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BrnRedefinir, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnExcluirPsicologo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtCRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCRPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCRPActionPerformed

    private void BtnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAlterarActionPerformed
        // TODO add your handling code here:
        boolean dadosvalidos = true;

        Psicologo p = new Psicologo();
        PsicologoDAO dao = new PsicologoDAO();
        Telefone tf = new Telefone();
        Telefone tf2 = new Telefone();
        TelefoneDAO tfdao = new TelefoneDAO();
        String msg = "Existem campos com formatos Inválidos\n\nFavor Verificar os campos:";
        
      if (!Validar.vCamposVaziosManterPSI(this, txtNome, txtEmail, txtCRP, txtLogin, TxtTelefone)) {
            if (Validar.vNome(txtNome.getText())) {
                p.setNome_completo(txtNome.getText());
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

            if (Validar.vCRP(txtCRP.getText())) {
                p.setCRP(txtCRP.getText());
            } else {
                dadosvalidos = false;
                msg += "\nCPF Invalido: " + txtCRP.getText();
            }

            p.setLogin(txtLogin.getText());
            
            p.setCodPsicologo(codpsicologo);
            
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
                    msg += "\nNúmero de Telefone 2 Invalido: " + TxtTelefone.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
                }
            }
            if( !TxtTelefone2.getText().isEmpty()){
             if (Validar.vTelefone(TxtTelefone2.getText()) ){
                tf.setNumero(TxtTelefone.getText());
            } else {
                dadosvalidos = false;
                msg += "\nNúmero de Telefone 2 Invalido: " + TxtTelefone.getText() + "\nO Número deve ser no formato xxxxxxxxxxx";
            }
            }
            if (dadosvalidos) {
                if (dao.UpdateMP(p)) {

                    p = dao.ReadPsicologo(p.getCRP());

                     List<Telefone> t = tfdao.ReadTPsicologo(p.getCodPsicologo());
                     t.get(0).setNumero(TxtTelefone.getText());
                    if (tfdao.UpdateTPsicologo(t.get(0))) {
                        
                        if(t.size() == 2){
                            t.get(1).setNumero(TxtTelefone2.getText());
                       tfdao.UpdateTPsicologo(t.get(1));
                        
                    }
                         JOptionPane.showMessageDialog(this, "Psicologo: " + p.getNome_completo() + " Salvo com sucesso");
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
    
    public void clear() {
        txtNome.setText(null);
        txtEmail.setText(null);
        txtCRP.setText(null);
        txtLogin.setText(null);
        
        TxtTelefone.setText(null);
    }
    private void BtnExcluirPsicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExcluirPsicologoActionPerformed
         if(codpsicologo != 0){
             Psicologo p = new Psicologo();
             PsicologoDAO pdao = new PsicologoDAO();
             p = pdao.ReadPsicologo(codpsicologo);
              boolean sucesso = Deletar.DPsicologo(p);
              if(sucesso){
                    JOptionPane.showMessageDialog(this, "Psicologo Deletado com sucesso");
                    codpsicologo = 0;
                    TelaLogin tl = new TelaLogin();
                    tl.setVisible(true);
                    this.dispose();
              }
    }
    }//GEN-LAST:event_BtnExcluirPsicologoActionPerformed

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        // TODO add your handling code here:
        TelaPrincipal tp = new TelaPrincipal();
        Util.SizeJanela(tp);
        this.dispose();
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void BtnManterAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterAnotacaoActionPerformed
        // TODO add your handling code here:
        ExibirAnamneses  ma = new ExibirAnamneses();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnManterAnotacaoActionPerformed

    private void BtnManterPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPacienteActionPerformed
        // TODO add your handling code here:
        ManterPaciente mp1 = new ManterPaciente();
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
        TelaLogin  tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

    private void BrnRedefinirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrnRedefinirActionPerformed
        // TODO add your handling code here:
        ValidarUsuarioMP  vu = new ValidarUsuarioMP();
        vu.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BrnRedefinirActionPerformed

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
            java.util.logging.Logger.getLogger(ManterPsicologo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterPsicologo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterPsicologo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterPsicologo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManterPsicologo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrnRedefinir;
    private javax.swing.JButton BtnAlterar;
    private javax.swing.JButton BtnExcluirPsicologo;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnManterAnotacao;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JTextField TxtTelefone;
    private javax.swing.JTextField TxtTelefone2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel labeltelefone;
    private javax.swing.JLabel labeltelefone2;
    private javax.swing.JTextField txtCRP;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtLogin;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
