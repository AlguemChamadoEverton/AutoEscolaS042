package br.com.senai.s042.autoescolas042.domain.instrutor;

import br.com.senai.s042.autoescolas042.domain.endereco.DadosEndereco;

public record DadosAtualizacaoInstrutor(
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}