/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import DAO.ConnectionFactory;
import DAO.boleto.BoletoDao;
import DAO.cliente.ClienteDAO;
import DAO.pedido.PedidoDAO;
import DAO.venda.ParcelaDAO;
import DAO.venda.VendaDAO;
import Modelo.Cliente.ModeloCliente;
import Modelo.Pedido.ModeloPedido;
import Modelo.boleto.ModeloBoleto;
import Modelo.venda.ModeloParcela;
import Modelo.venda.ModeloVenda;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

/**
 *
 * @author usuario
 */
public class NovoClass {

    public void executar(ModeloBoleto modeloBoleto) {

        String to = "";
        String subject = "subject";
        String msg = "email text....";
        final String from = "";
        final String password = "";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Boleto referente ao pedido: " + modeloBoleto.getIdPedido());

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText("Segue em o boleto referente ao pedido: " + modeloBoleto.getIdPedido() + "\n Att: ");

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = "";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);

            // Send the complete message parts
            message.setContent(multipart);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void enviarNotafiscal(String caminho, String notafiscal, String idvendaFora) throws SQLException, MessagingException {
        Connection connection = new ConnectionFactory().getConnection();
        VendaDAO vendaDAO = new VendaDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        PedidoDAO pedidoDAO = new PedidoDAO(connection);

        ModeloVenda modeloVenda = new ModeloVenda();
        ModeloPedido modeloPedido = new ModeloPedido();
        ModeloCliente modeloCliente = new ModeloCliente();
        modeloVenda = vendaDAO.buscaPorId(Long.parseLong(idvendaFora));
        modeloCliente = clienteDAO.buscaPorId(modeloVenda.getIdcliente());
        modeloPedido = pedidoDAO.buscaPorVenda(Integer.parseInt(String.valueOf(modeloVenda.getId())));
        System.err.println("cliente numero: " + modeloCliente.getId() + modeloCliente.geteMail());

        String to = modeloCliente.geteMail();
        String subject = "subject";
        String msg = "email text....";
        final String from = "";
        final String password = "";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

        // Set Subject: header field
        message.setSubject("Referente ao Pedido " + modeloPedido.getNumeroGrafica() + " e NF-E " + notafiscal);

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Segue em anexo a NF-E: " + notafiscal + "\n");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = "S:\\notafiscal\\" + caminho;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);
        //outro anexo

        String caminhoInicial = "S:\\boletos";
        File file = new File(caminhoInicial);
        File afile[] = file.listFiles();
        int i = 0;
        NovoClass novoClass = new NovoClass();
        List<String> numeroBoletos = new ArrayList<String>();

        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];

            String NomeDoArquivo = arquivos.getName();
            String[] arrayValores = NomeDoArquivo.split("-");
            String idVenda = arrayValores[2];

            idVenda = idVenda.replace(" ", "");
            idVenda = idVenda.replace(".pdf", "");

            String notaFiscal = arrayValores[0];

            notaFiscal = notaFiscal.replace(" ", "");
            idVenda = idVenda.replace(" ", "");

