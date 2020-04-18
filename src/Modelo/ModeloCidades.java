/*z
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Familia Nascimento
 */
public class ModeloCidades {
  private Long codig ;
  private String nome;
  private Long  cod_estado;

    @Override
    public String toString() {
        return getNome();
    }

    public Long getCodig() {
        return codig;
    }

    public void setCodig(Long codig) {
        this.codig = codig;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getCod_estado() {
        return cod_estado;
    }

    public void setCod_estado(Long cod_estado) {
        this.cod_estado = cod_estado;
    }

   

    
  
}
