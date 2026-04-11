package br.com.senai.s042.autoescolas042.application.port.out;

import br.com.senai.s042.autoescolas042.adapter.out.rabbitmq.event.EmailOcorrenciaEvent;

public interface EmailEventPublisher {
    void publishOcorrenciaEvent(EmailOcorrenciaEvent event);
}
