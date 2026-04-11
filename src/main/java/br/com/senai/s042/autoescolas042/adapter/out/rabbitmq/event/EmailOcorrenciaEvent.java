package br.com.senai.s042.autoescolas042.adapter.out.rabbitmq.event;

import java.io.Serializable;
import java.util.List;

public record EmailOcorrenciaEvent(Long ocorrenciaId, List<String> emails, String assunto,
                                   String mensagem) implements Serializable {
}
