/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NovoClass;

import Modelo.Controle.ModeloControle;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

/**
 *
 * @author usuario
 */
public class ControleDAO {

    private Connection connection;

    public ControleDAO(Connection connection) {
        this.connection = connection;
    }

    public int adiciona(ModeloControle modeloControle) throws ParseException {
        String sql = "INSERT INTO controle(idfuncionario, idvenda) VALUES (?, ?);";
        PreparedStatement stmt;
        int idObjeto = 0;
        try {

            stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, modeloControle.getIdFuncionario());
            stmt.setLong(2, modeloControle.getIdVenda());
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

    public boolean verificar(ModeloControle modeloControle) throws SQLException {
      
       
     boolean resultBoolean = false;
        

        PreparedStatement stmt = this.connection
                .prepareStatement("SELECT id  FROM controle where idfuncionario = ? and idvenda=?");
        stmt.setLong(1, modeloControle.getIdFuncionario());
        stmt.setLong(2, modeloControle.getIdVenda());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            return true;
        }

        rs.close();
        stmt.close();
        System.err.println("alou terezinha");
        return resultBoolean;
    
    }

}
