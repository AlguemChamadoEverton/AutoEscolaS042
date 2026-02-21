package br.com.senai.s042.autoescolas042.domain.aluno;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findAllByAtivoTrue();
}