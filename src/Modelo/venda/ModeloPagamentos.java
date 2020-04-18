/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.venda;

import java.sql.Date;

/**
 *
 * @author usuario
 */
public class ModeloPagamentos {
   private Date datapaga;
   private double valor;

    public Date getDatapaga() {
        return datapaga;
    }

    public void setDatapaga(Date datapaga) {
        this.datapaga = datapaga;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "ModeloPagamentos{" + "datapaga=" + datapaga + ", valor=" + valor + '}';
    }
   
    
}
