package br.com.senai.s042.autoescolas042.adapter.in.viacep;

import br.com.senai.s042.autoescolas042.adapter.in.viacep.request.DadosConsultaCEP;
import br.com.senai.s042.autoescolas042.adapter.in.viacep.response.DadosDetalhamentoCEP;
import br.com.senai.s042.autoescolas042.application.core.service.ViaCEPService;
import br.com.senai.s042.autoescolas042.application.port.in.ViaCEPPortIn;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/viacep")
public class ViaCEPController implements ViaCEPPortIn {
    private final ViaCEPService service;

    public ViaCEPController(ViaCEPService service) {
        this.service = service;
    }

    @PostMapping
    @Override
    public ResponseEntity<DadosDetalhamentoCEP> consultarCEP(DadosConsultaCEP dados) {
        return ResponseEntity.ok(service.consultar(dados));
    }
}
