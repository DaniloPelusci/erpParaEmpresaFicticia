/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.boleto;

import Modelo.Pedido.ModeloPedido;
import Modelo.boleto.ModeloBoleto;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class BoletoDao {

    private Connection connection;

    public BoletoDao(Connection connection) {
        this.connection = connection;
    }

    public void remove(Long idpedido) {

        if (idpedido == null) {
            throw new IllegalStateException("Id da modeloPedido naoo deve ser nula.");
        }
        String sql = "DELETE FROM public.boleto WHERE idpedido = ?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, String.valueOf(idpedido));
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloBoleto buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da boleto nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from boleto where idpedido = ?");
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

    public List<ModeloBoleto> listaNaoGerados() {
        try {
            List<ModeloBoleto> bairros = new ArrayList<ModeloBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idpedido, idliquidacao, nossonumero,idpedido,dataocorrencia,trim(numerodocumento) as numerodocumento,dataquitacaocredito,dataemissao"
                            + ",datavencimento,valortitulo,valorpagotitulo,emitido from boleto where emitido = false order by id;");

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

    private ModeloBoleto populaObjeto(ResultSet rs) throws SQLException {
        ModeloBoleto modeloBoleto = new ModeloBoleto();

        modeloBoleto.setId(rs.getLong("id"));
        modeloBoleto.setIdPedido(rs.getLong("idPedido"));
        modeloBoleto.setCodLiquidacao(rs.getLong("idliquidacao"));
        modeloBoleto.setNossoNumero(rs.getLong("nossonumero"));
        modeloBoleto.setIdPedido(rs.getLong("idpedido"));
        modeloBoleto.setDataocorrencia(rs.getDate("dataocorrencia"));
        modeloBoleto.setNumDocumento(rs.getString("numerodocumento"));
        modeloBoleto.setDataLiquidacaoCredito(rs.getDate("dataquitacaocredito"));
        modeloBoleto.setDataEmissao(rs.getDate("dataemissao"));
        modeloBoleto.setVencimento(rs.getDate("datavencimento"));
        modeloBoleto.setValorTitulo(rs.getBigDecimal("valortitulo"));
        modeloBoleto.setValorPagodotitulo(rs.getBigDecimal("valorpagotitulo"));
        modeloBoleto.setEmitido(rs.getBoolean("emitido"));

        return modeloBoleto;
    }

    public void adiciona(ModeloBoleto modeloBoleto) {
        String sql = "insert into boleto(  idocorrencia,idliquidacao,nossonumero,idpedido,dataocorrencia,numerodocumento,dataquitacaocredito"
                + ",dataemissao,datavencimento,valortitulo,valorpagotitulo,emitido) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            System.out.println(modeloBoleto.getCodOcorrencia());
            stmt.setLong(1, modeloBoleto.getCodOcorrencia());
            stmt.setLong(2, modeloBoleto.getCodLiquidacao());
            stmt.setLong(3, modeloBoleto.getNossoNumero());
            stmt.setLong(4, modeloBoleto.getIdPedido());
            stmt.setDate(5, modeloBoleto.getDataocorrencia());
            stmt.setString(6, modeloBoleto.getNumDocumento());
            stmt.setDate(7, modeloBoleto.getDataLiquidacaoCredito());
            stmt.setDate(8, modeloBoleto.getDataEmissao());
            stmt.setDate(9, modeloBoleto.getVencimento());
            stmt.setBigDecimal(10, modeloBoleto.getValorTitulo());
            stmt.setBigDecimal(11, modeloBoleto.getValorPagodotitulo());

            stmt.setBoolean(12, modeloBoleto.isEmitido());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean validaBoleto(String numeroGrafica) throws SQLException {

        //
        String sql = "SELECT   boleto.id FROM   boleto WHERE   numerodocumento = ?;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, numeroGrafica);

        stmt.execute();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // adiciona a modeloBairro na lista
                System.err.println("acertou");
                retorno = true;

            }
        }
        stmt.close();

        return retorno;
    }

    public boolean validaBoletoNossoNumero(ModeloBoleto modeloBoleto) throws SQLException {

        //
        String sql = "SELECT  * FROM   public.boleto  where  boleto.nossonumero = ?";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setLong(1, modeloBoleto.getNossoNumero());

        stmt.execute();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // adiciona a modeloBairro na lista
                System.err.println("acertou");
                retorno = true;

            }
        }
        stmt.close();

        return retorno;
    }

    public void adicionaInicio(ModeloBoleto modeloBoleto) {
        String sql = "insert into boleto( idpedido,valortitulo,dataemissao,datavencimento,numerodocumento,emitido,nossonumero ) values (?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloBoleto.getIdPedido());
            stmt.setBigDecimal(2, modeloBoleto.getValorTitulo());
            stmt.setDate(3, modeloBoleto.getDataEmissao());
            stmt.setDate(4, modeloBoleto.getVencimento());
            stmt.setString(5, modeloBoleto.getNumDocumento());

            stmt.setBoolean(6, false);
            stmt.setLong(7, modeloBoleto.getNossoNumero());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloBoleto> listaBoletos() {
        try {
            List<ModeloBoleto> bairros = new ArrayList<ModeloBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT id, idocorrencia, idliquidacao, nossonumero, idparcela, dataocorrencia, \n"
                            + "                                  numerodocumento, dataquitacaocredito, datavencimento, dataemissao, \n"
                            + "                                   valortitulo, valorpagotitulo, emitido, idpedido\n"
                            + "                              FROM boleto order by emitido, datavencimento ,  idpedido ;");

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

    public void altera(ModeloBoleto modeloBoleto) {
        String sql = "UPDATE public.boleto\n"
                + "   SET  idocorrencia=?, idliquidacao=?, nossonumero=?, idpedido=?, \n"
                + "       dataocorrencia=?, dataquitacaocredito=?, datavencimento=?, \n"
                + "       valortitulo=?, valorpagotitulo=?, emitido=?\n"
                + " WHERE numerodocumento =?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            System.out.println(modeloBoleto.getCodOcorrencia());
            stmt.setLong(1, modeloBoleto.getCodOcorrencia());
            stmt.setLong(2, modeloBoleto.getCodLiquidacao());
            stmt.setLong(3, modeloBoleto.getNossoNumero());
            stmt.setLong(4, modeloBoleto.getIdPedido());
            stmt.setDate(5, modeloBoleto.getDataocorrencia());
            stmt.setDate(6, modeloBoleto.getDataLiquidacaoCredito());

            stmt.setDate(7, modeloBoleto.getVencimento());
            stmt.setBigDecimal(8, modeloBoleto.getValorTitulo());
            stmt.setBigDecimal(9, modeloBoleto.getValorPagodotitulo());
            stmt.setBoolean(10, modeloBoleto.isEmitido());

            stmt.setString(11, modeloBoleto.getNumDocumento());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraPrimeiroBoleto() {
        String sql = "UPDATE public.boleto SET nossonumero=? WHERE id = '1468'";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, pegarUltimoNumero());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void novoNossoNumero(String nossoNumero) {
        String sql = "UPDATE public.boleto SET nossonumero=? WHERE id = '1468'";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setInt(1, Integer.parseInt(nossoNumero));

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraEmissão(ModeloBoleto modeloBoleto) {

        String sql = "UPDATE public.boleto\n"
                + "   SET  emitido=?"
                + " WHERE numerodocumento =?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            System.out.println(modeloBoleto.getCodOcorrencia());

            stmt.setBoolean(1, true);
            stmt.setString(2, modeloBoleto.getNumDocumento());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adicionaLocacao(ModeloBoleto modeloBoleto) {

        String sql = "insert into boleto(  idocorrencia,idliquidacao,nossonumero,dataocorrencia,numerodocumento,dataquitacaocredito"
                + ",dataemissao,datavencimento,valortitulo,valorpagotitulo,emitido) values (?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            System.out.println(modeloBoleto.getCodOcorrencia());
            stmt.setLong(1, modeloBoleto.getCodOcorrencia());
            stmt.setLong(2, modeloBoleto.getCodLiquidacao());
            stmt.setLong(3, modeloBoleto.getNossoNumero());

            stmt.setDate(4, modeloBoleto.getDataocorrencia());
            stmt.setString(5, modeloBoleto.getNumDocumento());
            stmt.setDate(6, modeloBoleto.getDataLiquidacaoCredito());
            stmt.setDate(7, modeloBoleto.getDataEmissao());
            stmt.setDate(8, modeloBoleto.getVencimento());
            stmt.setBigDecimal(9, modeloBoleto.getValorTitulo());
            stmt.setBigDecimal(10, modeloBoleto.getValorPagodotitulo());

            stmt.setBoolean(11, modeloBoleto.isEmitido());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void alteraLocacao(ModeloBoleto modeloBoleto) {

        String sql = "UPDATE public.boleto\n"
                + "   SET  idocorrencia=?, idliquidacao=?, nossonumero=?,  \n"
                + "       dataocorrencia=?, dataquitacaocredito=?, datavencimento=?, \n"
                + "       valortitulo=?, valorpagotitulo=?, emitido=?\n"
                + " WHERE numerodocumento =?;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            System.out.println(modeloBoleto.getCodOcorrencia());
            stmt.setLong(1, modeloBoleto.getCodOcorrencia());
            stmt.setLong(2, modeloBoleto.getCodLiquidacao());
            stmt.setLong(3, modeloBoleto.getNossoNumero());

            stmt.setDate(4, modeloBoleto.getDataocorrencia());
            stmt.setDate(5, modeloBoleto.getDataLiquidacaoCredito());

            stmt.setDate(6, modeloBoleto.getVencimento());
            stmt.setBigDecimal(7, modeloBoleto.getValorTitulo());
            stmt.setBigDecimal(8, modeloBoleto.getValorPagodotitulo());
            stmt.setBoolean(9, modeloBoleto.isEmitido());

            stmt.setString(10, modeloBoleto.getNumDocumento());

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<ModeloBoleto> listaBoletosEmitidosHoje() {

        try {
            List<ModeloBoleto> bairros = new ArrayList<ModeloBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT id, idocorrencia, idliquidacao, nossonumero, idpedido, dataocorrencia, \n"
                            + "       trim(numerodocumento)as numerodocumento, dataquitacaocredito, datavencimento, dataemissao, \n"
                            + "       valortitulo, valorpagotitulo, emitido, idparcela\n"
                            + "  FROM public.boleto where dataemissao = CURRENT_DATE order by datavencimento DESC;");

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

    public List<ModeloBoleto> listaNaoGeradosespEcificos() {

        try {
            List<ModeloBoleto> bairros = new ArrayList<ModeloBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idpedido, idliquidacao, nossonumero,idpedido,dataocorrencia,trim(numerodocumento) as numerodocumento,dataquitacaocredito,dataemissao ,datavencimento,valortitulo,valorpagotitulo,emitido from boleto where emitido = false order by nossonumero ");

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

    public List<ModeloBoleto> gerarRemessaDiasEspecificos(Date inicio, Date fim) {

        try {
            List<ModeloBoleto> bairros = new ArrayList<ModeloBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idpedido, idliquidacao, nossonumero,idpedido,dataocorrencia,trim(numerodocumento) as numerodocumento,dataquitacaocredito,dataemissao ,datavencimento,valortitulo,valorpagotitulo,emitido "
                            + "from boleto where emitido = false "
                            + "and datavencimento BETWEEN ? AND ? order by nossonumero;");
            stmt.setDate(1, inicio);
            stmt.setDate(2, fim);
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

    public List<ModeloBoleto> gerarRemessaAll() {

        try {
            List<ModeloBoleto> bairros = new ArrayList<ModeloBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idpedido, idliquidacao, nossonumero,idpedido,dataocorrencia,trim(numerodocumento) as numerodocumento,dataquitacaocredito,dataemissao ,datavencimento,valortitulo,valorpagotitulo,emitido "
                            + "from boleto where emitido = false "
                            + " order by idpedido;");

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

    public int pegarUltimoNumero() {

        try {
            int result = 0;
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT MAX(nossonumero) "
                            + "FROM public.boleto;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista
                result = rs.getInt(1);
            }

            rs.close();
            stmt.close();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean validaBoletoParcela(Long id) throws SQLException {

        //
        String sql = "SELECT  * FROM   public.boleto  where  boleto.idpedido = ?";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, String.valueOf(id));

        stmt.execute();

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                // adiciona a modeloBairro na lista
                System.err.println("acertou");
                retorno = true;

            }
        }
        stmt.close();

        return retorno;
    }

    public void alteraEnvioBoleto(String nb, boolean emitido) {

        String sql = "UPDATE public.boleto SET emitido=? where numerodocumento = ?;";
        PreparedStatement stmt;
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, emitido);
            stmt.setString(2, nb);
            stmt.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }
    }

    public List<ModeloBoleto> listaMaiorQueId() {
        try {
            List<ModeloBoleto> bairros = new ArrayList<ModeloBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT id, idocorrencia, idliquidacao, nossonumero, idpedido, dataocorrencia, \n"
                            + "       numerodocumento, dataquitacaocredito, datavencimento, dataemissao, \n"
                            + "       valortitulo, valorpagotitulo, emitido, idparcela\n"
                            + "  FROM public.boleto where id > '2381' and id <= '2432' order by id ;");

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

    public ModeloBoleto buscarPorNumeroDocumento(long parseLong) {
    
        try {
            String numerododocumento= "%"+parseLong+"%";
            ModeloBoleto modeloBoleto = new ModeloBoleto();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idpedido, idliquidacao, nossonumero,idpedido,dataocorrencia,trim(numerodocumento) as numerodocumento,dataquitacaocredito,dataemissao ,datavencimento,valortitulo,valorpagotitulo,emitido from boleto where numerodocumento like ?;");
            stmt.setString(1, numerododocumento);
            
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista
                modeloBoleto = populaObjeto(rs);
            }

            rs.close();
            stmt.close();

            return modeloBoleto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
    }

}
