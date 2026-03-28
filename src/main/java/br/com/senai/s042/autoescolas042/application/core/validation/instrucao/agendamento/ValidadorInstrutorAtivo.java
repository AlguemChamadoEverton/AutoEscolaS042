package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.port.out.InstrutorRepository;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorInstrutorAtivo implements ValidadorAgendamento {
    @Autowired
    private InstrutorRepository repository;

    @Override
    public List<String> validar(DadosAgendamentoInstrucao dados) {
        List<String> erros = new ArrayList<>();
        Boolean instrutorAtivo = repository.findAtivoById(dados.idInstrutor());

        if(!instrutorAtivo) {
            erros.add("Instrução não pode ser agendada com instrutor inativo!");
        }
        return erros;
    }
}