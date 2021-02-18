package br.com.tropical.Telas;

import br.com.tropical.Dal.ModulodeConexao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author RSC Sistema
 */
public class TelaCadUsuario extends javax.swing.JInternalFrame {

    private static TelaCadUsuario telaCadUsuario;

    public static TelaCadUsuario getInstancia() throws IOException, SQLException {
        if (telaCadUsuario == null) {
            telaCadUsuario = new TelaCadUsuario();
        }
        return telaCadUsuario;

    }

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCadUsuario
     */
    public TelaCadUsuario() throws IOException {
        initComponents();
        conexao = ModulodeConexao.conector();
    }

    private void adicionar() {
        String sql = "insert into funcionario (nome_f,login_f,senha_f,cidade_f,loja_f,perfil,id_F) values (?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomUsu.getText().toUpperCase());
            pst.setString(2, txtLogUsu.getText().toUpperCase());
            pst.setString(3, txtSenhUsu.getText());
            pst.setString(4, cboCidade.getSelectedItem().toString());
            pst.setString(5, cboLoja.getSelectedItem().toString());
            pst.setString(6, cboPerfil.getSelectedItem().toString());
            pst.setString(7, txtId.getText());

//validação dos campos obrigatorios
            if ((txtNomUsu.getText().isEmpty()) || (txtLogUsu.getText().isEmpty()) || (txtSenhUsu.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
            } else {

// alinha abaixo atualiza o banco de dados com os dados do formulario
                //estrutura que confirma a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario Adicionado :)");
                    txtNomUsu.setText(null);
                    txtLogUsu.setText(null);
                    txtSenhUsu.setText(null);
                    cboCidade.setSelectedItem("Selecione um Item");
                    cboLoja.setSelectedItem("Selecione um Item");
                    cboPerfil.setSelectedItem("Nivel de acesso");
                    txtId.setText(null);
                }
            }
            //pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void adicionarCostu() {
        String sql = "insert into costureira (nome_c,cidade_c,loja_c) values (?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomcost.getText().toUpperCase());
            pst.setString(2, cboCidadeCostu.getSelectedItem().toString());
            pst.setString(3, cboLojaCost.getSelectedItem().toString());

//validação dos campos obrigatorios
            if ((txtNomcost.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
            } else {

// alinha abaixo atualiza o banco de dados com os dados do formulario
                //estrutura que confirma a inserção dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Costureira  Adicionado :)");
                    txtNomcost.setText(null);
                    cboCidadeCostu.setSelectedItem("Selecione um Item");
                    cboLojaCost.setSelectedItem("Selecione um Item");

                }
            }
            //pst.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    private void alterar() {
        String sql = "update funcionario set nome_f=?,login_f=?,senha_f=?,cidade_f=?,loja_f=? where id_F=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNomUsu.getText());
            pst.setString(2, txtLogUsu.getText());
            pst.setString(3, txtSenhUsu.getText());
            pst.setString(4, cboCidade.getSelectedItem().toString());
            pst.setString(5, cboLoja.getSelectedItem().toString());
            pst.setString(6, txtId.getText());

            if ((txtId.getText().isEmpty()) || (txtNomUsu.getText().isEmpty()) || (txtLogUsu.getText().isEmpty()) || (txtSenhUsu.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatorios");
            } else {

// alinha abaixo atualiza o banco de dados com os dados do formulario
                //estrutura que confirma a alteração dos dados na tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados Do Usuario Alterado  :)");
                    txtNomUsu.setText(null);
                    txtLogUsu.setText(null);
                    txtSenhUsu.setText(null);
                    txtId.setText(null);
                    cboCidade.setSelectedItem("Selecione um Item");
                    cboLoja.setSelectedItem("Selecione um Item");
                    cboPerfil.setSelectedItem("Nivel de acesso");

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    // metodo deletar usuario
    private void remover() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que deseja remover usuario", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from funcionario where id_F=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuario Removido con sucesso");
                    txtNomUsu.setText(null);
                    txtLogUsu.setText(null);
                    txtSenhUsu.setText(null);
                    txtId.setText(null);
                    cboCidade.setSelectedItem("Selecione um Item");
                    cboLoja.setSelectedItem("Selecione um Item");
                    cboPerfil.setSelectedItem("Nivel de acesso");

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void consultar() {
        String sql = "select * from funcionario where id_F = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtId.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                txtNomUsu.setText(rs.getString(1));
                txtLogUsu.setText(rs.getString(2));
                txtSenhUsu.setText(rs.getString(3));

                // a linha abaixo se refere a combo box
                cboCidade.setSelectedItem(rs.getString(4));
                cboLoja.setSelectedItem(rs.getString(5));
                cboPerfil.setSelectedItem(rs.getString(6));
                btnSalvar.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "Usuário Não Cadastrado");
                //limpar codigo dos campos
                txtNomUsu.setText(null);
                txtLogUsu.setText(null);
                txtSenhUsu.setText(null);
                txtId.setText(null);
                cboCidade.setSelectedItem("Selecione um Item");
                cboLoja.setSelectedItem("Selecione um Item");
                cboPerfil.setSelectedItem("Nivel de acesso");

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

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cboPerfil = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnSalvar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnExcluir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnPesquis = new javax.swing.JButton();
        txtNomUsu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtLogUsu = new javax.swing.JTextField();
        txtId = new javax.swing.JTextField();
        cboLoja = new javax.swing.JComboBox<>();
        cboCidade = new javax.swing.JComboBox<>();
        txtSenhUsu = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtId1 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtNomcost = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtLogUsu1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtSenhUsu1 = new javax.swing.JPasswordField();
        jLabel12 = new javax.swing.JLabel();
        cboLojaCost = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cboCidadeCostu = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cboPerfil1 = new javax.swing.JComboBox<>();
        btnSalvar1 = new javax.swing.JButton();
        btnEditar1 = new javax.swing.JButton();
        btnExcluir1 = new javax.swing.JButton();
        btnPesquis1 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cadastro de Usúarios");
        setMinimumSize(new java.awt.Dimension(544, 314));

        jLabel1.setText("Nome :");

        cboPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nivel de acesso", "admin", "user" }));

        jLabel2.setText("Login :");

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/cliAdd.png"))); // NOI18N
        btnSalvar.setPreferredSize(new java.awt.Dimension(60, 60));
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        jLabel3.setText("Senha :");

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/EditCli.png"))); // NOI18N
        btnEditar.setPreferredSize(new java.awt.Dimension(60, 60));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        jLabel4.setText("Loja :");

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/RemoveCli.png"))); // NOI18N
        btnExcluir.setPreferredSize(new java.awt.Dimension(60, 60));
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        jLabel5.setText("Cidade :");

        btnPesquis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/procurar.png"))); // NOI18N
        btnPesquis.setPreferredSize(new java.awt.Dimension(60, 60));
        btnPesquis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisActionPerformed(evt);
            }
        });

        jLabel7.setText("ID :");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        cboLoja.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um Item", "Principal", "Filial" }));

        cboCidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um Item", "Ivaiporã", "Jardim Alegre" }));
        cboCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCidadeActionPerformed(evt);
            }
        });

        jLabel6.setText("Perfil :");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenhUsu)
                            .addComponent(txtLogUsu)
                            .addComponent(txtNomUsu, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnPesquis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(24, 24, 24)
                                .addComponent(cboLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 83, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNomUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtLogUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtSenhUsu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cboLoja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cboPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquis, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Usúarios", jPanel1);

        jLabel8.setText("ID :");

        txtId1.setEditable(false);
        txtId1.setEnabled(false);
        txtId1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtId1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Nome :");

        jLabel10.setText("Login :");

        txtLogUsu1.setEditable(false);
        txtLogUsu1.setEnabled(false);

        jLabel11.setText("Senha :");

        txtSenhUsu1.setEditable(false);
        txtSenhUsu1.setEnabled(false);

        jLabel12.setText("Loja :");

        cboLojaCost.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um Item", "Principal", "Filial" }));

        jLabel13.setText("Cidade :");

        cboCidadeCostu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione um Item", "Ivaiporã", "Jardim Alegre" }));
        cboCidadeCostu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCidadeCostuActionPerformed(evt);
            }
        });

        jLabel14.setText("Perfil :");

        cboPerfil1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nivel de acesso", "admin", "user" }));
        cboPerfil1.setEnabled(false);

        btnSalvar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/cliAdd.png"))); // NOI18N
        btnSalvar1.setPreferredSize(new java.awt.Dimension(60, 60));
        btnSalvar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvar1ActionPerformed(evt);
            }
        });

        btnEditar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/EditCli.png"))); // NOI18N
        btnEditar1.setPreferredSize(new java.awt.Dimension(60, 60));
        btnEditar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditar1ActionPerformed(evt);
            }
        });

        btnExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/RemoveCli.png"))); // NOI18N
        btnExcluir1.setPreferredSize(new java.awt.Dimension(60, 60));
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });

        btnPesquis1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/tropical/Imagens/procurar.png"))); // NOI18N
        btnPesquis1.setPreferredSize(new java.awt.Dimension(60, 60));
        btnPesquis1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquis1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSenhUsu1)
                            .addComponent(txtLogUsu1)
                            .addComponent(txtNomcost, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(btnSalvar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(btnEditar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(btnPesquis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(24, 24, 24)
                                .addComponent(cboLojaCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboCidadeCostu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboPerfil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 83, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtId1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtNomcost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(txtLogUsu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtSenhUsu1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cboLojaCost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(cboCidadeCostu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(cboPerfil1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalvar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquis1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        jTabbedPane1.addTab("Costureiras", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setBounds(400, 100, 627, 393);
    }// </editor-fold>//GEN-END:initComponents

    private void cboCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCidadeActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        // Adicionar usuario em db
        adicionar();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // editar
        alterar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        // excluir
        remover();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnPesquisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisActionPerformed
        // procurar
        consultar();

    }//GEN-LAST:event_btnPesquisActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void cboCidadeCostuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCidadeCostuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCidadeCostuActionPerformed

    private void btnSalvar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvar1ActionPerformed
        // adicionar costureira
        this.adicionarCostu();
    }//GEN-LAST:event_btnSalvar1ActionPerformed

    private void btnEditar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditar1ActionPerformed

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnExcluir1ActionPerformed

    private void btnPesquis1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquis1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquis1ActionPerformed

    private void txtId1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtId1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEditar1;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnPesquis;
    private javax.swing.JButton btnPesquis1;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton btnSalvar1;
    private javax.swing.JComboBox<String> cboCidade;
    private javax.swing.JComboBox<String> cboCidadeCostu;
    private javax.swing.JComboBox<String> cboLoja;
    private javax.swing.JComboBox<String> cboLojaCost;
    private javax.swing.JComboBox<String> cboPerfil;
    private javax.swing.JComboBox<String> cboPerfil1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtId1;
    private javax.swing.JTextField txtLogUsu;
    private javax.swing.JTextField txtLogUsu1;
    private javax.swing.JTextField txtNomUsu;
    private javax.swing.JTextField txtNomcost;
    private javax.swing.JPasswordField txtSenhUsu;
    private javax.swing.JPasswordField txtSenhUsu1;
    // End of variables declaration//GEN-END:variables
}
