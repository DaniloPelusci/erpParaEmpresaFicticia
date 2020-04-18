/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import DAO.ConnectionFactory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author SERVIDOR
 */
public class Relatorio {
    public static void main(String[] args) throws SQLException, JRException, FileNotFoundException {
        Connection connection = new ConnectionFactory().getConnection();
        JasperCompileManager.compileReportToFile("relatorioVendas.jrxml");
        Map<String, Object> parameters = new HashMap<String, Object>();
        JasperPrint jasperPrint = JasperFillManager.fillReport("relatorioVendas.jasper", parameters, connection);
        JRExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, new FileOutputStream("testandoRelatorios/RelatorioVendas.pdf"));
        exporter.exportReport();
        connection.close();
    }
    
}
