package com.smartgest.web;

import com.smartgest.service.CrmPortfolioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CrmBeanTest {

    @Test
    void shouldFilterPartnerPortfolio() {
        CrmBean bean = new CrmBean();
        bean.setCrmPortfolioService(new CrmPortfolioService());

        bean.init();
        bean.setSelectedPartner("Parceiro Luanda Norte");

        Assertions.assertEquals(2, bean.getPartnerPortfolio().size());
        Assertions.assertEquals(2, bean.getPartners().size());
    }
}
