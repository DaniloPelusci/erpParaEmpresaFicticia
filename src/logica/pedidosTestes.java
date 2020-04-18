/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import DAO.ConnectionFactory;
import DAO.boleto.BoletoDao;
import DAO.cliente.ClienteDAO;
import DAO.pedido.PedidoDAO;
import DAO.venda.ParcelaDAO;
import DAO.venda.VendaDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.Pedido.ModeloPedido;
import Modelo.boleto.ModeloBoleto;
import Modelo.venda.ModeloParcela;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author SERVIDOR
 */
public class pedidosTestes {

    public void gerarboleto() throws SQLException, ParseException {

        Connection connection = new ConnectionFactory().getConnection();
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);

        List<ModeloPedido> pedidos = new ArrayList<ModeloPedido>();
        List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();

        pedidos = pedidoDAO.listarPedidosParaBoleto();
        ModeloBoleto modeloBoleto = new ModeloBoleto();
        VendaDAO vendaDAO = new VendaDAO(connection);
        BoletoDao boletoDao = new BoletoDao(connection);
        int nossoNumero = boletoDao.pegarUltimoNumero();
        nossoNumero++;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar horaAtual = Calendar.getInstance();
        String dataFormatada = sdf.format(horaAtual.getTime());
        Date vencimento;

        String stringDatavencimento;
        Date dataSalvarVencimento;
        Date horaemissao;
        String numDocumento = "";

