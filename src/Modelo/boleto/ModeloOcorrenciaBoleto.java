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
public class ModeloOcorrenciaBoleto {
    private Long id;
    private int codOcorrencia;
    private String nome;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCodOcorrencia() {
        return codOcorrencia;
    }

    public void setCodOcorrencia(int codOcorrencia) {
        this.codOcorrencia = codOcorrencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
