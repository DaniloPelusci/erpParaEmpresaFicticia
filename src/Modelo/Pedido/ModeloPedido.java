/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Pedido;

import java.sql.Date;

/**
 *
 * @author dan-pelusci
 */
public class ModeloPedido {
    private Long id;
    private Long idVenda;
    private Long idFuncionario;
    private Long idCliente;
    private Long idTipoPedido;
    private int numeroGrafica;
    private Date dataEmissao;
    private boolean situacao;
    private boolean processoQuitacao;
    private boolean boleto;

    public boolean isBoleto() {
        return boleto;
    }

    public void setBoleto(boolean boleto) {
        this.boleto = boleto;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Long idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public int getNumeroGrafica() {
        return numeroGrafica;
    }

    public void setNumeroGrafica(int numeroGrafica) {
        this.numeroGrafica = numeroGrafica;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public boolean isProcessoQuitacao() {
        return processoQuitacao;
    }

    public void setProcessoQuitacao(boolean processoQuitacao) {
        this.processoQuitacao = processoQuitacao;
    }


    
    
}
