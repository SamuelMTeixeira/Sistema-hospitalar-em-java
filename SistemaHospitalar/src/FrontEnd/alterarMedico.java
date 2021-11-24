/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import BackEnd.ConexaoSQL;
import Padroes.FormatacaoDeCampos;
import Padroes.Mensagens_Prontas;
import java.awt.Color;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author samuel
 */
public class alterarMedico extends javax.swing.JFrame {

    private ConexaoSQL         conexao;
    private Mensagens_Prontas  msg;
    private FormatacaoDeCampos formatar; 
    private JTable             grdSalaEspera;
    private DefaultListModel   dlm;
    private DefaultTableModel  dtm;
    private String             codMedicoSelecionado;

    /*--------------------------------------------*/
            //FUNÇÕES E PROCEDIMENTOS SOBRE O CADASTRO DE PACIENTES
        
    private void setPreencherComboBox (JComboBox cb, String table){
        try {
            String SQL = "SELECT DISTINCT c.nome FROM "+table+" c order by c.nome";
            this.conexao.setResultSet(SQL);
            cb.removeAllItems();
            if(this.conexao.getResultSet().first() ){
                do {
                    cb.addItem(this.conexao.getResultSet().getString("c.nome"));
                }while(this.conexao.getResultSet().next());

            }
            cb.setSelectedIndex(-1);
        }
        catch (SQLException e){
            msg.problemMessage("erro", "");
        }
        
    }
    
    private void setPreencherListaOpcoes (JList listaOpcoes){
        // DEFINE O MODELO DA LISTA DE OPÇÕES DO TEXTFIELD
        dlm = new DefaultListModel();
        listaOpcoes.setModel(dlm);
        
        
        try {
            String SQL = "SELECT DISTINCT m.CRM AS CRM FROM medico m where CRM like '"+tfEscolhaMedico.getText()+"%' LIMIT 4";
            this.conexao.setResultSet(SQL);
            dlm.removeAllElements();
            
            if(this.conexao.getResultSet().first() ){
                do {
                    dlm.addElement(this.conexao.getResultSet().getString("CRM"));     
                }while(this.conexao.getResultSet().next() );

            }
        }
        catch (SQLException e){
            msg.problemMessage("erro", "");
        }
        
    }

    private void setPreencherDados (){
        if(listaMedico.getSelectedIndex() >= 0) {
            // PREENCHE OS DADOS DO PACIENTE
            try {
                this.conexao.setResultSet("SELECT DISTINCT * FROM medico where CRM like '"+String.valueOf(listaMedico.getSelectedValue())+"%'");

                if(this.conexao.getResultSet().first() ){
                    do {
                       codMedicoSelecionado = this.conexao.getResultSet().getString("codMedico");
                        
                        tfNome.setText(this.conexao.getResultSet().getString("nome"));
                        tfNome.setForeground(Color.black);
                        
                        tfCRM.setText(this.conexao.getResultSet().getString("CRM"));
                        tfCRM.setForeground(Color.black);
                        
                        
                        listaMedico.setVisible(false);
                       
                    }while(this.conexao.getResultSet().next() );
                    tfEscolhaMedico.setText(listaMedico.getSelectedValue());
                    this.setBlockCampos(true);
                }
            }
            catch (SQLException e){
                msg.problemMessage("erro", "");
            }
            
            
        }
    }
   
    private void setBlockCampos (boolean val){
        tfNome.setEditable(val);
        tfCRM.setEditable(val);
    }
    
