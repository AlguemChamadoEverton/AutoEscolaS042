package br.com.senai.s042.autoescolas042.adapter.in.controller.mapper;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.usuario.DadosCadastroUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosDetalhamentoUsuario;
import br.com.senai.s042.autoescolas042.adapter.in.controller.response.usuario.DadosListagemUsuario;
import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    private final PasswordEncoder encoder;

    public UsuarioMapper(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public Usuario toDomain(DadosCadastroUsuario dados) {
        String senhaCriptografada = encoder.encode(dados.senha());
        return new Usuario(
                null,
                dados.login(),
                senhaCriptografada,
                true,
                dados.perfil()
        );
    }

    public DadosDetalhamentoUsuario toDetailsDTO(Usuario usuario) {
        return new DadosDetalhamentoUsuario(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getAtivo(),
                usuario.getPerfil()
        );
    }

    public DadosListagemUsuario toListDTO(Usuario usuario) {
        return new DadosListagemUsuario(
                usuario.getId(),
                usuario.getLogin()
        );
    }
}