package br.com.senai.s042.autoescolas042.adapter.out.email;

import br.com.senai.s042.autoescolas042.application.port.out.EmailSender;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SMTPEmailSender implements EmailSender {

    private final JavaMailSender emailSender;

    public SMTPEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }
    @Override
    public void enviar(String destinatario, String assunto, String conteudo) {
        try{
            /*SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(destinatario);
            message.setSubject(assunto);
            message.setText(conteudo);
            emailSender.send(message);*///
            MimeMessage mimeMessage = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true, "UTF-8");
            helper.setTo(destinatario);
            helper.setSubject(assunto);
            helper.setText(conteudo, true);
            emailSender.send(mimeMessage);
            System.out.println("Email enviado com sucesso para " + destinatario);
        } catch(Exception e){
            System.err.println("Erro ao enviar email para " + destinatario);
            e.printStackTrace();
        }
    }
}
