-- SmartGest tenant schema template
\connect smartgest_tenant_500000001;

DROP TABLE IF EXISTS movimentos_caixa CASCADE;
DROP TABLE IF EXISTS pagamentos CASCADE;
DROP TABLE IF EXISTS linhas_factura CASCADE;
DROP TABLE IF EXISTS facturacoes CASCADE;
DROP TABLE IF EXISTS caixas CASCADE;
DROP TABLE IF EXISTS movimentos_stock CASCADE;
DROP TABLE IF EXISTS produtos CASCADE;
DROP TABLE IF EXISTS categorias_produto CASCADE;
DROP TABLE IF EXISTS utilizadores CASCADE;
DROP TABLE IF EXISTS preferencias_sistema CASCADE;

-- Tabelas legadas mantidas para compatibilidade da UI atual
DROP TABLE IF EXISTS faturas CASCADE;
DROP TABLE IF EXISTS vendas CASCADE;
DROP TABLE IF EXISTS stock CASCADE;
DROP TABLE IF EXISTS categorias CASCADE;
DROP TABLE IF EXISTS clientes CASCADE;
DROP TABLE IF EXISTS configuracoes CASCADE;
DROP TABLE IF EXISTS series_documentos CASCADE;
DROP TABLE IF EXISTS auditoria_operacional CASCADE;

