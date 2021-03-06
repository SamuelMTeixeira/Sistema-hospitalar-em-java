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
import javax.swing.JTable;

/**
 *
 * @author samuel
 */
public class caEspecialidade extends javax.swing.JFrame {

    private ConexaoSQL         conexao;
    private Mensagens_Prontas  msg;
    private FormatacaoDeCampos formatar; 
    private JTable             grdLista;

    public caEspecialidade(JTable grd) {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
       conexao = new ConexaoSQL();
       
        msg = new Mensagens_Prontas();
        formatar = new FormatacaoDeCampos();
        this.grdLista = grd;

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
        tfNome = new javax.swing.JTextField();
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
        txtNomeHospital.setText("HOSPITAL DE TE??FILO OTONI");

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
        txtIncluirPaciente.setForeground(new java.awt.Color(255, 255, 102));
        txtIncluirPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/btnADD.png"))); // NOI18N
        txtIncluirPaciente.setText("FINALIZAR CADASTRO DA ESPECIALIDADE");

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
        txtAlterarPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/btnAlter.png"))); // NOI18N
        txtAlterarPaciente.setText("ALTERAR DADO DE ALGUMA ESPECIALIDADE");

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
        txtExcluirPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/btnDrop.png"))); // NOI18N
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
                .addContainerGap(229, Short.MAX_VALUE))
        );

        txtTituloAcao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtTituloAcao.setForeground(new java.awt.Color(205, 0, 0));
        txtTituloAcao.setText("ESPECIALIDADE M??DICA");

        sep1.setBackground(new java.awt.Color(255, 255, 255));

        tfNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tfNome.setForeground(new java.awt.Color(153, 153, 153));
        tfNome.setText("Informe a especialidade...");
        tfNome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfNomeFocusLost(evt);
            }
        });

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addComponent(pnlMenuLateral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(sep1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTituloAcao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sep2, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE))
                    .addComponent(tfNome))
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
                .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(429, Short.MAX_VALUE))
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

    private void tfNomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusLost
        formatar.TextoSemFoco(tfNome, "Informe a especialidade...");
    }//GEN-LAST:event_tfNomeFocusLost

    private void tfNomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfNomeFocusGained
        formatar.TextoComFoco(tfNome, "Informe a especialidade...");
    }//GEN-LAST:event_tfNomeFocusGained

    private void pnlMenu3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseExited
        pnlMenu3.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu3MouseExited

    private void pnlMenu3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseEntered
        pnlMenu3.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu3MouseEntered

    private void pnlMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu3MouseClicked
        this.dispose();
        dropEspecialidade telaDrop = new dropEspecialidade(this.grdLista);
        telaDrop.setVisible(true);
    }//GEN-LAST:event_pnlMenu3MouseClicked

    private void pnlMenu2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseExited
        pnlMenu2.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu2MouseExited

    private void pnlMenu2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseEntered
        pnlMenu2.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu2MouseEntered

    private void pnlMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu2MouseClicked
        alterarEspecialidade alterTela = new alterarEspecialidade(grdLista);
        this.dispose();
        alterTela.setVisible(true);
    }//GEN-LAST:event_pnlMenu2MouseClicked

    private void pnlMenu1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseExited
        pnlMenu1.setBackground(new java.awt.Color(72, 118, 255));
    }//GEN-LAST:event_pnlMenu1MouseExited

    private void pnlMenu1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseEntered
        pnlMenu1.setBackground(new java.awt.Color(30, 144, 255));
    }//GEN-LAST:event_pnlMenu1MouseEntered

    private void pnlMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlMenu1MouseClicked
        boolean isWrong = false;

        /* <<< SE O NOME N??O FOR INFORMADO EXIBE MENSAGEM DE ERRO >>> */
        if(tfNome.getText().equals("Informe o nome do paciente...")  || tfNome.getText().equals("")) {
            msg.erro("nome do paciente", "O campo n??o pode ficar vazio", tfNome);
            tfNome.setForeground(Color.black);
            isWrong = true;
        }



        /* <<< SE ESTIVER TUDO CERTO REALIZA O CADASTRO >>> */
        if(isWrong == false) {
            // CADASTRAR A ESPECIALIDADE
            this.conexao.SQLExecute("INSERT INTO especialidade (nome) VALUE ('"+tfNome.getText()+"')");
            msg.successfullyRegistered("Cadastro");
            this.dispose();
        }

    }//GEN-LAST:event_pnlMenu1MouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JTextField tfNome;
    private javax.swing.JLabel txtAlterarPaciente;
    private javax.swing.JLabel txtExcluirPaciente;
    private javax.swing.JLabel txtIncluirPaciente;
    private javax.swing.JLabel txtNomeHospital;
    private javax.swing.JLabel txtTituloAcao;
    // End of variables declaration//GEN-END:variables
}
