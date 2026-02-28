package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.aluno.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> cadastrarAluno(
            @RequestBody @Valid DadosCadastroAluno dados,
            UriComponentsBuilder uriBuilder) {
        Aluno aluno = new Aluno(dados);
        repository.save(aluno);
        URI uri = uriBuilder.path("/alunos/{id}")
                .buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAluno>> listarAlunos(
            @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        Page page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAluno::new);
        return ResponseEntity.ok(page);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemAluno> detalharAluno(@PathVariable Long id){
        Aluno aluno = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosListagemAluno(aluno));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> atualizarAluno(@RequestBody DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.getReferenceById(dados.id());
        aluno.atualizarInformacoes(dados);
        repository.save(aluno);
        return ResponseEntity.ok(new DadosDetalhamentoAluno(aluno));
    }

    @DeleteMapping("/{id}") // padrao de mercado
    @Transactional
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        Aluno aluno = repository.getReferenceById(id);
        aluno.excluir();
        repository.save(aluno);
        return ResponseEntity.noContent().build();
    }
    /*@DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAluno> excluirAluno(@PathVariable Long id) {
        Aluno Aluno = repository.getReferenceById(id);
        Aluno.excluir();
        repository.save(Aluno);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new DadosDetalhamentoAluno(Aluno));
    }*/
}