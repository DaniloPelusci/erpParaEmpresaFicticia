/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.boleto;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author usuario
 */
public class ModeloBoleto {

    private Long id;
    private Long codLiquidacao;
    private Long NossoNumero;
    private Long codOcorrencia;
    private Date Dataocorrencia;
    private Long idPedido;
    private String numDocumento;
    private BigDecimal valorPagodotitulo;
    private Date dataLiquidacaoCredito;
    private Date vencimento;
    private Date dataEmissao;
    private BigDecimal valorTitulo;
    private boolean emitido;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodLiquidacao() {
        return codLiquidacao;
    }

    public void setCodLiquidacao(Long codLiquidacao) {
        this.codLiquidacao = codLiquidacao;
    }

    public Long getCodOcorrencia() {
        return codOcorrencia;
    }

    public void setCodOcorrencia(Long codOcorrencia) {
        this.codOcorrencia = codOcorrencia;
    }

    public Date getDataocorrencia() {
        return Dataocorrencia;
    }

    public void setDataocorrencia(Date Dataocorrencia) {
        this.Dataocorrencia = Dataocorrencia;
    }

    public String getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    public BigDecimal getValorPagodotitulo() {
        return valorPagodotitulo;
    }

    public void setValorPagodotitulo(BigDecimal valorPagodotitulo) {
        this.valorPagodotitulo = valorPagodotitulo;
    }

    public Date getDataLiquidacaoCredito() {
        return dataLiquidacaoCredito;
    }

    public void setDataLiquidacaoCredito(Date dataLiquidacaoCredito) {
        this.dataLiquidacaoCredito = dataLiquidacaoCredito;
    }

    public Long getNossoNumero() {
        return NossoNumero;
    }

    public void setNossoNumero(Long NossoNumero) {
        this.NossoNumero = NossoNumero;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public BigDecimal getValorTitulo() {
        return valorTitulo;
    }

    public void setValorTitulo(BigDecimal valorTitulo) {
        this.valorTitulo = valorTitulo;
    }

    public boolean isEmitido() {
        return emitido;
    }

    public void setEmitido(boolean emitido) {
        this.emitido = emitido;
    }

}
