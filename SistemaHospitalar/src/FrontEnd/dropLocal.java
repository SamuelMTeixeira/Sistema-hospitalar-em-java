/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import BackEnd.ConexaoSQL;
import Padroes.Mensagens_Prontas;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author samuel
 */
public class dropLocal extends javax.swing.JFrame {

    private ConexaoSQL        conexao;
    private Mensagens_Prontas msg;
    private DefaultTableModel dtm; 
    private JTable            grdSalaEspera;
    
    
    /*--------------------------------------------*/
            //FUNÇÕES E PROCEDIMENTOS SOBRE A TABELA
    
    public void setAtualizarGrid (String table, String filtro){

        dtm = (DefaultTableModel) grdLista.getModel();
        
        // DEIXA A TABELA SEM NENHUM LINHA (INICIALMENTE)
        while (dtm.getRowCount() > 0 )
            dtm.removeRow(0);
        
        // PREENCHE A TABELA
        try {
            /* <<< LINHA DE COMANDO SQL >>> */
            String SQL = "SELECT m.nome as nome FROM "+table+" m ORDER BY "+filtro;
            
            /* <<< EXECUTA LINHA DE COMANDO SQL >>> */
            this.conexao.setResultSet(SQL);
            
            /* <<< PEGA OS VALORES DA TELA, DO PRIMEIRO PARA O ULTIMO >>> */
            if (this.conexao.getResultSet().first() ){
                do {
                    dtm.addRow  (
                            new Object[] {
                                this.conexao.getResultSet().getString("nome"),
                            }
                    );
              
                } while (this.conexao.getResultSet().next());
            } 
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    public void setDeleteGrid (String nome, String table) {      
        String codigo = null;

        // PEGA O CODIGO DO MÉDICO SELECIONADO
        if(table.equals("estado")) {
            this.conexao.setResultSet("SELECT codEstado FROM "+table+" WHERE nome LIKE '"+nome+"'");
            try {
                if(this.conexao.getResultSet().first() )
                    codigo = this.conexao.getResultSet().getString("codEstado"); 
                    this.conexao.SQLExecute("DELETE FROM "+table+" WHERE codEstado = '"+codigo+"'");
                }
                catch (SQLException e){
                    JOptionPane.showMessageDialog(this, "erro");
                }
            
        }
        else if(table.equals("cidade")) {
            this.conexao.setResultSet("SELECT codCidade FROM "+table+" WHERE nome LIKE '"+nome+"'");
            
            try {
                if(this.conexao.getResultSet().first() )
                    codigo = this.conexao.getResultSet().getString("codCidade"); 
                    
                }
                catch (SQLException e){
                    JOptionPane.showMessageDialog(this, "erro");
                }
        
            
            // DELETA OS DADOS
            if(JOptionPane.showConfirmDialog(null, "Se você excluir essa cidade, todos os bairros vinculados à esta também serão deletados!\n tem certeza que deseja concluir essa ação?", "Mensagem importante", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                this.conexao.SQLExecute("DELETE FROM bairro WHERE codCidade = '"+codigo+"'");
                this.conexao.SQLExecute("DELETE FROM cidade WHERE codCidade = '"+codigo+"'");
                  
            }
        
        }
        else if(table.equals("bairro")) {
            this.conexao.setResultSet("SELECT codBairro FROM "+table+" WHERE nome LIKE '"+nome+"'");
            
            try {
                if(this.conexao.getResultSet().first() )
                    codigo = this.conexao.getResultSet().getString("codBairro"); 
                    this.conexao.SQLExecute("DELETE FROM "+table+" WHERE codBairro = '"+codigo+"'");
                }
                catch (SQLException e){
                    JOptionPane.showMessageDialog(this, "erro");
                }
        }
    
    
    
    
    }  
   
    public void setAtualizarSalaEspera (String filtro){

        dtm = (DefaultTableModel) grdLista.getModel();
        
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
     
    
    public dropLocal(JTable grd) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
       conexao = new ConexaoSQL();
       
        msg = new Mensagens_Prontas();
        this.grdSalaEspera = grd;
        
        spLista.getViewport().setBackground(new java.awt.Color(164,211,238));
        grdLista.setSelectionBackground(new java.awt.Color(30,144,255));
        mbMenu.setBackground(new java.awt.Color(65,105,225));
        txtTituloAcao.requestFocus();
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
        spLista = new javax.swing.JScrollPane();
        grdLista = new javax.swing.JTable();
        cbTipoOpcao = new javax.swing.JComboBox<>();
        txtTipoOpcao = new javax.swing.JLabel();
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
        txtIncluirPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/localADD.png"))); // NOI18N
        txtIncluirPaciente.setText("CADASTRAR UM LOCAL");

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
        txtAlterarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        txtAlterarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/localAlter.png"))); // NOI18N
        txtAlterarPaciente.setText("ALTERAR DADOS DE ALGUM LOCAL");

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
        txtExcluirPaciente.setForeground(new java.awt.Color(255, 255, 102));
        txtExcluirPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/localAlter.png"))); // NOI18N
        txtExcluirPaciente.setText("EXCLUIR  LOCAL SELECIONADO");

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
        txtTituloAcao.setText("EXCLUIR USUÁRIO");

        sep1.setBackground(new java.awt.Color(255, 255, 255));

        grdLista.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        spLista.setViewportView(grdLista);

        cbTipoOpcao.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Estado", "Cidade", "Bairro" }));
        cbTipoOpcao.setSelectedIndex(-1);
        cbTipoOpcao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoOpcaoActionPerformed(evt);
            }
        });

        txtTipoOpcao.setText("Tipo de local:");

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addComponent(pnlMenuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTituloAcao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sep2, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                    .addComponent(spLista, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(txtTipoOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbTipoOpcao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(48, 48, 48))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMenuLateral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTituloAcao)
                            .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(sep2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoOpcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipoOpcao))
                .addGap(48, 48, 48)
                .addComponent(spLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(55, Short.MAX_VALUE))
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

    private void pnlMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseClicked
        this.dispose();
        caLocal cadTela = new caLocal(this.grdSalaEspera);
        cadTela.setVisible(true);
    }//GEN-LAST:event_pnlMenu1MouseClicked

