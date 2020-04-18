/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.produto;

import Controle.ModeloTabela;
import Modelo.Produto.ModeloProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author dan-pelusci
 */
public class ProdutoDAO {

    private Connection connection;

    public ProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloProduto modeloProduto) {
        String sql = "insert into produto (idmodelo,idtipoproduto,idnomenclatura,nome) values (?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloProduto.getModelo());
            stmt.setLong(2, modeloProduto.getTipo());
            stmt.setLong(3, modeloProduto.getNomenclatura());
            stmt.setString(4, modeloProduto.getNome());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloProduto modeloProduto) {

        if (modeloProduto.getId() == null) {
            throw new IllegalStateException("Id da modeloproduto naoo deve ser nula.");
        }

        String sql = "delete from produto where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloProduto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloProduto modeloProduto) {

        String sql = "update produto set idtipoproduto = ?, idmodelo = ?,idnomenclatura=?,nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloProduto.getTipo());
            stmt.setLong(2, modeloProduto.getModelo());
            stmt.setLong(3, modeloProduto.getNomenclatura());

            stmt.setString(4, modeloProduto.getNome());

            stmt.setLong(5, modeloProduto.getId());
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloProduto> lista() {
        try {
            List<ModeloProduto> produtos = new ArrayList<ModeloProduto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from produto order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloFornecedor na lista
                produtos.add(populaModelo(rs));

            }

            rs.close();
            stmt.close();

            return produtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela listaTabela() {
        try {
            //tiponome,modelo,nomenclatura,capacidade
            String[] colunas = new String[]{"ID", "Nome", "Capacidade", "Tipo", "Modelo"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select p.id,trim(p.nome),n.nome nome,t.nome,m.nome from produto as p inner join tipoproduto as t on p.idtipoproduto = t.id inner join modelo as m on p.idmodelo = m.id inner join nomenclatura as n on p.idnomenclatura = n.id order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloProduto buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloProduto nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idmodelo, idtipoproduto, idnomenclatura, trim(nome) as nome from produto where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaModelo(rs);
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

        String sql = "select p.id,p.nome,n.nome,t.nome,m.nome from produto as p inner join tipoproduto as t on p.idtipoproduto = t.id inner join modelo as m on p.idmodelo = m.id inner join nomenclatura as n on p.idnomenclatura = n.id where p.nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Capacidade", "Tipo", "Modelo"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloProduto populaModelo(ResultSet rs) throws SQLException {
        ModeloProduto modeloProduto = new ModeloProduto();
        modeloProduto.setId(rs.getLong("id"));
        modeloProduto.setModelo(rs.getLong("idmodelo"));
        modeloProduto.setTipo(rs.getLong("idtipoproduto"));
        modeloProduto.setNomenclatura(rs.getLong("idnomenclatura"));
        modeloProduto.setNome(rs.getString("nome"));

        return modeloProduto;
    }

    public TableModel buscaLikeVenda(String nome) {
        nome = "%" + nome + "%";

        String sql = "select t.nome,p.nome,n.nome,m.nome,p.id from produto as p inner join tipoproduto as t on p.idtipoproduto = t.id inner join modelo as m on p.idmodelo = m.id inner join nomenclatura as n on p.idnomenclatura = n.id where p.nome like ?  ;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] colunas = new String[]{"Tipo", "Nome", "Capacidade", "Modelo", "ID"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TableModel buscaLikeLastro(String nome) {

        nome = "%" + nome + "%";

        String sql = "select t.nome,p.nome,n.nome,m.nome,p.id from produto as p inner join tipoproduto as t on p.idtipoproduto = t.id inner join modelo as m on p.idmodelo = m.id inner join nomenclatura as n on p.idnomenclatura = n.id where p.nome like ? and p.idtipoProduto = '2' or p.idtipoProduto = '3' or p.idtipoProduto = '4' order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] colunas = new String[]{"Tipo", "Nome", "Capacidade", "Modelo", "ID"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean verificacao(Long idPproduto) throws SQLException {

        boolean resultBoolean = false;
        if (idPproduto == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        PreparedStatement stmt = this.connection
                .prepareStatement("select id from produto where id = ?");
        stmt.setLong(1, idPproduto);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            return true;
        }

        rs.close();
        stmt.close();

        return resultBoolean;

    }
    public boolean verificacaoProdutoLastro(Long idPproduto) throws SQLException {

        boolean resultBoolean = false;
        if (idPproduto == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        PreparedStatement stmt = this.connection
                .prepareStatement("select * from produto as p where p.id = ? and p.idtipoProduto != '2' and p.idtipoProduto != '3' and p.idtipoProduto != '4' order by p.id;");
        stmt.setLong(1, idPproduto);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            return true;
        }

        rs.close();
        stmt.close();

        return resultBoolean;

    }

}
