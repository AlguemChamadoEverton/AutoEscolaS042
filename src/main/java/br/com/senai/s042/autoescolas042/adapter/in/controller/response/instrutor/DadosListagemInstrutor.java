package br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrutor;

import br.com.senai.s042.autoescolas042.application.core.domain.enums.Especialidade;

public record DadosListagemInstrutor(
        Long id,
        String nome,
        String email,
        String cnh,
        Especialidade especialidade) {
}