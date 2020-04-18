/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controle.ModeloTabela;
import DAO.cliente.ClienteDAO;
import DAO.produto.ModeloProdutoDAO;
import DAO.produto.ProdutoDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.ModeloTipoAcao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author E.B.O 2
 */
public class TipoAcaoDAO {

    private Connection connection;

    public TipoAcaoDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloTipoAcao modeloTipoAcao) {
        String sql = "insert into tipoacao (nome) values (?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoAcao.getNome());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloTipoAcao modeloTipoAcao) {

        if (modeloTipoAcao.getId() == null) {
            throw new IllegalStateException("Id da tipoacao naoo deve ser nula.");
        }

        String sql = "delete from tipoacao where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloTipoAcao.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloTipoAcao modeloTipoAcao) {
        String sql = "update tipoacao set nome = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloTipoAcao.getNome());

            stmt.setLong(2, modeloTipoAcao.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloTipoAcao> lista() {
        try {
            List<ModeloTipoAcao> bairros = new ArrayList<ModeloTipoAcao>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipoacao order by id");

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
                    .prepareStatement("select * from tipoacao order by id;");

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
                    .prepareStatement("select * from tipoacao order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTipoAcao buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloRamoAtividade nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, trim(nome) as nome from tipoacao where id = ?");
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

    public List<ModeloTipoAcao> buscaIdPorNome(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloCidades nao deve ser nula.");
        }

        try {
            List<ModeloTipoAcao> bairros = new ArrayList<ModeloTipoAcao>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, nome from tipoacao where c.nome = ? ");
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

    public ModeloTabela listaTabelaLastroCliente(int Idcliente) {
        try {

            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            ModeloProdutoDAO modeloProdutoDAO = new ModeloProdutoDAO(connection);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String[] Colunas = new String[]{"ID", "Data", "Numero da Grafica", "NomeProduto", "Capacidade","Modelo", "Quantidade", "Ação"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from ramoatividade inner join cidade on ramoatividade.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select ped.id, ped.dataemissao,ped.numerografica,prod.nome, nomem.nome,mod.nome, pp.quantidade ,tipoa.nome from lastro as pp inner join tipoacao as tipoa on pp.idtipoacao= tipoa.id inner join produto as prod on pp.idproduto= prod.id inner join nomenclatura as nomem on prod.idnomenclatura = nomem.id inner join pedido as ped on pp.idpedido = ped.id inner join modelo AS mod on prod.idmodelo = mod.id where ped.idtipopedido = '3' and ped.idcliente = ?  order by ped.dataemissao ;");
            stmt.setInt(1, Idcliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBanco na lista
                
                dados.add(new Object[]{rs.getLong(1), format.format(rs.getDate(2)), rs.getInt(3), rs.getString(4), rs.getString(5),rs.getString(6), rs.getInt(7), rs.getString(8)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ModeloTabela listaTabelaLastroClientesDiaEspecifico() {
        try {

            CidadeDAO cidadeDAO = new CidadeDAO(connection);
            ProdutoDAO produtoDAO = new ProdutoDAO(connection);
            ModeloProdutoDAO modeloProdutoDAO = new ModeloProdutoDAO(connection);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String[] Colunas = new String[]{"NomeCliente","ID", "Data", "Numero da Grafica", "NomeProduto", "Capacidade","Modelo", "Quantidade", "Ação"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from ramoatividade inner join cidade on ramoatividade.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select cli.nome, ped.id, ped.dataemissao,ped.numerografica,prod.nome, nomem.nome,mod.nome, pp.quantidade ,tipoa.nome from lastro as pp inner join tipoacao as tipoa on pp.idtipoacao= tipoa.id inner join produto as prod on pp.idproduto= prod.id inner join nomenclatura as nomem on prod.idnomenclatura = nomem.id inner join pedido as ped on pp.idpedido = ped.id inner join modelo AS mod on prod.idmodelo = mod.id inner join cliente as cli on cli.id = ped.idcliente where ped.idtipopedido = '3' and ped.dataemissao = '20/12/2017' ;");
           // stmt.setDate(1, data);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBanco na lista
                
                dados.add(new Object[]{rs.getString(1),rs.getLong(2), format.format(rs.getDate(3)), rs.getInt(4), rs.getString(5), rs.getString(6),rs.getString(7), rs.getInt(8), rs.getString(9)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela listaTabelaLastroFull() {
        try {

            CidadeDAO cidadeDAO = new CidadeDAO(connection);

            String[] Colunas = new String[]{"Ação", "Numero da Grafica", "NomeProduto", "Capacidade", "QUantidade", "data"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from ramoatividade inner join cidade on ramoatividade.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select tipoa.nome, ped.numerografica, prod.nome, nomem.nome, pp.quantidade, ped.dataemissao from lastro as pp inner join tipoacao as tipoa on pp.idtipoacao= tipoa.id inner join produto as prod on pp.idproduto= prod.id inner join nomenclatura as nomem on prod.idnomenclatura = nomem.id inner join pedido as ped on pp.idpedido = ped.id where and ped.idtipopedido = '3' ;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBanco na lista

                dados.add(new Object[]{rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getDate(6)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaPecasLocacao() {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '1' and prod.idtipoproduto = '3' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaPecasLocacaoCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '1' and prod.idtipoproduto = '3' and ped.idcliente = ? ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaPecasDevolucaoCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '2' and prod.idtipoproduto = '3' and ped.idcliente = ? ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaPecasVendaClienteCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '3' and prod.idtipoproduto = '3' and ped.idcliente = ? ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaPecasVendaCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '3' and prod.idtipoproduto = '3' and ped.idcliente = ? ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloCliente> listaParaLocacao() {
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        List<Long> idclientes = new ArrayList<Long>();
        List<ModeloCliente> modeloClientes = new ArrayList<ModeloCliente>();

        String sql = "select idcliente from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '1' and prod.idtipoproduto = '2' "
                + "or (pp.idtipoacao = '2' and prod.idtipoproduto = '2') "
                + "or (pp.idtipoacao = '1' and prod.idtipoproduto = '3') "
                + "or (pp.idtipoacao = '2' and prod.idtipoproduto = '3') "
                + "or (pp.idtipoacao = '1' and prod.idtipoproduto = '4') "
                + "or (pp.idtipoacao = '2' and prod.idtipoproduto = '4') "
                + "group by"
                + " idcliente "
                + "order by"
                + " idcliente;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            while (rs.next()) {
                // adiciona a modeloCidades na lista

                modeloClientes.add(clienteDAO.buscaPorId(rs.getLong("idcliente")));
            }

            rs.close();
            stmt.close();

            return modeloClientes;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public int somaConcentradorLocacaoCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '1' and prod.idtipoproduto = '4' and ped.idcliente = ? ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaConcentradorDevolucaoCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '2' and prod.idtipoproduto = '4' and ped.idcliente = ? ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaConcentradorVendaClienteCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '3' and prod.idtipoproduto = '4' and ped.idcliente = ? ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaCilindroLocacao() {
        // idtipoacao 1 é locacao  idtipoproduto 2 = cilindro

        String sql = "select sum(pp.quantidade) from Lastro as pp  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '1' and prod.idtipoproduto = '2' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaCilindroLocacaoCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 2 = cilindro

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id  where pp.idtipoacao = '1' and prod.idtipoproduto = '2' and ped.idcliente = ?  ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaCilindroDevolucaoCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 2 = cilindro

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id  where pp.idtipoacao = '2' and prod.idtipoproduto = '2' and ped.idcliente = ? and ped.idtipopedido = '3' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int somaCilindroVendaCliente(int idCliente) {
        // idtipoacao 1 é locacao  idtipoproduto 2 = cilindro

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id  where pp.idtipoacao = '3' and prod.idtipoproduto = '2' and ped.idcliente = ? and ped.idtipopedido = '3' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela buscaLike(String nome) {
        nome = "%" + nome + "%";
        String sql = "select id, nome from tipoacao where nome like ? order by id;";
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

    private ModeloTipoAcao populaObjeto(ResultSet rs) throws SQLException {
        ModeloTipoAcao modeloTipoAcao = new ModeloTipoAcao();

        modeloTipoAcao.setId(rs.getLong("id"));
        modeloTipoAcao.setNome(rs.getString("nome"));

        return modeloTipoAcao;
    }
    
    public int somaPecasLocacaoTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '1' and prod.idtipoproduto = '3' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int somaPecasDevolucaoTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '2' and prod.idtipoproduto = '3' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int somaPecasVendaTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '3' and prod.idtipoproduto = '3'  ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public int somaCilindroLocacaoTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 2 = cilindro

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id  where pp.idtipoacao = '1' and prod.idtipoproduto = '2'  ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     public int somaCilindroDevolucaoTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 2 = cilindro

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id  where pp.idtipoacao = '2' and prod.idtipoproduto = '2' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     public int somaCilindroVendaTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 2 = cilindro

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id  where pp.idtipoacao = '3' and prod.idtipoproduto = '2' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     
     public int somaConcentradorLocacaoTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '1' and prod.idtipoproduto = '4'  ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     
     public int somaConcentradorDevolucaoTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '2' and prod.idtipoproduto = '4'  ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     
     public int somaConcentradorVendaTotal() {
        // idtipoacao 1 é locacao  idtipoproduto 3 = pecas

        String sql = "select sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id where pp.idtipoacao = '3' and prod.idtipoproduto = '4' ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            int resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaLocaçao(Long idCliente) throws SQLException {
        
        //
        String sql = "select cl.id from parcelas as cl where   cl.idcliente = ?;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setLong(1, idCliente);

        stmt.execute();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // adiciona a modeloBairro na lista
                retorno = true;

            }
        }
        stmt.close();

        return retorno;
    }

}
