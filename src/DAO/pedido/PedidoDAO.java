/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.pedido;

import Controle.ModeloTabela;
import DAO.PedidoProdutoDAO;
import DAO.produto.NomenclaturaDAO;
import DAO.produto.ProdutoDAO;
import Modelo.ModeloPedidoProduto;
import Modelo.Pedido.ModeloPedido;
import Modelo.Pedido.ViewProdutoPedido;
import Modelo.Produto.Auxiliar;
import Modelo.Produto.ModeloNomenclatura;
import Modelo.Produto.ModeloProduto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author dan-pelusci
 */
public class PedidoDAO {

    private Connection connection;

    public PedidoDAO(Connection connection) {
        this.connection = connection;
    }

    public int adiciona(ModeloPedido modeloPedido) {
        String sql = "insert into pedido (idfuncionario,idcliente,idtipopedido,numerografica,dataemissao,situacao,processoquitacao) values (?,?,?,?,?,?,?)";
        PreparedStatement stmt;

        int idObjeto = 0;
        try {
            modeloPedido.setSituacao(false);
            modeloPedido.setProcessoQuitacao(false);
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, modeloPedido.getIdFuncionario());
            stmt.setLong(2, modeloPedido.getIdCliente());
            stmt.setLong(3, modeloPedido.getIdTipoPedido());
            stmt.setInt(4, modeloPedido.getNumeroGrafica());
            stmt.setDate(5, modeloPedido.getDataEmissao());
            stmt.setBoolean(6, modeloPedido.isSituacao());
            stmt.setBoolean(7, modeloPedido.isProcessoQuitacao());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            while (rs.next()) {

                idObjeto = rs.getInt(1);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idObjeto;

    }

    public int adicionaLastro(ModeloPedido modeloPedido) {
        String sql = "insert into pedido (idfuncionario,idcliente,idtipopedido,numerografica,dataemissao,situacao,processoquitacao,boleto) values (?,?,?,?,?,?,?,?)";
        PreparedStatement stmt;

        int idObjeto = 0;
        try {
            modeloPedido.setBoleto(true);
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, modeloPedido.getIdFuncionario());
            stmt.setLong(2, modeloPedido.getIdCliente());
            stmt.setLong(3, modeloPedido.getIdTipoPedido());
            stmt.setInt(4, modeloPedido.getNumeroGrafica());
            stmt.setDate(5, modeloPedido.getDataEmissao());
            stmt.setBoolean(6, modeloPedido.isSituacao());
            stmt.setBoolean(7, modeloPedido.isProcessoQuitacao());
            stmt.setBoolean(8, modeloPedido.isBoleto());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();

            while (rs.next()) {

                idObjeto = rs.getInt(1);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return idObjeto;

    }

    public void remove(ModeloPedido modeloPedido) {

        if (modeloPedido.getId() == null) {
            throw new IllegalStateException("Id da modeloPedido naoo deve ser nula.");
        }

        String sql = "delete from pedido where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloPedido.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloPedido modeloPedido) {
        String sql = "update pedido set idfuncionario = ?, idcliente = ?, idtipopedido = ?, numerografica = ? ,dataemissao = ?, situacao=?, processoquitacao = ?,idvenda =? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloPedido.getIdFuncionario());
            stmt.setLong(2, modeloPedido.getIdCliente());
            stmt.setLong(3, modeloPedido.getIdTipoPedido());
            stmt.setInt(4, modeloPedido.getNumeroGrafica());
            stmt.setDate(5, modeloPedido.getDataEmissao());
            stmt.setBoolean(6, modeloPedido.isSituacao());
            stmt.setBoolean(7, modeloPedido.isProcessoQuitacao());
            stmt.setLong(8, modeloPedido.getIdVenda());

            stmt.setLong(9, modeloPedido.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraPedido(ModeloPedido modeloPedido) {
        String sql = "update pedido set idfuncionario = ?, idcliente = ?, idtipopedido = ?, numerografica = ? ,dataemissao = ?, situacao=?, processoquitacao = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloPedido.getIdFuncionario());
            stmt.setLong(2, modeloPedido.getIdCliente());
            stmt.setLong(3, modeloPedido.getIdTipoPedido());
            stmt.setInt(4, modeloPedido.getNumeroGrafica());
            stmt.setDate(5, modeloPedido.getDataEmissao());
            stmt.setBoolean(6, modeloPedido.isSituacao());
            stmt.setBoolean(7, modeloPedido.isProcessoQuitacao());

            stmt.setLong(8, modeloPedido.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloPedido> lista() {
        try {
            List<ModeloPedido> bairros = new ArrayList<ModeloPedido>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from pedido order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista
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

            String[] Colunas = new String[]{"ID", "Cliente", "Funcionario", "Tipo do Pedido", "Numero da Grafica", "Pedido Aber/Fechado", "Quitacão"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select p.id, vd.id, cli.nome, f.nome,  tip.nome, p.numerografica, p.situacao, p.processoquitacao from pedido as p inner join venda as vd p.idvenda = vd.id inner join funcionario as f p.idfuncionario = f.id inner join cliente as cli p.idcliente = cli.id inner join tipopedido tip p.idtipopedido = tip.id order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedidos = new ModeloTabela(dados, Colunas);
            return pedidos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloPedido buscaPorVenda(int idVenda) {
        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from pedido where idvenda = ?");
            stmt.setLong(1, idVenda);
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

    public ModeloPedido buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloPedido nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from pedido where id = ?");
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
        String sql = "select p.id, vd.id, cli.nome, f.nome,  tip.nome, p.numerografica, p.situacao, p.processoquitacao from pedido as p inner join venda as vd p.idvenda = vd.id inner join funcionario as f p.idfuncionario = f.id inner join cliente as cli p.idcliente = cli.id inner join tipopedido tip p.idtipopedido = tip.id order by id where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Cliente", "Funcionario", "Tipo do Pedido", "Numero da Grafica", "Pedido Aber/Fechado", "Quitacão"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedido = new ModeloTabela(dados, Colunas);
            return pedido;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removerPedidoProdutoPeloCliente(int idclinte) throws SQLException {
        List<ModeloPedido> pedidos = new ArrayList<ModeloPedido>();
        String sql = " select * from pedido as p where p.idcliente = ? and p.situacao = false";
        PreparedStatement stmt = this.connection
                .prepareStatement(sql);
        stmt.setLong(1, idclinte);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // adiciona a modeloPedidoProduto na lista
            pedidos.add(populaObjeto(rs));
        }
        rs.close();
        stmt.close();
        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        for (ModeloPedido pd : pedidos) {
            pedidoProdutoDAO.removeTodosProdutoPedidosPeloPedido(pd);

        }

    }

    public void removerPedidoProdutoPeloPedido(int idclinte) throws SQLException {
        List<ModeloPedido> pedidos = new ArrayList<ModeloPedido>();
        String sql = " select * from pedido as p where p.idcliente = ?";
        PreparedStatement stmt = this.connection
                .prepareStatement(sql);
        stmt.setLong(1, idclinte);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // adiciona a modeloPedidoProduto na lista
            pedidos.add(populaObjeto(rs));
        }
        rs.close();
        stmt.close();
        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        for (ModeloPedido pd : pedidos) {
            pedidoProdutoDAO.removeTodosProdutoPedidosPeloPedido(pd);

        }

    }

    private ModeloPedido populaObjeto(ResultSet rs) throws SQLException {
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
        modeloPedido.setBoleto(rs.getBoolean("boleto"));
        System.out.println(modeloPedido.isBoleto());

        return modeloPedido;
    }

    public Long adicionaPedidoProduto(ModeloPedido modeloPedido, List<ViewProdutoPedido> viewProdutoPedidos) {
        //vai add o pedido
        Long idPedido = Long.valueOf(adicionaLastro(modeloPedido));

        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        ModeloPedidoProduto modeloPedidoProduto = new ModeloPedidoProduto();
        //
        for (int i = 0; i < viewProdutoPedidos.size(); i++) {
            modeloPedidoProduto.setIdpedido(idPedido);
            modeloPedidoProduto.setIdproduto(viewProdutoPedidos.get(i).getId());
            modeloPedidoProduto.setQuantidade(Integer.parseInt(viewProdutoPedidos.get(i).getQuantidade()));
            modeloPedidoProduto.setTipoAcao(viewProdutoPedidos.get(i).getTipoAcao());
            pedidoProdutoDAO.adiciona(modeloPedidoProduto);

        }
        return idPedido;

    }

    public Long adicionaPedidoProdutoVenda(ModeloPedido modeloPedido, List<ViewProdutoPedido> viewProdutoPedidos) {
        Long idPedido = Long.valueOf(adicionaLastro(modeloPedido));

        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        ModeloPedidoProduto modeloPedidoProduto = new ModeloPedidoProduto();

        for (int i = 0; i < viewProdutoPedidos.size(); i++) {
            modeloPedidoProduto.setIdpedido(idPedido);
            modeloPedidoProduto.setIdproduto(viewProdutoPedidos.get(i).getId());
            modeloPedidoProduto.setQuantidade(Integer.parseInt(viewProdutoPedidos.get(i).getQuantidade()));
            modeloPedidoProduto.setTipoAcao(viewProdutoPedidos.get(i).getTipoAcao());
            pedidoProdutoDAO.adicionaVenda(modeloPedidoProduto);

        }
        return idPedido;

    }

    //não mecher
    public void alteraPedidoProduto(List<Integer> itemDeletado, ModeloPedido modeloPedido, List<ViewProdutoPedido> viewProdutoPedidos) throws SQLException {

        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        ModeloPedidoProduto modeloPedidoProduto = new ModeloPedidoProduto();
        PedidoDAO pedidoDAO = new PedidoDAO(connection);

        for (int i = 0; i < viewProdutoPedidos.size(); i++) {
            modeloPedidoProduto.setIdpedido(modeloPedido.getId());
            modeloPedidoProduto.setIdproduto(viewProdutoPedidos.get(i).getId());
            modeloPedidoProduto.setTipoAcao(viewProdutoPedidos.get(i).getTipoAcao());
            modeloPedidoProduto.setQuantidade(Integer.parseInt(viewProdutoPedidos.get(i).getQuantidade()));

            if (pedidoProdutoDAO.verificaSeTem(modeloPedidoProduto.getIdproduto(), modeloPedidoProduto.getIdpedido())) {
                pedidoProdutoDAO.altera(modeloPedidoProduto);
            } else {
                pedidoProdutoDAO.adiciona(modeloPedidoProduto);
            }

        }
        if (itemDeletado.size() != 0) {
            for (int i = 0; i < itemDeletado.size(); i++) {
                modeloPedidoProduto.setIdpedido(modeloPedido.getId());
                modeloPedidoProduto.setIdproduto(Long.valueOf(itemDeletado.get(i)));
                pedidoProdutoDAO.remove(modeloPedidoProduto);
            }
        }
        pedidoDAO.alteraPedido(modeloPedido);

    }

    public void alteraPedidoProdutoLastro(List<Auxiliar> itemDeletado, ModeloPedido modeloPedido, List<ViewProdutoPedido> viewProdutoPedidos) throws SQLException {

        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        ModeloPedidoProduto modeloPedidoProduto = new ModeloPedidoProduto();
        PedidoDAO pedidoDAO = new PedidoDAO(connection);

        for (int i = 0; i < viewProdutoPedidos.size(); i++) {
            modeloPedidoProduto.setIdpedido(modeloPedido.getId());
            modeloPedidoProduto.setIdproduto(viewProdutoPedidos.get(i).getId());
            modeloPedidoProduto.setTipoAcao(viewProdutoPedidos.get(i).getTipoAcao());
            modeloPedidoProduto.setQuantidade(Integer.parseInt(viewProdutoPedidos.get(i).getQuantidade()));
            if (pedidoProdutoDAO.verificaSeTemLastro(modeloPedidoProduto.getIdproduto(), modeloPedidoProduto.getIdpedido(), modeloPedidoProduto.getTipoAcao())) {
                pedidoProdutoDAO.alteraLastro(modeloPedidoProduto);
            } else {
                pedidoProdutoDAO.adicionaLastro(modeloPedidoProduto);
            }

        }
        if (itemDeletado.size() != 0) {
            for (int i = 0; i < itemDeletado.size(); i++) {
                modeloPedidoProduto.setIdpedido(modeloPedido.getId());
                modeloPedidoProduto.setIdproduto(itemDeletado.get(i).getID());
                modeloPedidoProduto.setTipoAcao(itemDeletado.get(i).getIdacao());

                pedidoProdutoDAO.removeLastro(modeloPedidoProduto);
            }
        }
        pedidoDAO.alteraPedido(modeloPedido);

    }

    public List<ViewProdutoPedido> buscarPedidoproduto(int idPedido) {
        List<ViewProdutoPedido> viewProdutoPedidos = new ArrayList<ViewProdutoPedido>();
        List<ViewProdutoPedido> viewProdutoPedidos2 = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        NomenclaturaDAO nomenclaturaDAO = new NomenclaturaDAO(connection);
        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        List<ModeloPedidoProduto> pedidos = pedidoProdutoDAO.lista(Long.valueOf(idPedido));

        ModeloProduto modeloProduto = new ModeloProduto();
        ModeloNomenclatura modeloNomenclatura = new ModeloNomenclatura();

        for (int i = 0; i < pedidos.size(); i++) {
            ViewProdutoPedido viewProdutoPedido = new ViewProdutoPedido();

            modeloProduto = produtoDAO.buscaPorId(pedidos.get(i).getIdproduto());

            modeloNomenclatura = nomenclaturaDAO.buscaPorId(modeloProduto.getNomenclatura());

            viewProdutoPedido.setId(modeloProduto.getId());

            viewProdutoPedido.setNome(modeloProduto.getNome());
            viewProdutoPedido.setNomenclatura(modeloNomenclatura.getNome());
            viewProdutoPedido.setQuantidade("" + pedidos.get(i).getQuantidade());

            viewProdutoPedidos2.add(viewProdutoPedido);

        }

        return viewProdutoPedidos2;
    }

    public List<ViewProdutoPedido> buscarPedidoprodutoVasilhame(int idPedido) throws SQLException {

        List<ViewProdutoPedido> viewProdutoPedidos2 = new ArrayList<>();
        ProdutoDAO produtoDAO = new ProdutoDAO(connection);
        NomenclaturaDAO nomenclaturaDAO = new NomenclaturaDAO(connection);
        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        List<ModeloPedidoProduto> pedidos = pedidoProdutoDAO.listaLastro(Long.valueOf(idPedido));

        ModeloProduto modeloProduto = new ModeloProduto();
        ModeloNomenclatura modeloNomenclatura = new ModeloNomenclatura();

        for (int i = 0; i < pedidos.size(); i++) {
            ViewProdutoPedido viewProdutoPedido = new ViewProdutoPedido();

            modeloProduto = produtoDAO.buscaPorId(pedidos.get(i).getIdproduto());

            modeloNomenclatura = nomenclaturaDAO.buscaPorId(modeloProduto.getNomenclatura());

            viewProdutoPedido.setId(modeloProduto.getId());
            viewProdutoPedido.setTipoAcao(pedidos.get(i).getTipoAcao());
            viewProdutoPedido.setNome(modeloProduto.getNome());
            viewProdutoPedido.setNomenclatura(modeloNomenclatura.getNome());
            viewProdutoPedido.setQuantidade("" + pedidos.get(i).getQuantidade());

            viewProdutoPedidos2.add(viewProdutoPedido);

        }

        return viewProdutoPedidos2;
    }

    public TableModel listaTabelaPedidosAbertos() {

        try {

            String[] Colunas = new String[]{"ID Cliente", "Cliente", "Numero Pedido"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select  cli.id, cli.nome,p.id from pedido as p inner join cliente as cli on p.idcliente = cli.id inner join funcionario as fun on p.idfuncionario = fun.id where p.situacao = false order by cli.id");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getString(2), rs.getLong(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedidos = new ModeloTabela(dados, Colunas);
            return pedidos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TableModel listaTabelaPedidosAbertosBuscaPorIDCliente(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloPedido nao deve ser nula.");
        }

        try {

            String[] Colunas = new String[]{"ID Cliente", "Cliente", "Pedidos em aberto"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select cli.id,cli.nome, count(p.situacao)\n"
                            + "from pedido as p \n"
                            + "inner join cliente as cli on p.idcliente = cli.id inner join\n"
                            + "funcionario as fun on p.idfuncionario = fun.id where p.situacao = false and cli.id = ? group by cli.id order by cli.id; ");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getString(2), rs.getString(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedidos = new ModeloTabela(dados, Colunas);
            return pedidos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TableModel listaTabelaPedidosProdutoPorIDCliente(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloPedido nao deve ser nula.");
        }

        try {

            String[] Colunas = new String[]{"ID do Pedido", "Quantos itens"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select pd.id, count(pp.idproduto) from relationship_21 as pp inner join pedido as pd on pp.idpedido = pd.id inner join cliente as cli on pd.idcliente = cli.id where cli.id = ? and pd.idvenda isnull group by pd.id order by pd.id");

            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getString(2)});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedidos = new ModeloTabela(dados, Colunas) {

            };
            return pedidos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TableModel testes(Long id, List<ModeloPedido> pedidosSelecionados) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloPedido nao deve ser nula.");
        }

        try {

            String[] Colunas = new String[]{"ID do Pedido", "Quantos itens"};
            ArrayList dados = new ArrayList();
            String pedidos1 = " and pd.id =";
            String pedido = "";
            for (ModeloPedido pd : pedidosSelecionados) {

                pedido = pedido + pedidos1 + pd.getId();

            }

            PreparedStatement stmt = this.connection
                    .prepareStatement("select pd.id, count(pp.idproduto) from relationship_21 as pp inner join pedido as pd on pp.idpedido = pd.id inner join cliente as cli on pd.idcliente = cli.id where cli.id = ?" + pedido + "group by pd.id order by pd.id");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getString(2)});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedidos = new ModeloTabela(dados, Colunas) {

            };
            return pedidos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeAllPedidosPorIdVenda(int idVenda) {

        try {

            String sql = "delete  from pedido as p where p.idVenda = ? ";
            PreparedStatement stmt;

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idVenda);
            stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeAllPedidosAbertosporIdCLiente(int id) {
        int idCliente = id;
        try {

            String sql = "delete  from pedido as p where p.situacao = false and p.idcliente = ? ";
            PreparedStatement stmt;

            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idCliente);
            stmt.execute();

            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public TableModel listaTabelaPorNumeroGraficaLocacao(String text) {
        if (text == null) {
            throw new IllegalStateException("Id da Numero de pedido nao deve ser nula.");
        }

        try {

            String[] Colunas = new String[]{"ID do Pedido", "Quantos itens"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select pd.id, count(pp.idproduto) from relationship_21 as pp inner join pedido as pd on pp.idpedido = pd.id  where pd.numerografica = ? and idtipopedido= 3 group by pd.id order by pd.id");
            stmt.setInt(1, Integer.parseInt(text));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getString(2)});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedidos = new ModeloTabela(dados, Colunas) {

            };
            return pedidos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public TableModel listaTabelaPorNumeroGraficaVenda(String text) {
        if (text == null) {
            throw new IllegalStateException("Id da Numero de pedido nao deve ser nula.");
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            String[] Colunas = new String[]{"ID do Pedido", "Data de emissão"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select pd.id, pd.dataemissao  from pedido as pd   where pd.numerografica = ? and idtipopedido= 3 group by pd.id order by pd.id;");
            stmt.setInt(1, Integer.parseInt(text));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), format.format(rs.getDate(2).getTime())});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedidos = new ModeloTabela(dados, Colunas) {

            };
            return pedidos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long adicionaPedidoProdutoLastro(ModeloPedido modeloPedido, List<ViewProdutoPedido> viewProdutoPedidos) {
        //vai add o pedido
        Long idPedido = Long.valueOf(adicionaLastro(modeloPedido));

        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        ModeloPedidoProduto modeloPedidoProduto = new ModeloPedidoProduto();
        //
        for (int i = 0; i < viewProdutoPedidos.size(); i++) {
            modeloPedidoProduto.setIdpedido(idPedido);
            modeloPedidoProduto.setIdproduto(viewProdutoPedidos.get(i).getId());
            modeloPedidoProduto.setQuantidade(Integer.parseInt(viewProdutoPedidos.get(i).getQuantidade()));
            modeloPedidoProduto.setTipoAcao(viewProdutoPedidos.get(i).getTipoAcao());
            pedidoProdutoDAO.adicionaLastro(modeloPedidoProduto);

        }
        return idPedido;

    }

    public TableModel listaTabelaPorNumeroPedido(String text) {

        if (text == null) {
            throw new IllegalStateException("Id da Numero de pedido nao deve ser nula.");
        }

        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            String[] Colunas = new String[]{"ID do Pedido", "Data de emissão"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select pd.id, pd.dataemissao  from pedido as pd   where pd.id = ? and idtipopedido= 3 group by pd.id order by pd.id;");
            stmt.setInt(1, Integer.parseInt(text));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), format.format(rs.getDate(2).getTime())});
            }

            rs.close();
            stmt.close();
            ModeloTabela pedidos = new ModeloTabela(dados, Colunas) {

            };
            return pedidos;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ModeloPedido> listarPedidosParaBoleto() throws SQLException {
        List<ModeloPedido> pedidos = new ArrayList<ModeloPedido>();
        String sql = " select pd.* from pedido as pd inner join cliente as cl  on pd.idcliente = cl.id where pd.boleto = false and cl.boleto = true order by id;";
        PreparedStatement stmt = this.connection
                .prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            // adiciona a modeloPedidoProduto na lista
            pedidos.add(populaObjeto(rs));
        }
        rs.close();
        stmt.close();

        return pedidos;

    }

    public ModeloPedido buscaPornPedido(int idVenda) {
        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from pedido as ped where ped.numerografica = ? and ped.boleto = true");
            stmt.setLong(1, idVenda);
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

    public boolean verificaPedidoNumerografica(Long idVenda) {
        try {
            boolean resultBoolean = false;

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from pedido as ped where ped.numerografica = ? and ped.boleto = true");
            stmt.setLong(1, idVenda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                return resultBoolean = true;
            }

            rs.close();
            stmt.close();

            return resultBoolean;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void alterarBoleto(boolean selected, long parseLong) {

        String sql = "UPDATE public.pedido   SET  boleto=?  WHERE id = ?;";
        PreparedStatement stmt;
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, selected);
            stmt.setLong(2, parseLong);
            stmt.execute();
            System.out.println("Add: "+ parseLong);
            System.out.println("como: "+ selected);
        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    

}
