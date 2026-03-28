package br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario;

import br.com.senai.s042.autoescolas042.application.core.domain.enums.Role;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(

        @NotNull
        Long id,
        String login,
        Boolean ativo,
        Role perfil) {
}