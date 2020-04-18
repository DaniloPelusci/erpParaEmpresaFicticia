/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controle.ModeloTabela;
import Modelo.ModeloEstado;
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
public class EstadoDAO {

    private Connection connection;

    public EstadoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloEstado modeloEstado) {
        String sql = "insert into estado (nome, sigla) values (?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloEstado.getNome());
            stmt.setString(2, modeloEstado.getSigla());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloEstado modeloEstado) {

        if (modeloEstado.getId() == null) {
            throw new IllegalStateException("Id da modeloEstado naoo deve ser nula.");
        }

        String sql = "delete from estado where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloEstado.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloEstado modeloEstado) {
        String sql = "update estado set nome = ?, sigla = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloEstado.getNome());
            stmt.setString(2, modeloEstado.getSigla());

            stmt.setLong(3, modeloEstado.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloEstado> lista() {
        try {
            List<ModeloEstado> estado = new ArrayList<ModeloEstado>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, trim(nome) as nome, sigla from estado order by nome");

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

            CidadeDAO cidadeDAO = new CidadeDAO(connection);

            String[] Colunas = new String[]{"ID", "Bairro", "Sigla"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from estado inner join cidade on estado.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from estado order by id ");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloEstado na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString("sigla")});
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
                    .prepareStatement("select * from estado order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloEstado buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloEstado nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from estado where id = ?");
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

    public ModeloTabela buscaLike(String nome) {
        nome = "%" + nome + "%";
        //
        String sql = "select id, nome, sigla from estado where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Sigla"};
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

    private ModeloEstado populaEstado(ResultSet rs) throws SQLException {
        ModeloEstado modeloEstado = new ModeloEstado();

        modeloEstado.setId(rs.getLong("id"));
        modeloEstado.setNome(rs.getString("nome"));
        modeloEstado.setSigla(rs.getString("sigla"));

        return modeloEstado;
    }

}
