package br.com.senai.s042.autoescolas042.exception.types.viacep;

public class CEPNotFoundException extends RuntimeException {
    public CEPNotFoundException(String message) {
        super(message);
    }
    public CEPNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
