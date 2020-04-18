/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controle.ModeloTabela;
import Modelo.ModeloPedidoProduto;
import Modelo.Pedido.ModeloPedido;
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
public class PedidoProdutoDAO {

    private Connection connection;

    public PedidoProdutoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloPedidoProduto modeloPedidoProduto) {
        String sql = "insert into relationship_21 (idpedido,idproduto,quantidade) values (?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloPedidoProduto.getIdpedido());
            stmt.setLong(2, modeloPedidoProduto.getIdproduto());
            stmt.setInt(3, modeloPedidoProduto.getQuantidade());
           // stmt.setLong(4, modeloPedidoProduto.getTipoAcao());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void adicionaVenda(ModeloPedidoProduto modeloPedidoProduto) {
        String sql = "insert into relationship_21 (idpedido,idproduto,quantidade) values (?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloPedidoProduto.getIdpedido());
            stmt.setLong(2, modeloPedidoProduto.getIdproduto());
            stmt.setInt(3, modeloPedidoProduto.getQuantidade());
            //stmt.setLong(4, modeloPedidoProduto.getTipoAcao());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloPedidoProduto modeloPedidoProduto) {

        if (modeloPedidoProduto.getIdpedido() == null && modeloPedidoProduto.getIdproduto() == null) {
            throw new IllegalStateException("Id da modeloPedidoProduto naoo deve ser nula.");
        }

        String sql = "delete from relationship_21 where idproduto = ? and idpedido = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloPedidoProduto.getIdproduto());
            stmt.setLong(2, modeloPedidoProduto.getIdpedido());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeVasilhame(ModeloPedidoProduto modeloPedidoProduto) {

        if (modeloPedidoProduto.getIdpedido() == null && modeloPedidoProduto.getIdproduto() == null) {
            throw new IllegalStateException("Id da modeloPedidoProduto naoo deve ser nula.");
        }

        String sql = "delete from relationship_21 where idproduto = ? and idpedido = ? and idtipoacao=?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloPedidoProduto.getIdproduto());
            stmt.setLong(2, modeloPedidoProduto.getIdpedido());
            stmt.setLong(3, modeloPedidoProduto.getTipoAcao());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeTodosProdutoPedidos(ModeloPedidoProduto modeloPedidoProduto) {

        if (modeloPedidoProduto.getIdpedido() == null && modeloPedidoProduto.getIdproduto() == null) {
            throw new IllegalStateException("Id da modeloPedidoProduto naoo deve ser nula.");
        }

        String sql = "delete * from relationship_21 where idpedido = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(2, modeloPedidoProduto.getIdpedido());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeTodosProdutoPedidosPeloPedido(ModeloPedido modeloPedido) {

        if (modeloPedido.getId() == null) {
            throw new IllegalStateException("Id da modeloPedidoProduto naoo deve ser nula.");
        }

        String sql = "delete  from relationship_21 where idpedido = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloPedido.getId());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloPedidoProduto modeloPedidoProduto) {

        String sql = "update relationship_21 set quantidade=? where idproduto = ? and idpedido = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, modeloPedidoProduto.getQuantidade());
            stmt.setLong(2, modeloPedidoProduto.getIdproduto());
            stmt.setLong(3, modeloPedidoProduto.getIdpedido());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloPedidoProduto> lista(Long pedido) {
        try {
            List<ModeloPedidoProduto> contas = new ArrayList<ModeloPedidoProduto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from relationship_21 where idpedido = ?  order by idpedido");
            stmt.setLong(1, pedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedidoProduto na lista
                contas.add(populaModelo(rs));
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

            String[] colunas = new String[]{"ID Do pedido", "Nome do Produto", "Quantidade", "Capacidade", "Medida", "Tipo", "Modelo", "ID do produto"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from bairro inner join cidade on bairro.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select pp.idpedido, pr.nome, pp.quantidade,pp.capacidade,nomen.nome, tipPro.nome,mode.nome,  pp.idproduto    from relationship_21 as pp inner join produto as pr on pp.idproduto = pr.id inner join pedido as ped on pp.idpedido = ped.id inner join nomenclatura as nomen on pr.idnomenclatura = nomen.id inner join tipoproduto as tipPro on pr.idtipoproduto = tipPro.id inner join modelo as mode on pr.idmodelo = mode.id order by idpedido;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela buscaPorPedido(ModeloPedido modeloPedido) {

        String sql = "select pp.idpedido, pr.nome, pp.quantidade,pp.capacidade,nomen.nome, tipPro.nome,mode.nome,  pp.idproduto    from relationship_21 as pp inner join produto as pr on pp.idproduto = pr.id inner join pedido as ped on pp.idpedido = ped.id inner join nomenclatura as nomen on pr.idnomenclatura = nomen.id inner join tipoproduto as tipPro on pr.idtipoproduto = tipPro.id inner join modelo as mode on pr.idmodelo = mode.id where ped.id = ? order by idpedido;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloPedido.getId());

            stmt.execute();

            String[] colunas = new String[]{"ID Do pedido", "Nome do Produto", "Quantidade", "Capacidade", "Medida", "Tipo", "Modelo", "ID do produto"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloPedidoProduto populaModelo(ResultSet rs) throws SQLException {
        ModeloPedidoProduto modeloPedidoProduto = new ModeloPedidoProduto();
        
        modeloPedidoProduto.setIdpedido(rs.getLong("idpedido"));
        modeloPedidoProduto.setIdproduto(rs.getLong("idproduto"));
        modeloPedidoProduto.setQuantidade(rs.getInt("quantidade"));

        return modeloPedidoProduto;
    }
    private ModeloPedidoProduto populaModelo2(ResultSet rs) throws SQLException {
        ModeloPedidoProduto modeloPedidoProduto = new ModeloPedidoProduto();
        modeloPedidoProduto.setTipoAcao(rs.getLong("idtipoacao"));
        modeloPedidoProduto.setIdpedido(rs.getLong("idpedido"));
        modeloPedidoProduto.setIdproduto(rs.getLong("idproduto"));
        modeloPedidoProduto.setQuantidade(rs.getInt("quantidade"));

        return modeloPedidoProduto;
    }

    private ModeloPedido populaModelopedidos(ResultSet rs) throws SQLException {
        ModeloPedido modeloPedido = new ModeloPedido();

        modeloPedido.setId(rs.getLong("id"));
        modeloPedido.setIdVenda(rs.getLong("idvenda"));
        modeloPedido.setIdFuncionario(rs.getLong("idfuncionario"));
        modeloPedido.setIdCliente(rs.getLong("idcliente"));
        modeloPedido.setIdTipoPedido(rs.getLong("idtipopedido"));
        modeloPedido.setNumeroGrafica(rs.getInt("numerografica"));
        modeloPedido.setDataEmissao(rs.getDate("dataemissao"));
        modeloPedido.setSituacao(rs.getBoolean("situacao"));
        modeloPedido.setProcessoQuitacao(rs.getBoolean("processoquitacao"));

        return modeloPedido;
    }

    public Long buscaAcaoPorId(Long idproduto, Long idpedido) throws SQLException {
        
        List<ModeloPedidoProduto> contas = new ArrayList<ModeloPedidoProduto>();
        PreparedStatement stmt = this.connection
                .prepareStatement("select ac.id from relationship_21 as pp inner join tipoacao as ac on ac.id = pp.idtipoacao where pp.idproduto = ? and pp.idpedido = ?");
        stmt.setLong(1, idproduto);
        stmt.setLong(2, idpedido);
        ResultSet rs = stmt.executeQuery();
        Long idacao = null;
        while (rs.next()) {
            // adiciona a modeloPedidoProduto na lista
            idacao = rs.getLong(1);
        }

        rs.close();
        stmt.close();
       
        return idacao;

    }

    public boolean verificaSeTem(Long idproduto, Long idpedido) throws SQLException {
        
        List<ModeloPedidoProduto> contas = new ArrayList<ModeloPedidoProduto>();
        PreparedStatement stmt = this.connection
                .prepareStatement("select * from relationship_21 as pp  where pp.idproduto = ? and pp.idpedido = ? ;");
        stmt.setLong(1, idproduto);
        stmt.setLong(2, idpedido);
        ResultSet rs = stmt.executeQuery();
        boolean idacao = false;
        while (rs.next()) {
            // adiciona a modeloPedidoProduto na lista
            idacao = true;
        }

        rs.close();
        stmt.close();
        
        return idacao;
    }
    public Long buscaAcaoPorIdLastro(Long idproduto, Long idpedido) throws SQLException {
        
        List<ModeloPedidoProduto> contas = new ArrayList<ModeloPedidoProduto>();
        PreparedStatement stmt = this.connection
                .prepareStatement("select pp.idtipoacao from lastro as pp inner join tipoacao as ac on ac.id = pp.idtipoacao where pp.idproduto = ? and pp.idpedido = ?");
        stmt.setLong(1, idproduto);
        stmt.setLong(2, idpedido);
        ResultSet rs = stmt.executeQuery();
        Long idacao = null;
        while (rs.next()) {
            // adiciona a modeloPedidoProduto na lista
            
            idacao = rs.getLong(1);
          
        }

        rs.close();
        stmt.close();
        
        return idacao;

    }

    public void removeLastro(ModeloPedidoProduto modeloPedidoProduto) {

        if (modeloPedidoProduto.getIdpedido() == null && modeloPedidoProduto.getIdproduto() == null) {
            throw new IllegalStateException("Id da modeloPedidoProduto naoo deve ser nula.");
        }

        String sql = "delete from lastro where idproduto = ? and idpedido = ? and idtipoacao=?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloPedidoProduto.getIdproduto());
            stmt.setLong(2, modeloPedidoProduto.getIdpedido());
            stmt.setLong(3, modeloPedidoProduto.getTipoAcao());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void alteraLastro(ModeloPedidoProduto modeloPedidoProduto) {

        String sql = "update lastro set quantidade=? where idproduto = ? and idpedido = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, modeloPedidoProduto.getQuantidade());
            stmt.setLong(2, modeloPedidoProduto.getIdproduto());
            stmt.setLong(3, modeloPedidoProduto.getIdpedido());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void adicionaLastro(ModeloPedidoProduto modeloPedidoProduto) {
        String sql = "insert into lastro (idpedido,idproduto,quantidade,idtipoacao) values (?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloPedidoProduto.getIdpedido());
            stmt.setLong(2, modeloPedidoProduto.getIdproduto());
            stmt.setInt(3, modeloPedidoProduto.getQuantidade());
            stmt.setLong(4, modeloPedidoProduto.getTipoAcao());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean verificaSeTemLastro(Long idproduto, Long idpedido,Long idTipoAcao) throws SQLException {
        
        List<ModeloPedidoProduto> contas = new ArrayList<ModeloPedidoProduto>();
        PreparedStatement stmt = this.connection
                .prepareStatement("select ac.id from lastro as pp inner join tipoacao as ac on ac.id = pp.idtipoacao where pp.idproduto = ? and pp.idpedido = ? and pp.idtipoacao= ?;");
        stmt.setLong(1, idproduto);
        stmt.setLong(2, idpedido);
        stmt.setLong(3, idTipoAcao);
        ResultSet rs = stmt.executeQuery();
        boolean idacao = false;
        while (rs.next()) {
            // adiciona a modeloPedidoProduto na lista
            idacao = true;
        }

        rs.close();
        stmt.close();
       
        return idacao;
    }
    public List<ModeloPedidoProduto> listaLastro(Long pedido) {
        try {
            List<ModeloPedidoProduto> contas = new ArrayList<ModeloPedidoProduto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from lastro where idpedido = ?  order by idpedido");
            stmt.setLong(1, pedido);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedidoProduto na lista
                contas.add(populaModelo2(rs));
            }

            rs.close();
            stmt.close();

            return contas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
