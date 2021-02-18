/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tropical.Telas;

import br.com.tropical.Dal.ModulodeConexao;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author RSC SYSTEMAS
 */
public final class TelaTabelaConsulta extends javax.swing.JInternalFrame {

    private static TelaTabelaConsulta telaTabelaConsulta;

    public static TelaTabelaConsulta getInstancia() throws IOException, SQLException {
        if (telaTabelaConsulta == null) {
            telaTabelaConsulta = new TelaTabelaConsulta();
        }
        return telaTabelaConsulta;
    }
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaTabelaConsulta
     */
    public TelaTabelaConsulta() throws IOException {
        initComponents();
        data();
        conexao = ModulodeConexao.conector();
        SemMaterial();
        Corte();
        Bordado();
        Pintura();
        costura();
        Finalizado();

    }

    public void SemMaterial() {
        String sql = " select pedido.id as Pedido,cliente.nome_c as Nome ,pedido.material as Material,pedido.obs_material as OBS,pedido.data_p as Data  from cliente join pedido on pedido.id_c = cliente.id_c where (pedido.material like \"Não\") order by cliente.nome_c asc";
        try {

            //----------render
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    String str = (String) value;
                    if ("Não".equals(str)) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.RED);
                    }
                    return c;
                }
            };
            //----------render
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            jtbSemMaterial.setModel(DbUtils.resultSetToTableModel(rs));
            //tbEntregas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jtbSemMaterial.getColumnModel().getColumn(0).setPreferredWidth(70);
            jtbSemMaterial.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtbSemMaterial.getColumnModel().getColumn(2).setPreferredWidth(65);
            jtbSemMaterial.getColumnModel().getColumn(3).setPreferredWidth(300);
            jtbSemMaterial.getColumnModel().getColumn(4).setPreferredWidth(70);
            jtbSemMaterial.getColumnModel().getColumn(1).setCellRenderer(renderer);

            rs.close();

        } catch (Exception e) {

        }
    }

    public void Corte() {
        String sql = " select pedido.id as Pedido,cliente.nome_c as Nome ,pedido.material as Material,pedido.corte as 'Esta em corte',pedido.data_p as Data  from cliente join pedido on pedido.id_c = cliente.id_c where (pedido.corte like \"Sim\") order by cliente.nome_c asc";
        try {

            //----------render
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    String str = (String) value;
                    if ("Não".equals(str)) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.RED);
                    }
                    return c;
                }
            };
            //----------render
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            jtCorte.setModel(DbUtils.resultSetToTableModel(rs));
            //tbEntregas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jtCorte.getColumnModel().getColumn(0).setPreferredWidth(70);
            jtCorte.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtCorte.getColumnModel().getColumn(2).setPreferredWidth(65);
            jtCorte.getColumnModel().getColumn(3).setPreferredWidth(300);
            jtCorte.getColumnModel().getColumn(4).setPreferredWidth(70);
            jtCorte.getColumnModel().getColumn(1).setCellRenderer(renderer);

            rs.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Bordado() {
        String sql = " select pedido.id as Pedido,cliente.nome_c as Nome ,pedido.material as Material,pedido.bordado_pintura as Setor,pedido.data_p as Data  from cliente join pedido on pedido.id_c = cliente.id_c where (pedido.bordado_pintura like \"Bordado\") order by cliente.nome_c asc";
        try {

            //----------render
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    String str = (String) value;
                    if ("Não".equals(str)) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.RED);
                    }
                    return c;
                }
            };
            //----------render
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            jtBordado.setModel(DbUtils.resultSetToTableModel(rs));
            //tbEntregas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jtBordado.getColumnModel().getColumn(0).setPreferredWidth(70);
            jtBordado.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtBordado.getColumnModel().getColumn(2).setPreferredWidth(65);
            jtBordado.getColumnModel().getColumn(3).setPreferredWidth(300);
            jtBordado.getColumnModel().getColumn(4).setPreferredWidth(70);
            jtBordado.getColumnModel().getColumn(1).setCellRenderer(renderer);

            rs.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Pintura() {
        String sql = " select pedido.id as Pedido,cliente.nome_c as Nome ,pedido.material as Material,pedido.bordado_pintura as Setor,pedido.data_p as Data  from cliente join pedido on pedido.id_c = cliente.id_c where (pedido.bordado_pintura like \"Pintura\") order by cliente.nome_c asc";
        try {

            //----------render
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    String str = (String) value;
                    if ("Não".equals(str)) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.RED);
                    }
                    return c;
                }
            };
            //----------render
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            jtPintura.setModel(DbUtils.resultSetToTableModel(rs));
            //tbEntregas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jtPintura.getColumnModel().getColumn(0).setPreferredWidth(70);
            jtPintura.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtPintura.getColumnModel().getColumn(2).setPreferredWidth(65);
            jtPintura.getColumnModel().getColumn(3).setPreferredWidth(300);
            jtPintura.getColumnModel().getColumn(4).setPreferredWidth(70);
            jtPintura.getColumnModel().getColumn(1).setCellRenderer(renderer);

            rs.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void costura() {
        String sql = "select pedido.id as Pedido,cliente.nome_c as Nome ,pedido.costura as Setor,pedido.costureira as Costureira   from cliente join pedido on pedido.id_c = cliente.id_c where (pedido.costura like \"sim\") order by cliente.nome_c asc";
        try {
            //----------render
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    String str = (String) value;
                    if ("Não".equals(str)) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.RED);
                    }
                    return c;
                }
            };
            //----------render
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            jtCostura.setModel(DbUtils.resultSetToTableModel(rs));
            jtCostura.getColumnModel().getColumn(0).setPreferredWidth(70);
            jtCostura.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtCostura.getColumnModel().getColumn(2).setPreferredWidth(65);
            jtCostura.getColumnModel().getColumn(3).setPreferredWidth(300);
            //jtCostura.getColumnModel().getColumn(4).setPreferredWidth(70);
            jtCostura.getColumnModel().getColumn(1).setCellRenderer(renderer);

            rs.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void Finalizado() {
        String sql = " select pedido.id as Pedido,cliente.nome_c as Nome ,pedido.finalizado as Finalizado,pedido.funcionario_p as Funcionario ,pedido.data_p as Data  from cliente join pedido on pedido.id_c = cliente.id_c where (pedido.finalizado like \"Sim\") order by cliente.nome_c asc";
        try {

            //----------render
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                    String str = (String) value;
                    if ("Não".equals(str)) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.RED);
                    }
                    return c;
                }
            };
            //----------render
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            jtFinalizado.setModel(DbUtils.resultSetToTableModel(rs));
            //tbEntregas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jtFinalizado.getColumnModel().getColumn(0).setPreferredWidth(70);
            jtFinalizado.getColumnModel().getColumn(1).setPreferredWidth(200);
            jtFinalizado.getColumnModel().getColumn(2).setPreferredWidth(65);
            jtFinalizado.getColumnModel().getColumn(3).setPreferredWidth(300);
            jtFinalizado.getColumnModel().getColumn(4).setPreferredWidth(70);
            jtFinalizado.getColumnModel().getColumn(1).setCellRenderer(renderer);

            rs.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Açoes de evento quando apertar o botão especifico
    public void Tmaterial() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem Material para o pedido?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String data = lblData.getText().toString();

            String sql = "update pedido set corte = \"Sim\",material=\"ok\",data_c =\"" + data + "\"  where id = ?";
            int row = jtbSemMaterial.getSelectedRow();
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jtbSemMaterial.getModel().getValueAt(row, 0).toString());
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Erro Contate Administrador do sistema" + e);
            }

        }
    }//fim tem material

    public void Tbordado_p() {
        String confirmap = "";
        confirmap = JOptionPane.showInputDialog("Digitepara qual setor vai:  \n 1-Bordado: \n 2-Pintura:");
        if ("1".equals(confirmap)) {
            String data = lblData.getText().toString();
            String sql = "update pedido set bordado_pintura = \"Bordado\",corte = \"ok\",material=\"ok\",data_b =\"" + data + "\" where id = ?";
            int row = jtCorte.getSelectedRow();

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jtCorte.getModel().getValueAt(row, 0).toString());
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Erro Contate Administrador do sistema" + e);
            }

        } else if ("2".equals(confirmap)) {
            String data = lblData.getText().toString();
            String sql = "update pedido set bordado_pintura = \"Pintura\",corte = \"ok\",material=\"ok\",data_pe =\"" + data + "\" where id = ?";
            int row = jtCorte.getSelectedRow();

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jtCorte.getModel().getValueAt(row, 0).toString());
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Erro Contate Administrador do sistema" + e);
            }
        } else {
            //JOptionPane.showMessageDialog(null, "erro grave ");

        }
    }//fim opçao bordado pintura

    public void Tpintura() {
        String data = lblData.getText().toString();
        String sql = "update pedido set bordado_pintura = \"Pintura\",corte = \"ok\",material=\"ok\",costura=\"sim\",data_pe =\"" + data + "\" where id = ?";
        int row = jtBordado.getSelectedRow();
        int confirma = JOptionPane.showConfirmDialog(null, "finalizar e ir para Pintura?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jtBordado.getModel().getValueAt(row, 0).toString());
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Erro Contate Administrador do sistema" + e);
            }
        }
    }//fim opçao pintura

    public void Tcostura() {

        int confirma = JOptionPane.showConfirmDialog(null, "Enviar para costura?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            try {
                String sql = "select * from costureira";
                pst = conexao.prepareStatement(sql);
                rs = pst.executeQuery();
                //cria um array
                ArrayList<String> nome = new ArrayList();
                while (rs.next()) {
                    //adiciona os dados do campo no db em uma variavel
                    nome.add(rs.getString("nome_c"));
                }
                //faz a exibiçao em forma de aviso   parte que deu trabalho  --->nome.toArray(), nome.get(0)
                Object ok = JOptionPane.showInputDialog(this, "Selecione a costureira ", "Costureira", JOptionPane.PLAIN_MESSAGE, null, nome.toArray(), nome.get(0));
                if (ok == null) {
                    JOptionPane.showMessageDialog(null, "Escolha pelomenos uma ");
                } else {
                    //armazena o item selecionado em uma variavel para uso
                    String costureira = (String) ok;
                    String data = lblData.getText().toString();
                    String sql2 = "update pedido set costura = \"sim\",costureira=\"" + costureira + "\",corte = \"ok\",material=\"ok\",bordado_pintura=\"ok\",data_co =\"" + data + "\"where id = ?";
                    int row = jtPintura.getSelectedRow();
                    if (confirma == JOptionPane.YES_OPTION) {
                        try {
                            pst = conexao.prepareStatement(sql2);
                            pst.setString(1, jtPintura.getModel().getValueAt(row, 0).toString());
                            pst.executeUpdate();

                        } catch (SQLException e) {
                            System.out.println("Erro Contate Administrador do sistema" + e);
                        }
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Não Foi Posivel encontrar usuarios no Banco de dados");
            }

        }
    }// fim opçao costura

    public void Finalizar_p() {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Finalizar  este Pedido ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String data = lblData.getText().toString();
            String sql = "update pedido set finalizado = \"sim\",costura = \"ok\",data_f =\"" + data + "\" where id = ?";
            int row = jtCostura.getSelectedRow();
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jtCostura.getModel().getValueAt(row, 0).toString());
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Erro Contate Administrador do sistema" + e);
            }

        }
    }//fim finalizar pedido

    public void Finalizar_e() {
        int confirma = JOptionPane.showConfirmDialog(null, "Deseja Finalizar  este Pedido ?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String data = lblData.getText().toString();
            String sql = "update pedido set finalizado = \"Entrege\",costura = \"ok\",data_fim =\"" + data + "\" where id = ?";
            int row = jtFinalizado.getSelectedRow();
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, jtFinalizado.getModel().getValueAt(row, 0).toString());
                pst.executeUpdate();

            } catch (SQLException e) {
                System.out.println("Erro Contate Administrador do sistema" + e);
            }

        }
    }// fim de tudo entregando o pedido

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbSemMaterial = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtCorte = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtBordado = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtFinalizado = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtCostura = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jtPintura = new javax.swing.JTable();
        btnRefresh = new javax.swing.JButton();
        lblData = new javax.swing.JLabel();

        setClosable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos sem Material"));

        jtbSemMaterial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtbSemMaterial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtbSemMaterialKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jtbSemMaterial);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos em corte"));

        jtCorte.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtCorte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCorteKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtCorte);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos em bordado"));

        jtBordado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtBordado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtBordadoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jtBordado);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos Finalizados"));

        jtFinalizado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtFinalizado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtFinalizadoKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(jtFinalizado);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("pedidos em Costura"));

        jtCostura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtCostura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtCosturaKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(jtCostura);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Pedidos em pintura"));

        jtPintura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jtPintura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtPinturaKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(jtPintura);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnRefresh.setText("Atualizar");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        lblData.setText("10/11/12");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblData)
                                        .addGap(108, 108, 108)))
                                .addComponent(btnRefresh))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(lblData)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        setBounds(175, 50, 1126, 596);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        //Atualizar
        SemMaterial();
        Corte();
        Bordado();
        Pintura();
        costura();
        Finalizado();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void jtCosturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCosturaKeyPressed
        //ao presionar enter
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Finalizar_p();
            Finalizado();
            costura();

        }
    }//GEN-LAST:event_jtCosturaKeyPressed

    private void jtbSemMaterialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtbSemMaterialKeyPressed
        // presionar enter em marterial
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tmaterial();
            SemMaterial();
            Corte();

        }
    }//GEN-LAST:event_jtbSemMaterialKeyPressed

    private void jtCorteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtCorteKeyPressed
        // presionar enter em corte
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tbordado_p();
            Corte();
            Bordado();
            Pintura();
        }

    }//GEN-LAST:event_jtCorteKeyPressed

    private void jtBordadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtBordadoKeyPressed
        //
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tpintura();
            Bordado();
            Pintura();
        }
    }//GEN-LAST:event_jtBordadoKeyPressed

    private void jtPinturaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtPinturaKeyPressed
        // enter em pintura
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Tcostura();
            Pintura();
            costura();
        }
    }//GEN-LAST:event_jtPinturaKeyPressed

    private void jtFinalizadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtFinalizadoKeyPressed
        // finalizar
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Finalizar_e();
            Finalizado();

        }
    }//GEN-LAST:event_jtFinalizadoKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRefresh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jtBordado;
    private javax.swing.JTable jtCorte;
    private javax.swing.JTable jtCostura;
    private javax.swing.JTable jtFinalizado;
    private javax.swing.JTable jtPintura;
    private javax.swing.JTable jtbSemMaterial;
    private javax.swing.JLabel lblData;
    // End of variables declaration//GEN-END:variables

    public void data() {
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
        lblData.setText(formatador.format(data));
    }
}
