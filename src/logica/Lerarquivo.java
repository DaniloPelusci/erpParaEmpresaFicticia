/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SERVIDOR
 */
public class Lerarquivo {
    FileReader fileR;
    BufferedReader buff;
    public Lerarquivo() {
        
    }
    public String ler() throws IOException{
       String ip = null;
        try {
            fileR = new FileReader("pokemon.txt");
            buff = new BufferedReader(fileR);
            while (buff.ready()) {
               ip = buff.readLine();                
            }
            buff.close();
            System.err.println(ip);
                    } catch (FileNotFoundException ex) {
            Logger.getLogger(Lerarquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ip;
    }
    
    
}
