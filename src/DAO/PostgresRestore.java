/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author E.B.O 2
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PostgresRestore {

    public static void PostgresRestore() throws IOException, InterruptedException {
        final List<String> comandos = new ArrayList<String>();
        //comandos.add("C:\\Program Files (x86)\\PostgreSQL\\8.4\\bin\\pg_dump.exe"); 
        //comandos.add("C:\\Program Files\\PostgresPlus\\8.4SS\\bin\\pg_dump.exe"); 

        Runtime r = Runtime.getRuntime();
        Process p;
        ProcessBuilder pb;
        r = Runtime.getRuntime();
        pb = new ProcessBuilder(
                 "C:/Program Files/PostgreSQL/9.5/bin\\pg_restore.exe",
                "--host", "localhost",
                "--port", "5432",
                "--username", "postgres",
                "--dbname", "testes3",
                "--no-password",
                "--verbose",
                "D:\\Testes\\testes");
        pb.redirectErrorStream(true);
        p = pb.start();
        InputStream is = p.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String ll;
        while ((ll = br.readLine()) != null) {
            System.out.println(ll);
        }
    }

    public static void main(String[] args) {
        try {
            PostgresRestore.PostgresRestore();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
