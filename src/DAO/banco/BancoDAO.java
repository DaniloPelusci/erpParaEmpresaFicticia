/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.banco;

import Controle.ModeloTabela;
import DAO.CidadeDAO;
import Modelo.banco.ModeloBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SERVIDOR
 */
public class BancoDAO {
     private Connection connection;

    public BancoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloBanco modeloBanco) {
        String sql = "insert into banco (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloBanco.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloBanco modeloBanco) {

        if (modeloBanco.getId()== null) {
            throw new IllegalStateException("Id da modeloBanco naoo deve ser nula.");
        }

        String sql = "delete from banco where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloBanco.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloBanco modeloBanco) {
        String sql = "update banco set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloBanco.getNome());
            

            stmt.setLong(2, modeloBanco.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloBanco> lista() {
        try {
            List<ModeloBanco> bairros = new ArrayList<ModeloBanco>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from banco order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBanco na lista
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
                    .prepareStatement("select * from banco order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBanco na lista

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
                    .prepareStatement("select * from banco order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloBanco buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloBanco nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from banco where id = ?");
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

    public List<ModeloBanco> buscaIdPorCidade(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            List<ModeloBanco> bairros = new ArrayList<ModeloBanco>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, nome from banco where c.nome = ? ");
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
        String sql = "select id, nome from banco where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Ramoatividade"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBanco na lista

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

    private ModeloBanco populaObjeto(ResultSet rs) throws SQLException {
        ModeloBanco modeloBanco = new ModeloBanco();

        modeloBanco.setId(rs.getLong("id"));
        modeloBanco.setNome(rs.getString("nome"));

        return modeloBanco;
    }
    
}
