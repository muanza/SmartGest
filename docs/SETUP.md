# SmartGest - Setup

## 1) Pré-requisitos

- Java 11+
- Maven 3.8+
- PostgreSQL 12+
- WildFly 26+ (recomendado para Java EE 8)

## 2) Configurar PostgreSQL

```sql
CREATE USER smartgest WITH PASSWORD 'smartgest';
ALTER ROLE smartgest CREATEDB;
```

Executar scripts:

```bash
psql -U postgres -f sql/01_database.sql
psql -U postgres -d smartgest_master -f sql/02_schema.sql
psql -U postgres -d smartgest_tenant_500000001 -f sql/03_tenant_schema.sql
```

## 3) Build Maven

```bash
mvn clean test
mvn clean package
```

Resultado esperado: `target/smartgest-1.0.0-SNAPSHOT.war`

## 4) Configurar servidor de aplicação (WildFly)

1. Criar datasource `java:/jdbc/smartgest_master` no WildFly
2. Fazer deploy de `target/smartgest-1.0.0-SNAPSHOT.war` no WildFly
3. Iniciar o serviço e validar os logs de boot

## 5) Executar aplicação

Abrir:

- `http://localhost:8080/smartgest-1.0.0-SNAPSHOT/`
- `http://localhost:8080/smartgest-1.0.0-SNAPSHOT/pos.xhtml`
- `http://localhost:8080/smartgest-1.0.0-SNAPSHOT/vendas.xhtml`
- `http://localhost:8080/smartgest-1.0.0-SNAPSHOT/stock.xhtml`
- `http://localhost:8080/smartgest-1.0.0-SNAPSHOT/crm.xhtml`
- `http://localhost:8080/smartgest-1.0.0-SNAPSHOT/operacoes.xhtml`

## 6) Testar integração API CRM

Configurar endpoint em `src/main/resources/application.properties`:

```properties
app.crm.api.base-url=https://crm.smartgest.local/api
```

Teste de conectividade (exemplo):

```bash
curl -i https://crm.smartgest.local/api/health
```

## 7) Configuração recomendada

- `app.billing.daily-limit=1000`
- `app.backup.local-dir=/var/backups/smartgest`
- `app.backup.incremental-interval-minutes=15`
- `app.cloud.sync.enabled=true`
- `app.crm.api.base-url=https://crm.smartgest.local/api`

## 8) Notas multi-tenant

- Cada empresa usa uma base dedicada: `smartgest_tenant_<NIF>`
- `empresas.nome_base_dados` armazena a base associada ao tenant no catálogo master
- O tenant corrente é resolvido via `TenantContext`
- A base master centraliza parceiros, licenças, banners, comunicação em massa e inventário de máquinas activas

## 9) Deploy passo a passo

1. Criar base master e pelo menos uma base tenant com `sql/01_database.sql`
2. Aplicar `sql/02_schema.sql` na base master
3. Aplicar `sql/03_tenant_schema.sql` em cada base tenant
4. Ajustar `src/main/resources/application.properties` e `src/main/resources/database.properties`
5. Gerar o WAR com `mvn clean package`
6. Publicar o WAR no WildFly
7. Validar a homepage, módulos JSF e endpoints `/api/crm/*`
8. Configurar rotina do sistema operativo para copiar `app.backup.local-dir` para o destino local desejado
9. Configurar o endpoint cloud indicado em `app.cloud.sync.endpoint`
