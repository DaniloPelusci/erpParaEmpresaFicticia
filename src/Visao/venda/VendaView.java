/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.venda;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.cliente.ClienteDAO;
import DAO.funcionario.FuncionarioDAO;
import DAO.pedido.PedidoDAO;
import DAO.venda.ParcelaDAO;
import DAO.venda.VendaDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.Funcionario.ModeloFuncionario;
import Modelo.ModeloVendaPedido;
import Modelo.Pedido.ModeloPedido;
import Modelo.Pedido.ViewProdutoPedido;
import Modelo.venda.ModeloParcela;
import Modelo.venda.ModeloVenda;
import Visao.parcelas.Parcela;
import Visao.pedido.Pedido;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.Date;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author SERVIDOR
 */
public class VendaView extends javax.swing.JFrame {

    private int numeroParcelas = 0;

    private List<ViewProdutoPedido> viewProdutoPedidos = new ArrayList<>();

    List<ModeloVendaPedido> pedidosSelecionados = new ArrayList<ModeloVendaPedido>();
    int idCliente1 = 0;
    private Date dataSalvar;

    private Long idPedidoClasse;
    private ModeloPedido modeloPedido1;
    private ModeloVenda modeloVenda1;
    private String idVenda;

    /**
     * Creates new form Venda
     */
    public VendaView(int idVenda) throws SQLException {
        initComponents();

        jTextFieldIDFuncionario.setEnabled(false);
        jTextFieldNomeCliente.setEnabled(false);
        jTextFieldNomeFuncionario.setEnabled(false);
//        jTextFieldNotaFiscal.setEnabled(false);
        jTextFieldNumeroParcelas.setEnabled(false);
        jTextFieldValorVenda.setEnabled(false);
        jFormattedTextDataVenda.setEnabled(false);
        jTextFieldIDCliente.setEnabled(false);
        int idVenda1 = idVenda;
        buscarPressedEnter();

        if (idVenda != -1) {
            textFieldIDVenda.setText("" + idVenda1);

            Connection connection;
            try {
                connection = new ConnectionFactory().getConnection();

                VendaDAO vendaDAO = new VendaDAO(connection);
                modeloVenda1 = vendaDAO.buscaPorId(Long.parseLong(String.valueOf(idVenda1)));

                PedidoDAO pedidoDAO = new PedidoDAO(connection);
                ClienteDAO clienteDAO = new ClienteDAO(connection);
                ParcelaDAO parcelasDao = new ParcelaDAO(connection);
                List<ModeloParcela> modeloParcelas = new ArrayList<ModeloParcela>();

                if (null == pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(modeloVenda1.getId())))) {

                } else {
                    modeloPedido1 = pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(modeloVenda1.getId())));