            if (idVenda.equals(idvendaFora)) {

                messageBodyPart = new MimeBodyPart();

                caminhoInicial = "S:\\boletos\\" + NomeDoArquivo;
                source = new FileDataSource(caminhoInicial);

                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(caminhoInicial);

                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText("Segue em anexo o boleto referente ao pedido: " + modeloPedido.getNumeroGrafica() + "\n");
                multipart.addBodyPart(messageBodyPart);

                numeroBoletos.add(notaFiscal);

            }

        }
        messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);

        // Send message
        Transport.send(message);
        for (String nb : numeroBoletos) {
            BoletoDao boletoDao = new BoletoDao(connection);
            boletoDao.alteraEnvioBoleto(nb, true);
        }

    }

    public void enviarLocacaoComNota(String caminho, String idcliente, String referenciaDeFora, String notafiscal) throws SQLException, MessagingException {

        String[] referenciaSeparada = referenciaDeFora.split("_");

        String mes = referenciaSeparada[0];
        String ano = referenciaSeparada[1];

        Connection connection = new ConnectionFactory().getConnection();

        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);

        ModeloParcela modeloParcela = new ModeloParcela();
        ModeloCliente modeloCliente = new ModeloCliente();

        modeloCliente = clienteDAO.buscaPorId(Long.parseLong(idcliente));
        modeloParcela = parcelaDAO.listaLocacaoPorReferenciaEnviarEmail(Long.parseLong(idcliente), Integer.parseInt(mes), Integer.parseInt(ano));

        System.err.println("cliente numero: " + modeloCliente.getId() + modeloCliente.geteMail());

        String to = modeloCliente.geteMail();
        String subject = "subject";
        String msg = "email text....";
        final String from = "";
        final String password = "";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

        // Set Subject: header field
        message.setSubject("Referente a locacao " + idcliente + "-" + mes + "/" + ano + " e NF-E " + notafiscal);

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Segue em anexo a NF-E: " + notafiscal + "\n");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = "S:\\nt\\" + caminho;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);
        //outro anexo

        String caminhoInicial = "S:\\boletolocacao\\";
        File file = new File(caminhoInicial);
        File afile[] = file.listFiles();
        int i = 0;
        NovoClass novoClass = new NovoClass();
        List<String> numeroBoletos = new ArrayList<String>();

        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];

            String NomeDoArquivo = arquivos.getName();
            System.err.println(NomeDoArquivo);
            String[] arrayValores = NomeDoArquivo.split("-");

            String idNotaEnvio = arrayValores[3];

            idNotaEnvio = idNotaEnvio.replace(" ", "");
            idNotaEnvio = idNotaEnvio.replace(".pdf", "");

            if (idNotaEnvio.equals(notafiscal)) {

                messageBodyPart = new MimeBodyPart();

                caminhoInicial = "S:\\boletolocacao\\" + NomeDoArquivo;
                source = new FileDataSource(caminhoInicial);

                messageBodyPart.setDataHandler(new DataHandler(source));
                messageBodyPart.setFileName(caminhoInicial);

                multipart.addBodyPart(messageBodyPart);
                messageBodyPart = new MimeBodyPart();

                // Now set the actual message
                messageBodyPart.setText("Segue em anexo o boleto referente ao locação: " + idcliente + "-" + mes + "/" + ano + "\n");
                multipart.addBodyPart(messageBodyPart);

                //numeroBoletos.add(notaFiscal);
            }

        }
        messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Atenciosamente,\n\nBruno Halisson.\n(62) 3233-8603\n\n*Mensagem Automática* Respostas para este email devem ser encaminhadas à \n financeiroebo@hotmail.com ");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);

        // Send message
        Transport.send(message);
