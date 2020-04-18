/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import DAO.ConnectionFactory;
import DAO.admin.AdminDAO;
import Modelo.admin.ModeloAdmin;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author E.B.O 2
 */
public class LogicasPedido {

    public static boolean isInt(String v) {
        /* Verifica se um numero é inteiro ou não */
        try {
            Integer.parseInt(v);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
   
    public static boolean isDouble(String v) {
        /* Verifica se um numero é inteiro ou não */
        try {
            Double.parseDouble(v);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean isfloat(String v) {
        /* Verifica se um numero é inteiro ou não */
        try {

            BigDecimal b = new BigDecimal(v);
            System.out.println(b);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean validaData(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            sdf.setLenient(false);

            Date data2 = (Date) sdf.parse(data);

            return true;
            // se passou pra cá, é porque a data é válida
        } catch (ParseException e) {
            return false;
            // se cair aqui, a data é inválida
//            validar += "\n" + "data invalida ";
//            validacaoData.setVisible(true);
//            cont++;
        }
    }
    public static boolean validaDatabanco(String data) {
         boolean decisao = false;
        try {
           
           
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            
            if(data!=null ){
            Date data2 = (Date) sdf.parse(data);
            
            decisao= true;
            }
            
            return decisao;
            // se passou pra cá, é porque a data é válida
        } catch (ParseException e) {
            return decisao = false;
            // se cair aqui, a data é inválida
//            validar += "\n" + "data invalida ";
//            validacaoData.setVisible(true);
//            cont++;
        }
    }
    public static boolean validaData2(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
            sdf.setLenient(false);

            Date data2 = (Date) sdf.parse(data);

            return true;
            // se passou pra cá, é porque a data é válida
        } catch (ParseException e) {
            return false;
            // se cair aqui, a data é inválida
//            validar += "\n" + "data invalida ";
//            validacaoData.setVisible(true);
//            cont++;
        }
    }

    public static boolean joptionSenha() throws SQLException {
        

        //Criar a mensagem sera exibida
        JLabel label2 = new JLabel("Digite a login:");
        JLabel label = new JLabel("Digite a senha:");

//criar o componente grafico que recebera o que for digitado
        JPasswordField jpf = new JPasswordField();
        JTextField jtf = new JTextField();
//Exibir a janela para o usuario
        JOptionPane.showConfirmDialog(null,
                new Object[]{label2, jtf, label, jpf}, "Senha:",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

//Pegamos o que foi digitado e comparamos com a senha correta
        String senhaDigitada = new String(jpf.getPassword());
        String nomeDigitado = new String(jtf.getText());
        ModeloAdmin modeloAdmin = new ModeloAdmin();
        modeloAdmin.setNome(nomeDigitado);
        modeloAdmin.setSenha(senhaDigitada);

        Connection connection = new ConnectionFactory().getConnection();
        AdminDAO adminDAO = new AdminDAO(connection);
        if (adminDAO.verificaparcela(modeloAdmin)) {
            System.out.println("Senha correta");
            return true;
        } else {
            System.out.println("Senha incorreta");
            return false;
        }
    }

}
