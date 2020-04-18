/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.fornecedor;

import Controle.ModeloTabela;
import Modelo.Fornecedor.ModeloFornecedor;
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
public class FornecedorDAO {

    private Connection connection;

    public FornecedorDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloFornecedor modeloFornecedor) {
        String sql = "insert into fornecedor (idtipofornecedor,idbairro,idcidade,idestado,nome,cep,endereco,telefone,telefone2,fax,celular,observacao,nomefantasia,cnpj,inscricaomunicipal) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloFornecedor.getTipo());
            stmt.setLong(2, modeloFornecedor.getBairro());
            stmt.setLong(3, modeloFornecedor.getCidade());
            stmt.setLong(4, modeloFornecedor.getEstado());
            stmt.setString(5, modeloFornecedor.getNome());
            stmt.setString(6, modeloFornecedor.getCep());
            stmt.setString(7, modeloFornecedor.getEndereco());
            stmt.setString(8, modeloFornecedor.getTelefone());
            stmt.setString(9, modeloFornecedor.getTelefone1());
            stmt.setString(10, modeloFornecedor.getFax());
            stmt.setString(11, modeloFornecedor.getCelular());
            stmt.setString(12, modeloFornecedor.getObservacao());
            stmt.setString(13, modeloFornecedor.getNomeFantasia());
            stmt.setString(14, modeloFornecedor.getCnpj());
            stmt.setString(15, modeloFornecedor.getInscricaoMunicipal());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloFornecedor modeloFornecedor) {

        if (modeloFornecedor.getId() == null) {
            throw new IllegalStateException("Id da modeloFornecedor naoo deve ser nula.");
        }

        String sql = "delete from fornecedor where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloFornecedor.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloFornecedor modeloFornecedor) {

        String sql = "update fornecedor set idtipofornecedor=?, idbairro=?, idcidade=?,idestado=?, nome=?, cep=?, endereco=?, telefone=? ,telefone1=? ,fax=? ,celular=? ,observacao=? ,nomefantasia=? ,cnpj=? ,inscricaomunicipal=? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloFornecedor.getTipo());
            stmt.setLong(2, modeloFornecedor.getBairro());
            stmt.setLong(3, modeloFornecedor.getCidade());
            stmt.setLong(4, modeloFornecedor.getEstado());
            stmt.setString(5, modeloFornecedor.getNome());
            stmt.setString(6, modeloFornecedor.getCep());
            stmt.setString(7, modeloFornecedor.getEndereco());
            stmt.setString(8, modeloFornecedor.getTelefone());
            stmt.setString(9, modeloFornecedor.getTelefone1());
            stmt.setString(10, modeloFornecedor.getFax());
            stmt.setString(11, modeloFornecedor.getCelular());
            stmt.setString(12, modeloFornecedor.getObservacao());
            stmt.setString(13, modeloFornecedor.getNomeFantasia());
            stmt.setString(14, modeloFornecedor.getCnpj());
            stmt.setString(15, modeloFornecedor.getInscricaoMunicipal());
            stmt.setLong(16, modeloFornecedor.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloFornecedor> lista() {
        try {
            List<ModeloFornecedor> contas = new ArrayList<ModeloFornecedor>();
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

    public ModeloFornecedor buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloFornecedor nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from fornecedor where id = ?");
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

    public ModeloFornecedor buscaIdPorNome(String nome) {

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

    public ModeloTabela buscaLike(String nome) {
        nome = "%" + nome + "%";
        //
        String sql = "select f.id,f.nome,f.nomefantasia,t.nome,c.nome,e.nome,b.nome,f.telefone, f.telefone1 from fornecedor as f inner join cidade as c on f.idcidade = c.id inner join bairro as b on f.idbairro = b.id inner join estado as e on f.idestado = e.id inner join tipo as t on f.idtipofornecedor = t.id  where f.nome like ? order by id;";
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

    private ModeloFornecedor populaModelo(ResultSet rs) throws SQLException {
        ModeloFornecedor modeloFornecedor = new ModeloFornecedor();

        modeloFornecedor.setId(rs.getLong("id"));
        modeloFornecedor.setTipo(rs.getLong("idtipofornecedor"));
        modeloFornecedor.setBairro(rs.getLong("idbairro"));
        modeloFornecedor.setCidade(rs.getLong("idcidade"));
        modeloFornecedor.setEstado(rs.getLong("idestado"));
        modeloFornecedor.setNome(rs.getString("nome"));
        modeloFornecedor.setCep(rs.getString("cep"));
        modeloFornecedor.setEndereco(rs.getString("endereco"));
        modeloFornecedor.setTelefone(rs.getString("telefone"));
        modeloFornecedor.setTelefone1(rs.getString("telefone2"));
        modeloFornecedor.setFax(rs.getString("fax"));
        modeloFornecedor.setCelular(rs.getString("celular"));
        modeloFornecedor.setObservacao(rs.getString("observacao"));
        modeloFornecedor.setNomeFantasia(rs.getString("nomefantasia"));
        modeloFornecedor.setCnpj(rs.getString("cnpj"));
        modeloFornecedor.setInscricaoMunicipal(rs.getString("inscricaomunicipal"));

        return modeloFornecedor;
    }

}
