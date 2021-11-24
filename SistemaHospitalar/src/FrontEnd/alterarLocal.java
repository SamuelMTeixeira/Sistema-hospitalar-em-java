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
import javax.swing.JTable;

/**
 *
 * @author samuel
 */
public class alterarLocal extends javax.swing.JFrame {

    private ConexaoSQL         conexao;
    private Mensagens_Prontas  msg;
    private FormatacaoDeCampos formatar; 
    private JTable             grdSalaEspera;
    private DefaultListModel   dlm;
    private String             codSelecionado;
    private String             opcao;
    private String             codOpcao;
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
    
    private void setPreencherListaOpcoes (JList listaOpcoes, String table){
        // DEFINE O MODELO DA LISTA DE OPÇÕES DO TEXTFIELD
        dlm = new DefaultListModel();
        listaOpcoes.setModel(dlm);
        
        
        try {
            String SQL = "SELECT DISTINCT nome FROM "+table+" WHERE nome LIKE '"+tfEscolha.getText()+"%' LIMIT 4";
            this.conexao.setResultSet(SQL);
            dlm.removeAllElements();
            
            if(this.conexao.getResultSet().first() ){
                do {
                    dlm.addElement(this.conexao.getResultSet().getString("nome"));     
                }while(this.conexao.getResultSet().next() );

            }
        }
        catch (SQLException e){
            msg.problemMessage("erro", "");
        }
        
    }

    private void setPreencherDados (){
        if(lista.getSelectedIndex() >= 0) {
            // PREENCHE OS DADOS DO PACIENTE
            try {
                this.conexao.setResultSet("SELECT DISTINCT * FROM "+opcao+" where nome LIKE '"+String.valueOf(lista.getSelectedValue())+"%'");

                if(this.conexao.getResultSet().first() ){
                    do {
                        if((!codOpcao.equals("nulo")) && (!opcao.equals("nulo")) ) {
                            codSelecionado = this.conexao.getResultSet().getString(codOpcao);

                             tfNome.setText(this.conexao.getResultSet().getString("nome"));
                             tfNome.setForeground(Color.black);

                             lista.setVisible(false);
                        }
                        else msg.problemMessage("erro", "");
                        
                    }while(this.conexao.getResultSet().next() );
                    tfEscolha.setText(lista.getSelectedValue());
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
    }
      
    // OCULTA OS COMBOBOX DE ACORDO COM O TIPO DE LOCAL INFORMADO
    private void setShowOpcoes (String tipo) {
        switch (tipo) {
            case "nenhum":
                txtEscolha.setVisible(false);
                tfEscolha.setVisible(false);

                tfNome.setVisible(false);
                    break;
                
            case "tudo":
                txtEscolha.setVisible(true);
                tfEscolha.setVisible(true);

                tfNome.setVisible(true);
                    break;      
            default:
                txtEscolha.setVisible(false);
                tfEscolha.setVisible(false);

                tfNome.setVisible(false);
                    break;      
        }
    
    
    }
    
    /*--------------------------------------------*/
    
 
    public alterarLocal(JTable grd) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        this.conexao = new ConexaoSQL();
       
        this.msg = new Mensagens_Prontas();
        this.formatar = new FormatacaoDeCampos();
        this.grdSalaEspera = grd;
        
        mbMenu.setBackground(new java.awt.Color(65,105,225));
        txtTituloAcao.requestFocus();

        this.codSelecionado = "nulo";
        this.opcao = "nulo";
        this.codOpcao = "nulo";
        
        camadaDados.setOpaque(false);
        PnlDados.setOpaque(false);
        lista.setVisible(false);
        this.setShowOpcoes("nenhum");
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
        txtTipoLocal = new javax.swing.JLabel();
        cbTipoLocal = new javax.swing.JComboBox<>();
        txtTituloAcao = new javax.swing.JLabel();
        sep1 = new javax.swing.JSeparator();
        sep2 = new javax.swing.JSeparator();
        camadaDadosPrincipal = new javax.swing.JLayeredPane();
        tfEscolha = new javax.swing.JTextField();
        lista = new javax.swing.JList<>();
        camadaDados = new javax.swing.JLayeredPane();
        PnlDados = new javax.swing.JPanel();
        tfNome = new javax.swing.JTextField();
        txtEscolha = new javax.swing.JLabel();
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
        txtIncluirPaciente.setText("CADASTRAR UMA ESPECIALIDADE");

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
        txtAlterarPaciente.setText("CONCLUIR ALTERAÇÃO DA ESPECIALIDADE");

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
        txtExcluirPaciente.setText("EXCLUIR ALGUMA ESPECIALIDADE");

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

        txtTipoLocal.setText("Selecione qual opção você deseja cadastrar: ");

        cbTipoLocal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estado", "Cidade", "Bairro" }));
        cbTipoLocal.setSelectedIndex(-1);
        cbTipoLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoLocalActionPerformed(evt);
            }
        });

        txtTituloAcao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTituloAcao.setForeground(new java.awt.Color(205, 0, 0));
        txtTituloAcao.setText("ALTERAR LOCALIDADE");

        sep1.setBackground(new java.awt.Color(255, 255, 255));

