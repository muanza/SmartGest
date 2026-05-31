package com.smartgest.web;

import com.smartgest.model.view.InvoiceTranslationView;
import com.smartgest.model.view.SaleItemView;
import com.smartgest.model.view.SaleView;
import com.smartgest.service.InvoiceTranslationService;
import com.smartgest.service.VendaOperacionalService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "vendasBean")
@ViewScoped
public class VendasBean implements Serializable {

    @EJB
    private VendaOperacionalService vendaOperacionalService;

    @EJB
    private InvoiceTranslationService invoiceTranslationService;

    private List<SaleItemView> items = Collections.emptyList();
    private List<SaleView> sales = Collections.emptyList();
    private List<InvoiceTranslationView> labels = Collections.emptyList();
    private Map<String, String> selectedLabels = Collections.emptyMap();
    private String selectedLocale = "pt";

    @PostConstruct
    public void init() {
        items = vendaOperacionalService == null ? Collections.emptyList() : vendaOperacionalService.defaultItems();
        sales = vendaOperacionalService == null ? Collections.emptyList() : vendaOperacionalService.recentSales();
        labels = invoiceTranslationService == null ? Collections.emptyList() : invoiceTranslationService.listInvoiceLabels();
        refreshLocale();
    }

    public void refreshLocale() {
        selectedLabels = invoiceTranslationService == null
                ? Collections.emptyMap()
                : invoiceTranslationService.labelsFor(selectedLocale);
    }

    public BigDecimal getTotal() {
        return vendaOperacionalService == null ? BigDecimal.ZERO : vendaOperacionalService.calculateTotal(items);
    }

    public List<SaleItemView> getItems() {
        return items;
    }

    public List<SaleView> getSales() {
        return sales;
    }

    public List<InvoiceTranslationView> getLabels() {
        return labels;
    }

    public Map<String, String> getSelectedLabels() {
        return selectedLabels;
    }

    public String getSelectedLocale() {
        return selectedLocale;
    }

    public void setSelectedLocale(String selectedLocale) {
        this.selectedLocale = selectedLocale;
    }

    public void setVendaOperacionalService(VendaOperacionalService vendaOperacionalService) {
        this.vendaOperacionalService = vendaOperacionalService;
    }

    public void setInvoiceTranslationService(InvoiceTranslationService invoiceTranslationService) {
        this.invoiceTranslationService = invoiceTranslationService;
    }
}
