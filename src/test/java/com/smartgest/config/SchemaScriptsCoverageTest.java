package com.smartgest.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SchemaScriptsCoverageTest {

    @Test
    void shouldContainRequiredMasterTables() throws IOException {
        String schema = Files.readString(Path.of("sql", "02_schema.sql"));

        Assertions.assertAll(
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE idiomas")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE empresas")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE parceiros")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE licencas")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE banners_publicidade")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE comunicacoes_massa")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE maquinas_licenciadas")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE configuracoes_globais")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE auditoria_licenciamento"))
        );
    }

    @Test
    void shouldContainRequiredTenantTables() throws IOException {
        String schema = Files.readString(Path.of("sql", "03_tenant_schema.sql"));

        Assertions.assertAll(
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE utilizadores")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE categorias_produto")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE produtos")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE movimentos_stock")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE caixas")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE facturacoes")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE linhas_factura")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE pagamentos")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE movimentos_caixa")),
                () -> Assertions.assertTrue(schema.contains("CREATE TABLE preferencias_sistema"))
        );
    }
}
