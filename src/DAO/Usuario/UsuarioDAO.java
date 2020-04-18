/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Usuario;

import Modelo.Funcionario.ModeloFuncionario;
import Modelo.Usuario.ModeloUsuario;
import Modelo.venda.ModeloParcela;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author E.B.O 2
 */
public class UsuarioDAO {

    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }
    public boolean verificacaoLoguin(ModeloUsuario modeloUsuario){
        try {
            boolean resultBoolean = false;

            

            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    
                    .prepareStatement("select id from usuario where usuario = ? and senha = ?");

            stmt.setString(2, modeloUsuario.getSenha());
            stmt.setString(1, modeloUsuario.getUsuario());
            
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
    public boolean verificacao(ModeloFuncionario modeloFuncionario) throws SQLException{
        String sql = "select use.id from usuario as use where use.idfuncionario = ?;";
        PreparedStatement stmt;
        boolean retorno = false;

        stmt = connection.prepareStatement(sql);
        stmt.setLong(1, modeloFuncionario.getId());

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
    public ModeloUsuario busca(ModeloFuncionario modeloFuncionario){
        if (modeloFuncionario.getId() == null) {
            throw new IllegalStateException("Id da modeloCliente nao deve ser nula.");
        }

        try {
            PreparedStatement stmt = this.connection
                    .prepareStatement("select id, trim(usuario)as usuario, trim(senha) as senha,idfuncionario from usuario where idfuncionario = ?");
            stmt.setLong(1, modeloFuncionario.getId());
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

    private ModeloUsuario populaModelo(ResultSet rs) throws SQLException {
        ModeloUsuario modeloUsuario = new ModeloUsuario();
        modeloUsuario.setId(rs.getLong("id"));
        modeloUsuario.setIdFuncionario(rs.getLong("idfuncionario"));
        modeloUsuario.setUsuario(rs.getString("usuario"));
        modeloUsuario.setSenha(rs.getString("senha"));
        return modeloUsuario;
        
    
    }

    public void adcionar(ModeloUsuario modeloUsuario) {
        String sql = "insert into usuario (idfuncionario,senha,usuario) values (?,?,?)";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);

            stmt.setLong(1, modeloUsuario.getIdFuncionario());
            stmt.setString(2, modeloUsuario.getSenha());
            stmt.setString(3, modeloUsuario.getUsuario());
            
            

            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    public void altera(ModeloUsuario modeloUsuario) {
    
        String sql = "update usuario set senha=?, usuario=? where id = ?";
        PreparedStatement stmt;
        try {
            stmt = connection.prepareStatement(sql);            
            stmt.setString(1, modeloUsuario.getSenha());
            stmt.setString(2, modeloUsuario.getUsuario());
            stmt.setLong(3, modeloUsuario.getId());
            
            stmt.execute();
            stmt.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    
    }

    public boolean verificacaoUsuarioExistenteFuncionarioCadastrado(ModeloUsuario modeloUsuario) {
        String sql = "select use.id from usuario as use where use.usuario = ? and use.id != ? ;";
        PreparedStatement stmt;
        boolean retorno = false;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloUsuario.getUsuario());
            stmt.setLong(2, modeloUsuario.getId());

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

    public boolean verificacaoUsuario(ModeloUsuario modeloUsuario) {
    
         String sql = "select use.id from usuario as use where use.usuario = ?;";
        PreparedStatement stmt;
        boolean retorno = false;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, modeloUsuario.getUsuario());
            

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

}