    private void pnlMenu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseEntered
        pnlMenu1.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu1MouseEntered

    private void pnlMenu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseExited
        pnlMenu1.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu1MouseExited

    private void mnTelaInicialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnTelaInicialMouseClicked
        this.dispose();
    }//GEN-LAST:event_mnTelaInicialMouseClicked

    private void pnlMenu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseEntered
        pnlMenu2.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu2MouseEntered

    private void pnlMenu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseExited
        pnlMenu2.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu2MouseExited

    private void pnlMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseClicked
        this.dispose();
        alterarLocal alterLocal = new alterarLocal(grdLista);
        alterLocal.setVisible(true);
    }//GEN-LAST:event_pnlMenu2MouseClicked

    private void pnlMenu3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseEntered
        pnlMenu3.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu3MouseEntered

    private void pnlMenu3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseExited
       pnlMenu3.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu3MouseExited

    private void pnlMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseClicked

        if(cbTipoOpcao.getSelectedIndex() == -1)
            msg.problemMessage("exclusão de dados", "Nao foi informado o tipo de dado");
        
        else {        
            if(grdLista.getSelectedRowCount() > 0 ){
                
            switch (cbTipoOpcao.getSelectedIndex() ) {
                case 0:
                    this.setDeleteGrid(String.valueOf(grdLista.getValueAt(grdLista.getSelectedRow(), 0)), "estado"); 
                    break;
                case 1:
                    this.setDeleteGrid(String.valueOf(grdLista.getValueAt(grdLista.getSelectedRow(), 0)), "cidade"); 
                    break;
                case 2:
                    this.setDeleteGrid(String.valueOf(grdLista.getValueAt(grdLista.getSelectedRow(), 0)), "bairro"); 
                    break;
                default:

                    break;
            }
                
               
                    this.setAtualizarSalaEspera("horario");
                    msg.successfullyRegistered("Exclusão");
                    this.dispose();
            }   
            else
                msg.problemMessage("exclusão de paciente", "Nenhum paciente da tabela foi selecionado");
        }
        
    }//GEN-LAST:event_pnlMenu3MouseClicked

    private void cbTipoOpcaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoOpcaoActionPerformed
            if(cbTipoOpcao.getSelectedIndex() == 0) {
                this.setAtualizarGrid("estado" ,"nome");
            }
            else if (cbTipoOpcao.getSelectedIndex() == 1) {
                this.setAtualizarGrid("cidade" ,"nome");
            }
            else if (cbTipoOpcao.getSelectedIndex() == 2) {
                this.setAtualizarGrid("bairro" ,"nome");
            }
            
            
    }//GEN-LAST:event_cbTipoOpcaoActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbTipoOpcao;
    private javax.swing.JTable grdLista;
    private javax.swing.JLabel iconeCruz;
    private javax.swing.JMenuBar mbMenu;
    private javax.swing.JMenu mnTelaInicial;
    private javax.swing.JPanel pnlMenu1;
    private javax.swing.JPanel pnlMenu2;
    private javax.swing.JPanel pnlMenu3;
    private javax.swing.JPanel pnlMenuLateral;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JSeparator sep1;
    private javax.swing.JSeparator sep2;
    private javax.swing.JScrollPane spLista;
    private javax.swing.JLabel txtAlterarPaciente;
    private javax.swing.JLabel txtExcluirPaciente;
    private javax.swing.JLabel txtIncluirPaciente;
    private javax.swing.JLabel txtNomeHospital;
    private javax.swing.JLabel txtTipoOpcao;
    private javax.swing.JLabel txtTituloAcao;
    // End of variables declaration//GEN-END:variables
}
