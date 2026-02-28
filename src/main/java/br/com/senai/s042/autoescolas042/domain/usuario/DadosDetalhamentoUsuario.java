package br.com.senai.s042.autoescolas042.domain.usuario;

import br.com.senai.s042.autoescolas042.domain.instrutor.Instrutor;
import jakarta.validation.constraints.NotBlank;

public record DadosDetalhamentoUsuario (
        Long id,
        @NotBlank
        String login) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getLogin()
        );
    }
}

