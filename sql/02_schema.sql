\connect smartgest_master;

CREATE TABLE IF NOT EXISTS idiomas (
    codigo VARCHAR(10) PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS empresas (
    nif VARCHAR(14) PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    nome_base_dados VARCHAR(80) NOT NULL UNIQUE,
    codigo_idioma VARCHAR(10) NOT NULL REFERENCES idiomas(codigo),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS produtos (
    id BIGSERIAL PRIMARY KEY,
    categoria_id BIGINT NOT NULL REFERENCES categorias(id),
    codigo VARCHAR(50) NOT NULL UNIQUE,
    descricao VARCHAR(200) NOT NULL,
    preco NUMERIC(12,2) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS clientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    nif VARCHAR(14),
    email VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS utilizadores (
    id BIGSERIAL PRIMARY KEY,
    empresa_nif VARCHAR(14) NOT NULL REFERENCES empresas(nif),
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    perfil VARCHAR(30) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS stock (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL REFERENCES produtos(id),
    quantidade INTEGER NOT NULL,
    quantidade_minima INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS vendas (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT REFERENCES clientes(id),
    utilizador_id BIGINT NOT NULL REFERENCES utilizadores(id),
    data_venda TIMESTAMP NOT NULL DEFAULT NOW(),
    total NUMERIC(12,2) NOT NULL
);

CREATE TABLE IF NOT EXISTS faturas (
    id BIGSERIAL PRIMARY KEY,
    venda_id BIGINT NOT NULL REFERENCES vendas(id),
    numero VARCHAR(50) NOT NULL UNIQUE,
    data_emissao TIMESTAMP NOT NULL DEFAULT NOW(),
    total NUMERIC(12,2) NOT NULL,
    idioma_codigo VARCHAR(10) NOT NULL REFERENCES idiomas(codigo)
);

CREATE TABLE IF NOT EXISTS movimentos_caixa (
    id BIGSERIAL PRIMARY KEY,
    utilizador_id BIGINT NOT NULL REFERENCES utilizadores(id),
    tipo_movimento VARCHAR(30) NOT NULL,
    valor NUMERIC(12,2) NOT NULL,
    data_movimento TIMESTAMP NOT NULL DEFAULT NOW(),
    descricao VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS configuracoes (
    id BIGSERIAL PRIMARY KEY,
    empresa_nif VARCHAR(14) NOT NULL REFERENCES empresas(nif),
    chave VARCHAR(80) NOT NULL,
    valor VARCHAR(255) NOT NULL,
    UNIQUE (empresa_nif, chave)
);

CREATE TABLE IF NOT EXISTS licencas (
    id BIGSERIAL PRIMARY KEY,
    empresa_nif VARCHAR(14) NOT NULL REFERENCES empresas(nif),
    tipo VARCHAR(30) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    limite_faturas_dia INTEGER NOT NULL DEFAULT 1000,
    ativa BOOLEAN NOT NULL DEFAULT TRUE
);

INSERT INTO idiomas (codigo, nome) VALUES
('pt', 'Português'),
('en', 'English'),
('fr', 'Français'),
('zh', '中文')
ON CONFLICT (codigo) DO NOTHING;
