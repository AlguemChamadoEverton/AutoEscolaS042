package br.com.senai.s042.autoescolas042.application.port.out;

import br.com.senai.s042.autoescolas042.application.core.domain.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository {
    UserDetails findByLogin(String login);
    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
}