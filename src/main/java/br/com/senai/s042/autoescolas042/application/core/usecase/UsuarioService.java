package br.com.senai.s042.autoescolas042.application.core.usecase;

import br.com.senai.s042.autoescolas042.adapter.in.controller.mapper.UsuarioMapper;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosAtualizacaoSenha;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosAtualizacaoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosListagemUsuario;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import br.com.senai.s042.autoescolas042.application.port.out.UsuarioRepository;
import br.com.senai.s042.autoescolas042.exception.types.usuario.UsuarioNaoExisteException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    private final PasswordEncoder encoder;
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioService(
            PasswordEncoder encoder,
            UsuarioRepository repository,
            UsuarioMapper mapper) {
        this.encoder = encoder;
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional
    public DadosDetalhamentoUsuario cadastrar(DadosCadastroUsuario dados) {
        Usuario usuario = mapper.toDomain(dados);
        Usuario salvo = repository.save(usuario);
        return mapper.toDetailsDTO(salvo);
    }

    public Page<DadosListagemUsuario> listar(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao)
                .map(mapper::toListDTO);
    }

    public DadosDetalhamentoUsuario detalhar(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoExisteException("ID do usuário informado não existe!"));
        return mapper.toDetailsDTO(usuario);
    }

    @Transactional
    public DadosDetalhamentoUsuario atualizar(DadosAtualizacaoUsuario dados) {
        Usuario usuario = repository.findById(dados.id())
                .orElseThrow(() -> new UsuarioNaoExisteException("ID do usuário informado não existe!"));
        usuario.atualizarInformacoes(
                dados.login(),
                dados.ativo(),
                dados.perfil()
        );
        Usuario salvo = repository.save(usuario);
        return mapper.toDetailsDTO(salvo);
    }

    @Transactional
    public void atualizarSenha(DadosAtualizacaoSenha dados) {
        String senhaCriptografada = encoder.encode(dados.senha());
        Usuario usuario = repository.findById(dados.id())
                .orElseThrow(() -> new UsuarioNaoExisteException("ID do usuário informado não existe!"));
        usuario.atualizarSenha(senhaCriptografada);
        repository.save(usuario);
    }

    @Transactional
    public void excluir(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new UsuarioNaoExisteException("ID do usuário informado não existe!"));
        usuario.excluir(id);
        repository.save(usuario);
    }
}