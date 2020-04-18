/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Controle;

import java.sql.Date;

/**
 *
 * @author usuario
 */
public class ModeloControle {
    private Long id;
    private Long idFuncionario;
    private Long idVenda;
    private Date dataRetirada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    @Override
    public String toString() {
        return "ModeloControle{" + "id=" + id + ", idFuncionario=" + idFuncionario + ", idVenda=" + idVenda + ", dataRetirada=" + dataRetirada + '}';
    }

   
           
}
