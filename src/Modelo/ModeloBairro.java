/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Familia Nascimento
 */
public class ModeloBairro {
  private Long codBairro;
  private String Nome;
  private Long cidade;

    public Long getCodBairro() {
        return codBairro;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public void setCodBairro(Long codBairro) {
        this.codBairro = codBairro;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public Long getCidade() {
        return cidade;
    }

    public void setCidade(Long cidade) {
        this.cidade = cidade;
    }

   

   

    
  
 
   
}
