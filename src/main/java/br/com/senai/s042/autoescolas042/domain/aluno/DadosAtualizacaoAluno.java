package br.com.senai.s042.autoescolas042.domain.aluno;

import br.com.senai.s042.autoescolas042.domain.endereco.DadosEndereco;

public record DadosAtualizacaoAluno(
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}