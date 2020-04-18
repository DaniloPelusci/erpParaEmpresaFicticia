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
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;

public class PostgresBackup {

    public static void realizaBackup() throws IOException, InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MMM");
        Calendar horaAtual = Calendar.getInstance();

        // Ou qualquer outra forma que tem
        String dataFormatada = sdf.format(horaAtual.getTime());
        dataFormatada = dataFormatada.replace("/", "_");
        
        String dataMes = sdf2.format(horaAtual.getTime());
        String diretorioBackup = "D:\\Backup\\"+dataMes+dataFormatada;
        File diretorio = new File("D:\\Backup\\"+dataMes+dataFormatada);
        diretorio.mkdirs(); 
        
        
        System.out.println(dataFormatada);
        System.out.println(dataMes);
        final List<String> comandos = new ArrayList<String>();
        //comandos.add("C:\\Program Files (x86)\\PostgreSQL\\8.4\\bin\\pg_dump.exe"); 
        //comandos.add("C:\\Program Files\\PostgresPlus\\8.4SS\\bin\\pg_dump.exe"); 
        comandos.add("C:\\Program Files\\PostgreSQL\\9.5\\bin\\pg_dump.exe");    // esse é meu caminho  
//        comandos.add("-i");
        comandos.add("-h");
        comandos.add("192.168.1.106");     //ou  comandos.add("192.168.0.1"); 
        comandos.add("-p");
        comandos.add("5432");
        comandos.add("-U");
        comandos.add("postgres");
        comandos.add("-F");
        comandos.add("c");
        comandos.add("-b");
        comandos.add("-v");
        comandos.add("-f");
        comandos.add(diretorioBackup+"\\Backup_"+dataMes+"_"+dataFormatada+".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
        comandos.add("testes2");
        ProcessBuilder pb = new ProcessBuilder(comandos);
        pb.environment().put("PGPASSWORD", "1234");      //Somente coloque sua senha        
        try {
            final Process process = pb.start();
            final BufferedReader r = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            String line = r.readLine();
            while (line != null) {
                System.err.println(line);
                line = r.readLine();
            }
            r.close();
            process.waitFor();
            process.destroy();
            JOptionPane.showMessageDialog(null, "backup realizado com sucesso.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            PostgresBackup.realizaBackup();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
