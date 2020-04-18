/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.produto;

import Controle.ModeloTabela;
import Modelo.Produto.ModeloNomenclatura;
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
public class NomenclaturaDAO {

    private Connection connection;

    public NomenclaturaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloNomenclatura modeloNomenclatura) {
        String sql = "insert into nomenclatura (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloNomenclatura.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloNomenclatura modeloNomenclatura) {

        if (modeloNomenclatura.getId() == null) {
            throw new IllegalStateException("Id da modeloNomenclatura naoo deve ser nula.");
        }

        String sql = "delete from nomenclatura where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloNomenclatura.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloNomenclatura modeloNomenclatura) {
        String sql = "update nomenclatura set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloNomenclatura.getNome());

            stmt.setLong(2, modeloNomenclatura.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloNomenclatura> lista() {
        try {
            List<ModeloNomenclatura> bairros = new ArrayList<ModeloNomenclatura>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from nomenclatura order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloNomenclatura na lista
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
            //		.prepareStatement("select * from nomenclatura inner join cidade on nomenclatura.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from nomenclatura order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloNomenclatura na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }

            rs.close();
            stmt.close();
            ModeloTabela nomenclatura = new ModeloTabela(dados, Colunas);
            return nomenclatura;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloNomenclatura buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloNomenclatura nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, trim(nome) as nome from nomenclatura where id = ?");
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
        String sql = "select id, nome from nomenclatura where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Nome"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloNomenclatura na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }

            rs.close();
            stmt.close();
            ModeloTabela nomenclatura = new ModeloTabela(dados, Colunas);
            return nomenclatura;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloNomenclatura populaObjeto(ResultSet rs) throws SQLException {
        ModeloNomenclatura modeloNomenclatura = new ModeloNomenclatura();

        modeloNomenclatura.setId(rs.getLong("id"));
        modeloNomenclatura.setNome(rs.getString("nome"));

        return modeloNomenclatura;
    }

}

