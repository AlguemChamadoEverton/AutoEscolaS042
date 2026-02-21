package br.com.senai.s042.autoescolas042.domain.aluno;

public record DadosAtualizacaoAluno(
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}