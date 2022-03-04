/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view_adm.MenuPrincipal;
import view.*;
import util.Util;
import Validacoes.Validar;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import model.bean.Anamnese;
import model.bean.Consulta;
import model.bean.Paciente;
import model.bean.Psicologo;
import model.bean.Vw_Anamnese_Paciente;
import model.dao.ADMDAO;
import model.dao.AnamneseDAO;
import model.dao.ConsultaDAO;
import model.dao.PacienteDAO;
import model.dao.ViewsDAO;
import static view.ExibirAnamnesesPaciente.codpaciente;
/**
 *
 * @author guimu
 */
public class ExibirAnamnesesAdm extends javax.swing.JFrame {

   public boolean existe = false;
          public static int codigoanamnese;
    public ExibirAnamnesesAdm() {
        initComponents();
         DefaultTableModel dtmPacientes = (DefaultTableModel) JTAnamneses.getModel();
        TableColumnModel cmod = JTAnamneses.getColumnModel();
        cmod.removeColumn(cmod.getColumn(0));
        JTAnamneses.setRowSorter(new TableRowSorter(dtmPacientes));
        ReadJTable();
    }
public boolean readcampos(int cod) {
        Anamnese a = new Anamnese();
        AnamneseDAO dao = new AnamneseDAO();
        a = dao.ReadAnamnese(cod);
        int codanamnese = a.getCodAnamnese();
        if (codanamnese != 0) {

            txtQueixaPrincipal.setText(a.getQueixaPrincipal());
            DataInicio.setText((String) Validar.fDataNascBD((Date) a.getInicioDaQueixa()));
            SubitaOuProgressiva.setSelectedItem(a.getSubitaOuProgressiva());
            txtQueixaSecundaria.setText(a.getQueixasSecundarias());
            txtHistoricoFamiliar.setText(a.getHistoricoFamiliar());
            txtDiagnostico.setText(a.getDiagnostico());
            txtEncaminhamento.setText(a.getEncaminhamento());
            txtDoencasConhecidas.setText(a.getDoencasConhecidas());
            txtMedicamentosUtilizados.setText(a.getMedicamentosUtilizados());
            txtOqueMudou.setText(a.getOqueMudou());
            txtSintomas.setText(a.getSintomas());
            txtComoComecou.setText(a.getComoComecou());
            JCBQueixasCognitivas.setSelectedItem(a.getQueixasCognitivas());
            JCBQueixasAfetivoEmocionais.setSelectedItem(a.getQueixasAfetivoEmocionais());
            JCBPsicomotricidade.setSelectedItem(a.getPsicomotricidade());
            return true;
        }
        return false;
    }
private void ReadJTable() {
        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
        model.setNumRows(0);
        ADMDAO vwdao = new ADMDAO();
        //ConsultaDAO cdao = new ConsultaDAO();
        //PacienteDAO pdao = new PacienteDAO();
        //Paciente p = new Paciente();

        for (Vw_Anamnese_Paciente v : vwdao.ReadALLAnamneses()) {
            //p = pdao.ReadPaciente(c.getPaciente().getCodPaciente());
            model.addRow(new Object[]{
                v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
                v.getPsicologo().getNome_completo(),
                v.getAnamnese().getDiagnostico(),
                Validar.fDatetime((Timestamp)  v.getConsulta().getDataConsulta())
               

            });
        }
    }

