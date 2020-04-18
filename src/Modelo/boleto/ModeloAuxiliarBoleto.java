/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.boleto;

/**
 *
 * @author usuario
 */
public class ModeloAuxiliarBoleto {
    private ModeloBoleto boleto;
    private int sequencial;

    public ModeloBoleto getBoleto() {
        return boleto;
    }

    public void setBoleto(ModeloBoleto boleto) {
        this.boleto = boleto;
    }

    public int getSequencial() {
        return sequencial;
    }

    public void setSequencial(int sequencial) {
        this.sequencial = sequencial;
    }
}
