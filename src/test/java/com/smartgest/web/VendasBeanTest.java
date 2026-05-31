package com.smartgest.web;

import com.smartgest.service.InvoiceTranslationService;
import com.smartgest.service.VendaOperacionalService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class VendasBeanTest {

    @Test
    void shouldLoadInvoiceLabelsForSelectedLocale() {
        VendasBean bean = new VendasBean();
        bean.setVendaOperacionalService(new VendaOperacionalService());
        bean.setInvoiceTranslationService(new InvoiceTranslationService());

        bean.init();
        bean.setSelectedLocale("fr");
        bean.refreshLocale();

        Assertions.assertEquals("Facture", bean.getSelectedLabels().get("documento"));
        Assertions.assertFalse(bean.getSales().isEmpty());
    }
}
