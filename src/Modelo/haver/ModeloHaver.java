/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.haver;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author E.B.O 2
 */
public class ModeloHaver {

    private Long id;
    private Long idCliente;
    private BigDecimal valorRetirado;
    private BigDecimal valorHaver;
    private Date dataEmissao;
    private Date dataQuitado;
    private String observacao;

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public BigDecimal getValorRetirado() {
        return valorRetirado;
    }

    public void setValorRetirado(BigDecimal valorRetirado) {
        this.valorRetirado = valorRetirado;
    }

    public BigDecimal getValorHaver() {
        return valorHaver;
    }

    public void setValorHaver(BigDecimal valorHaver) {
        this.valorHaver = valorHaver;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataQuitado() {
        return dataQuitado;
    }

    public void setDataQuitado(Date dataQuitado) {
        this.dataQuitado = dataQuitado;
    }

}
