/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.pedidoLocacao;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.cliente.ClienteDAO;
import DAO.venda.ParcelaDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.venda.ModeloParcela;
import Visao.parcelas.Parcela;
import Visao.venda.Venda;
import Visao.venda.VendaView;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import logica.Duplicata;

/**
 *
 * @author E.B.O 2
 */
public class Locacao extends javax.swing.JFrame {

    /**
     * Creates new form Locacao
     */
    public Locacao() throws SQLException {
        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldIDCliente = new javax.swing.JTextField();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableParcelas = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("E.B.O. Lista Locação");

        jTextFieldIDCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDClienteFocusLost(evt);
            }
        });
        jTextFieldIDCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIDClienteActionPerformed(evt);
            }
        });

        jTextFieldNomeCliente.setEnabled(false);

        jLabel8.setText("Nome");

        jLabel6.setText("ID Cliente");

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTableParcelas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableParcelas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableParcelasMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableParcelas);

        jButton2.setText("Gerar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNomeCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1023, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldIDClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIDClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIDClienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new PesquisaCliente(null, true, this).setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Connection connection;
        try {
            connection = ConnectionFactory.getConnection();

            ClienteDAO clienteDAO = new ClienteDAO(connection);
            if (!jTextFieldIDCliente.getText().isEmpty() || !logica.LogicasPedido.isInt(jTextFieldIDCliente.getText())) {
                if (clienteDAO.verificacao(Long.parseLong(jTextFieldIDCliente.getText()))) {
                    try {
                        new GeracaoLocacao(Integer.parseInt(jTextFieldIDCliente.getText())).setVisible(true);
                    } catch (SQLException ex) {
                        Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Insira o cliente", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                }
            }
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTableParcelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableParcelasMouseClicked
        try {
            new Parcela(Long.parseLong("" + jTableParcelas.getValueAt(jTableParcelas.getSelectedRow(), 0))).setVisible(true);
            System.out.println(jTableParcelas.getValueAt(jTableParcelas.getSelectedRow(), 0));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableParcelasMouseClicked

    private void jTextFieldIDClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDClienteFocusLost
        if (jTextFieldIDCliente.getText().isEmpty() || !logica.LogicasPedido.isInt(jTextFieldIDCliente.getText())) {

        } else {
            try {

                Connection connection = ConnectionFactory.getConnection();
                ClienteDAO clienteDAO = new ClienteDAO(connection);
                if (clienteDAO.verificacao(Long.parseLong(jTextFieldIDCliente.getText()))) {

                    ModeloCliente modeloCliente = new ModeloCliente();
                    modeloCliente = clienteDAO.buscaPorId(Long.parseLong(jTextFieldIDCliente.getText()));
                    jTextFieldNomeCliente.setText(modeloCliente.getNome());
                } else {
                    JOptionPane.showMessageDialog(null, "ID do cliente invalido");

                }
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        listarParcelas();
    }//GEN-LAST:event_jTextFieldIDClienteFocusLost

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
            java.util.logging.Logger.getLogger(Locacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Locacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Locacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Locacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Locacao().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    void setRetornoConsultaCliente(String iDCliente, String nomeCliente) {

        this.jTextFieldIDCliente.setText(String.valueOf(iDCliente));
        this.jTextFieldNomeCliente.setText(String.valueOf(nomeCliente));
        listarParcelas();

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableParcelas;
    private javax.swing.JTextField jTextFieldIDCliente;
    private javax.swing.JTextField jTextFieldNomeCliente;
    // End of variables declaration//GEN-END:variables

    private void listarParcelas() {
        Connection connection;
        try {
            connection = new ConnectionFactory().getConnection();

            ParcelaDAO parcelaDAO = new ParcelaDAO(connection);

            List<ModeloParcela> modeloParcelas = new ArrayList<ModeloParcela>();
            modeloParcelas = parcelaDAO.listaLocacao(Long.parseLong(jTextFieldIDCliente.getText()));

            String[] colunas = new String[]{"ID", "Estado", "Data Vencimento", "Data Pagamento", "Valor", "Referencia"};
            ArrayList dados = new ArrayList();
            Date teste;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            Duplicata duplicata = new Duplicata();
            for (ModeloParcela v : modeloParcelas) {
                if (v.isEstatosParcela()) {
                    dados.add(new Object[]{v.getId(), "Pago",format.format(v.getDataVencimento()), format.format(v.getDataPagamento()),  v.getValor(),logica.Duplicata.gerarReferencia(v.getId())});
                } else if (!v.isEstatosParcela()) {
                    dados.add(new Object[]{v.getId(), "Não Pago",format.format(v.getDataVencimento().getTime()), "00/00/0000",  v.getValor(),logica.Duplicata.gerarReferencia(v.getId())});
                }
            }
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            jTableParcelas.setModel(modelo);
            jTableParcelas.setRowSorter(new TableRowSorter(modelo));
            jTableParcelas.getColumnModel().getColumn(0).setPreferredWidth(2);
            jTableParcelas.getColumnModel().getColumn(0).setResizable(false);
            jTableParcelas.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTableParcelas.getColumnModel().getColumn(1).setResizable(false);
            jTableParcelas.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableParcelas.getColumnModel().getColumn(2).setResizable(false);
            jTableParcelas.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableParcelas.getColumnModel().getColumn(3).setResizable(false);
            jTableParcelas.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableParcelas.getColumnModel().getColumn(4).setResizable(false);
            jTableParcelas.getColumnModel().getColumn(5).setPreferredWidth(20);
            jTableParcelas.getColumnModel().getColumn(5).setResizable(false);
           
            connection.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Locacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
