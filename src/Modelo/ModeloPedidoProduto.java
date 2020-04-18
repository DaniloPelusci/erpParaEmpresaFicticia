/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author dan-pelusci
 */
public class ModeloPedidoProduto {

    private Long idproduto;
    private Long idpedido;
    private Long tipoAcao;
    private int quantidade;

    public Long getTipoAcao() {
        return tipoAcao;
    }

    public void setTipoAcao(Long tipoAcao) {
        this.tipoAcao = tipoAcao;
    }

    public Long getIdproduto() {
        return idproduto;
    }

    public void setIdproduto(Long idproduto) {
        this.idproduto = idproduto;
    }

    public Long getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(Long idpedido) {
        this.idpedido = idpedido;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

}
