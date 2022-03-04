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
import model.dao.ADMDAO;
import model.dao.AnotacaoDAO;
import model.dao.ConsultaDAO;
import model.dao.ViewsDAO;
/**
 *
 * @author guimu
 */
public class ExibirAnotacoesAdm extends javax.swing.JFrame {

     public static int codigoanotacao;
    public boolean existe;
    
    public ExibirAnotacoesAdm() {
        initComponents();
        DefaultTableModel dtmPacientes = (DefaultTableModel) JTAnotacoes.getModel();
        TableColumnModel cmod = JTAnotacoes.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTAnotacoes.setRowSorter(new TableRowSorter(dtmPacientes));
        ReadJTable();
    }
 public void ReadJTable() {
        DefaultTableModel model = (DefaultTableModel) JTAnotacoes.getModel();
        model.setNumRows(0);
        ADMDAO vwdao = new ADMDAO();
        //ConsultaDAO cdao = new ConsultaDAO();
        //PacienteDAO pdao = new PacienteDAO();
        //Paciente p = new Paciente();

        for (Vw_Anotacoes_Paciente v : vwdao.ReadALLAnotacoes()) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                v.getAnotacao().getCodAnotacao(),
                v.getPaciente().getNome_Completo(),
                v.getPsicologo().getNome_completo(),
                v.getAnotacao().getAssunto(),
                Validar.fDatetime((Timestamp)  v.getAnotacao().getDataAnotacao())

            });
        }
    }
 public void ReadJTableBusca(String Atributo, String Busca) {

        DefaultTableModel model = (DefaultTableModel) JTAnotacoes.getModel();
        model.setNumRows(0);
        ADMDAO vwdao = new ADMDAO();
        if (Atributo.equals("Nome do Paciente")) {
            Atributo = "Paciente";
        }
        if (Atributo.equals("Data da Anotação")) {
            Atributo = "DataAnotacao";
        }

        for (Vw_Anotacoes_Paciente v : vwdao.BuscaALLAnotacoes(Atributo, Busca)) {

            model.addRow(new Object[]{
                v.getAnotacao().getCodAnotacao(),
                v.getPaciente().getNome_Completo(),
                 v.getPsicologo().getNome_completo(),
                v.getAnotacao().getAssunto(),
                Validar.fDatetime((Timestamp)  v.getAnotacao().getDataAnotacao())

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
        BtnAlterarAnotacao = new javax.swing.JButton();
        BtnExcluirAnotacao = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JCBAtributo = new javax.swing.JComboBox<>();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAnotacoes = new javax.swing.JTable();

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

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(59, 131, 117));
        jLabel7.setText("Anotações da Consulta");

        LabelNomePaciente.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelNomePaciente.setText("Nome do Paciente :");

        LabelNome5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelNome5.setText(" Variável Nome do Paciente");

        LabelAssunto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelAssunto.setText("Assunto:");

        txtAssunto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelAssunto1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        LabelAssunto1.setText("Texto:");

        txtTexto.setColumns(20);
        txtTexto.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtTexto.setRows(5);
        jScrollPane2.setViewportView(txtTexto);

        BtnCancelarAnotacao.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelarAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelarAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelarAnotacao.setText("Cancelar");
        BtnCancelarAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelarAnotacaoActionPerformed(evt);
            }
        });

        BtnSalvarAlteracoesAnotacao.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoesAnotacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoesAnotacao.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoesAnotacao.setText("Salvar Alterações");
        BtnSalvarAlteracoesAnotacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoesAnotacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ModalAnotacaoLayout = new javax.swing.GroupLayout(ModalAnotacao.getContentPane());
        ModalAnotacao.getContentPane().setLayout(ModalAnotacaoLayout);
        ModalAnotacaoLayout.setHorizontalGroup(
            ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(ModalAnotacaoLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ModalAnotacaoLayout.createSequentialGroup()
                        .addGroup(ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelNomePaciente)
                            .addComponent(LabelAssunto))
                        .addGap(18, 18, 18)
                        .addComponent(LabelNome5))
                    .addComponent(txtAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(ModalAnotacaoLayout.createSequentialGroup()
                            .addComponent(BtnCancelarAnotacao)
                            .addGap(18, 18, 18)
                            .addComponent(BtnSalvarAlteracoesAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(LabelAssunto1)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(ModalAnotacaoLayout.createSequentialGroup()
                .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        ModalAnotacaoLayout.setVerticalGroup(
            ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ModalAnotacaoLayout.createSequentialGroup()
                .addGroup(ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jEImagePanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ModalAnotacaoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addGroup(ModalAnotacaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnCancelarAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoesAnotacao, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1080, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);

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
        BtnManterPsicologo.setText("Meus Dados");
        BtnManterPsicologo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPsicologoActionPerformed(evt);
            }
        });

        BtnExibiranamneses.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibiranamneses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibiranamneses.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibiranamneses.setText("Exibir Anamneses");
        BtnExibiranamneses.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnExibiranamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibiranamnesesActionPerformed(evt);
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

        jLabel2.setText("Buscar Anotação Por");

        JCBAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Nome do Paciente", "Assunto", "Data da Anotação" }));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        JTAnotacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Anotação", "Paciente", "Psicologo", "Assunto", "Data da Anotação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JTAnotacoes);

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addComponent(BtnAlterarAnotacao)
                .addGap(18, 18, 18)
                .addComponent(BtnExcluirAnotacao)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(97, 97, 97)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnAlterarAnotacao)
                    .addComponent(BtnExcluirAnotacao)
                    .addComponent(jLabel2)
                    .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jEImagePanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVoltarActionPerformed
        TelaPrincipalAdm mp1 = new TelaPrincipalAdm();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnVoltarActionPerformed

    private void BtnManterPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPacienteActionPerformed
        // TODO add your handling code here:
        ManterPacienteAdm mp1 = new ManterPacienteAdm();
        Util.SizeJanela(mp1);
        this.dispose();
    }//GEN-LAST:event_BtnManterPacienteActionPerformed

    private void BtnManterConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterConsultaActionPerformed
        // TODO add your handling code here:
//        CadastrarConsulta2 mc = new CadastrarConsulta2();
//        Util.SizeJanela(mc);
//        this.dispose();
    }//GEN-LAST:event_BtnManterConsultaActionPerformed

    private void BtnManterPsicologoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnManterPsicologoActionPerformed
        // TODO add your handling code here:
        ManterPsicologoAdm mp = new ManterPsicologoAdm();
        Util.SizeJanela(mp);
    
        this.dispose();
    }//GEN-LAST:event_BtnManterPsicologoActionPerformed

    private void BtnExibiranamnesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibiranamnesesActionPerformed
        // TODO add your handling code here:
        ExibirAnamnesesAdm ma = new ExibirAnamnesesAdm();
        Util.SizeJanela(ma);
        this.dispose();
    }//GEN-LAST:event_BtnExibiranamnesesActionPerformed

    private void BtnExibirAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacaoActionPerformed
        // TODO add your handling code here:
