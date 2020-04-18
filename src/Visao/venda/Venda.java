/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.venda;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.banco.BancoDAO;
import DAO.cliente.ClienteDAO;
import DAO.funcionario.FuncionarioDAO;
import DAO.pedido.PedidoDAO;
import DAO.venda.ParcelaDAO;
import DAO.venda.TipoPagamentoDAO;
import DAO.venda.VendaDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.Funcionario.ModeloFuncionario;
import Modelo.ModeloVendaPedido;
import Modelo.Pedido.ModeloPedido;
import Modelo.Pedido.ViewProdutoPedido;
import Modelo.banco.ModeloBanco;
import Modelo.venda.ModeloCheque;
import Modelo.venda.ModeloParcela;
import Modelo.venda.ModeloTipoPagamento;
import Modelo.venda.ModeloVenda;
import Visao.pedido.Pedido;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author SERVIDOR
 */
public class Venda extends javax.swing.JFrame {

    private List<ViewProdutoPedido> viewProdutoPedidos = new ArrayList<>();

    ModeloVendaPedido pedidosSelecionados = new ModeloVendaPedido();
    int idCliente1 = 0;
    private Date dataSalvar;
    private List<ModeloParcela> modeloParcelas = new ArrayList<ModeloParcela>();
    private Long idPedidoClasse;
    private ModeloPedido modeloPedido1;
    private int auto;

    /**
     * Creates new form Venda
     */
    public Venda(int idCLiente, Long idPedido, int autof) throws SQLException {
        initComponents();
        this.auto = autof;
        idCliente1 = idCLiente;
        idPedidoClasse = idPedido;
         jTextFieldClienteNaoID.setVisible(false);
         jLabel1.setVisible(false);
         jTextField1.setText("0");
        if (idCliente1 == 675) {
            jTextFieldClienteNaoID.setVisible(true);
            jLabel1.setVisible(true);
        }
        jComboBoxParcelas.setSelectedIndex(1);
        Connection connection = new ConnectionFactory().getConnection();

        BancoDAO bancoDAO = new BancoDAO(connection);
        TipoPagamentoDAO tipoPagamentoDAO = new TipoPagamentoDAO(connection);
        List<ModeloTipoPagamento> tipoPagamentos = tipoPagamentoDAO.lista();

        for (ModeloTipoPagamento mp : tipoPagamentos) {

            jComboBoxTipoPagamento.addItem(mp);
        }
        jComboBoxTipoPagamento.setSelectedIndex(0);

        List<ModeloBanco> bancos = bancoDAO.lista();
        for (ModeloBanco t : bancos) {
            jComboBoxBanco.addItem(t);
        }

        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        viewProdutoPedidos = pedidoDAO.buscarPedidoproduto(Integer.parseInt(String.valueOf(idPedidoClasse)));

        modeloPedido1 = pedidoDAO.buscaPorId(idPedidoClasse);

        Date horaAtual = modeloPedido1.getDataEmissao();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        jFormattedTextFieldPrimeiraParcela.setText(sdf.format(modeloPedido1.getDataEmissao()));

        if (this.auto == 1) {

            GregorianCalendar vencimento = new GregorianCalendar();
            vencimento.setTime(horaAtual);

            vencimento.add(Calendar.MONTH, 1);

            //sdf.format(horaAtual.getTime());
            jFormattedTextFieldPrimeiraParcela.setText(sdf.format(vencimento.getTime()));
        }

        PreencherTabela();
        PreencherTabela2();

        ClienteDAO clienteDAO = new ClienteDAO(connection);
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
        ModeloFuncionario modeloFuncionario = new ModeloFuncionario();
        modeloFuncionario = funcionarioDAO.buscaPorId(modeloPedido1.getIdFuncionario());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        ModeloCliente modeloCliente = new ModeloCliente();
        modeloCliente = clienteDAO.buscaPorId(Long.valueOf(idCliente1));
        jTextFieldIDFuncionario.setText("" + modeloFuncionario.getId());
        jTextFieldNomeFuncionario.setText(modeloFuncionario.getNome());
        jFormattedTextDataVenda.setText(format.format(modeloPedido1.getDataEmissao()));

        jTextFieldIDCliente.setText("" + modeloCliente.getId());
        jTextFieldNomeCliente.setText(modeloCliente.getNome());
        PreencherTabela();
        connection.close();

    }

