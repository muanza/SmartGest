package com.smartgest.config;

import com.smartgest.util.TenantContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MultiTenantIdentifierResolverTest {

    @Test
    void shouldResolveTenantFromContext() {
        MultiTenantIdentifierResolver resolver = new MultiTenantIdentifierResolver();
        TenantContext.setTenantNif("500000001");

        Assertions.assertEquals("500000001", resolver.resolveCurrentTenantIdentifier());

        TenantContext.clear();
    }

    @Test
    void shouldValidateExistingSessions() {
        Assertions.assertTrue(new MultiTenantIdentifierResolver().validateExistingCurrentSessions());
    }
}
