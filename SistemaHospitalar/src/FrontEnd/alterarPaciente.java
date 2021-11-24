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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
public class alterarPaciente extends javax.swing.JFrame {

    private ConexaoSQL         conexao;
    private Mensagens_Prontas  msg;
    private FormatacaoDeCampos formatar; 
    private JTable             grdSalaEspera;
    private DefaultListModel   dlm;
    private String             codEstrangeiroEndereco;
    private String             codPacienteSelecionado;

    /*--------------------------------------------*/
            //FUNÇÕES E PROCEDIMENTOS SOBRE O CADASTRO DE PACIENTES
    private String getCodigoPorNome (JComboBox cb, String cod, String table){
        String nome = (String) cb.getSelectedItem(); 
        try {
           this.conexao.setResultSet("SELECT c."+cod+" FROM "+table+" c WHERE nome LIKE '"+nome+"'");
           if(this.conexao.getResultSet().first() )
                return this.conexao.getResultSet().getString(cod);
            else return "erro";
        }
        catch (SQLException e){
            return "erro";
        }
    }  
        
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
    
    private void setPreencherListaOpcoes (JList listaOpcoes, String table){
        // DEFINE O MODELO DA LISTA DE OPÇÕES DO TEXTFIELD
        dlm = new DefaultListModel();
        listaOpcoes.setModel(dlm);
        
        
        try {
            String SQL = "SELECT DISTINCT c.CPF AS CPF FROM "+table+" c where CPF like '"+tfEscolhaPaciente.getText()+"%' LIMIT 4";
            this.conexao.setResultSet(SQL);
            dlm.removeAllElements();
            
            
            
            if(this.conexao.getResultSet().first() ){
                do {
                    dlm.addElement(this.conexao.getResultSet().getString("CPF"));     
                }while(this.conexao.getResultSet().next() );

            }
        }
        catch (SQLException e){
            msg.problemMessage("erro", "");
        }
        
    }
    
    private void setPreencherDados (){
        if(listaPaciente.getSelectedIndex() >= 0) {
            // PREENCHE OS DADOS DO PACIENTE
            try {
                String SQL = "SELECT DISTINCT * FROM paciente where CPF like '"+String.valueOf(listaPaciente.getSelectedValue())+"%'";
                this.conexao.setResultSet(SQL);

                if(this.conexao.getResultSet().first() ){
                    do {
                        codPacienteSelecionado = this.conexao.getResultSet().getString("codPaciente");
                        
                        tfNome.setText(this.conexao.getResultSet().getString("nome"));
                        tfNome.setForeground(Color.black);
                        
                        tfCPF.setText(this.conexao.getResultSet().getString("CPF"));
                        tfCPF.setForeground(Color.black);
                        
                        tfContato.setText(this.conexao.getResultSet().getString("contato"));
                        tfContato.setForeground(Color.black);
                        
                        codEstrangeiroEndereco = this.conexao.getResultSet().getString("codEndereco");
                        listaPaciente.setVisible(false);
                       
                    }while(this.conexao.getResultSet().next() );
                    tfEscolhaPaciente.setText(listaPaciente.getSelectedValue());
                    this.setBlockCampos(true);
                }
            }
            catch (SQLException e){
                msg.problemMessage("erro", "");
            }
            
            
            // PREENCHE OS DADOS DO ENDEREÇO
            if(!codEstrangeiroEndereco.equals("nulo")) {
                try {
                    String SQL = "SELECT * FROM endereco e WHERE e.codEndereco = '"+codEstrangeiroEndereco+"'";
                    this.conexao.setResultSet(SQL);
                    
                    if(this.conexao.getResultSet().first() ){
                        do {
                            tfLogradouro.setText(this.conexao.getResultSet().getString("logradouro"));
                            tfLogradouro.setForeground(Color.black);
                            
                            tfNum.setText(this.conexao.getResultSet().getString("numero"));
                            tfNum.setForeground(Color.black);
                            
                            if(!this.conexao.getResultSet().getString("complemento").equals("") ) {
                                tfComplemento.setText(this.conexao.getResultSet().getString("complemento"));
                                tfComplemento.setForeground(Color.black);
                            }
                            
                        }while(this.conexao.getResultSet().next() );
                        
                    }
                }
                catch (SQLException e){
                    msg.problemMessage("erro", "");
                }
            }
            
        }
    }
    