    private Venda() {
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
        jTableClientes = new javax.swing.JTable();
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
        jTableClientes2 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxParcelas = new javax.swing.JComboBox<>();
        label1 = new java.awt.Label();
        jLabel14 = new javax.swing.JLabel();
        jFormattedTextFieldPrimeiraParcela = new javax.swing.JFormattedTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableParcelas = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        textFieldIDVenda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jCheckBoxAVista = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextFieldNumeroCheque = new javax.swing.JTextField();
        jTextFieldValorCheque = new javax.swing.JTextField();
        jComboBoxBanco = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jComboBoxTipoPagamento = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextFieldClienteNaoID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(" E.B.O. Cadastro de Venda");

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
        jTableClientes.setAutoscrolls(false);
        jTableClientes.setFocusable(false);
        jTableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClientesMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableClientes);

        jLabelIDCliente.setText("Cliente");

        jLabel6.setText("ID Cliente");

        jTextFieldIDCliente.setEnabled(false);

        jLabel8.setText("Nome");

        jTextFieldNomeCliente.setEnabled(false);

        jLabel4.setText("Funcionario");

        jLabel7.setText("ID Funcionario");

        jTextFieldIDFuncionario.setEnabled(false);

        jLabel9.setText("Nome");

        jTextFieldNomeFuncionario.setEnabled(false);

        jLabel3.setText("Data da Venda");

        try {
            jFormattedTextDataVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextDataVenda.setText("12/12/2000");

        jLabel5.setText("Valor da venda");

        jTextFieldValorVenda.setText("0");
        jTextFieldValorVenda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldValorVendaKeyReleased(evt);
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

        jTableClientes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTableClientes2.setAutoscrolls(false);
        jTableClientes2.setFocusable(false);
        jScrollPane4.setViewportView(jTableClientes2);

        jLabel13.setText("Numero de parcelas");

        jComboBoxParcelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        label1.setText("ID Venda");

        jLabel14.setText("Data da primeira parcela");

        try {
            jFormattedTextFieldPrimeiraParcela.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldPrimeiraParcela.setText("12/12/2000");

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
        jTableParcelas.setFocusable(false);
        jScrollPane5.setViewportView(jTableParcelas);

        jLabel15.setText("Parcelas");

        jButton3.setText("Ver Pedido");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButtonSalvar.setText("Salvar Venda");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        textFieldIDVenda.setEditable(false);
        textFieldIDVenda.setEnabled(false);
        textFieldIDVenda.setFocusable(false);

        jButton1.setText("Gerar Parcelas");
        jButton1.setFocusable(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jCheckBoxAVista.setText("Primeira parcela á Vista");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Primeira parcela"));

        jLabel2.setText("Numedo do cheque");

        jLabel16.setText("Valor pago");

        jTextFieldValorCheque.setText("0");

        jLabel17.setText("Banco");

        jComboBoxTipoPagamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTipoPagamentoItemStateChanged(evt);
            }
        });

