package br.com.senai.s042.autoescolas042.application.port.out;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AlunoRepository {
    Page<Aluno> findAllByAtivoTrue(Pageable paginacao);
    Boolean findByIdAndAtivoTrue(Long id);
    Aluno save(Aluno aluno);
    Optional<Aluno> findById(Long id);
    boolean existsById(Long id);
    Aluno getReferenceById(Long aLong);
}