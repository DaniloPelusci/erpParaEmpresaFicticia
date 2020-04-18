/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao.pedido;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.cliente.ClienteDAO;
import DAO.funcionario.FuncionarioDAO;
import DAO.pedido.PedidoDAO;
import DAO.pedido.TipoPedidoDAO;
import DAO.produto.NomenclaturaDAO;
import DAO.produto.ProdutoDAO;
import DAO.venda.VendaDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.Funcionario.ModeloFuncionario;
import Modelo.Pedido.ModeloPedido;
import Modelo.Pedido.ModeloTipoPedido;
import Modelo.Pedido.ViewProdutoPedido;
import Modelo.Produto.ModeloProduto;
import Visao.venda.Venda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author SERVIDOR
 */
public class Pedido extends javax.swing.JFrame {

    private List<ViewProdutoPedido> viewProdutoPedidos = new ArrayList<>();
    private ArrayList<Integer> itemDeletado = new ArrayList<Integer>();
    private int idpedido = 0;
    private Long idPedido2;
    private int auto;

    /**
     * Creates new form Pedido
     */
    public Pedido(int id, int tipoP , int autof ) throws SQLException {
        //tipoP 0 aluguel e 1 é venda.
        initComponents();
        desativaLableValidation();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar horaAtual = Calendar.getInstance();
        
        this.idpedido = id;
        this.auto = autof;
        if(this.auto != 1){
            jTextFieldNumeroGrafica.setText("0");
            jTextFieldNumeroGrafica.setEnabled(false);
        }

        

        // Ou qualquer outra forma que tem
        String dataFormatada = sdf.format(horaAtual.getTime());
        if(id == -1){
        
        jFormattedTextFieldDATA.setText(dataFormatada);
        }
        
        jComboBoxTipoPedido.setEnabled(true);

        jTextFieldIDVenda.setEnabled(false);

        jTextFieldNomeCliente.setEditable(false);

        jTextFieldNomeFuncionario.setEditable(false);

        Connection connection = new ConnectionFactory().getConnection();
        TipoPedidoDAO tipoPedidoDAO = new TipoPedidoDAO(connection);
        List<ModeloTipoPedido> tipoPedidos = tipoPedidoDAO.lista();
        for (ModeloTipoPedido t : tipoPedidos) {
            jComboBoxTipoPedido.addItem(t);
        }

        jComboBoxTipoPedido.setSelectedIndex(tipoP);
        jComboBoxTipoPedido.setEnabled(false);

        if (id != -1) {
            jTextFieldIDPedido.setText("" + id);
            PedidoDAO pedidoDAO = new PedidoDAO(connection);
            viewProdutoPedidos = pedidoDAO.buscarPedidoproduto(id);
            Long idteste = Long.valueOf(id);
            ModeloPedido modeloPedido1 = pedidoDAO.buscaPorId(idteste);
            dataFormatada = sdf.format(modeloPedido1.getDataEmissao().getTime());
            preencherFormulario(pedidoDAO.buscaPorId(idteste));
            jFormattedTextFieldDATA.setText(dataFormatada);
            listarProduto();

        }

        connection.close();

    }

    public Pedido(int id, int tipoP, String data, int autof ) throws SQLException {
        //tipoP 0 aluguel e 1 é venda.
        initComponents();
        desativaLableValidation();
        this.auto = autof;
        if(this.auto != 1){
            jTextFieldNumeroGrafica.setText("0");
            jTextFieldNumeroGrafica.setEnabled(false);
        }

        this.idpedido = id;
       
        // Ou qualquer outra forma que tem

        jFormattedTextFieldDATA.setText(data);
        jComboBoxTipoPedido.setEnabled(true);

        jTextFieldIDVenda.setEnabled(false);

        jTextFieldNomeCliente.setEditable(false);

        jTextFieldNomeFuncionario.setEditable(false);

        Connection connection = new ConnectionFactory().getConnection();
        TipoPedidoDAO tipoPedidoDAO = new TipoPedidoDAO(connection);
        List<ModeloTipoPedido> tipoPedidos = tipoPedidoDAO.lista();
        for (ModeloTipoPedido t : tipoPedidos) {
            jComboBoxTipoPedido.addItem(t);
        }
        jComboBoxTipoPedido.setSelectedIndex(tipoP);
        jComboBoxTipoPedido.setEnabled(false);

        if (id != -1) {
            jTextFieldIDPedido.setText("" + id);
            jCheckBoxboleto.setEnabled(false);
            PedidoDAO pedidoDAO = new PedidoDAO(connection);
            viewProdutoPedidos = pedidoDAO.buscarPedidoproduto(id);
            Long idteste = Long.valueOf(id);
            ModeloPedido modeloPedido1 = pedidoDAO.buscaPorId(idteste);

            preencherFormulario(pedidoDAO.buscaPorId(idteste));
            listarProduto();

        }

        connection.close();

    }

