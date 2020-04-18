/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Vale;

import Controle.ModeloTabela;
import Modelo.Vale.ModeloVale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.TableModel;

/**
 *
 * @author E.B.O 2
 */
public class ValeDAO {

    private Connection connection;

    public ValeDAO(Connection connection) {
        this.connection = connection;
    }

    public ModeloVale busca(Long idVale) throws SQLException {
        PreparedStatement stmt = this.connection
                .prepareStatement("select vale.id, vale.dataemissao, vale.valor, vale.observacao,vale.idfuncionario from vale as vale "
                        + "inner join funcionario as func on vale.idfuncionario = func.id "
                        + "where vale.id = ?;");
        stmt.setLong(1, idVale);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return populaObjeto(rs);
        }
        rs.close();
        stmt.close();
        return null;

    }

    private ModeloVale populaObjeto(ResultSet rs) throws SQLException {
        ModeloVale modeloVale = new ModeloVale();

        modeloVale.setId(rs.getLong("id"));
        modeloVale.setIdFuncionario(rs.getLong("idfuncionario"));
        modeloVale.setObservacao(rs.getString("observacao"));
        modeloVale.setDataEmissao(rs.getDate("dataemissao"));
        modeloVale.setValor(rs.getBigDecimal("valor"));
        return modeloVale;
    }

    public TableModel listaValePorFuncionario(Long idFuncionario) {
        try {

            String[] Colunas = new String[]{"ID Vale", "Data de Emissao", "Valor do vale", "Observanção"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select vale.id, vale.dataemissao, vale.valor, vale.observacao from vale as vale inner join funcionario as func on vale.idfuncionario = func.id where func.id = ? order by vale.dataemissao;");
            stmt.setLong(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }

            rs.close();
            stmt.close();
            ModeloTabela vales = new ModeloTabela(dados, Colunas);
            return vales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int adiciona(ModeloVale modeloVale) {
        String sql = "insert into vale (dataemissao, idfuncionario, observacao, valor) values (?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, modeloVale.getDataEmissao());
            stmt.setLong(2, modeloVale.getIdFuncionario());
            stmt.setString(3, modeloVale.getObservacao());
            stmt.setBigDecimal(4, modeloVale.getValor());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            int idObjeto = 0;
            while (rs.next()) {

                idObjeto = rs.getInt(1);

            }
            return idObjeto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloVale modeloVale) {
        if (modeloVale.getId() == null) {
            throw new IllegalStateException("Id da vale naoo deve ser nula.");
        }

        String sql = "delete from vale where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloVale.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
