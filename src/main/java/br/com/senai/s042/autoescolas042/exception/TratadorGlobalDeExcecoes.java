package br.com.senai.s042.autoescolas042.exception;

import br.com.senai.s042.autoescolas042.exception.types.aluno.AlunoNaoExisteException;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.ValidacaoException;
import br.com.senai.s042.autoescolas042.exception.types.instrutor.InstrutorNaoExisteException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorGlobalDeExcecoes {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> tratarNotFound() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosBadRequest>> tratarBadRequest(MethodArgumentNotValidException ex) {
        List<FieldError> erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosBadRequest::new).toList());
    }

    @ExceptionHandler(InstrutorNaoExisteException.class)
    public ResponseEntity<String> tratarInstrutorNaoExiste(InstrutorNaoExisteException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(AlunoNaoExisteException.class)
    public ResponseEntity<String> tratarAlunoNaoExiste(AlunoNaoExisteException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<List<String>> tratarValidacao(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(ex.getErros());
    }

    private record DadosBadRequest(String campo, String mensagem) {
        public DadosBadRequest(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}