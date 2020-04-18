/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.venda;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.cliente.ClienteDAO;
import DAO.venda.ParcelaDAO;
import Modelo.venda.ModeloParcela;
import Visao.pedidoLocacao.Locacao;
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
 * @author usuario
 */
public class ListaLocacao extends javax.swing.JFrame {

    /**
     * Creates new form ListaLocacao
     */
    public ListaLocacao() {
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableLocacao = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextFieldMes = new javax.swing.JTextField();
        jTextFieldIDAno = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableLocacao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableLocacao.setFocusable(false);
        jScrollPane3.setViewportView(jTableLocacao);

        jLabel1.setText("Mês");

        jButton1.setText("Listar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Ano");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldMes, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldIDAno, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(695, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jTextFieldMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIDAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(411, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addGap(0, 51, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Connection connection;
        try {
            connection = new ConnectionFactory().getConnection();

            ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
            ClienteDAO clienteDAO = new ClienteDAO(connection);

            List<ModeloParcela> modeloParcelas = new ArrayList<ModeloParcela>();
            modeloParcelas = parcelaDAO.listaLocacaoPorMes(Integer.parseInt(jTextFieldMes.getText()), Integer.parseInt(jTextFieldIDAno.getText()));

            String[] colunas = new String[]{"ID", "Nome", "Referencia", "Valor", "Condição", "Data Vencimento", "Data Pagamento"};
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

                    dados.add(new Object[]{v.getId(),clienteDAO.buscaPorId(v.getIdCliente()).getNome(), logica.Duplicata.gerarReferencia(v.getId()), v.getValor(), "Pago", format.format(v.getDataVencimento().getTime()), format.format(v.getDataPagamento())});
                } else if (!v.isEstatosParcela()) {
                    dados.add(new Object[]{v.getId(),clienteDAO.buscaPorId(v.getIdCliente()).getNome(),  logica.Duplicata.gerarReferencia(v.getId()), v.getValor(), "Não Pago", format.format(v.getDataVencimento().getTime()), "00/00/0000"});
                }
            }
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            jTableLocacao.setModel(modelo);
            jTableLocacao.setRowSorter(new TableRowSorter(modelo));
            jTableLocacao.getColumnModel().getColumn(0).setPreferredWidth(2);
            jTableLocacao.getColumnModel().getColumn(0).setResizable(false);
            jTableLocacao.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTableLocacao.getColumnModel().getColumn(1).setResizable(false);
            jTableLocacao.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableLocacao.getColumnModel().getColumn(2).setResizable(false);
            jTableLocacao.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableLocacao.getColumnModel().getColumn(3).setResizable(false);
            jTableLocacao.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableLocacao.getColumnModel().getColumn(4).setResizable(false);
            connection.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);

            Logger.getLogger(Locacao.class
                    .getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ListaLocacao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaLocacao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaLocacao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaLocacao.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaLocacao().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableLocacao;
    private javax.swing.JTextField jTextFieldIDAno;
    private javax.swing.JTextField jTextFieldMes;
    // End of variables declaration//GEN-END:variables
}
