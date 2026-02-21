package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.aluno.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @Transactional
    public String cadastrarAluno (@RequestBody DadosCadastroAluno dados) {
        repository.save(new Aluno(dados));
        return "Cadastro realizado com sucesso!";
    }

    @GetMapping
    public List<DadosListagemAluno> listarAlunos() {
        return repository.findAllByAtivoTrue().stream().map(DadosListagemAluno::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizarAluno(@RequestBody DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.getReferenceById(dados.id());
        aluno.atualizarInformacoes(dados);
        repository.save(aluno);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluirInstrutor(@PathVariable Long id) {
        //repository.deleteById(id);
        Aluno aluno = repository.getReferenceById(id);
        aluno.excluir();
        repository.save(aluno);
    }
}