/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author SERVIDOR
 */
public class GeradorRelatorio {

    private String nomeArquivo;
    private Map<String, Object> parametros;
    private Connection connection;

    public GeradorRelatorio(String nomeArquivo, Map<String, Object> parametros, Connection connection) {
        this.nomeArquivo = nomeArquivo;
        this.parametros = parametros;
        this.connection = connection;
    }

    public void gerapdfParaOutputStream(String pasta) throws JRException, FileNotFoundException, IOException, SQLException, InterruptedException {
        Map<String, Object> parameters = new HashMap<String, Object>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(this.nomeArquivo, this.parametros, this.connection);

        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        FileOutputStream stream = new FileOutputStream(pasta);

        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, stream);
        exporter.exportReport();
        JOptionPane.showMessageDialog(null, "Relatorio gerado com sussesso ");

        stream.close();
        this.connection.close();
        Desktop desktop = Desktop.getDesktop();
        File file = new File(pasta);

        desktop.open(file);

    }

}
