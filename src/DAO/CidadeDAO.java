/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controle.ModeloTabela;
import Modelo.ModeloCidades;
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
public class CidadeDAO {

    private Connection connection;

    public CidadeDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloCidades modeloCidades) {
        String sql = "insert into cidade (nome, idestado) values (?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloCidades.getNome());
            stmt.setLong(2, modeloCidades.getCod_estado());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloCidades modeloCidades) {

        if (modeloCidades.getCodig() == null) {
            throw new IllegalStateException("Id da modeloCidades naoo deve ser nula.");
        }

        String sql = "delete from cidade where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloCidades.getCodig());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloCidades modeloCidades) {
        String sql = "update cidade set nome = ?, idestado = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloCidades.getNome());
            stmt.setLong(2, modeloCidades.getCod_estado());

            stmt.setLong(3, modeloCidades.getCodig());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloCidades> lista() {
        try {
            List<ModeloCidades> contas = new ArrayList<ModeloCidades>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idestado, trim(nome) as nome from cidade order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCidades na lista
                contas.add(populaConta(rs));
            }

            rs.close();
            stmt.close();

            return contas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela listaTabela() {
        try {

            String[] Colunas = new String[]{"ID", "Cidade", "Estado"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from bairro inner join cidade on bairro.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select c.id, c.nome, e.nome from cidade as c inner join estado as e on c.idestado = e.id order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet listaRS() {
        try (PreparedStatement stmt = this.connection
                .prepareStatement("select * from cidade order by id")) {

            ResultSet rs = stmt.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ModeloCidades buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cidade where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaConta(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloCidades buscaIdPorNome(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cidade where nome = ?");
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaConta(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloCidades> buscaIdPorEstado(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            List<ModeloCidades> cidades = new ArrayList<ModeloCidades>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select c.id, c.nome, c.idestado  from cidade as c inner join estado as e on c.idestado = e.id where e.id = ? order by c.nome ");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCidades na lista
                cidades.add(populaConta(rs));
            }

            rs.close();
            stmt.close();

            return cidades;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ModeloTabela buscaLike(String nome) {
        
        nome = "%"+nome+"%";
        
        String sql = "select c.id, c.nome, e.nome from cidade as c inner join estado as e on c.idestado = e.id where c.nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Cidade", "Estado"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloCidades populaConta(ResultSet rs) throws SQLException {
        ModeloCidades modeloCidades = new ModeloCidades();

        modeloCidades.setCodig(rs.getLong("id"));
        modeloCidades.setNome(rs.getString("nome"));
        modeloCidades.setCod_estado(rs.getLong("idestado"));

        return modeloCidades;
    }
    
    public boolean varificaDuplicidadeAdicionar(String cidade, Long idEstado) throws SQLException {

       
        //
        String sql = "select * from cidade as cd where cd.nome = ? and cd.idestado =? ;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, cidade);
        stmt.setLong(2, idEstado);

        stmt.execute();

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // adiciona a modeloBairro na lista
            retorno = true;

        }

        rs.close();
        stmt.close();

        return retorno;

    }

    public boolean varificaDuplicidadeAlterar(String cidade, Long idEstado, Long idCidade) throws SQLException {
        
    
        String sql = "select * from cidade as cd where cd.nome = ? and cd.idestado =? and cd.id !=? ;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, cidade);
        stmt.setLong(2, idEstado);
        stmt.setLong(3, idCidade);

        stmt.execute();

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // adiciona a modeloBairro na lista
            retorno = true;

        }

        rs.close();
        stmt.close();

        return retorno;
    }


}
