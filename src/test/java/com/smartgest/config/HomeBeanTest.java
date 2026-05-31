package com.smartgest.config;

import com.smartgest.model.view.ModuleSummary;
import com.smartgest.service.CrmApiClient;
import com.smartgest.service.PortfolioService;
import java.net.URI;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HomeBeanTest {

    @Test
    void shouldExposeExpandedHomeContent() {
        HomeBean bean = new HomeBean();
        bean.setPortfolioService(new PortfolioService());
        bean.setCrmApiClient(new CrmApiClient() {
            @Override
            public URI buildHealthUri() {
                return URI.create("https://crm.example.test/api/crm/health");
            }
        });

        List<ModuleSummary> modules = bean.getBillingModules();

        Assertions.assertFalse(modules.isEmpty());
        Assertions.assertTrue(bean.getCrmHealthEndpoint().contains("/api/crm/health"));
        Assertions.assertTrue(bean.getMessage().contains("scaffold"));
    }
}
