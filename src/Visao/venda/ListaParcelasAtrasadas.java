/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.venda;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.pedido.PedidoDAO;
import DAO.venda.ParcelaDAO;
import Modelo.Pedido.ModeloPedido;
import Modelo.venda.ModeloParcela;
import Visao.parcelas.Parcela;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author E.B.O 2
 */
public class ListaParcelasAtrasadas extends javax.swing.JFrame {

    /**
     * Creates new form ListaParcelas
     */
    public ListaParcelasAtrasadas() throws SQLException {
        initComponents();
        Connection connection = new ConnectionFactory().getConnection();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        List<ModeloParcela> modeloParcelaVendas = parcelaDAO.listaVendasVencidasSemana();
        List<ModeloParcela> modeloParcelaLocacoes = parcelaDAO.listaLocacoesVencidasSemana();
        listarParcelasVendas(modeloParcelaVendas);
        listarParcelasLocacao(modeloParcelaLocacoes);
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
        jScrollPane8 = new javax.swing.JScrollPane();
        jTableParcelasLocacaoNPagas = new javax.swing.JTable();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTableParcelasVendaNPagas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableParcelasLocacaoNPagas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableParcelasLocacaoNPagas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableParcelasLocacaoNPagasMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(jTableParcelasLocacaoNPagas);

        jTableParcelasVendaNPagas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableParcelasVendaNPagas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableParcelasVendaNPagasMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(jTableParcelasVendaNPagas);

        jLabel1.setText("Venda");

        jLabel2.setText("Locação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane8)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableParcelasLocacaoNPagasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableParcelasLocacaoNPagasMouseClicked
        try {
            new Parcela(Long.parseLong("" + jTableParcelasLocacaoNPagas.getValueAt(jTableParcelasLocacaoNPagas.getSelectedRow(), 0))).setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(ListaParcelasAtrasadas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableParcelasLocacaoNPagasMouseClicked

    private void jTableParcelasVendaNPagasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableParcelasVendaNPagasMouseClicked
      try {
            new Parcela(Long.parseLong("" + jTableParcelasVendaNPagas.getValueAt(jTableParcelasVendaNPagas.getSelectedRow(), 0))).setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(ListaParcelasAtrasadas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableParcelasVendaNPagasMouseClicked

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
            java.util.logging.Logger.getLogger(ListaParcelasAtrasadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaParcelasAtrasadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaParcelasAtrasadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaParcelasAtrasadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new ListaParcelasAtrasadas().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(ListaParcelasAtrasadas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTable jTableParcelasLocacaoNPagas;
    private javax.swing.JTable jTableParcelasVendaNPagas;
    // End of variables declaration//GEN-END:variables

private void listarParcelasVendas(List<ModeloParcela> modeloParcelas) throws SQLException {
        String[] colunas = new String[]{"ID", "N° da Venda", "Condição", "Valor", "Valor Pago", "Data Vencimento", "Data Pagamento", "Numero de Grafica"};
        ArrayList dados = new ArrayList();
        Date teste;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Connection connection = new ConnectionFactory().getConnection();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        PedidoDAO pedidoDAO = new PedidoDAO(connection);

        int numerografica = 0;
        for (ModeloParcela v : modeloParcelas) {
            ModeloPedido modeloPedido = pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(v.getIdVenda())));

            if (v.isEstatosParcela()) {

                dados.add(new Object[]{v.getId(), v.getIdVenda(), "Pago", v.getValor(), parcelaDAO.SomaTotalValorPagoPorParcela(v.getId()), format.format(v.getDataVencimento().getTime()), format.format(v.getDataPagamento().getTime()), modeloPedido.getNumeroGrafica()});
            } else if (!v.isEstatosParcela()) {
                dados.add(new Object[]{v.getId(), v.getIdVenda(), "Não Pago", v.getValor(), parcelaDAO.SomaTotalValorPagoPorParcela(v.getId()), format.format(v.getDataVencimento().getTime()), "00/00/0000", modeloPedido.getNumeroGrafica()});
            }
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableParcelasVendaNPagas.setModel(modelo);
        jTableParcelasVendaNPagas.setRowSorter(new TableRowSorter( modelo));
        jTableParcelasVendaNPagas.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(0).setResizable(false);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(1).setPreferredWidth(10);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(1).setResizable(false);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(2).setPreferredWidth(10);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(2).setResizable(false);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(3).setPreferredWidth(10);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(3).setResizable(false);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(4).setPreferredWidth(10);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(4).setResizable(false);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(5).setPreferredWidth(10);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(5).setResizable(false);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(6).setPreferredWidth(10);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(6).setResizable(false);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(7).setPreferredWidth(10);
        jTableParcelasVendaNPagas.getColumnModel().getColumn(7).setResizable(false);
        connection.close();
    }

private void listarParcelasLocacao(List<ModeloParcela> modeloParcelas) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();

            ParcelaDAO parcelaDAO = new ParcelaDAO(connection);

            
            

            String[] colunas = new String[]{"ID", "Referencia", "Valor", "Condição", "Data Vencimento", "Data Pagamento"};
            ArrayList dados = new ArrayList();
            Date teste;
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat format2 = new SimpleDateFormat("MM/yyyy");

            Calendar data = Calendar.getInstance();
            String referencia = "";
            for (ModeloParcela v : modeloParcelas) {
                if (v.isEstatosParcela()) {

                    data.setTime(v.getDataPagamento());
                    data.add(data.MONTH, -1);

                    referencia = v.getId() + "_" + format2.format(data.getTime());

                   
                } else if (!v.isEstatosParcela()) {
                    dados.add(new Object[]{v.getId(), logica.Duplicata.gerarReferencia(v.getId()), v.getValor(), "Não Pago", format.format(v.getDataVencimento().getTime()), "00/00/0000"});
                }
            }
            
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            jTableParcelasLocacaoNPagas.setModel(modelo);
            jTableParcelasLocacaoNPagas.setRowSorter(new TableRowSorter( modelo));
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(0).setPreferredWidth(2);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(0).setResizable(false);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(1).setResizable(false);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(2).setResizable(false);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(3).setResizable(false);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableParcelasLocacaoNPagas.getColumnModel().getColumn(4).setResizable(false);
            connection.close();
            
    }

}
