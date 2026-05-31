\connect smartgest_tenant_500000001;

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
    activo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS clientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    nif VARCHAR(14),
    email VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS utilizadores (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    email VARCHAR(120) NOT NULL UNIQUE,
    senha_hash VARCHAR(255) NOT NULL,
    perfil VARCHAR(30) NOT NULL,
    activo BOOLEAN NOT NULL DEFAULT TRUE
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
    total NUMERIC(12,2) NOT NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'EMITIDA'
);

CREATE TABLE IF NOT EXISTS faturas (
    id BIGSERIAL PRIMARY KEY,
    venda_id BIGINT NOT NULL REFERENCES vendas(id),
    numero VARCHAR(50) NOT NULL UNIQUE,
    data_emissao TIMESTAMP NOT NULL DEFAULT NOW(),
    total NUMERIC(12,2) NOT NULL,
    idioma_codigo VARCHAR(10) NOT NULL,
    hash_fiscal VARCHAR(120)
);

CREATE TABLE IF NOT EXISTS movimentos_caixa (
    id BIGSERIAL PRIMARY KEY,
    utilizador_id BIGINT NOT NULL REFERENCES utilizadores(id),
    tipo_movimento VARCHAR(30) NOT NULL,
    valor NUMERIC(12,2) NOT NULL,
    data_movimento TIMESTAMP NOT NULL DEFAULT NOW(),
    descricao VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS series_documentos (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    prefixo VARCHAR(15) NOT NULL,
    proximo_numero BIGINT NOT NULL DEFAULT 1,
    activa BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS auditoria_operacional (
    id BIGSERIAL PRIMARY KEY,
    evento VARCHAR(80) NOT NULL,
    detalhes VARCHAR(255),
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO categorias (nome) VALUES ('Geral') ON CONFLICT DO NOTHING;

INSERT INTO series_documentos (codigo, prefixo, proximo_numero, activa)
VALUES ('SERIE-2026', 'FT', 1, TRUE)
ON CONFLICT (codigo) DO NOTHING;
