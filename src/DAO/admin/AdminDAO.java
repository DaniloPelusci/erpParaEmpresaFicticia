/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.admin;

import Modelo.admin.ModeloAdmin;
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
public class AdminDAO {
    
     private Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }
    
    public boolean verificaparcela(ModeloAdmin modeloAdmin) {
        try {
            boolean resultBoolean = false;

            

            List<ModeloParcela> parcelas = new ArrayList<ModeloParcela>();
            PreparedStatement stmt = this.connection
                    
                    .prepareStatement("select id from admin where nome = ? and senha = ?");

            stmt.setString(2, modeloAdmin.getSenha());
            stmt.setString(1, modeloAdmin.getNome());
            
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
    
}
