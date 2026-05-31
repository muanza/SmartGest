# SmartGest - Multi-tenant Billing & CRM System

Uma solução completa de faturação multi-tenant com CRM integrado, desenvolvida com Java, JSF, e PostgreSQL, licenciada pela AGT.

## 📋 Visão Geral do Projeto

SmartGest é um software de faturação multi-tenant robusto e escalável, capaz de processar 1000 faturas por dia, com suporte multilingue (Português, Inglês, Francês, Mandarim).

### Componentes Principais

1. **Software de Faturação (SmartGest Billing)**
   - Gestão de POS com abertura/fecho de caixa
   - Gestão de Vendas
   - Gestão de Produtos
   - Gestão de Stock
   - Gestão de Utilizadores

2. **CRM (SmartGest CRM)**
   - Gestão de Parceiros e Tenants
   - Gestão de Licenças
   - Comunicação em Massa
   - Monitoramento de Máquinas com Licenças Ativas

## 🏗️ Estrutura do Projeto

```
smartgest/
├── smartgest-billing/          # Módulo de Faturação
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/smartgest/billing/
│   │   │   │       ├── config/
│   │   │   │       ├── controllers/
│   │   │   │       ├── services/
│   │   │   │       ├── entities/
│   │   │   │       ├── repositories/
│   │   │   │       ├── utils/
│   │   │   │       └── interceptors/
│   │   │   ├── resources/
│   │   │   └── webapp/
│   │   │       ├── WEB-INF/
│   │   │       ├── pages/
│   │   │       └── resources/
│   │   └── test/
│   └── pom.xml
│
├── smartgest-crm/              # Módulo CRM
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/smartgest/crm/
│   │   │   │       ├── config/
│   │   │   │       ├── controllers/
│   │   │   │       ├── services/
│   │   │   │       ├── entities/
│   │   │   │       ├── repositories/
│   │   │   │       ├── api/
│   │   │   │       └── utils/
│   │   │   ├── resources/
│   │   │   └── webapp/
│   │   │       ├── WEB-INF/
│   │   │       ├── pages/
│   │   │       └── resources/
│   │   └── test/
│   └── pom.xml
│
├── smartgest-common/           # Módulo Compartilhado
│   ├── src/
│   │   ├── main/
│   │   │   └── java/
│   │   │       └── com/smartgest/common/
│   │   │           ├── dto/
│   │   │           ├── exceptions/
│   │   │           ├── interfaces/
│   │   │           ├── constants/
│   │   │           └── utils/
│   │   └── test/
│   └── pom.xml
│
├── smartgest-database/         # Módulo de Database
│   ├── sql/
│   │   ├── init/
│   │   │   └── schema.sql
│   │   ├── migrations/
│   │   └── data/
│   │       └── initial_data.sql
│   └── pom.xml
│
├── docs/                       # Documentação
│   ├── SETUP.md
│   ├── DEPLOYMENT.md
│   ├── API.md
│   ├── DATABASE.md
│   └── ARCHITECTURE.md
│
├── config/                     # Arquivos de Configuração
│   ├── application.properties
│   ├── application-dev.properties
│   ├── application-prod.properties
│   └── logback.xml
│
├── pom.xml                     # POM pai
├── .gitignore
├── .env.example
└── docker-compose.yml
```

## 🚀 Quick Start

### Pré-requisitos
- Java 11+
- Maven 3.6+
- PostgreSQL 12+
- Git

### Instalação

1. **Clone o repositório**
   ```bash
   git clone https://github.com/muanza/SmartGest.git
   cd SmartGest
   ```

2. **Configure as variáveis de ambiente**
   ```bash
   cp .env.example .env
   # Edite .env com suas configurações
   ```

3. **Compile o projeto**
   ```bash
   mvn clean install
   ```

4. **Configure o banco de dados**
   ```bash
   # Veja docs/DATABASE.md para instruções detalhadas
   ```

5. **Inicie a aplicação**
   ```bash
   # Para desenvolvimento
   mvn tomcat7:run
   
   # Ou use Docker
   docker-compose up
   ```

## 📚 Documentação

- [Setup e Instalação](docs/SETUP.md)
- [Deploy em Produção](docs/DEPLOYMENT.md)
- [API REST](docs/API.md)
- [Estrutura do Banco de Dados](docs/DATABASE.md)
- [Arquitetura do Sistema](docs/ARCHITECTURE.md)

## 🏛️ Arquitetura

### Arquitetura Multi-tenant
- Database-per-Tenant Strategy
- Tenant ID = NIF da Empresa
- Isolamento de dados garantido

### Stack Tecnológico
- **Frontend**: JSF (JavaServer Faces), PrimeFaces
- **Backend**: Java EE
- **Banco de Dados**: PostgreSQL
- **Build**: Maven
- **Containerização**: Docker
- **API**: REST/JSON
- **Autenticação**: JWT + OAuth2

## 🔐 Segurança

- Criptografia de senhas com BCrypt
- JWT para autenticação de API
- CORS configurado
- SQL Injection Prevention
- OWASP Top 10 compliance

## 🌍 Suporte Multilingue

- Português
- Inglês
- Francês
- Mandarim

Todas as etiquetas de faturação são traduzidas automaticamente conforme a língua selecionada pelo utilizador.

## 📦 Módulos

### Faturação
- POS (Ponto de Venda)
- Gestão de Vendas
- Gestão de Produtos
- Gestão de Stock
- Gestão de Utilizadores
- Relatórios e Análises

### CRM
- Gestão de Parceiros
- Gestão de Tenants
- Gestão de Licenças
- Comunicação em Massa
- Monitoramento de Máquinas
- Gestão de Anúncios

## ⚙️ Configuração

Veja [docs/SETUP.md](docs/SETUP.md) para instruções detalhadas de configuração.

## 📝 Licença

Este projeto é licenciado pela AGT (Autoridade Geral Tributária).

## 👥 Perfis de Utilizador

### Faturação
- **Administrador**: Controle total do sistema
- **Operador**: Operações básicas de faturação

### CRM
- **Administrador**: Gestão total, parceiros, licenças, tenants
- **Parceiro**: Gestão apenas dos tenants do seu portfolio

## 🤝 Contribuição

Para contribuir, por favor:
1. Faça um Fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📞 Suporte

Para suporte, abra uma issue no GitHub ou entre em contato através de support@smartgest.local

## 🔄 Backup e Sincronização

- Backup incremental automático local
- Sincronização com Cloud
- Retenção de 30 dias de backups

---

**Desenvolvido com ❤️ para transformar a faturação em Portugal**
