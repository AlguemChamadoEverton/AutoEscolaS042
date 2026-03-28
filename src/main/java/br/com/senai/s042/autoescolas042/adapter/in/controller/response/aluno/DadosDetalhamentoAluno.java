package br.com.senai.s042.autoescolas042.adapter.in.controller.response.aluno;

import br.com.senai.s042.autoescolas042.application.core.domain.vo.Endereco;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco,
        Boolean ativo) {
}