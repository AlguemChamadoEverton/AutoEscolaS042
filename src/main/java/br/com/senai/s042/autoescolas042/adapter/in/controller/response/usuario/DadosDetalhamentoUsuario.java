package br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario;

import br.com.senai.s042.autoescolas042.application.core.domain.enums.Role;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        Boolean ativo,
        Role perfil) {
}
