# SmartGest - Setup

## 1) Pré-requisitos

- Java 11+
- Maven 3.8+
- PostgreSQL 12+
- Tomcat 9+ (ou outro servlet container compatível com Java EE 8)

## 2) Configurar PostgreSQL

```sql
CREATE USER smartgest WITH PASSWORD 'smartgest';
ALTER ROLE smartgest CREATEDB;
```

Executar scripts:

```bash
psql -U postgres -f sql/01_database.sql
psql -U postgres -d smartgest_master -f sql/02_schema.sql
```

## 3) Build Maven

```bash
mvn clean test
mvn clean package
```

Resultado esperado: `target/smartgest-1.0.0-SNAPSHOT.war`

## 4) Configurar servidor de aplicação (Tomcat)

1. Copiar `target/smartgest-1.0.0-SNAPSHOT.war` para `${TOMCAT_HOME}/webapps/`
2. Validar datasource JNDI em `src/main/webapp/META-INF/context.xml`
3. Iniciar Tomcat

## 5) Executar aplicação

Abrir:

- `http://localhost:8080/smartgest-1.0.0-SNAPSHOT/`

## 6) Testar integração API CRM

Configurar endpoint em `src/main/resources/application.properties`:

```properties
app.crm.api.base-url=https://crm.smartgest.local/api
```

Teste de conectividade (exemplo):

```bash
curl -i https://crm.smartgest.local/api/health
```

## 7) Notas multi-tenant

- Cada empresa usa uma base dedicada: `smartgest_tenant_<NIF>`
- `empresas.nome_base_dados` armazena a base associada ao tenant
- O tenant corrente é resolvido via `TenantContext`
