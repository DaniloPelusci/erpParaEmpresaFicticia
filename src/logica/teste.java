/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import DAO.BairroDAO;
import DAO.CidadeDAO;
import DAO.ConnectionFactory;
import DAO.EstadoDAO;
import DAO.boleto.BoletoDao;
import DAO.cliente.ClienteDAO;
import DAO.pedido.PedidoDAO;
import DAO.venda.ParcelaDAO;
import DAO.venda.VendaDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.Pedido.ModeloPedido;
import Modelo.boleto.ModeloBoleto;
import Modelo.venda.ModeloParcela;
import Modelo.venda.ModeloVenda;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.CEP;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

/**
 *
 * @author usuario
 */
public class teste {

    public void executar(ModeloBoleto modeloBoleto) throws SQLException {

        Connection connection = new ConnectionFactory().getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        VendaDAO vendaDAO = new VendaDAO(connection);
        BairroDAO bairroDAO = new BairroDAO(connection);
        CidadeDAO cidadeDAO = new CidadeDAO(connection);
        EstadoDAO estadoDAO = new EstadoDAO(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        NovoClass novoClass = new NovoClass();

        //modelos
        ModeloBoleto modeloboletoEmiissao = new ModeloBoleto();
        ModeloCliente modeloCliente = new ModeloCliente();
        ModeloPedido modeloPedido = new ModeloPedido();
        ModeloVenda modeloVenda = new ModeloVenda();
        ModeloParcela modeloParcela = new ModeloParcela();

        modeloboletoEmiissao = modeloBoleto;

        modeloParcela = parcelaDAO.buscaPorId(modeloboletoEmiissao.getIdPedido());

        modeloPedido = pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(modeloParcela.getIdVenda())));
        modeloCliente = clienteDAO.buscaPorId(modeloPedido.getIdCliente());
        modeloVenda = vendaDAO.buscaPorId(modeloPedido.getIdVenda());
        String numeroDocumento = modeloBoleto.getNumDocumento();
        numeroDocumento = numeroDocumento.replace(".", "-");

        /*
                 * INFORMANDO DADOS SOBRE O CEDENTE.
         */
        System.err.println(modeloCliente.getNome());
        Cedente cedente = new Cedente("EBO EMPRESA BRASILEIRA DE OXIGENIO LTDA", "06.338.353/0001-82");
        //Cedente cedente = new Cedente("EBO EMPRESA BRASILEIRA", "018.276.761-24");

        /*
                 * INFORMANDO DADOS SOBRE O SACADO.
         */
        String cadastro = "";

        if (modeloCliente.getCnpj() != null) {
            cadastro = modeloCliente.getCnpj();
            cadastro = cadastro.replace(" ", "");

        } else {
            cadastro = modeloCliente.getCpf();
            cadastro = cadastro.replace(" ", "");

        }
        Sacado sacado = new Sacado(modeloCliente.getNome(), cadastro);
        // Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();

        enderecoSac.setUF(UnidadeFederativa.GO);

        //enderecoSac.setLocalidade("Goiania");
        enderecoSac.setCep(new CEP("74000-000"));
        enderecoSac.setBairro(bairroDAO.buscaPorId(modeloCliente.getBairro()).getNome());
        enderecoSac.setLogradouro(modeloCliente.getEndereco());
        enderecoSac.setNumero("0");
        sacado.addEndereco(enderecoSac);

        /*
                 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
         */
//        SacadorAvalista sacadorAvalista = new SacadorAvalista("", "");
//
//        // Informando o endereço do sacador avalista.
//        Endereco enderecoSacAval = new Endereco();
//        enderecoSacAval.setUF(UnidadeFederativa.GO);
//        enderecoSacAval.setLocalidade("");
//        enderecoSacAval.setCep(new CEP(""));
//        enderecoSacAval.setBairro("");
//        enderecoSacAval.setLogradouro("");
//        enderecoSacAval.setNumero("001");
//        sacadorAvalista.addEndereco(enderecoSacAval);

