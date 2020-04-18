
package Controle;

import Modelo.ModeloCidades;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Familia Nascimento
 */
public class ControleCidade {
 ConectaBanco2 connCidade = new ConectaBanco2();
    
    public void inserirCidade(ModeloCidades mod){
     
    
     connCidade.conexao();
     try {
     
         PreparedStatement pst = connCidade.conn.prepareStatement("INSERT INTO cidade (nome,idestado)values(?,?)");
        
         pst.setString(1,mod.getNome());
         pst.setLong(2,mod.getCod_estado());
         pst.execute();
                 JOptionPane.showMessageDialog(null," dados  armazenado com sucesso");
     
     } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null," Falha no Armazenamento de dados \n ERRO :"+ ex);
     }
     connCidade.desconecta();
     
 }   
 public void  ExcluiCidade(ModeloCidades Mod){
     connCidade.conexao();
     try {
          
         PreparedStatement pst = connCidade.conn.prepareStatement("delete from cidade whare id=?");
         pst.setLong(1,Mod.getCodig());
         pst.execute();
         JOptionPane.showMessageDialog(null," dados  Excluidos com sucesso");
         
     } catch (SQLException ex) {
         Logger.getLogger(ControleCidade.class.getName()).log(Level.SEVERE, null, ex);
     }
 }
public void ExcluiCidades(ModeloCidades Mod){
    connCidade.conexao();
     try {
         PreparedStatement pst = connCidade.conn.prepareStatement("delete from cidade where id=?");
         pst.setLong(1,Mod.getCod_estado());
         
         pst.execute();
         JOptionPane.showMessageDialog(null," dados  Excluidos com sucesso");
         
     } catch (SQLException ex) {
         Logger.getLogger(ControleCidade.class.getName()).log(Level.SEVERE, null, ex);
     }
}
public void AlteraCidade(ModeloCidades Mod){
    connCidade.conexao();
     try {
         PreparedStatement pst = connCidade.conn.prepareStatement("update cidade set nome=?, idestado= ? where id=? ");
         pst.setString(1,Mod.getNome());
         pst.setLong(2, Mod.getCod_estado());
         pst.setLong(3, Mod.getCodig());
         pst.execute();
         JOptionPane.showMessageDialog(null," dados  Alterados com sucesso");
     } catch (SQLException ex) {
         //Logger.getLogger(ControleCidade.class.getName()).log(Level.SEVERE, null, ex);
           JOptionPane.showMessageDialog(null," Erro ao tentar Alterar Dados\nErro:"+ex);
     }
}
}
