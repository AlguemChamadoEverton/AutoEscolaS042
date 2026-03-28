package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.agendamento;

import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidadorAlunoAtivo implements ValidadorAgendamento {
    @Autowired
    private AlunoRepository repository;

    @Override
    public List<String> validar(DadosAgendamentoInstrucao dados) {
        List<String> erros = new ArrayList<>();
        Boolean alunoAtivo = repository.findByIdAndAtivoTrue(dados.idAluno());

        if(!alunoAtivo) {
            erros.add("Agendamento não pode ser realizado para aluno inativo!");
        }
        return erros;
    }
}