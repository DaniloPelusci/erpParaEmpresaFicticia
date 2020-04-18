/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Locacao;

import Controle.ModeloTabela;
import DAO.ConnectionFactory;
import DAO.TipoAcaoDAO;
import DAO.cliente.ClienteDAO;
import DAO.vasilhame.VasilhameDAO;
import DAO.venda.ParcelaDAO;
import DAO.venda.VendaDAO;
import Modelo.Vasilhame.Modeloauxiliarvasilhame;
import Modelo.venda.ModeloParcela;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author usuario
 */
public class LogicaLocacao {

    public void locacaoAutomatica() throws SQLException, ParseException {
        Connection connection = ConnectionFactory.getConnection();
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        VendaDAO vendaDAO = new VendaDAO(connection);
        List<Modeloauxiliarvasilhame> emprestimo = new ArrayList<>();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ModeloParcela modeloParcela = new ModeloParcela();
        VasilhameDAO vasilhameDAO = new VasilhameDAO(connection);
        Modeloauxiliarvasilhame modeloauxiliarvasilhame = new Modeloauxiliarvasilhame();
        int cont = 0;
        emprestimo = vasilhameDAO.listaEmprestimo();
        TipoAcaoDAO tipoAcaoDAO = new TipoAcaoDAO(connection);
        String[] colunas = new String[]{"ID Cliente", "Nome", "Quantidade de Cilindro"};
        ArrayList dados = new ArrayList();
        int soma = 0;
        String dataLocacaoString;
        Calendar horaAtual;
        SimpleDateFormat mes = new SimpleDateFormat("MM");
        SimpleDateFormat dia = new SimpleDateFormat("dd");
        SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy");
        String dataTela;
        BigDecimal valorParcela;
        double totalParcela;
        //String valorParcela;
        for (Modeloauxiliarvasilhame dev : emprestimo) {

            int locacaoCilindro = tipoAcaoDAO.somaCilindroLocacaoCliente(Integer.parseInt(dev.getIdCliente().toString()));
            int devolucaoCilindro = tipoAcaoDAO.somaCilindroDevolucaoCliente(Integer.parseInt(dev.getIdCliente().toString())) + tipoAcaoDAO.somaCilindroVendaCliente(Integer.parseInt(dev.getIdCliente().toString()));
            int resultCilindros = locacaoCilindro - devolucaoCilindro;
            horaAtual = Calendar.getInstance();

            if (resultCilindros > 0) {

                if (parcelaDAO.buscaPorId(clienteDAO.ultimaDataLocacao(dev.getIdCliente())) == null) {

                    if (!clienteDAO.verificaVenda(dev.getIdCliente(), Integer.parseInt(dia.format(horaAtual.getTime())))) {
                        if (clienteDAO.valorultimaLocacao(dev.getIdCliente()) == null) {
//                            String valorLocacao = JOptionPane.showInputDialog("Informe o preço unitario da locaçao do cliente:" + dev.getIdCliente());
//                            while (!logica.LogicasPedido.isInt(valorLocacao) && valorLocacao != null) {
//                                valorLocacao = JOptionPane.showInputDialog("Informe o preço unitario da locaçao do cliente:" + dev.getIdCliente());
//                                //gerar locacao
//                                System.err.println(valorLocacao);
//                            }
                        } else {
                            //gerar locaçao
                        }
                    }
                } else {
                    modeloParcela = parcelaDAO.buscaPorId(clienteDAO.ultimaDataLocacao(dev.getIdCliente()));

                    if (mes.format(modeloParcela.getDataVencimento()) != mes.format(horaAtual.getTime())) {

                        dataLocacaoString = dia.format(modeloParcela.getDataVencimento().getTime());
                        dataTela = dataformat.format(horaAtual.getTime());

                        dataTela = dataTela.substring(2);
                        dataTela = dataLocacaoString + dataTela;
                        System.err.println(dev.getIdCliente());
                        System.err.println(resultCilindros);

                        totalParcela = Integer.parseInt(String.valueOf(modeloParcela.getValor())) ;
                        valorParcela = new BigDecimal(totalParcela);
                        
                        modeloParcela.setValor(valorParcela);

                        modeloParcela.setDataVencimento(new Date(dataformat.parse(dataTela).getTime()));

                        //System.err.println(modeloParcela.getValor());
                    }
                }

            }
        }
        ModeloTabela modelo = new ModeloTabela(dados, colunas);
        System.err.println(cont);
        connection.close();

    }

}
