package Controle;

import Modelo.ModeloBairro;
import Modelo.ModeloCidades;
//import Modelo.ModeloCidades;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Familia Nascimento
 */
public class ControleBairroNovo { 
ConectaBanco2 connBairro = new ConectaBanco2();
    
    public void inserirBairro(ModeloBairro modeloBairro){
     
     //JOptionPane.showMessageDialog(null, modeloBairro.getNome());
    
     connBairro.conexao();
     try {
         
         PreparedStatement pst = connBairro.conn.prepareStatement("INSERT INTO bairro (nome,idcidade)values(?,?)");
         pst.setString(1,modeloBairro.getNome());
         pst.setLong(2,modeloBairro.getCodBairro());
         pst.execute();
          
         JOptionPane.showMessageDialog(null," dados  armazenado com sucesso");
     
     } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null," Falha no Armazenamento de dados \n ERRO :"+ ex);
     }
     connBairro.desconecta();

    
    }
    public void AlteraBairro(ModeloBairro ModBairro){
    connBairro.conexao();
     try {
         PreparedStatement pst = connBairro.conn.prepareStatement("update bairro set nome=?, id= ? where id=? ");
         pst.setString(1,ModBairro.getNome());
         pst.setLong(2, ModBairro.getCodBairro());
         pst.setLong(3, ModBairro.getCodBairro());
         pst.execute();
         JOptionPane.showMessageDialog(null," dados  Alterados com sucesso");
     } catch (SQLException ex) {
         //Logger.getLogger(ControleCidade.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null," Erro ao tentar Alterar Dados\nErro:"+ex);
     }
}
    public void ExcluiBairro(ModeloBairro ModBairro){
    connBairro.conexao();
     try {
         PreparedStatement pst = connBairro.conn.prepareStatement("delete from bairro where id=?");
         pst.setLong(1,ModBairro.getCodBairro());
         
         pst.execute();
         JOptionPane.showMessageDialog(null," dados  Excluidos com sucesso");
         
     } catch (SQLException ex) {
         Logger.getLogger(ControleCidade.class.getName()).log(Level.SEVERE, null, ex);
     }
}
    }

    