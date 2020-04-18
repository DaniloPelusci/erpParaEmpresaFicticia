/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.venda;

import Controle.ModeloTabela;
import Modelo.venda.ModeloTipoPagamento;
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
public class TipoPagamentoDAO {

    private Connection connection;

    public TipoPagamentoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloTipoPagamento modeloTipoPagamento) {
        String sql = "insert into tipopagamento (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoPagamento.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloTipoPagamento modeloTipoPagamento) {

        if (modeloTipoPagamento.getId() == null) {
            throw new IllegalStateException("Id da modeloTipoPagamento naoo deve ser nula.");
        }

        String sql = "delete from tipopagamento where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloTipoPagamento.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloTipoPagamento modeloTipoPagamento) {
        String sql = "update tipopagamento set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoPagamento.getNome());

            stmt.setLong(2, modeloTipoPagamento.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloTipoPagamento> lista() {
        try {
            List<ModeloTipoPagamento> tipoPagamentos = new ArrayList<ModeloTipoPagamento>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipopagamento order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                tipoPagamentos.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return tipoPagamentos;
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
                    .prepareStatement("select * from tipopagamento order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }

            rs.close();
            stmt.close();
            ModeloTabela tipoPagamento = new ModeloTabela(dados, Colunas);
            return tipoPagamento;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTipoPagamento buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloTipoPedido nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipopagamento where id = ?");
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
        String sql = "select id, nome from tipopagamento where nome like ? order by id;";
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

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"),});
            }

            rs.close();
            stmt.close();
            ModeloTabela tipoPagamento = new ModeloTabela(dados, Colunas);
            return tipoPagamento;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloTipoPagamento populaObjeto(ResultSet rs) throws SQLException {
        ModeloTipoPagamento modeloTipoPagamento = new ModeloTipoPagamento();

        modeloTipoPagamento.setId(rs.getLong("id"));
        modeloTipoPagamento.setNome(rs.getString("nome"));

        return modeloTipoPagamento;
    }

}
