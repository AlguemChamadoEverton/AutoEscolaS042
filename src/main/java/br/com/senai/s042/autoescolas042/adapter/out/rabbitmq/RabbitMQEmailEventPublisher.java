package br.com.senai.s042.autoescolas042.adapter.out.rabbitmq;

import br.com.senai.s042.autoescolas042.adapter.in.rabbitmq.EmailEventConsumer;
import br.com.senai.s042.autoescolas042.adapter.out.rabbitmq.event.EmailOcorrenciaEvent;
import br.com.senai.s042.autoescolas042.application.port.out.EmailEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQEmailEventPublisher implements EmailEventPublisher {
    private final RabbitTemplate template;

    public RabbitMQEmailEventPublisher(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void publishOcorrenciaEvent(EmailOcorrenciaEvent event){
        template.convertAndSend("email.ocorrencia", event);
    }
}
