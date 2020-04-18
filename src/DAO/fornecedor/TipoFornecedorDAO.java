/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.fornecedor;

import Controle.ModeloTabela;
import DAO.CidadeDAO;
import Modelo.Fornecedor.ModeloTipoFornecedor;
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
public class TipoFornecedorDAO {

    private Connection connection;

    public TipoFornecedorDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloTipoFornecedor modeloTipoFornecedor) {
        String sql = "insert into tipofornecedor (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoFornecedor.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloTipoFornecedor modeloTipoFornecedor) {

        if (modeloTipoFornecedor.getId() == null) {
            throw new IllegalStateException("Id da modeloTipoFornecedor naoo deve ser nula.");
        }

        String sql = "delete from tipofornecedor where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloTipoFornecedor.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloTipoFornecedor modeloTipoFornecedor) {
        String sql = "update tipofornecedor set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoFornecedor.getNome());

            stmt.setLong(2, modeloTipoFornecedor.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloTipoFornecedor> lista() {
        try {
            List<ModeloTipoFornecedor> tipoFornecedores = new ArrayList<ModeloTipoFornecedor>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipofornecedor order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoFornecedor na lista
                tipoFornecedores.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return tipoFornecedores;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Construindo a tabela.
    public ModeloTabela listaTabela() {
        try {

            CidadeDAO cidadeDAO = new CidadeDAO(connection);

            String[] Colunas = new String[]{"ID", "Ramoatividade"};
            ArrayList dados = new ArrayList();

            
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipofornecedor order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                
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

    public ResultSet listaAnterior() {
        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipofornecedor order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTipoFornecedor buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloTipoFornecedor nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipofornecedor where id = ?");
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

    public List<ModeloTipoFornecedor> buscaIdPorCidade(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            List<ModeloTipoFornecedor> bairros = new ArrayList<ModeloTipoFornecedor>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, nome from tipofornecedor where c.nome = ? ");
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCidades na lista
                bairros.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return bairros;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela buscaLike(String nome) {
        nome = "%" + nome + "%";
        String sql = "select id, nome from tipofornecedor where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Ramoatividade"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoFornecedor na lista

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

    private ModeloTipoFornecedor populaObjeto(ResultSet rs) throws SQLException {
        ModeloTipoFornecedor modeloTipoFornecedor = new ModeloTipoFornecedor();

        modeloTipoFornecedor.setId(rs.getLong("id"));
        modeloTipoFornecedor.setNome(rs.getString("nome"));

        return modeloTipoFornecedor;
    }

}
