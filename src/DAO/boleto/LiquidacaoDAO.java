/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.boleto;

import Controle.ModeloTabela;
import Modelo.boleto.ModeloLiquidacaoBoleto;
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
public class LiquidacaoDAO {
     private Connection connection;

    public LiquidacaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloLiquidacaoBoleto modeloLiquidacaoBoleto) {
        String sql = "insert into liquidacao (nome, liquidacao) values (?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloLiquidacaoBoleto.getNome());
            stmt.setString(2, modeloLiquidacaoBoleto.getLiquidacao());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloLiquidacaoBoleto modeloLiquidacaoBoleto) {

        if (modeloLiquidacaoBoleto.getId() == null) {
            throw new IllegalStateException("Id da modeloEstado naoo deve ser nula.");
        }

        String sql = "delete from liquidacao where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloLiquidacaoBoleto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloLiquidacaoBoleto modeloLiquidacaoBoleto) {
        String sql = "update liquidacao set nome = ?, liquidacao = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloLiquidacaoBoleto.getNome());
            stmt.setString(2, modeloLiquidacaoBoleto.getLiquidacao());

            stmt.setLong(3, modeloLiquidacaoBoleto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloLiquidacaoBoleto> lista() {
        try {
            List<ModeloLiquidacaoBoleto> estado = new ArrayList<ModeloLiquidacaoBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, trim(nome) as nome, liquidacao from liquidacao order by nome");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloEstado na lista
                estado.add(populaEstado(rs));
            }

            rs.close();
            stmt.close();

            return estado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Construindo a tabela.
    public ModeloTabela listaTabela() {
        try {

            

            String[] Colunas = new String[]{"ID", "Nome", "Liquidação"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from estado inner join cidade on estado.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from liquidacao order by id ");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloEstado na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString("liquidacao")});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet listaResult() {
        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from liquidacao order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloLiquidacaoBoleto buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloEstado nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id,trim(nomeliquidacao) as nomeliquidacao, trim(liquidacao) as liquidacao from liquidacao where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaEstado(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     public ModeloLiquidacaoBoleto buscaPorliquidacao(String liquidacao) {

        

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from liquidacao where liquidacao = ?;");
            stmt.setString(1,liquidacao);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaEstado(rs);
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
        //
        String sql = "select id, nome, liquidacao from liquidacao where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Liquidacao"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloLiquidacaoBoleto populaEstado(ResultSet rs) throws SQLException {
        ModeloLiquidacaoBoleto modeloLiquidacaoBoleto = new ModeloLiquidacaoBoleto();

        modeloLiquidacaoBoleto.setId(rs.getLong("id"));
       modeloLiquidacaoBoleto.setNome(rs.getString("nomeliquidacao"));
        modeloLiquidacaoBoleto.setLiquidacao("liquidacao");

        return modeloLiquidacaoBoleto;
    }
}