    private Pedido() {
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
        jLabelIDProduto = new javax.swing.JLabel();
        jTextFieldIDPedido = new javax.swing.JTextField();
        jLabelIDVenda = new javax.swing.JLabel();
        jTextFieldIDVenda = new javax.swing.JTextField();
        jLabelIDCliente = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTipoPedido = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNumeroGrafica = new javax.swing.JTextField();
        jFormattedTextFieldDATA = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonProduto = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldIDProduto = new javax.swing.JTextField();
        jTextFieldNomeProduto = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextFieldCapacidade2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jButtonAdicionar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();
        jTextFieldQuantidade = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jTextFieldIDCliente = new javax.swing.JTextField();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jButtonFuncionario = new javax.swing.JButton();
        jTextFieldIDFuncionario = new javax.swing.JTextField();
        jTextFieldNomeFuncionario = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButtonSalvar = new javax.swing.JButton();
        jButtonLimpar = new javax.swing.JButton();
        jButtonRemover = new javax.swing.JButton();
        validacaoData = new javax.swing.JLabel();
        validacaoGrafica = new javax.swing.JLabel();
        validacaoCliente = new javax.swing.JLabel();
        validacaoFuncionario = new javax.swing.JLabel();
        jCheckBoxboleto = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("E.B.O. Cadastro de Pedidos   ");

        jLabelIDProduto.setText("ID");

        jTextFieldIDPedido.setEditable(false);

        jLabelIDVenda.setText("ID Negociação");

        jTextFieldIDVenda.setEditable(false);

        jLabelIDCliente.setText("Cliente");

        jLabel4.setText("Funcionario");

        jLabel1.setText("Tipo Pedido");

        jLabel2.setText("N° Grafica");

        try {
            jFormattedTextFieldDATA.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldDATA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldDATAActionPerformed(evt);
            }
        });

        jLabel3.setText("Data Emissão");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        jButtonProduto.setText("+");
        jButtonProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdutoActionPerformed(evt);
            }
        });

        jLabel5.setText("Produto");

        jTextFieldIDProduto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDProdutoFocusLost(evt);
            }
        });

        jTextFieldNomeProduto.setEnabled(false);
        jTextFieldNomeProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeProdutoActionPerformed(evt);
            }
        });

        jLabel10.setText("ID Produto");

        jLabel11.setText("Nome");

        jLabel12.setText("Capacidade");

        jTextFieldCapacidade2.setEnabled(false);

        jLabel14.setText("Quantidade");

        jButtonAdicionar.setText("Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jButtonProduto)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldIDProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jTextFieldNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldCapacidade2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdicionar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 971, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonProduto)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldIDProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldCapacidade2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jButtonAdicionar)
                    .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
        );

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextFieldIDCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDClienteFocusLost(evt);
            }
        });

        jTextFieldNomeCliente.setEnabled(false);

        jButtonFuncionario.setText("+");
        jButtonFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFuncionarioActionPerformed(evt);
            }
        });

        jTextFieldIDFuncionario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldIDFuncionarioFocusLost(evt);
            }
        });

        jTextFieldNomeFuncionario.setEnabled(false);

        jLabel6.setText("ID Cliente");

        jLabel7.setText("ID Funcionario");

        jLabel8.setText("Nome");

        jLabel9.setText("Nome");

        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonLimpar.setText("Limpar");
        jButtonLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimparActionPerformed(evt);
            }
        });

        jButtonRemover.setText("Remover item");
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        validacaoData.setForeground(new java.awt.Color(255, 0, 0));
        validacaoData.setText("*");

        validacaoGrafica.setForeground(new java.awt.Color(255, 0, 0));
        validacaoGrafica.setText("*");

        validacaoCliente.setForeground(new java.awt.Color(255, 0, 0));
        validacaoCliente.setText("*");

        validacaoFuncionario.setForeground(new java.awt.Color(255, 0, 0));
        validacaoFuncionario.setText("*");

        jCheckBoxboleto.setText("Boleto");
        jCheckBoxboleto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBoxboletoItemStateChanged(evt);
            }
        });
        jCheckBoxboleto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCheckBoxboletoMouseClicked(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        jLabel13.setText("Observação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSalvar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLimpar)
                        .addGap(117, 117, 117)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemover))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(validacaoGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)
                                .addComponent(jTextFieldNumeroGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabelIDProduto)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldIDPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabelIDVenda)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextFieldIDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(validacaoData, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextFieldDATA, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(343, 343, 343)
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBoxTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabelIDCliente))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButtonFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel7))
                                    .addGap(23, 23, 23)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(validacaoFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(validacaoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldIDFuncionario)
                                        .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel9))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                                        .addComponent(jTextFieldNomeFuncionario)))
                                .addComponent(jCheckBoxboleto)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIDProduto)
                    .addComponent(jTextFieldIDPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIDVenda)
                    .addComponent(jTextFieldIDVenda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldDATA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxTipoPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validacaoData))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldNumeroGrafica, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validacaoGrafica))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIDCliente)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonFuncionario)
                                .addComponent(jLabel7))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIDCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(validacaoCliente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldIDFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNomeFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(validacaoFuncionario))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxboleto)
                .addGap(10, 10, 10)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSalvar)
                        .addComponent(jButtonLimpar)
                        .addComponent(jButtonRemover))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new PesquisaCliente(null, true, this).setVisible(true);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextFieldNomeProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeProdutoActionPerformed

    private void jButtonLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimparActionPerformed
        limparTudo();
    }//GEN-LAST:event_jButtonLimparActionPerformed

    private void jButtonFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFuncionarioActionPerformed
        new PesquisaFuncionario(null, true, this).setVisible(true);
    }//GEN-LAST:event_jButtonFuncionarioActionPerformed

    private void jButtonProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdutoActionPerformed
        new PesquisaProduto(null, true, this).setVisible(true);
    }//GEN-LAST:event_jButtonProdutoActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed

        int cont = 0;
        if (!jTextFieldQuantidade.getText().equals("  ") && !jTextFieldIDProduto.getText().isEmpty()) {
            ViewProdutoPedido viewProdutoPedido = new ViewProdutoPedido();
            String id = "" + jTextFieldIDProduto.getText();
            Long idparse = Long.parseLong(id);
            viewProdutoPedido.setId(idparse);
            viewProdutoPedido.setNome("" + jTextFieldNomeProduto.getText());
            viewProdutoPedido.setNomenclatura("" + jTextFieldCapacidade2.getText());

            viewProdutoPedido.setQuantidade("" + jTextFieldQuantidade.getText());

            for (ViewProdutoPedido v : viewProdutoPedidos) {
                if (v.getId() == viewProdutoPedido.getId()) {
                    JOptionPane.showMessageDialog(rootPane, "Já existe esse produto na lista");
                    cont += 1;

                    break;
                }
            }
            if (cont == 0) {
                viewProdutoPedidos.add(viewProdutoPedido);
                jTextFieldQuantidade.setText("");
                jTextFieldNomeProduto.setText("");

                jTextFieldIDProduto.setText("");
                jTextFieldCapacidade2.setText("");
                listarProduto();
            }
        } else {
            JOptionPane.showMessageDialog(null, " Falha no Armazenamento de dados \n ERRO :");

        }


    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        Connection connection;
        try {
            if (validacao().isEmpty()) {

                connection = new ConnectionFactory().getConnection();
                PedidoDAO pedidoDAO = new PedidoDAO(connection);
                ClienteDAO clienteDAO = new ClienteDAO(connection);
                ModeloPedido modeloPedido = new ModeloPedido();

                modeloPedido.setIdCliente(Long.parseLong(jTextFieldIDCliente.getText()));
                modeloPedido.setIdFuncionario(Long.parseLong(jTextFieldIDFuncionario.getText()));
                //modeloPedido
                ModeloTipoPedido modeloTipoPedido = (ModeloTipoPedido) jComboBoxTipoPedido.getSelectedItem();
                modeloPedido.setIdTipoPedido(modeloTipoPedido.getId());
                modeloPedido.setNumeroGrafica(Integer.parseInt(jTextFieldNumeroGrafica.getText()));
                String stringDataSalvar = jFormattedTextFieldDATA.getText();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                Date dataSalvar;
                try {
                    
                    dataSalvar = new Date(format.parse(stringDataSalvar).getTime());
                    modeloPedido.setDataEmissao(dataSalvar);
                } catch (ParseException ex) {
                    Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
                }

                String idSalvar = jTextFieldIDPedido.getText();

                if (idSalvar.equals("")) {
                    if (!(viewProdutoPedidos.isEmpty())) {
                        modeloPedido.setSituacao(false);
                        modeloPedido.setProcessoQuitacao(false);
                        modeloPedido.setBoleto(true);
                        
                        if(modeloPedido.getNumeroGrafica()!= 0){
                            modeloPedido.setBoleto(false);
                            
                        }
                        System.out.println(modeloPedido.isBoleto());
                        idPedido2 = pedidoDAO.adicionaPedidoProdutoVenda(modeloPedido, viewProdutoPedidos);

                        limparTudo();
                        new Venda(Integer.parseInt(String.valueOf(modeloPedido.getIdCliente())), idPedido2,this.auto).setVisible(true);
                        connection.close();
                        dispose();
                        

                    } else {
                        JOptionPane.showMessageDialog(null, "Adicione o produto");
                    }
                } else if (!(viewProdutoPedidos.isEmpty())) {
                    if (logica.LogicasPedido.joptionSenha()) {

                        modeloPedido.setId(Long.parseLong(jTextFieldIDPedido.getText()));

                        
                        pedidoDAO.alteraPedidoProduto(itemDeletado, modeloPedido, viewProdutoPedidos);
                        connection.close();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Senha Invalida");

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Adicione o produto");
                }
            } else {
                String message = validacao();
                JOptionPane.showMessageDialog(null, message, "Aviso", JOptionPane.INFORMATION_MESSAGE);

            }
             
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }

       
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        int idtabela = Integer.parseInt("" + jTableProduto.getValueAt(jTableProduto.getSelectedRow(), 0));

        if (jTableProduto.getSelectedRow() != -1) {
            if (idpedido != -1) {
                int cont = 0;

                for (Integer it : itemDeletado) {
                    if (it.intValue() == idtabela) {

                        cont += 1;
                        break;
                    }
                }
                if (cont == 0) {
                    itemDeletado.add(idtabela);
                }
                for (int i = 0; i < viewProdutoPedidos.size(); i++) {
                    if (idtabela == viewProdutoPedidos.get(i).getId()) {

                        viewProdutoPedidos.remove(i);

                    }
                }

            } else {
                for (int i = 0; i < viewProdutoPedidos.size(); i++) {
                    if (idtabela == viewProdutoPedidos.get(i).getId()) {

                        viewProdutoPedidos.remove(i);

                    }
                }

            }
        }
        listarProduto();
    }//GEN-LAST:event_jButtonRemoverActionPerformed

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
            } catch (SQLException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTextFieldIDClienteFocusLost

    private void jTextFieldIDFuncionarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDFuncionarioFocusLost
        if (jTextFieldIDFuncionario.getText().isEmpty() || !logica.LogicasPedido.isInt(jTextFieldIDFuncionario.getText())) {
        } else {
            try {

                Connection connection = ConnectionFactory.getConnection();
                FuncionarioDAO funcionarioDAO = new FuncionarioDAO(connection);
                if (funcionarioDAO.verificacao(Long.parseLong(jTextFieldIDFuncionario.getText()))) {

                    ModeloFuncionario modeloFuncionario = new ModeloFuncionario();
                    modeloFuncionario = funcionarioDAO.buscaPorId(Long.parseLong(jTextFieldIDFuncionario.getText()));
                    jTextFieldNomeFuncionario.setText(modeloFuncionario.getNome());
                } else {
                    JOptionPane.showMessageDialog(null, "ID do Funcionario invalido");

                }
            } catch (SQLException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTextFieldIDFuncionarioFocusLost

    private void jTextFieldIDProdutoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldIDProdutoFocusLost
        if (jTextFieldIDProduto.getText().isEmpty() || !logica.LogicasPedido.isInt(jTextFieldIDProduto.getText())) {

        } else {
            try {

                Connection connection = ConnectionFactory.getConnection();
                ProdutoDAO produtoDAO = new ProdutoDAO(connection);
                NomenclaturaDAO nomenclaturaDAO = new NomenclaturaDAO(connection);
                if (produtoDAO.verificacao(Long.parseLong(jTextFieldIDProduto.getText()))) {

                    ModeloProduto modeloProduto = new ModeloProduto();
                    modeloProduto = produtoDAO.buscaPorId(Long.parseLong(jTextFieldIDProduto.getText()));

                    jTextFieldCapacidade2.setText(nomenclaturaDAO.buscaPorId(modeloProduto.getNomenclatura()).getNome());
                    jTextFieldNomeProduto.setText(modeloProduto.getNome());
                    connection.close();
                } else {
                    JOptionPane.showMessageDialog(null, "ID do cliente invalido");
                    connection.close();

                }
            } catch (SQLException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTextFieldIDProdutoFocusLost

    private void jFormattedTextFieldDATAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldDATAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldDATAActionPerformed

    private void jCheckBoxboletoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCheckBoxboletoMouseClicked
        Connection connection;
        try {
            connection = new ConnectionFactory().getConnection();
       
                PedidoDAO pedidoDAO = new PedidoDAO(connection);
                
                pedidoDAO.alterarBoleto(jCheckBoxboleto.isSelected(), Long.parseLong(jTextFieldIDPedido.getText()));
                connection.close();
                 } catch (SQLException ex) {
            Logger.getLogger(Pedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jCheckBoxboletoMouseClicked

    private void jCheckBoxboletoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBoxboletoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxboletoItemStateChanged

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
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pedido().setVisible(true);
            }
        });
    }

    public void setRetornoConsultaCliente(String iDCliente, String nomeCliente) throws SQLException {
        this.jTextFieldIDCliente.setText(String.valueOf(iDCliente));
        this.jTextFieldNomeCliente.setText(String.valueOf(nomeCliente));

    }

    public void setRetornoConsultaFuncionario(String id, String nome) {
        this.jTextFieldNomeFuncionario.setText(String.valueOf(nome));
        this.jTextFieldIDFuncionario.setText(String.valueOf(id));

    }

    public void setRetornoConsultaProduto(String id, String nome, String capacidade) {
        this.jTextFieldIDProduto.setText(String.valueOf(id));
        this.jTextFieldNomeProduto.setText(String.valueOf(nome));
        this.jTextFieldCapacidade2.setText(String.valueOf(capacidade));
    }

    public void listarProduto() {
        String[] colunas = new String[]{"ID", "Nome", "Nomenclatura", "Quantidade"};
        ArrayList dados = new ArrayList();

        for (ViewProdutoPedido v : viewProdutoPedidos) {
            dados.add(new Object[]{v.getId(), v.getNome(), v.getNomenclatura(), v.getQuantidade()});
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        jTableProduto.setModel(modelo);
        jTableProduto.getColumnModel().getColumn(0).setPreferredWidth(2);
        jTableProduto.getColumnModel().getColumn(0).setResizable(false);
        jTableProduto.getColumnModel().getColumn(1).setPreferredWidth(20);
        jTableProduto.getColumnModel().getColumn(1).setResizable(false);
        jTableProduto.getColumnModel().getColumn(2).setPreferredWidth(20);
        jTableProduto.getColumnModel().getColumn(2).setResizable(false);
        jTableProduto.getColumnModel().getColumn(3).setPreferredWidth(20);
        jTableProduto.getColumnModel().getColumn(3).setResizable(false);

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonFuncionario;
    private javax.swing.JButton jButtonLimpar;
    private javax.swing.JButton jButtonProduto;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JCheckBox jCheckBoxboleto;
    private javax.swing.JComboBox<Object> jComboBoxTipoPedido;
    private javax.swing.JFormattedTextField jFormattedTextFieldDATA;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelIDCliente;
    private javax.swing.JLabel jLabelIDProduto;
    private javax.swing.JLabel jLabelIDVenda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldCapacidade2;
    private javax.swing.JTextField jTextFieldIDCliente;
    private javax.swing.JTextField jTextFieldIDFuncionario;
    private javax.swing.JTextField jTextFieldIDPedido;
    private javax.swing.JTextField jTextFieldIDProduto;
    private javax.swing.JTextField jTextFieldIDVenda;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldNomeFuncionario;
    private javax.swing.JTextField jTextFieldNomeProduto;
    private javax.swing.JTextField jTextFieldNumeroGrafica;
    private javax.swing.JFormattedTextField jTextFieldQuantidade;
    private javax.swing.JLabel validacaoCliente;
    private javax.swing.JLabel validacaoData;
    private javax.swing.JLabel validacaoFuncionario;
    private javax.swing.JLabel validacaoGrafica;
    // End of variables declaration//GEN-END:variables

    private void limparTudo() {
        viewProdutoPedidos.removeAll(viewProdutoPedidos);
        jTextFieldIDCliente.setText("");
        jTextFieldIDFuncionario.setText("");
        jTextFieldIDVenda.setText("");
        jTextFieldNomeCliente.setText("");
        jTextFieldNomeFuncionario.setText("");
        jTextFieldNumeroGrafica.setText("");
        jTextFieldQuantidade.setText("");
        jTextFieldNomeProduto.setText("");
        jTextFieldIDPedido.setText("");
        jTextFieldIDProduto.setText("");
        jTextFieldCapacidade2.setText("");
        jFormattedTextFieldDATA.setText("");

        listarProduto();
    }

    private void preencherFormulario(ModeloPedido modeloPedido) throws SQLException {
        
        jTextFieldIDCliente.setText("" + modeloPedido.getIdCliente());
        jTextFieldIDFuncionario.setText("" + modeloPedido.getIdFuncionario());
        //jTextFieldIDPedido.setText("" + modeloPedido.getId());
        jTextFieldIDVenda.setText("" + modeloPedido.getIdVenda());
        Connection Connection = new ConnectionFactory().getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(Connection);
        jCheckBoxboleto.setSelected(modeloPedido.isBoleto());
        
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO(Connection);
        ModeloFuncionario modeloFuncionario = new ModeloFuncionario();
        modeloFuncionario = funcionarioDAO.buscaPorId(modeloPedido.getIdFuncionario());
        
        ModeloCliente modeloCliente = clienteDAO.buscaPorId(modeloPedido.getIdCliente());
        jTextFieldNomeCliente.setText(modeloCliente.getNome());
        jTextFieldNomeFuncionario.setText(modeloFuncionario.getNome());
        jTextFieldNumeroGrafica.setText("" + modeloPedido.getNumeroGrafica());

        for (int i = 0; i < jComboBoxTipoPedido.getItemCount(); ++i) {
            ModeloTipoPedido modeloTipoPedidocombo = (ModeloTipoPedido) jComboBoxTipoPedido.getItemAt(i);
            if (modeloPedido.getIdTipoPedido().equals((modeloTipoPedidocombo.getId()))) {
                break;
            }
        }
        Connection.close();

    }

    private String validacao() {
        String validar = "";

        int cont = 0;

        if (jTextFieldIDCliente.getText().isEmpty()) {
            validar += "Cliente ";
            validacaoCliente.setVisible(true);
            cont++;
        }
        if (jTextFieldIDFuncionario.getText().isEmpty()) {
            validar += "\n" + "cundionario ";
            validacaoFuncionario.setVisible(true);
            cont++;
        }
        if (!logica.LogicasPedido.isInt(jTextFieldNumeroGrafica.getText())) {
            validar += "\n" + "Numero da grafica ";
            validacaoGrafica.setVisible(true);
            cont++;
        }
        if (!logica.LogicasPedido.validaData(jFormattedTextFieldDATA.getText())) {
            validar += "\n" + "data ";
            validacaoData.setVisible(true);
            cont++;
        }

        if (cont != 0) {
            validar = "\n" + "Campos obrigatorios: \n" + validar;
        }
        return validar;
    }

    public void desativaLableValidation() {
        validacaoCliente.setVisible(false);
        validacaoData.setVisible(false);
        validacaoFuncionario.setVisible(false);
        validacaoGrafica.setVisible(false);

    }

}
