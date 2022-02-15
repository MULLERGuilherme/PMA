/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import LIB.FadeEffect;
import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;


/**
 *
 * @author guimu
 */
public class ModalPaciente extends javax.swing.JFrame {

    /**
     * Creates new form ModalPaciente
     */
    public ModalPaciente() {
        initComponents();
        BtnSalvarAlteracoes.setEnabled(false);
        this.setDefaultCloseOperation(0);
        FadeEffect.transp(ModalPaciente.this);
        FadeEffect.fadeInFrame(this, 15, 0.5f);
        PainelNome.setBorder(new RoundedBorder(5));
        PainelData.setBorder(new RoundedBorder(5));
        PainelCidade.setBorder(new RoundedBorder(5));
        PainelEndereco.setBorder(new RoundedBorder(5));
        PainelProfissao.setBorder(new RoundedBorder(5));
        PainelReligiao.setBorder(new RoundedBorder(5));
        PainelEscolaridade.setBorder(new RoundedBorder(5));
        PainelTelefone.setBorder(new RoundedBorder(5));
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jEImagePanel2 = new LIB.JEImagePanel();
        PainelDadosPaciente = new javax.swing.JPanel();
        PainelIdentificacaoPessoal = new javax.swing.JPanel();
        PainelData = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        DataNasc = new com.github.lgooddatepicker.components.DatePicker();
        jLabel3 = new javax.swing.JLabel();
        Sexo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        estadocivil = new javax.swing.JComboBox<>();
        PainelEndereco = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        TxtEndereco = new javax.swing.JTextField();
        PainelNome = new javax.swing.JPanel();
        LabelNome = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        PainelCidade = new javax.swing.JPanel();
        LabelCidade = new javax.swing.JLabel();
        LabelCidade1 = new javax.swing.JLabel();
        txtCPF = new javax.swing.JTextField();
        PainelProfissao = new javax.swing.JPanel();
        TxtProfissao = new javax.swing.JTextField();
        LabelProfissao = new javax.swing.JLabel();
        PainelReligiao = new javax.swing.JPanel();
        txtReligiao = new javax.swing.JTextField();
        LabelReligiao = new javax.swing.JLabel();
        PainelEscolaridade = new javax.swing.JPanel();
        txtEscolaridade = new javax.swing.JTextField();
        LabelEscolaridade = new javax.swing.JLabel();
        PainelTelefone = new javax.swing.JPanel();
        LabelCidade2 = new javax.swing.JLabel();
        LabelCidade3 = new javax.swing.JLabel();
        txtCPF1 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        BtnSalvarAlteracoes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jEImagePanel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/fondotransparente.png"))); // NOI18N
        jEImagePanel2.setLayout(new java.awt.GridBagLayout());

        PainelDadosPaciente.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal.setBackground(new java.awt.Color(0, 255, 0));

        javax.swing.GroupLayout PainelIdentificacaoPessoalLayout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal);
        PainelIdentificacaoPessoal.setLayout(PainelIdentificacaoPessoalLayout);
        PainelIdentificacaoPessoalLayout.setHorizontalGroup(
            PainelIdentificacaoPessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 902, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoalLayout.setVerticalGroup(
            PainelIdentificacaoPessoalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        PainelData.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("   *  Data de Nascimento: ");

        DataNasc.setPreferredSize(new java.awt.Dimension(160, 17));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("*  Sexo:");

        Sexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Masculino", "Feminino","Não Definido"}));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("*  Estado Civil:");

        estadocivil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Solteiro", "Casado", "Separado", "Divorciado","Viuvo" }));

        javax.swing.GroupLayout PainelDataLayout = new javax.swing.GroupLayout(PainelData);
        PainelData.setLayout(PainelDataLayout);
        PainelDataLayout.setHorizontalGroup(
            PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(estadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelDataLayout.setVerticalGroup(
            PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Sexo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(estadocivil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGroup(PainelDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(DataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        PainelEndereco.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("   Endereço:");

        TxtEndereco.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelEnderecoLayout = new javax.swing.GroupLayout(PainelEndereco);
        PainelEndereco.setLayout(PainelEnderecoLayout);
        PainelEnderecoLayout.setHorizontalGroup(
            PainelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEnderecoLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TxtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelEnderecoLayout.setVerticalGroup(
            PainelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEnderecoLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(PainelEnderecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtEndereco, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)))
        );

        PainelNome.setBackground(new java.awt.Color(255, 255, 255));

        LabelNome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelNome.setText("*  Nome:");

        txtNome.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelNomeLayout = new javax.swing.GroupLayout(PainelNome);
        PainelNome.setLayout(PainelNomeLayout);
        PainelNomeLayout.setHorizontalGroup(
            PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelNomeLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(LabelNome)
                .addGap(26, 26, 26)
                .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 771, Short.MAX_VALUE)
                .addContainerGap())
        );
        PainelNomeLayout.setVerticalGroup(
            PainelNomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelNomeLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(LabelNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(txtNome, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
        );

        PainelCidade.setBackground(new java.awt.Color(255, 255, 255));

        LabelCidade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelCidade.setText("*  CPF:");

        LabelCidade1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelCidade1.setText("    Cidade:");

        txtCPF.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelCidadeLayout = new javax.swing.GroupLayout(PainelCidade);
        PainelCidade.setLayout(PainelCidadeLayout);
        PainelCidadeLayout.setHorizontalGroup(
            PainelCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCidadeLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(LabelCidade1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LabelCidade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1101, 1101, 1101))
        );
        PainelCidadeLayout.setVerticalGroup(
            PainelCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelCidadeLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(PainelCidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelCidade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(LabelCidade1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        PainelProfissao.setBackground(new java.awt.Color(255, 255, 255));

        TxtProfissao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelProfissao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelProfissao.setText("        Profissão:");

        javax.swing.GroupLayout PainelProfissaoLayout = new javax.swing.GroupLayout(PainelProfissao);
        PainelProfissao.setLayout(PainelProfissaoLayout);
        PainelProfissaoLayout.setHorizontalGroup(
            PainelProfissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelProfissaoLayout.createSequentialGroup()
                .addComponent(LabelProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, 758, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelProfissaoLayout.setVerticalGroup(
            PainelProfissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelProfissaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(TxtProfissao, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                .addComponent(LabelProfissao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PainelReligiao.setBackground(new java.awt.Color(255, 255, 255));

        txtReligiao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelReligiao.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelReligiao.setText("        Religião:");

        javax.swing.GroupLayout PainelReligiaoLayout = new javax.swing.GroupLayout(PainelReligiao);
        PainelReligiao.setLayout(PainelReligiaoLayout);
        PainelReligiaoLayout.setHorizontalGroup(
            PainelReligiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelReligiaoLayout.createSequentialGroup()
                .addComponent(LabelReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelReligiaoLayout.setVerticalGroup(
            PainelReligiaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelReligiao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtReligiao, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        PainelEscolaridade.setBackground(new java.awt.Color(255, 255, 255));

        txtEscolaridade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEscolaridade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelEscolaridade.setText("        Escolaridade:");

        javax.swing.GroupLayout PainelEscolaridadeLayout = new javax.swing.GroupLayout(PainelEscolaridade);
        PainelEscolaridade.setLayout(PainelEscolaridadeLayout);
        PainelEscolaridadeLayout.setHorizontalGroup(
            PainelEscolaridadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelEscolaridadeLayout.createSequentialGroup()
                .addComponent(LabelEscolaridade)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PainelEscolaridadeLayout.setVerticalGroup(
            PainelEscolaridadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(LabelEscolaridade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtEscolaridade, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
        );

        PainelTelefone.setBackground(new java.awt.Color(255, 255, 255));

        LabelCidade2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelCidade2.setText("Telefone2:");

        LabelCidade3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelCidade3.setText("*  Telefone1:");

        txtCPF1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout PainelTelefoneLayout = new javax.swing.GroupLayout(PainelTelefone);
        PainelTelefone.setLayout(PainelTelefoneLayout);
        PainelTelefoneLayout.setHorizontalGroup(
            PainelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTelefoneLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(LabelCidade3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 289, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(LabelCidade2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtCPF1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(994, 994, 994))
        );
        PainelTelefoneLayout.setVerticalGroup(
            PainelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTelefoneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelTelefoneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(LabelCidade2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCPF1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE))
                    .addComponent(LabelCidade3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("* Campos Obrigatórios");

        BtnSalvarAlteracoes.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes.setText("Salvar Alterações");
        BtnSalvarAlteracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelDadosPacienteLayout = new javax.swing.GroupLayout(PainelDadosPaciente);
        PainelDadosPaciente.setLayout(PainelDadosPacienteLayout);
        PainelDadosPacienteLayout.setHorizontalGroup(
            PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                .addGroup(PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(PainelCidade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(PainelNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PainelIdentificacaoPessoal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(PainelTelefone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(PainelEscolaridade, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PainelReligiao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PainelProfissao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(PainelEndereco, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PainelData, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 3, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPacienteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(327, 327, 327))
        );
        PainelDadosPacienteLayout.setVerticalGroup(
            PainelDadosPacienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPacienteLayout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(PainelIdentificacaoPessoal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(PainelEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelProfissao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelReligiao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelEscolaridade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(PainelTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(jLabel1)
                .addGap(106, 106, 106)
                .addComponent(BtnSalvarAlteracoes, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 3;
        gridBagConstraints.ipady = 186;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(10, 230, 232, 384);
        jEImagePanel2.add(PainelDadosPaciente, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jEImagePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 2051, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jEImagePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1372, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void BtnSalvarAlteracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoesActionPerformed

    }//GEN-LAST:event_BtnSalvarAlteracoesActionPerformed

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
            java.util.logging.Logger.getLogger(ModalPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ModalPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ModalPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ModalPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ModalPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSalvarAlteracoes;
    private com.github.lgooddatepicker.components.DatePicker DataNasc;
    private javax.swing.JLabel LabelCidade;
    private javax.swing.JLabel LabelCidade1;
    private javax.swing.JLabel LabelCidade2;
    private javax.swing.JLabel LabelCidade3;
    private javax.swing.JLabel LabelEscolaridade;
    private javax.swing.JLabel LabelNome;
    private javax.swing.JLabel LabelProfissao;
    private javax.swing.JLabel LabelReligiao;
    private javax.swing.JPanel PainelCidade;
    private javax.swing.JPanel PainelDadosPaciente;
    private javax.swing.JPanel PainelData;
    private javax.swing.JPanel PainelEndereco;
    private javax.swing.JPanel PainelEscolaridade;
    private javax.swing.JPanel PainelIdentificacaoPessoal;
    private javax.swing.JPanel PainelNome;
    private javax.swing.JPanel PainelProfissao;
    private javax.swing.JPanel PainelReligiao;
    private javax.swing.JPanel PainelTelefone;
    private javax.swing.JComboBox<String> Sexo;
    private javax.swing.JTextField TxtEndereco;
    private javax.swing.JTextField TxtProfissao;
    private javax.swing.JComboBox<String> estadocivil;
    private LIB.JEImagePanel jEImagePanel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JTextField txtCPF1;
    private javax.swing.JTextField txtEscolaridade;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtReligiao;
    // End of variables declaration//GEN-END:variables

 public class RoundedBorder implements Border {
        
        private int radius;
        
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }

        @Override
        public boolean isBorderOpaque() {
            return false;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x,y,width-1,height-1,radius,radius);
        }
    }
}
