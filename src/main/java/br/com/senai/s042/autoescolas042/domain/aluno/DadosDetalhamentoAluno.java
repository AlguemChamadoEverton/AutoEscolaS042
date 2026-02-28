package br.com.senai.s042.autoescolas042.domain.aluno;

import br.com.senai.s042.autoescolas042.domain.endereco.Endereco;
import br.com.senai.s042.autoescolas042.domain.instrutor.Especialidade;
import br.com.senai.s042.autoescolas042.domain.instrutor.Instrutor;

public record DadosDetalhamentoAluno(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco,
        Boolean ativo) {
    public DadosDetalhamentoAluno(Aluno aluno){
        this(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getTelefone(),
                aluno.getCpf(),
                aluno.getEndereco(),
                aluno.getAtivo()
        );
    }
}
