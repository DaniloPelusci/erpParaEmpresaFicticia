/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.venda;

import Controle.ModeloTabela;
import DAO.PedidoProdutoDAO;
import DAO.pedido.PedidoDAO;
import Modelo.Pedido.ModeloPedido;
import Modelo.venda.ModeloCheque;
import Modelo.venda.ModeloParcela;
import Modelo.venda.ModeloVenda;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author SERVIDOR
 */
public class VendaDAO {

    private Connection connection;

    public VendaDAO(Connection connection) {
        this.connection = connection;
    }

    public int adiciona(ModeloVenda modeloVenda) throws ParseException {
        String sql = "insert into venda (idcliente,idfuncionario,valorvenda,datavenda,estatus,notafiscal,notaenviada,observacao,lotes) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        int idObjeto = 0;
        try {

            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, modeloVenda.getIdcliente());
            stmt.setLong(2, modeloVenda.getIdfuncionario());
            stmt.setBigDecimal(3, modeloVenda.getValorVenda());
            stmt.setDate(4, (Date) modeloVenda.getDataVenda());
            stmt.setBoolean(5, modeloVenda.isStatus());
            stmt.setString(6, modeloVenda.getNotaFiscal());
            stmt.setBoolean(7, false);
            stmt.setString(8, modeloVenda.getObservacao());
            stmt.setString(9, modeloVenda.getLote());
            stmt.execute();
            

            ResultSet rs = stmt.getGeneratedKeys();

            while (rs.next()) {

                idObjeto = rs.getInt(1);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return idObjeto;

    }

    public void remove(ModeloVenda modeloVenda) {

        if (modeloVenda.getId() == null) {
            throw new IllegalStateException("Id da modeloFornecedor naoo deve ser nula.");
        }

        String sql = "delete from venda where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloVenda.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraEstatus(Long idVenda) {

        String sql = "update venda set estatus = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setLong(2, idVenda);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeVenda(ModeloVenda modeloVenda, int pedido) throws SQLException {
        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        PedidoProdutoDAO pedidoProdutoDAO = new PedidoProdutoDAO(connection);
        ChequeDAO chequeDAO = new ChequeDAO(connection);
        ModeloPedido modeloPedido = new ModeloPedido();
        ModeloParcela modeloParcela = new ModeloParcela();
        List<ModeloParcela> modeloParcelas = new ArrayList<>();

        modeloParcelas = parcelaDAO.buscaParcelasPorIdVenda(modeloVenda.getId());

        for (ModeloParcela p : modeloParcelas) {
            System.out.println(p.getId());
            chequeDAO.removeIdParcela(p.getId());
        }
        modeloPedido = pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(modeloVenda.getId())));

        parcelaDAO.removePorIdvenda(modeloVenda.getId());

        pedidoProdutoDAO.removeTodosProdutoPedidosPeloPedido(modeloPedido);
        pedidoDAO.remove(modeloPedido);

