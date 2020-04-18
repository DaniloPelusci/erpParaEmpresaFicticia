/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cliente;

import Controle.ModeloTabela;
import Modelo.Cliente.ModeloRamoAtividade;
import Modelo.Cliente.ModeloRota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author E.B.O 2
 */
public class RotasDAO {

    private final Connection connection;

    public RotasDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloRota modeloRota) {
        String sql = "insert into rota (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloRota.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void altera(ModeloRota modeloRota) {
        String sql = "update rota set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloRota.getNome());

            stmt.setLong(2, modeloRota.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela buscaLike(String nome) {
        nome = "%" + nome + "%";
        String sql = "select id, nome from rota where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Rota"};
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

    public void remove(ModeloRota modeloRota) {

        if (modeloRota.getId() == null) {
            throw new IllegalStateException("Id da rota naoo deve ser nula.");
        }

        String sql = "delete from rota where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloRota.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<ModeloRota> lista() {
        try {
            List<ModeloRota> rotas = new ArrayList<ModeloRota>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from rota order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloRamoAtividade na lista
                rotas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return rotas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private ModeloRota populaObjeto(ResultSet rs) throws SQLException {
        ModeloRota modeloRota = new ModeloRota();

        modeloRota.setId(rs.getLong("id"));
        modeloRota.setNome(rs.getString("nome"));

        return modeloRota;
    }

    public ModeloRota buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloRamoAtividade nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from rota where id = ?");
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

}
