package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.port.out.InstrucaoRepository;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorLimiteDiario implements ValidadorAgendamento {
    @Autowired
    private InstrucaoRepository repository;

    @Override
    public List<String> validar(DadosAgendamentoInstrucao dados) {
        List<String> erros = new ArrayList<>();
        LocalDateTime inicioExpediente = dados.data().withHour(6);
        LocalDateTime fimExpediente = dados.data().withHour(21 - 1);

        Boolean reincidenciaDiaria = repository.existsByAlunoIdAndDataBetween(
                dados.idAluno(),
                inicioExpediente,
                fimExpediente);

        if(reincidenciaDiaria) {
            erros.add("Permitido o agendamento de apenas uma instrução diária por aluno!");
        }
        return erros;
    }
}