/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author usuario
 */
public class NovoClass {

    private String pokemon = "";
    private int pokemon2 = 0;
    Map<String, Float> example = new HashMap<String, Float>();

    public NovoClass() {

    }

    public HashMap<String, String> getMapa() {
        HashMap<String, String> map = new HashMap<>();
        map.put("AC", "Acre");
        map.put("AL", "Alagoas");
        map.put("AP", "Amapá");
        map.put("AM", "Amazonas");
        map.put("BA", "Bahia");
        map.put("CE", "Ceará");
        map.put("DF", "Distrito Federal");
        map.put("ES", "Espírito Santo");
        map.put("GO", "Goiás");
        //resto dos put()
        return map;
    }

    public void pokemon() {
        HashMap<String, String> mapaEstados = getMapa();
        for (String valor : mapaEstados.values()) {
            System.out.println(valor);
        }

    }

    public static void main(String[] args){
        
    }

}
