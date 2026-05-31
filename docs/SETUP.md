# SmartGest - Complete Setup & Installation Guide

## 🎯 Índice
1. [Pré-requisitos](#pré-requisitos)
2. [Instalação Local](#instalação-local)
3. [Docker Setup](#docker-setup)
4. [Configuração da Base de Dados](#configuração-da-base-de-dados)
5. [Configuração Inicial](#configuração-inicial)
6. [Troubleshooting](#troubleshooting)

## 🔧 Pré-requisitos

### Necessário
- **Java JDK 11+**: [Download](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- **Maven 3.6.0+**: [Download](https://maven.apache.org/download.cgi)
- **PostgreSQL 12+**: [Download](https://www.postgresql.org/download/)
- **Git**: [Download](https://git-scm.com/)

### Opcional
- **Docker**: [Download](https://www.docker.com/)
- **IDE**: IntelliJ IDEA, Eclipse, VS Code

### Verificar Instalações
```bash
java -version
mvn -version
psql --version
git --version
```

## 📥 Instalação Local

### 1. Clone o Repositório
```bash
git clone https://github.com/muanza/SmartGest.git
cd SmartGest
```

### 2. Configure Variáveis de Ambiente

#### Linux/Mac
```bash
cp .env.example .env
nano .env  # edite com seus valores
```

#### Windows (PowerShell)
```powershell
Copy-Item .env.example -Destination .env
notepad .env
```

### 3. Compile o Projeto
```bash
# Limpar build anterior
mvn clean

# Compilar e instalar todos os módulos
mvn install -DskipTests

# Compilar com testes (opcional)
mvn clean install
```

### 4. Executar Testes
```bash
# Todos os testes
mvn test

# Apenas um módulo
mvn -pl smartgest-billing test
mvn -pl smartgest-crm test
```

## 🐳 Docker Setup

### Quickstart
```bash
# Construir imagens
docker-compose build

# Iniciar serviços
docker-compose up -d

# Verificar status
docker-compose ps
```

### Acesso aos Serviços
- **PostgreSQL**: `localhost:5432`
- **Adminer (DB UI)**: `http://localhost:8081`
- **Redis Cache**: `localhost:6379`

### Parar e Limpar
```bash
# Parar
docker-compose down

# Parar e remover volumes
docker-compose down -v

# Ver logs
docker-compose logs -f postgres
```

## 🗄️ Configuração da Base de Dados

### Criação Manual

#### 1. Conectar ao PostgreSQL
```bash
psql -U postgres
```

#### 2. Criar Usuário
```sql
CREATE USER smartgest WITH PASSWORD 'smartgest_secure_password_123';
ALTER ROLE smartgest CREATEDB;
```

#### 3. Criar Banco de Dados
```sql
CREATE DATABASE smartgest_master OWNER smartgest;
```

#### 4. Executar Scripts SQL
```bash
psql -U smartgest -d smartgest_master -f smartgest-database/sql/init/schema.sql
```

#### 5. Verificar Instalação
```bash
psql -U smartgest -d smartgest_master -c "SELECT version();"
```

### Restaurar Backup (se existir)
```bash
pg_restore -U smartgest -d smartgest_master backup.dump
```

## ⚙️ Configuração Inicial

### 1. Credenciais de Acesso

**CRM - Admin Master:**
- Username: `admin@smartgest.local`
- Password: `admin123`
- Perfil: `ROLE_CRM_ADMIN`

### 2. Criar Parceiro

Acesse o CRM e crie seu primeiro parceiro:
```
Nome: Seu Parceiro Lda
NIF: 123456789
Email: contacto@parceiro.local
Telefone: +258 21 123456
```

### 3. Criar Tenant

```
Nome Empresa: Sua Empresa Lda
NIF: 987654321
Parceiro: Seu Parceiro Lda
Idioma: pt_PT
Moeda: MZN
```

### 4. Gerar Licença

```
Tipo: PROFESSIONAL
Período: 1 ano
Limite: 1000 faturas/dia
Status: ATIVA
```

## 🚀 Deploy em Produção

### Variáveis de Ambiente para Produção

```bash
# .env (produção)
ENVIRONMENT=production
DEBUG=false

DATABASE_HOST=prod-db.example.com
DATABASE_PORT=5432
DATABASE_NAME=smartgest_prod
DATABASE_USER=smartgest_prod
DATABASE_PASSWORD=strong_password_here

JWT_SECRET=your_secret_key_from_secure_generator
ENCRYPTION_KEY=your_encryption_key_from_secure_generator

AGT_LICENSE_KEY=your_agt_license

CLOUD_BACKUP_ENABLED=true
AWS_S3_BUCKET=smartgest-backups-prod
AWS_ACCESS_KEY=prod_access_key
AWS_SECRET_KEY=prod_secret_key
```

### Build para Produção

```bash
mvn clean package -P production
```

## 🔍 Troubleshooting

### Problema: "Connection refused" PostgreSQL

**Solução:**
```bash
# Linux
sudo systemctl status postgresql
sudo systemctl start postgresql

# Mac
brew services list
brew services start postgresql

# Windows - Services Control Panel
services.msc
```

### Problema: "Port 5432 already in use"

```bash
# Find processo
# Linux/Mac
lsof -i :5432

# Windows
netstat -ano | findstr :5432

# Kill processo
kill -9 <PID>  # Linux/Mac
taskkill /PID <PID> /F  # Windows
```

### Problema: Maven não encontra dependências

```bash
# Limpar cache
mvn clean
rm -rf ~/.m2/repository

# Redownload
mvn install
```

### Problema: Erro de Encoding

```bash
# Adicionar ao JAVA_OPTS
export JAVA_OPTS="-Dfile.encoding=UTF-8"
```

### Problema: Docker não inicia

```bash
# Verificar logs
docker-compose logs postgres

# Remover container e tentar novamente
docker-compose down -v
docker-compose up
```

## 📋 Checklist de Setup

- [ ] Java 11+ instalado
- [ ] Maven instalado
- [ ] PostgreSQL instalado e rodando
- [ ] Repositório clonado
- [ ] `.env` configurado
- [ ] `mvn install` concluído com sucesso
- [ ] Base de dados criada
- [ ] Scripts SQL executados
- [ ] Servidor iniciado
- [ ] Testes passando

## 📚 Próximos Passos

1. Leia [ARCHITECTURE.md](ARCHITECTURE.md)
2. Consulte [API.md](API.md)
3. Veja [DATABASE.md](DATABASE.md)
4. Configure [DEPLOYMENT.md](DEPLOYMENT.md)

## 🆘 Suporte

Para dúvidas e problemas:
1. Consulte a [documentação](docs/)
2. Abra uma [issue no GitHub](https://github.com/muanza/SmartGest/issues)
3. Entre em contato: support@smartgest.local

---

**Última atualização: 31 de Maio de 2026**
