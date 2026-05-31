package com.smartgest.util;

/**
 * Utilitário para padronizar nomes de base de dados por tenant.
 */
public final class DatabaseNamingUtil {

    private static final String PREFIX = "smartgest_tenant_";

    private DatabaseNamingUtil() {
    }

    public static String toDatabaseName(String nif) {
        if (nif == null || !nif.matches("\\d{9,14}")) {
            throw new IllegalArgumentException("NIF inválido. Utilize apenas dígitos (9-14).");
        }
        return PREFIX + nif;
    }
}
