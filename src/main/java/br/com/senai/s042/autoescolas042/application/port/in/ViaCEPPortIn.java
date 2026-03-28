package br.com.senai.s042.autoescolas042.application.port.in;

import br.com.senai.s042.autoescolas042.adapter.in.viacep.request.DadosConsultaCEP;
import br.com.senai.s042.autoescolas042.adapter.in.viacep.response.DadosDetalhamentoCEP;
import org.springframework.http.ResponseEntity;

public interface ViaCEPPortIn {
    ResponseEntity<DadosDetalhamentoCEP> consultarCEP(DadosConsultaCEP cep);

}
