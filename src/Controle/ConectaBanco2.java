/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Familia Nascimento
 */
public class ConectaBanco2 {
   public Statement stm ;
   public ResultSet rs;
    private String driver = "org.postgresql.driver";
    private String caminho ="jdbc:postgresql://localhost:5432/testes";
    private String usuario = "postgres";
    private String senha ="1234";
    public Connection conn;
    
    public void conexao (){
        System.setProperty("jdbc.Drivers", driver);
       try {
           conn = DriverManager.getConnection(caminho,usuario,senha);
           JOptionPane.showMessageDialog(null,"conectado com sucesso!");
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null,"Erro ao tentar se conectar ao Banco de dados, erro na classe conexao!"+ex.getMessage());
       }
    }
    public void desconecta (){
       try {
           conn.close();
       } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null,"Erro ao tentar fechar, erro na classe conexao!"+ex.getMessage());
       }
       }
        
         }


