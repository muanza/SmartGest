package com.smartgest.util;

/**
 * Mantém o identificador do tenant (NIF) no contexto da thread atual.
 */
public final class TenantContext {

    private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

    private TenantContext() {
    }

    public static void setTenantNif(String tenantNif) {
        CURRENT_TENANT.set(tenantNif);
    }

    public static String getTenantNif() {
        return CURRENT_TENANT.get();
    }

    public static String getTenantOrDefault() {
        String tenant = CURRENT_TENANT.get();
        return tenant == null || tenant.isBlank() ? "master" : tenant;
    }

    public static void clear() {
        CURRENT_TENANT.remove();
    }
}
