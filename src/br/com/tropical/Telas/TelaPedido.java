package br.com.tropical.Telas;

import br.com.tropical.Dal.ModulodeConexao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author RSC Sistemas
 */
public class TelaPedido extends javax.swing.JInternalFrame {

    private static TelaPedido telapedido;

    public static TelaPedido getInstancia() throws IOException, SQLException {
        if (telapedido == null) {
            telapedido = new TelaPedido();
        }
        return telapedido;
    }

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaPedido
     */
    public TelaPedido() throws IOException {
        initComponents();
        data();
        conexao = ModulodeConexao.conector();
        this.Listacombo();
    }

    private void Listacombo() {

        try {
            String sql = "select * from costureira";
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                jcoCostureira.addItem(rs.getString("nome_c"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Não Foi Posivel encontrar usuarios no Banco de dados");
        }
    }

    private void EmitirPedido() {
        String sql = "insert into pedido (material,corte,bordado_pintura,costura,costureira,obs_material,funcionario_p,loja_p,cidade_p,finalizado,id_c,data_m)values(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);

            if (jrSim.isSelected()) {
                pst.setString(1, jrSim.getText());
                pst.setString(2, jcoCorte.getSelectedItem().toString());
                pst.setString(3, jcoBordado.getSelectedItem().toString().replace("Status", "NULO"));
                pst.setString(4, jcoCostura.getSelectedItem().toString().replace("Status", "NULO"));
                pst.setString(5, jcoCostureira.getSelectedItem().toString().replace("Selecione", "NULO"));
                pst.setString(6, jtxObs.getText());
                pst.setString(7, TelaPrincipal.lbl_Usuario.getText());
                pst.setString(8, TelaPrincipal.lbl_loja.getText());
                pst.setString(9, TelaPrincipal.lbl_Cidade.getText());
                pst.setString(10, "não");
                pst.setString(11, lblId.getText());
                pst.setString(12, lblData.getText().toString());

            } else {
                pst.setString(1, jrNao.getText());
                pst.setString(2, jcoCorte.getSelectedItem().toString().replace("Status", "NULO"));
                pst.setString(3, jcoBordado.getSelectedItem().toString().replace("Status", "NULO"));
                pst.setString(4, jcoCostura.getSelectedItem().toString().replace("Status", "NULO"));
                pst.setString(5, jcoCostureira.getSelectedItem().toString().replace("Selecione", "NULO"));
                pst.setString(6, jtxObs.getText());
                pst.setString(7, TelaPrincipal.lbl_Usuario.getText());
                pst.setString(8, TelaPrincipal.lbl_loja.getText());
                pst.setString(9, TelaPrincipal.lbl_Cidade.getText());
                pst.setString(10, "não");
                pst.setString(11, lblId.getText());
                pst.setString(12, lblData.getText().toString());

            }
            if ("00".equals(lblId.getText())) {
                JOptionPane.showMessageDialog(null, "Selecione um cliente");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Pedido Adicionado :)");

                }
            }

        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e);
            JOptionPane.showMessageDialog(null, "Não foi possível Adiconar Contate o administrador" + e);
        }

    }

    private void pesquisar_cliente() {
        // String sql = "select * from tbclientes where nome like ?";
        String sql = "select id_c as ID, nome_c as Nome  from cliente where nome_c like ? order by cliente.nome_c asc";
        try {
            pst = conexao.prepareStatement(sql);
            //passar para a caixa de psquisa para o ?
            //atençao ao % que e a continuaçao do comando sql
            pst.setString(1, txtPesqui.getText() + "%");
            rs = pst.executeQuery();
            //alinha abaixo usa a biblioteca rs2xml.jar para preencher a tabela
            jtbResultC.setModel(DbUtils.resultSetToTableModel(rs));
            jtbResultC.getColumnModel().getColumn(0).setPreferredWidth(10);
            jtbResultC.getColumnModel().getColumn(1).setPreferredWidth(300);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //metodo para setar os campos do formulario com o conteudo da tabela[
    public void setar_campos() {
        int setar = jtbResultC.getSelectedRow();
        lblId.setText(jtbResultC.getModel().getValueAt(setar, 0).toString());

        //bloqueio adicionar com infos
    }

    public void Material() {
        if (jrSim.isSelected()) {
            jrNao.setSelected(false);
            jcoCorte.setEnabled(true);
            jcoBordado.setEnabled(true);
            jcoCostura.setEnabled(true);
            jcoCostureira.setEnabled(true);
            jtxObs.setEnabled(false);
            jtxObs.setEditable(false);

        } else if (jrNao.isSelected()) {
            jrSim.setSelected(false);
            jtxObs.setEnabled(true);
            jtxObs.setEditable(true);
            jcoCorte.setSelectedItem("Status");
            jcoBordado.setSelectedItem("Status");
            jcoCostura.setSelectedItem("Status");
            jcoCostureira.setSelectedItem("Selecione");
            jcoCorte.setEnabled(false);
            jcoBordado.setEnabled(false);
            jcoCostura.setEnabled(false);
            jcoCostureira.setEnabled(false);
        }
    }

    public void Pedido_andamento() {
        // adiciona pedido
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbResultC = new javax.swing.JTable();
        txtPesqui = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblId = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtxObs = new javax.swing.JTextArea();
        jrSim = new javax.swing.JRadioButton();
        jrNao = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jcoCorte = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jcoBordado = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jcoCostura = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jcoCostureira = new javax.swing.JComboBox<>();
        btnEntrada = new javax.swing.JButton();

        setClosable(true);
        setTitle("Pedidos--");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jtbResultC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null}
            },
            new String [] {
                "Title 1"
            }
        ));
        jtbResultC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtbResultCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jtbResultC);

        txtPesqui.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquiKeyReleased(evt);
            }
        });

        jLabel1.setText("Cliente :");

        jLabel2.setText("ID :");

        lblId.setText("00");

        lblData.setText("10/12/12");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblData)
                        .addGap(148, 148, 148)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPesqui, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblId)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblData)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(lblId)
                                .addComponent(txtPesqui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Material :");

        jLabel4.setText("Observaçã Sobre Material");

        jtxObs.setColumns(20);
        jtxObs.setRows(5);
        jScrollPane2.setViewportView(jtxObs);

        buttonGroup1.add(jrSim);
        jrSim.setText("Sim");
        jrSim.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrSimMouseClicked(evt);
            }
        });

        buttonGroup1.add(jrNao);
        jrNao.setText("Não");
        jrNao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jrNaoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(44, 44, 44)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jrSim)
                    .addComponent(jrNao))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jrSim)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jrNao)))
                .addGap(0, 27, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Corte : ");

        jcoCorte.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Sim", "Não" }));

        jLabel6.setText("bordado _ pintura :");

        jcoBordado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Bordado", "Pintura" }));

        jLabel7.setText("Costura :");

        jcoCostura.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Status", "Não", "Sim" }));

        jLabel8.setText("Costureira :");

        jcoCostureira.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione" }));

        btnEntrada.setText("Dar entrada");
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEntrada)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcoBordado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcoCostura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jcoCostureira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcoCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jcoBordado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jcoCostura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jcoCostureira, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                .addComponent(btnEntrada)
                .addGap(42, 42, 42))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtPesquiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquiKeyReleased
        // pesquisar cli
        this.pesquisar_cliente();
    }//GEN-LAST:event_txtPesquiKeyReleased

    private void jtbResultCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtbResultCMouseClicked
        // setar campos
        this.setar_campos();
    }//GEN-LAST:event_jtbResultCMouseClicked

    private void jrSimMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrSimMouseClicked
        // sim
        this.Material();
    }//GEN-LAST:event_jrSimMouseClicked

    private void jrNaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jrNaoMouseClicked
        // nao
        this.Material();
    }//GEN-LAST:event_jrNaoMouseClicked

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        EmitirPedido();
    }//GEN-LAST:event_btnEntradaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrada;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> jcoBordado;
    private javax.swing.JComboBox<String> jcoCorte;
    private javax.swing.JComboBox<String> jcoCostura;
    private javax.swing.JComboBox<String> jcoCostureira;
    private javax.swing.JRadioButton jrNao;
    private javax.swing.JRadioButton jrSim;
    private javax.swing.JTable jtbResultC;
    private javax.swing.JTextArea jtxObs;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblId;
    private javax.swing.JTextField txtPesqui;
    // End of variables declaration//GEN-END:variables

    private Object getSelected() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void data() {
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }
}
