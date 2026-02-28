package br.com.senai.s042.autoescolas042.controller;

import br.com.senai.s042.autoescolas042.domain.aluno.DadosDetalhamentoAluno;
import br.com.senai.s042.autoescolas042.domain.instrutor.DadosCadastroInstrutor;
import br.com.senai.s042.autoescolas042.domain.instrutor.DadosDetalhamentoInstrutor;
import br.com.senai.s042.autoescolas042.domain.instrutor.Instrutor;
import br.com.senai.s042.autoescolas042.domain.usuario.DadosAuthenticacao;
import br.com.senai.s042.autoescolas042.domain.usuario.DadosDetalhamentoUsuario;
import br.com.senai.s042.autoescolas042.domain.usuario.Usuario;
import br.com.senai.s042.autoescolas042.domain.usuario.UsuarioRepository;
import br.com.senai.s042.autoescolas042.infra.security.DadosTokenJWT;
import br.com.senai.s042.autoescolas042.infra.security.TokenService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthenticacaoController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private UsuarioRepository repository;
    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAuthenticacao dados){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        Authentication authentication = manager.authenticate(token);
        String tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return  ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<DadosDetalhamentoUsuario> efetuarCadastro(
            @RequestBody @Valid DadosAuthenticacao dados,
            UriComponentsBuilder uriBuilder) {

        BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
        String hash = enconder.encode(dados.senha());
        Usuario usuario = new Usuario(new DadosAuthenticacao(dados.login(), hash));
        repository.save(usuario);
        URI uri = uriBuilder.path("/usuario/{id}")
                .buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario.getId(),usuario.getLogin()));
    }

}
