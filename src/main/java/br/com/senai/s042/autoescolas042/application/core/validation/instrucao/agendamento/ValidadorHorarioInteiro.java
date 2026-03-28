package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorHorarioInteiro implements ValidadorAgendamento {
    @Override
    public List<String> validar(DadosAgendamentoInstrucao dados) {
        List<String> erros = new ArrayList<>();
        LocalDateTime dataAgendamento = dados.data();

        if(dataAgendamento.getMinute() != 0 || dataAgendamento.getSecond() != 0) {
            erros.add("O horário deve ser preenchido em horas inteiras (ex: 08:00, 13:00)");
        }
        return erros;
    }
}