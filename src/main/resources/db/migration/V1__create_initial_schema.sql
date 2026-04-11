CREATE TABLE instrutores
(
    id            BIGSERIAL    NOT NULL,
    nome          VARCHAR(100) NOT NULL,
    email         VARCHAR(100) NOT NULL UNIQUE,
    cnh           VARCHAR(11)  NOT NULL UNIQUE,
    especialidade VARCHAR(20)  NOT NULL,
    logradouro    VARCHAR(100) NOT NULL,
    numero        VARCHAR(10),
    complemento   VARCHAR(100),
    bairro        VARCHAR(100) NOT NULL,
    cidade        VARCHAR(100) NOT NULL,
    uf            VARCHAR(2)   NOT NULL,
    cep           VARCHAR(8)   NOT NULL,
    telefone      VARCHAR(20)  NOT NULL,
    ativo         BOOLEAN      NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);

CREATE TABLE usuarios
(
    id     BIGSERIAL    NOT NULL,
    login  VARCHAR(100) NOT NULL UNIQUE,
    senha  VARCHAR(255) NOT NULL,
    ativo  BOOLEAN      NOT NULL DEFAULT TRUE,
    perfil VARCHAR(20)  NOT NULL DEFAULT 'USER',

    PRIMARY KEY (id)
);

CREATE TABLE alunos
(
    id          BIGSERIAL    NOT NULL,
    nome        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    telefone    VARCHAR(20)  NOT NULL,
    cpf         VARCHAR(11)  NOT NULL UNIQUE,
    logradouro  VARCHAR(100) NOT NULL,
    numero      VARCHAR(10),
    complemento VARCHAR(100),
    bairro      VARCHAR(100) NOT NULL,
    cidade      VARCHAR(100) NOT NULL,
    uf          VARCHAR(2)   NOT NULL,
    cep         VARCHAR(8)   NOT NULL,
    ativo       BOOLEAN      NOT NULL DEFAULT TRUE,

    PRIMARY KEY (id)
);

CREATE TABLE instrucoes
(
    id           BIGSERIAL NOT NULL,
    aluno_id     BIGINT    NOT NULL,
    instrutor_id BIGINT    NOT NULL,
    data         TIMESTAMP NOT NULL,

    PRIMARY KEY (id),
    CONSTRAINT fk_instrucoes_aluno_id FOREIGN KEY (aluno_id) REFERENCES alunos (id),
    CONSTRAINT fk_instrucoes_instrutor_id FOREIGN KEY (instrutor_id) REFERENCES instrutores (id)
);