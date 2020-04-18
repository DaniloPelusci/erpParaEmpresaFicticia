/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Endereco;

import java.sql.SQLException;
import javax.swing.JOptionPane;

import DAO.BairroDAO;
import DAO.CidadeDAO;
import DAO.ConnectionFactory;
import DAO.EstadoDAO;
import Modelo.ModeloBairro;
import Modelo.ModeloCidades;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Familia Nascimento
 */
public class FrmBairro extends javax.swing.JFrame {

    ModeloBairro ModeloBairro = new ModeloBairro();

    /**
     * Creates new form FrmBairros
     *
     * @throws java.sql.SQLException
     */
    public FrmBairro() throws SQLException {
        initComponents();
        java.util.List<ModeloCidades> cidades;
        jComboBoxCidades.removeAllItems();
        try {
            Connection Connection = ConnectionFactory.getConnection();
            BairroDAO BairroDao = new BairroDAO(Connection);
            CidadeDAO CidadeDao = new CidadeDAO(Connection);
            cidades = CidadeDao.lista();

            for (ModeloCidades c : cidades) {
                jComboBoxCidades.addItem(c);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            JOptionPane.showMessageDialog(null, " Falha no inicializar de dados \n ERRO :" + ex);
        }

        PreencherTabela();
    }

    public void PreencherTabela() throws SQLException {
        try (Connection connection = ConnectionFactory.getConnection()) {
            BairroDAO bairroDAO = new BairroDAO(connection);

            jTableBairros.setModel(bairroDAO.listaTabela());
            jTableBairros.setRowSorter(new TableRowSorter(bairroDAO.listaTabela()));
            jTableBairros.getColumnModel().getColumn(0).setPreferredWidth(3);
            jTableBairros.getColumnModel().getColumn(0).setResizable(false);
            jTableBairros.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTableBairros.getColumnModel().getColumn(1).setResizable(false);
            jTableBairros.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTableBairros.getColumnModel().getColumn(2).setResizable(false);
            jTableBairros.getTableHeader().setReorderingAllowed(false);
            connection.close();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCodigo = new javax.swing.JTextField();
        jTextFieldBairro = new javax.swing.JTextField();
        jComboBoxCidades = new javax.swing.JComboBox<>();
        jButtonSalvar = new javax.swing.JButton();
        jButtonNovo = new javax.swing.JButton();
        jButtonALTERAR = new javax.swing.JButton();
        jButtonPesquisar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonSAIR = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableBairros = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("E.B.O. Cadastro de Bairros");
        setResizable(false);

        jLabel1.setText("Codigo");

        jLabel2.setText("Nome do Bairro");

        jLabel3.setText("Cidade");

        jTextFieldCodigo.setToolTipText("Codigo");
        jTextFieldCodigo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextFieldCodigo.setEnabled(false);

        jTextFieldBairro.setToolTipText("Digite o nome da cidade");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.setToolTipText("Salvar");
        jButtonSalvar.setEnabled(false);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonNovo.setText("Novo");
        jButtonNovo.setToolTipText("Novo");
        jButtonNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovoActionPerformed(evt);
            }
        });