        tfEscolha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfEscolhaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEscolhaFocusLost(evt);
            }
        });
        tfEscolha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfEscolhaMouseClicked(evt);
            }
        });
        tfEscolha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfEscolhaKeyReleased(evt);
            }
        });

        lista.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMouseClicked(evt);
            }
        });

        PnlDados.setBackground(new java.awt.Color(164, 211, 238));
        PnlDados.setOpaque(false);

        tfNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome.setForeground(new java.awt.Color(153, 153, 153));
        tfNome.setText("Informe a localidade...");
        tfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeFocusLost(evt);
            }
        });

        javax.swing.GroupLayout PnlDadosLayout = new javax.swing.GroupLayout(PnlDados);
        PnlDados.setLayout(PnlDadosLayout);
        PnlDadosLayout.setHorizontalGroup(
            PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tfNome, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
        );
        PnlDadosLayout.setVerticalGroup(
            PnlDadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PnlDadosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
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

        txtEscolha.setText("Pesquise o local que deseja alterar:");

        camadaDadosPrincipal.setLayer(tfEscolha, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(lista, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(camadaDados, javax.swing.JLayeredPane.DEFAULT_LAYER);
        camadaDadosPrincipal.setLayer(txtEscolha, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout camadaDadosPrincipalLayout = new javax.swing.GroupLayout(camadaDadosPrincipal);
        camadaDadosPrincipal.setLayout(camadaDadosPrincipalLayout);
        camadaDadosPrincipalLayout.setHorizontalGroup(
            camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(camadaDadosPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfEscolha, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lista, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(camadaDadosPrincipalLayout.createSequentialGroup()
                        .addComponent(txtEscolha)
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
                .addComponent(txtEscolha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfEscolha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lista)
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
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTituloAcao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(sep2, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE))
                            .addComponent(camadaDadosPrincipal, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbTipoLocal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(txtTipoLocal)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addComponent(txtTipoLocal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbTipoLocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
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
        dropEspecialidade telaDrop = new dropEspecialidade(this.grdSalaEspera);
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
            
            if(cbTipoLocal.getSelectedIndex() == -1) {
                msg.erro("escolha de médico", "Não foi selecionado nenhuma opção de local", tfEscolha );
                tfEscolha.setForeground(Color.black);
                isWrong = true;
            }
            else if(tfNome.getText().equals("Informe a localidade...")  || tfNome.getText().equals("")) {
                msg.erro("nome do médico", "O campo não pode ficar vazio", tfNome);
                tfNome.setForeground(Color.black);
                isWrong = true;
            }
            

                // ALTERA OS DADOS DO MÉDICO
                if(isWrong == false) {
                    String SQL = "UPDATE "+opcao+" SET nome = '"+tfNome.getText()+"' WHERE "+this.codOpcao +" = '"+this.codSelecionado+"'";
                    this.conexao.SQLExecute(SQL);

                    msg.successfullyRegistered("Cadastro");
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
        caLocal cadTela = new caLocal(this.grdSalaEspera);
        cadTela.setVisible(true);
    }//GEN-LAST:event_pnlMenu1MouseClicked

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
        this.setPreencherDados();
    }//GEN-LAST:event_listaMouseClicked

    private void tfEscolhaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfEscolhaKeyReleased
        this.setPreencherListaOpcoes(this.lista, opcao);
    }//GEN-LAST:event_tfEscolhaKeyReleased

    private void tfEscolhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfEscolhaMouseClicked
        lista.setVisible(true);
    }//GEN-LAST:event_tfEscolhaMouseClicked

    private void tfEscolhaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEscolhaFocusLost
        lista.setVisible(false);
    }//GEN-LAST:event_tfEscolhaFocusLost

    private void tfEscolhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEscolhaFocusGained
        lista.setVisible(true);
    }//GEN-LAST:event_tfEscolhaFocusGained

    private void tfNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusLost
        formatar.TextoSemFoco(tfNome, "Informe a localidade...");
    }//GEN-LAST:event_tfNomeFocusLost

    private void tfNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusGained
        formatar.TextoComFoco(tfNome, "Informe a localidade...");
    }//GEN-LAST:event_tfNomeFocusGained

    private void cbTipoLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoLocalActionPerformed
        switch (cbTipoLocal.getSelectedIndex()) {
            case 0:
                tfEscolha.setText("");
                tfNome.setText("");
                this.setShowOpcoes("tudo");
                opcao = "estado";
                codOpcao = "codEstado";
                this.setPreencherListaOpcoes(lista, opcao);
                break;
            case 1:
                tfEscolha.setText("");
                tfNome.setText("");
                this.setShowOpcoes("tudo");
                opcao = "cidade";
                codOpcao = "codCidade";
                this.setPreencherListaOpcoes(lista, opcao);
                break;
            case 2:
                tfEscolha.setText("");
                tfNome.setText("");
                this.setShowOpcoes("tudo");
                opcao = "bairro";
                codOpcao = "codBairro";
                this.setPreencherListaOpcoes(lista, opcao);
                break;
            default:
                tfEscolha.setText("");
                tfNome.setText("");
                this.setShowOpcoes("nenhum");
                break;
        }
    }//GEN-LAST:event_cbTipoLocalActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PnlDados;
    private javax.swing.JLayeredPane camadaDados;
    private javax.swing.JLayeredPane camadaDadosPrincipal;
    private javax.swing.JComboBox<String> cbTipoLocal;
    private javax.swing.JLabel iconeCruz;
    private javax.swing.JList<String> lista;
    private javax.swing.JMenuBar mbMenu;
    private javax.swing.JMenu mnTelaInicial;
    private javax.swing.JPanel pnlMenu1;
    private javax.swing.JPanel pnlMenu2;
    private javax.swing.JPanel pnlMenu3;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JSeparator sep1;
    private javax.swing.JSeparator sep2;
    private javax.swing.JTextField tfEscolha;
    private javax.swing.JTextField tfNome;
    private javax.swing.JLabel txtAlterarPaciente;
    private javax.swing.JLabel txtEscolha;
    private javax.swing.JLabel txtExcluirPaciente;
    private javax.swing.JLabel txtIncluirPaciente;
    private javax.swing.JLabel txtNomeHospital;
    private javax.swing.JLabel txtTipoLocal;
    private javax.swing.JLabel txtTituloAcao;
    // End of variables declaration//GEN-END:variables
}