                    idPedidoClasse = modeloPedido1.getId();
                    viewProdutoPedidos = pedidoDAO.buscarPedidoproduto(Integer.parseInt(String.valueOf(idPedidoClasse)));
                    modeloParcelas = parcelasDao.listaPorIdVenda(modeloVenda1.getId());
                    listarParcelas(modeloParcelas);
                    PreencherTabela();
                    PreencherTabela2();
                }

                FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
                ModeloFuncionario modeloFuncionario = new ModeloFuncionario();

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                ModeloCliente modeloCliente = new ModeloCliente();
                modeloFuncionario = funcionarioDAO.buscaPorId(modeloVenda1.getIdfuncionario());
                modeloCliente = clienteDAO.buscaPorId(modeloVenda1.getIdcliente());
                System.out.println(modeloVenda1.getIdcliente());
                jTextFieldIDFuncionario.setText("" + modeloFuncionario.getId());
                jTextFieldNomeFuncionario.setText(modeloFuncionario.getNome());
                jFormattedTextDataVenda.setText(format.format(modeloVenda1.getDataVenda()));

                jTextFieldIDCliente.setText("" + modeloCliente.getId());
                jTextFieldNomeCliente.setText("" + modeloCliente.getNome());
                jTextFieldNotaFiscal.setText(modeloVenda1.getNotaFiscal());

                jTextFieldValorVenda.setText("" + modeloVenda1.getValorVenda());
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private VendaView() {
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
        jScrollPane3 = new javax.swing.JScrollPane();
        jTablePedido = new javax.swing.JTable();
        jLabelIDCliente = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldIDCliente = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldIDFuncionario = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldNomeFuncionario = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextDataVenda = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldValorVenda = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldNotaFiscal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableItensPedido = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableParcelas = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        textFieldIDVenda = new javax.swing.JTextField();
        jTextFieldNumeroParcelas = new javax.swing.JTextField();
        jButtonRemover = new javax.swing.JButton();
        jButtonBusca = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        textFieldIDVenda2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("E.B.O. Pesquisa Vendas");

        jTablePedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTablePedido.setAutoscrolls(false);
        jTablePedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTablePedidoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTablePedido);

        jLabelIDCliente.setText("Cliente");

        jLabel6.setText("ID Cliente");

        jLabel8.setText("Nome");

        jLabel4.setText("Funcionario");

        jLabel7.setText("ID Funcionario");

        jLabel9.setText("Nome");

        jLabel3.setText("Data da Venda");

        try {
            jFormattedTextDataVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel5.setText("Valor da venda");

        jTextFieldValorVenda.setText("0");
        jTextFieldValorVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorVendaActionPerformed(evt);
            }
        });

        jLabel10.setText("Nota fiscal");

        jTextFieldNotaFiscal.setText("sem nota");
        jTextFieldNotaFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNotaFiscalActionPerformed(evt);
            }
        });

        jLabel11.setText("Pedido");

        jLabel12.setText("Itens do Pedido");

        jTableItensPedido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableItensPedido.setAutoscrolls(false);
        jScrollPane4.setViewportView(jTableItensPedido);

        jLabel13.setText("Numero de parcelas");

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
        jScrollPane5.setViewportView(jTableParcelas);

        jLabel15.setText("Parcelas");

        jButtonRemover.setText("Deletar Venda");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButtonBusca.setText("Buscar");
        jButtonBusca.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonBusca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuscaActionPerformed(evt);
            }
        });

        jButton1.setText("Alterar nota fiscal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        textFieldIDVenda2.setEnabled(false);
        textFieldIDVenda2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textFieldIDVenda2ActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Venda Busca");

        jLabel2.setText("ID Venda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel7))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelIDCliente)
                                .addGap(117, 117, 117)
                                .addComponent(jLabel6)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldIDFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel9)
                                .addGap(19, 19, 19)
                                .addComponent(jTextFieldNomeFuncionario))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldNomeCliente))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldIDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textFieldIDVenda2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(250, 250, 250))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addComponent(jTextFieldValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 431, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldNumeroParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonBusca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemover)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jFormattedTextDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textFieldIDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textFieldIDVenda2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelIDCliente)
                        .addComponent(jLabel8)
                        .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jTextFieldIDFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jTextFieldNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldNumeroParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextFieldNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(84, 84, 84)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRemover)
                    .addComponent(jButtonBusca))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNotaFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNotaFiscalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNotaFiscalActionPerformed

    private void jTextFieldValorVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorVendaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorVendaActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        try {
            if (logica.LogicasPedido.joptionSenha()) {
                try {
                    Connection connection = new ConnectionFactory().getConnection();
                    VendaDAO vendaDAO = new VendaDAO(connection);

                    vendaDAO.removeVenda(modeloVenda1, Integer.parseInt(String.valueOf(modeloPedido1.getId())));
                    limparCampos();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex);
                    Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Senha Invalida");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButtonBuscaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuscaActionPerformed
        limparCampos();
        if (!textFieldIDVenda.getText().isEmpty()) {
            this.idVenda = textFieldIDVenda.getText();
            Connection connection;
            try {
                connection = new ConnectionFactory().getConnection();

                VendaDAO vendaDAO = new VendaDAO(connection);
                if (!(null == vendaDAO.buscaPorId(Long.parseLong(textFieldIDVenda.getText())))) {
                    ModeloVenda modeloVenda12 = new ModeloVenda();
                    modeloVenda12 = vendaDAO.buscaPorId(Long.parseLong(textFieldIDVenda.getText()));
                    this.modeloVenda1 = vendaDAO.buscaPorId(Long.parseLong(textFieldIDVenda.getText()));
                    PedidoDAO pedidoDAO = new PedidoDAO(connection);
                    ClienteDAO clienteDAO = new ClienteDAO(connection);
                    ParcelaDAO parcelasDao = new ParcelaDAO(connection);
                    List<ModeloParcela> modeloParcelas = new ArrayList<ModeloParcela>();

                    if (null == pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(modeloVenda12.getId())))) {

                    } else {
                        modeloPedido1 = pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(modeloVenda12.getId())));

                        idPedidoClasse = modeloPedido1.getId();
                        viewProdutoPedidos = pedidoDAO.buscarPedidoproduto(Integer.parseInt(String.valueOf(idPedidoClasse)));
                        modeloParcelas = parcelasDao.listaPorIdVenda(modeloVenda12.getId());
                        listarParcelas(modeloParcelas);
                        PreencherTabela();
                        PreencherTabela2();
                    }

                    FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
                    ModeloFuncionario modeloFuncionario = new ModeloFuncionario();

                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                    ModeloCliente modeloCliente = new ModeloCliente();
                    modeloFuncionario = funcionarioDAO.buscaPorId(modeloVenda12.getIdfuncionario());
                    modeloCliente = clienteDAO.buscaPorId(modeloVenda12.getIdcliente());

                    jTextFieldIDFuncionario.setText("" + modeloFuncionario.getId());
                    jTextFieldNomeFuncionario.setText(modeloFuncionario.getNome());
                    textFieldIDVenda2.setText(""+modeloVenda12.getId());
                    jFormattedTextDataVenda.setText(format.format(modeloVenda12.getDataVenda()));
                    jTextFieldNotaFiscal.setText(modeloVenda12.getNotaFiscal());
                    jTextFieldValorVenda.setText("" + modeloVenda12.getValorVenda());
                    jTextFieldIDCliente.setText("" + modeloCliente.getId());
                    jTextFieldNomeCliente.setText("" + modeloCliente.getNome());
                    jTextFieldNumeroParcelas.setText("" + this.numeroParcelas);
                    textFieldIDVenda.setText("");
                    
                   
                    connection.close();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButtonBuscaActionPerformed

    private void jTableParcelasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableParcelasMouseClicked

        try {
            new Parcela(Long.parseLong("" + jTableParcelas.getValueAt(jTableParcelas.getSelectedRow(), 0))).setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTableParcelasMouseClicked

    private void jTablePedidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablePedidoMouseClicked
        try {
            int pedido = 0;

            pedido = Integer.parseInt("" + jTablePedido.getValueAt(jTablePedido.getSelectedRow(), 0));
            new Pedido(pedido, 1,0).setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTablePedidoMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Deseja alterar a nota fiscal dessa venda?") == 0) {
            try {
                Connection connection = new ConnectionFactory().getConnection();
                VendaDAO vendaDAO = new VendaDAO(connection);
                
                vendaDAO.alterarNotaFiscal(jTextFieldNotaFiscal.getText(), Long.parseLong(textFieldIDVenda2.getText()));
                connection.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(VendaView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textFieldIDVenda2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textFieldIDVenda2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textFieldIDVenda2ActionPerformed

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
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendaView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonBusca;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JFormattedTextField jFormattedTextDataVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIDCliente;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableItensPedido;
    private javax.swing.JTable jTableParcelas;
    private javax.swing.JTable jTablePedido;
    private javax.swing.JTextField jTextFieldIDCliente;
    private javax.swing.JTextField jTextFieldIDFuncionario;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldNomeFuncionario;
    private javax.swing.JTextField jTextFieldNotaFiscal;
    private javax.swing.JTextField jTextFieldNumeroParcelas;
    private javax.swing.JTextField jTextFieldValorVenda;
    private javax.swing.JTextField textFieldIDVenda;
    private javax.swing.JTextField textFieldIDVenda2;
    // End of variables declaration//GEN-END:variables

    public void setRetornoConsultaFuncionario(String id, String nome) {
        this.jTextFieldNomeFuncionario.setText(String.valueOf(nome));
        this.jTextFieldIDFuncionario.setText(String.valueOf(id));

    }

    public void PreencherTabela() throws SQLException {// preenchimento da tabela  ,  linhas e colunas , LuizDOURADO
        try (Connection connection = ConnectionFactory.getConnection()) {
            String[] colunas = new String[]{"ID", "Nome", "Nomenclatura", "Quantidade"};
            ArrayList dados = new ArrayList();
            for (ViewProdutoPedido v : viewProdutoPedidos) {
                dados.add(new Object[]{v.getId(), v.getNome(), v.getNomenclatura(), v.getQuantidade()});
            }
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            jTableItensPedido.setModel(modelo);
            jTableItensPedido.setRowSorter(new TableRowSorter(modelo));
            jTableItensPedido.getColumnModel().getColumn(0).setPreferredWidth(2);
            jTableItensPedido.getColumnModel().getColumn(0).setResizable(false);
            jTableItensPedido.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTableItensPedido.getColumnModel().getColumn(1).setResizable(false);
            jTableItensPedido.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableItensPedido.getColumnModel().getColumn(2).setResizable(false);
            jTableItensPedido.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableItensPedido.getColumnModel().getColumn(3).setResizable(false);

        }
    }

    public void PreencherTabela2() throws SQLException {// preenchimento da tabela  ,  linhas e colunas , LuizDOURADO

        String[] colunas = new String[]{"ID do Pedido", "Numero Grafica", "Data Emissao","Boleto"};
        ArrayList dados = new ArrayList();
        Connection connection = ConnectionFactory.getConnection();
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        ModeloPedido modeloPedido = new ModeloPedido();
        modeloPedido = pedidoDAO.buscaPorId(idPedidoClasse);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        dados.add(new Object[]{modeloPedido.getId(), modeloPedido.getNumeroGrafica(), format.format(modeloPedido.getDataEmissao()), modeloPedido.isBoleto()});

        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTablePedido.setModel(modelo);
        jTablePedido.setRowSorter(new TableRowSorter(modelo));
        jTablePedido.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTablePedido.getColumnModel().getColumn(0).setResizable(false);
        jTablePedido.getColumnModel().getColumn(1).setPreferredWidth(20);
        jTablePedido.getColumnModel().getColumn(1).setResizable(false);

        jTablePedido.getTableHeader().setReorderingAllowed(false);
        connection.close();
    }

    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

    //Convert Calendar to Date
    private Date calendarToDate(Calendar calendar) {
        return (Date) calendar.getTime();
    }

    private void listarParcelas(List<ModeloParcela> modeloParcelas) {
        this.numeroParcelas = modeloParcelas.size();
        String[] colunas = new String[]{"ID", "Estado", "Data Pagamento", "Data Vencimento", "Valor"};
        ArrayList dados = new ArrayList();
        Date teste;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        for (ModeloParcela v : modeloParcelas) {
            if (v.isEstatosParcela()) {
                dados.add(new Object[]{v.getId(), "Pago", format.format(v.getDataPagamento()), format.format(v.getDataVencimento()), v.getValor()});
            } else if (!v.isEstatosParcela()) {
                dados.add(new Object[]{v.getId(), "Não Pago", "00/00/0000", format.format(v.getDataVencimento().getTime()), v.getValor()});
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
        

    }

    public String data2(Date data) {
        String data1 = String.valueOf(data);
        String data3[] = data1.split(Pattern.quote("-"));

        String ano = data3[0];
        String mes = data3[1];
        String dia = data3[2];
        String dataSalvar = dia + mes + ano;
        return dataSalvar;
    }

    public void buscarPressedEnter() {
        InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "forward");
        this.getRootPane().setInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW, inputMap);
        this.getRootPane().getActionMap().put("forward", new AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(ActionEvent arg0) {

                jButtonBusca.doClick();
            }
        });

    }

    private void limparCampos() {
        jTextFieldIDCliente.setText("");
        jTextFieldIDFuncionario.setText("");
        jTextFieldNomeCliente.setText("");
        jTextFieldNomeFuncionario.setText("");
        jTextFieldNotaFiscal.setText("");
        jTextFieldNumeroParcelas.setText("");
        jTextFieldValorVenda.setText("");
    }

}
