package br.com.senai.s042.autoescolas042.exception.types.aluno;

public class AlunoNaoExisteException extends RuntimeException {
    public AlunoNaoExisteException(String message) {
        super(message);
    }
}