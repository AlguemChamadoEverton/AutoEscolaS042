package br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno;

import br.com.senai.s042.autoescolas042.application.core.domain.dto.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroAluno(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        String cpf,

        @NotNull
        @Valid
        DadosEndereco endereco) {
}