        /*
                 * INFORMANDO OS DADOS SOBRE O TÍTULO.
         */
        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(43222, "0"));
        contaBancaria.setCarteira(new Carteira(109));
        contaBancaria.setAgencia(new org.jrimum.domkee.financeiro.banco.febraban.Agencia(4373));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento(numeroDocumento);
        titulo.setNossoNumero(String.valueOf(modeloBoleto.getNossoNumero()));

        //titulo.setDigitoDoNossoNumero("1");
        titulo.setValor(modeloBoleto.getValorTitulo());
        titulo.setDataDoDocumento(modeloBoleto.getDataEmissao());
        titulo.setDataDoVencimento(modeloBoleto.getVencimento());
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Aceite.N);
        //titulo.setDesconto(new BigDecimal(0.05));
        //titulo.setDeducao(BigDecimal.ZERO);
        double mora = Double.parseDouble(String.valueOf(modeloBoleto.getValorTitulo()));
        DecimalFormat formato = new DecimalFormat("#.##");

        double multa = mora * 0.059;
        String multa2 = formato.format(multa);
        multa2 = multa2.replace(",", ".");

        multa = Double.parseDouble(multa2);
        mora = Double.parseDouble(formato.format((mora / 30) * 0.059).replace(",", "."));

        //titulo.setMora(new BigDecimal(multa));
        //titulo.setAcrecimo(new BigDecimal(5));
        //titulo.setValorCobrado(BigDecimal.ZERO);

        /*
                 * INFORMANDO OS DADOS SOBRE O BOLETO.
         */
        Boleto boleto = new Boleto(titulo);

        boleto.setLocalPagamento("ATÉ O VENCIMENTO, PREFERENCIALMENTE NO ITAÚ. "
                + "APÓS O VENCIMENTO, SOMENTE NO ITAÚ");
