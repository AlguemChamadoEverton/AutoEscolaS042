package br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;

import java.util.List;

public interface ValidadorAgendamento {
    List<String> validar(DadosAgendamentoInstrucao dados);
}