package com.smartgest.util;

import java.util.Properties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatabasePropertiesLoaderTest {

    @Test
    void shouldLoadDatabasePropertiesFromClasspath() {
        Properties properties = DatabasePropertiesLoader.load();

        Assertions.assertEquals("localhost", properties.getProperty("db.host"));
        Assertions.assertEquals("5432", properties.getProperty("db.port"));
        Assertions.assertEquals("smartgest_master", properties.getProperty("db.name"));
        Assertions.assertEquals("smartgest", properties.getProperty("db.user"));
    }
}
