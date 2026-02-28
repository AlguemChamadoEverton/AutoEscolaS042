package br.com.senai.s042.autoescolas042.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAuthenticacao(
        @NotBlank
        String login,
        @NotBlank
        //@Pattern(regexp = ".{8}")
        String senha) {

}
