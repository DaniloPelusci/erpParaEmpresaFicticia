/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.Boleto;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.boleto.BoletoDao;
import DAO.boleto.LiquidacaoDAO;
import DAO.venda.ParcelaDAO;
import Modelo.boleto.ModeloBoleto;
import Modelo.boleto.ModeloLiquidacaoBoleto;
import Modelo.venda.ModeloParcela;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author usuario
 */
public class remessaView extends javax.swing.JFrame {
    List<ModeloBoleto> modeloBoletosClass = new ArrayList<ModeloBoleto>();
    /**
     * Creates new form remessaView
     */
    public remessaView() {
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
        jTableClientes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableClientes.setFillsViewportHeight(true);
        jScrollPane3.setViewportView(jTableClientes);

        jButton1.setText("Pesquisar boleto");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Numero do documento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
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
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            tabelaboletos();
        } catch (SQLException ex) {
            Logger.getLogger(remessaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(remessaView.class.getName()).log(Level.SEVERE, null, ex);
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
            java.util.logging.Logger.getLogger(remessaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(remessaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(remessaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(remessaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new remessaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    

    private void tabelaboletos() throws SQLException, ParseException {
        Connection connection = new ConnectionFactory().getConnection();

        BoletoDao boletoDao = new BoletoDao(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ModeloParcela modeloParcela = new ModeloParcela();
        LiquidacaoDAO liquidacaoDAO = new LiquidacaoDAO(connection);
        ModeloLiquidacaoBoleto modeloLiquidacaoBoleto = new ModeloLiquidacaoBoleto();
        String liquidacao = "";
        ModeloBoleto modeloBoleto = new ModeloBoleto();
        modeloBoleto = boletoDao.buscarPorNumeroDocumento(Long.parseLong(jTextField1.getText()));

       
       
        
        String[] colunas = new String[]{"id","nossonumero", "idvenda", 
            "numerodocumento", "datavencimento", "dataemissao",
            "valortitulo",  "emitido"};
        ArrayList dados = new ArrayList();

        Calendar data = Calendar.getInstance();

            String emissao="";
            System.out.println("id: " + modeloBoleto.getId());
            System.out.println("ocorrencia " + modeloBoleto.getCodOcorrencia());
            System.out.println("liquidacao :" + liquidacao);
            System.out.println("nosso Numero: " + modeloBoleto.getNossoNumero());
            System.out.println("idVenda: " + modeloParcela.getIdVenda());
            System.out.println("data ocorrencia: " + modeloBoleto.getDataocorrencia());
            System.out.println("numDocumento" + modeloBoleto.getNumDocumento());
            System.out.println("Data liquidacao Credito: " + modeloBoleto.getDataLiquidacaoCredito());
            System.out.println("vencimento" + modeloBoleto.getVencimento());
            System.out.println("data Emissao: " + modeloBoleto.getDataEmissao());
            System.out.println("valor Titudo: " + modeloBoleto.getValorTitulo());
            System.out.println("valor pago do titulo: " + modeloBoleto.getValorPagodotitulo());
            System.out.println("emitido: " + modeloBoleto.isEmitido());
            System.out.println("idPedido: " + modeloBoleto.getIdPedido());
            System.out.println(" .");
            if(modeloBoleto.isEmitido()==true){
                emissao="Sim";
            }else{
                emissao = "não";
            }

            modeloParcela = parcelaDAO.buscaPorId(modeloBoleto.getIdPedido());
            if (!modeloBoleto.getCodLiquidacao().equals(Long.parseLong("0"))) {
                liquidacao = liquidacaoDAO.buscaPorId(modeloBoleto.getCodLiquidacao()).getNome();

            } else {
                liquidacao = "Não liquidado";
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dados.add(new Object[]{modeloBoleto.getId(),  modeloBoleto.getNossoNumero(), modeloParcela.getIdVenda(),
                modeloBoleto.getNumDocumento(), format.format(modeloBoleto.getVencimento()), format.format(modeloBoleto.getDataEmissao()),
                modeloBoleto.getValorTitulo(),  emissao});

        
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableClientes.setModel(modelo);
        jTableClientes.setRowSorter(new TableRowSorter(modelo));
        jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(0).setResizable(false);
        jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(1).setResizable(false);
        jTableClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(2).setResizable(false);
        jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(3).setResizable(false);
        jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(4).setResizable(false);
        jTableClientes.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(5).setResizable(false);
        jTableClientes.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(6).setResizable(false);
        jTableClientes.getColumnModel().getColumn(7).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(7).setResizable(false);
       
        
        connection.close();
    }

    
}