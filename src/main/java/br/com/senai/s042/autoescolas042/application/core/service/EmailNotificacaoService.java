package br.com.senai.s042.autoescolas042.application.core.service;

import br.com.senai.s042.autoescolas042.adapter.out.rabbitmq.event.EmailOcorrenciaEvent;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import br.com.senai.s042.autoescolas042.application.port.out.EmailEventPublisher;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmailNotificacaoService {
    private final EmailEventPublisher emailEventPublisher;
    private final TemplateEngine templateEngine;

    public EmailNotificacaoService(EmailEventPublisher emailEventPublisher, TemplateEngine templateEngine) {
        this.emailEventPublisher = emailEventPublisher;
        this.templateEngine = templateEngine;
    }

    public String carregarTemplate(String template) {
        try {
            return new String(
                    Files.readAllBytes(Paths.get(getClass()
                            .getClassLoader()
                            .getResource(template)
                            .toURI()
                    )),
                    StandardCharsets.UTF_8
            );
        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar template: " + template, e);
        }
    }

    public void enviarNotificacaoInstrucao(Instrucao instrucao, String acao) {
        List<String> emails = List.of(
                "vrtnrdrgs@gmail.com",
                instrucao.getAluno().getEmail(),
                instrucao.getInstrutor().getEmail()
        );
        String assunto = "instrucao" + acao;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String dataFormatada = instrucao.getData().format(formatter);

            /*String message = "\nUma instrucao foi " + acao + "!" +
                    "\nID: " + instrucao.getId() +
                    "\nAluno: " + instrucao.getAluno().getNome() +
                    "\nInstrutor: " + instrucao.getInstrutor().getNome() +
                    "\nData: " + dataFormatada;*///
        Context context = new Context();
        context.setVariable("acao", acao);
        context.setVariable("id", instrucao.getId().toString());
        context.setVariable("aluno", instrucao.getAluno().getNome());
        context.setVariable("instrutor", instrucao.getInstrutor().getNome());
        context.setVariable("data", dataFormatada);
        String mensagem = templateEngine.process("email_instrucao", context);

        EmailOcorrenciaEvent event = new EmailOcorrenciaEvent(
                instrucao.getId(),
                emails,
                assunto,
                mensagem
        );
        emailEventPublisher.publishOcorrenciaEvent(event);
    }
}

