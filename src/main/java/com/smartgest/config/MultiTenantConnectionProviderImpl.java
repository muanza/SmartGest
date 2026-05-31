package com.smartgest.config;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.UnknownUnwrapTypeException;

/**
 * Provider de conexão para estratégia DATABASE multi-tenant.
 */
public class MultiTenantConnectionProviderImpl implements MultiTenantConnectionProvider {

    private static final String MASTER_DATASOURCE = "java:comp/env/jdbc/smartgest_master";

    @Override
    public Connection getAnyConnection() throws SQLException {
        return getDataSource(MASTER_DATASOURCE).getConnection();
    }

    @Override
    public void releaseAnyConnection(Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public Connection getConnection(String tenantIdentifier) throws SQLException {
        String jndiName = "java:comp/env/jdbc/" + tenantIdentifier;
        return getDataSource(jndiName).getConnection();
    }

    @Override
    public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
        connection.close();
    }

    @Override
    public boolean supportsAggressiveRelease() {
        return false;
    }

    @Override
    public boolean isUnwrappableAs(Class unwrapType) {
        return MultiTenantConnectionProvider.class.equals(unwrapType)
                || MultiTenantConnectionProviderImpl.class.isAssignableFrom(unwrapType);
    }

    @Override
    public <T> T unwrap(Class<T> unwrapType) {
        if (isUnwrappableAs(unwrapType)) {
            return unwrapType.cast(this);
        }
        throw new UnknownUnwrapTypeException(unwrapType);
    }

    private DataSource getDataSource(String jndiName) {
        try {
            return (DataSource) new InitialContext().lookup(jndiName);
        } catch (NamingException ex) {
            throw new HibernateException("Não foi possível resolver o DataSource: " + jndiName, ex);
        }
    }
}
