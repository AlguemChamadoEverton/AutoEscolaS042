package br.com.senai.s042.autoescolas042.exception.types.instrucao;

import java.util.List;

public class ValidacaoException extends RuntimeException {
    private List<String> erros;

    public ValidacaoException(List<String> erros) {
        super("Erros de validação:");
        this.erros = erros;
    }

    public List<String> getErros() {
        return erros;
    }
}