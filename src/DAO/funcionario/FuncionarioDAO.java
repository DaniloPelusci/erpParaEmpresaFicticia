/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.funcionario;

import Controle.ModeloTabela;
import DAO.CidadeDAO;
import Modelo.Funcionario.ModeloFuncionario;
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
public class FuncionarioDAO {

    private Connection connection;

    public FuncionarioDAO(Connection connection) {
        this.connection = connection;
    }

    public void adiciona(ModeloFuncionario modeloFuncionario) {
        String sql = "insert into Funcionario (nome,idcargo,idbairro,idcidade,idestado,observacao,datanascimento,salario,rg,cpf,carteiratrabalho,endereco,dataadmissao,datademissao,telefone,celular) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setString(1, modeloFuncionario.getNome());
            stmt.setLong(2, modeloFuncionario.getCargo());
            stmt.setLong(3, modeloFuncionario.getBairro());
            stmt.setLong(4, modeloFuncionario.getCidade());
            stmt.setLong(5, modeloFuncionario.getEstado());
            stmt.setString(6, modeloFuncionario.getObservacao());
            stmt.setDate(7, modeloFuncionario.getDataNascimento());
            stmt.setBigDecimal(8, modeloFuncionario.getSalario());
            stmt.setString(9, modeloFuncionario.getRg());
            stmt.setString(10, modeloFuncionario.getCpf());
            stmt.setString(11, modeloFuncionario.getCarteiraTrabalho());
            stmt.setString(12, modeloFuncionario.getEndereco());
            stmt.setDate(13, modeloFuncionario.getDataAdmissao());
            stmt.setDate(14, modeloFuncionario.getDataDemissao());
            stmt.setString(15, modeloFuncionario.getTelefone());
            stmt.setString(16, modeloFuncionario.getCelular());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloFuncionario modeloFuncionario) {

        if (modeloFuncionario.getId() == null) {
            throw new IllegalStateException("Id da modeloFuncionario naoo deve ser nula.");
        }

        String sql = "delete from Funcionario where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloFuncionario.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloFuncionario modeloFuncionario) {
        String sql = "update Funcionario set nome = ?,idcargo,idbairro,idcidade, idestado = ?, observacao, dataNascimento, salario, rg,cpf,carteiraTrabalho, endereco, dataadmissao, dataDemissao,telefone,celular where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloFuncionario.getNome());
            stmt.setLong(2, modeloFuncionario.getCargo());
            stmt.setLong(3, modeloFuncionario.getBairro());
            stmt.setLong(4, modeloFuncionario.getCidade());
            stmt.setLong(5, modeloFuncionario.getEstado());
            stmt.setString(6, modeloFuncionario.getObservacao());
            stmt.setDate(7, modeloFuncionario.getDataNascimento());
            stmt.setBigDecimal(8, modeloFuncionario.getSalario());
            stmt.setString(9, modeloFuncionario.getRg());
            stmt.setString(10, modeloFuncionario.getCpf());
            stmt.setString(11, modeloFuncionario.getCarteiraTrabalho());
            stmt.setString(12, modeloFuncionario.getEndereco());
            stmt.setDate(13, modeloFuncionario.getDataAdmissao());
            stmt.setDate(14, modeloFuncionario.getDataDemissao());
            stmt.setString(15, modeloFuncionario.getTelefone());
            stmt.setString(16, modeloFuncionario.getCelular());
            stmt.setLong(17, modeloFuncionario.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloFuncionario> lista() {
        try {
            List<ModeloFuncionario> contas = new ArrayList<ModeloFuncionario>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from Funcionario order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloFuncionario na lista
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

            CidadeDAO cidadeDAO = new CidadeDAO(connection);

            String[] Colunas = new String[]{"ID", "nome", "Estado", "Cidade", "Bairro", "Data nascimento", "RG", "CPF", "Carteira de Trabalho", "Endereço", "Cargo", "Telefone", "Celular", "Observação"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from bairro inner join cidade on bairro.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select f.id,f.nome,e.nome,c.nome,b.nome, f.datanascimento, f.rg, f.cpf, f.carteiratrabalho, f.endereco, car.nome, f.telefone, f.celular, f.Observacao from funcionario as f inner join cidade as c on f.idcidade = c.id inner join bairro as b on f.idbairro = b.id inner join estado as e on f.idestado = e.id inner join cargo as car on f.idcargo = car.id  order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, Colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloFuncionario buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloFuncionario nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from funcionario where id = ?");
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

    public ModeloFuncionario buscaIdPorNome(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloFuncionario nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from Funcionario where nome = ?");
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

    private ModeloFuncionario populaModelo(ResultSet rs) throws SQLException {
        ModeloFuncionario modeloFuncionario = new ModeloFuncionario();

        modeloFuncionario.setId(rs.getLong("id"));
        modeloFuncionario.setNome(rs.getString("nome"));
        modeloFuncionario.setBairro(rs.getLong("idbairro"));
        modeloFuncionario.setCidade(rs.getLong("idcidade"));
        modeloFuncionario.setEstado(rs.getLong("idestado"));
        modeloFuncionario.setCargo(rs.getLong("idcargo"));
        modeloFuncionario.setObservacao(rs.getString("observacao"));
        modeloFuncionario.setDataNascimento(rs.getDate("datanascimento"));
        modeloFuncionario.setSalario(rs.getBigDecimal("salario"));
        modeloFuncionario.setRg(rs.getString("rg"));
        modeloFuncionario.setCpf(rs.getString("cpf"));
        modeloFuncionario.setCarteiraTrabalho(rs.getString("carteiratrabalho"));
        modeloFuncionario.setEndereco(rs.getString("endereco"));
        modeloFuncionario.setDataAdmissao(rs.getDate("dataadmissao"));
        modeloFuncionario.setDataDemissao(rs.getDate("datademissao"));
        modeloFuncionario.setTelefone(rs.getString("telefone"));
        modeloFuncionario.setCelular(rs.getString("celular"));

        return modeloFuncionario;
    }

    public ModeloTabela buscaLike(String nome) {
        nome = "%" + nome + "%";

        String sql = "select f.id,f.nome,e.nome,c.nome,b.nome, f.datanascimento, f.rg, f.cpf, f.carteiratrabalho, f.endereco, car.nome, f.telefone, f.celular, f.Observacao from funcionario as f inner join cidade as c on f.idcidade = c.id inner join bairro as b on f.idbairro = b.id inner join estado as e on f.idestado = e.id inner join cargo as car on f.idcargo = car.id where f.nome like ?  order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);

            stmt.execute();

            String[] colunas = new String[]{"ID", "nome", "RG", "CPF", "Carteira de Trabalho", "Endereço", "Cargo", "Telefone", "Celular", "Observação"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificacao(Long idFunc) throws SQLException {
        boolean resultBoolean = false;
        if (idFunc == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        PreparedStatement stmt = this.connection
                .prepareStatement("select id from funcionario where id = ?");
        stmt.setLong(1, idFunc);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            return true;
        }

        rs.close();
        stmt.close();

        return resultBoolean;

    }

}