        jButtonALTERAR.setText("Alterar");
        jButtonALTERAR.setToolTipText("Alterar");
        jButtonALTERAR.setEnabled(false);
        jButtonALTERAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonALTERARActionPerformed(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.setToolTipText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jButtonExcluir.setText("Excluir");
        jButtonExcluir.setToolTipText("Excluir");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonSAIR.setText("Sair");
        jButtonSAIR.setToolTipText("Sair");
        jButtonSAIR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSAIRMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 342, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxCidades, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonNovo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonALTERAR)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonPesquisar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonSAIR)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSalvar)
                    .addComponent(jButtonNovo)
                    .addComponent(jButtonALTERAR)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jButtonExcluir)
                    .addComponent(jButtonSAIR))
                .addContainerGap())
        );

        jTableBairros.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableBairros.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableBairrosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableBairros);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSAIRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSAIRMouseClicked
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSAIRMouseClicked

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        Connection connection;
        try {
            connection = ConnectionFactory.getConnection();

            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            BairroDAO BairroDao = new BairroDAO(connection);
            //ModeloBairro ModeloBairro  = new ModeloBairro();
            ModeloCidades cidadeCombo = (ModeloCidades) jComboBoxCidades.getSelectedItem();
            ModeloBairro.setCidade(cidadeCombo.getCodig());
            ModeloBairro.setNome(jTextFieldBairro.getText().toUpperCase());

            if (validacao().isEmpty()) {
                if (BairroDao.varificaDuplicidadeAdicionar(ModeloBairro.getNome(), ModeloBairro.getCidade())) {
                    JOptionPane.showMessageDialog(rootPane, "ja existe este bairro");
                } else {
                    BairroDao.adiciona(ModeloBairro);
                    JOptionPane.showMessageDialog(rootPane, "Dados Inseridos Com Sucesso");
                    limpar();
                }
            } else {
                String message = validacao();

                JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.INFORMATION_MESSAGE);

            }
            connection.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            JOptionPane.showMessageDialog(null, " Falha no Armazenamento de dados \n ERRO :" + ex);
        }


    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovoActionPerformed
        jTextFieldBairro.setText("");
        jTextFieldCodigo.setText("");        // TODO add your handling code here:
        jButtonSalvar.setEnabled(true);


    }//GEN-LAST:event_jButtonNovoActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        BairroDAO BairroDao;
        try {
            BairroDao = new BairroDAO(ConnectionFactory.getConnection());
            jTableBairros.setModel(BairroDao.buscaLike(jTextFieldBairro.getText().toUpperCase()));
            jTableBairros.setRowSorter(new TableRowSorter(BairroDao.buscaLike(jTextFieldBairro.getText().toUpperCase())));
            jTableBairros.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTableBairros.getColumnModel().getColumn(0).setResizable(false);
            jTableBairros.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTableBairros.getColumnModel().getColumn(1).setResizable(false);
            jTableBairros.getColumnModel().getColumn(2).setPreferredWidth(40);
            jTableBairros.getColumnModel().getColumn(2).setResizable(false);
            jTableBairros.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(FrmBairro.class.getName()).log(Level.SEVERE, null, ex);
        }

        jButtonExcluir.setEnabled(true);
        jButtonALTERAR.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonALTERARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonALTERARActionPerformed
        Connection connection;
        try {
            connection = ConnectionFactory.getConnection(); // TODO add your handling code here:
            CidadeDAO CidadeDao = new CidadeDAO(connection);
            BairroDAO BairroDAO = new BairroDAO(connection);
            ModeloCidades cidadeCombo = (ModeloCidades) jComboBoxCidades.getSelectedItem();
            if (validacao().isEmpty()) {
                ModeloBairro.setCidade(cidadeCombo.getCodig());
                ModeloBairro.setCodBairro(Long.parseLong(jTextFieldCodigo.getText()));
                ModeloBairro.setNome(jTextFieldBairro.getText().toUpperCase());
                if (!(jTextFieldCodigo.getText().isEmpty())) {
                    if (BairroDAO.varificaDuplicidadeAlterar(ModeloBairro.getNome(), ModeloBairro.getCidade(), ModeloBairro.getCodBairro())) {
                        JOptionPane.showMessageDialog(rootPane, "ja existe este bairro");
                    } else {
                        BairroDAO.altera(ModeloBairro);
                        JOptionPane.showMessageDialog(rootPane, "Dados Inseridos Com Sucesso");
                        limpar();
                    }

                } else {
                    String message = validacao();

                    JOptionPane.showMessageDialog(rootPane, "empty");

                }
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(FrmBairro.class.getName()).log(Level.SEVERE, null, ex);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonALTERARActionPerformed

    private void jTableBairrosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableBairrosMouseClicked

        String Id = "" + jTableBairros.getValueAt(jTableBairros.getSelectedRow(), 0);        // TODO add your handling code here:
        String Nome_Bairro = "" + jTableBairros.getValueAt(jTableBairros.getSelectedRow(), 1);
        String Cidade = "" + jTableBairros.getValueAt(jTableBairros.getSelectedRow(), 2);

        ModeloBairro modeloBairro = new ModeloBairro();
        try {
            Connection connection = ConnectionFactory.getConnection();
            EstadoDAO estadoDao = new EstadoDAO(connection);
            BairroDAO bairroDAO = new BairroDAO(connection);
            modeloBairro = bairroDAO.buscaPorId(Long.parseLong(Id));

            jTextFieldCodigo.setText(Id);
            jTextFieldBairro.setText(modeloBairro.getNome());

            for (int i = 0; i < jComboBoxCidades.getItemCount(); ++i) {
                ModeloCidades modeloCidades = (ModeloCidades) jComboBoxCidades.getItemAt(i);

                if (modeloBairro.getCidade().equals(modeloCidades.getCodig())) {

                    jComboBoxCidades.setSelectedIndex(i);
                    break;

                }
            }
            jButtonALTERAR.setEnabled(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(FrmCidades.class.getName()).log(Level.SEVERE, null, ex);
        }
//  lutar pelos direitos é uma coisa agora tudo é Machismo!O cara não baixa a tampa do vaso é machista o cara não bebe cerveja no copo é machista tudo é machismo!TODO add your handling code here:
    }//GEN-LAST:event_jTableBairrosMouseClicked

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed

        try {
            Connection connection = ConnectionFactory.getConnection();
            BairroDAO bairroDAO = new BairroDAO(connection);
            ModeloBairro modeloBairro = new ModeloBairro();
            modeloBairro.setCodBairro(Long.parseLong(jTextFieldCodigo.getText()));

            bairroDAO.remove(modeloBairro);
            JOptionPane.showMessageDialog(rootPane, "Bairro excluido com Sucesso");

            PreencherTabela();//TODO add your handling code here:
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            JOptionPane.showConfirmDialog(rootPane, "Erro ao excluir os  Registro!!\n erro:" + ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(FrmBairro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmBairro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmBairro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmBairro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new FrmBairro().setVisible(true);
            } catch (SQLException ex) {
                
                Logger.getLogger(FrmBairro.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonALTERAR;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonNovo;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSAIR;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<Object> jComboBoxCidades;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableBairros;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCodigo;
    // End of variables declaration//GEN-END:variables

    private String validacao() {
        String validar = "";

        int cont = 0;

        if (jTextFieldBairro.getText().isEmpty()) {
            validar += "bairro ";
            cont++;
        }

        if (cont != 0) {
            validar = "Campos obrigatorios: \n" + validar;
        }
        return validar;
    }

    public void limpar() {
        jTextFieldBairro.setText("");
        jTextFieldCodigo.setText("");

    }
}
