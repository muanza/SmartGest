package com.smartgest.config;

import com.smartgest.model.view.ChecklistItem;
import com.smartgest.model.view.ModuleSummary;
import com.smartgest.service.CrmApiClient;
import com.smartgest.service.PortfolioService;
import java.io.Serializable;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "homeBean")
@RequestScoped
public class HomeBean implements Serializable {

    @EJB
    private PortfolioService portfolioService;

    @EJB
    private CrmApiClient crmApiClient;

    public String getMessage() {
        return "SmartGest billing + CRM scaffold ready";
    }

    public List<ModuleSummary> getBillingModules() {
        return portfolioService == null ? Collections.emptyList() : portfolioService.listBillingModules();
    }

    public List<ModuleSummary> getCrmModules() {
        return portfolioService == null ? Collections.emptyList() : portfolioService.listCrmModules();
    }

    public List<ChecklistItem> getComplianceItems() {
        return portfolioService == null ? Collections.emptyList() : portfolioService.complianceChecklist();
    }

    public List<String> getArchitectureHighlights() {
        return portfolioService == null ? Collections.emptyList() : portfolioService.architectureHighlights();
    }

    public String getCrmHealthEndpoint() {
        if (crmApiClient == null) {
            return "https://crm.smartgest.local/api/crm/health";
        }
        URI uri = crmApiClient.buildHealthUri();
        return uri.toString();
    }

    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    public void setCrmApiClient(CrmApiClient crmApiClient) {
        this.crmApiClient = crmApiClient;
    }
}
