package br.com.senai.s042.autoescolas042.adapter.in.controller;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.usecase.AgendaDeInstrucoes;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/instrucoes")
@SecurityRequirement(name = "bearer-key")
public class InstrucaoController {

    @Autowired
    private AgendaDeInstrucoes agenda;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<DadosDetalhamentoInstrucao> agendarInstrucao(
            @RequestBody @Valid DadosAgendamentoInstrucao dados) {
        DadosDetalhamentoInstrucao dto = agenda.agendarInstrucao(dados);
        return ResponseEntity.ok(dto);
    }
}