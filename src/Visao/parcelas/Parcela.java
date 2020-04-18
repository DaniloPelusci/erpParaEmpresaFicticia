/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.parcelas;

import DAO.ConnectionFactory;
import DAO.cliente.ClienteDAO;
import DAO.venda.ChequeDAO;
import DAO.venda.ParcelaDAO;
import DAO.venda.TipoPagamentoDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.venda.ModeloParcela;
import Modelo.venda.ModeloTipoPagamento;
import Visao.Funcionario.Funcionario;
import Visao.venda.ChequeDialog;
import Visao.venda.VendaView;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author E.B.O 2
 */
public class Parcela extends javax.swing.JFrame {

    private ModeloParcela modeloParcelaframe;

    /**
     * Creates new form Parcela
     */
    public Parcela(Long idParcela) throws SQLException {
        initComponents();
        jButtonDeletarLocacao.setVisible(false);
        jButtonVenda.setVisible(false);
        jTextFieldidParcela.setEnabled(false);
        Connection connection = new ConnectionFactory().getConnection();

        TipoPagamentoDAO tipoPagamentoDAO = new TipoPagamentoDAO(connection);
        List<ModeloTipoPagamento> tipoPagamentos = tipoPagamentoDAO.lista();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ModeloCliente modeloCliente = new ModeloCliente();

        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ModeloParcela modeloParcela = new ModeloParcela();
        modeloParcela = parcelaDAO.buscaPorId(idParcela);
        modeloCliente = clienteDAO.buscaPorId(modeloParcela.getIdCliente());
        jTextFieldValorPago.setText("" + parcelaDAO.SomaTotalValorPagoPorParcela(idParcela));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        jFormattedTextDataVencimento.setText(format.format(modeloParcela.getDataVencimento()));
        if (modeloParcela.isEstatosParcela()) {
            jFormattedTextDataPagamento.setText(format.format(modeloParcela.getDataPagamento()));
        }
        jTextFieldValor.setText("" + modeloParcela.getValor());
        jTextFieldidParcela.setText("" + modeloParcela.getId());
        jTextFieldNomeCliente.setText("" + modeloCliente.getNome());
        Long idvenda = modeloParcela.getIdVenda();
        if (idvenda != 0) {
            jButtonVenda.setVisible(true);
        } else {
            jButtonDeletarLocacao.setVisible(true);
        }
        double restante = Double.parseDouble(jTextFieldValor.getText()) - parcelaDAO.SomaTotalValorPagoPorParcela(idParcela) ;
        jTextField1.setText(String.valueOf(restante));
        preencherTabela();

        modeloParcelaframe = modeloParcela;
    }

    private Parcela() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldValor = new javax.swing.JTextField();
        jTextFieldValorPago = new javax.swing.JTextField();
        jFormattedTextDataPagamento = new javax.swing.JFormattedTextField();
        jFormattedTextDataVencimento = new javax.swing.JFormattedTextField();
        jButtonSalvar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableCheques = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextFieldidParcela = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jButtonVenda = new javax.swing.JButton();
        jButtonDeletarLocacao = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("E.B.O. Parcela");

        jPanel1.setToolTipText("E.B.O. Parcela");

        jLabel1.setText("ID Parcela");

        jLabel2.setText("Valor");

        jLabel3.setText("Valor pago");

        jLabel4.setText("Data Pagamento");

        jLabel5.setText("Data Vencimento");

        jTextFieldValor.setEditable(false);
        jTextFieldValor.setEnabled(false);

        jTextFieldValorPago.setEnabled(false);