        remove(modeloVenda);

    }

    public List<ModeloVenda> lista() {
        try {
            List<ModeloVenda> contas = new ArrayList<ModeloVenda>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from fornecedor order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloFornecedor na lista
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

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Tipo", "Cidade", "Estado", "Bairro", "Telefone", "Telefone Secundario"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select f.id,f.nome,f.nomefantasia,t.nome,c.nome,e.nome,b.nome,f.telefone, f.telefone2 from fornecedor as f inner join cidade as c on f.idcidade = c.id inner join bairro as b on f.idbairro = b.id inner join estado as e on f.idestado = e.id inner join tipofornecedor as t on f.idtipofornecedor = t.id order by id");

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

    public ModeloVenda buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloFornecedor nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idcliente, idfuncionario, valorvenda, datavenda, estatus, trim(notafiscal) as notafiscal, trim(lotes) as lotes from venda where id = ?");
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

    public ModeloVenda buscaIdPorNome(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloFornecedor nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from fornecedor where nome = ?");
            stmt.setString(1, nome);
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

    public ModeloTabela buscarPorCliente(Long idCliente) {

        String sql = "select v.id, v.valorvenda, v.datavenda, pd.numerografica, v.notafiscal from venda as v  inner join pedido as pd on pd.idvenda = v.id where v.idcliente = ? order by v.datavenda";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idCliente);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Valor", "Data Venda", "Numero de Grafica", "Numero da nota fiscal"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();
            TipoPagamentoDAO tipoPagamentoDAO = new TipoPagamentoDAO(connection);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), format.format(rs.getDate(3)), rs.getString(4), rs.getString(5)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ModeloTabela buscaLike(String nome) {
        nome = "%" + nome + "%";
        //
        String sql = "select f.id,f.nome,f.nomefantasia,t.nome,c.nome,e.nome,b.nome,f.telefone, f.telefone1 from fornecedor as f inner join cidade as c on f.idcidade = c.id inner join bairro as b on f.idbairro = b.id inner join estado as e on f.idestado = e.id inner join tipo as t on f.idtipofornecedor = t.id  where f.nome like ? order by id";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Tipo", "Cidade", "Estado", "Bairro", "Telefone", "Telefone Secundario"};
            ArrayList dados = new ArrayList();

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

    private ModeloVenda populaModelo(ResultSet rs) throws SQLException {
        ModeloVenda modeloVenda = new ModeloVenda();

        modeloVenda.setId(rs.getLong("id"));
        modeloVenda.setIdcliente(rs.getLong("idcliente"));
        modeloVenda.setIdfuncionario(rs.getLong("idfuncionario"));
        modeloVenda.setValorVenda(rs.getBigDecimal("valorvenda"));
        modeloVenda.setDataVenda(rs.getDate("datavenda"));
        modeloVenda.setStatus(rs.getBoolean("estatus"));
        modeloVenda.setNotaFiscal(rs.getString("notafiscal"));
        modeloVenda.setLote(rs.getString("lotes"));

        return modeloVenda;
    }

    public int adicionaVendaPedidos(Date dataPagamento, boolean avista, ModeloVenda modeloVenda, ModeloPedido modeloPedido1, List<ModeloParcela> modeloParcelas, ModeloCheque modeloCheque) throws ParseException {

        Long idVenda = Long.valueOf(adiciona(modeloVenda));
        ModeloCheque modeloCheque1 = new ModeloCheque();
        modeloCheque1 = modeloCheque;

        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ChequeDAO chequeDAO = new ChequeDAO(connection);
        int i = 1;
        String idparcela = "";
        if (avista == true) {
            for (ModeloParcela mp : modeloParcelas) {
                if (i == 1) {
                    mp.setTipoParcela(true);
                    if (modeloCheque.getValor() == mp.getValor().floatValue()) {
                        mp.setEstatosParcela(true);
                    }

                    mp.setDataPagamento(dataPagamento);
                    mp.setId(null);
                    mp.setIdVenda(idVenda);
                    mp.setIdCliente(modeloPedido1.getIdCliente());
                    modeloCheque1.setIdparcela(Long.parseLong(String.valueOf(parcelaDAO.adicionaPaga(mp))));

                    chequeDAO.adiciona(modeloCheque);
                    i++;
                } else {
                    mp.setTipoParcela(true);
                    mp.setId(null);
                    mp.setIdVenda(idVenda);
                    mp.setIdCliente(modeloPedido1.getIdCliente());
                    parcelaDAO.adiciona(mp);
                }

            }
        } else {
            for (ModeloParcela mp : modeloParcelas) {
                mp.setTipoParcela(true);
                mp.setId(null);
                mp.setIdVenda(idVenda);
                mp.setIdCliente(modeloPedido1.getIdCliente());
                parcelaDAO.adiciona(mp);
                i++;
            }
        }

        PedidoDAO pedidoDAO = new PedidoDAO(connection);
        ModeloPedido modeloPedido = new ModeloPedido();

        modeloPedido = pedidoDAO.buscaPorId(modeloPedido1.getId());
        modeloPedido.setIdVenda(idVenda);
        modeloPedido.setSituacao(true);
        pedidoDAO.altera(modeloPedido);
        return Integer.parseInt(String.valueOf(idVenda));

    }

    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

    //Convert Calendar to Date
    private Date calendarToDate(Calendar calendar) {
        return (Date) calendar.getTime();
    }

    public TableModel listaTabelaPorNumeroPedido(String text) {
        if (text == null) {
            throw new IllegalStateException("Id da Numero de pedido nao deve ser nula.");
        }

        try {

            String[] Colunas = new String[]{"ID do Venda", "Data de emissão"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select vend.id, vend.datavenda, vend.notafiscal from venda as vend inner join pedido as ped on ped.idvenda = vend.id where ped.numerografica = ?;");
            stmt.setInt(1, Integer.parseInt(text));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getDate(2), rs.getString(3)});
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

    public TableModel listaTabelaPorNotaFiscal(String text) {

        if (text == null) {
            throw new IllegalStateException("Id da Numero de pedido nao deve ser nula.");
        }

        try {

            String[] Colunas = new String[]{"ID do Venda", "Data da Venda", "Nota Fiscal"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select vend.id, vend.datavenda, vend.notafiscal from venda as vend where vend.notafiscal = ?");
            stmt.setString(1, text);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getDate(2), rs.getString(3)});
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

    public void alterarNotaFiscal(String notafiscal, Long idVenda) {

        String sql = "update venda set notafiscal = ? where id = ?";
        PreparedStatement stmt;
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, notafiscal);
            stmt.setLong(2, idVenda);
            stmt.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    public boolean verificaEnvioBoleto(String idVenda) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean verificaEnvioNotaFiscal(Long idVenda) {

        try {
            boolean resultBoolean = false;

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from venda as ped where ped.id = ? and ped.notaenviada = false");
            stmt.setLong(1, idVenda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                return resultBoolean = true;
            }

            rs.close();
            stmt.close();
            System.err.println("aaaaaaaaaaaaaa " + idVenda);
            return resultBoolean;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alterarEnvioNotaFiscal(Long idVenda, boolean envioNotaFiscal) {

        String sql = "update venda set notaenviada = ? where id = ?";
        PreparedStatement stmt;
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, envioNotaFiscal);
            System.err.println("Dentro do metodo envio nota: " + idVenda);
            stmt.setLong(2, idVenda);
            stmt.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public boolean verificar(Long idVenda) throws SQLException {
       
     boolean resultBoolean = false;
        if (idVenda == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        PreparedStatement stmt = this.connection
                .prepareStatement("select id from Venda where id = ?");
        stmt.setLong(1, idVenda);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            return true;
        }

        rs.close();
        stmt.close();

        return resultBoolean;
    
    }

    public List<ModeloVenda> buscarLote(String nome) {
        nome = "%" + nome + "%";
        try {
            List<ModeloVenda> contas = new ArrayList<ModeloVenda>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT *FROM venda where lotes like ?");
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            

            while (rs.next()) {
                // adiciona a modeloFornecedor na lista
                contas.add(populaModelo(rs));
            }

            rs.close();
            stmt.close();

            return contas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

}
