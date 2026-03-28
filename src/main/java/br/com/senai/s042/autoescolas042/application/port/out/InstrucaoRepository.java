package br.com.senai.s042.autoescolas042.application.port.out;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;

import java.time.LocalDateTime;

public interface InstrucaoRepository {
    Boolean existsByAlunoIdAndDataBetween(Long id, LocalDateTime inicioExpediente, LocalDateTime fimExpediente);
    Boolean existsByInstrutorIdAndData(Long id, LocalDateTime data);
    Instrucao save(Instrucao instrucao);
}