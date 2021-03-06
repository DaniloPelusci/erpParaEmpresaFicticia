/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.pedidoLocacao;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.TipoAcaoDAO;
import DAO.cliente.ClienteDAO;
import DAO.venda.ParcelaDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.venda.ModeloParcela;
import Visao.venda.Venda;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author E.B.O 2
 */
public class Multlocacao extends javax.swing.JFrame {

    private List<ModeloParcela> modeloParcelas = new ArrayList<>();

    /**
     * Creates new form Multlocaçao
     */
    public Multlocacao() {
        initComponents();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar horaAtual = Calendar.getInstance();

        // Ou qualquer outra forma que tem
        String dataFormatada = sdf.format(horaAtual.getTime());

        dataFormatada = dataFormatada.substring(2);
        dataFormatada = "12" + dataFormatada;

        jFormattedTextFieldDATA.setText(dataFormatada);

        jTextFieldTotal.setEnabled(false);
        jTextFieldCilindro.setEnabled(false);
        jTextFieldConcentrador.setEnabled(false);
        jTextFieldPeça.setEnabled(false);
        jTextFieldNomeCliente.setEnabled(false);
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
        jLabel2 = new javax.swing.JLabel();
        jTextFieldIDCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldValorUnitario = new javax.swing.JTextField();
        jCheckBoxCilindro = new javax.swing.JCheckBox();
        jTextFieldCilindro = new javax.swing.JTextField();
        jCheckBoxPeca = new javax.swing.JCheckBox();
        jTextFieldPeça = new javax.swing.JTextField();
        jCheckBoxConcentrador = new javax.swing.JCheckBox();
        jTextFieldConcentrador = new javax.swing.JTextField();
        jTextFieldTotal = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jFormattedTextFieldDATA = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("MultLocação");

        jLabel2.setText("ID Cliente");

        jTextFieldIDCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDClienteFocusLost(evt);
            }
        });

        jLabel1.setText("Valor Unitario");

        jCheckBoxCilindro.setText("Cilindro");

        jCheckBoxPeca.setText("Peça");

        jCheckBoxConcentrador.setText("Concentrador");
        jCheckBoxConcentrador.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jCheckBoxConcentradorFocusLost(evt);
            }
        });

        jLabel5.setText("Total");

        jButton2.setText("Salvar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTableProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableProduto.setFocusable(false);
        jScrollPane3.setViewportView(jTableProduto);

        jButton3.setText("Adicionar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nome:");

        jButton4.setText("+");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        try {
            jFormattedTextFieldDATA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel6.setText("Data inical de referencia:");

        jButton5.setText("Remover locação");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBoxCilindro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldCilindro))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxPeca)
                            .addComponent(jTextFieldPeça, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jCheckBoxConcentrador, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldConcentrador, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel5)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldDATA, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldIDCliente)
                                    .addGap(29, 29, 29)
                                    .addComponent(jLabel4)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton5)
                            .addComponent(jButton3))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextFieldDATA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxCilindro)
                            .addComponent(jCheckBoxPeca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldPeça, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCilindro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxConcentrador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldConcentrador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(14, 14, 14)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //para saber que é uma locação o tipodeparcela seria false
        try {
            Connection connection = ConnectionFactory.getConnection();
            ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
            ClienteDAO clienteDao = new ClienteDAO(connection);

            if (!(modeloParcelas.size() == 0)) {
                for (ModeloParcela mp : modeloParcelas) {
                    System.err.println(mp.getId());
                    System.err.println(mp.getIdCliente());
                    parcelaDAO.adicionaLocacao(mp);

                    clienteDao.atualizarLocacao(mp.getIdCliente(), mp.getValorUnitario());
                }

            } else {
                JOptionPane.showMessageDialog(null, "Lista de Locação vazia", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            connection.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(GeracaoLocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldIDClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDClienteFocusLost
        if (jTextFieldIDCliente.getText().isEmpty() || !logica.LogicasPedido.isInt(jTextFieldIDCliente.getText())) {

        } else {
            try {

                Connection connection = ConnectionFactory.getConnection();
                ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
                ClienteDAO clienteDAO = new ClienteDAO(connection);
                if (clienteDAO.verificacao(Long.parseLong(jTextFieldIDCliente.getText()))) {

                    ModeloCliente modeloCliente = new ModeloCliente();
                    modeloCliente = clienteDAO.buscaPorId(Long.parseLong(jTextFieldIDCliente.getText()));
                    jTextFieldNomeCliente.setText(modeloCliente.getNome());
                    BigDecimal valor = clienteDAO.valorultimaLocacao(Long.parseLong(jTextFieldIDCliente.getText()));
                    jTextFieldValorUnitario.setText(String.valueOf(valor));
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    System.err.println(clienteDAO.ultimaDataLocacao(Long.parseLong(jTextFieldIDCliente.getText())));
                    if (clienteDAO.ultimaDataLocacao(Long.parseLong(jTextFieldIDCliente.getText())) != 0) {
                        Date dataLocacao = parcelaDAO.buscaPorId(clienteDAO.ultimaDataLocacao(Long.parseLong(jTextFieldIDCliente.getText()))).getDataVencimento();

                        String dataLocacaoString = sdf.format(dataLocacao);
                        if (logica.LogicasPedido.validaData(dataLocacaoString)) {
                            SimpleDateFormat dd = new SimpleDateFormat("dd");
                            dataLocacaoString = dd.format(dataLocacao.getTime());
                            String dataTela = jFormattedTextFieldDATA.getText();

                            dataTela = dataTela.substring(2);
                            dataTela = dataLocacaoString + dataTela;
                            jFormattedTextFieldDATA.setText(dataTela);
                        }

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "ID do cliente invalido");

                }
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            lastro();
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Multlocacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTextFieldIDClienteFocusLost

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {

            Connection connection = ConnectionFactory.getConnection();
            ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
            ModeloParcela modeloParcela = new ModeloParcela();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            if (logica.LogicasPedido.validaData(jFormattedTextFieldDATA.getText())) {
                String stringDataSalvar = jFormattedTextFieldDATA.getText();

                Date dataVencimento = new Date(format.parse(stringDataSalvar).getTime());

                Calendar cal = Calendar.getInstance();
                cal.setTime(dataVencimento);

                //cal.add(cal.MONTH, 1);
                String oi = format.format(cal.getTime());
                dataVencimento = format.parse(oi);

                modeloParcela.setTipoParcela(false);
                modeloParcela.setDataVencimento(dataVencimento);

                modeloParcela.setIdCliente(Long.parseLong(jTextFieldIDCliente.getText()));

                int cont = 0;
                for (ModeloParcela v : modeloParcelas) {
                    if (v.getIdCliente() == modeloParcela.getIdCliente()) {

                        cont += 1;

                        break;
                    }
                }
                if (!(cont > 0)) {
                    if (!jTextFieldTotal.getText().isEmpty()) {
                        if (!parcelaDAO.verificaparcela(modeloParcela)) {
                            BigDecimal b = new BigDecimal(jTextFieldTotal.getText());
                            BigDecimal bd2 = b.setScale(2, BigDecimal.ROUND_HALF_UP);
                            modeloParcela.setValor(bd2);
                            BigDecimal c = new BigDecimal(jTextFieldValorUnitario.getText());

                            modeloParcela.setValorUnitario(c);
                            this.modeloParcelas.add(modeloParcela);
                            atualizatabela();
                            limparCampos();
                            jButton4.requestFocus();;

                        } else {
                            JOptionPane.showMessageDialog(null, "Já tem locação gerada neste mes", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "Valor não gerado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Já possui locação para esse cliente na lista", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "data invalida", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
            connection.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(GeracaoLocacao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(GeracaoLocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        new PesquisaClientemultlocacao(null, true, this).setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int idtabela = Integer.parseInt("" + jTableProduto.getValueAt(jTableProduto.getSelectedRow(), 0));

        for (int i = 0; i < modeloParcelas.size(); i++) {
            if (idtabela == modeloParcelas.get(i).getIdCliente()) {

                modeloParcelas.remove(i);

            }
        }
        try {
            atualizatabela();
        } catch (SQLException ex) {
            Logger.getLogger(Multlocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jCheckBoxConcentradorFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jCheckBoxConcentradorFocusLost
        double multiplicador = 0;
        double valorUnitario = 0;
        double total = 0;
        if (!(jTextFieldValorUnitario.getText().equals(""))) {
            if ((jCheckBoxCilindro.isSelected() || jCheckBoxConcentrador.isSelected() || jCheckBoxPeca.isSelected())) {
                if (jCheckBoxCilindro.isSelected()) {

                    if (Integer.parseInt(jTextFieldCilindro.getText()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Não ha cilindro locado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        multiplicador = Double.parseDouble(jTextFieldCilindro.getText());
                        valorUnitario = Double.parseDouble(jTextFieldValorUnitario.getText());
                        total += multiplicador * valorUnitario;

                    }
                }
                if (jCheckBoxConcentrador.isSelected()) {
                    if (Integer.parseInt(jTextFieldConcentrador.getText()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Não ha concentrador locado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        multiplicador = Double.parseDouble(jTextFieldConcentrador.getText());
                        valorUnitario = Double.parseDouble(jTextFieldValorUnitario.getText());
                        total += multiplicador * valorUnitario;

                    }
                }
                if (jCheckBoxPeca.isSelected()) {
                    if (Integer.parseInt(jTextFieldPeça.getText()) <= 0) {
                        JOptionPane.showMessageDialog(null, "Não ha peça locado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        multiplicador = Double.parseDouble(jTextFieldPeça.getText());
                        valorUnitario = Double.parseDouble(jTextFieldValorUnitario.getText());
                        total += multiplicador * valorUnitario;

                    }

                }
                jTextFieldTotal.setText("" + total);

            } else {
                JOptionPane.showMessageDialog(null, "Marque o item do aluguel", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Insira o valor unitario", "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_jCheckBoxConcentradorFocusLost

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
            java.util.logging.Logger.getLogger(Multlocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Multlocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Multlocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Multlocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Multlocacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCheckBoxCilindro;
    private javax.swing.JCheckBox jCheckBoxConcentrador;
    private javax.swing.JCheckBox jCheckBoxPeca;
    private javax.swing.JFormattedTextField jFormattedTextFieldDATA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JTextField jTextFieldCilindro;
    private javax.swing.JTextField jTextFieldConcentrador;
    private javax.swing.JTextField jTextFieldIDCliente;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldPeça;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextField jTextFieldValorUnitario;
    // End of variables declaration//GEN-END:variables

    private void atualizatabela() throws SQLException {
        String[] colunas = new String[]{"ID Cliente", "Nome Cliente", "Referencia", "Valor", "Valor Unitario"};
        ArrayList dados = new ArrayList();
        Connection connection = new ConnectionFactory().getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        TipoAcaoDAO tipoAcaoDAO = new TipoAcaoDAO(connection);
        Collections.reverse(modeloParcelas);
        for (ModeloParcela mp : modeloParcelas) {

            dados.add(new Object[]{mp.getIdCliente(), clienteDAO.buscaPorId(mp.getIdCliente()).getNome(), logica.Duplicata.gerarReferenciaForaBanco(mp), mp.getValor(), mp.getValorUnitario()});
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableProduto.setModel(modelo);
        jTableProduto.setRowSorter(new TableRowSorter(modelo));
        jTableProduto.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTableProduto.getColumnModel().getColumn(0).setResizable(false);
        jTableProduto.getColumnModel().getColumn(1).setPreferredWidth(20);
        jTableProduto.getColumnModel().getColumn(1).setResizable(false);
        jTableProduto.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTableProduto.getColumnModel().getColumn(2).setResizable(false);

    }

    private void limparCampos() {
        jTextFieldIDCliente.setText("");
        jTextFieldNomeCliente.setText("");
        jTextFieldPeça.setText("");
        jTextFieldTotal.setText("");
        jTextFieldValorUnitario.setText("");
        jTextFieldCilindro.setText("");
        jTextFieldConcentrador.setText("");

    }

    private void lastro() throws ParseException {
        try {

            jTextFieldTotal.setText("");
            jTextFieldCilindro.setText("");
            jTextFieldConcentrador.setText("");
            jTextFieldPeça.setText("");

            //Date dataAtual = new Date(System.currentTimeMillis());
            Connection connection;

            connection = ConnectionFactory.getConnection();

            TipoAcaoDAO tipoAcaoDAO = new TipoAcaoDAO(connection);
            int resultCilindros = 0;
            int resultConcentrador = 0;
            int resultPecas = 0;
            if (!jTextFieldIDCliente.getText().isEmpty()) {
                int locacaoCilindro = tipoAcaoDAO.somaCilindroLocacaoCliente(Integer.parseInt(jTextFieldIDCliente.getText()));
                int devolucaoCilindro = tipoAcaoDAO.somaCilindroDevolucaoCliente(Integer.parseInt(jTextFieldIDCliente.getText())) + tipoAcaoDAO.somaCilindroVendaCliente(Integer.parseInt(jTextFieldIDCliente.getText()));
                resultCilindros = locacaoCilindro - devolucaoCilindro;
                jTextFieldCilindro.setText("" + resultCilindros);
                int locacaoConcentrador = tipoAcaoDAO.somaConcentradorLocacaoCliente(Integer.parseInt(jTextFieldIDCliente.getText()));
                int devolucaoConcentrador = tipoAcaoDAO.somaConcentradorDevolucaoCliente(Integer.parseInt(jTextFieldIDCliente.getText())) + tipoAcaoDAO.somaConcentradorVendaClienteCliente(Integer.parseInt(jTextFieldIDCliente.getText()));
                resultConcentrador = locacaoConcentrador - devolucaoConcentrador;
                jTextFieldConcentrador.setText("" + resultConcentrador);
                int locacaoPecas = tipoAcaoDAO.somaPecasLocacaoCliente(Integer.parseInt(jTextFieldIDCliente.getText()));
                int devolucaopecas = tipoAcaoDAO.somaPecasDevolucaoCliente(Integer.parseInt(jTextFieldIDCliente.getText())) + tipoAcaoDAO.somaPecasVendaCliente(Integer.parseInt(jTextFieldIDCliente.getText()));
                resultPecas = locacaoPecas - devolucaopecas;
                jTextFieldPeça.setText("" + resultPecas);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Multlocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setRetornoConsultaCliente(String iDCliente, String nomeCliente) throws ParseException {

        this.jTextFieldIDCliente.setText(String.valueOf(iDCliente));
        this.jTextFieldNomeCliente.setText(String.valueOf(nomeCliente));
        lastro();
    }
}
