package com.smartgest.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TenantContextTest {

    @Test
    void shouldStoreAndClearTenant() {
        TenantContext.clear();
        TenantContext.setTenantNif("500000001");

        Assertions.assertEquals("500000001", TenantContext.getTenantNif());
        Assertions.assertEquals("500000001", TenantContext.getTenantOrDefault());

        TenantContext.clear();

        Assertions.assertNull(TenantContext.getTenantNif());
        Assertions.assertEquals("master", TenantContext.getTenantOrDefault());
    }

    @Test
    void shouldReturnDefaultTenantWhenEmpty() {
        TenantContext.clear();
        Assertions.assertEquals("master", TenantContext.getTenantOrDefault());
    }

    @Test
    void shouldBuildDatabaseNameFromNif() {
        Assertions.assertEquals("smartgest_tenant_500000001", DatabaseNamingUtil.toDatabaseName("500000001"));
    }

    @Test
    void shouldRejectInvalidNif() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> DatabaseNamingUtil.toDatabaseName("50A"));
    }
}
