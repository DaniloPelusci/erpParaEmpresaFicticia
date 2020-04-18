/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.venda;

import Controle.ModeloTabela;
import Modelo.venda.ModeloCheque;
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
 * @author SERVIDOR
 */

// na verdade é pagamento.

public class ChequeDAO {

    private Connection connection;

    public ChequeDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloCheque modeloCheque) {
        String sql = "insert into cheque (numero,valor,idparcela,idbanco, datapaga ,idtipopagamento) values (?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloCheque.getNumeroCheque());
            stmt.setFloat(2, modeloCheque.getValor());
            
            stmt.setLong(3, modeloCheque.getIdparcela());
            stmt.setLong(4, modeloCheque.getIdBanco());
            stmt.setDate(5, (java.sql.Date) modeloCheque.getDatapaga());
            stmt.setLong(6, modeloCheque.getIdTipoPagamento());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    
    

    public void remove(Long idCheque) {

        if (idCheque == null) {
            throw new IllegalStateException("Id da cheque naoo deve ser nula.");
        }

        String sql = "delete from cheque where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idCheque);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloCheque modeloCheque) {
        String sql = "update cheque set numero = ?, valor = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloCheque.getNumeroCheque());
            stmt.setFloat(2, modeloCheque.getValor());
           

            stmt.setLong(3, modeloCheque.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloCheque> lista() {
        try {
            List<ModeloCheque> cheques = new ArrayList<ModeloCheque>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cheque order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoProduto na lista
                cheques.add(populaObjeto(rs));
            }

            rs.close();
            stmt.close();

            return cheques;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Construindo a tabela.
    public ModeloTabela listaTabelaPorIDParcela(Long idparcela) {
        try {

            String[] Colunas = new String[]{"ID", "Numero do cheque", "Valor do Pagamento","Nome Banco","Tipo de Pagamento","Data paga"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from tipoproduto inner join cidade on tipoproduto.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select c.id, c.numero, c.valor, banc.nome, tpag.nome, c.datapaga from cheque as c inner join banco as banc on c.idbanco = banc.id   inner join tipopagamento as tpag on c.idtipopagamento = tpag.id where  idparcela = ? order by c.id;");
            stmt.setLong(1, idparcela);
            ResultSet rs = stmt.executeQuery();
            TipoPagamentoDAO tipoPagamentoDAO = new TipoPagamentoDAO(connection);
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            while (rs.next()) {
                // adiciona a modeloTipoProduto na lista
                if(rs.getDate(6)!= null){
                dados.add(new Object[]{rs.getInt(1), rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getString(5),format.format(rs.getDate(6))});
                }else{
                    dados.add(new Object[]{rs.getInt(1), rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getString(5),"00/00/0000"});
                
                }
                }

            rs.close();
            stmt.close();
            ModeloTabela tipoproduto = new ModeloTabela(dados, Colunas);
            return tipoproduto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloCheque buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloTipoProduto nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cheque where id = ?");
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

    public ModeloTabela buscaNumero(String nome) {
        nome = "%" + nome + "%";
        String sql = "select id, nome from chque where numero like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] Colunas = new String[]{"ID", "Nume do cheque", "Valor do cheque"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloTipoProduto na lista

               dados.add(new Object[]{rs.getInt("id"), rs.getString("numero"),rs.getFloat("valor")});
           
            }

            rs.close();
            stmt.close();
            ModeloTabela tipoproduto = new ModeloTabela(dados, Colunas);
            return tipoproduto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloCheque populaObjeto(ResultSet rs) throws SQLException {
        ModeloCheque modeloCheque = new ModeloCheque();

        modeloCheque.setId(rs.getLong("id"));
        modeloCheque.setNumeroCheque(rs.getString("numero"));
        modeloCheque.setIdparcela(rs.getLong("idparcela"));
        modeloCheque.setValor(rs.getFloat("valor"));

        return modeloCheque;
    }

    public void removeIdParcela(Long idParcela) {
        if (idParcela == null) {
            throw new IllegalStateException("Id da cheque naoo deve ser nula.");
        }

        String sql = "delete from cheque where idparcela = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idParcela);
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
       }

}
