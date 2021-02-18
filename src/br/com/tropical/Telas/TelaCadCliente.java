package br.com.tropical.Telas;

import java.io.IOException;
import br.com.tropical.Dal.ModulodeConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author RSC Sistemas
 */
public class TelaCadCliente extends javax.swing.JInternalFrame {

    private static TelaCadCliente telaCadCliente;

    public static TelaCadCliente getInstancia() throws IOException, SQLException {
        if (telaCadCliente == null) {
            telaCadCliente = new TelaCadCliente();
        }
        return telaCadCliente;

    }

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCadCliente
     */
    public TelaCadCliente() throws IOException {
        initComponents();
        conexao = ModulodeConexao.conector();

    }

    // adicionar Clientes ao banco de dados---------------------------------------------------------------
    private void adicionar() {
        String sql = "insert into cliente (nome_c,telefone_c) values (?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomeCli.getText().toUpperCase());
            pst.setString(2, txtFonCli.getText());

//validação dos campos obrigatorios
            if ((txtNomeCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Nome e Obrigatorio");
            } else {

// alinha abaixo atualiza o banco de dados com os dados do formulario
                //estrutura que confirma a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente Adicionado :)");
                    txtNomeCli.setText(null);
                    txtFonCli.setText(null);

                }
            }
            //pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //método para pesquisar clientes pelo nome com filtro-------------------------------
    private void pesquisar_cliente() {
        // String sql = "select * from tbclientes where nome like ?";
        String sql = "select id_c as ID, nome_c as Nome,telefone_c as Telefone  from cliente where nome_c like ? order by cliente.nome_c asc";
        try {
            pst = conexao.prepareStatement(sql);
            //passar para a caixa de psquisa para o ?
            //atençao ao % que e a continuaçao do comando sql
            pst.setString(1, txtPesqCli.getText() + "%");
            rs = pst.executeQuery();
            //alinha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            jtabResulCli.setModel(DbUtils.resultSetToTableModel(rs));
            jtabResulCli.getColumnModel().getColumn(0).setPreferredWidth(10);
            jtabResulCli.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtabResulCli.getColumnModel().getColumn(2).setPreferredWidth(86);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        String sql = "update cliente set nome_c= ?,telefone_c= ? where id_c=?";
        try {
            pst = conexao.prepareStatement(sql);

            pst.setString(1, txtNomeCli.getText().toUpperCase());
            pst.setString(2, txtFonCli.getText());
            pst.setString(3, lblIdCli.getText());

            if ((txtNomeCli.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
            } else {

// alinha abaixo atualiza o banco de dados com os dados do formulario
                //estrutura que confirma a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados Do Cliente  Alterado  :)");
                    txtNomeCli.setText(null);
                    txtFonCli.setText(null);
                    btnAddCli.setEnabled(true);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    //metodo deletar
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja remover Cliente",
                "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from cliente where id_c=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, lblIdCli.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario Removido con sucesso");

                    txtNomeCli.setText(null);
                    txtFonCli.setText(null);

                    btnAddCli.setEnabled(true);

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //metodo para setar os campos do formulario com o conteudo da tabela[
    public void setar_campos() {
        int setar = jtabResulCli.getSelectedRow();
        lblIdCli.setText(jtabResulCli.getModel().getValueAt(setar, 0).toString());
        txtNomeCli.setText(jtabResulCli.getModel().getValueAt(setar, 1).toString());
        txtFonCli.setText(jtabResulCli.getModel().getValueAt(setar, 2).toString());

        //bloqueio adicionar com infos
        btnAddCli.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        txtPesqCli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        lblIdCli = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtabResulCli = new javax.swing.JTable();
        btnAddCli = new javax.swing.JButton();
        btnEdtCli = new javax.swing.JButton();
        btnRemvCli = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNomeCli = new javax.swing.JTextField();
        txtFonCli = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Clientes"));

        txtPesqCli.setBackground(new java.awt.Color(214, 217, 223));
        txtPesqCli.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtPesqCli.setBorder(null);
        txtPesqCli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesqCliKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("ID");

        lblIdCli.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblIdCli.setText("00");

        jtabResulCli.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jtabResulCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtabResulCliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtabResulCli);

        btnAddCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/cliAdd.png"))); // NOI18N
        btnAddCli.setBorder(null);
        btnAddCli.setMaximumSize(new java.awt.Dimension(60, 60));
        btnAddCli.setMinimumSize(new java.awt.Dimension(60, 60));
        btnAddCli.setPreferredSize(new java.awt.Dimension(60, 60));
        btnAddCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnAddCliMouseClicked(evt);
            }
        });
        btnAddCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCliActionPerformed(evt);
            }
        });

        btnEdtCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/EditCli.png"))); // NOI18N
        btnEdtCli.setPreferredSize(new java.awt.Dimension(60, 60));
        btnEdtCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdtCliActionPerformed(evt);
            }
        });

        btnRemvCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/RemoveCli.png"))); // NOI18N
        btnRemvCli.setPreferredSize(new java.awt.Dimension(60, 60));
        btnRemvCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemvCliActionPerformed(evt);
            }
        });

        jLabel3.setText("Nome:");

        jLabel4.setText("Telefone :");

        txtNomeCli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtNomeCliMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAddCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnEdtCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btnRemvCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNomeCli)
                            .addComponent(txtFonCli))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPesqCli, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIdCli, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesqCli, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lblIdCli))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtFonCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAddCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEdtCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemvCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(400, 100, 641, 399);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesqCliKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesqCliKeyReleased
        // campo pesquisa
        pesquisar_cliente();
    }//GEN-LAST:event_txtPesqCliKeyReleased

    private void btnAddCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCliActionPerformed
        // adicionar cliente
        adicionar();
    }//GEN-LAST:event_btnAddCliActionPerformed

    private void btnAddCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAddCliMouseClicked
        // quando clica en cima de add quando esta funcionando

    }//GEN-LAST:event_btnAddCliMouseClicked

    private void btnEdtCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdtCliActionPerformed
        // editaar
        alterar();
    }//GEN-LAST:event_btnEdtCliActionPerformed

    private void btnRemvCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemvCliActionPerformed
        // remover usuario
        remover();
    }//GEN-LAST:event_btnRemvCliActionPerformed

    private void jtabResulCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtabResulCliMouseClicked
        // ao clicar  na tabela
        setar_campos();
    }//GEN-LAST:event_jtabResulCliMouseClicked

    private void txtNomeCliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtNomeCliMouseClicked
        // clicar com o mouse 2 veses
        if (evt.getClickCount() == 2) {

            txtNomeCli.setText(null);
            lblIdCli.setText(null);
            txtFonCli.setText(null);
            btnAddCli.setEnabled(true);

        }
    }//GEN-LAST:event_txtNomeCliMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCli;
    private javax.swing.JButton btnEdtCli;
    private javax.swing.JButton btnRemvCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jtabResulCli;
    private javax.swing.JLabel lblIdCli;
    private javax.swing.JTextField txtFonCli;
    private javax.swing.JTextField txtNomeCli;
    private javax.swing.JTextField txtPesqCli;
    // End of variables declaration//GEN-END:variables
}
