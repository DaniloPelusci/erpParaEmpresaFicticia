/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cliente;

import Controle.ModeloTabela;
import DAO.CidadeDAO;
import Modelo.Cliente.ModeloRamoAtividade;

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
public class RamoAtividadeDAO {

    private Connection connection;

    public RamoAtividadeDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloRamoAtividade modeloRamoAtividade) {
        String sql = "insert into ramoatividade (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloRamoAtividade.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloRamoAtividade modeloRamoAtividade) {

        if (modeloRamoAtividade.getId()== null) {
            throw new IllegalStateException("Id da modeloRamoAtividade naoo deve ser nula.");
        }

        String sql = "delete from ramoatividade where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloRamoAtividade.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloRamoAtividade modeloRamoAtividade) {
        String sql = "update ramoatividade set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloRamoAtividade.getNome());
            

            stmt.setLong(2, modeloRamoAtividade.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloRamoAtividade> lista() {
        try {
            List<ModeloRamoAtividade> bairros = new ArrayList<ModeloRamoAtividade>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from ramoatividade order by nome");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloRamoAtividade na lista
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
            //		.prepareStatement("select * from ramoatividade inner join cidade on ramoatividade.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from ramoatividade order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloRamoAtividade na lista

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
                    .prepareStatement("select * from ramoatividade order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloRamoAtividade buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloRamoAtividade nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from ramoatividade where id = ?");
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

    public List<ModeloRamoAtividade> buscaIdPorCidade(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            List<ModeloRamoAtividade> bairros = new ArrayList<ModeloRamoAtividade>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, nome from ramoatividade where c.nome = ? ");
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
        String sql = "select id, nome from ramoatividade where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Ramoatividade"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloRamoAtividade na lista

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

    private ModeloRamoAtividade populaObjeto(ResultSet rs) throws SQLException {
        ModeloRamoAtividade modeloRamoAtividade = new ModeloRamoAtividade();

        modeloRamoAtividade.setId(rs.getLong("id"));
        modeloRamoAtividade.setNome(rs.getString("nome"));

        return modeloRamoAtividade;
    }

}
