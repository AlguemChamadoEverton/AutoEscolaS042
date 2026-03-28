package br.com.senai.s042.autoescolas042.exception.types.instrutor;

public class InstrutorNaoExisteException extends RuntimeException {
    public InstrutorNaoExisteException(String message) {
        super(message);
    }
}