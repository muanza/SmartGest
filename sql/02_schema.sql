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
   parceiro_nome VARCHAR(150),
   ativo BOOLEAN NOT NULL DEFAULT TRUE,
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS parceiros (
   id BIGSERIAL PRIMARY KEY,
   nome VARCHAR(150) NOT NULL UNIQUE,
   nif VARCHAR(14),
   email VARCHAR(120) NOT NULL,
   activo BOOLEAN NOT NULL DEFAULT TRUE,
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS licencas (
   id BIGSERIAL PRIMARY KEY,
   empresa_nif VARCHAR(14) NOT NULL REFERENCES empresas(nif),
   chave VARCHAR(80) NOT NULL,
   tipo VARCHAR(30) NOT NULL,
   data_inicio DATE NOT NULL,
   data_fim DATE NOT NULL,
   limite_faturas_dia INTEGER NOT NULL DEFAULT 1000,
   activa BOOLEAN NOT NULL DEFAULT TRUE,
   token_activacao VARCHAR(120),
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS banners_publicidade (
   id BIGSERIAL PRIMARY KEY,
   titulo VARCHAR(150) NOT NULL,
   mensagem VARCHAR(255) NOT NULL,
   publico_alvo VARCHAR(120) NOT NULL,
   activo BOOLEAN NOT NULL DEFAULT TRUE,
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS comunicacoes_massa (
   id BIGSERIAL PRIMARY KEY,
   titulo VARCHAR(150) NOT NULL,
   mensagem TEXT NOT NULL,
   canal VARCHAR(50) NOT NULL,
   publico_alvo VARCHAR(120) NOT NULL,
   enviada_em TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS maquinas_licenciadas (
   id BIGSERIAL PRIMARY KEY,
   empresa_nif VARCHAR(14) NOT NULL REFERENCES empresas(nif),
   hostname VARCHAR(100) NOT NULL,
   endereco_ip VARCHAR(50) NOT NULL,
   fingerprint_hardware VARCHAR(120),
   activa BOOLEAN NOT NULL DEFAULT TRUE,
   ultimo_heartbeat TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS configuracoes_globais (
   id BIGSERIAL PRIMARY KEY,
   chave VARCHAR(80) NOT NULL UNIQUE,
   valor VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS auditoria_licenciamento (
   id BIGSERIAL PRIMARY KEY,
   empresa_nif VARCHAR(14) REFERENCES empresas(nif),
   evento VARCHAR(80) NOT NULL,
   detalhes VARCHAR(255),
   criado_em TIMESTAMP NOT NULL DEFAULT NOW()
);

INSERT INTO idiomas (codigo, nome) VALUES
('pt', 'Português'),
('en', 'English'),
('fr', 'Français'),
('zh', '中文')
ON CONFLICT (codigo) DO NOTHING;

INSERT INTO parceiros (nome, nif, email, activo) VALUES
('Parceiro Luanda Norte', '500100001', 'luanda.norte@smartgest.local', TRUE),
('Parceiro Benguela Sul', '500100002', 'benguela.sul@smartgest.local', TRUE)
ON CONFLICT (nome) DO NOTHING;

INSERT INTO empresas (nif, nome, nome_base_dados, codigo_idioma, parceiro_nome, ativo) VALUES
('500000001', 'Empresa Exemplo', 'smartgest_tenant_500000001', 'pt', 'Parceiro Luanda Norte', TRUE)
ON CONFLICT (nif) DO NOTHING;

INSERT INTO licencas (empresa_nif, chave, tipo, data_inicio, data_fim, limite_faturas_dia, activa, token_activacao) VALUES
('500000001', 'LIC-500000001-PRO', 'PRO', CURRENT_DATE, CURRENT_DATE + INTERVAL '365 days', 1000, TRUE, 'TOKEN-CRM-500000001')
ON CONFLICT DO NOTHING;

INSERT INTO banners_publicidade (titulo, mensagem, publico_alvo, activo) VALUES
('Campanha Junho', 'Renovação anual com condições especiais.', 'Todos os tenants', TRUE)
ON CONFLICT DO NOTHING;

INSERT INTO comunicacoes_massa (titulo, mensagem, canal, publico_alvo) VALUES
('Boas práticas de backup', 'Valide backups incrementais e sincronização cloud diariamente.', 'EMAIL', 'Administradores')
ON CONFLICT DO NOTHING;
