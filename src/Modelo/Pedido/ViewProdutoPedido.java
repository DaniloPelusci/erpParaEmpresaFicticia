/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Pedido;

/**
 *
 * @author SERVIDOR
 */
public class ViewProdutoPedido {
    Long id;
    Long tipoAcao;
    String nome;
    String nomenclatura; 
   
    
    String quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomenclatura() {
        return nomenclatura;
    }

    public void setNomenclatura(String nomenclatura) {
        this.nomenclatura = nomenclatura;
    }

    

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Long getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(Long tipoAcao) {
        this.tipoAcao = tipoAcao;
    }
    
}
