/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.produto;

import Controle.ModeloTabela;
import DAO.CidadeDAO;
import Modelo.Produto.ModeloModeloProduto;
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
public class ModeloProdutoDAO {

    private Connection connection;

    public ModeloProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloModeloProduto modeloProduto) {
        String sql = "insert into modelo (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloProduto.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloModeloProduto modeloProduto) {

        if (modeloProduto.getId() == null) {
            throw new IllegalStateException("Id da modeloProduto naoo deve ser nula.");
        }

        String sql = "delete from modelo where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloProduto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloModeloProduto modeloProduto) {
        String sql = "update modelo set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloProduto.getNome());

            stmt.setLong(2, modeloProduto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloModeloProduto> lista() {
        try {
            List<ModeloModeloProduto> bairros = new ArrayList<ModeloModeloProduto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from modelo order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloProduto na lista
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
            //		.prepareStatement("select * from modelo inner join cidade on modelo.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from modelo order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloProduto na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloModeloProduto buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloProduto nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from modelo where id = ?");
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
        String sql = "select id, nome from modelo where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Nome"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloProduto na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloModeloProduto populaObjeto(ResultSet rs) throws SQLException {
        ModeloModeloProduto modeloProduto = new ModeloModeloProduto();

        modeloProduto.setId(rs.getLong("id"));
        modeloProduto.setNome(rs.getString("nome"));

        return modeloProduto;
    }

}
