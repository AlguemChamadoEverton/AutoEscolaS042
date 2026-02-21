package br.com.senai.s042.autoescolas042.domain.aluno;

public record DadosListagemAluno(
        String nome,
        String email,
        String cpf ) {
    public DadosListagemAluno(Aluno aluno) {
        this(aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf()
        );
    }
}