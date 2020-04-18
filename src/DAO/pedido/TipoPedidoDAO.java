/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.pedido;

import Controle.ModeloTabela;
import Modelo.Pedido.ModeloTipoPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dan-pelusci
 */
public class TipoPedidoDAO {

    private Connection connection;

    public TipoPedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloTipoPedido modeloTipoPedido) {
        String sql = "insert into tipopedido (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoPedido.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloTipoPedido modeloTipoPedido) {

        if (modeloTipoPedido.getId() == null) {
            throw new IllegalStateException("Id da modeloTipoPedido naoo deve ser nula.");
        }

        String sql = "delete from tipopedido where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloTipoPedido.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloTipoPedido modeloTipoPedido) {
        String sql = "update tipopedido set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoPedido.getNome());

            stmt.setLong(2, modeloTipoPedido.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloTipoPedido> lista() {
        try {
            List<ModeloTipoPedido> bairros = new ArrayList<ModeloTipoPedido>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipopedido order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                bairros.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return bairros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Construindo a tabela.
    public ModeloTabela listaTabela() {
        try {

            String[] Colunas = new String[]{"ID", "Nome"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from tipopedido inner join cidade on tipopedido.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipopedido order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }

            rs.close();
            stmt.close();
            ModeloTabela tipopedido = new ModeloTabela(dados, Colunas);
            return tipopedido;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTipoPedido buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloTipoPedido nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipopedido where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaObjeto(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela buscaLike(String nome) {
        nome = "%" + nome + "%";
        String sql = "select id, nome from tipopedido where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Nome"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }

            rs.close();
            stmt.close();
            ModeloTabela tipopedido = new ModeloTabela(dados, Colunas);
            return tipopedido;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloTipoPedido populaObjeto(ResultSet rs) throws SQLException {
        ModeloTipoPedido modeloTipoPedido = new ModeloTipoPedido();

        modeloTipoPedido.setId(rs.getLong("id"));
        modeloTipoPedido.setNome(rs.getString("nome"));

        return modeloTipoPedido;
    }

}
