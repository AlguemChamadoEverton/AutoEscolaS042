package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.mapper.InstrucaoMapper;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.instrucao.DadosAgendamentoInstrucao;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.instrucao.DadosDetalhamentoInstrucao;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Aluno;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrucao;
import br.com.senai.s042.autoescolas042.application.port.out.InstrucaoRepository;
import br.com.senai.s042.autoescolas042.exception.types.aluno.AlunoNaoExisteException;
import br.com.senai.s042.autoescolas042.application.port.out.AlunoRepository;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.ValidacaoException;
import br.com.senai.s042.autoescolas042.application.core.validation.instrucao.interfaces.ValidadorAgendamento;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrutor;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.EspecialidadeNaoInformada;
import br.com.senai.s042.autoescolas042.exception.types.instrucao.InstrutorIndisponivelException;
import br.com.senai.s042.autoescolas042.exception.types.instrutor.InstrutorNaoExisteException;
import br.com.senai.s042.autoescolas042.application.port.out.InstrutorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgendaDeInstrucoes {
    private final InstrucaoRepository repository;
    private final AlunoRepository alunoRepository;
    private final InstrutorRepository instrutorRepository;
    private final InstrucaoMapper mapper;
    private final List<ValidadorAgendamento> validadores;

    public AgendaDeInstrucoes(
            InstrucaoRepository repository,
            AlunoRepository alunoRepository,
            InstrutorRepository instrutorRepository,
            InstrucaoMapper mapper,
            List<ValidadorAgendamento> validadores) {
        this.repository = repository;
        this.alunoRepository = alunoRepository;
        this.instrutorRepository = instrutorRepository;
        this.mapper = mapper;
        this.validadores = validadores;
    }

    @Transactional
    public DadosDetalhamentoInstrucao agendarInstrucao(DadosAgendamentoInstrucao dados) {
        List<String> erros = new ArrayList<>();

        if(!alunoRepository.existsById(dados.idAluno())) {
            throw new AlunoNaoExisteException("ID do aluno informado não existe!");
        }
        if(dados.idInstrutor() != null && !instrutorRepository.existsById(dados.idInstrutor())) {
            throw new InstrutorNaoExisteException("ID do instrutor informado não existe!");
        }

        //Validações das regras de negócio

        validadores.forEach(v -> erros.addAll(v.validar(dados)));

        if(!erros.isEmpty()) {
            throw new ValidacaoException(erros);
        }

        Aluno aluno = alunoRepository.getReferenceById(dados.idAluno());
        Instrutor instrutor = escolherInstrutor(dados);

        if(instrutor == null) {
            throw new InstrutorIndisponivelException("Não há instrutor disponível para a data e hora escolhida!");
        }

        Instrucao instrucao = new Instrucao(
                null,
                aluno,
                instrutor,
                dados.data()
        );
        Instrucao salvo = repository.save(instrucao);
        return mapper.toDetailsDTO(salvo);
    }

    private Instrutor escolherInstrutor(DadosAgendamentoInstrucao dados) {
        if(dados.idInstrutor() != null) {
            return instrutorRepository.getReferenceById(dados.idInstrutor());
        }
        if(dados.especialidade() == null) {
            throw new EspecialidadeNaoInformada("Especialidade é obrigatória quando o instrutor não for informado!");
        }
        return instrutorRepository.escolherInstrutorAleatorioDisponivel(dados.especialidade(), dados.data());
    }
}