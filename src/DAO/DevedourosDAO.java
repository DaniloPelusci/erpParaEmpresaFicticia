/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.ModeloDevedor;
import Modelo.venda.ModeloParcela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class DevedourosDAO {

    private Connection connection;

    public DevedourosDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ModeloDevedor> lista() {
        try {
            List<ModeloDevedor> contas = new ArrayList<ModeloDevedor>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from devedores where devendo = true  order by idCliente");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCliente na lista
                contas.add(populaModelo(rs));
            }

            rs.close();
            stmt.close();

            return contas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificacaoDevedores(Long idCliente) {
        try {
            boolean resultBoolean = false;

            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT idcliente FROM devedores  where idcliente = ?");

            stmt.setLong(1, idCliente);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                return true;
            }

            rs.close();
            stmt.close();

            return resultBoolean;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void altera(ModeloDevedor modeloDevedor) {

        String sql = "UPDATE devedores SET  valor=?, devendo=? WHERE idcliente=?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setFloat(1, modeloDevedor.getValor());
            stmt.setBoolean(2, modeloDevedor.isDevendo());
            stmt.setLong(3, modeloDevedor.getIdCliente());

            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void adcionar(ModeloDevedor modeloDevedor) {
        String sql = "INSERT INTO devedores( idcliente, valor, devendo) VALUES ( ?, ?, ?);";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloDevedor.getIdCliente());
            stmt.setFloat(2, modeloDevedor.getValor());
            stmt.setBoolean(3, modeloDevedor.isDevendo());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private ModeloDevedor populaModelo(ResultSet rs) throws SQLException {
        ModeloDevedor modeloDevedor = new ModeloDevedor();
        modeloDevedor.setId(rs.getLong("id"));
        modeloDevedor.setIdCliente(rs.getLong("idCliente"));
        modeloDevedor.setDevendo(rs.getBoolean("devendo"));
        modeloDevedor.setValor(rs.getFloat("valor"));
        return modeloDevedor;

    }

    private ModeloDevedor populaModeloPClientes(ResultSet rs) throws SQLException {
        ModeloDevedor modeloDevedor = new ModeloDevedor();

        modeloDevedor.setIdCliente(rs.getLong("id"));
        return modeloDevedor;

    }

}
