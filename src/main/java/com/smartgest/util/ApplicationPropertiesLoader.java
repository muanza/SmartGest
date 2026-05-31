package com.smartgest.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ApplicationPropertiesLoader {

    private ApplicationPropertiesLoader() {
    }

    public static Properties load() {
        Properties properties = new Properties();
        try (InputStream stream = ApplicationPropertiesLoader.class
                .getClassLoader()
                .getResourceAsStream("application.properties")) {
            if (stream == null) {
                throw new IllegalStateException("application.properties não encontrado no classpath.");
            }
            properties.load(stream);
            return properties;
        } catch (IOException ex) {
            throw new IllegalStateException("Falha ao carregar configurações da aplicação.", ex);
        }
    }
}
