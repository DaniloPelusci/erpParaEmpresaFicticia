/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.boleto;

import Controle.ModeloTabela;
import Modelo.boleto.ModeloOcorrenciaBoleto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author usuario
 */
public class OcorrenciaDAO {
    private Connection connection;

    public OcorrenciaDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloOcorrenciaBoleto modeloOcorrenciaBoleto) {
        String sql = "insert into ocorrencia (nomeocorrencia, codocorrencia) values (?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloOcorrenciaBoleto.getNome());
            stmt.setLong(2, modeloOcorrenciaBoleto.getCodOcorrencia());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloOcorrenciaBoleto modeloOcorrenciaBoleto) {

        if (modeloOcorrenciaBoleto.getId() == null) {
            throw new IllegalStateException("Id da modeloEstado não deve ser nula.");
        }

        String sql = "delete from ocorrencia where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloOcorrenciaBoleto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloOcorrenciaBoleto modeloOcorrenciaBoleto) {
        String sql = "update ocorrencia set nome = ?, codocorrencia = ? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloOcorrenciaBoleto.getNome());
            stmt.setLong(2, modeloOcorrenciaBoleto.getCodOcorrencia());

            stmt.setLong(3, modeloOcorrenciaBoleto.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloOcorrenciaBoleto> lista() {
        try {
            List<ModeloOcorrenciaBoleto> estado = new ArrayList<ModeloOcorrenciaBoleto>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, trim(nome) as nome, codocorrencia from ocorrencia order by nome");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloEstado na lista
                estado.add(populaEstado(rs));
            }

            rs.close();
            stmt.close();

            return estado;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Construindo a tabela.
    public ModeloTabela listaTabela() {
        try {

            

            String[] Colunas = new String[]{"ID", "Nome", "codocorrencia"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from estado inner join cidade on estado.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from ocorrencia order by id ");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloEstado na lista

                dados.add(new Object[]{rs.getInt("id"), rs.getString("nome"), rs.getString("codocorrencia")});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet listaResult() {
        try {

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from ocorrencia order by id");

            ResultSet rs = stmt.executeQuery();

            stmt.close();

            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloOcorrenciaBoleto buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloEstado nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id,trim(nome) as nome, trim(codocorrencia) as codocorrencia from ocorrencia where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaEstado(rs);
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
     public ModeloOcorrenciaBoleto buscaPorliquidacao(String ocorrencia) {

        

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from ocorrencia where codocorrencia = ?;");
            stmt.setString(1,ocorrencia);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return populaEstado(rs);
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
        //
        String sql = "select id, nome, ocorrencia from ocorrencia where nome like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "codocorrencia"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ModeloOcorrenciaBoleto populaEstado(ResultSet rs) throws SQLException {
        ModeloOcorrenciaBoleto modeloOcorrenciaBoleto = new ModeloOcorrenciaBoleto();

        modeloOcorrenciaBoleto.setId(rs.getLong("id"));
       modeloOcorrenciaBoleto.setNome(rs.getString("nome"));
        modeloOcorrenciaBoleto.setCodOcorrencia(rs.getInt("codocorrencia"));

        return modeloOcorrenciaBoleto;
    }
    
}
