package com.smartgest.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Carrega configurações de base de dados do ficheiro database.properties.
 */
public final class DatabasePropertiesLoader {

    private DatabasePropertiesLoader() {
    }

    public static Properties load() {
        Properties properties = new Properties();
        try (InputStream stream = DatabasePropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream("database.properties")) {
            if (stream == null) {
                throw new IllegalStateException("database.properties não encontrado no classpath.");
            }
            properties.load(stream);
            return properties;
        } catch (IOException ex) {
            throw new IllegalStateException("Falha ao carregar configurações de base de dados.", ex);
        }
    }
}
