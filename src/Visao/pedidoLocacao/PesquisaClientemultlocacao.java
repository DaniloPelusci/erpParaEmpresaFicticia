/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.pedidoLocacao;

import Controle.ModeloTabela;
import DAO.cliente.ClienteDAO;
import DAO.ConnectionFactory;
import DAO.TipoAcaoDAO;
import Modelo.Cliente.ModeloCliente;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author SERVIDOR
 */
public class PesquisaClientemultlocacao extends javax.swing.JDialog {

    Multlocacao telaPai;

    /**
     * Creates new form PesquisaCliente2
     */
    public PesquisaClientemultlocacao(java.awt.Frame parent, boolean modal, Multlocacao telaPai) {
        super(parent, modal);
        initComponents();

        this.telaPai = telaPai;
        confirmarPressedEnter();
    }

    private PesquisaClientemultlocacao(JFrame jFrame, boolean b) {
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
        jTextFieldNome = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jButtonConfirmar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("E.B.O. Pesquisa Cliente");

        jLabel1.setText("Nome");

        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyReleased(evt);
            }
        });

        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.setFocusable(false);
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

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
        jScrollPane3.setViewportView(jTableClientes);

        jButtonConfirmar.setText("Confirmar");
        jButtonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonPesquisar))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 726, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonConfirmar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jLabel1))
                .addGap(82, 82, 82)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonConfirmar)
                .addContainerGap())
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed
        try {

            ModeloTabela modelo = listar();

            jTableClientes.setModel(modelo);
            jTableClientes.setRowSorter(new TableRowSorter(modelo));
            jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTableClientes.getColumnModel().getColumn(0).setResizable(false);
            jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(5);
            jTableClientes.getColumnModel().getColumn(1).setResizable(false);
            jTableClientes.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableClientes.getColumnModel().getColumn(2).setResizable(false);
            jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableClientes.getColumnModel().getColumn(3).setResizable(false);
            jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTableClientes.getColumnModel().getColumn(4).setResizable(false);

            jTableClientes.getTableHeader().setReorderingAllowed(false);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(PesquisaClientemultlocacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jButtonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmarActionPerformed
        if (jTableClientes.getSelectedRow() != -1) {
            String id = "" + jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0);        // TODO add your handling code here:
            String nome = "" + jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 1);
            try {
                this.telaPai.setRetornoConsultaCliente(id, nome);
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(PesquisaClientemultlocacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "item não selecionado");

        }

        this.dispose();
    }//GEN-LAST:event_jButtonConfirmarActionPerformed

    private void jTextFieldNomeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyReleased
        ClienteDAO clientedao = null;
        try {
            clientedao = new ClienteDAO(ConnectionFactory.getConnection());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(PesquisaClientemultlocacao.class.getName()).log(Level.SEVERE, null, ex);
        }
        jTableClientes.setModel(clientedao.buscaLike(jTextFieldNome.getText().toUpperCase()));
        jTableClientes.setRowSorter(new TableRowSorter(clientedao.buscaLike(jTextFieldNome.getText().toUpperCase())));
        jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(5);
        jTableClientes.getColumnModel().getColumn(0).setResizable(false);
        jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(5);
        jTableClientes.getColumnModel().getColumn(1).setResizable(false);
        jTableClientes.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(2).setResizable(false);
        jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(3).setResizable(false);
        jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(4).setResizable(false);
        jTableClientes.getColumnModel().getColumn(5).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(5).setResizable(false);
        jTableClientes.getColumnModel().getColumn(6).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(6).setResizable(false);
        jTableClientes.getColumnModel().getColumn(7).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(7).setResizable(false);
        jTableClientes.getColumnModel().getColumn(8).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(8).setResizable(false);
        jTableClientes.getTableHeader().setReorderingAllowed(false);
    }//GEN-LAST:event_jTextFieldNomeKeyReleased

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
            java.util.logging.Logger.getLogger(PesquisaClientemultlocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisaClientemultlocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisaClientemultlocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisaClientemultlocacao.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesquisaClientemultlocacao dialog = new PesquisaClientemultlocacao(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonConfirmar;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTextField jTextFieldNome;
    // End of variables declaration//GEN-END:variables
public void confirmarPressedEnter() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "forward");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        this.getRootPane().getActionMap().put("forward", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent arg0) {

                jButtonConfirmar.doClick();
            }
        });

    }

    private ModeloTabela listar() throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        ClienteDAO clientedao = new ClienteDAO(connection);
        TipoAcaoDAO tipoAcaoDAO = new TipoAcaoDAO(connection);

        List<ModeloCliente> modeloClientes = tipoAcaoDAO.listaParaLocacao();

        String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Endereço", "CNPJ"};
        ArrayList dados = new ArrayList();

        for (ModeloCliente mc : modeloClientes) {
            if (pecas(Integer.parseInt(String.valueOf(mc.getId())), connection)||cilindro(Integer.parseInt(String.valueOf(mc.getId())), connection) || concentrador(Integer.parseInt(String.valueOf(mc.getId())), connection)) {
                dados.add(new Object[]{mc.getId(), mc.getNome(), mc.getNomeFantasia(), mc.getEndereco(), mc.getCnpj()});
            }
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        return modelo;
    }

    private boolean pecas(Integer Idcliente, Connection connection) throws SQLException {
        boolean resultado = true;

        TipoAcaoDAO tipoAcaoDAO = new TipoAcaoDAO(connection);
        int locacaoPecas = tipoAcaoDAO.somaPecasLocacaoCliente(Idcliente);
        int devolucaopecas = tipoAcaoDAO.somaPecasDevolucaoCliente(Idcliente) + tipoAcaoDAO.somaPecasVendaCliente(Idcliente);
        int resultPecas = locacaoPecas - devolucaopecas;
        if (resultPecas == 0) {
            resultado = false;
        }

        return resultado;
    }

    private boolean cilindro(Integer Idcliente, Connection connection) {
        boolean resultado = true;
        TipoAcaoDAO tipoAcaoDAO = new TipoAcaoDAO(connection);
        int locacaoCilindro = tipoAcaoDAO.somaCilindroLocacaoCliente(Idcliente);
        int devolucaoCilindro = tipoAcaoDAO.somaCilindroDevolucaoCliente(Idcliente) + tipoAcaoDAO.somaCilindroVendaCliente(Idcliente);
        int resultCilindros = locacaoCilindro - devolucaoCilindro;
        if (resultCilindros == 0) {
            resultado = false;
        }

        return resultado;

    }

    private boolean concentrador(Integer Idcliente, Connection connection) {
        boolean resultado = true;
        TipoAcaoDAO tipoAcaoDAO = new TipoAcaoDAO(connection);
        int locacaoConcentrador = tipoAcaoDAO.somaConcentradorLocacaoCliente(Idcliente);
        int devolucaoConcentrador = tipoAcaoDAO.somaConcentradorDevolucaoCliente(Idcliente) + tipoAcaoDAO.somaConcentradorVendaClienteCliente(Idcliente);

        int resultConcentrador = locacaoConcentrador - devolucaoConcentrador;
        if (resultConcentrador == 0) {
            resultado = false;
        }

        return resultado;
    }

}
