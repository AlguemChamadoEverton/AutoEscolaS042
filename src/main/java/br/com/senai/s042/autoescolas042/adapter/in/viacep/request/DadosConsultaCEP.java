package br.com.senai.s042.autoescolas042.adapter.in.viacep.request;

import jakarta.validation.constraints.NotBlank;

public record DadosConsultaCEP(
        @NotBlank
        String cep) {
}