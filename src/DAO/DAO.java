/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Controle.ModeloTabela;
import Modelo.ModeloBairro;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author dan-pelusci
 */
interface DAO {

  

    void adiciona(ModeloBairro modeloBairro);

    void altera(ModeloBairro modeloBairro);

    List<ModeloBairro> lista();

    ModeloTabela listaTabela();

    ResultSet resultSet();

    ModeloBairro buscaPorId(Long id);

    List<ModeloBairro> buscaIdPorCidade(String nome);

    ModeloTabela buscaLike(String nome);

    
}
