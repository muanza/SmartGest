# SmartGest

SmartGest é um scaffold pronto para correr/deploy que expande a fundação de um software de faturação multi-tenant com CRM online de licenciamento.

## Funcionalidades incluídas no scaffold expandido

- Arquitetura **database-per-tenant** (tenant identificado pelo NIF da empresa)
- Base **master** para CRM, parceiros, licenças, banners e comunicação em massa
- POS com abertura/fecho de caixa, movimentos, bloqueio do ecrã e full screen
- Gestão de vendas com pré-visualização de factura multilíngua
- Gestão de produtos, stock e utilizadores
- CRM online com gestão de parceiros, licenças, tenants e máquinas activas
- Endpoint REST de CRM em `/api/crm/*`
- Checklist operacional AGT, backups incrementais e sincronização cloud
- Scripts SQL em português para base master e base tenant
- Suporte multilingue inicial (PT/EN/FR/ZH)
- Ficheiros de propriedades para aplicação, base de dados e logging

## Estrutura

```text
SmartGest/
├── pom.xml
├── sql/
│   ├── 01_database.sql
│   ├── 02_schema.sql
│   └── 03_tenant_schema.sql
├── src/main/java/com/smartgest/
│   ├── config/
│   ├── model/view/
│   ├── model/
│   └── util/
├── src/main/resources/
│   ├── META-INF/persistence.xml
│   ├── application.properties
│   ├── database.properties
│   └── logging.properties
├── src/main/webapp/
│   ├── index.xhtml
│   ├── pos.xhtml
│   ├── vendas.xhtml
│   ├── stock.xhtml
│   ├── crm.xhtml
│   ├── operacoes.xhtml
│   ├── META-INF/context.xml
│   └── WEB-INF/
│       ├── web.xml
│       └── faces-config.xml
└── docs/SETUP.md
```

## Tecnologias

- Java 11
- JSF 2.3
- JPA/Hibernate 5.6
- JAX-RS 2.1
- PostgreSQL
- Maven

## Build rápido

```bash
mvn clean test
mvn clean package
```

## Endpoints CRM incluídos

- `GET /api/crm/health`
- `GET /api/crm/licencas/{tenantNif}`
- `GET /api/crm/banners`

## Setup detalhado

Consulte: [`docs/SETUP.md`](docs/SETUP.md)
