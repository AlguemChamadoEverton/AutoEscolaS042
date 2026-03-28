package br.com.senai.s042.autoescolas042.application.core.domain;

import br.com.senai.s042.autoescolas042.application.core.domain.enums.Role;


public class Usuario {

    private Long id;
    private String login;
    private String senha;
    private Boolean ativo;
    private Role perfil;

    public Usuario(Long id,
                   String login,
                   String senha,
                   Boolean ativo,
                   Role perfil
    ) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.ativo = ativo;
        this.perfil = perfil;
    }

    public Long getId() { return id; }
    public String getLogin() { return login; }
    public Boolean getAtivo() { return ativo; }
    public Role getPerfil() { return perfil; }

    public void atualizarInformacoes(String login, Boolean ativo, Role perfil) {
        if(login != null) {
            this.login = login;
        }
        if(ativo != null) {
            this.ativo = ativo;
        }
        if(perfil != null) {
            this.perfil = perfil;
        }
    }

    public void excluirUsuario(Long id) {
        this.ativo = false;
    }

    public void atualizarSenha(String senha) {
        this.senha = senha;
    }
}