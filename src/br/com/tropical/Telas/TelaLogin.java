package br.com.tropical.Telas;

import br.com.tropical.Dal.ModulodeConexao;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author RSC Sistemas (tela de login )
 */
public final class TelaLogin extends javax.swing.JFrame {

    private static TelaLogin telaLogin;

    public static TelaLogin getInstancia() throws IOException, SQLException {
        if (telaLogin == null) {
            telaLogin = new TelaLogin();
        }
        return telaLogin;

    }

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaLogin
     *
     * @throws java.io.IOException
     */
    public TelaLogin() throws IOException {
        initComponents();

        lblLogUser.setVisible(false);  //esconde a label aonde fica o logim do usuario
        conexao = ModulodeConexao.conector();
        this.Listacombo();//inicia o lista no conbobox sempre antes de conexao se nao ano acha

        if (conexao
                != null) {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/imagens/sqlok64.png")));
        } else {
            lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/imagens/sqlerror64.png")));
            txtSenha.requestFocus(true);
        }
    }

    private void Listacombo() {

        try {
            String sql = "select * from funcionario";
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                jcboUsuar.addItem(rs.getString("nome_f"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Posivel encontrar usuarios no Banco de dados");
        }
    }

    //seleção de user por nome setando o login real
    public void SetarU() {
//campo de txtfild aonde coloca a senha e onde ele valida o label com o logim
//pois ele e que valida esta ação escolhe o nome do usuario e ele seta o login
        try {
            String sql1 = "select login_f from funcionario where nome_f =? ";
            String usu = jcboUsuar.getSelectedItem().toString();
            pst = conexao.prepareStatement(sql1);
            pst.setString(1, usu);
            rs = pst.executeQuery();
            if (rs.next()) {
                lblLogUser.setText(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("Erro Inesperado Contate o Programador");

        }
    }

    public void logar() {
        String sql = "select * from funcionario where login_f=? and senha_f=?";
        try {
            //as linhas abaixo prepara a consuta no banco
            pst = conexao.prepareStatement(sql);
            pst.setString(1, lblLogUser.getText());
            pst.setString(2, txtSenha.getText());
            //a linha abaixo execulta a query
            rs = pst.executeQuery();
            //se existir usuario e senha certas
            if (rs.next()) {
                // a linha abaixo obten o conteudo do campo perfil da tabela tbusuario
                String perfil = rs.getString(6);
                //System.out.println(perfil);

                // estrututra de tratamento de perfil de usuário
                if (perfil.equals("admin")) {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.menFuncio.setVisible(true);
                    //TelaPrincipal.MenCadUsu.setEnabled(true);
                    TelaPrincipal.lbl_Usuario.setText(rs.getString(1));
                    TelaPrincipal.lbl_Cidade.setText(rs.getString(4));
                    TelaPrincipal.lbl_Usuario.setForeground(Color.red);
                    TelaPrincipal.lbl_loja.setText(rs.getString(5));
                    this.dispose();
                    conexao.close();
                } else {
                    TelaPrincipal principal = new TelaPrincipal();
                    principal.setVisible(true);
                    TelaPrincipal.menFuncio.setVisible(false);
                    TelaPrincipal.lbl_Usuario.setText(rs.getString(1));
                    TelaPrincipal.lbl_Cidade.setText(rs.getString(4));
                    TelaPrincipal.lbl_loja.setText(rs.getString(5));
                    this.dispose();
                    conexao.close();
                }
            } else {

                JOptionPane.showMessageDialog(null, "Senha invalida Tente novamente");
                txtSenha.setText(null);
                txtSenha.requestFocus();

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        lblStatus = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jcboUsuar = new javax.swing.JComboBox<>();
        lblLogUser = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login - Tropical Bordados");
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("usúario :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Senha :");

        txtSenha.setNextFocusableComponent(jButton1);
        txtSenha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSenhaFocusGained(evt);
            }
        });
        txtSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSenhaKeyPressed(evt);
            }
        });

        lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/sqlok64.png"))); // NOI18N

        jButton1.setText("Entrar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/userkey.jpg"))); // NOI18N

        jcboUsuar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione Usúario", "---------------------" }));
        jcboUsuar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jcboUsuar, org.jdesktop.beansbinding.ELProperty.create("${selectedIndex}"), jcboUsuar, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        jcboUsuar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcboUsuarItemStateChanged(evt);
            }
        });

        lblLogUser.setText("LOG_USER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblLogUser)
                                .addComponent(jcboUsuar, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblStatus)
                        .addGap(329, 329, 329)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblLogUser)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jcboUsuar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(lblStatus)
                        .addGap(22, 22, 22))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        bindingGroup.bind();

        setSize(new java.awt.Dimension(863, 312));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // botao entrar
        logar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // ao presionar o enter

    }//GEN-LAST:event_formKeyPressed

    private void txtSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSenhaKeyPressed
        // campo senha e apertar emter

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            logar();
        }
    }//GEN-LAST:event_txtSenhaKeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // ao clicar enter no botao
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            logar();
        }
    }//GEN-LAST:event_jButton1KeyPressed

    private void txtSenhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSenhaFocusGained
        // focus ele seta os dados na label :
        SetarU();
    }//GEN-LAST:event_txtSenhaFocusGained

    private void jcboUsuarItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcboUsuarItemStateChanged
        // TODO add your handling code here:
        txtSenha.requestFocus();
        txtSenha.setText(null);
    }//GEN-LAST:event_jcboUsuarItemStateChanged

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
                try {
                    new TelaLogin().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JComboBox<String> jcboUsuar;
    private javax.swing.JLabel lblLogUser;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JPasswordField txtSenha;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
