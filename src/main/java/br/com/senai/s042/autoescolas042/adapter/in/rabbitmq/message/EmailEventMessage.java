package br.com.senai.s042.autoescolas042.adapter.in.rabbitmq.message;

import java.util.List;

public record EmailEventMessage(Long ocorrenciaId,
                                List<String> emails,
                                String assunto,
                                String mensagem) {
}
