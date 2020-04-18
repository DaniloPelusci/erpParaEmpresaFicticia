/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.venda;

import java.util.Date;

/**
 *
 * @author SERVIDOR
 */
public class ModeloCheque {

    private Long id;
    private Long idparcela;
    private Long idBanco;
    private Long idTipoPagamento;
    private String numeroCheque;
    private float valor;
    private Date datapaga;

    public Date getDatapaga() {
        return datapaga;
    }

    public void setDatapaga(Date datapaga) {
        this.datapaga = datapaga;
    }

    

    public Long getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(Long idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public Long getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Long idBanco) {
        this.idBanco = idBanco;
    }

    public Long getIdparcela() {
        return idparcela;
    }

    public void setIdparcela(Long idparcela) {
        this.idparcela = idparcela;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCheque() {
        return numeroCheque;
    }

    public void setNumeroCheque(String numeroCheque) {
        this.numeroCheque = numeroCheque;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

}
