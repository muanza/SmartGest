package com.smartgest.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Listener de arranque para inicialização básica da aplicação.
 */
public class AppStartupListener implements ServletContextListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppStartupListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("SmartGest iniciado com suporte multi-tenant.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        LOGGER.info("SmartGest finalizado.");
    }
}
