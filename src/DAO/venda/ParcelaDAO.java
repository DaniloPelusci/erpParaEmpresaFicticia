/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.venda;

import Controle.ModeloTabela;
import Modelo.venda.ModeloParcela;
import java.math.BigDecimal;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author dan-pelusci
 */
public class ParcelaDAO {

    private Connection connection;

    public ParcelaDAO(Connection connection) {
        this.connection = connection;
    }

    public int adiciona(ModeloParcela modeloParcela) {
        String sql = "insert into parcelas (idvenda,idcliente,valor,datavencimento, estatosparcela,valorpago) values (?,?,?,?,?,?)";
        PreparedStatement stmt;
        int idObjeto = 0;
        try {
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, modeloParcela.getIdVenda());

            stmt.setLong(2, modeloParcela.getIdCliente());
            stmt.setBigDecimal(3, modeloParcela.getValor());

            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.setTime(modeloParcela.getDataVencimento());

            java.sql.Date dataSql = new java.sql.Date(modeloParcela.getDataVencimento().getTime());
            stmt.setDate(4, dataSql);

            stmt.setBoolean(5, modeloParcela.isEstatosParcela());
            stmt.setInt(6, 0);

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

    public int adicionaPaga(ModeloParcela modeloParcela) {
        String sql = "insert into parcelas (idvenda,idcliente,valor,datavencimento, estatosparcela,valorpago,datapagamento) values (?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        int idObjeto = 0;
        try {
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.setLong(1, modeloParcela.getIdVenda());

            stmt.setLong(2, modeloParcela.getIdCliente());
            stmt.setBigDecimal(3, modeloParcela.getValor());

            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.setTime(modeloParcela.getDataVencimento());

            java.sql.Date dataSql = new java.sql.Date(modeloParcela.getDataVencimento().getTime());
            stmt.setDate(4, dataSql);

            stmt.setBoolean(5, modeloParcela.isEstatosParcela());

            stmt.setInt(6, 0);
            Calendar tempCalendarPG = Calendar.getInstance();
            tempCalendarPG.setTime(modeloParcela.getDataPagamento());

            java.sql.Date dataSqlPG = new java.sql.Date(modeloParcela.getDataVencimento().getTime());
            stmt.setDate(7, dataSqlPG);

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

    public void atualizarValorPago(BigDecimal valor, Long id) {
        String sql = "update parcelas set valorpago = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setBigDecimal(1, valor);
            stmt.setLong(2, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void adicionaLocacao(ModeloParcela modeloParcela) {
        String sql = "insert into parcelas (idcliente,valor,datavencimento, estatosparcela, tipoparcela) values (?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloParcela.getIdCliente());
            stmt.setBigDecimal(2, modeloParcela.getValor());

            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.setTime(modeloParcela.getDataVencimento());

            java.sql.Date dataSql = new java.sql.Date(modeloParcela.getDataVencimento().getTime());

            stmt.setDate(3, dataSql);

            stmt.setBoolean(4, modeloParcela.isEstatosParcela());
            stmt.setBoolean(5, modeloParcela.isTipoParcela());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloParcela modeloParcela) {

        if (modeloParcela.getId() == null) {
            throw new IllegalStateException("Id da modeloTipoPagamento naoo deve ser nula.");
        }

        String sql = "delete from parcelas where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloParcela.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloParcela modeloParcela) {
        String sql = "update parcelas set  valor=?, datapagamento = ? , datavencimento =?, estatosparcela = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setBigDecimal(1, modeloParcela.getValor());

            stmt.setDate(2, (java.sql.Date) modeloParcela.getDataPagamento());
            stmt.setDate(3, (java.sql.Date) modeloParcela.getDataVencimento());
            stmt.setBoolean(4, modeloParcela.isEstatosParcela());

            stmt.setLong(5, modeloParcela.getId());

            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloParcela> lista() {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloParcela> listaLocacao(Long idCliente) {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and idCliente = ? order by id;");
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloParcela> listaLocacaoPorReferencia(Long idCliente, int mes, int ano) {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and idCliente = ? and EXTRACT(MONTH FROM datavencimento) = ? and EXTRACT(YEAR FROM datavencimento) = ? order by id;");
            stmt.setLong(1, idCliente);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ModeloParcela listaLocacaoPorReferenciaEnviarEmail(Long idCliente, int mes, int ano) {
        try {
            ModeloParcela parcelas = new ModeloParcela();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and idCliente = ? and EXTRACT(MONTH FROM datavencimento) = ? and EXTRACT(YEAR FROM datavencimento) = ? order by id;");
            stmt.setLong(1, idCliente);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas = populaObjeto(rs);
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
   
    
    public List<ModeloParcela> listaLocacaoPorMes( int mes, int ano) {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and EXTRACT(MONTH FROM datavencimento) = ? and EXTRACT(YEAR FROM datavencimento) = ?  order by id;");
            stmt.setInt(1, mes);
            stmt.setInt(2, ano);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloParcela listaLocacaoPorReferenciaCliente(Long idCliente, Date data) {

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and idCliente = ? and EXTRACT(MONTH FROM datavencimento) = ? and EXTRACT(YEAR FROM datavencimento) = ? order by id;");
            SimpleDateFormat ano = new SimpleDateFormat("yyyy");
            SimpleDateFormat mes = new SimpleDateFormat("MM");
            stmt.setLong(1, idCliente);
            stmt.setInt(2, Integer.parseInt(mes.format(data)));
            stmt.setInt(3, Integer.parseInt(ano.format(data)));
            
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

    public ModeloParcela locacaoPorReferencia(Long idCliente, int mes, int ano) {
        try {
            int ano1 = Integer.parseInt("20" + ano);
            ModeloParcela parcela = new ModeloParcela();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and idCliente = ? and EXTRACT(MONTH FROM datavencimento) = ? and EXTRACT(YEAR FROM datavencimento) = ? order by id;");
            stmt.setLong(1, idCliente);
            stmt.setInt(2, mes);
            stmt.setInt(3, ano1);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // adiciona a modeloTipoPedido na lista
               return populaObjeto(rs);
            }

            rs.close();
            stmt.close();

            return parcela;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloParcela> listaPorIdVenda(Long Idvenda) {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where idvenda = ? order by datavencimento");
            stmt.setLong(1, Idvenda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
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
            //		.prepareStatement("select * from tipopedido inner join cidade on tipopedido.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from tipopagamento order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome")});
            }

            rs.close();
            stmt.close();
            ModeloTabela tipoPagamento = new ModeloTabela(dados, Colunas);
            return tipoPagamento;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloParcela buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da parcela nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where id = ?");
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
        String sql = "select id, nome from tipopagamento where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Nome"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"),});
            }

            rs.close();
            stmt.close();
            ModeloTabela tipoPagamento = new ModeloTabela(dados, Colunas);
            return tipoPagamento;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloParcela populaObjeto(ResultSet rs) throws SQLException {
        ModeloParcela modeloParcela = new ModeloParcela();

        modeloParcela.setId(rs.getLong("id"));     
        modeloParcela.setIdVenda(rs.getLong("idvenda"));
        modeloParcela.setValor(rs.getBigDecimal("valor"));
        modeloParcela.setIdCliente(rs.getLong("idcliente"));

        modeloParcela.setDataPagamento(rs.getDate("datapagamento"));
        modeloParcela.setDataVencimento(rs.getDate("datavencimento"));
        modeloParcela.setEstatosParcela(rs.getBoolean("estatosparcela"));

        return modeloParcela;
    }

    //Convert Date to Calendar
    private Calendar dateToCalendar(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

    //Convert Calendar to Date
    private Date calendarToDate(Calendar calendar) {
        return (Date) calendar.getTime();
    }

    void removePorIdvenda(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloTipoPagamento naoo deve ser nula.");
        }

        String sql = "delete  from parcelas where idvenda = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, id);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean verificaparcela(ModeloParcela modeloParcela) {
        try {
            boolean resultBoolean = false;

            SimpleDateFormat ano = new SimpleDateFormat("yyyy");
            SimpleDateFormat mes = new SimpleDateFormat("MM");

            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and idCliente = ? and EXTRACT(MONTH FROM datavencimento) = ? and EXTRACT(YEAR FROM datavencimento) = ? order by id;");

            stmt.setLong(1, modeloParcela.getIdCliente());
            stmt.setInt(2, Integer.parseInt(mes.format(modeloParcela.getDataVencimento())));
            stmt.setInt(3, Integer.parseInt(ano.format(modeloParcela.getDataVencimento())));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                return true;
            }

            rs.close();
            stmt.close();

            return resultBoolean;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean verificaparcelaLocacao(Long id, Date data) {
        try {
            boolean resultBoolean = false;

            SimpleDateFormat ano = new SimpleDateFormat("yyyy");
            SimpleDateFormat mes = new SimpleDateFormat("MM");

            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and idCliente = ? and EXTRACT(MONTH FROM datavencimento) = ? and EXTRACT(YEAR FROM datavencimento) = ? order by id;");
            
            
            stmt.setLong(1, id);
            stmt.setInt(2, Integer.parseInt(mes.format(data)));
            stmt.setInt(3, Integer.parseInt(ano.format(data)));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                return true;
            }

            rs.close();
            stmt.close();

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public float SomaTotalValorPagarVenda(Long idCliente) {
        String sql = "select sum(p.valor)  from parcelas as p inner join venda as vend on p.idvenda = vend.id  where vend.idcliente = ? and p.estatosparcela = false; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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
    public float SomaTotalValorPagarVendaVencidas(Long idCliente) {
        String sql = "select sum(p.valor)  from parcelas as p inner join venda as vend on p.idvenda = vend.id  where vend.idcliente = ? and p.estatosparcela = false and p.datavencimento < CURRENT_DATE";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();
            
            ResultSet rs = stmt.executeQuery();
            
            float resultado = 0;
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

    public float SomaTotalValorPagoVenda(Long idCliente) {
        String sql = "select sum(chec.valor) from parcelas as p inner join venda as vend on p.idvenda = vend.id inner join cheque as chec on chec.idparcela = p.id where p.estatosparcela = false and vend.idcliente = ? ;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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
    public float SomaTotalValorPagoVendaVencidos(Long idCliente) {
        String sql = "select sum(chec.valor) from parcelas as p inner join venda as vend on p.idvenda = vend.id inner join cheque as chec on chec.idparcela = p.id where p.estatosparcela = false and vend.idcliente = ? and p.datavencimento <= CURRENT_DATE;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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

    public float SomaTotalValorASerpagoLocação(Long idCliente) {
        String sql = "select sum(p.valor)  from parcelas as p   where p.tipoparcela = false and p.idcliente = ? and p.estatosparcela = false ;  ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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
    public float SomaTotalValorASerpagoLocaçãoVencidas(Long idCliente) {
        String sql = "select sum(p.valor)  from parcelas as p   where p.tipoparcela = false and p.idcliente = ? and p.estatosparcela = false and p.datavencimento <= CURRENT_DATE;  ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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

    public double SomaTotalValorPagoPorParcela(Long IdParcela) {
        String sql = "select sum(cheq.valor) from parcelas as p inner join cheque as cheq on cheq.idparcela = p.id where cheq.idparcela = ? ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, IdParcela);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            double resultado = 0;
            if (rs.next()) {
                resultado = rs.getDouble(1);
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ModeloParcela> listaPorNPagoIdCliente(Long idCliente) {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select *  from parcelas as p   where p.tipoparcela = false and p.idcliente = ? ;");
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloParcela> listaPoridClienteNPagaVenda(long idCliente) {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select *  from parcelas as p inner join venda as vend on p.idvenda = vend.id  where vend.idcliente = ? and p.estatosparcela = false order by datavencimento");
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ModeloParcela> listaPoridClienteVenda(long idCliente) {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select p.*  from parcelas as p inner join venda as vend on p.idvenda = vend.id  where vend.idcliente = ? order by datavencimento");
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ModeloParcela> listaVendasVencidas() {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select p.*  from parcelas as p inner join venda as vend on p.idvenda = vend.id  where p.datavencimento < CURRENT_DATE and p.estatosparcela = false order by datavencimento");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ModeloParcela> listaVendasVencidasSemana() {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select p.*  from parcelas as p inner join venda as vend on p.idvenda = vend.id  where p.datavencimento BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '7 DAYS' AND p.estatosparcela = false   order by p.datavencimento");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ModeloParcela> listaLocacoesVencidas() {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select *  from parcelas as p   where p.tipoparcela = false and datavencimento < CURRENT_DATE;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ModeloParcela> listaLocacoesVencidasSemana() {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select *  from parcelas as p   where p.tipoparcela = false and datavencimento BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '7 DAYS' order by datavencimento");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public TableModel listaTabelaPorNumeroCheque(String text, Long idBanco) {
        if (text == null) {
            throw new IllegalStateException("Id da Numero de pedido nao deve ser nula.");
        }
        text = "%" + text + "%";

        try {

            String[] Colunas = new String[]{"ID do Venda", "Data da Venda", "Nota Fiscal"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select vend.id, vend.datavenda, vend.notafiscal from cheque as cheq "
                            + "inner join parcelas as parc on parc.id = cheq.idparcela "
                            + "inner join venda as vend on parc.idvenda = vend.id "
                            + "where cheq.numero LIKE ? and cheq.idbanco = ? ;");
            stmt.setString(1, text);
            stmt.setLong(2, idBanco);
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

    public List<ModeloParcela> listaPorNumeroCheque(String NumeroCheque, Long idBanco) {
        try {
            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select parc.*  from cheque as cheq inner join parcelas as parc on parc.id = cheq.idparcela "
                            + "where cheq.numero = ? and cheq.idbanco = ? and parc.tipoparcela = false;");
            stmt.setString(1, NumeroCheque);
            stmt.setLong(2, idBanco);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoPedido na lista
                parcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return parcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void parcelaPaga(Long idParcela) {

        String sql = "update parcelas set estatosparcela = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setLong(2, idParcela);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void parcelaNaoPaga(Long idParcela) {
        String sql = "update parcelas set estatosparcela = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, false);
            stmt.setLong(2, idParcela);
            System.out.println("oieeee");
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public float SomaTotalValorPagoLocação(Long idCliente) {

        String sql = "select sum(chec.valor) from parcelas as p inner join cheque as chec on chec.idparcela = p.id where p.tipoparcela = false and p.idcliente = ? and p.estatosparcela = true ; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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
    public float SomaTotalValorPagoLocaçãoVencidas(Long idCliente) {

        String sql = "select sum(chec.valor) from parcelas as p inner join cheque as chec on chec.idparcela = p.id where p.tipoparcela = false and p.idcliente = ? and p.estatosparcela = true and p.datavencimento <= CURRENT_DATE ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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

    List<ModeloParcela> buscaParcelasPorIdVenda(Long idVenda) {

        if (idVenda == null) {
            throw new IllegalStateException("Id da parcela nao deve ser nula.");
        }
        List<ModeloParcela> modeloParcelas = new ArrayList<>();

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where idvenda = ?");
            stmt.setLong(1, idVenda);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                modeloParcelas.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return modeloParcelas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public float SomaTotalValorPagoTotal() {
        String sql = "select sum(chec.valor) from parcelas as p inner join venda as vend on p.idvenda = vend.id inner join cheque as chec on chec.idparcela = p.id where p.estatosparcela = false and vend.idcliente = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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
    public float SomaTotalValorPagarVendaVencidasDevedores(Long idCliente) {
        String sql = "select sum(p.valor)  from parcelas as p inner join venda as vend on p.idvenda = vend.id  where vend.idcliente = ? and p.estatosparcela = false and p.datavencimento < CURRENT_DATE";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();
            
            ResultSet rs = stmt.executeQuery();
            
            float resultado = 0;
            if (rs.next()) {
                resultado = rs.getInt(1);
                System.err.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA!!! " + rs.getInt(1));
            }

            rs.close();
            stmt.close();

            return resultado;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
     public float SomaTotalValorPagoVendaVencidosDevedores(Long idCliente) {
        String sql = "select sum(chec.valor) from parcelas as p inner join venda as vend on p.idvenda = vend.id inner join cheque as chec on chec.idparcela = p.id where p.estatosparcela = false and vend.idcliente = ? and p.datavencimento <=  CURRENT_DATE - INTERVAL '10' day;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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
     public float SomaTotalValorASerpagoLocaçãoVencidasDevedores(Long idCliente) {
        String sql = "select sum(p.valor)  from parcelas as p   where p.tipoparcela = false and p.idcliente = ? and p.estatosparcela = false and p.datavencimento <=  CURRENT_DATE - INTERVAL '10' day; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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
     public float SomaTotalValorPagoLocaçãoVencidasDevedores(Long idCliente) {

        String sql = "select sum(chec.valor) from parcelas as p inner join cheque as chec on chec.idparcela = p.id where p.tipoparcela = false and p.idcliente = ? and p.estatosparcela = true and p.datavencimento <=  CURRENT_DATE - INTERVAL '10' day; ";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, idCliente);
            stmt.execute();

            ResultSet rs = stmt.executeQuery();
            float resultado = 0;
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



}
