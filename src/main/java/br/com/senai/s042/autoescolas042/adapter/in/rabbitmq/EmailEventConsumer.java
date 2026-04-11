package br.com.senai.s042.autoescolas042.adapter.in.rabbitmq;

import br.com.senai.s042.autoescolas042.adapter.in.rabbitmq.mapper.EmailEventMapper;
import br.com.senai.s042.autoescolas042.adapter.in.rabbitmq.message.EmailEventMessage;
import br.com.senai.s042.autoescolas042.adapter.out.rabbitmq.event.EmailOcorrenciaEvent;
import br.com.senai.s042.autoescolas042.application.port.out.EmailSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class EmailEventConsumer {
    private final EmailEventMapper mapper;
    private final EmailSender sender;

    private EmailEventConsumer(EmailEventMapper mapper, EmailSender sender) {
        this.mapper = mapper;
        this.sender = sender;
    }

    @RabbitListener(queues = "email.ocorrencia")
    public void receberMensagem(EmailEventMessage message) {
        EmailOcorrenciaEvent event = mapper.toProducer(message);
        for(String email : event.emails()){
            sender.enviar(email, event.assunto(), event.mensagem());
        }
    }
}
