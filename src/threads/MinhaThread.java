/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threads;

import DAO.ConnectionFactory;
import DAO.DevedourosDAO;
import DAO.venda.ParcelaDAO;
import Modelo.ModeloDevedor;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class MinhaThread extends Thread {

    private Long idCliente;

    public MinhaThread(Long idClienteF) {
        this.idCliente = idClienteF;
    }

    public void execucao() throws SQLException {
        
            Connection connection = new ConnectionFactory().getConnection();
            ParcelaDAO parcelasDAO = new ParcelaDAO(connection);
            ModeloDevedor modeloDevedor = new ModeloDevedor();
            DevedourosDAO devedourosDAO = new DevedourosDAO(connection);
            ModeloDevedor modeloDevedortreed = new ModeloDevedor();

            float valor;
            float valorAPagarVendaVencidas = parcelasDAO.SomaTotalValorPagarVendaVencidasDevedores(idCliente);

            float valorPagoVendaVencidas = parcelasDAO.SomaTotalValorPagoVendaVencidosDevedores(idCliente);

            float valorTotalDevidoVendaVencidas = valorAPagarVendaVencidas - valorPagoVendaVencidas;

            float valotTotalDevidoLocacaoVencidas = parcelasDAO.SomaTotalValorASerpagoLocaçãoVencidasDevedores(idCliente);
            float valorPagoLocacaoVencidas = parcelasDAO.SomaTotalValorPagoLocaçãoVencidasDevedores(idCliente);

            float valorTotalVencidas = valorTotalDevidoVendaVencidas + valotTotalDevidoLocacaoVencidas;

            boolean result = false;
            if (valorTotalVencidas > 0) {
                result = true;
            }

            modeloDevedor.setValor(valorTotalVencidas);
            modeloDevedor.setDevendo(result);
            modeloDevedor.setIdCliente(idCliente);

            

            
                if (devedourosDAO.verificacaoDevedores(modeloDevedor.getIdCliente())) {

                    devedourosDAO.altera(modeloDevedor);
                } else {

                    devedourosDAO.adcionar(modeloDevedor);
                }

            
            connection.close();
      

    }

    public void run() {
        try {
            execucao();
        } catch (SQLException ex) {
            Logger.getLogger(MinhaThread.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