        jLabel18.setText("Tipo de pagamento");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addGap(95, 95, 95))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jComboBoxBanco, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jComboBoxTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Cliente não identificado.");

        jLabel19.setText("LOTES:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(131, 131, 131))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonSalvar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldClienteNaoID, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 472, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(jCheckBoxAVista))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextFieldNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                                    .addComponent(jScrollPane4))
                                .addGap(9, 9, 9))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabelIDCliente)
                                        .addGap(117, 117, 117)
                                        .addComponent(jLabel6))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(86, 86, 86)
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(1, 1, 1)
                                                .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jTextFieldIDFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldNomeFuncionario)
                                    .addComponent(jTextFieldNomeCliente)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textFieldIDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextDataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldPrimeiraParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)))
                .addGap(28, 28, 28))
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
                        .addComponent(jLabel14)
                        .addComponent(jFormattedTextFieldPrimeiraParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textFieldIDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelIDCliente)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIDFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jTextFieldNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jComboBoxParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxAVista)
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextFieldNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSalvar)
                            .addComponent(jButton3)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldClienteNaoID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))))
                .addGap(95, 95, 95))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        if (validacao().isEmpty()) {
            BigDecimal b = new BigDecimal(jTextFieldValorVenda.getText());
            BigDecimal valorVendab = new BigDecimal("4000");
            if (b.doubleValue() > valorVendab.doubleValue()) {
                if (JOptionPane.showConfirmDialog(rootPane, "Você tem certeza que esse é o valor da venda?") == 0) {
                    try {
                        if (totalapagarVencidas() && this.auto == 1) {
                            String message = "ESTE CLIENTE DEVE!!!!!!!!!!!!!!!!!";
                            JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    salvar();

                }
            } else {
                try {
                    if (totalapagarVencidas() && this.auto == 1) {
                        String message = "ESTE CLIENTE DEVE!!!!!!!!!!!!!!!!!";
                        JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                }
                salvar();
            }
        } else {
            String message = validacao();
            JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.INFORMATION_MESSAGE);

        }


    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextFieldNotaFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNotaFiscalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNotaFiscalActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (jTableClientes.getSelectedRow() != -1) {

            int idPedido = Integer.parseInt("" + jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0));
            try {
                new Pedido(idPedido, 1, 0).setVisible(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(rootPane, ex);
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "item não selecionado");

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (!jComboBoxParcelas.getSelectedItem().toString().equals("0")) {
            if (!jTextFieldValorVenda.getText().isEmpty()) {
                modeloParcelas.removeAll(modeloParcelas);
                BigDecimal b = new BigDecimal(jTextFieldValorVenda.getText());

                BigDecimal valorParcelas = b;

                BigDecimal b2 = new BigDecimal(jComboBoxParcelas.getSelectedItem().toString());
                BigDecimal qtdParcelaTemp = b2;
                int quantidade = Integer.parseInt(jComboBoxParcelas.getSelectedItem().toString());
                BigDecimal[] result = valorParcelas.divideAndRemainder(qtdParcelaTemp);

                ModeloParcela modeloParcela = new ModeloParcela();
                String stringDataSalvar = jFormattedTextFieldPrimeiraParcela.getText();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    dataSalvar = new Date(format.parse(stringDataSalvar).getTime());

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex);
                    Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                }
                modeloParcela.setDataVencimento(dataSalvar);

                modeloParcela.setValor(result[0].add(result[1]));

                modeloParcela.setId(Long.parseLong("0"));

                modeloParcelas.add(modeloParcela);
                Calendar calendarTemp = dateToCalendar(dataSalvar);

                if (quantidade != 1) {

                    for (int i = 1; i < quantidade; i++) {

                        ModeloParcela modeloParcela2 = new ModeloParcela();

                        calendarTemp.add(calendarTemp.MONTH, 1);

                        String oi = format.format(calendarTemp.getTime());

                        try {
                            modeloParcela2.setDataVencimento(format.parse(oi));
                        } catch (ParseException ex) {
                            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        modeloParcela2.setValor(result[0]);
                        modeloParcela2.setId(Long.parseLong(String.valueOf(i)));
                        modeloParcelas.add(modeloParcela2);

                    }
                }

                listarParcelas(modeloParcelas);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Campo Valor esta vasio");

            }
        } else {
            modeloParcelas.removeAll(modeloParcelas);
            listarParcelas(modeloParcelas);

        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxTipoPagamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTipoPagamentoItemStateChanged
        ModeloTipoPagamento modeloTipoPagamento = (ModeloTipoPagamento) jComboBoxTipoPagamento.getSelectedItem();

        if (modeloTipoPagamento.getId() == Long.parseLong("3")) {
            jTextFieldNumeroCheque.setEnabled(true);
        } else {
            jTextFieldNumeroCheque.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBoxTipoPagamentoItemStateChanged

    private void jTableClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClientesMouseClicked
        try {
            int pedido = 0;

            pedido = Integer.parseInt("" + jTableClientes.getValueAt(jTableClientes.getSelectedRow(), 0));
            new Pedido(pedido, 1, 0).setVisible(true);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTableClientesMouseClicked

    private void jTextFieldValorVendaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorVendaKeyReleased
        if (this.auto != 1) {
            jCheckBoxAVista.setSelected(true);
            jCheckBoxAVista.setEnabled(false);
            //jComboBoxParcelas.setEnabled(false);
            jButton1.setEnabled(false);
        }
        if (this.auto == 1) {

            jCheckBoxAVista.setEnabled(false);
            //jComboBoxParcelas.setEnabled(false);
            jButton1.setEnabled(false);
            jComboBoxBanco.setEnabled(false);
            jComboBoxTipoPagamento.setEnabled(false);
            jTextFieldValorCheque.setEnabled(false);

        }

        if (!jComboBoxParcelas.getSelectedItem().toString().equals("0")) {
            if (!jTextFieldValorVenda.getText().isEmpty()) {
                modeloParcelas.removeAll(modeloParcelas);
                BigDecimal b = new BigDecimal(jTextFieldValorVenda.getText());

                BigDecimal valorParcelas = b;

                BigDecimal b2 = new BigDecimal(jComboBoxParcelas.getSelectedItem().toString());
                BigDecimal qtdParcelaTemp = b2;
                int quantidade = Integer.parseInt(jComboBoxParcelas.getSelectedItem().toString());
                System.out.println(quantidade);
                BigDecimal[] result = valorParcelas.divideAndRemainder(qtdParcelaTemp);

                ModeloParcela modeloParcela = new ModeloParcela();
                String stringDataSalvar = jFormattedTextFieldPrimeiraParcela.getText();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    dataSalvar = new Date(format.parse(stringDataSalvar).getTime());

                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex);
                    Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                }
                modeloParcela.setDataVencimento(dataSalvar);

                modeloParcela.setValor(result[0].add(result[1]));

                modeloParcela.setId(Long.parseLong("0"));

                modeloParcelas.add(modeloParcela);
                Calendar calendarTemp = dateToCalendar(dataSalvar);

                if (quantidade != 1) {

                    for (int i = 1; i < quantidade; i++) {

                        ModeloParcela modeloParcela2 = new ModeloParcela();

                        calendarTemp.add(calendarTemp.MONTH, 1);

                        String oi = format.format(calendarTemp.getTime());

                        try {
                            modeloParcela2.setDataVencimento(format.parse(oi));
                        } catch (ParseException ex) {
                            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        modeloParcela2.setValor(result[0]);
                        modeloParcela2.setId(Long.parseLong(String.valueOf(i)));
                        modeloParcelas.add(modeloParcela2);

                    }
                }

                listarParcelas(modeloParcelas);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Campo Valor esta vasio");

            }
        } else {
            modeloParcelas.removeAll(modeloParcelas);
            listarParcelas(modeloParcelas);

        }
        if (this.auto != 1) {
            jTextFieldValorCheque.setText(jTextFieldValorVenda.getText());
        }


    }//GEN-LAST:event_jTextFieldValorVendaKeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed
    private boolean totalapagarVencidas() throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        ParcelaDAO parcelasDAO = new ParcelaDAO(connection);
        float valorAPagarVendaVencidas = parcelasDAO.SomaTotalValorPagarVendaVencidas(Long.parseLong(jTextFieldIDCliente.getText()));

        float valorPagoVendaVencidas = parcelasDAO.SomaTotalValorPagoVendaVencidos(Long.parseLong(jTextFieldIDCliente.getText()));

        float valorTotalDevidoVendaVencidas = valorAPagarVendaVencidas - valorPagoVendaVencidas;

        float valotTotalDevidoLocacaoVencidas = parcelasDAO.SomaTotalValorASerpagoLocaçãoVencidas(Long.parseLong(jTextFieldIDCliente.getText()));
        float valorPagoLocacaoVencidas = parcelasDAO.SomaTotalValorPagoLocaçãoVencidas(Long.parseLong(jTextFieldIDCliente.getText()));

        float valorTotalVencidas = valorTotalDevidoVendaVencidas + valotTotalDevidoLocacaoVencidas;

        boolean result = false;
        if (valorTotalVencidas > 0) {
            result = true;
        }
        connection.close();
        return result;

    }

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
            java.util.logging.Logger.getLogger(Venda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Venda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Venda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Venda.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Venda().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JCheckBox jCheckBoxAVista;
    private javax.swing.JComboBox<Object> jComboBoxBanco;
    private javax.swing.JComboBox<String> jComboBoxParcelas;
    private javax.swing.JComboBox<Object> jComboBoxTipoPagamento;
    private javax.swing.JFormattedTextField jFormattedTextDataVenda;
    private javax.swing.JFormattedTextField jFormattedTextFieldPrimeiraParcela;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTableClientes;
    private javax.swing.JTable jTableClientes2;
    private javax.swing.JTable jTableParcelas;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldClienteNaoID;
    private javax.swing.JTextField jTextFieldIDCliente;
    private javax.swing.JTextField jTextFieldIDFuncionario;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldNomeFuncionario;
    private javax.swing.JTextField jTextFieldNotaFiscal;
    private javax.swing.JTextField jTextFieldNumeroCheque;
    private javax.swing.JTextField jTextFieldValorCheque;
    private javax.swing.JTextField jTextFieldValorVenda;
    private java.awt.Label label1;
    private javax.swing.JTextField textFieldIDVenda;
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
            jTableClientes2.setModel(modelo);
            jTableClientes2.setRowSorter(new TableRowSorter(modelo));
            jTableClientes2.getColumnModel().getColumn(0).setPreferredWidth(2);
            jTableClientes2.getColumnModel().getColumn(0).setResizable(false);
            jTableClientes2.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTableClientes2.getColumnModel().getColumn(1).setResizable(false);
            jTableClientes2.getColumnModel().getColumn(2).setPreferredWidth(20);
            jTableClientes2.getColumnModel().getColumn(2).setResizable(false);
            jTableClientes2.getColumnModel().getColumn(3).setPreferredWidth(20);
            jTableClientes2.getColumnModel().getColumn(3).setResizable(false);

        }
    }

    public void salvar() {
        if (!(this.modeloParcelas.size() == 0)) {
            if (!(this.modeloParcelas.size() == 0)) {
                try {
                    Connection connection = ConnectionFactory.getConnection();
                    ModeloCheque modeloCheque = new ModeloCheque();
                    ModeloBanco modeloBanco = (ModeloBanco) jComboBoxBanco.getSelectedItem();
                    ModeloTipoPagamento modeloTipoPagamento = (ModeloTipoPagamento) jComboBoxTipoPagamento.getSelectedItem();
                    modeloCheque.setNumeroCheque(jTextFieldNumeroCheque.getText());
                    if (logica.LogicasPedido.isfloat(jTextFieldValorCheque.getText())) {
                        modeloCheque.setValor(Float.parseFloat(jTextFieldValorCheque.getText()));
                    }

                    modeloCheque.setIdBanco(modeloBanco.getId());
                    modeloCheque.setIdTipoPagamento(modeloTipoPagamento.getId());

                    VendaDAO vendaDAO = new VendaDAO(connection);
                    ModeloVenda modeloVenda = new ModeloVenda();

                    modeloVenda.setIdcliente(Long.parseLong(jTextFieldIDCliente.getText()));

                    modeloVenda.setIdfuncionario(Long.parseLong(jTextFieldIDFuncionario.getText()));
                    modeloVenda.setNotaFiscal(jTextFieldNotaFiscal.getText());
                    BigDecimal b = new BigDecimal(jTextFieldValorVenda.getText());
                    modeloVenda.setValorVenda(b);
                    String stringDataVendaSalvar = jFormattedTextDataVenda.getText();
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    
                    modeloVenda.setLote(jTextField1.getText());
                    
                    String stringDataPrimeiraParcela = jFormattedTextFieldPrimeiraParcela.getText();
                    

                    Date dataPrimeiraParcela;

                    try {
                        dataPrimeiraParcela = new Date(format.parse(stringDataPrimeiraParcela).getTime());
                        dataSalvar = new Date(format.parse(stringDataVendaSalvar).getTime());
                        modeloVenda.setDataVenda(dataSalvar);
                        modeloCheque.setDatapaga(dataSalvar);
                        modeloVenda.setStatus(false);
                        float valorPrimeiraParcela = 0;
                        modeloVenda.setObservacao(jTextFieldClienteNaoID.getText().toUpperCase());
                        
                        for (ModeloParcela mp : modeloParcelas) {
                            valorPrimeiraParcela = +mp.getValor().floatValue();

                            break;
                        }

                        if (valorPrimeiraParcela < (modeloCheque.getValor())) {
                           
                            JOptionPane.showMessageDialog(rootPane, "Valor invalido");

                        } else {

                            boolean Aavista = jCheckBoxAVista.isSelected();
                            JOptionPane.showMessageDialog(null, "O numero da Venda é: \n" + vendaDAO.adicionaVendaPedidos(dataPrimeiraParcela, Aavista, modeloVenda, modeloPedido1, modeloParcelas, modeloCheque));
                            if (JOptionPane.showConfirmDialog(rootPane, "Deseja cadastrar outra venda?") == 0) {
                                System.err.println(this.auto);
                                new Pedido(-1, 1, jFormattedTextDataVenda.getText(), this.auto).setVisible(true);
                            }
                            dispose();
                        }

                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(rootPane, ex);
                        Logger
                                .getLogger(Venda.class
                                        .getName()).log(Level.SEVERE, null, ex);
                    }

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, ex);
                    Logger
                            .getLogger(Venda.class
                                    .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Gere as parcelas");

            }
        }
    }

    public void PreencherTabela2() throws SQLException {// preenchimento da tabela  ,  linhas e colunas , LuizDOURADO

        String[] colunas = new String[]{"ID do Pedido", "Numero Grafica", "Data Emissao"};
        ArrayList dados = new ArrayList();
        Connection connection = ConnectionFactory.getConnection();
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        ModeloPedido modeloPedido = new ModeloPedido();
        modeloPedido = pedidoDAO.buscaPorId(idPedidoClasse);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        dados.add(new Object[]{modeloPedido.getId(), modeloPedido.getNumeroGrafica(), format.format(modeloPedido.getDataEmissao())});

        ModeloTabela modelo = new ModeloTabela(dados, colunas);

        jTableClientes.setModel(modelo);
        jTableClientes.setRowSorter(new TableRowSorter(modelo));
        jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTableClientes.getColumnModel().getColumn(0).setResizable(false);
        jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(20);
        jTableClientes.getColumnModel().getColumn(1).setResizable(false);

        jTableClientes.getTableHeader().setReorderingAllowed(false);
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
        String[] colunas = new String[]{"ID", "Data Vencimento", "Valor"};
        ArrayList dados = new ArrayList();
        Date teste;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        for (ModeloParcela v : modeloParcelas) {
            dados.add(new Object[]{v.getId(), format.format(v.getDataVencimento()), v.getValor()});
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

    private void ativaDesativaCamposAvista() {
        jTextFieldNumeroCheque.setEnabled(false);
        jTextFieldValorCheque.setEnabled(false);
        jComboBoxBanco.setEnabled(false);
        jComboBoxTipoPagamento.setEnabled(false);
    }

    private String validacao() {
        String validar = "";

        int cont = 0;

        if (jCheckBoxAVista.isSelected()) {
            if (!logica.LogicasPedido.isDouble(jTextFieldValorCheque.getText())) {
                validar += "\n" + "Valor do cheque invalido ";

                cont++;
            }
        }

        if (cont != 0) {
            validar = "\n" + "Campos obrigatorios: \n" + validar;
        }

        return validar;
    }
}
