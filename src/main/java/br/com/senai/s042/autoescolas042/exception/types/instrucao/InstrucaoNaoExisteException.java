package br.com.senai.s042.autoescolas042.exception.types.instrucao;

public class InstrucaoNaoExisteException extends RuntimeException {
    public InstrucaoNaoExisteException(String message) {
        super(message);
    }
}