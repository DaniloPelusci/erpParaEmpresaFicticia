/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Produto;

/**
 *
 * @author dan-pelusci
 */
public class ModeloProduto {

    private Long id;
    private Long tipo;
    private Long nomenclatura;
    private Long modelo; 
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTipo() {
        return tipo;
    }

    public void setTipo(Long tipo) {
        this.tipo = tipo;
    }

    public Long getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(Long nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    public Long getModelo() {
        return modelo;
    }

    public void setModelo(Long modelo) {
        this.modelo = modelo;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
   
}
