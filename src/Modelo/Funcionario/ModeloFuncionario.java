/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Funcionario;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author dan-pelusci
 */
public class ModeloFuncionario {
    private Long id;
    private Long cargo;
    private Long bairro;
    private Long cidade;
    private Long estado;
    private String nome;
    private Date dataNascimento;
    private BigDecimal salario;
    private String rg;
    private String cpf;
    private String carteiraTrabalho;
    private String endereco;
    private Date dataAdmissao;
    private Date dataDemissao;
    private String telefone;
    private String celular;
    private String Observacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCargo() {
        return cargo;
    }

    public void setCargo(Long cargo) {
        this.cargo = cargo;
    }

    public Long getBairro() {
        return bairro;
    }

    public void setBairro(Long bairro) {
        this.bairro = bairro;
    }

    public Long getCidade() {
        return cidade;
    }

    public void setCidade(Long cidade) {
        this.cidade = cidade;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(String carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getObservacao() {
        return Observacao;
    }

    public void setObservacao(String Observacao) {
        this.Observacao = Observacao;
    }

    @Override
    public String toString() {
        return "ModeloFuncionario{" + "id=" + id + ", cargo=" + cargo + ", bairro=" + bairro + ", cidade=" + cidade + ", estado=" + estado + ", nome=" + nome + ", dataNascimento=" + dataNascimento + ", salario=" + salario + ", rg=" + rg + ", cpf=" + cpf + ", carteiraTrabalho=" + carteiraTrabalho + ", endereco=" + endereco + ", dataAdmissao=" + dataAdmissao + ", dataDemissao=" + dataDemissao + ", telefone=" + telefone + ", celular=" + celular + ", Observacao=" + Observacao + '}';
    }
    
    
}
