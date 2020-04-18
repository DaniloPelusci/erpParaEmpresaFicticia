/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controle.ModeloTabela;
import Modelo.ModeloBairro;
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
public class BairroDAO {

    private Connection connection;

    public BairroDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloBairro modeloBairro) {
        String sql = "insert into bairro (nome, idcidade) values (?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloBairro.getNome());
            stmt.setLong(2, modeloBairro.getCidade());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloBairro modeloBairro) {

        if (modeloBairro.getCodBairro() == null) {
            throw new IllegalStateException("Id da modeloBairro naoo deve ser nula.");
        }

        String sql = "delete from bairro where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloBairro.getCodBairro());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloBairro modeloBairro) {
        String sql = "update bairro set nome = ?, idcidade = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloBairro.getNome());
            stmt.setLong(2, modeloBairro.getCidade());

            stmt.setLong(3, modeloBairro.getCodBairro());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloBairro> lista() {
        try {
            List<ModeloBairro> bairros = new ArrayList<ModeloBairro>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from bairro order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista
                bairros.add(populaBairro(rs));
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

            String[] Colunas = new String[]{"ID", "Bairro", "Cidade"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from bairro inner join cidade on bairro.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select b.id, b.nome, c.nome from bairro as b inner join cidade as c on b.idcidade = c.id order by id;");

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

    public ResultSet listaAnterior() {
        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from bairro order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloBairro buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloBairro nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id,idcidade, trim(nome) as nome  from bairro where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaBairro(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloBairro> buscaIdPorCidade(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            List<ModeloBairro> bairros = new ArrayList<ModeloBairro>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select b.id, b.nome, b.idcidade  from bairro as b inner join cidade as c on b.idcidade = c.id where c.id = ? ORDER BY b.nome ASC");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCidades na lista
                bairros.add(populaBairro(rs));
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
        String sql = "select b.id, b.nome, c.nome from bairro as b inner join cidade as c on b.idcidade = c.id where b.nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Bairro", "Cidade"};
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

    private ModeloBairro populaBairro(ResultSet rs) throws SQLException {
        ModeloBairro modeloBairro = new ModeloBairro();

        modeloBairro.setCodBairro(rs.getLong("id"));
        modeloBairro.setNome(rs.getString("nome"));
        modeloBairro.setCidade(rs.getLong("idcidade"));

        return modeloBairro;
    }

    public boolean varificaDuplicidadeAdicionar(String bairro, Long idcidade) throws SQLException {

       
        //
        String sql = "select * from bairro as br where BR.nome = ? and br.idcidade =? ;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, bairro);
        stmt.setLong(2, idcidade);

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

    public boolean varificaDuplicidadeAlterar(String bairro, Long idcidade, Long idbairro) throws SQLException {
        
    
        String sql = "select * from bairro as br where BR.nome = ? and br.idcidade =? and br.id !=? ;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, bairro);
        stmt.setLong(2, idcidade);
        stmt.setLong(3, idbairro);

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
