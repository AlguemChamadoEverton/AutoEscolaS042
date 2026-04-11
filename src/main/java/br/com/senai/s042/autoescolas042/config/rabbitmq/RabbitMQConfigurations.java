package br.com.senai.s042.autoescolas042.config.rabbitmq;

import br.com.senai.s042.autoescolas042.application.core.service.EmailNotificacaoService;
import br.com.senai.s042.autoescolas042.application.port.out.EmailEventPublisher;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;


@Configuration
public class RabbitMQConfigurations {
    @Bean
    public Queue emailOcorrenciaQueue() {
        return new Queue("email.ocorrencia", true);
    }

    @Bean
    JacksonJsonMessageConverter messageConverter() {
        return new JacksonJsonMessageConverter();
    }

    @Bean()
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
    @Bean
    public EmailNotificacaoService emailNotificacaoService(
            EmailEventPublisher emailEventPublisher,
            TemplateEngine  templateEngine) {
        return new EmailNotificacaoService(emailEventPublisher, templateEngine);
    }
}
