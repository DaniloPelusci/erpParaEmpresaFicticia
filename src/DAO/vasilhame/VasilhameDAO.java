/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.vasilhame;

import Controle.ModeloTabela;
import Modelo.Vale.ModeloVale;
import Modelo.Vasilhame.Modeloauxiliarvasilhame;
import Modelo.venda.ModeloParcela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;

/**
 *
 * @author E.B.O 2
 */
public class VasilhameDAO {

    private Connection connection;

    public VasilhameDAO(Connection connection) {
        this.connection = connection;
    }

    public Modeloauxiliarvasilhame busca(Long idVale) throws SQLException {
        PreparedStatement stmt = this.connection
                .prepareStatement("select vale.id, vale.dataemissao, vale.valor, vale.observacao,vale.idfuncionario from vale as vale "
                        + "inner join funcionario as func on vale.idfuncionario = func.id "
                        + "where vale.id = ?;");
        stmt.setLong(1, idVale);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return populaObjeto(rs);
        }
        rs.close();
        stmt.close();
        return null;

    }

    private Modeloauxiliarvasilhame populaObjeto(ResultSet rs) throws SQLException {
        Modeloauxiliarvasilhame modeloauxiliarvasilhame = new Modeloauxiliarvasilhame();

        modeloauxiliarvasilhame.setIdCliente(rs.getLong("id"));
        modeloauxiliarvasilhame.setNome(rs.getString("nome"));
        modeloauxiliarvasilhame.setQuantidade(rs.getInt("quantidade"));

        return modeloauxiliarvasilhame;
    }

    public TableModel listaValePorFuncionario(Long idFuncionario) {
        try {

            String[] Colunas = new String[]{"ID Vale", "Data de Emissao", "Valor do vale", "Observanção"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select vale.id, vale.dataemissao, vale.valor, vale.observacao from vale as vale inner join funcionario as func on vale.idfuncionario = func.id where func.id = ? order by vale.dataemissao;");
            stmt.setLong(1, idFuncionario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getString(2), rs.getInt(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela vales = new ModeloTabela(dados, Colunas);
            return vales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public TableModel listaLastroPorProduto(Long idproduto) {
        try {

            String[] Colunas = new String[]{"ID Vale", "Data de Emissao", "Valor do vale", "Observanção"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("select cli.id, cli.nome, sum(rela.quantidade) \n"
                            + "from \n"
                            + "cliente as cli inner join\n"
                            + "pedido as ped on cli.id = ped.idcliente inner join\n"
                            + "lastro as rela on rela.idpedido = ped.id inner join\n"
                            + "produto as prod on prod.id = rela.idproduto\n"
                            + "where\n"
                            + " prod.id = ? and rela.idtipoacao = '1' group by cli.id order by cli.id; ");
            stmt.setLong(1, idproduto);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloPedido na lista

                dados.add(new Object[]{rs.getLong(1), rs.getString(2), rs.getInt(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela vales = new ModeloTabela(dados, Colunas);
            return vales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifIcarLocacao(Long id) {
        try {
            boolean resultBoolean = false;

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from parcelas where tipoparcela = false and idCliente = ? AND EXTRACT(MONTH FROM datavencimento) = EXTRACT(MONTH FROM CURRENT_DATE) order by id;");
            stmt.setLong(1, id);
            Long id1 = null;
            ResultSet rs = stmt.executeQuery();
            int cont = 0;

            while (rs.next()) {
                id1 = rs.getLong("id");

                if (id1 != null) {

                    cont++;
                }

            }

            rs.close();
            stmt.close();
            if (cont > 0) {

                resultBoolean = true;
            }
            return resultBoolean;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<Modeloauxiliarvasilhame> listaEmprestimo() {
        try {
            List<Modeloauxiliarvasilhame> bairros = new ArrayList<Modeloauxiliarvasilhame>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select clie.id,trim(clie.nome)as nome, sum(pp.quantidade) as quantidade from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id inner join cliente as clie on ped.idcliente = clie.id where pp.idtipoacao = '1' and prod.idtipoproduto = '2' GROUP BY clie.id ORDER BY clie.id ;");

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

    public List<Modeloauxiliarvasilhame> listaDevolucao() {
        try {
            List<Modeloauxiliarvasilhame> bairros = new ArrayList<Modeloauxiliarvasilhame>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select clie.id,clie.nome, sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id inner join cliente as clie on ped.idcliente = clie.id where pp.idtipoacao = '2' and prod.idtipoproduto = '2' and ped.idtipopedido = '3' GROUP BY clie.id ORDER BY clie.id ;");

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

    public List<Modeloauxiliarvasilhame> listaVendaLoja() {
        try {
            List<Modeloauxiliarvasilhame> bairros = new ArrayList<Modeloauxiliarvasilhame>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select clie.id,clie.nome, sum(pp.quantidade) from lastro as pp inner join pedido as ped on pp.idpedido = ped.id  inner join produto as prod on pp.idproduto = prod.id inner join cliente as clie on ped.idcliente = clie.id  where pp.idtipoacao = '3' and prod.idtipoproduto = '3' GROUP BY clie.id ORDER BY clie.id ;");

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

    public int adiciona(ModeloVale modeloVale) {
        String sql = "insert into vale (dataemissao, idfuncionario, observacao, valor) values (?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setDate(1, modeloVale.getDataEmissao());
            stmt.setLong(2, modeloVale.getIdFuncionario());
            stmt.setString(3, modeloVale.getObservacao());
            stmt.setBigDecimal(4, modeloVale.getValor());
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            int idObjeto = 0;
            while (rs.next()) {

                idObjeto = rs.getInt(1);

            }
            return idObjeto;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloVale modeloVale) {
        if (modeloVale.getId() == null) {
            throw new IllegalStateException("Id da vale naoo deve ser nula.");
        }

        String sql = "delete from vale where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloVale.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void listaLastroGravar(Long idCliente, int resultCilindros) {
     String sql = "INSERT INTO public.listadelastro(idcliente, result) VALUES ( ?, ?) ;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idCliente);
            stmt.setLong(2, resultCilindros);
            
            stmt.execute();
            ResultSet rs = stmt.getGeneratedKeys();
            int idObjeto = 0;
            while (rs.next()) {

                idObjeto = rs.getInt(1);

            }
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }  
    }

}
