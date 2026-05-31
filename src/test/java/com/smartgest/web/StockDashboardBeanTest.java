package com.smartgest.web;

import com.smartgest.service.StockMonitorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StockDashboardBeanTest {

    @Test
    void shouldLoadStockAlerts() {
        StockDashboardBean bean = new StockDashboardBean();
        bean.setStockMonitorService(new StockMonitorService());

        bean.init();

        Assertions.assertEquals(3, bean.getAlerts().size());
        Assertions.assertEquals("PRD-001", bean.getAlerts().get(0).getCodigoProduto());
    }
}
