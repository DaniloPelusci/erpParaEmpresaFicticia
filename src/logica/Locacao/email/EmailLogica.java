/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica.Locacao.email;

import DAO.ConnectionFactory;
import DAO.venda.VendaDAO;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import logica.NovoClass;

/**
 *
 * @author usuario
 */
public class EmailLogica {

    public boolean SalvarNotaFiscal(String caminhoInicial) throws SQLException, MessagingException {
        Connection connection = new ConnectionFactory().getConnection();
        VendaDAO vendaDAO = new VendaDAO(connection);

        File file = new File(caminhoInicial);
        File afile[] = file.listFiles();
        int i = 0;
        NovoClass novoClass = new NovoClass();

        for (int j = afile.length; i < j; i++) {

            File arquivos = afile[i];

            String NomeDoArquivo = arquivos.getName();
            System.out.println(arquivos.getName());
            String[] arrayValores = NomeDoArquivo.split("-");

            String notaFiscal = arrayValores[0];

            String idVenda = arrayValores[2];

//            String[] arrayIdvendaSeparar = idVenda.split(".");
//            idVenda = arrayIdvendaSeparar[0];
            notaFiscal = notaFiscal.replace(" ", "");
            idVenda = idVenda.replace(" ", "");
            idVenda = idVenda.replace(".pdf", "");
            vendaDAO.alterarNotaFiscal(notaFiscal, Long.parseLong(idVenda));
            
            if(vendaDAO.verificaEnvioNotaFiscal(Long.parseLong(idVenda))){
            novoClass.enviarNotafiscal(arquivos.getName(), notaFiscal, idVenda);
            vendaDAO.alterarEnvioNotaFiscal(Long.parseLong(idVenda), true);
               
            }else{
                 System.err.println("Nota Fiscal da venda "+idVenda+" já foi enviada.");
            }
        }

        JOptionPane.showMessageDialog(null, "Emails enviados com sucesso!");
        return false;
    }

    
    
     public void SalvarNotaLocacao(String caminhoInicial) throws SQLException, MessagingException {
        Connection connection = new ConnectionFactory().getConnection();
        VendaDAO vendaDAO = new VendaDAO(connection);

        File file = new File(caminhoInicial);
        File afile[] = file.listFiles();
        int i = 0;
        NovoClass novoClass = new NovoClass();

        for (int j = afile.length; i < j; i++) {

            File arquivos = afile[i];

            String NomeDoArquivo = arquivos.getName();
            System.out.println(arquivos.getName());
            String[] arrayValores = NomeDoArquivo.split("-");
            
            String arrayNotafiscal =  arrayValores[3].replace(".pdf", "");
             arrayNotafiscal =  arrayNotafiscal.replace(" ", "");

            

            String idcliente = arrayValores[0];
            String referencia = arrayValores[1];
            String notafiscal = arrayNotafiscal;

//            String[] arrayIdvendaSeparar = idVenda.split(".");
//            idVenda = arrayIdvendaSeparar[0];
            
            
            novoClass.enviarLocacaoComNota(arquivos.getName(), idcliente, referencia,notafiscal);
           
               
            
        }

        JOptionPane.showMessageDialog(null, "Emails enviados com sucesso!");
       
    }

    public boolean validaNotaParaEnvio(String caminhoInicial) throws SQLException, MessagingException {
        Connection connection = new ConnectionFactory().getConnection();
        VendaDAO vendaDAO = new VendaDAO(connection);
        File file = new File(caminhoInicial);
        File afile[] = file.listFiles();

        int i = 0;
        NovoClass novoClass = new NovoClass();
        boolean marcador = true;

        for (int j = afile.length; i < j; i++) {
            marcador = true;
            File arquivos = afile[i];

            String NomeDoArquivo = arquivos.getName();

            String[] arrayValores = NomeDoArquivo.split("-");

            String notaFiscal = arrayValores[0];

            String idVenda = arrayValores[2];
            System.err.println(idVenda);
//            String[] arrayIdvendaSeparar = idVenda.split(".");
//            idVenda = arrayIdvendaSeparar[0];
            notaFiscal = notaFiscal.replace(" ", "");
            idVenda = idVenda.replace(" ", "");
            idVenda = idVenda.replace(".pdf", "");

            marcador = novoClass.verificacaoEmail(idVenda);
            vendaDAO.alterarNotaFiscal(notaFiscal, Long.parseLong(idVenda));

            if (marcador == false) {

                i = afile.length;

            }

        }
        return false;
    }

}