    public void setAtualizarSalaEspera (String filtro){
        dtm = (DefaultTableModel) grdSalaEspera.getModel();
        
        // DEIXA A TABELA SEM NENHUM LINHA (INICIALMENTE)
        while (dtm.getRowCount() > 0 )
            dtm.removeRow(0);
        
        // PREENCHE A TABELA
        try {
            /* <<< LINHA DE COMANDO SQL >>> */
            String SQL = "SELECT DISTINCT p.nome AS paciente, m.nome AS medico, l.horarioAtendimento AS horario FROM listaEspera l INNER JOIN agendamento a  ON a.codAgendamento = l.codAgendamento INNER JOIN paciente p ON p.codPaciente = a.codPaciente  INNER JOIN medico m   ON m.codMedico = a.codMedico WHERE a.diaAtendimento = curdate() WHERE a.diaAtendimento = curdate() ORDER BY "+filtro;
            
            /* <<< EXECUTA LINHA DE COMANDO SQL >>> */
            this.conexao.setResultSet(SQL);
            
            /* <<< PEGA OS VALORES DA TELA, DO PRIMEIRO PARA O ULTIMO >>> */
            if (this.conexao.getResultSet().first() ){
                do {
                    dtm.addRow  (
                            new Object[] {
                                this.conexao.getResultSet().getString("paciente"),
                                this.conexao.getResultSet().getString("horario"),
                                this.conexao.getResultSet().getString("medico")
                            }
                    );
              
                } while (this.conexao.getResultSet().next());
            } 
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    /*--------------------------------------------*/
    
 
    public alterarMedico(JTable grd) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        this.conexao = new ConexaoSQL();
       
        this.msg = new Mensagens_Prontas();
        this.formatar = new FormatacaoDeCampos();
        this.grdSalaEspera = grd;
        this.setPreencherComboBox(cbEspecialidade, "especialidade");
  
        
        mbMenu.setBackground(new java.awt.Color(65,105,225));
        txtTituloAcao.requestFocus();

        codMedicoSelecionado = "nulo";
        
        camadaDados.setOpaque(false);
        PnlDados.setOpaque(false);
        listaMedico.setVisible(false);
        this.setBlockCampos(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        pnlMenuLateral = new javax.swing.JPanel();
        iconeCruz = new javax.swing.JLabel();
        txtNomeHospital = new javax.swing.JLabel();
        pnlMenu1 = new javax.swing.JPanel();
        txtIncluirPaciente = new javax.swing.JLabel();
        pnlMenu2 = new javax.swing.JPanel();
        txtAlterarPaciente = new javax.swing.JLabel();
        pnlMenu3 = new javax.swing.JPanel();
        txtExcluirPaciente = new javax.swing.JLabel();
        txtTituloAcao = new javax.swing.JLabel();
        sep1 = new javax.swing.JSeparator();
        sep2 = new javax.swing.JSeparator();
        camadaDadosPrincipal = new javax.swing.JLayeredPane();
        tfEscolhaMedico = new javax.swing.JTextField();
        listaMedico = new javax.swing.JList<>();
        camadaDados = new javax.swing.JLayeredPane();
        PnlDados = new javax.swing.JPanel();
        tfCRM = new javax.swing.JTextField();
        tfNome = new javax.swing.JTextField();
        txtEspecialidade = new javax.swing.JLabel();
        cbEspecialidade = new javax.swing.JComboBox<>();
        txtEscolhaPaciente = new javax.swing.JLabel();
        mbMenu = new javax.swing.JMenuBar();
        mnTelaInicial = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Hospitalar");

        pnlPrincipal.setBackground(new java.awt.Color(164, 211, 238));

        pnlMenuLateral.setBackground(new java.awt.Color(65, 105, 225));
        pnlMenuLateral.setForeground(new java.awt.Color(65, 105, 225));

        iconeCruz.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        iconeCruz.setForeground(new java.awt.Color(255, 0, 0));
        iconeCruz.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        iconeCruz.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/cruz.png"))); // NOI18N

        txtNomeHospital.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtNomeHospital.setForeground(new java.awt.Color(255, 255, 255));
        txtNomeHospital.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtNomeHospital.setText("HOSPITAL DE TEÓFILO OTONI");

        pnlMenu1.setBackground(new java.awt.Color(72, 118, 255));
        pnlMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenu1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlMenu1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlMenu1MouseExited(evt);
            }
        });

        txtIncluirPaciente.setBackground(new java.awt.Color(135, 206, 235));
        txtIncluirPaciente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtIncluirPaciente.setForeground(new java.awt.Color(255, 255, 255));
        txtIncluirPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/addUser.png"))); // NOI18N
        txtIncluirPaciente.setText("CADASTRAR UM MÉDICO");

