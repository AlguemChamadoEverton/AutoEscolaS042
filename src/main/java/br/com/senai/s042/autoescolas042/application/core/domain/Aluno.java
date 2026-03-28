package br.com.senai.s042.autoescolas042.application.core.domain;

import br.com.senai.s042.autoescolas042.adapter.in.controller.request.aluno.DadosAtualizacaoAluno;
import br.com.senai.s042.autoescolas042.application.core.domain.enums.Especialidade;
import br.com.senai.s042.autoescolas042.application.core.domain.vo.Endereco;
import jakarta.persistence.*;


public class Aluno {

    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    private Endereco endereco;
    private Boolean ativo = true;

    public Aluno(Long id,
                 String nome,
                 String email,
                 String telefone,
                 String cpf,
                 Endereco endereco,
                 Boolean ativo
    ) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.cpf = cpf;
        this.endereco = endereco;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getTelefone() {
        return telefone;
    }
    public String getCpf() {
        return cpf;
    }
    public Endereco getEndereco() {
        return endereco;
    }
    public Boolean getAtivo() {
        return ativo;
    }

    public void atualizarInformacoes(String nome, String telefone, Endereco endereco) {
        if(nome != null) {
            this.nome = nome;
        }
        if(telefone != null) {
            this.telefone = telefone;
        }
        if(endereco != null) {
            this.endereco.atualizarInformacoes(endereco);
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}