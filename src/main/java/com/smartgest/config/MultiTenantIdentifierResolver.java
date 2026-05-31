package com.smartgest.config;

import com.smartgest.util.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

/**
 * Resolve o tenant atual para Hibernate Multi-Tenancy (DATABASE).
 */
public class MultiTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        return TenantContext.getTenantOrDefault();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
