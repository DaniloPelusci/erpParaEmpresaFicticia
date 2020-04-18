/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controle;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
//**
//*
// * @author Familia Nascimento
//*/
//public class ModeloTabela extends AbstractTabeleModel; {

public class ModeloTabela extends AbstractTableModel {

//public class ModeloTabela 
    private ArrayList Linhas = null;
    private String[] Colunas = null;

    public ModeloTabela(ArrayList lin, String[] col) {
        setLinhas(lin);
        setColunas(col);

    }

    public ArrayList getLinhas() {
        return Linhas;

    }

    public void setLinhas(ArrayList dados) {
        Linhas = dados;
    }

    public String[] getColunas() {
        return Colunas;

    }

    public void setColunas(String[] nomes) {
        Colunas = nomes;
    }

    @Override
    public int getColumnCount() {
        return Colunas.length;
    }

    @Override
    public int getRowCount() {
        return Linhas.size();

    }

    @Override
    public String getColumnName(int numCol) {
        return Colunas[numCol];
    }

    @Override
    public Object getValueAt(int numLin, int numCol) {
        Object[] Linhas = (Object[]) getLinhas().get(numLin);
        return Linhas[numCol];

    }
}
