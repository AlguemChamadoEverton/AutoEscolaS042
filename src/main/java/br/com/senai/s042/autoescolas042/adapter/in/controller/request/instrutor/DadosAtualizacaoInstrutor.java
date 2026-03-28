package br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrutor;

import br.com.senai.s042.autoescolas042.application.core.domain.dto.DadosEndereco;

public record DadosAtualizacaoInstrutor(
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}