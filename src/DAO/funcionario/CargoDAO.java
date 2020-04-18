/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.funcionario;

import Controle.ModeloTabela;
import DAO.CidadeDAO;
import Modelo.Funcionario.ModeloCargo;
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
public class CargoDAO {
     private Connection connection;

    public CargoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloCargo modeloCargo) {
        String sql = "insert into cargo (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloCargo.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloCargo modeloCargo) {

        if (modeloCargo.getId() == null) {
            throw new IllegalStateException("Id da modeloCargo naoo deve ser nula.");
        }

        String sql = "delete from cargo where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloCargo.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloCargo modeloCargo) {
        String sql = "update cargo set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloCargo.getNome());

            stmt.setLong(2, modeloCargo.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloCargo> lista() {
        try {
            List<ModeloCargo> bairros = new ArrayList<ModeloCargo>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cargo order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCargo na lista
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

            CidadeDAO cidadeDAO = new CidadeDAO(connection);

            String[] Colunas = new String[]{"ID", "Ramoatividade"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from cargo inner join cidade on cargo.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cargo order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCargo na lista

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
                    .prepareStatement("select * from cargo order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloCargo buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloCargo nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cargo where id = ?");
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

    public List<ModeloCargo> buscaIdPorCidade(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            List<ModeloCargo> bairros = new ArrayList<ModeloCargo>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, nome from cargo where c.nome = ? ");
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
        String sql = "select id, nome from cargo where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Cargo"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCargo na lista

                dados.add(new Object[]{rs.getString(1), rs.getString(2)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloCargo populaObjeto(ResultSet rs) throws SQLException {
        ModeloCargo modeloCargo = new ModeloCargo();

        modeloCargo.setId(rs.getLong("id"));
        modeloCargo.setNome(rs.getString("nome"));

        return modeloCargo;
    }
    
}
