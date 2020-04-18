/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Cliente;

import Modelo.ModeloEmpresa;
import java.sql.Date;

/**
 *
 * @author dan-pelusci
 */
public class ModeloCliente extends ModeloEmpresa {

    private Long rota;
    private Long ramoAtividade;
    private String cpf;
    private String rg;
    private Date dataCadastro;
    private String contato;
    private String inscricaoEstadual;
    private String eMail;
    private boolean boleto;

    public boolean isBoleto() {
        return boleto;
    }

    public void setBoleto(boolean boleto) {
        this.boleto = boleto;
    }

    public Long getRota() {
        return rota;
    }

    public void setRota(Long rota) {
        this.rota = rota;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public Long getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(Long ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "ModeloCliente{" + "ramoAtividade=" + ramoAtividade + ", cpf=" + cpf + ", rg=" + rg + ", dataCadastro=" + dataCadastro + ", contato=" + contato + '}';
    }

}
