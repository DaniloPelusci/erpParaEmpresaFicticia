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
import java.io.IOException;
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
import logica.teste;

/**
 *
 * @author usuario
 */
public class remessaViewTodos extends javax.swing.JFrame {
    List<ModeloBoleto> modeloBoletosClass = new ArrayList<ModeloBoleto>();
    /**
     * Creates new form remessaView
     */
    public remessaViewTodos() {
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
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jButton1.setText("Visualizar remessa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Gerar remessa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Limpar Boletos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1067, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(29, 29, 29))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jButton1)
                .addGap(82, 82, 82)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                Logger.getLogger(remessaViewTodos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(remessaViewTodos.class.getName()).log(Level.SEVERE, null, ex);
            }

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
           
            try {
                gerarRemessa();
            } catch (IOException ex) {
                Logger.getLogger(remessaViewTodos.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(remessaViewTodos.class.getName()).log(Level.SEVERE, null, ex);
            }
            

    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(remessaViewTodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(remessaViewTodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(remessaViewTodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(remessaViewTodos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new remessaViewTodos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables

    

    private void tabelaboletos() throws SQLException, ParseException {
        Connection connection = new ConnectionFactory().getConnection();

        BoletoDao boletoDao = new BoletoDao(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ModeloParcela modeloParcela = new ModeloParcela();
        LiquidacaoDAO liquidacaoDAO = new LiquidacaoDAO(connection);
        ModeloLiquidacaoBoleto modeloLiquidacaoBoleto = new ModeloLiquidacaoBoleto();
        String liquidacao = "";

        List<ModeloBoleto> modeloBoletos = new ArrayList<ModeloBoleto>();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

       

        modeloBoletos = boletoDao.gerarRemessaAll();
        this.modeloBoletosClass = modeloBoletos;
        String[] colunas = new String[]{"id", "idocorrencia", "idliquidacao", "nossonumero", "idvenda", "dataocorrencia",
            "numerodocumento", "dataquitacaocredito", "datavencimento", "dataemissao",
            "valortitulo", "valorpagotitulo", "emitido", "idpedido"};
        ArrayList dados = new ArrayList();

        Calendar data = Calendar.getInstance();

        for (ModeloBoleto v : modeloBoletos) {
            System.out.println("id: " + v.getId());
            System.out.println("ocorrencia " + v.getCodOcorrencia());
            System.out.println("liquidacao :" + liquidacao);
            System.out.println("nosso Numero: " + v.getNossoNumero());
            System.out.println("idVenda: " + modeloParcela.getIdVenda());
            System.out.println("data ocorrencia: " + v.getDataocorrencia());
            System.out.println("numDocumento" + v.getNumDocumento());
            System.out.println("Data liquidacao Credito: " + v.getDataLiquidacaoCredito());
            System.out.println("vencimento" + v.getVencimento());
            System.out.println("data Emissao: " + v.getDataEmissao());
            System.out.println("valor Titudo: " + v.getValorTitulo());
            System.out.println("valor pago do titulo: " + v.getValorPagodotitulo());
            System.out.println("emitido: " + v.isEmitido());
            System.out.println("idPedido: " + v.getIdPedido());
            System.out.println(" .");

            modeloParcela = parcelaDAO.buscaPorId(v.getIdPedido());
            if (!v.getCodLiquidacao().equals(Long.parseLong("0"))) {
                liquidacao = liquidacaoDAO.buscaPorId(v.getCodLiquidacao()).getNome();

            } else {
                liquidacao = "Não liquidado";
            }

            dados.add(new Object[]{v.getId(), v.getCodOcorrencia(), liquidacao, v.getNossoNumero(), modeloParcela.getIdVenda(), v.getDataocorrencia(),
                v.getNumDocumento(), v.getDataLiquidacaoCredito(), v.getVencimento(), v.getDataEmissao(),
                v.getValorTitulo(), v.getValorPagodotitulo(), v.isEmitido(), v.getIdPedido()});

        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableClientes.setModel(modelo);
        jTableClientes.setRowSorter(new TableRowSorter(modelo));
        jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(0).setResizable(false);
        jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(1).setResizable(false);
        jTableClientes.getColumnModel().getColumn(2).setPreferredWidth(200);
        jTableClientes.getColumnModel().getColumn(2).setResizable(false);
        jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(3).setResizable(false);
        jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(4).setResizable(false);
        jTableClientes.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(5).setResizable(false);
        jTableClientes.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(6).setResizable(false);
        jTableClientes.getColumnModel().getColumn(7).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(7).setResizable(false);
        jTableClientes.getColumnModel().getColumn(8).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(8).setResizable(false);
        jTableClientes.getColumnModel().getColumn(9).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(9).setResizable(false);
        jTableClientes.getColumnModel().getColumn(10).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(10).setResizable(false);
        jTableClientes.getColumnModel().getColumn(11).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(11).setResizable(false);
        jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(100);
        jTableClientes.getColumnModel().getColumn(4).setResizable(false);

        connection.close();
    }

    private void gerarRemessa() throws IOException, SQLException {
        teste teste = new teste();
        teste.contruirRemessaSemEmail(this.modeloBoletosClass);

    }
}
