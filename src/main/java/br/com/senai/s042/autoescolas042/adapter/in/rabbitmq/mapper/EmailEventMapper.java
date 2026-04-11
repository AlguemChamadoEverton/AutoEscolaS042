package br.com.senai.s042.autoescolas042.adapter.in.rabbitmq.mapper;

import br.com.senai.s042.autoescolas042.adapter.in.rabbitmq.message.EmailEventMessage;
import br.com.senai.s042.autoescolas042.adapter.out.rabbitmq.event.EmailOcorrenciaEvent;
import org.springframework.stereotype.Component;

@Component
public class EmailEventMapper {
    public EmailOcorrenciaEvent toProducer(EmailEventMessage  message){
        return new EmailOcorrenciaEvent(
                message.ocorrenciaId(),
                message.emails().reversed(),
                message.assunto(),
                message.mensagem()
        );
    }
}