    private void setBlockCampos (boolean val){
        tfNome.setEditable(val);
        tfCPF.setEditable(val);
        tfContato.setEditable(val);
        tfComplemento.setEditable(val);
        tfLogradouro.setEditable(val);
        tfNum.setEditable(val);

        if(val == false){
            cbEstado.removeAllItems();
        }
        else 
            this.setPreencherComboBox(cbEstado, "estado");
    
    }
    
    public void setAtualizarSalaEspera (String filtro){
        DefaultTableModel dtm = (DefaultTableModel) grdSalaEspera.getModel();
        
        // DEIXA A TABELA SEM NENHUM LINHA (INICIALMENTE)
        while (dtm.getRowCount() > 0 )
            dtm.removeRow(0);
        
        // PREENCHE A TABELA
        try {
            /* <<< LINHA DE COMANDO SQL >>> */
            String SQL = "SELECT DISTINCT p.nome AS paciente, m.nome AS medico, l.horarioAtendimento AS horario FROM listaEspera l INNER JOIN agendamento a  ON a.codAgendamento = l.codAgendamento INNER JOIN paciente p ON p.codPaciente = a.codPaciente  INNER JOIN medico m   ON m.codMedico = a.codMedico WHERE a.diaAtendimento = curdate() ORDER BY "+filtro;
            
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
    
 
    public alterarPaciente(JTable grd) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
       conexao = new ConexaoSQL();
       
        msg = new Mensagens_Prontas();
        formatar = new FormatacaoDeCampos();
        this.grdSalaEspera = grd;
  
        
        mbMenu.setBackground(new java.awt.Color(65,105,225));
        
        txtTituloAcao.requestFocus();
        this.setBlockCampos(false);
        
        codEstrangeiroEndereco = "nulo";
        codPacienteSelecionado = "nulo";
        
        camadaDados.setOpaque(false);
        PnlDados.setOpaque(false);
        
        listaPaciente.setVisible(false);
        
        cbCidade.removeAllItems();
        cbCidade.removeAllItems();
        cbBairro.removeAllItems();
        
        this.setPreencherComboBox(cbEstado, "estado");
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
        tfEscolhaPaciente = new javax.swing.JTextField();
        listaPaciente = new javax.swing.JList<>();
        camadaDados = new javax.swing.JLayeredPane();
        PnlDados = new javax.swing.JPanel();
        tfNum = new javax.swing.JTextField();
        tfComplemento = new javax.swing.JTextField();
        tfCPF = new javax.swing.JTextField();
        txtEstado = new javax.swing.JLabel();
        txtCidade = new javax.swing.JLabel();
        tfNome = new javax.swing.JTextField();
        txtBairro = new javax.swing.JLabel();
        tfLogradouro = new javax.swing.JTextField();
        cbEstado = new javax.swing.JComboBox<>();
        cbBairro = new javax.swing.JComboBox<>();
        cbCidade = new javax.swing.JComboBox<>();
        tfContato = new javax.swing.JTextField();
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
        txtIncluirPaciente.setText("CADASTRAR UM PACIENTE");

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
        txtExcluirPaciente.setText("EXCLUIR ALGUM PACIENTE");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtTituloAcao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTituloAcao.setForeground(new java.awt.Color(205, 0, 0));
        txtTituloAcao.setText("ALTERAR DADOS DO PACIENTE");

        sep1.setBackground(new java.awt.Color(255, 255, 255));

        tfEscolhaPaciente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfEscolhaPacienteFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEscolhaPacienteFocusLost(evt);
            }
        });
        tfEscolhaPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfEscolhaPacienteMouseClicked(evt);
            }
        });
        tfEscolhaPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEscolhaPacienteKeyReleased(evt);
            }
        });

        listaPaciente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        listaPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaPacienteMouseClicked(evt);
            }
        });

        PnlDados.setBackground(new java.awt.Color(164, 211, 238));
        PnlDados.setOpaque(false);

        tfNum.setForeground(new java.awt.Color(153, 153, 153));
        tfNum.setText("Nº");
        tfNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNumFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNumFocusLost(evt);
            }
        });

        tfComplemento.setForeground(new java.awt.Color(153, 153, 153));
        tfComplemento.setText("Complemento");
        tfComplemento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfComplementoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfComplementoFocusLost(evt);
            }
        });

        tfCPF.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfCPF.setForeground(new java.awt.Color(153, 153, 153));
        tfCPF.setText("Informe o CPF do paciente...");
        tfCPF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfCPFFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfCPFFocusLost(evt);
            }
        });

        txtEstado.setText("Estado:");

        txtCidade.setText("Cidade:");

        tfNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome.setForeground(new java.awt.Color(153, 153, 153));
        tfNome.setText("Informe o nome do paciente...");
        tfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeFocusLost(evt);
            }
        });

        txtBairro.setText("Bairro:");

        tfLogradouro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfLogradouro.setForeground(new java.awt.Color(153, 153, 153));
        tfLogradouro.setText("Informe o nome da rua...");
        tfLogradouro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfLogradouroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfLogradouroFocusLost(evt);
            }
        });

        cbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbEstadoItemStateChanged(evt);
            }
        });

        cbCidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbCidadeItemStateChanged(evt);
            }
        });

        tfContato.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfContato.setForeground(new java.awt.Color(153, 153, 153));
        tfContato.setText("Informe o número de contato...");
        tfContato.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfContatoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfContatoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout PnlDadosLayout = new javax.swing.GroupLayout(PnlDados);
        PnlDados.setLayout(PnlDadosLayout);
        PnlDadosLayout.setHorizontalGroup(
            PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PnlDadosLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PnlDadosLayout.createSequentialGroup()
                        .addComponent(tfLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tfComplemento))
                    .addComponent(tfNome)
                    .addComponent(tfCPF)
                    .addComponent(tfContato, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDadosLayout.createSequentialGroup()
                        .addGroup(PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtEstado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                        .addGroup(PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbBairro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );
        PnlDadosLayout.setVerticalGroup(
            PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tfCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tfContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEstado)
                    .addComponent(txtCidade)
                    .addComponent(txtBairro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(PnlDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        txtEscolhaPaciente.setText("Selecione o cpf do paciente que deseja modificar os dados:");

        camadaDadosPrincipal.setLayer(tfEscolhaPaciente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(listaPaciente, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(camadaDados, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(txtEscolhaPaciente, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout camadaDadosPrincipalLayout = new javax.swing.GroupLayout(camadaDadosPrincipal);
        camadaDadosPrincipal.setLayout(camadaDadosPrincipalLayout);
        camadaDadosPrincipalLayout.setHorizontalGroup(
            camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfEscolhaPaciente, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(listaPaciente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(tfEscolhaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(listaPaciente)
                .addGap(364, 364, 364))
            .addGroup(camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                    .addGap(76, 76, 76)
                    .addComponent(camadaDados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(113, Short.MAX_VALUE)))
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
                        .addComponent(sep2, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE))
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
                .addComponent(camadaDadosPrincipal)
                .addGap(638, 638, 638))
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
        dropPaciente telaDrop = new dropPaciente(this.grdSalaEspera);
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
            
            if(codPacienteSelecionado.equals("nulo")) {
                msg.erro("escolha de paciente", "Não foi selecionado nenhum paciente para editar", tfEscolhaPaciente );
                tfEscolhaPaciente.setForeground(Color.black);
                isWrong = true;
            }
            else if(tfNome.getText().equals("Informe o nome do paciente...")  || tfNome.getText().equals("")) {
                msg.erro("nome do paciente", "O campo não pode ficar vazio", tfNome);
                tfNome.setForeground(Color.black);
                isWrong = true;
            }
            /* <<< SE O NOME NÃO FOR INFORMADO EXIBE MENSAGEM DE ERRO >>> */
            else if(tfCPF.getText().equals("Informe o CPF do paciente...")  || tfCPF.getText().equals("")) {
                msg.erro("CPF do paciente", "O campo não pode ficar vazio", tfCPF);
                tfCPF.setForeground(Color.black);
                isWrong = true;
            }
            /* <<< SE O CONTATO NÃO FOR INFORMADO EXIBE MENSAGEM DE ERRO >>> */
            else if(tfContato.getText().equals("Informe o número de contato...")  || (tfContato.getText().equals("")) ) {
                msg.erro("contato do paciente", "O campo não pode ficar vazio", tfContato );
                tfContato.setForeground(Color.black);
                isWrong = true;
            }
            /* <<< SE A RUA NÃO FOR INFORMADA EXIBE MENSAGEM DE ERRO >>> */
            else if(tfLogradouro.getText().equals("Informe o nome da rua...")  || (tfLogradouro.getText().equals("")) ) {
                msg.erro("rua", "O campo não pode ficar vazio", tfLogradouro );
                tfLogradouro.setForeground(Color.black);
                isWrong = true;
            }
            /* <<< SE O NÚMERO NÃO FOR INFORMADO EXIBE MENSAGEM DE ERRO >>> */
            else if(tfNum.getText().equals("Nº")  || (tfLogradouro.getText().equals("")) ) {
                msg.erro("número da residência", "O campo não pode ficar vazio", tfNum);
                tfNum.setForeground(Color.black);
                isWrong = true;
            }
            /* <<< SE OS COMBOBOX NÃO FOREM SELECIONADOS, EXIBE MENSAGEM DE ERRO >>> */
            else if(cbEstado.getSelectedIndex() == -1) {
                msg.texto("Nenhum estado foi selecionado");
                cbEstado.requestFocus();
                isWrong = true;
            }
            else if(cbCidade.getSelectedIndex() == -1) {
                msg.texto("Nenhuma cidade foi selecionada");
                cbCidade.requestFocus();
                isWrong = true;
            }
            else if(cbBairro.getSelectedIndex() == -1) {
                msg.texto("Nenhum bairro foi selecionado");
                cbBairro.requestFocus();
                isWrong = true;
            }

            /* <<< SE ESTIVER TUDO CERTO REALIZA O CADASTRO >>> */
            if(isWrong == false) {
                
                // MUDA O ENDEREÇO
                String SQL = "UPDATE endereco SET logradouro ='"+tfLogradouro.getText()+"', numero = '"+tfNum.getText()+"', complemento = '"+tfComplemento.getText()+"', codBairro = '"+this.getCodigoPorNome(cbBairro, "codBairro", "bairro")+"' WHERE codEndereco = "+codEstrangeiroEndereco;
                this.conexao.SQLExecute(SQL);

                // ALTERA O PACIENTE
                SQL = "UPDATE paciente SET nome = '"+tfNome.getText()+"', CPF = '"+tfCPF.getText()+"',  contato = '"+tfContato.getText()+"' WHERE codPaciente =  '"+codPacienteSelecionado+"'";
                this.conexao.SQLExecute(SQL);

                msg.successfullyRegistered("Alteração concluída");
                this.setAtualizarSalaEspera("horario");
                this.dispose();
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
        caPaciente cadTela = new caPaciente(this.grdSalaEspera);
        cadTela.setVisible(true);
    }//GEN-LAST:event_pnlMenu1MouseClicked

    private void tfContatoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfContatoFocusLost
        formatar.TextoSemFoco(tfContato, "Informe o número de contato...");
    }//GEN-LAST:event_tfContatoFocusLost

    private void tfContatoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfContatoFocusGained
        formatar.TextoComFoco(tfContato, "Informe o número de contato...");
    }//GEN-LAST:event_tfContatoFocusGained

    private void tfLogradouroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLogradouroFocusLost
        formatar.TextoSemFoco(tfLogradouro, "Informe o nome da rua...");
    }//GEN-LAST:event_tfLogradouroFocusLost

    private void tfLogradouroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfLogradouroFocusGained
        formatar.TextoComFoco(tfLogradouro, "Informe o nome da rua...");
    }//GEN-LAST:event_tfLogradouroFocusGained

    private void tfNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusLost
        formatar.TextoSemFoco(tfNome, "Informe o nome do paciente...");
    }//GEN-LAST:event_tfNomeFocusLost

    private void tfNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusGained
        formatar.TextoComFoco(tfNome, "Informe o nome do paciente...");
    }//GEN-LAST:event_tfNomeFocusGained

    private void tfCPFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCPFFocusLost
        formatar.TextoSemFoco(tfCPF, "Informe o CPF do paciente...");
    }//GEN-LAST:event_tfCPFFocusLost

    private void tfCPFFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfCPFFocusGained
        formatar.TextoComFoco(tfCPF, "Informe o CPF do paciente...");
    }//GEN-LAST:event_tfCPFFocusGained

    private void tfComplementoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfComplementoFocusLost
        formatar.TextoSemFoco(tfComplemento, "Complemento");
    }//GEN-LAST:event_tfComplementoFocusLost

    private void tfComplementoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfComplementoFocusGained
        formatar.TextoComFoco(tfComplemento, "Complemento");
    }//GEN-LAST:event_tfComplementoFocusGained

    private void tfNumFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNumFocusLost
        formatar.TextoSemFoco(tfNum, "Nº");
    }//GEN-LAST:event_tfNumFocusLost

    private void tfNumFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNumFocusGained
        formatar.TextoComFoco(tfNum, "Nº");
    }//GEN-LAST:event_tfNumFocusGained

    private void tfEscolhaPacienteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEscolhaPacienteFocusGained
        listaPaciente.setVisible(true);
    }//GEN-LAST:event_tfEscolhaPacienteFocusGained

    private void tfEscolhaPacienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEscolhaPacienteFocusLost
        listaPaciente.setVisible(false);
    }//GEN-LAST:event_tfEscolhaPacienteFocusLost

    private void tfEscolhaPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEscolhaPacienteKeyReleased
        this.setPreencherListaOpcoes(this.listaPaciente, "paciente");
    }//GEN-LAST:event_tfEscolhaPacienteKeyReleased

    private void listaPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaPacienteMouseClicked
        this.setPreencherDados();
    }//GEN-LAST:event_listaPacienteMouseClicked

    private void tfEscolhaPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfEscolhaPacienteMouseClicked
        listaPaciente.setVisible(true);
    }//GEN-LAST:event_tfEscolhaPacienteMouseClicked

    private void cbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbEstadoItemStateChanged
        // O LISTENER FILTRA AS CIDADES DE ACORDO COM O ESTADO SELECIONADO
    cbEstado.addItemListener(new ItemListener()
    {
        ConexaoSQL         conexao = new ConexaoSQL();
        public void itemStateChanged(ItemEvent ev) { if(ev.getStateChange() == ItemEvent.SELECTED){     
        try {
             String SQL = "SELECT DISTINCT nome FROM cidade WHERE codEstado = (SELECT codEstado FROM estado WHERE nome = '"+String.valueOf(cbEstado.getSelectedItem())+"') ORDER BY nome";
            this.conexao.setResultSet(SQL);
            cbCidade.removeAllItems();
            if(this.conexao.getResultSet().first() ) {
                do {
                    cbCidade.addItem(this.conexao.getResultSet().getString("nome"));
                }while(this.conexao.getResultSet().next());
            }
          
            cbBairro.removeAllItems();
           cbCidade.setSelectedIndex(-1);
        }
        catch (SQLException e){
            msg.problemMessage("erro", "");
        }
                    
                    
        }}});
    }//GEN-LAST:event_cbEstadoItemStateChanged

    private void cbCidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbCidadeItemStateChanged
    // O LISTENER FILTRA OS BAIRROS DE ACORDO COM A CIDADE SELECIONADA
        cbCidade.addItemListener(new ItemListener()
    {
        ConexaoSQL         conexao = new ConexaoSQL();
        public void itemStateChanged(ItemEvent ev) { if(ev.getStateChange() == ItemEvent.SELECTED){     
        try {
             String SQL = "SELECT DISTINCT nome FROM bairro WHERE codCidade = (SELECT codCidade FROM cidade WHERE nome = '"+String.valueOf(cbCidade.getSelectedItem())+"') ORDER BY nome";
            this.conexao.setResultSet(SQL);
            cbBairro.removeAllItems();
            if(this.conexao.getResultSet().first() ) {
                do {
                    cbBairro.addItem(this.conexao.getResultSet().getString("nome"));
                }while(this.conexao.getResultSet().next());
            }
          
            
           cbBairro.setSelectedIndex(-1);
        }
        catch (SQLException e){
            msg.problemMessage("erro", "");
        }
                    
                    
        }}});
    }//GEN-LAST:event_cbCidadeItemStateChanged



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlDados;
    private javax.swing.JLayeredPane camadaDados;
    private javax.swing.JLayeredPane camadaDadosPrincipal;
    private javax.swing.JComboBox<String> cbBairro;
    private javax.swing.JComboBox<String> cbCidade;
    private javax.swing.JComboBox<String> cbEstado;
    private javax.swing.JLabel iconeCruz;
    private javax.swing.JList<String> listaPaciente;
    private javax.swing.JMenuBar mbMenu;
    private javax.swing.JMenu mnTelaInicial;
    private javax.swing.JPanel pnlMenu1;
    private javax.swing.JPanel pnlMenu2;
    private javax.swing.JPanel pnlMenu3;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JSeparator sep1;
    private javax.swing.JSeparator sep2;
    private javax.swing.JTextField tfCPF;
    private javax.swing.JTextField tfComplemento;
    private javax.swing.JTextField tfContato;
    private javax.swing.JTextField tfEscolhaPaciente;
    private javax.swing.JTextField tfLogradouro;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfNum;
    private javax.swing.JLabel txtAlterarPaciente;
    private javax.swing.JLabel txtBairro;
    private javax.swing.JLabel txtCidade;
    private javax.swing.JLabel txtEscolhaPaciente;
    private javax.swing.JLabel txtEstado;
    private javax.swing.JLabel txtExcluirPaciente;
    private javax.swing.JLabel txtIncluirPaciente;
    private javax.swing.JLabel txtNomeHospital;
    private javax.swing.JLabel txtTituloAcao;
    // End of variables declaration//GEN-END:variables
}
