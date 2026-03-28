package br.com.senai.s042.autoescolas042.adapter.out.repository;

import br.com.senai.s042.autoescolas042.adapter.out.repository.entity.InstrutorEntity;
import br.com.senai.s042.autoescolas042.adapter.out.repository.mapper.InstrutorEntityMapper;
import br.com.senai.s042.autoescolas042.adapter.out.repository.persistence.JpaInstrutorRepository;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.Especialidade;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Instrutor;
import br.com.senai.s042.autoescolas042.application.port.out.InstrutorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class InstrutorRepositoryImpl implements InstrutorRepository {
    private final JpaInstrutorRepository repository;
    private final InstrutorEntityMapper mapper;

    public InstrutorRepositoryImpl(
            JpaInstrutorRepository repository,
            InstrutorEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<Instrutor> findAllByAtivoTrue(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(mapper::toDomain);
    }

    @Override
    public Instrutor escolherInstrutorAleatorioDisponivel(
            Especialidade especialidade,
            LocalDateTime data) {
        InstrutorEntity entity = repository.escolherInstrutorAleatorioDisponivel(
                especialidade,
                data
        );
        return mapper.toDomain(entity);
    }

    @Override
    public Boolean findAtivoById(Long id) {
        return repository.findAtivoById(id);
    }

    @Override
    public Instrutor save(Instrutor instrutor) {
        InstrutorEntity entity = mapper.toEntity(instrutor);
        InstrutorEntity salvo = repository.save(entity);
        return mapper.toDomain(salvo);
    }

    @Override
    public Optional<Instrutor> findById(Long id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Instrutor getReferenceById(Long id) {
        InstrutorEntity entity = repository.getReferenceById(id);
        return mapper.toDomain(entity);
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
}