/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.venda;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author SERVIDOR
 */
public class ModeloParcela {

    private Long id;
    private Long idVenda;
    private Long idCliente;
    private BigDecimal valor;
    private BigDecimal valorPago;
    private Date dataPagamento;
    private Date dataVencimento;
    private boolean estatosParcela;
    private boolean tipoParcela;
    private BigDecimal valorUnitario;

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isTipoParcela() {
        return tipoParcela;
    }

    public void setTipoParcela(boolean tipoParcela) {
        this.tipoParcela = tipoParcela;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public boolean isEstatosParcela() {
        return estatosParcela;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setEstatosParcela(boolean estatosParcela) {
        this.estatosParcela = estatosParcela;
    }

}
