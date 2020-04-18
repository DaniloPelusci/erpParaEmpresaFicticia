/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.haver;

import Controle.ModeloTabela;
import Modelo.haver.ModeloHaver;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import logica.LogicasPedido;

/**
 *
 * @author E.B.O 2
 */
public class HaverDAO {

    private Connection connection;

    public HaverDAO(Connection connection) {
        this.connection = connection;
    }

    public ModeloHaver busca(Long idhaver) throws SQLException {
        PreparedStatement stmt = this.connection
                .prepareStatement("select haver.id,haver.idcliente, haver.dataemissao, haver.dataquitado, haver.valor ,haver.valorretirado, trim(haver.observacao) as observacao "
                        + "from haver as haver "
                        + "inner join cliente as cli on haver.idcliente = cli.id "
                        + "where haver.id = ?;");
        stmt.setLong(1, idhaver);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return populaObjeto(rs);
        }
        rs.close();
        stmt.close();
        return null;

    }

    private ModeloHaver populaObjeto(ResultSet rs) throws SQLException {
        ModeloHaver modeloHaver = new ModeloHaver();

        modeloHaver.setId(rs.getLong("id"));
        modeloHaver.setIdCliente(rs.getLong("idcliente"));
        modeloHaver.setValorHaver(rs.getBigDecimal("valor"));
        modeloHaver.setObservacao(rs.getString("observacao"));
        modeloHaver.setValorRetirado(rs.getBigDecimal("valorretirado"));
        modeloHaver.setDataQuitado(rs.getDate("dataquitado"));
        modeloHaver.setDataEmissao(rs.getDate("dataemissao"));

        return modeloHaver;
    }

    public TableModel listaValePorCliente(Long idCliente) {
        try {

            String[] Colunas = new String[]{"ID Haver", "Data de Emissao", "Valor Haver", "Data Quitado", "Valor Retirado", "observacao"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select haver.id, haver.dataemissao,  haver.valor ,haver.dataquitado, haver.valorretirado, trim(haver.observacao) as observacao from haver as haver inner join cliente as cli on haver.idcliente = cli.id where cli.id = ?;");
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), format.format(rs.getDate(2).getTime()), rs.getString(3), rs.getString(4), rs.getBigDecimal(5), rs.getString(6)});
            }

            rs.close();
            stmt.close();
            ModeloTabela vales = new ModeloTabela(dados, Colunas);
            return vales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public double totalHaver(Long idCliente) throws SQLException {

        String[] Colunas = new String[]{"ID Haver", "Data de Emissao", "Valor Haver", "Data Quitado", "Valor Retirado", "observacao"};
        double haver = 0;
        double valorRetirado = 0;
        double total = 0;

        PreparedStatement stmt = this.connection
                .prepareStatement("select haver.valor, haver.valorretirado from haver as haver inner join cliente as cli on haver.idcliente = cli.id where cli.id = ?;");
        stmt.setLong(1, idCliente);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // adiciona a modeloPedido na lista
            valorRetirado = valorRetirado + rs.getDouble(2);
            haver = haver + rs.getDouble(1);
        }

        total = haver - valorRetirado;
        rs.close();
        stmt.close();

        return total;

    }

    public void adiciona(ModeloHaver modeloHaver) {
        String sql = "insert into haver (idcliente, valor, valorretirado, dataquitado,dataemissao, observacao) values (?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloHaver.getIdCliente());
            stmt.setBigDecimal(2, modeloHaver.getValorHaver());
            stmt.setBigDecimal(3, modeloHaver.getValorRetirado());
            stmt.setDate(4, modeloHaver.getDataQuitado());
            stmt.setDate(5, modeloHaver.getDataEmissao());
            stmt.setString(6, modeloHaver.getObservacao());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloHaver modeloHaver) {
        if (modeloHaver.getId() == null) {
            throw new IllegalStateException("Id da vale naoo deve ser nula.");
        }

        String sql = "delete from haver where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloHaver.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloHaver modeloHaver) {
        String sql = "update haver set idCLiente=? ,valorretirado=? ,valor=? ,dataemissao=? ,dataquitado=?, observacao=?  where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloHaver.getIdCliente());
            stmt.setBigDecimal(2, modeloHaver.getValorRetirado());
            stmt.setBigDecimal(3, modeloHaver.getValorHaver());
            stmt.setDate(4, modeloHaver.getDataEmissao());
            stmt.setDate(5, modeloHaver.getDataQuitado());
            stmt.setString(6, modeloHaver.getObservacao());
            stmt.setLong(7, modeloHaver.getId());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TableModel listaPagamentos(long idCliente) {
        try {

            String[] Colunas = new String[]{"Data Paga", "Valor"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT haver.dataquitado, sum(haver.valor) as valor \n"
                            + "FROM haver \n"
                            + "where haver.idcliente = ? \n"
                            + "group by  haver.dataquitado \n"
                            + "order by haver.dataquitado");
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                // adiciona a modeloPedido na lista
                if (LogicasPedido.validaDatabanco(rs.getString(1))) {
                    dados.add(new Object[]{format.format(rs.getDate(1).getTime()), rs.getDouble(2)});

                } else {
                    dados.add(new Object[]{rs.getString(1), rs.getDouble(2)});

                }
            }

            rs.close();
            stmt.close();
            ModeloTabela vales = new ModeloTabela(dados, Colunas);
            return vales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
