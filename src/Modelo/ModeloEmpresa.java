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
public class ModeloEmpresa {

    private Long id;
    private Long Bairro;
    private Long Estado;
    private Long Cidade;
    private String nome;
    private String nomeFantasia;
    private String cnpj;
    private String endereco;
    private String cep;
    private String telefone;
    private String telefone1;
    private String celular;
    private String fax;
    private String observacao;
    private String inscricaoMunicipal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBairro() {
        return Bairro;
    }

    public void setBairro(Long Bairro) {
        this.Bairro = Bairro;
    }

    public Long getEstado() {
        return Estado;
    }

    public void setEstado(Long Estado) {
        this.Estado = Estado;
    }

    public Long getCidade() {
        return Cidade;
    }

    public void setCidade(Long Cidade) {
        this.Cidade = Cidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefone1() {
        return telefone1;
    }

    public void setTelefone1(String telefone1) {
        this.telefone1 = telefone1;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    @Override
    public String toString() {
        return "ModeloEmpresa{" + "id=" + id + ", Bairro=" + Bairro + ", Estado=" + Estado + ", Cidade=" + Cidade + ", nome=" + nome + ", nomeFantasia=" + nomeFantasia + ", cnpj=" + cnpj + ", endereco=" + endereco + ", cep=" + cep + ", telefone=" + telefone + ", telefone1=" + telefone1 + ", celular=" + celular + ", fax=" + fax + ", observacao=" + observacao + ", inscricaoMunicipal=" + inscricaoMunicipal + '}';
    }

}
