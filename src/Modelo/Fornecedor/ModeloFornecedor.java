/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Fornecedor;

import Modelo.ModeloEmpresa;

/**
 *
 * @author dan-pelusci
 */
public class ModeloFornecedor extends ModeloEmpresa{
    private Long tipo;

    @Override
    public String toString() {
        return "ModeloFornecedor{" + "tipo=" + tipo + '}';
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }
    
}
