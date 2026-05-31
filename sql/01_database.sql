-- Script para PostgreSQL (executar com utilizador administrador)
DROP DATABASE IF EXISTS smartgest_master;
CREATE DATABASE smartgest_master
    WITH ENCODING 'UTF8'
         LC_COLLATE 'C.UTF-8'
         LC_CTYPE 'C.UTF-8'
         TEMPLATE template0;

-- Exemplo de criação de base dedicada por tenant (NIF = 500000001)
-- CREATE DATABASE smartgest_tenant_500000001
--     WITH ENCODING 'UTF8'
--          LC_COLLATE 'C.UTF-8'
--          LC_CTYPE 'C.UTF-8'
--          TEMPLATE template0;