CREATE TABLE utilizadores (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(80) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    nome_completo VARCHAR(200) NOT NULL,
    tipo VARCHAR(20) NOT NULL,
    empresa_nif VARCHAR(14),
    email VARCHAR(120),
    senha_hash VARCHAR(255),
    perfil VARCHAR(30),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE categorias_produto (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    descricao VARCHAR(500),
    ativa BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE produtos (
    id BIGSERIAL PRIMARY KEY,
    categoria_id BIGINT REFERENCES categorias_produto(id),
    codigo VARCHAR(60) UNIQUE NOT NULL,
    nome VARCHAR(200),
    descricao VARCHAR(500) NOT NULL,
    preco_unitario NUMERIC(12,2),
    preco NUMERIC(12,2) NOT NULL,
    iva_percentual NUMERIC(5,2) NOT NULL DEFAULT 14.00,
    stock_atual NUMERIC(12,3) NOT NULL DEFAULT 0,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE movimentos_stock (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL REFERENCES produtos(id),
    tipo VARCHAR(20) NOT NULL,
    quantidade NUMERIC(12,3) NOT NULL,
    motivo VARCHAR(255),
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE caixas (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(40) UNIQUE NOT NULL,
    operador_id BIGINT REFERENCES utilizadores(id),
    aberta BOOLEAN NOT NULL DEFAULT FALSE,
    saldo_abertura NUMERIC(12,2) NOT NULL DEFAULT 0,
    saldo_atual NUMERIC(12,2) NOT NULL DEFAULT 0,
    aberta_em TIMESTAMP,
    fechada_em TIMESTAMP
);

CREATE TABLE facturacoes (
    id BIGSERIAL PRIMARY KEY,
    numero VARCHAR(50) UNIQUE NOT NULL,
    cliente_nome VARCHAR(200) NOT NULL,
    cliente_nif VARCHAR(20),
    subtotal NUMERIC(12,2) NOT NULL,
    imposto NUMERIC(12,2) NOT NULL,
    total NUMERIC(12,2) NOT NULL,
    estado VARCHAR(20) NOT NULL DEFAULT 'EMITIDA',
    emitida_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE linhas_factura (
    id BIGSERIAL PRIMARY KEY,
    facturacao_id BIGINT NOT NULL REFERENCES facturacoes(id) ON DELETE CASCADE,
    produto_id BIGINT REFERENCES produtos(id),
    descricao VARCHAR(255) NOT NULL,
    quantidade NUMERIC(12,3) NOT NULL,
    preco_unitario NUMERIC(12,2) NOT NULL,
    desconto NUMERIC(12,2) NOT NULL DEFAULT 0,
    total_linha NUMERIC(12,2) NOT NULL
);

CREATE TABLE pagamentos (
    id BIGSERIAL PRIMARY KEY,
    facturacao_id BIGINT NOT NULL REFERENCES facturacoes(id) ON DELETE CASCADE,
    metodo VARCHAR(30) NOT NULL,
    valor NUMERIC(12,2) NOT NULL,
    pago_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE movimentos_caixa (
    id BIGSERIAL PRIMARY KEY,
    caixa_id BIGINT NOT NULL REFERENCES caixas(id) ON DELETE CASCADE,
    utilizador_id BIGINT REFERENCES utilizadores(id),
    tipo VARCHAR(20) NOT NULL,
    tipo_movimento VARCHAR(30),
    valor NUMERIC(12,2) NOT NULL,
    descricao VARCHAR(255),
    criado_em TIMESTAMP NOT NULL DEFAULT NOW(),
    data_movimento TIMESTAMP
);

CREATE TABLE preferencias_sistema (
    id BIGSERIAL PRIMARY KEY,
    chave VARCHAR(100) UNIQUE NOT NULL,
    valor VARCHAR(500),
    atualizada_em TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Tabelas legadas para componentes já existentes
CREATE TABLE categorias (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE clientes (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    nif VARCHAR(14),
    email VARCHAR(120)
);

CREATE TABLE stock (
    id BIGSERIAL PRIMARY KEY,
    produto_id BIGINT NOT NULL REFERENCES produtos(id),
    quantidade INTEGER NOT NULL,
    quantidade_minima INTEGER NOT NULL
);

CREATE TABLE vendas (
    id BIGSERIAL PRIMARY KEY,
    cliente_id BIGINT REFERENCES clientes(id),
    utilizador_id BIGINT REFERENCES utilizadores(id),
    data_venda TIMESTAMP NOT NULL DEFAULT NOW(),
    total NUMERIC(12,2) NOT NULL,
    estado VARCHAR(30) NOT NULL DEFAULT 'EMITIDA'
);

CREATE TABLE faturas (
    id BIGSERIAL PRIMARY KEY,
    venda_id BIGINT REFERENCES vendas(id),
    numero VARCHAR(50) NOT NULL UNIQUE,
    data_emissao TIMESTAMP NOT NULL DEFAULT NOW(),
    total NUMERIC(12,2) NOT NULL,
    idioma_codigo VARCHAR(10),
    hash_fiscal VARCHAR(120)
);

CREATE TABLE configuracoes (
    id BIGSERIAL PRIMARY KEY,
    empresa_nif VARCHAR(14),
    chave VARCHAR(80) NOT NULL,
    valor VARCHAR(255) NOT NULL
);

CREATE TABLE series_documentos (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL UNIQUE,
    prefixo VARCHAR(15) NOT NULL,
    proximo_numero BIGINT NOT NULL DEFAULT 1,
    activa BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE auditoria_operacional (
    id BIGSERIAL PRIMARY KEY,
    evento VARCHAR(80) NOT NULL,
    detalhes VARCHAR(255),
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO utilizadores (username, password_hash, nome_completo, tipo, perfil, senha_hash, ativo)
VALUES ('admin', '$2a$10$examplehash', 'Administrador Demo', 'ADMINISTRADOR', 'ADMINISTRADOR', '$2a$10$examplehash', TRUE);

INSERT INTO categorias_produto (nome, descricao, ativa)
VALUES ('Geral', 'Categoria padrão', TRUE);

INSERT INTO categorias (nome) VALUES ('Geral');

INSERT INTO produtos (categoria_id, codigo, nome, descricao, preco_unitario, preco, iva_percentual, stock_atual, ativo)
VALUES (1, 'P001', 'Produto Exemplo', 'Produto para demonstração', 100.00, 100.00, 14.00, 50, TRUE);

INSERT INTO movimentos_stock (produto_id, tipo, quantidade, motivo)
VALUES (1, 'ENTRADA', 50, 'Stock inicial');

INSERT INTO caixas (codigo, operador_id, aberta, saldo_abertura, saldo_atual, aberta_em)
VALUES ('CX-01', 1, TRUE, 1000.00, 1000.00, NOW());

INSERT INTO facturacoes (numero, cliente_nome, cliente_nif, subtotal, imposto, total, estado)
VALUES ('FT 2026/1', 'Cliente Exemplo', '500000500', 100.00, 14.00, 114.00, 'EMITIDA');

INSERT INTO linhas_factura (facturacao_id, produto_id, descricao, quantidade, preco_unitario, desconto, total_linha)
VALUES (1, 1, 'Produto Exemplo', 1, 100.00, 0, 100.00);

INSERT INTO pagamentos (facturacao_id, metodo, valor)
VALUES (1, 'DINHEIRO', 114.00);

INSERT INTO movimentos_caixa (caixa_id, utilizador_id, tipo, tipo_movimento, valor, descricao, data_movimento)
VALUES (1, 1, 'ENTRADA', 'ENTRADA', 114.00, 'Pagamento fatura FT 2026/1', NOW());

INSERT INTO preferencias_sistema (chave, valor)
VALUES ('tema', 'claro');

INSERT INTO stock (produto_id, quantidade, quantidade_minima) VALUES (1, 50, 5);

INSERT INTO clientes (nome, nif, email) VALUES ('Cliente Exemplo', '500000500', 'cliente@exemplo.test');

INSERT INTO vendas (cliente_id, utilizador_id, total, estado) VALUES (1, 1, 114.00, 'EMITIDA');

INSERT INTO faturas (venda_id, numero, total, idioma_codigo, hash_fiscal)
VALUES (1, 'FT-LEGACY-2026/1', 114.00, 'pt', 'HASH-DEMO');

INSERT INTO series_documentos (codigo, prefixo, proximo_numero, activa)
VALUES ('SERIE-2026', 'FT', 1, TRUE);

INSERT INTO auditoria_operacional (evento, detalhes)
VALUES ('SEED', 'Base tenant inicializada com dados de demonstração.');