        try {
            jFormattedTextDataPagamento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextDataPagamento.setEnabled(false);

        jFormattedTextDataVencimento.setEditable(false);
        try {
            jFormattedTextDataVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextDataVencimento.setEnabled(false);

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jTableCheques.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableCheques.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableChequesMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(jTableCheques);

        jLabel8.setText("Cheque");

        jButton2.setText("Add pagamento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Remover pagamento");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setText("Gerar Duplicata");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Nome Cliente");

        jTextFieldNomeCliente.setEnabled(false);

        jButtonVenda.setText("Ir para a Venda");
        jButtonVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVendaActionPerformed(evt);
            }
        });

        jButtonDeletarLocacao.setText("Deletar Locação");
        jButtonDeletarLocacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarLocacaoActionPerformed(evt);
            }
        });

        jLabel7.setText("Restante");

        jTextField1.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNomeCliente)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldidParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel8)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonVenda)))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldValor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonSalvar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonDeletarLocacao, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jFormattedTextDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel6)
                    .addComponent(jButtonVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldidParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jFormattedTextDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSalvar)
                            .addComponent(jButtonDeletarLocacao))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableChequesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableChequesMouseClicked


    }//GEN-LAST:event_jTableChequesMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            new ChequeDialog(null, true, this, jTextFieldidParcela.getText()).setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTableCheques.getSelectedRow() != -1) {
            try {
                Connection connection = new ConnectionFactory().getConnection();
                ChequeDAO chequeDAO = new ChequeDAO(connection);
                int resultShowMSG = JOptionPane.showConfirmDialog(rootPane, "Tem certeza que quer deletar?");
                if (resultShowMSG == JOptionPane.YES_OPTION) {
                    chequeDAO.remove(Long.parseLong("" + jTableCheques.getValueAt(jTableCheques.getSelectedRow(), 0)));

                }
                preencherTabela();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Cheque não selecionado");
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        if (validacao().isEmpty()) {
            try {
                Connection connection = new ConnectionFactory().getConnection();
                ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
                ModeloParcela modeloParcela = new ModeloParcela();

                modeloParcela = modeloParcelaframe;

                BigDecimal b = new BigDecimal(jTextFieldValorPago.getText());

                String stringDataVendaSalvar = jFormattedTextDataPagamento.getText();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                Date dataSalvar = new Date(format.parse(stringDataVendaSalvar).getTime());
                modeloParcela.setDataPagamento(dataSalvar);
                modeloParcela.setEstatosParcela(true);

                parcelaDAO.altera(modeloParcela);
                dispose();

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String message = validacao();
            JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (!jTextFieldidParcela.getText().isEmpty()) {

                new GeraDuplicata(Long.parseLong(jTextFieldidParcela.getText())).setVisible(true);

            } else {
                JOptionPane.showMessageDialog(rootPane, "Selecione um Funcionario");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Funcionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVendaActionPerformed
        try {
            new VendaView(Integer.parseInt(String.valueOf(modeloParcelaframe.getIdVenda()))).setVisible(true);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonVendaActionPerformed

    private void jButtonDeletarLocacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarLocacaoActionPerformed

        try {
            if (logica.LogicasPedido.joptionSenha()) {
                Connection connection;
                try {
                    connection = new ConnectionFactory().getConnection();

                    ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
                    ChequeDAO chequeDAO = new ChequeDAO(connection);
                    Long idparcela = Long.parseLong(jTextFieldidParcela.getText());

                    chequeDAO.removeIdParcela(idparcela);
                    ModeloParcela modeloParcela = parcelaDAO.buscaPorId(idparcela);
                    parcelaDAO.remove(modeloParcela);
                    JOptionPane.showMessageDialog(rootPane, "Locação deletada com sucesso");

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex);
                    Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Senha Invalida");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Parcela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonDeletarLocacaoActionPerformed

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
            java.util.logging.Logger.getLogger(Parcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Parcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Parcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Parcela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Parcela().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonDeletarLocacao;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonVenda;
    private javax.swing.JFormattedTextField jFormattedTextDataPagamento;
    private javax.swing.JFormattedTextField jFormattedTextDataVencimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableCheques;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldValor;
    private javax.swing.JTextField jTextFieldValorPago;
    private javax.swing.JTextField jTextFieldidParcela;
    // End of variables declaration//GEN-END:variables
public void preencherTabela() throws SQLException {// preenchimento da tabela  ,  linhas e colunas , LuizDOURADO
        Connection connection;

        connection = new ConnectionFactory().getConnection();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);

        jTextFieldValorPago.setText("" + parcelaDAO.SomaTotalValorPagoPorParcela(Long.parseLong(jTextFieldidParcela.getText())));
        BigDecimal b = new BigDecimal(jTextFieldValorPago.getText());
        parcelaDAO.atualizarValorPago(b, Long.parseLong(jTextFieldidParcela.getText()));
        double janela = Double.parseDouble(jTextFieldValor.getText());
        double banco = parcelaDAO.SomaTotalValorPagoPorParcela(Long.parseLong(jTextFieldidParcela.getText()));
        if (banco == janela) {
            System.err.println(true);
        }
        System.out.println("da janela: " + Double.parseDouble(jTextFieldValor.getText()));

        System.out.println("do banco: " + parcelaDAO.SomaTotalValorPagoPorParcela(Long.parseLong(jTextFieldidParcela.getText())));
        if (parcelaDAO.SomaTotalValorPagoPorParcela(Long.parseLong(jTextFieldidParcela.getText())) == Double.parseDouble(jTextFieldValor.getText())) {
            parcelaDAO.parcelaPaga(Long.parseLong(jTextFieldidParcela.getText()));

        } else {
            parcelaDAO.parcelaNaoPaga(Long.parseLong(jTextFieldidParcela.getText()));

        }

        ChequeDAO chequeDAO = new ChequeDAO(connection);

        jTableCheques.setModel(chequeDAO.listaTabelaPorIDParcela(Long.parseLong(jTextFieldidParcela.getText())));
        jTableCheques.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTableCheques.getColumnModel().getColumn(0).setResizable(false);
        jTableCheques.getColumnModel().getColumn(1).setPreferredWidth(20);
        jTableCheques.getColumnModel().getColumn(1).setResizable(false);
        jTableCheques.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTableCheques.getColumnModel().getColumn(2).setResizable(false);
        jTableCheques.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTableCheques.getColumnModel().getColumn(3).setResizable(false);
        jTableCheques.getColumnModel().getColumn(4).setPreferredWidth(20);
        jTableCheques.getColumnModel().getColumn(4).setResizable(false);

        connection.close();

    }

    public void tranferenciaData(String data) throws SQLException {
        preencherTabela();
        Connection connection = ConnectionFactory.getConnection();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ModeloParcela modeloParcela = new ModeloParcela();
        modeloParcela = parcelaDAO.buscaPorId(Long.parseLong(jTextFieldidParcela.getText()));
        jTextFieldValorPago.setText("" + parcelaDAO.SomaTotalValorPagoPorParcela(Long.parseLong(jTextFieldidParcela.getText())));
        BigDecimal b = new BigDecimal(jTextFieldValorPago.getText());
        parcelaDAO.atualizarValorPago(b, Long.parseLong(jTextFieldidParcela.getText()));
        if (parcelaDAO.SomaTotalValorPagoPorParcela(Long.parseLong(jTextFieldidParcela.getText())) == Float.parseFloat(jTextFieldValor.getText())) {
            parcelaDAO.parcelaPaga(Long.parseLong(jTextFieldidParcela.getText()));

        } else {
            parcelaDAO.parcelaNaoPaga(Long.parseLong(jTextFieldidParcela.getText()));

        }
        jFormattedTextDataPagamento.setText(data);
        jButtonSalvar.doClick();
    }

    private String validacao() {

        String validar = "";

        int cont = 0;

        if (!logica.LogicasPedido.validaData(jFormattedTextDataPagamento.getText())) {
            validar += "\n" + "data invalida  ";

            cont++;

        }

        return validar;
    }

}