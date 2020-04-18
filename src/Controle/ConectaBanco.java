/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fernando  Dourado
 */
public class ConectaBanco {
    public Statement stm;// preparar e realizar pesquisas no banco de dados
    public ResultSet rs;// responsavel por armazenar o resultado de uma pesquisa passada pelo statement
    private String Driver = "org.postgresql.Driver";
    private String Caminho = "jdbc:postgresql://localhost:5432/testes";
    private String usuario  = "postgres";
    private String Senha = "1234";
    public Connection conn;//responsavel por realizar a conexao com o banco de dados
    Object Conn;
 
    public void  conexao() {
        try{
        System.setProperty("jdbc.Drivers", Driver);//seta as propriedades do Driver de conexao
        conn =DriverManager.getConnection(Caminho, usuario, Senha);//realiza a conexao com o banco
        //JOptionPane.showMessageDialog(null,"Conexão com o Banco de dados Feita com Sucesso!");
                    
        } catch (SQLException ex) {//excessão
            JOptionPane.showConfirmDialog(null, "Erro de Conexão");
            Logger.getLogger(ConectaBanco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
          public void executaSQL(String sql){
        try {
            stm =conn.createStatement(rs.TYPE_SCROLL_INSENSITIVE , rs.CONCUR_READ_ONLY);
            rs = stm.executeQuery(sql);
        } catch (SQLException ex) {
      //      JOptionPane.showMessageDialog(null, "Erro  ");
        }
          } 
        
          public void desconecta(){
              try{
                  conn.close();
                 // JOptionPane.showMessageDialog(null, "desconectado com Sucesso");
              
              } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro ao tentar fechar a Conexão!\n Erro/;"+ ex.getLocalizedMessage());
        }
              
              
                  
              }

    public void first() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          
}
