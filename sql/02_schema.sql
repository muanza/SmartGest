-- SmartGest master schema (CRM/licenciamento)
\connect smartgest_master;

DROP TABLE IF EXISTS auditoria_licenciamento CASCADE;
DROP TABLE IF EXISTS maquinas_licenciadas CASCADE;
DROP TABLE IF EXISTS comunicacoes_massa CASCADE;
DROP TABLE IF EXISTS banners_publicidade CASCADE;
DROP TABLE IF EXISTS licencas CASCADE;
DROP TABLE IF EXISTS configuracoes_globais CASCADE;
DROP TABLE IF EXISTS empresas CASCADE;
DROP TABLE IF EXISTS parceiros CASCADE;
DROP TABLE IF EXISTS idiomas CASCADE;

CREATE TABLE idiomas (
    codigo VARCHAR(10) PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE parceiros (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(200) NOT NULL,
    nif VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(200),
    telefone VARCHAR(50),
    ativo BOOLEAN NOT NULL DEFAULT TRUE,
    criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE empresas (
   nif VARCHAR(14) PRIMARY KEY,
   nome VARCHAR(150) NOT NULL,
   nome_base_dados VARCHAR(80) NOT NULL UNIQUE,
   codigo_idioma VARCHAR(10) NOT NULL REFERENCES idiomas(codigo),
   parceiro_nome VARCHAR(200),
   ativo BOOLEAN NOT NULL DEFAULT TRUE,
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE licencas (
   id BIGSERIAL PRIMARY KEY,
   empresa_nif VARCHAR(14) NOT NULL REFERENCES empresas(nif),
   chave_licenca VARCHAR(120) UNIQUE NOT NULL,
   estado VARCHAR(30) NOT NULL,
   limite_maquinas INTEGER NOT NULL DEFAULT 1,
   validade DATE NOT NULL,
   criada_em TIMESTAMP NOT NULL DEFAULT NOW(),
   atualizada_em TIMESTAMP
);

CREATE TABLE banners_publicidade (
   id BIGSERIAL PRIMARY KEY,
   titulo VARCHAR(200) NOT NULL,
   imagem_url VARCHAR(500),
   link_url VARCHAR(500),
   ativo BOOLEAN NOT NULL DEFAULT TRUE,
   inicio_exibicao TIMESTAMP,
   fim_exibicao TIMESTAMP,
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE comunicacoes_massa (
   id BIGSERIAL PRIMARY KEY,
   assunto VARCHAR(200) NOT NULL,
   mensagem TEXT NOT NULL,
   canal VARCHAR(50) NOT NULL,
   programada_para TIMESTAMP,
   enviada_em TIMESTAMP,
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE maquinas_licenciadas (
   id BIGSERIAL PRIMARY KEY,
   licenca_id BIGINT NOT NULL REFERENCES licencas(id) ON DELETE CASCADE,
   identificador_maquina VARCHAR(200) NOT NULL,
   descricao VARCHAR(200),
   ativa BOOLEAN NOT NULL DEFAULT TRUE,
   ultimo_heartbeat TIMESTAMP,
   criada_em TIMESTAMP NOT NULL DEFAULT NOW(),
   UNIQUE (licenca_id, identificador_maquina)
);

CREATE TABLE configuracoes_globais (
   id BIGSERIAL PRIMARY KEY,
   chave VARCHAR(100) UNIQUE NOT NULL,
   valor VARCHAR(500)
);

CREATE TABLE auditoria_licenciamento (
   id BIGSERIAL PRIMARY KEY,
   licenca_id BIGINT REFERENCES licencas(id) ON DELETE SET NULL,
   evento VARCHAR(100) NOT NULL,
   detalhe TEXT,
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO idiomas (codigo, nome) VALUES
('pt', 'Português'),
('en', 'English'),
('fr', 'Français'),
('zh', '中文');

INSERT INTO parceiros (nome, nif, email, telefone, ativo) VALUES
('Parceiro Demo', '500100100', 'parceiro@smartgest.test', '+351900000001', TRUE);

INSERT INTO empresas (nif, nome, nome_base_dados, codigo_idioma, parceiro_nome, ativo) VALUES
('500000001', 'Empresa Exemplo', 'smartgest_tenant_500000001', 'pt', 'Parceiro Demo', TRUE);

INSERT INTO licencas (empresa_nif, chave_licenca, estado, limite_maquinas, validade)
VALUES ('500000001', 'LIC-500000001-AAAA-BBBB', 'ATIVA', 3, CURRENT_DATE + INTERVAL '365 days');

INSERT INTO banners_publicidade (titulo, imagem_url, link_url, ativo)
VALUES ('Promo SmartGest', 'https://cdn.smartgest.test/banner1.png', 'https://smartgest.test/promo', TRUE);

INSERT INTO comunicacoes_massa (assunto, mensagem, canal)
VALUES ('Bem-vindo ao SmartGest', 'Configuração inicial concluída com sucesso.', 'EMAIL');

INSERT INTO maquinas_licenciadas (licenca_id, identificador_maquina, descricao)
VALUES (1, 'MACHINE-001', 'Posto principal');

INSERT INTO configuracoes_globais (chave, valor)
VALUES ('agt.modo', 'producao');

INSERT INTO auditoria_licenciamento (licenca_id, evento, detalhe)
VALUES (1, 'ATIVACAO', 'Licença inicial ativada para tenant 500000001');