//        ExibirAnotacoesAdm ea = new ExibirAnotacoesAdm();
//        Util.SizeJanela(ea);
//        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacaoActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        // TODO add your handling code here:
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

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
            ModalAnotacao.setSize(862, 870);
            LabelNome5.setText(nome);
            ModalAnotacao.setModal(true);
            ModalAnotacao.setLocationRelativeTo(null);
            ModalAnotacao.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma anotacao para alterar");
        }
    }//GEN-LAST:event_BtnAlterarAnotacaoActionPerformed

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
            ReadJTable();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione um paciente para excluir");
        }
    }//GEN-LAST:event_BtnExcluirAnotacaoActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        //System.out.println(JCBAtributo.getSelectedIndex());
        this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

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
        ReadJTable();
    }//GEN-LAST:event_BtnSalvarAlteracoesAnotacaoActionPerformed

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
            java.util.logging.Logger.getLogger(ExibirAnotacoesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExibirAnotacoesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExibirAnotacoesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExibirAnotacoesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ExibirAnotacoesAdm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAlterarAnotacao;
    private javax.swing.JButton BtnCancelarAnotacao;
    private javax.swing.JButton BtnExcluirAnotacao;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnExibiranamneses;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSalvarAlteracoesAnotacao;
    private javax.swing.JButton BtnVoltar;
    private javax.swing.JComboBox<String> JCBAtributo;
    private javax.swing.JTable JTAnotacoes;
    private javax.swing.JLabel LabelAssunto;
    private javax.swing.JLabel LabelAssunto1;
    private javax.swing.JLabel LabelNome5;
    private javax.swing.JLabel LabelNomePaciente;
    private javax.swing.JDialog ModalAnotacao;
    private javax.swing.JPanel PainelIdentificacaoPessoal3;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JButton btnBuscar;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel4;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtAssunto;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextArea txtTexto;
    // End of variables declaration//GEN-END:variables
}