    public void ReadJTableBusca(String Atributo, String Busca) {

        DefaultTableModel model = (DefaultTableModel) JTAnamneses.getModel();
        model.setNumRows(0);
        if (Atributo.equals("Nome do Paciente")) {
            Atributo = "Paciente";
        }
        if (Atributo.equals("Diagnóstico")) {
            Atributo = "Diagnostico";
        }
        if (Atributo.equals("Data da Consulta")) {
            Atributo = "DataConsulta";
        }
        ADMDAO vwdao = new ADMDAO();

        for (Vw_Anamnese_Paciente v : vwdao.BuscaALLAnamneses(Atributo, Busca)) {

            model.addRow(new Object[]{
               v.getAnamnese().getCodAnamnese(),
                v.getPaciente().getNome_Completo(),
                v.getPsicologo().getNome_completo(),
                v.getAnamnese().getDiagnostico(),
                Validar.fDatetime((Timestamp)  v.getConsulta().getDataConsulta())

            });
        }
    }
      private void Alterar(int cod) {
        Anamnese a = new Anamnese();
        AnamneseDAO dao = new AnamneseDAO();
        Anamnese a2 = new Anamnese();
        AnamneseDAO dao2 = new AnamneseDAO();
        a2 = dao2.ReadAnamnese(cod);
        a.setCodAnamnese(a2.getCodAnamnese());
        if (a.getCodAnamnese() != 0) {
            a.setQueixaPrincipal(txtQueixaPrincipal.getText());
            a.setSubitaOuProgressiva((String) SubitaOuProgressiva.getSelectedItem());

            //java.util.Date date = new java.util.Date();
            Object param = DataInicio.getDate();
            a.setInicioDaQueixa(param);
            a.setQueixasSecundarias(txtQueixaSecundaria.getText());
            a.setHistoricoFamiliar(txtHistoricoFamiliar.getText());
            a.setDiagnostico(txtDiagnostico.getText());
            a.setEncaminhamento(txtEncaminhamento.getText());
            a.setDoencasConhecidas(txtDoencasConhecidas.getText());
            a.setMedicamentosUtilizados(txtMedicamentosUtilizados.getText());
            a.getConsulta().setCodConsulta(a2.getConsulta().getCodConsulta());
            a.setOqueMudou(txtOqueMudou.getText());
            a.setSintomas(txtSintomas.getText());
            a.setComoComecou(txtComoComecou.getText());
            a.setQueixasCognitivas((String) JCBQueixasCognitivas.getSelectedItem());
            a.setQueixasAfetivoEmocionais((String) JCBQueixasAfetivoEmocionais.getSelectedItem());
            a.setPsicomotricidade((String) JCBPsicomotricidade.getSelectedItem());

            boolean sucesso = dao.Update(a);
            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Anamnese Alterada Com Sucesso");
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
        PainelDadosPaciente4 = new javax.swing.JPanel();
        PainelIdentificacaoPessoal4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        BtnSalvarAlteracoes5 = new javax.swing.JButton();
        BtnCancelar2 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        SubitaOuProgressiva = new javax.swing.JComboBox<>();
        labelInicioQueixa1 = new javax.swing.JLabel();
        JCBPsicomotricidade = new javax.swing.JComboBox<>();
        labelInicioQueixa = new javax.swing.JLabel();
        JCBQueixasAfetivoEmocionais = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        JCBQueixasCognitivas = new javax.swing.JComboBox<>();
        LabelEmail1 = new javax.swing.JLabel();
        txtComoComecou = new javax.swing.JTextField();
        LabelNome3 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtQueixaSecundaria = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        txtDiagnostico = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        txtHistoricoFamiliar = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        txtDoencasConhecidas = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        txtSintomas = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        txtMedicamentosUtilizados = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtOqueMudou = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        txtEncaminhamento = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        DataInicio = new com.github.lgooddatepicker.components.DatePicker();
        BtnCancelar3 = new javax.swing.JButton();
        BtnSalvarAlteracoes3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtQueixaPrincipal = new javax.swing.JTextField();
        jEImagePanel2 = new LIB.JEImagePanel();
        jLabel5 = new javax.swing.JLabel();
        LabelModalAnamnese = new javax.swing.JLabel();
        jPanel1 = new JPanel();
        jLabel1 = new javax.swing.JLabel();
        PainelMenu = new javax.swing.JPanel();
        BtnVoltar = new javax.swing.JButton();
        BtnManterPaciente = new javax.swing.JButton();
        BtnManterConsulta = new javax.swing.JButton();
        BtnManterPsicologo = new javax.swing.JButton();
        BtnExibiranamneses = new javax.swing.JButton();
        BtnExibirAnotacao = new javax.swing.JButton();
        BtnSair = new javax.swing.JButton();
        jEImagePanel1 = new LIB.JEImagePanel();
        btnalterar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        JCBAtributo = new javax.swing.JComboBox<>();
        txtBusca = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTAnamneses = new javax.swing.JTable();

        ModalAnamnese2.setResizable(false);

        PainelDadosPaciente4.setBackground(new java.awt.Color(255, 255, 255));
        PainelDadosPaciente4.setPreferredSize(new java.awt.Dimension(300, 1000));

        PainelIdentificacaoPessoal4.setBackground(new java.awt.Color(59, 131, 117));

        javax.swing.GroupLayout PainelIdentificacaoPessoal4Layout = new javax.swing.GroupLayout(PainelIdentificacaoPessoal4);
        PainelIdentificacaoPessoal4.setLayout(PainelIdentificacaoPessoal4Layout);
        PainelIdentificacaoPessoal4Layout.setHorizontalGroup(
            PainelIdentificacaoPessoal4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        PainelIdentificacaoPessoal4Layout.setVerticalGroup(
            PainelIdentificacaoPessoal4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jLabel7.setText("* Campos Obrigatórios");
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        BtnSalvarAlteracoes5.setText("Salvar Alterações");
        BtnSalvarAlteracoes5.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes5.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes5ActionPerformed(evt);
            }
        });

        BtnCancelar2.setText("Cancelar");
        BtnCancelar2.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar2.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar2ActionPerformed(evt);
            }
        });

        jLabel29.setText("*Subita ou Progressiva:");
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        SubitaOuProgressiva.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Subita", "Progressiva"}));
        SubitaOuProgressiva.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelInicioQueixa1.setText("*  Psicomotricidade: ");
        labelInicioQueixa1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBPsicomotricidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Lento", "Agitado"}));
        JCBPsicomotricidade.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        labelInicioQueixa.setText("*Queixas Afetivo-emocionais: ");
        labelInicioQueixa.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBQueixasAfetivoEmocionais.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nenhuma","Volição", "Afeto", "Ansiedade", "Medo", "Culpa", "Raiva", "Luto", "Desanimo" }));
        JCBQueixasAfetivoEmocionais.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel26.setText("*  Queixas Cognitivas:");
        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        JCBQueixasCognitivas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Nenhuma", "Integridade Sensorial","Percepção", "Atenção", "Memória" }));
        JCBQueixasCognitivas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelEmail1.setText("  Queixa Secundária:");
        LabelEmail1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtComoComecou.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        LabelNome3.setText("*Queixa Principal:");
        LabelNome3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel30.setText("  Como começou:");
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtQueixaSecundaria.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel31.setText("  Diagnóstico:");
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtDiagnostico.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel32.setText("  Histórico Familiar:");
        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtHistoricoFamiliar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel33.setText("  Doenças Conhecidas:");
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtDoencasConhecidas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel34.setText("  Sintomas:");
        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtSintomas.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel35.setText("  Medicamentos Utilizados:");
        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtMedicamentosUtilizados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel36.setText("  O que mudou:");
        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtOqueMudou.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel37.setText("  Encaminhamento:");
        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtEncaminhamento.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel39.setText("  Data de Início:");
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        DataInicio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        DataInicio.setPreferredSize(new java.awt.Dimension(160, 17));

        BtnCancelar3.setText("Cancelar");
        BtnCancelar3.setBackground(new java.awt.Color(255, 153, 153));
        BtnCancelar3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnCancelar3.setForeground(new java.awt.Color(255, 255, 255));
        BtnCancelar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCancelar3ActionPerformed(evt);
            }
        });

        BtnSalvarAlteracoes3.setText("Salvar Alterações");
        BtnSalvarAlteracoes3.setBackground(new java.awt.Color(0, 112, 186));
        BtnSalvarAlteracoes3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnSalvarAlteracoes3.setForeground(new java.awt.Color(255, 255, 255));
        BtnSalvarAlteracoes3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalvarAlteracoes3ActionPerformed(evt);
            }
        });

        jLabel4.setText("* Campos Obrigatórios");
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        txtQueixaPrincipal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jEImagePanel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/simboloma.png"))); // NOI18N

        javax.swing.GroupLayout jEImagePanel2Layout = new javax.swing.GroupLayout(jEImagePanel2);
        jEImagePanel2.setLayout(jEImagePanel2Layout);
        jEImagePanel2Layout.setHorizontalGroup(
            jEImagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE)
        );
        jEImagePanel2Layout.setVerticalGroup(
            jEImagePanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel5.setText("Anamnese Psicológica");
        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(59, 131, 117));

        LabelModalAnamnese.setText("jLabel7");
        LabelModalAnamnese.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 14)); // NOI18N

        javax.swing.GroupLayout PainelDadosPaciente4Layout = new javax.swing.GroupLayout(PainelDadosPaciente4);
        PainelDadosPaciente4.setLayout(PainelDadosPaciente4Layout);
        PainelDadosPaciente4Layout.setHorizontalGroup(
            PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PainelIdentificacaoPessoal4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnCancelar2)
                .addGap(42, 42, 42)
                .addComponent(BtnSalvarAlteracoes5, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addComponent(jEImagePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75))
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel7))
                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                            .addGap(55, 55, 55)
                            .addComponent(LabelNome3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtQueixaPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                            .addGap(56, 56, 56)
                            .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelInicioQueixa)
                                        .addComponent(jLabel29))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addComponent(JCBQueixasAfetivoEmocionais, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jLabel26))
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addComponent(SubitaOuProgressiva, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(labelInicioQueixa1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(JCBPsicomotricidade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JCBQueixasCognitivas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                        .addComponent(LabelEmail1)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtQueixaSecundaria, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel31)
                                        .addComponent(jLabel30)
                                        .addComponent(jLabel33)
                                        .addComponent(jLabel34)
                                        .addComponent(jLabel35)
                                        .addComponent(jLabel36)
                                        .addComponent(jLabel39)
                                        .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jLabel37, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jLabel4))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtComoComecou)
                                        .addComponent(txtDiagnostico)
                                        .addComponent(txtHistoricoFamiliar)
                                        .addComponent(txtDoencasConhecidas)
                                        .addComponent(txtSintomas)
                                        .addComponent(txtMedicamentosUtilizados)
                                        .addComponent(txtOqueMudou)
                                        .addComponent(txtEncaminhamento)
                                        .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                                            .addComponent(DataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(0, 0, Short.MAX_VALUE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelDadosPaciente4Layout.createSequentialGroup()
                                    .addComponent(BtnCancelar3)
                                    .addGap(18, 18, 18)
                                    .addComponent(BtnSalvarAlteracoes3)))))
                    .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(LabelModalAnamnese)))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        PainelDadosPaciente4Layout.setVerticalGroup(
            PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelDadosPaciente4Layout.createSequentialGroup()
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jEImagePanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(PainelIdentificacaoPessoal4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(LabelModalAnamnese)
                .addGap(28, 28, 28)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelNome3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQueixaPrincipal))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelEmail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtQueixaSecundaria))
                .addGap(18, 18, 18)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JCBQueixasAfetivoEmocionais, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelInicioQueixa)
                        .addComponent(jLabel26)
                        .addComponent(JCBQueixasCognitivas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(SubitaOuProgressiva, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInicioQueixa1)
                    .addComponent(JCBPsicomotricidade, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(txtComoComecou))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDiagnostico)
                    .addComponent(jLabel31))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEncaminhamento)
                    .addComponent(jLabel37))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHistoricoFamiliar)
                    .addComponent(jLabel32))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDoencasConhecidas)
                    .addComponent(jLabel33))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSintomas)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMedicamentosUtilizados)
                    .addComponent(jLabel35))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOqueMudou)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(DataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnCancelar3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnSalvarAlteracoes3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(480, 480, 480)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelDadosPaciente4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnCancelar2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnSalvarAlteracoes5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout ModalAnamnese2Layout = new javax.swing.GroupLayout(ModalAnamnese2.getContentPane());
        ModalAnamnese2.getContentPane().setLayout(ModalAnamnese2Layout);
        ModalAnamnese2Layout.setHorizontalGroup(
            ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 984, Short.MAX_VALUE)
            .addGroup(ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(PainelDadosPaciente4, javax.swing.GroupLayout.DEFAULT_SIZE, 984, Short.MAX_VALUE))
        );
        ModalAnamnese2Layout.setVerticalGroup(
            ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 924, Short.MAX_VALUE)
            .addGroup(ModalAnamnese2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ModalAnamnese2Layout.createSequentialGroup()
                    .addComponent(PainelDadosPaciente4, javax.swing.GroupLayout.PREFERRED_SIZE, 924, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(59, 131, 117));
        jPanel1.setForeground(new java.awt.Color(59, 131, 117));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 89));

        jLabel1.setText("Exibir Anamneses");
        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1920, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(580, 580, 580)
                    .addComponent(jLabel1)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(30, 30, 30)
                    .addComponent(jLabel1)
                    .addContainerGap(30, Short.MAX_VALUE)))
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

        BtnManterPaciente.setText("Manter Pacientes");
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

        BtnManterPsicologo.setText("Meus Dados");
        BtnManterPsicologo.setBackground(new java.awt.Color(102, 102, 102));
        BtnManterPsicologo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnManterPsicologo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnManterPsicologo.setForeground(new java.awt.Color(255, 255, 255));
        BtnManterPsicologo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnManterPsicologoActionPerformed(evt);
            }
        });

        BtnExibiranamneses.setText("Exibir Anamneses");
        BtnExibiranamneses.setBackground(new java.awt.Color(102, 102, 102));
        BtnExibiranamneses.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BtnExibiranamneses.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        BtnExibiranamneses.setForeground(new java.awt.Color(255, 255, 255));
        BtnExibiranamneses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnExibiranamnesesActionPerformed(evt);
            }
        });

        BtnExibirAnotacao.setText("Exibir Anotações");
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

        btnalterar.setText("Alterar");
        btnalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalterarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel2.setText("Buscar Anamnese Por");

        JCBAtributo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome do Paciente", "Diagnóstico", "Data da Consulta" }));

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        JTAnamneses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Anamnese", "Paciente", "Psicólogo", "Diagnostico", "Data da Consulta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JTAnamneses);

        javax.swing.GroupLayout jEImagePanel1Layout = new javax.swing.GroupLayout(jEImagePanel1);
        jEImagePanel1.setLayout(jEImagePanel1Layout);
        jEImagePanel1Layout.setHorizontalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jEImagePanel1Layout.createSequentialGroup()
                        .addComponent(btnalterar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jEImagePanel1Layout.setVerticalGroup(
            jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jEImagePanel1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jEImagePanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnalterar)
                    .addComponent(btnExcluir)
                    .addComponent(jLabel2)
                    .addComponent(JCBAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
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
  
    }//GEN-LAST:event_BtnExibiranamnesesActionPerformed

    private void BtnExibirAnotacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnExibirAnotacaoActionPerformed
        // TODO add your handling code here:
        ExibirAnotacoesAdm ea = new ExibirAnotacoesAdm();
        Util.SizeJanela(ea);
        this.dispose();
    }//GEN-LAST:event_BtnExibirAnotacaoActionPerformed

    private void BtnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSairActionPerformed
        // TODO add your handling code here:
        TelaLogin tl = new TelaLogin();
        tl.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_BtnSairActionPerformed

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
            if (existe) {
                LabelModalAnamnese.setText("Lendo dados da Anamnese Cadastrada na consulta");
            } else {
                LabelModalAnamnese.setText(" Cadastrar anamnese na consulta");
            }

            ModalAnamnese2.setSize(1039, 967);
            ModalAnamnese2.setModal(true);
            ModalAnamnese2.setLocationRelativeTo(null);
            ModalAnamnese2.setVisible(true);
        }else {
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
            boolean sucesso = adao.Delete(a);

            if (sucesso) {
                JOptionPane.showMessageDialog(this, "Anamnese Apagada com Sucesso");

            }

        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma anamnese para excluir");
        }
        ReadJTable();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:

        //System.out.println(JCBAtributo.getSelectedIndex());
        this.ReadJTableBusca((String) JCBAtributo.getSelectedItem(), txtBusca.getText());
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void BtnSalvarAlteracoes5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnSalvarAlteracoes5ActionPerformed

    private void BtnCancelar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BtnCancelar2ActionPerformed

    private void BtnCancelar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCancelar3ActionPerformed
        ModalAnamnese2.dispose();
    }//GEN-LAST:event_BtnCancelar3ActionPerformed

    private void BtnSalvarAlteracoes3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalvarAlteracoes3ActionPerformed

        if (existe) {
            Alterar(codigoanamnese);
            LabelModalAnamnese.setText("Lendo dados da Anamnese Cadastrada na consulta");
        }
    }//GEN-LAST:event_BtnSalvarAlteracoes3ActionPerformed

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
            java.util.logging.Logger.getLogger(ExibirAnamnesesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamnesesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamnesesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ExibirAnamnesesAdm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new ExibirAnamnesesAdm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnCancelar2;
    private javax.swing.JButton BtnCancelar3;
    private javax.swing.JButton BtnExibirAnotacao;
    private javax.swing.JButton BtnExibiranamneses;
    private javax.swing.JButton BtnManterConsulta;
    private javax.swing.JButton BtnManterPaciente;
    private javax.swing.JButton BtnManterPsicologo;
    private javax.swing.JButton BtnSair;
    private javax.swing.JButton BtnSalvarAlteracoes3;
    private javax.swing.JButton BtnSalvarAlteracoes5;
    private javax.swing.JButton BtnVoltar;
    private com.github.lgooddatepicker.components.DatePicker DataInicio;
    private javax.swing.JComboBox<String> JCBAtributo;
    private javax.swing.JComboBox<String> JCBPsicomotricidade;
    private javax.swing.JComboBox<String> JCBQueixasAfetivoEmocionais;
    private javax.swing.JComboBox<String> JCBQueixasCognitivas;
    private javax.swing.JTable JTAnamneses;
    private javax.swing.JLabel LabelEmail1;
    private javax.swing.JLabel LabelModalAnamnese;
    private javax.swing.JLabel LabelNome3;
    private javax.swing.JDialog ModalAnamnese2;
    private javax.swing.JPanel PainelDadosPaciente4;
    private javax.swing.JPanel PainelIdentificacaoPessoal4;
    private javax.swing.JPanel PainelMenu;
    private javax.swing.JComboBox<String> SubitaOuProgressiva;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnalterar;
    private LIB.JEImagePanel jEImagePanel1;
    private LIB.JEImagePanel jEImagePanel2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelInicioQueixa;
    private javax.swing.JLabel labelInicioQueixa1;
    private javax.swing.JTextField txtBusca;
    private javax.swing.JTextField txtComoComecou;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtDoencasConhecidas;
    private javax.swing.JTextField txtEncaminhamento;
    private javax.swing.JTextField txtHistoricoFamiliar;
    private javax.swing.JTextField txtMedicamentosUtilizados;
    private javax.swing.JTextField txtOqueMudou;
    private javax.swing.JTextField txtQueixaPrincipal;
    private javax.swing.JTextField txtQueixaSecundaria;
    private javax.swing.JTextField txtSintomas;
    // End of variables declaration//GEN-END:variables
}
