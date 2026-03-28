package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.mapper.AlunoMapper;
import br.com.senai.s042.autoescolas042.adapter.in.controller.mapper.EnderecoMapper;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosAtualizacaoAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosCadastroAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.aluno.DadosDetalhamentoAluno;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.aluno.DadosListagemAluno;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import br.com.senai.s042.autoescolas042.exception.types.aluno.AlunoNaoExisteException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
    private final AlunoRepository repository;
    private final EnderecoMapper enderecoMapper;
    private final AlunoMapper mapper;

    public AlunoService(
            AlunoRepository repository,
            EnderecoMapper enderecoMapper,
            AlunoMapper mapper) {
        this.repository = repository;
        this.enderecoMapper = enderecoMapper;
        this.mapper = mapper;
    }

    @Transactional
    public DadosDetalhamentoAluno cadastrar(DadosCadastroAluno dados) {
        Aluno aluno = mapper.toDomain(dados);
        Aluno salvo = repository.save(aluno);
        return mapper.toDetailsDTO(salvo);
    }

    public Page<DadosListagemAluno> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(mapper::doListDTO);
    }

    public DadosDetalhamentoAluno detalhar(Long id) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new AlunoNaoExisteException("ID do aluno informado não existe!"));
        return mapper.toDetailsDTO(aluno);
    }

    @Transactional
    public DadosDetalhamentoAluno atualizar(DadosAtualizacaoAluno dados) {
        Aluno aluno = repository.findById(dados.id())
                .orElseThrow(() -> new AlunoNaoExisteException("ID do aluno informado não existe!"));
        aluno.atualizarInformacoes(
                dados.nome(),
                dados.telefone(),
                enderecoMapper.toDomain(dados.endereco())
        );
        Aluno salvo = repository.save(aluno);
        return mapper.toDetailsDTO(salvo);
    }

    @Transactional
    public void excluir(Long id) {
        Aluno aluno = repository.findById(id)
                .orElseThrow(() -> new AlunoNaoExisteException("ID do aluno informado não existe!"));
        aluno.excluir();
        repository.save(aluno);
    }
}