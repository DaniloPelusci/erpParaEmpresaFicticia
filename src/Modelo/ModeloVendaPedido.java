/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author SERVIDOR
 */
public class ModeloVendaPedido {
    private String id ;
    private String qtdItens;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQtdItens() {
        return qtdItens;
    }

    public void setQtdItens(String qtdItens) {
        this.qtdItens = qtdItens;
    }
    
}
