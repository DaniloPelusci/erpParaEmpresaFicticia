/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.produto;

import Controle.ModeloTabela;
import Modelo.Produto.ModeloTipoProduto;
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
public class TipoProdutoDAO {

    private Connection connection;

    public TipoProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloTipoProduto modeloTipoProduto) {
        String sql = "insert into tipoproduto (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoProduto.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloTipoProduto modeloTipoProduto) {

        if (modeloTipoProduto.getId() == null) {
            throw new IllegalStateException("Id da modeloTipoProduto naoo deve ser nula.");
        }

        String sql = "delete from tipoproduto where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloTipoProduto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloTipoProduto modeloTipoProduto) {
        String sql = "update tipoproduto set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoProduto.getNome());

            stmt.setLong(2, modeloTipoProduto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloTipoProduto> lista() {
        try {
            List<ModeloTipoProduto> bairros = new ArrayList<ModeloTipoProduto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipoproduto order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoProduto na lista
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
            //		.prepareStatement("select * from tipoproduto inner join cidade on tipoproduto.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipoproduto order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoProduto na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }

            rs.close();
            stmt.close();
            ModeloTabela tipoproduto = new ModeloTabela(dados, Colunas);
            return tipoproduto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTipoProduto buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloTipoProduto nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipoproduto where id = ?");
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
        String sql = "select id, nome from tipoproduto where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Nome"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoProduto na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela tipoproduto = new ModeloTabela(dados, Colunas);
            return tipoproduto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloTipoProduto populaObjeto(ResultSet rs) throws SQLException {
        ModeloTipoProduto modeloTipoProduto = new ModeloTipoProduto();

        modeloTipoProduto.setId(rs.getLong("id"));
        modeloTipoProduto.setNome(rs.getString("nome"));

        return modeloTipoProduto;
    }

}
