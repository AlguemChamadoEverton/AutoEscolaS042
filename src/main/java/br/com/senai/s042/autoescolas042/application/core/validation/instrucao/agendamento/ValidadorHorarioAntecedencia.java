package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento {
    @Override
    public List<String> validar(DadosAgendamentoInstrucao dados) {
        List<String> erros = new ArrayList<>();
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime horaAgendamento = dados.data();

        Long antecedencia = Duration.between(agora, horaAgendamento).toMinutes();

        if(antecedencia < 30) {
            erros.add("Instrução deve ser agendada com antecedência mínima de 30 minutos!");
        }
        return erros;
    }
}