//        for (String nb : numeroBoletos) {
//            BoletoDao boletoDao = new BoletoDao(connection);
//            boletoDao.alteraEnvioBoleto(nb, true);
//        }

    }

    public void enviarBoleto(String caminho, String boleto, String idvendaFora) throws SQLException, MessagingException {
        Connection connection = new ConnectionFactory().getConnection();
        VendaDAO vendaDAO = new VendaDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        BoletoDao boletoDao = new BoletoDao(connection);
        ModeloVenda modeloVenda = new ModeloVenda();
        ModeloCliente modeloCliente = new ModeloCliente();
        modeloVenda = vendaDAO.buscaPorId(Long.parseLong(idvendaFora));
        modeloCliente = clienteDAO.buscaPorId(modeloVenda.getIdcliente());
        System.err.println("cliente numero: " + modeloCliente.getId() + modeloCliente.geteMail());

        String to = modeloCliente.geteMail();
        String subject = "subject";
        String msg = "email text....";
        final String from = "financeiroebo@gmail.com";
        final String password = "ebo32915151";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

        // Set Subject: header field
        message.setSubject("Referente ao Boleto " + boleto);

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Segue em anexo o boleto: " + boleto + "\n");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        // Part two is attachment
        messageBodyPart = new MimeBodyPart();

        String filename = "S:\\boletos\\" + caminho;
        System.err.println(filename + " ");
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Atenciosamente,\n\nBruno Halisson."
                + "\n(62) 3233-8603\n\n*Mensagem Automática* Respostas para este email devem ser encaminhadas à "
                + "\n financeiroebo@hotmail.com ");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);

        // Send message
        Transport.send(message);

        boletoDao.alteraEnvioBoleto(boleto, true);
        connection.close();

    }

    public void enviarBoletoLocacao(String caminho, String referenciaDeFora, String idCliente) throws SQLException, MessagingException {

        String[] referenciaSeparada = referenciaDeFora.split("_");

        String mes = referenciaSeparada[0];
        String ano = referenciaSeparada[1];

        Connection connection = new ConnectionFactory().getConnection();

        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ParcelaDAO parcelaDAO = new ParcelaDAO(connection);

        ModeloParcela modeloParcela = new ModeloParcela();
        ModeloCliente modeloCliente = new ModeloCliente();

        modeloCliente = clienteDAO.buscaPorId(Long.parseLong(idCliente));
        modeloParcela = parcelaDAO.listaLocacaoPorReferenciaEnviarEmail(Long.parseLong(idCliente), Integer.parseInt(mes), Integer.parseInt(ano));

        System.err.println("cliente numero: " + modeloCliente.getId() + modeloCliente.geteMail());

        String to = modeloCliente.geteMail();
        String subject = "subject";
        String msg = "email text....";
        final String from = "financeiroebo@gmail.com";
        final String password = "ebo32915151";

        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        // Set From: header field of the header.
        message.setFrom(new InternetAddress(from));

        // Set To: header field of the header.
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

        // Set Subject: header field
        message.setSubject("Referente ao Boleto " + idCliente + "-" + mes + "/" + ano);

        // Create the message part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Segue em anexo o boleto: " + idCliente + "-" + mes + "/" + ano + "\n");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        // Part two is attachment
        messageBodyPart = new MimeBodyPart();

        String filename = "S:\\boletolocacao\\" + caminho;
        System.err.println(filename + " ");
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);

        multipart.addBodyPart(messageBodyPart);
        messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText("Atenciosamente,\n\nBruno Halisson."
                + "\n(62) 3233-8603\n\n*Mensagem Automática* Respostas para este email devem ser encaminhadas à "
                + "\n financeiroebo@hotmail.com ");
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);

        // Send message
        Transport.send(message);

    }

    public boolean verificacaoEmail(String idvendaFora) throws SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        VendaDAO vendaDAO = new VendaDAO(connection);
        ClienteDAO clienteDAO = new ClienteDAO(connection);
        ModeloVenda modeloVenda = new ModeloVenda();
        ModeloCliente modeloCliente = new ModeloCliente();

        modeloVenda = vendaDAO.buscaPorId(Long.parseLong(idvendaFora));
        modeloCliente = clienteDAO.buscaPorId(modeloVenda.getIdcliente());
        boolean marcador = true;
        //System.err.println("cliente numero: "+modeloCliente.geteMail());

        String emailValidacao = modeloCliente.geteMail();

        if (modeloCliente.geteMail() == null || modeloCliente.geteMail().trim() == "") {

            String email = JOptionPane.showInputDialog("Informe o preço unitario da locaçao do cliente:" + modeloCliente.getId() + "\n" + modeloCliente.getNome() + "\n" + modeloCliente.getNomeFantasia());

            if (email == null) {

                marcador = false;
            } else {
                clienteDAO.alterarNotaFiscal(modeloCliente.getId(), email);
                marcador = true;
            }

        }
        connection.close();
        return marcador;
    }

    public void verificarSeTem(String caminho) throws SQLException, MessagingException {

        File file = new File(caminho);
        File afile[] = file.listFiles();
        int i = 0;

        //primeiro laço
        int cont = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];

            String nomeDoArquivo = arquivos.getName();
            String[] arrayValores = nomeDoArquivo.split("-");
            String notaFiscal = arrayValores[0];
            String idVenda = arrayValores[2];

            notaFiscal = notaFiscal.replace(" ", "");
            idVenda = idVenda.replace(" ", "");
            idVenda = idVenda.replace(".pdf", "");

            String caminhoInicial2 = "S:\\notafiscal";
            File file2 = new File(caminhoInicial2);
            File afile2[] = file2.listFiles();
            int i2 = 0;
            String[] teste = null;
            cont = 0;

            for (int j2 = afile2.length; i2 < j2; i2++) {
                File arquivos2 = afile2[i2];

                String NomeDoArquivo2 = arquivos2.getName();
                String[] arrayValores2 = NomeDoArquivo2.split("-");

                String idVenda2 = arrayValores2[2];

                idVenda2 = idVenda2.replace(" ", "");
                idVenda2 = idVenda2.replace(".pdf", "");

                String notaFiscal2 = arrayValores2[0];

                notaFiscal2 = notaFiscal2.replace(" ", "");
                idVenda2 = idVenda2.replace(" ", "");

                if (idVenda.equals(idVenda2)) {

                    //teste[i] = caminho;
                    cont++;
                }

            }
            System.err.println(cont);
            if (cont == 0) {
                System.err.println(nomeDoArquivo);
                verificacaoEmail(idVenda);

                enviarBoleto(nomeDoArquivo, notaFiscal, idVenda);

            }
        }
    }

    public void verificarSeTemLocacao(String caminho) throws SQLException, MessagingException {

        File file = new File(caminho);
        File afile[] = file.listFiles();
        int i = 0;

        //primeiro laço
        int cont = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];

            String nomeDoArquivo = arquivos.getName();
            System.out.println(arquivos.getName());
            String[] arrayValores = nomeDoArquivo.split("-");

            String arrayNotafiscal = arrayValores[3].replace(".pdf", "");

            String idcliente = arrayValores[0];
            String referencia = arrayValores[1];
            String notafiscal = arrayNotafiscal;

            if (notafiscal.equals("0")) {
                System.err.println(nomeDoArquivo);

                enviarBoletoLocacao(nomeDoArquivo, referencia, idcliente);
            }
        }
    }
}
