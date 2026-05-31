package com.smartgest.web;

import com.smartgest.model.view.BannerView;
import com.smartgest.model.view.BroadcastView;
import com.smartgest.model.view.LicenseStatusView;
import com.smartgest.model.view.MachineView;
import com.smartgest.model.view.PartnerView;
import com.smartgest.service.CrmPortfolioService;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "crmBean")
@ViewScoped
public class CrmBean implements Serializable {

    @EJB
    private CrmPortfolioService crmPortfolioService;

    private List<PartnerView> partners = Collections.emptyList();
    private List<LicenseStatusView> licenses = Collections.emptyList();
    private List<BannerView> banners = Collections.emptyList();
    private List<BroadcastView> broadcasts = Collections.emptyList();
    private List<MachineView> machines = Collections.emptyList();
    private String selectedPartner = "Parceiro Luanda Norte";

    @PostConstruct
    public void init() {
        partners = crmPortfolioService == null ? Collections.emptyList() : crmPortfolioService.listPartners();
        licenses = crmPortfolioService == null ? Collections.emptyList() : crmPortfolioService.listLicenses();
        banners = crmPortfolioService == null ? Collections.emptyList() : crmPortfolioService.listBanners();
        broadcasts = crmPortfolioService == null ? Collections.emptyList() : crmPortfolioService.listBroadcasts();
        machines = crmPortfolioService == null ? Collections.emptyList() : crmPortfolioService.listMachines();
    }

    public List<LicenseStatusView> getPartnerPortfolio() {
        return crmPortfolioService == null ? Collections.emptyList() : crmPortfolioService.listPartnerPortfolio(selectedPartner);
    }

    public List<PartnerView> getPartners() {
        return partners;
    }

    public List<LicenseStatusView> getLicenses() {
        return licenses;
    }

    public List<BannerView> getBanners() {
        return banners;
    }

    public List<BroadcastView> getBroadcasts() {
        return broadcasts;
    }

    public List<MachineView> getMachines() {
        return machines;
    }

    public String getSelectedPartner() {
        return selectedPartner;
    }

    public void setSelectedPartner(String selectedPartner) {
        this.selectedPartner = selectedPartner;
    }

    public void setCrmPortfolioService(CrmPortfolioService crmPortfolioService) {
        this.crmPortfolioService = crmPortfolioService;
    }
}
