# SmartGest

SmartGest é a fundação de um software de faturação multi-tenant com stack Java + JSF + PostgreSQL + Maven.

## Funcionalidades base incluídas

- Arquitetura **database-per-tenant** (tenant identificado pelo NIF da empresa)
- Configuração JSF (`web.xml`, `faces-config.xml`) com UTF-8
- Configuração JPA/Hibernate (`persistence.xml`) com estratégia multi-tenancy
- Entidades JPA base para módulos nucleares (POS, vendas, produtos, stock, utilizadores)
- Scripts SQL em português para criação da base e schema inicial
- Suporte multilingue inicial (PT/EN/FR/ZH)
- Ficheiros de propriedades para aplicação, base de dados e logging

## Estrutura

```text
SmartGest/
├── pom.xml
├── sql/
│   ├── 01_database.sql
│   └── 02_schema.sql
├── src/main/java/com/smartgest/
│   ├── config/
│   ├── model/
│   └── util/
├── src/main/resources/
│   ├── META-INF/persistence.xml
│   ├── application.properties
│   ├── database.properties
│   └── logging.properties
├── src/main/webapp/
│   ├── index.xhtml
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
- PostgreSQL
- Maven

## Build rápido

```bash
mvn clean test
mvn clean package
```

## Setup detalhado

Consulte: [`docs/SETUP.md`](docs/SETUP.md)
