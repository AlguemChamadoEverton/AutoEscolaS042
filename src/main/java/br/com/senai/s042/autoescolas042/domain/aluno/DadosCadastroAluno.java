package br.com.senai.s042.autoescolas042.domain.aluno;

public record DadosCadastroAluno(
        String nome,
        String email,
        String telefone,
        String cpf,
        DadosEndereco endereco) {
}
