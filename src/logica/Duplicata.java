/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import DAO.ConnectionFactory;
import DAO.venda.ParcelaDAO;
import Modelo.venda.ModeloParcela;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author E.B.O 2
 */
public class Duplicata {

    public static String gerarReferencia(Long id) throws SQLException {
        String referencia = "";
        Connection connection = new ConnectionFactory().getConnection();
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);
        ModeloParcela modeloParcela = new ModeloParcela();
        modeloParcela = parcelaDAO.buscaPorId(id);

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(modeloParcela.getDataVencimento());
        //calendarTemp.add(calendarTemp.MONTH, -1);

        referencia = modeloParcela.getIdCliente()+ "-" + sdf.format(calendarTemp.getTime());
       
        connection.close();

        return referencia;

    }
    public static String gerarReferenciaForaBanco(ModeloParcela modeloParcela)  {
        

        SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
        Calendar calendarTemp = Calendar.getInstance();
        calendarTemp.setTime(modeloParcela.getDataVencimento());
        //calendarTemp.add(calendarTemp.MONTH, -1);

        String referencia = modeloParcela.getIdCliente()+ "-" + sdf.format(calendarTemp.getTime());
        System.err.println(calendarTemp.getTime());
       
       

        return referencia;

    }

}
