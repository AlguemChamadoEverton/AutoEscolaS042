package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorHorarioFuncionamento implements ValidadorAgendamento {
    @Override
    public List<String> validar(DadosAgendamentoInstrucao dados) {
        List<String> erros = new ArrayList<>();
        LocalDateTime dataAgendamento = dados.data();

        Boolean ehDomingo = dataAgendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        Boolean preAbertura = dataAgendamento.getHour() < 6;
        Boolean posFechamento = dataAgendamento.getHour() > 21 - 1;

        if(ehDomingo || preAbertura || posFechamento) {
            erros.add("Tentativa de agendamento fora do horário de funcionamento!");
        }
        return erros;
    }
}