        for (ModeloPedido e : pedidos) {

            horaemissao = e.getDataEmissao();
            GregorianCalendar vencimento2 = new GregorianCalendar();
            vencimento2.setTime(horaemissao);

            vencimento2.add(Calendar.MONTH, 1);

            vencimento = new Date(sdf.parse(sdf.format(vencimento2.getTime())).getTime());

            parcelas = parcelaDAO.listaPorIdVenda(e.getIdVenda());
            int cont = 0;

            for (ModeloParcela p : parcelas) {

                numDocumento = String.valueOf(e.getNumeroGrafica());
                numDocumento = numDocumento + "." + cont;
                System.err.println(numDocumento);
                if (!p.isEstatosParcela()) {
                    if (e.getIdVenda() == null) {
                        System.err.println("idperido  :" + e.getId());
                        System.err.println(e.getIdVenda());
                    }

                    if (!boletoDao.validaBoleto(numDocumento) && (e.getIdVenda() != null)) {

                        vencimento2.setTime(p.getDataVencimento());

                        vencimento = new Date(sdf.parse(sdf.format(vencimento2.getTime())).getTime());

                        modeloBoleto.setIdPedido(p.getId());

                        modeloBoleto.setValorTitulo(p.getValor());
                        modeloBoleto.setDataEmissao(new Date(sdf.parse(sdf.format(horaAtual.getTime())).getTime()));
                        modeloBoleto.setVencimento(vencimento);
                        modeloBoleto.setNumDocumento(numDocumento);
                        modeloBoleto.setNossoNumero(Long.parseLong(String.valueOf(nossoNumero)));
                        modeloBoleto.setEmitido(false);
                        boletoDao.adicionaInicio(modeloBoleto);

                        nossoNumero++;

                    }

                }
                cont++;
            }
            pedidoDAO.alterarBoleto(true, e.getId());
        }
        connection.close();
    }

    public List<ModeloBoleto> gerarboletoParaGerar() throws SQLException, ParseException {

        Connection connection = new ConnectionFactory().getConnection();
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);

        List<ModeloPedido> pedidos = new ArrayList<ModeloPedido>();
        List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
        List<ModeloBoleto> boletos = new ArrayList<ModeloBoleto>();

        pedidos = pedidoDAO.listarPedidosParaBoleto();

        VendaDAO vendaDAO = new VendaDAO(connection);
        BoletoDao boletoDao = new BoletoDao(connection);
        int nossoNumero = boletoDao.pegarUltimoNumero();
        nossoNumero++;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar horaAtual = Calendar.getInstance();
        String dataFormatada = sdf.format(horaAtual.getTime());
        Date vencimento;

        String stringDatavencimento;
        Date dataSalvarVencimento;
        Date horaemissao;
        String numDocumento = "";

        for (ModeloPedido e : pedidos) {

            horaemissao = e.getDataEmissao();
            GregorianCalendar vencimento2 = new GregorianCalendar();
            vencimento2.setTime(horaemissao);

            vencimento2.add(Calendar.MONTH, 1);

            vencimento = new Date(sdf.parse(sdf.format(vencimento2.getTime())).getTime());

            parcelas = parcelaDAO.listaPorIdVenda(e.getIdVenda());
            int cont = 0;

            for (ModeloParcela p : parcelas) {

                numDocumento = String.valueOf(e.getNumeroGrafica());
                numDocumento = numDocumento + "." + cont;
                System.err.println(numDocumento);
                if (!p.isEstatosParcela()) {
                    if (e.getIdVenda() == null) {
                        System.err.println("idperido  :" + e.getId());
                        System.err.println(e.getIdVenda());
                    }

                    if (!boletoDao.validaBoleto(numDocumento) && (e.getIdVenda() != null)) {
                        ModeloBoleto modeloBoleto = new ModeloBoleto();
                        vencimento2.setTime(p.getDataVencimento());

                        vencimento = new Date(sdf.parse(sdf.format(vencimento2.getTime())).getTime());

                        modeloBoleto.setIdPedido(p.getId());

                        modeloBoleto.setValorTitulo(p.getValor());
                        modeloBoleto.setDataEmissao(new Date(sdf.parse(sdf.format(horaAtual.getTime())).getTime()));
                        modeloBoleto.setVencimento(vencimento);
                        modeloBoleto.setNumDocumento(numDocumento);
                        modeloBoleto.setNossoNumero(Long.parseLong(String.valueOf(nossoNumero)));
                        modeloBoleto.setEmitido(false);
                        boletos.add(modeloBoleto);

                        nossoNumero++;

                    }

                }
                cont++;
            }

        }
        connection.close();
        return boletos;
    }

    public List<ModeloBoleto> gerarboletoParaGerarLocacao(Date data) throws SQLException, ParseException {

        Connection connection = new ConnectionFactory().getConnection();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);

        ModeloParcela parcela = new ModeloParcela();
        List<ModeloBoleto> boletos = new ArrayList<ModeloBoleto>();

        List<ModeloCliente> clientes = new ArrayList<ModeloCliente>();
        clientes = clienteDAO.listaBoleto();

        BoletoDao boletoDao = new BoletoDao(connection);
        int nossoNumero = boletoDao.pegarUltimoNumero();
        nossoNumero++;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar horaAtual = Calendar.getInstance();
        String dataFormatada = sdf.format(horaAtual.getTime());
        Date vencimento;

        String stringDatavencimento;
        Date dataSalvarVencimento;
        Date horaemissao;
        String numDocumento = "";

        for (ModeloCliente cli : clientes) {

            if (clienteDAO.verificarboleto(cli.getId())) {

                if (parcelaDAO.verificaparcelaLocacao(cli.getId(), data)) {

                    parcela = parcelaDAO.listaLocacaoPorReferenciaCliente(cli.getId(), data);

                    numDocumento = String.valueOf(logica.Duplicata.gerarReferencia(parcela.getId()));

                    if (!parcela.isEstatosParcela()) {

                        if (!boletoDao.validaBoleto(numDocumento)) {
                            GregorianCalendar vencimento2 = new GregorianCalendar();
                            ModeloBoleto modeloBoleto = new ModeloBoleto();
                            vencimento2.setTime(parcela.getDataVencimento());

                            vencimento = new Date(sdf.parse(sdf.format(vencimento2.getTime())).getTime());

                            modeloBoleto.setIdPedido(parcela.getId());

                            modeloBoleto.setValorTitulo(parcela.getValor());
                            modeloBoleto.setDataEmissao(new Date(sdf.parse(sdf.format(horaAtual.getTime())).getTime()));
                            modeloBoleto.setVencimento(vencimento);
                            modeloBoleto.setNumDocumento(numDocumento);
                            modeloBoleto.setNossoNumero(Long.parseLong(String.valueOf(nossoNumero)));
                            modeloBoleto.setEmitido(false);
                            boletos.add(modeloBoleto);

                            nossoNumero++;

                        }

                    }

                }
            }
        }
        connection.close();
        return boletos;
    } 
    public List<ModeloBoleto> gerarBoletoLocacao(Date data) throws SQLException, ParseException {

        Connection connection = new ConnectionFactory().getConnection();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);

        ModeloParcela parcela = new ModeloParcela();
        List<ModeloBoleto> boletos = new ArrayList<ModeloBoleto>();

        List<ModeloCliente> clientes = new ArrayList<ModeloCliente>();
        clientes = clienteDAO.listaBoleto();

        BoletoDao boletoDao = new BoletoDao(connection);
        int nossoNumero = boletoDao.pegarUltimoNumero();
        nossoNumero++;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar horaAtual = Calendar.getInstance();
        String dataFormatada = sdf.format(horaAtual.getTime());
        Date vencimento;

        String stringDatavencimento;
        Date dataSalvarVencimento;
        Date horaemissao;
        String numDocumento = "";

        for (ModeloCliente cli : clientes) {

            if (clienteDAO.verificarboleto(cli.getId())) {

                if (parcelaDAO.verificaparcelaLocacao(cli.getId(), data)) {

                    parcela = parcelaDAO.listaLocacaoPorReferenciaCliente(cli.getId(), data);

                    numDocumento = String.valueOf(logica.Duplicata.gerarReferencia(parcela.getId()));

                    if (!parcela.isEstatosParcela()) {

                        if (!boletoDao.validaBoleto(numDocumento)) {
                            GregorianCalendar vencimento2 = new GregorianCalendar();
                            ModeloBoleto modeloBoleto = new ModeloBoleto();
                            vencimento2.setTime(parcela.getDataVencimento());

                            vencimento = new Date(sdf.parse(sdf.format(vencimento2.getTime())).getTime());

                            modeloBoleto.setIdPedido(parcela.getId());

                            modeloBoleto.setValorTitulo(parcela.getValor());
                            modeloBoleto.setDataEmissao(new Date(sdf.parse(sdf.format(horaAtual.getTime())).getTime()));
                            modeloBoleto.setVencimento(vencimento);
                            modeloBoleto.setNumDocumento(numDocumento);
                            modeloBoleto.setNossoNumero(Long.parseLong(String.valueOf(nossoNumero)));
                            modeloBoleto.setEmitido(false);
                            boletoDao.adicionaInicio(modeloBoleto);

                            nossoNumero++;

                        }

                    }

                }
            }
        }
        
        connection.close();
        return boletos;
    }

}
