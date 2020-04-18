/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Boleto;

import DAO.ConnectionFactory;
import DAO.boleto.BoletoDao;
import DAO.boleto.LiquidacaoDAO;
import DAO.boleto.OcorrenciaDAO;
import DAO.cliente.ClienteDAO;
import DAO.pedido.PedidoDAO;
import DAO.venda.ParcelaDAO;
import Modelo.Pedido.ModeloPedido;
import Modelo.boleto.ModeloBoleto;
import Modelo.boleto.ModeloLiquidacaoBoleto;
import Modelo.boleto.ModeloOcorrenciaBoleto;
import Modelo.venda.ModeloParcela;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author usuario
 */
public class LogicaRetorno {

    public void identificadorLinha(String linha, Connection conection) throws SQLException, ParseException {

        int tipoRegistro = Integer.parseInt(linha.substring(0, 1));
        String numDocumento = linha.substring(116, 126);
        numDocumento = numDocumento.replace(" ", "");

        if (tipoRegistro == 1) {

            gerarboleto(linha, conection);

            //registrarBoleto(linha);
        }
    }

    private void registrarBoleto(String linha) throws SQLException {

        Connection connection = new ConnectionFactory().getConnection();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ModeloPedido modeloPedido = new ModeloPedido();
        ModeloParcela modeloParcela = new ModeloParcela();
        String registro = linha.substring(116, 126);

        if (registro.indexOf("/") > 0) {
            System.out.println("locação: " + registro);

            String locacao[] = registro.split("-");

            String data[] = locacao[1].split("/");
            String idCliente = locacao[0];

            String mes = data[0];
            String ano = data[1];
//                    System.out.println("Id Cliente: "+idCliente);
//                    System.out.println("20"+ano.trim());
//                    System.out.println(mes);

            modeloParcela = parcelaDAO.locacaoPorReferencia(Long.parseLong(idCliente), Integer.parseInt(mes), Integer.parseInt("20" + ano.trim()));
            //   System.out.println("mês: "+modeloParcela.getId());   

        } else if (registro.indexOf("-") < 0) {
            System.out.println("Pedido: " + registro.trim());
            if (pedidoDAO.verificaPedidoNumerografica(Long.parseLong(registro.trim()))) {
                modeloPedido = pedidoDAO.buscaPornPedido(Integer.parseInt(registro.trim()));
                System.out.println(modeloPedido.getId());
                String nome = linha.substring(85, 93);
                String nome2 = linha.substring(126, 134);

                System.out.println("Nosso numero id: " + nome);
                System.out.println("Nosso numero : " + nome2);
            } else {

            }
        }
        String tipoRegistro = linha.substring(0, 1);
        connection.close();

    }

    private void gerarboleto(String linha, Connection connection) throws ParseException, SQLException {

        ModeloBoleto modeloBoleto = new ModeloBoleto();

        ModeloLiquidacaoBoleto modeloLiquidacaoBoleto = new ModeloLiquidacaoBoleto();
        ModeloOcorrenciaBoleto modeloOcorrenciaBoleto = new ModeloOcorrenciaBoleto();
        LiquidacaoDAO liquidacaoDAO = new LiquidacaoDAO(connection);
        OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO(connection);
        BoletoDao boletoDao = new BoletoDao(connection);
        PedidoDAO pedidoDAO = new PedidoDAO(connection);

        String dataOcorrencia = linha.substring(110, 116);

        SimpleDateFormat format = new SimpleDateFormat("ddMMyy");
        SimpleDateFormat format2 = new SimpleDateFormat("dd/MM/yyyy");
        String numDocumento = linha.substring(116, 126);
        numDocumento = numDocumento.replace(" ", "");
        modeloBoleto.setDataocorrencia(new java.sql.Date(format.parse(dataOcorrencia).getTime()));
        modeloBoleto.setNumDocumento(numDocumento);

        modeloBoleto.setNossoNumero(Long.parseLong(linha.substring(126, 134)));
        String dataVencimento = linha.substring(146, 152);
        modeloBoleto.setVencimento(new java.sql.Date(format.parse(dataVencimento).getTime()));
        String valorTitulo = linha.substring(152, 163);
        String valorTitulo2 = linha.substring(163, 165);
        String valorTituloTotal = valorTitulo + "." + valorTitulo2;
        modeloBoleto.setValorTitulo(new BigDecimal(valorTituloTotal));
        String dataCredito = linha.substring(295, 301);
        String liquidacao = linha.substring(392, 394);
        String ocorrencia = linha.substring(108, 110);
        if (modeloBoleto.getNumDocumento().indexOf("/") == -1) {

            if (modeloBoleto.getNumDocumento().indexOf("-") == -1) {

                if (modeloBoleto.getNumDocumento().indexOf(".") != -1) {

                    String[] t = modeloBoleto.getNumDocumento().split(".");
                    modeloBoleto.setIdPedido(pedidoDAO.buscaPornPedido(Integer.parseInt(t[0])).getId());

                } else if (pedidoDAO.verificaPedidoNumerografica(Long.parseLong(modeloBoleto.getNumDocumento()))) {

                    modeloBoleto.setIdPedido(pedidoDAO.buscaPornPedido(Integer.parseInt(modeloBoleto.getNumDocumento())).getId());
                }
            }
        } else {
            System.out.println("arroz" + modeloBoleto.getNumDocumento());
        }

        if (!liquidacao.equals("  ")) {
            modeloBoleto.setCodLiquidacao(liquidacaoDAO.buscaPorliquidacao(liquidacao).getId());
        } else {
            modeloBoleto.setCodLiquidacao(Long.parseLong("25"));
        }
        modeloBoleto.setCodOcorrencia(Long.parseLong(ocorrencia));

        if (logica.LogicasPedido.validaData2(dataCredito)) {
            modeloBoleto.setDataLiquidacaoCredito(new java.sql.Date(format.parse(dataCredito).getTime()));
        }
        modeloBoleto.setEmitido(true);
        System.out.println("pedido: " + modeloBoleto.getIdPedido());
        System.out.println("numero documento: " + modeloBoleto.getNumDocumento());
        System.out.println("nosso numero: " + modeloBoleto.getNossoNumero());
        System.out.println("Data Ocorrencia: " + format2.format(modeloBoleto.getDataocorrencia().getTime()));

        System.out.println("data vencimento: " + format2.format(modeloBoleto.getVencimento().getTime()));
        System.out.println("Valor Do Titulo: " + modeloBoleto.getValorTitulo());

        if (logica.LogicasPedido.validaData2(dataCredito)) {
            System.out.println("data Credito: " + format2.format(modeloBoleto.getDataLiquidacaoCredito().getTime()));
        } else {
            System.out.println("data Credito: 00/00/0000");
        }
        if (!liquidacao.equals("  ")) {
            System.out.println("Liquidacao: " + liquidacaoDAO.buscaPorId(modeloBoleto.getCodLiquidacao()).getNome());
        }
        System.out.println("Ocorrencia: " + modeloBoleto.getCodOcorrencia());
        System.err.println(modeloBoleto.isEmitido());

        System.out.println("");
        if (modeloBoleto.getIdPedido()!=null) {
            if (!boletoDao.validaBoleto(numDocumento)) {
                boletoDao.adiciona(modeloBoleto);
            } else {
                boletoDao.altera(modeloBoleto);
            }
        }else{
            if (!boletoDao.validaBoleto(numDocumento)) {
                boletoDao.adicionaLocacao(modeloBoleto);
            } else {
                boletoDao.alteraLocacao(modeloBoleto);
            }
        }
    }

}
