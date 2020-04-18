/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.cliente;

import Controle.ModeloTabela;
import Modelo.Cliente.ModeloCliente;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.TableModel;
import logica.LogicasPedido;

/**
 *
 * @author dan-pelusci
 */
public class ClienteDAO {

    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public BigDecimal valorultimaLocacao(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select valorlocacao from cliente where id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return rs.getBigDecimal("valorlocacao");
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void atualizarLocacao(Long idCliente, BigDecimal valor) {
        try {
            String sql = "update cliente set valorlocacao=?  where id = ?";
            PreparedStatement stmt;

            stmt = connection.prepareStatement(sql);

            stmt.setLong(2, idCliente);
            stmt.setBigDecimal(1, valor);

            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adiciona(ModeloCliente modeloCliente) {
        String sql = "insert into cliente (idramoatividade,idbairro,idcidade,idestado,nome,contato,cep,telefone,telefone2,fax,celular,identidade,cpf,observacao,nomefantasia,cnpj,inscricaomunicipal,datacadastro,endereco, inscricaoestadual, email,idrotas,boleto) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloCliente.getRamoAtividade());
            stmt.setLong(2, modeloCliente.getBairro());
            stmt.setLong(3, modeloCliente.getCidade());
            stmt.setLong(4, modeloCliente.getEstado());
            stmt.setString(5, modeloCliente.getNome());
            stmt.setString(6, modeloCliente.getContato());
            stmt.setString(7, modeloCliente.getCep());
            stmt.setString(8, modeloCliente.getTelefone());
            stmt.setString(9, modeloCliente.getTelefone1());
            stmt.setString(10, modeloCliente.getFax());
            stmt.setString(11, modeloCliente.getCelular());
            stmt.setString(12, modeloCliente.getRg());
            stmt.setString(13, modeloCliente.getCpf());
            stmt.setString(14, modeloCliente.getObservacao());
            stmt.setString(15, modeloCliente.getNomeFantasia());
            stmt.setString(16, modeloCliente.getCnpj());
            stmt.setString(17, modeloCliente.getInscricaoMunicipal());

            stmt.setDate(18, modeloCliente.getDataCadastro());
            stmt.setString(19, modeloCliente.getEndereco());
            stmt.setString(20, modeloCliente.getInscricaoEstadual());
            stmt.setString(21, modeloCliente.geteMail());
            stmt.setLong(22, modeloCliente.getRota());
            stmt.setBoolean(23, modeloCliente.isBoleto());

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void remove(ModeloCliente modeloCliente) {

        if (modeloCliente.getId() == null) {
            throw new IllegalStateException("Id da modeloCliente naoo deve ser nula.");
        }

        String sql = "delete from cliente where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloCliente.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void altera(ModeloCliente modeloCliente) {

        String sql = "update cliente set idramoatividade=? ,idbairro=? ,idcidade=? ,idestado=? ,nome=? ,contato=? ,cep=? ,telefone=? ,telefone2=? ,fax=? ,celular=? ,identidade=? ,cpf=? ,observacao=? ,nomefantasia=? ,cnpj=? ,inscricaomunicipal=? ,datacadastro = ?, endereco =?,inscricaoestadual=?, email=?, idrotas=? , boleto=? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, modeloCliente.getRamoAtividade());
            stmt.setLong(2, modeloCliente.getBairro());
            stmt.setLong(3, modeloCliente.getCidade());
            stmt.setLong(4, modeloCliente.getEstado());
            stmt.setString(5, modeloCliente.getNome());
            stmt.setString(6, modeloCliente.getContato());
            stmt.setString(7, modeloCliente.getCep());
            stmt.setString(8, modeloCliente.getTelefone());
            stmt.setString(9, modeloCliente.getTelefone1());
            stmt.setString(10, modeloCliente.getFax());
            stmt.setString(11, modeloCliente.getCelular());
            stmt.setString(12, modeloCliente.getRg());
            stmt.setString(13, modeloCliente.getCpf());
            stmt.setString(14, modeloCliente.getObservacao());
            stmt.setString(15, modeloCliente.getNomeFantasia());
            stmt.setString(16, modeloCliente.getCnpj());
            stmt.setString(17, modeloCliente.getInscricaoMunicipal());
            stmt.setDate(18, (Date) modeloCliente.getDataCadastro());
            stmt.setString(19, modeloCliente.getEndereco());
            stmt.setString(20, modeloCliente.getInscricaoEstadual());
            stmt.setString(21, modeloCliente.geteMail());
            stmt.setLong(22, modeloCliente.getRota());
            stmt.setBoolean(23, modeloCliente.isBoleto());
            stmt.setLong(24, modeloCliente.getId());
            stmt.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloCliente> lista() {
        try {
            List<ModeloCliente> contas = new ArrayList<ModeloCliente>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cliente order by id");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCliente na lista
                contas.add(populaModelo(rs));
            }

            rs.close();
            stmt.close();

            return contas;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<ModeloCliente> listaBoleto() {
        try {
            List<ModeloCliente> contas = new ArrayList<ModeloCliente>();
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT *  FROM public.cliente  where boleto = true order by id;");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloCliente na lista
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

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Ramoatividade", "Estado", "Cidade", "Bairro", "Telefone", "Telefone Secundario"};
            ArrayList dados = new ArrayList();

            // tem que estudar melhor o jeito de fazer esse iner join
            //PreparedStatement stmt = this.connection
            //		.prepareStatement("select * from bairro inner join cidade on bairro.idcidade = cidade.id");
            PreparedStatement stmt = this.connection
                    .prepareStatement("select cl.id,cl.nome,cl.nomefantasia,r.nome,c.nome,e.nome,b.nome,cl.telefone, cl.telefone2 from cliente as cl inner join cidade as c on cl.idcidade = c.id inner join bairro as b on cl.idbairro = b.id inner join estado as e on cl.idestado = e.id inner join ramoatividade as r on cl.idramoatividade = r.id order by id");

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

    public ModeloCliente buscaPorId(Long id) {

        if (id == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, idbairro, idestado, idcidade, idramoatividade, trim(nome)as nome, trim(contato) as contato, trim(endereco) as endereco, cep, telefone, telefone2, fax, celular, trim(observacao) as observacao, trim(cpf) as cpf, trim(identidade) as identidade, trim(nomefantasia) as nomefantasia, cnpj, trim(inscricaomunicipal) as inscricaomunicipal , datacadastro, trim(inscricaoestadual) as inscricaoestadual , trim(email) as email,idrotas,boleto from cliente where id = ?");
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

    public ModeloCliente buscaIdPorNome(String nome) {

        if (nome == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cliente where nome = ?");
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
        String sql = "select cl.id,trim(cl.nome) as nome,cl.nomefantasia,cl.endereco,b.nome,c.nome,e.nome,cl.telefone2, cl.cnpj, boleto from cliente as cl inner join cidade as c on cl.idcidade = c.id inner join bairro as b on cl.idbairro = b.id inner join estado as e on cl.idestado = e.id inner join ramoatividade as r on cl.idramoatividade = r.id  where cl.nome like ? or cl.nomefantasia like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, nome);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Endereço", "boleto", "Bairro", "Cidade", "Estado", "Telefone", "CNPJ"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean("boleto"), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela buscaLikeCliente(String nome) {
        nome = "%" + nome + "%";
        //
        String sql = "select cl.id,cl.nome,cl.nomefantasia,b.nome,c.nome,e.nome from cliente as cl inner join cidade as c on cl.idcidade = c.id inner join bairro as b on cl.idbairro = b.id inner join estado as e on cl.idestado = e.id inner join ramoatividade as r on cl.idramoatividade = r.id  where cl.nome like ? or cl.nomefantasia like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, nome);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Bairro", "Cidade", "Estado"};
            ArrayList dados = new ArrayList();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista

                dados.add(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }

            rs.close();
            stmt.close();
            ModeloTabela modelo = new ModeloTabela(dados, colunas);
            return modelo;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ModeloTabela buscaIDTabela(Long idcliente) {

        //
        String sql = "select cl.id,cl.nome,cl.nomefantasia,cl.endereco,b.nome,c.nome,e.nome,cl.telefone2, cl.cnpj from cliente as cl inner join cidade as c on cl.idcidade = c.id inner join bairro as b on cl.idbairro = b.id inner join estado as e on cl.idestado = e.id inner join ramoatividade as r on cl.idramoatividade = r.id  where cl.id = ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setLong(1, idcliente);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Endereço", "Bairro", "Cidade", "Estado", "Telefone", "CNPJ"};
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

    private ModeloCliente populaModelo(ResultSet rs) throws SQLException {
        ModeloCliente modeloCliente = new ModeloCliente();

        modeloCliente.setId(rs.getLong("id"));
        modeloCliente.setRamoAtividade(rs.getLong("idramoatividade"));
        modeloCliente.setBairro(rs.getLong("idbairro"));
        modeloCliente.setCidade(rs.getLong("idcidade"));
        modeloCliente.setEstado(rs.getLong("idestado"));
        modeloCliente.setNome(rs.getString("nome"));
        modeloCliente.setContato(rs.getString("contato"));
        modeloCliente.setCep(rs.getString("cep"));
        modeloCliente.setEndereco(rs.getString("endereco"));
        modeloCliente.setTelefone(rs.getString("telefone"));
        modeloCliente.setTelefone1(rs.getString("telefone2"));
        modeloCliente.setFax(rs.getString("fax"));
        modeloCliente.setCelular(rs.getString("celular"));
        modeloCliente.setRg(rs.getString("identidade"));
        modeloCliente.setCpf(rs.getString("cpf"));
        modeloCliente.setObservacao(rs.getString("observacao"));
        modeloCliente.setNomeFantasia(rs.getString("nomefantasia"));
        modeloCliente.setCnpj(rs.getString("cnpj"));
        modeloCliente.setInscricaoMunicipal(rs.getString("inscricaomunicipal"));
        modeloCliente.setDataCadastro(rs.getDate("datacadastro"));
        modeloCliente.setInscricaoEstadual(rs.getString("inscricaoestadual"));
        modeloCliente.seteMail(rs.getString("email"));
        modeloCliente.setRota(rs.getLong("idrotas"));
        modeloCliente.setBoleto(rs.getBoolean("boleto"));

        return modeloCliente;
    }

    public boolean verificacao(Long idCliente) throws SQLException {

        boolean resultBoolean = false;
        if (idCliente == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        PreparedStatement stmt = this.connection
                .prepareStatement("select id from cliente where id = ?");
        stmt.setLong(1, idCliente);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            return true;
        }

        rs.close();
        stmt.close();

        return resultBoolean;
    }

    public TableModel buscaCPF(String cpf) {
        cpf = "%" + cpf + "%";
        //
        String sql = "select cl.id,cl.nome,cl.nomefantasia,cl.endereco,b.nome,c.nome,e.nome,cl.telefone2, cl.cnpj from cliente as cl inner join cidade as c on cl.idcidade = c.id inner join bairro as b on cl.idbairro = b.id inner join estado as e on cl.idestado = e.id inner join ramoatividade as r on cl.idramoatividade = r.id  where cl.cpf like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Endereço", "Bairro", "Cidade", "Estado", "Telefone", "CNPJ"};
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

    public TableModel buscaCNPJ(String cnpj) {
        cnpj = "%" + cnpj + "%";
        //
        String sql = "select cl.id,cl.nome,cl.nomefantasia,cl.endereco,b.nome,c.nome,e.nome,cl.telefone2, cl.cnpj from cliente as cl inner join cidade as c on cl.idcidade = c.id inner join bairro as b on cl.idbairro = b.id inner join estado as e on cl.idestado = e.id inner join ramoatividade as r on cl.idramoatividade = r.id  where cl.cnpj like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cnpj);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Endereço", "Bairro", "Cidade", "Estado", "Telefone", "CNPJ"};
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

    public TableModel buscaEndereco(String endereco) {
        endereco = "%" + endereco + "%";
        //
        String sql = "select cl.id,cl.nome,cl.nomefantasia,cl.endereco,b.nome,c.nome,e.nome,cl.telefone2, cl.cnpj from cliente as cl inner join cidade as c on cl.idcidade = c.id inner join bairro as b on cl.idbairro = b.id inner join estado as e on cl.idestado = e.id inner join ramoatividade as r on cl.idramoatividade = r.id  where cl.endereco like ? order by id;";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, endereco);

            stmt.execute();

            String[] colunas = new String[]{"ID", "Nome", "Nome Fantasia", "Endereço", "Bairro", "Cidade", "Estado", "Telefone", "CNPJ"};
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

    public boolean verificaCPFAltera(String cpf, Long idCliente) {
        cpf = "%" + cpf + "%";
        //
        String sql = "select cl.id from cliente as cl where cl.cpf like ? and cl.id != ? ;";
        PreparedStatement stmt;
        boolean retorno = false;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setLong(2, idCliente);

            stmt.execute();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista
                retorno = true;

            }

            rs.close();
            stmt.close();

            return retorno;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verificaCNPJAltera(String cnpj, long idCliente) throws SQLException {
        cnpj = "%" + cnpj + "%";
        //
        String sql = "select cl.id from cliente as cl where cl.cnpj like ? and cl.id != ?;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, cnpj);
        stmt.setLong(2, idCliente);

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

    public boolean verificaCPFAdiciona(String cpf) throws SQLException {

        cpf = "%" + cpf + "%";
        //
        String sql = "select cl.id from cliente as cl where cl.cpf like ? ;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, cpf);

        stmt.execute();

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            // adiciona a modeloBairro na lista
            retorno = true;

        }

        rs.close();
        stmt.close();

        return retorno;

    }

    public boolean verificaCNPJAdiciona(String cnpj) throws SQLException {
        cnpj = "%" + cnpj + "%";
        //
        String sql = "select cl.id from cliente as cl where cl.cnpj like ?;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, cnpj);

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

    public boolean verificaNomeFantasiaadiciona(String nomeFantasia) throws SQLException {

        //
        String sql = "select cl.id from cliente as cl where cl.nomefantasia = ?;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setString(1, nomeFantasia);

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

    public boolean verificaNomeFantasiaAltera(String fantasia, Long idCliente) {

        //
        String sql = "select cl.id from cliente as cl where cl.nomefantasia = ? and cl.id != ? ;";
        PreparedStatement stmt;
        boolean retorno = false;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, fantasia);
            stmt.setLong(2, idCliente);

            stmt.execute();

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // adiciona a modeloBairro na lista
                retorno = true;

            }

            rs.close();
            stmt.close();

            return retorno;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Long ultimaDataLocacao(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT MAX(ID)as id from parcelas where tipoparcela = false and idCliente = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return rs.getLong("id");
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Long ultimaVenda(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT MAX(ID)as id from venda where idCliente = '?';");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return rs.getLong("id");
            }

            rs.close();
            stmt.close();

            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean verificaVenda(Long id, int dia) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }
        dia = dia + 30;

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT *  from venda where idCliente = ?  and (datavenda BETWEEN CURRENT_DATE - integer '30' AND CURRENT_DATE) ;");
            stmt.setLong(1, id);
            //stmt.setInt(2, dia);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return true;
            }

            rs.close();
            stmt.close();

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public boolean VerificaSeDeve(Long id) {
        if (id == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }
       

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT id, idvenda, idtipopagamento, idcliente, valor, datapagamento, datavencimento, estatosparcela, valorpago, tipoparcela FROM public.parcelas where idcliente = ? and datavencimento <= CURRENT_DATE and estatosparcela = false");
            stmt.setLong(1, id);
            
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                return true;
            }

            rs.close();
            stmt.close();

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void alterarNotaFiscal(Long id, String email) {

        String sql = "update cliente set email = ? where id = ?";
        PreparedStatement stmt;
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setLong(2, id);
            stmt.execute();

        } catch (SQLException e) {

            throw new RuntimeException(e);
        }

    }

    public boolean verificarboleto(Long idCliente) {
        try {
            boolean resultBoolean = false;

            PreparedStatement stmt = this.connection
                    .prepareStatement("select * from cliente as ped where ped.boleto = TRUE and id = ?");
            stmt.setLong(1, idCliente);
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

    public TableModel listaPagamentos(long idCliente) {
        try {

            String[] Colunas = new String[]{"Data Paga", "Valor"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT pc.idcliente, ch.datapaga, sum(ch.valor) as valor FROM cheque as ch inner join parcelas as pc on pc.id = ch.idparcela where pc.idcliente = ? group by pc.idcliente , ch.datapaga order by ch.datapaga");
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                // adiciona a modeloPedido na lista
                if(LogicasPedido.validaDatabanco(rs.getString(2))){
                dados.add(new Object[]{format.format(rs.getDate(2).getTime()), rs.getDouble(3)});
                
                }else{
                dados.add(new Object[]{rs.getString(2), rs.getDouble(3)});
                
                }
            }

            rs.close();
            stmt.close();
            ModeloTabela vales = new ModeloTabela(dados, Colunas);
            return vales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public TableModel listaPagamentosDetalhado(long idCliente) {
        try {

            String[] Colunas = new String[]{"ID venda","Data Paga", "Valor"};
            ArrayList dados = new ArrayList();

            PreparedStatement stmt = this.connection
                    .prepareStatement("SELECT pc.idvenda , ch.datapaga, ch.valor FROM cheque as ch inner join parcelas as pc on pc.id = ch.idparcela where pc.idcliente = ? order by ch.datapaga");
            stmt.setLong(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            while (rs.next()) {
                // adiciona a modeloPedido na lista
                if(LogicasPedido.validaDatabanco(rs.getString(2))){
                dados.add(new Object[]{rs.getLong(1), format.format(rs.getDate(2).getTime()), rs.getDouble(3)});
                
                }else{
                dados.add(new Object[]{rs.getLong(1),rs.getString(2), rs.getDouble(3)});
                
                }
            }

            rs.close();
            stmt.close();
            ModeloTabela vales = new ModeloTabela(dados, Colunas);
            return vales;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
