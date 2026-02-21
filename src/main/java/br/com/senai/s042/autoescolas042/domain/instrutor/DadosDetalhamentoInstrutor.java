package br.com.senai.s042.autoescolas042.domain.instrutor;

import br.com.senai.s042.autoescolas042.domain.endereco.Endereco;

public record DadosDetalhamentoInstrutor(
        Long id,
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade,
        Endereco endereco,
        Boolean ativo) {
    public DadosDetalhamentoInstrutor(Instrutor instrutor){
        this(
                instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade(),
                instrutor.getEndereco(),
                instrutor.getAtivo()
        );
    }
}