        javax.swing.GroupLayout pnlMenu1Layout = new javax.swing.GroupLayout(pnlMenu1);
        pnlMenu1.setLayout(pnlMenu1Layout);
        pnlMenu1Layout.setHorizontalGroup(
            pnlMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIncluirPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMenu1Layout.setVerticalGroup(
            pnlMenu1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtIncluirPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenu2.setBackground(new java.awt.Color(72, 118, 255));
        pnlMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenu2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlMenu2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlMenu2MouseExited(evt);
            }
        });

        txtAlterarPaciente.setBackground(new java.awt.Color(135, 206, 235));
        txtAlterarPaciente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtAlterarPaciente.setForeground(new java.awt.Color(255, 255, 102));
        txtAlterarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/alterUser.png"))); // NOI18N
        txtAlterarPaciente.setText("CONCLUIR ALTERAÇÃO DOS DADOS");

        javax.swing.GroupLayout pnlMenu2Layout = new javax.swing.GroupLayout(pnlMenu2);
        pnlMenu2.setLayout(pnlMenu2Layout);
        pnlMenu2Layout.setHorizontalGroup(
            pnlMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAlterarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMenu2Layout.setVerticalGroup(
            pnlMenu2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtAlterarPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlMenu3.setBackground(new java.awt.Color(72, 118, 255));
        pnlMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlMenu3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlMenu3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlMenu3MouseExited(evt);
            }
        });

        txtExcluirPaciente.setBackground(new java.awt.Color(135, 206, 235));
        txtExcluirPaciente.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        txtExcluirPaciente.setForeground(new java.awt.Color(255, 255, 255));
        txtExcluirPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/dropUser.png"))); // NOI18N
        txtExcluirPaciente.setText("EXCLUIR ALGUM MÉDICO");

        javax.swing.GroupLayout pnlMenu3Layout = new javax.swing.GroupLayout(pnlMenu3);
        pnlMenu3.setLayout(pnlMenu3Layout);
        pnlMenu3Layout.setHorizontalGroup(
            pnlMenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtExcluirPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlMenu3Layout.setVerticalGroup(
            pnlMenu3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenu3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtExcluirPaciente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlMenuLateralLayout = new javax.swing.GroupLayout(pnlMenuLateral);
        pnlMenuLateral.setLayout(pnlMenuLateralLayout);
        pnlMenuLateralLayout.setHorizontalGroup(
            pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(iconeCruz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtNomeHospital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(pnlMenu2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlMenu3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlMenuLateralLayout.setVerticalGroup(
            pnlMenuLateralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMenuLateralLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(iconeCruz)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNomeHospital)
                .addGap(50, 50, 50)
                .addComponent(pnlMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMenu2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlMenu3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(227, Short.MAX_VALUE))
        );

        txtTituloAcao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTituloAcao.setForeground(new java.awt.Color(205, 0, 0));
        txtTituloAcao.setText("ALTERAR DADOS DO MÉDICO");

        sep1.setBackground(new java.awt.Color(255, 255, 255));

        tfEscolhaMedico.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfEscolhaMedicoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEscolhaMedicoFocusLost(evt);
            }
        });
        tfEscolhaMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfEscolhaMedicoMouseClicked(evt);
            }
        });
        tfEscolhaMedico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEscolhaMedicoKeyReleased(evt);
            }
        });

        listaMedico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        listaMedico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMedicoMouseClicked(evt);
            }
        });

        PnlDados.setBackground(new java.awt.Color(164, 211, 238));
        PnlDados.setOpaque(false);

        tfCRM.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfCRM.setForeground(new java.awt.Color(153, 153, 153));
        tfCRM.setText("Informe o CRM...");
        tfCRM.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfCRMFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCRMFocusLost(evt);
            }
        });

        tfNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome.setForeground(new java.awt.Color(153, 153, 153));
        tfNome.setText("Informe o nome do médico...");
        tfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeFocusLost(evt);
            }
        });

        txtEspecialidade.setText("Especialidade:");

        javax.swing.GroupLayout PnlDadosLayout = new javax.swing.GroupLayout(PnlDados);
        PnlDados.setLayout(PnlDadosLayout);
        PnlDadosLayout.setHorizontalGroup(
            PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tfNome, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
            .addComponent(tfCRM)
            .addComponent(cbEspecialidade, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PnlDadosLayout.createSequentialGroup()
                .addComponent(txtEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 784, Short.MAX_VALUE))
        );
        PnlDadosLayout.setVerticalGroup(
            PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tfCRM, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(txtEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbEspecialidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        camadaDados.setLayer(PnlDados, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout camadaDadosLayout = new javax.swing.GroupLayout(camadaDados);
        camadaDados.setLayout(camadaDadosLayout);
        camadaDadosLayout.setHorizontalGroup(
            camadaDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(camadaDadosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(PnlDados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        camadaDadosLayout.setVerticalGroup(
            camadaDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, camadaDadosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PnlDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        txtEscolhaPaciente.setText("Selecione o CRM do médico que deseja modificar os dados:");

        camadaDadosPrincipal.setLayer(tfEscolhaMedico, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(listaMedico, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(camadaDados, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(txtEscolhaPaciente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout camadaDadosPrincipalLayout = new javax.swing.GroupLayout(camadaDadosPrincipal);
        camadaDadosPrincipal.setLayout(camadaDadosPrincipalLayout);
        camadaDadosPrincipalLayout.setHorizontalGroup(
            camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfEscolhaMedico, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listaMedico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                        .addComponent(txtEscolhaPaciente)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(camadaDados)
                    .addContainerGap()))
        );
        camadaDadosPrincipalLayout.setVerticalGroup(
            camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                .addComponent(txtEscolhaPaciente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEscolhaMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(listaMedico)
                .addContainerGap(314, Short.MAX_VALUE))
            .addGroup(camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addComponent(camadaDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(59, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addComponent(pnlMenuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTituloAcao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sep2, javax.swing.GroupLayout.DEFAULT_SIZE, 337, Short.MAX_VALUE))
                    .addComponent(camadaDadosPrincipal, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(48, 48, 48))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenuLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTituloAcao)
                    .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sep2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addComponent(camadaDadosPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        mbMenu.setBackground(new java.awt.Color(65, 105, 225));
        mbMenu.setPreferredSize(new java.awt.Dimension(60, 30));

        mnTelaInicial.setText("Ir para o menu principal");
        mnTelaInicial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mnTelaInicialMouseClicked(evt);
            }
        });
        mbMenu.add(mnTelaInicial);

        setJMenuBar(mbMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    
    
    private void mnTelaInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTelaInicialMouseClicked
        this.dispose();
    }//GEN-LAST:event_mnTelaInicialMouseClicked

    private void pnlMenu3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseExited
        pnlMenu3.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu3MouseExited

    private void pnlMenu3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseEntered
        pnlMenu3.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu3MouseEntered

    private void pnlMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseClicked
        this.dispose();
        dropMedico telaDrop = new dropMedico(this.grdSalaEspera);
        telaDrop.setVisible(true);
    }//GEN-LAST:event_pnlMenu3MouseClicked

    private void pnlMenu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseExited
        pnlMenu2.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu2MouseExited

    private void pnlMenu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseEntered
        pnlMenu2.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu2MouseEntered

    private void pnlMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseClicked
   
            /*<<< FAZ AS VALIDAÇÕES DOS CAMPOS >>>*/   
        boolean isWrong = false;

            /* <<< SE O NOME NÃO FOR INFORMADO EXIBE MENSAGEM DE ERRO >>> */
            
            if(codMedicoSelecionado.equals("nulo")) {
                msg.erro("escolha de médico", "Não foi selecionado nenhum médico para editar", tfEscolhaMedico );
                tfEscolhaMedico.setForeground(Color.black);
                isWrong = true;
            }
            else if(tfNome.getText().equals("Informe o nome do médico...")  || tfNome.getText().equals("")) {
                msg.erro("nome do médico", "O campo não pode ficar vazio", tfNome);
                tfNome.setForeground(Color.black);
                isWrong = true;
            }
            
            /* <<< SE OS COMBOBOX NÃO FOREM SELECIONADOS, EXIBE MENSAGEM DE ERRO >>> */
            else if(cbEspecialidade.getSelectedIndex() == -1) {
                msg.texto("Nenhuma especialidade foi selecionada");
                cbEspecialidade.requestFocus();
                isWrong = true;
            }
            

            /* <<< SE ESTIVER TUDO CERTO REALIZA O CADASTRO >>> */
            if(isWrong == false) {
                String codEspecialidade = "nulo";
                conexao.setResultSet("SELECT codEspecialidade FROM especialidade WHERE nome = '"+String.valueOf(cbEspecialidade.getSelectedItem())+"'");
                try {
                    if(this.conexao.getResultSet().first()) {
                        do {
                            codEspecialidade = this.conexao.getResultSet().getString("codEspecialidade");
                        } while (this.conexao.getResultSet().next());
                    }
                } catch (SQLException e){
                    msg.texto("erro");
                }
                
                
                
                
                // ALTERA OS DADOS DO MÉDICO
                if(!codEspecialidade.equals("nulo")) {
                    String SQL = "UPDATE medico SET nome = '"+tfNome.getText()+"', CRM = '"+tfCRM.getText()+"', codEspecialidade = '"+codEspecialidade+"' WHERE codMedico =  '"+codMedicoSelecionado+"'";
                    this.conexao.SQLExecute(SQL);

                    msg.successfullyRegistered("Alteração");
                    this.setAtualizarSalaEspera("horario");
                    this.dispose();
                }
                else 
                    msg.texto("erro");
            }

    }//GEN-LAST:event_pnlMenu2MouseClicked

    private void pnlMenu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseExited
        pnlMenu1.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu1MouseExited

    private void pnlMenu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseEntered
        pnlMenu1.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu1MouseEntered

    private void pnlMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseClicked
        this.dispose();
        caMedico cadTela = new caMedico(this.grdSalaEspera);
        cadTela.setVisible(true);
    }//GEN-LAST:event_pnlMenu1MouseClicked

    private void listaMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMedicoMouseClicked
        this.setPreencherDados();
    }//GEN-LAST:event_listaMedicoMouseClicked

    private void tfEscolhaMedicoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEscolhaMedicoKeyReleased
        this.setPreencherListaOpcoes(this.listaMedico);
    }//GEN-LAST:event_tfEscolhaMedicoKeyReleased

    private void tfEscolhaMedicoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfEscolhaMedicoMouseClicked
        listaMedico.setVisible(true);
    }//GEN-LAST:event_tfEscolhaMedicoMouseClicked

    private void tfEscolhaMedicoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEscolhaMedicoFocusLost
        listaMedico.setVisible(false);
    }//GEN-LAST:event_tfEscolhaMedicoFocusLost

    private void tfEscolhaMedicoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEscolhaMedicoFocusGained
        listaMedico.setVisible(true);
    }//GEN-LAST:event_tfEscolhaMedicoFocusGained

    private void tfNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusLost
        formatar.TextoSemFoco(tfNome, "Informe o nome do médico...");
    }//GEN-LAST:event_tfNomeFocusLost

    private void tfNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusGained
        formatar.TextoComFoco(tfNome, "Informe o nome do médico...");
    }//GEN-LAST:event_tfNomeFocusGained

    private void tfCRMFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCRMFocusLost
        formatar.TextoSemFoco(tfCRM, "Informe o CRM...");
    }//GEN-LAST:event_tfCRMFocusLost

    private void tfCRMFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCRMFocusGained
        formatar.TextoComFoco(tfCRM, "Informe o CRM...");
    }//GEN-LAST:event_tfCRMFocusGained



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlDados;
    private javax.swing.JLayeredPane camadaDados;
    private javax.swing.JLayeredPane camadaDadosPrincipal;
    private javax.swing.JComboBox<String> cbEspecialidade;
    private javax.swing.JLabel iconeCruz;
    private javax.swing.JList<String> listaMedico;
    private javax.swing.JMenuBar mbMenu;
    private javax.swing.JMenu mnTelaInicial;
    private javax.swing.JPanel pnlMenu1;
    private javax.swing.JPanel pnlMenu2;
    private javax.swing.JPanel pnlMenu3;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JSeparator sep1;
    private javax.swing.JSeparator sep2;
    private javax.swing.JTextField tfCRM;
    private javax.swing.JTextField tfEscolhaMedico;
    private javax.swing.JTextField tfNome;
    private javax.swing.JLabel txtAlterarPaciente;
    private javax.swing.JLabel txtEscolhaPaciente;
    private javax.swing.JLabel txtEspecialidade;
    private javax.swing.JLabel txtExcluirPaciente;
    private javax.swing.JLabel txtIncluirPaciente;
    private javax.swing.JLabel txtNomeHospital;
    private javax.swing.JLabel txtTituloAcao;
    // End of variables declaration//GEN-END:variables
}
