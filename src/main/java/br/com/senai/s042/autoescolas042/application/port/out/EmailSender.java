package br.com.senai.s042.autoescolas042.application.port.out;

public interface EmailSender {
    void enviar(String destinatario, String assunto, String conteudo);
}