//        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor "
//                + "cobrado não é o esperado, aproveite o DESCONTÃO!");
//        
        boleto.setInstrucao1("APOS VENCIMENTO COBRAR R$" + mora + " POR DIA DE ATRASO");
        boleto.setInstrucao2("APOS VENCIMENTO COBRAR MULTA DE R$" + multa);
        if (!modeloVenda.getNotaFiscal().equals("sem nota")) {
            boleto.setInstrucao3("BOLETO REFERENTE A NOTA: "+modeloVenda.getNotaFiscal());
        } else {
            boleto.setInstrucao3("BOLETO REFERENTE AO PEDIDO: " + modeloPedido.getNumeroGrafica());
        }
        /*
                 * GERANDO O BOLETO BANCÁRIO.
         */
        // Instanciando um objeto "BoletoViewer", classe responsável pela
        // geração do boleto bancário.
        BoletoViewer boletoViewer = new BoletoViewer(boleto);

        // Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
        // pasta do projeto. Outros exemplos:
        // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
        // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
        System.err.println(modeloVenda.getId());

        String numerodoc = modeloBoleto.getNumDocumento().replace(".", "-");;

        String t[] = numerodoc.split("-");

        System.out.println("S:\\boletos\\" + t[0] + "-" + modeloCliente.getNome().substring(0, 10) + "-" + modeloVenda.getId() + ".pdf");

        if (t[1].equals("0")) {
            File arquivoPdf = boletoViewer.getPdfAsFile("S:\\boletos\\" + modeloBoleto.getNumDocumento() + "-" + modeloCliente.getNome().substring(0, 10) + "-" + modeloVenda.getId() + ".pdf");
        } else {
            File arquivoPdf = boletoViewer.getPdfAsFile("S:\\boletos\\" + modeloBoleto.getNumDocumento() + "-" + modeloCliente.getNome().substring(0, 10) + "-" + modeloVenda.getId() + ".pdf");

        }
        // Mostrando o boleto gerado na tela.
        // mostreBoletoNaTela(arquivoPdf);
    }

    public void executarLocacao(ModeloBoleto modeloBoleto) throws SQLException {

        Connection connection = new ConnectionFactory().getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        VendaDAO vendaDAO = new VendaDAO(connection);
        BairroDAO bairroDAO = new BairroDAO(connection);
        CidadeDAO cidadeDAO = new CidadeDAO(connection);
        EstadoDAO estadoDAO = new EstadoDAO(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        NovoClass novoClass = new NovoClass();

        //modelos
        ModeloBoleto modeloboletoEmiissao = new ModeloBoleto();
        ModeloCliente modeloCliente = new ModeloCliente();
        ModeloPedido modeloPedido = new ModeloPedido();
        
        ModeloParcela modeloParcela = new ModeloParcela();

        modeloboletoEmiissao = modeloBoleto;
        String[] arrayValores = modeloBoleto.getNumDocumento().split("-");

        String idCliente = arrayValores[0];

        String data = arrayValores[1];
        
        String[] arrayValores2 = data.split("/");
        String mes = arrayValores2[0];
        String ano = arrayValores2[1];

        modeloParcela = parcelaDAO.locacaoPorReferencia(Long.parseLong(idCliente), Integer.parseInt(mes), Integer.parseInt(ano));
        System.out.println(modeloParcela.getIdCliente());
        modeloCliente = clienteDAO.buscaPorId(modeloParcela.getIdCliente());

        String numeroDocumento = modeloBoleto.getNumDocumento();
        numeroDocumento = numeroDocumento.replace(".", "-");

        /*
                 * INFORMANDO DADOS SOBRE O CEDENTE.
         */
        Cedente cedente = new Cedente("EBO EMPRESA BRASILEIRA DE OXIGENIO LTDA", "06.338.353/0001-82");
        //Cedente cedente = new Cedente("EBO EMPRESA BRASILEIRA", "018.276.761-24");

        /*
                 * INFORMANDO DADOS SOBRE O SACADO.
         */
        String cadastro = "";

        if (modeloCliente.getCnpj() != null) {
            cadastro = modeloCliente.getCnpj();
            cadastro = cadastro.replace(" ", "");

        } else {
            cadastro = modeloCliente.getCpf();
            cadastro = cadastro.replace(" ", "");

        }
        Sacado sacado = new Sacado(modeloCliente.getNome(), cadastro);
        // Informando o endereço do sacado.
        Endereco enderecoSac = new Endereco();

        enderecoSac.setUF(UnidadeFederativa.GO);

        //enderecoSac.setLocalidade("Goiania");
        enderecoSac.setCep(new CEP("74000-000"));
        enderecoSac.setBairro(bairroDAO.buscaPorId(modeloCliente.getBairro()).getNome());
        enderecoSac.setLogradouro(modeloCliente.getEndereco());
        enderecoSac.setNumero("0");
        sacado.addEndereco(enderecoSac);

        /*
                 * INFORMANDO DADOS SOBRE O SACADOR AVALISTA.
         */
//        SacadorAvalista sacadorAvalista = new SacadorAvalista("", "");
//
//        // Informando o endereço do sacador avalista.
//        Endereco enderecoSacAval = new Endereco();
//        enderecoSacAval.setUF(UnidadeFederativa.GO);
//        enderecoSacAval.setLocalidade("");
//        enderecoSacAval.setCep(new CEP(""));
//        enderecoSacAval.setBairro("");
//        enderecoSacAval.setLogradouro("");
//        enderecoSacAval.setNumero("001");
//        sacadorAvalista.addEndereco(enderecoSacAval);

        /*
                 * INFORMANDO OS DADOS SOBRE O TÍTULO.
         */
        // Informando dados sobre a conta bancária do título.
        ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_ITAU.create());
        contaBancaria.setNumeroDaConta(new NumeroDaConta(43222, "0"));
        contaBancaria.setCarteira(new Carteira(109));
        contaBancaria.setAgencia(new org.jrimum.domkee.financeiro.banco.febraban.Agencia(4373));

        Titulo titulo = new Titulo(contaBancaria, sacado, cedente);
        titulo.setNumeroDoDocumento(numeroDocumento);
        titulo.setNossoNumero(String.valueOf(modeloBoleto.getNossoNumero()));

        //titulo.setDigitoDoNossoNumero("1");
        titulo.setValor(modeloBoleto.getValorTitulo());
        titulo.setDataDoDocumento(modeloBoleto.getDataEmissao());
        titulo.setDataDoVencimento(modeloBoleto.getVencimento());
        titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
        titulo.setAceite(Aceite.N);
        //titulo.setDesconto(new BigDecimal(0.05));
        //titulo.setDeducao(BigDecimal.ZERO);
        double mora = Double.parseDouble(String.valueOf(modeloBoleto.getValorTitulo()));
        DecimalFormat formato = new DecimalFormat("#.##");

        double multa = mora * 0.059;
        String multa2 = formato.format(multa);
        multa2 = multa2.replace(",", ".");

        multa = Double.parseDouble(multa2);
        mora = Double.parseDouble(formato.format((mora / 30) * 0.059).replace(",", "."));

        //titulo.setMora(new BigDecimal(multa));
        //titulo.setAcrecimo(new BigDecimal(5));
        //titulo.setValorCobrado(BigDecimal.ZERO);

        /*
                 * INFORMANDO OS DADOS SOBRE O BOLETO.
         */
        Boleto boleto = new Boleto(titulo);

        boleto.setLocalPagamento("ATÉ O VENCIMENTO, PREFERENCIALMENTE NO ITAÚ. "
                + "APÓS O VENCIMENTO, SOMENTE NO ITAÚ");
//        boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor "
//                + "cobrado não é o esperado, aproveite o DESCONTÃO!");
//        
        boleto.setInstrucao1("APOS VENCIMENTO COBRAR R$" + mora + " POR DIA DE ATRASO");
        boleto.setInstrucao2("APOS VENCIMENTO COBRAR MULTA DE R$" + multa);
        
            boleto.setInstrucao3("BOLETO REFERENTE A LOCAÇÃO: " + modeloBoleto.getNumDocumento());
        
        /*
                 * GERANDO O BOLETO BANCÁRIO.
         */
        // Instanciando um objeto "BoletoViewer", classe responsável pela
        // geração do boleto bancário.
        BoletoViewer boletoViewer = new BoletoViewer(boleto);

        // Gerando o arquivo. No caso o arquivo mencionado será salvo na mesma
        // pasta do projeto. Outros exemplos:
        // WINDOWS: boletoViewer.getAsPDF("C:/Temp/MeuBoleto.pdf");
        // LINUX: boletoViewer.getAsPDF("/home/temp/MeuBoleto.pdf");
        System.err.println(modeloBoleto.getNumDocumento());

        String numerodoc = modeloBoleto.getNumDocumento().replace(".", "-");;

        String t[] = numerodoc.split("-");
        String referencia = modeloBoleto.getNumDocumento().replace("/", "_");
        System.out.println("S:\\boletos\\" + "10010" + "-");

        if (t[1].equals("0")) {
            File arquivoPdf = boletoViewer.getPdfAsFile("S:\\boletos\\" + t[0] + "-" + modeloCliente.getNome().substring(0, 10) + "-"+ referencia+ ".pdf");
        } else {
            File arquivoPdf = boletoViewer.getPdfAsFile("S:\\boletos\\" + referencia + "-" + modeloCliente.getNome().substring(0, 10) + "-" + referencia + ".pdf");

        }
        // Mostrando o boleto gerado na tela.
        // mostreBoletoNaTela(arquivoPdf);
    }

    /**
     * Exibe o arquivo na tela.
     *
     * @param arquivoBoleto
     *
     */
    private static void mostreBoletoNaTela(File arquivoBoleto) {

        java.awt.Desktop desktop = java.awt.Desktop.getDesktop();

        try {
            desktop.open(arquivoBoleto);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileWriter dadosBoletos(FileWriter arq, ModeloBoleto modeloBoleto, String sequencial) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        BairroDAO bairroDAO = new BairroDAO(connection);
        CidadeDAO cidadeDAO = new CidadeDAO(connection);
        EstadoDAO estadoDAO = new EstadoDAO(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        //modelos
        ModeloBoleto modeloboletoEmiissao = new ModeloBoleto();
        ModeloCliente modeloCliente = new ModeloCliente();
        ModeloPedido modeloPedido = new ModeloPedido();
        ModeloParcela modeloParcela = new ModeloParcela();

        modeloboletoEmiissao = modeloBoleto;
        modeloParcela = parcelaDAO.buscaPorId(modeloboletoEmiissao.getIdPedido());

        modeloPedido = pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(modeloParcela.getIdVenda())));

        modeloCliente = clienteDAO.buscaPorId(modeloPedido.getIdCliente());

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMYY");

        String dataFormatada = sdf.format(modeloBoleto.getVencimento().getTime());

        FileWriter arq2 = arq;
        String tipoRegistro = "1";
        String codigoInscricao = "02";
        String cNPJ = "06338353000182";
        String agencia = "4373";
        String zeros = "00";
        String conta = "43222";
        String dAC = "0";
        String brancos1 = "";
        String instrucaoAlegacao = "";
        String usoEmpresa = "";
        String nossoNumero = String.valueOf(modeloboletoEmiissao.getNossoNumero());
        String qtdeMoeda = "";
        String nCarteira = "109";
        String usoDobanco = "";
        String carteira = "I";
        String idOcorrencia = "01";
        // começa os dados do boleto migrado

        String nDocumento = String.valueOf(modeloboletoEmiissao.getNumDocumento());

        String dataVencimento = dataFormatada;
        String valorTitulo1 = String.valueOf(modeloBoleto.getValorTitulo());

        //fim 
        String codigoBanco = "341";
        String agenciaCobradora = "00000";
        String especie = "01";
        String aceite = "N";

        // dados inseridos        
        String dataEmissao2 = sdf.format(modeloBoleto.getVencimento().getTime());

        String dataEmissao = dataEmissao2;

        String instrucao1 = "00";
        String instruccao2 = "00";

        String jurosDe1Dia = String.valueOf(calculoJuros(modeloBoleto.getValorTitulo().toString()));
        String descontoAte = "000000";
        String valorDesconto = "";
        String valorIOF = "";
        String abatimento = "";
        String codigoInscricaoPagador = "02";
        String numeroDeInscricaoPagador = "";
        if (modeloCliente.getCnpj() == null) {
            codigoInscricaoPagador = "01";
            numeroDeInscricaoPagador = modeloCliente.getCpf();

        } else {
            codigoInscricaoPagador = "02";
            numeroDeInscricaoPagador = modeloCliente.getCnpj();

        }

        System.out.println(numeroDeInscricaoPagador);
        String nomePagador = modeloCliente.getNome();
        String brancos2 = "";
        String logradouro = modeloCliente.getEndereco();
        String bairroPagador = bairroDAO.buscaPorId(modeloCliente.getBairro()).getNome();
        String cepPagador = modeloCliente.getCep();
        String cidadePagador = cidadeDAO.buscaPorId(modeloCliente.getCidade()).getNome();
        String ufPagador = estadoDAO.buscaPorId(modeloCliente.getEstado()).getSigla();
        String nomeSacadorAvalista = "";
        String brancos3 = "";
        String dataMora = "000000";
        String prazo = "00";
        String brancos4 = "";
        String numeroSequencial = sequencial;

        brancos1 = String.format("%4s", brancos1);
        instrucaoAlegacao = String.format("%4s", instrucaoAlegacao);
        usoEmpresa = String.format("%25s", usoEmpresa);
        qtdeMoeda = String.format("%13s", qtdeMoeda);
        qtdeMoeda = qtdeMoeda.replace(" ", "0");
        usoDobanco = String.format("%21s", usoDobanco);

        nossoNumero = String.format("%8s", nossoNumero);
        nossoNumero = nossoNumero.replace(" ", "0");
        nossoNumero = nossoNumero.substring(0, 8);

        int cont = 0;
        if (valorTitulo1.indexOf(".") != -1) { //Se for diferente de -1 é pq existe o caracter.
            valorTitulo1 = valorTitulo1.replace(".", "");
            valorTitulo1 = String.format("%13s", valorTitulo1);
            valorTitulo1 = valorTitulo1.replace(" ", "0");

            cont++;
        }

        if (cont <= 0) {
            valorTitulo1 = valorTitulo1 + "00";
            valorTitulo1 = String.format("%13s", valorTitulo1);
            valorTitulo1 = valorTitulo1.replace(" ", "0");

        }

        jurosDe1Dia = jurosDe1Dia.replace(".", "");
        jurosDe1Dia = String.format("%13s", jurosDe1Dia);
        jurosDe1Dia = jurosDe1Dia.replace(" ", "0");

        valorDesconto = String.format("%13s", valorDesconto);
        valorDesconto = valorDesconto.replace(" ", "0");
        valorIOF = String.format("%13s", valorIOF);
        valorIOF = valorIOF.replace(" ", "0");
        abatimento = String.format("%13s", abatimento);
        abatimento = valorIOF.replace(" ", "0");
        numeroDeInscricaoPagador = numeroDeInscricaoPagador.replace(".", "");
        numeroDeInscricaoPagador = numeroDeInscricaoPagador.replace("/", "");
        numeroDeInscricaoPagador = numeroDeInscricaoPagador.replace("-", "");
        numeroDeInscricaoPagador = numeroDeInscricaoPagador.replace(" ", "");
        numeroDeInscricaoPagador = String.format("%14s", numeroDeInscricaoPagador);
        numeroDeInscricaoPagador = numeroDeInscricaoPagador.replace(" ", "0");
        nomePagador = nomePagador.replace("°", "");
        nomePagador = nomePagador.replace("º", "");
        nomePagador = nomePagador.replace("Â", "A");
        nomePagador = nomePagador.replace("Á", "A");
        nomePagador = nomePagador.replace("Ã", "A");
        nomePagador = nomePagador.replace("Í", "I");
        nomePagador = nomePagador.replace("Ì", "I");
        nomePagador = nomePagador.replace("É", "E");
        nomePagador = nomePagador.replace("	", "");
        nomePagador = nomePagador.replace("Ç", "C");
        nomePagador = nomePagador.replace("Õ", "O");
        nomePagador = nomePagador.replace("Ô", "O");
        nomePagador = nomePagador.replace("Ó", "O");

        nomePagador = String.format("%-30s", nomePagador);
        nomePagador = nomePagador.substring(0, 30);
        logradouro = logradouro.replace("°", "");
        logradouro = logradouro.replace("º", "");
        logradouro = logradouro.replace("Ã", "A");
        logradouro = logradouro.replace("Â", "A");
        logradouro = logradouro.replace("Á", "A");
        logradouro = logradouro.replace("Ì", "I");
        logradouro = logradouro.replace("Í", "I");
        logradouro = logradouro.replace("É", "E");
        logradouro = logradouro.replace("	", "");
        logradouro = logradouro.replace("Ç", "C");
        logradouro = logradouro.replace("Õ", "O");
        logradouro = logradouro.replace("Ô", "O");
        logradouro = logradouro.replace("Ó", "O");
        logradouro = String.format("%-40s", logradouro);
        logradouro = logradouro.substring(0, 40);

        brancos2 = String.format("%10s", brancos2);
        //logradouro = String.format("%40s", logradouro);
        //logradouro = logradouro.substring(0, 40);
        bairroPagador = bairroPagador.replace("°", "");
        bairroPagador = bairroPagador.replace("º", "");
        bairroPagador = bairroPagador.replace("Ã", "A");
        bairroPagador = bairroPagador.replace("Â", "A");
        bairroPagador = bairroPagador.replace("Á", "A");
        bairroPagador = bairroPagador.replace("Ì", "I");
        bairroPagador = bairroPagador.replace("Í", "I");
        bairroPagador = bairroPagador.replace("É", "E");
        bairroPagador = bairroPagador.replace("   ", "");
        bairroPagador = bairroPagador.replace("Ç", "C");
        bairroPagador = bairroPagador.replace("Õ", "O");
        bairroPagador = bairroPagador.replace("Ô", "O");
        bairroPagador = bairroPagador.replace("Ó", "O");
        bairroPagador = String.format("%-12s", bairroPagador);
        bairroPagador = bairroPagador.substring(0, 12);
        cepPagador = cepPagador.replace("-", "");

        cidadePagador = cidadePagador.replace("°", "");
        cidadePagador = cidadePagador.replace("º", "");
        cidadePagador = cidadePagador.replace("Ã", "A");
        cidadePagador = cidadePagador.replace("Â", "A");
        cidadePagador = cidadePagador.replace("Á", "A");
        cidadePagador = cidadePagador.replace("Ì", "I");
        cidadePagador = cidadePagador.replace("Í", "I");
        cidadePagador = cidadePagador.replace("É", "E");
        cidadePagador = cidadePagador.replace("	", "");
        cidadePagador = cidadePagador.replace("   ", "");
        cidadePagador = cidadePagador.replace("Ç", "C");
        cidadePagador = cidadePagador.replace("Õ", "O");
        cidadePagador = cidadePagador.replace("Ô", "O");
        cidadePagador = cidadePagador.replace("Ó", "O");
        cidadePagador = String.format("%-15s", cidadePagador);
        cidadePagador = cidadePagador.substring(0, 15);
        ufPagador = String.format("%2s", ufPagador);
        nomeSacadorAvalista = String.format("%30s", nomeSacadorAvalista);
        brancos3 = String.format("%4s", brancos3);
        brancos4 = String.format("%1s", brancos4);

        nDocumento = nDocumento.replace(".", "-");
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaa: " + nDocumento);
        nDocumento = String.format("%-10s", nDocumento);

        numeroSequencial = String.format("%6s", numeroSequencial);
        numeroSequencial = numeroSequencial.replace(" ", "0");

        //cpf = cpf.replace(".", "");
        //escreve txt
        PrintWriter gravarArq = new PrintWriter(arq2);
        gravarArq.printf(tipoRegistro);
        gravarArq.printf(codigoInscricao);
        gravarArq.printf(cNPJ);
        gravarArq.printf(agencia);
        gravarArq.printf(zeros);
        gravarArq.printf(conta);
        gravarArq.printf(dAC);
        gravarArq.printf(brancos1);
        gravarArq.printf(instrucaoAlegacao);
        gravarArq.printf(usoEmpresa);
        gravarArq.printf(nossoNumero);
        gravarArq.printf(qtdeMoeda);
        gravarArq.printf(nCarteira);
        gravarArq.printf(usoDobanco);
        gravarArq.printf(carteira);
        gravarArq.printf(idOcorrencia);
        gravarArq.printf(nDocumento);
        gravarArq.printf(dataVencimento);
        gravarArq.printf(valorTitulo1);
        gravarArq.printf(codigoBanco);
        gravarArq.printf(agenciaCobradora);
        gravarArq.printf(especie);
        gravarArq.printf(aceite);
        gravarArq.printf(dataEmissao);
        gravarArq.printf(instrucao1);
        gravarArq.printf(instruccao2);
        gravarArq.printf(jurosDe1Dia);
        gravarArq.printf(descontoAte);
        gravarArq.printf(valorDesconto);
        gravarArq.printf(valorIOF);
        gravarArq.printf(abatimento);
        gravarArq.printf(codigoInscricaoPagador);
        gravarArq.printf(numeroDeInscricaoPagador);

        gravarArq.printf(nomePagador);

        gravarArq.printf(brancos2);
        gravarArq.printf(logradouro);
        gravarArq.printf(bairroPagador);
        gravarArq.printf(cepPagador);
        gravarArq.printf(cidadePagador);
        gravarArq.printf(ufPagador);
        gravarArq.printf(nomeSacadorAvalista);
        gravarArq.printf(brancos3);
        gravarArq.printf(dataMora);
        gravarArq.printf(prazo);
        gravarArq.printf(brancos4);

        gravarArq.printf(numeroSequencial);
        gravarArq.printf("%n");
        System.err.println(nDocumento);
        System.err.println(nossoNumero);
        connection.close();
        return arq2;

    }

    public double calculoJuros(String valor) {

        //calculo = new BigDecimal("30").divide(valor) ;
        double calculo = Double.parseDouble(valor);
        double resultado;

        resultado = (calculo / 30) * 0.059;
        DecimalFormat df = new DecimalFormat("#.00");

        String resultado2 = df.format(resultado);
        resultado2 = resultado2.replace(" ", "0");
        resultado2 = resultado2.replace(",", ".");

        resultado = Double.parseDouble(resultado2);

        return resultado;
    }

    public FileWriter Hender(FileWriter arq) throws IOException {
        FileWriter arq2 = arq;
        String fraseInteira = "";
        String cobranca = "";
        String fraseCortada = String.format("%8s", fraseInteira);
        String nomeEmpresa = "EBO EMPRESA B OXIGENIO LTDA ME";
        String codigoDObanco = "341";
        String nomeBanco = "BANCO ITAU SA";
        String branco = "";
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMYY");
        Calendar horaAtual = Calendar.getInstance();
        String dataFormatada = sdf.format(horaAtual.getTime());

        branco = String.format("%294s", branco);
        cobranca = "COBRANCA" + String.format("%7s", cobranca);
        nomeBanco = String.format("%-15S", nomeBanco);
        nomeBanco = nomeBanco.substring(0, 15);

        nomeEmpresa = nomeEmpresa.substring(0, 30);

        //fraseInteira.substring(0,100);
        System.err.println(fraseCortada);
        int i, n;

        n = 10;

        PrintWriter gravarArq = new PrintWriter(arq2);

        gravarArq.printf("01");
        gravarArq.printf("REMESSA");
        gravarArq.printf("01");
        gravarArq.printf(cobranca);
        gravarArq.printf("4373");
        gravarArq.printf("00");
        gravarArq.printf("43222");
        gravarArq.printf("0");
        gravarArq.printf(fraseCortada);
        gravarArq.printf(nomeEmpresa);
        gravarArq.printf(codigoDObanco);
        gravarArq.printf(nomeBanco);
        gravarArq.printf(dataFormatada);
        gravarArq.printf(branco);
        gravarArq.printf("000001%n");

        System.out.printf("remessa foi salva com sucesso!! ", n);
        // NovoClass nc = new NovoClass();
        // teste boleto = new teste();
        return arq2;
    }

    public FileWriter rodape(FileWriter arq, String sequencial) {
        FileWriter arq2 = arq;

        String tipoRegistro = "9";
        String brancos = "";
        String numeroSequencial = sequencial;

        brancos = String.format("%393s", brancos);
        numeroSequencial = String.format("%6s", numeroSequencial);
        numeroSequencial = numeroSequencial.replace(" ", "0");

        PrintWriter gravarArq = new PrintWriter(arq2);

        gravarArq.printf(tipoRegistro);
        gravarArq.printf(brancos);
        gravarArq.printf(numeroSequencial);
        gravarArq.printf("%n");

        return arq2;

    }

    public void contruirRemessa(List<ModeloBoleto> listaBoleto) throws IOException, SQLException {

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMYY");
        Calendar horaAtual = Calendar.getInstance();
        String dataFormatada = sdf.format(horaAtual.getTime());

        Connection connection = new ConnectionFactory().getConnection();
        NovoClass novoClass = new NovoClass();
        BoletoDao boletoDao = new BoletoDao(connection);
        List<ModeloBoleto> boletos = new ArrayList<ModeloBoleto>();
        boletos = listaBoleto;

        int sequencial = 1;
        for (ModeloBoleto e : boletos) {
            if ((e.getNumDocumento().indexOf("-") != -1)) { //Se for diferente de -1 é pq existe o caracter.  
                
                executarLocacao(e);
                sequencial++;
                //novoClass.executar(e);

            } else {
                executar(e);
                sequencial++;
                //novoClass.executar(e);
            }

        }

        sequencial++;

        connection.close();

    }

    public FileWriter contruirRemessaSemEmail(List<ModeloBoleto> listaBoleto) throws IOException, SQLException {

        SimpleDateFormat sdf = new SimpleDateFormat("ddMMYY");
        Calendar horaAtual = Calendar.getInstance();
        String dataFormatada = sdf.format(horaAtual.getTime());

        FileWriter arq = new FileWriter("S:\\remessa\\" + dataFormatada + ".txt");
        PrintWriter gravarArq = new PrintWriter(arq);
        arq = Hender(arq);
        Connection connection = new ConnectionFactory().getConnection();
        NovoClass novoClass = new NovoClass();
        BoletoDao boletoDao = new BoletoDao(connection);
        List<ModeloBoleto> boletos = new ArrayList<ModeloBoleto>();
        boletos = listaBoleto;

        int sequencial = 1;
        for (ModeloBoleto e : boletos) {

            sequencial++;
            //novoClass.executar(e);
            dadosBoletos(arq, e, String.valueOf(sequencial));
            //boletoDao.alteraEmissão(e);

        }

        sequencial++;
        rodape(arq, String.valueOf(sequencial));
        arq.close();

        connection.close();
        return arq;

    }

}
