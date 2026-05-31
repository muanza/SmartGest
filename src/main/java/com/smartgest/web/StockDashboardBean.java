package com.smartgest.web;

import com.smartgest.model.view.StockAlertView;
import com.smartgest.service.StockMonitorService;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "stockDashboardBean")
@ViewScoped
public class StockDashboardBean implements Serializable {

    @EJB
    private StockMonitorService stockMonitorService;

    private List<StockAlertView> alerts = Collections.emptyList();

    @PostConstruct
    public void init() {
        alerts = stockMonitorService == null ? Collections.emptyList() : stockMonitorService.loadAlerts();
    }

    public List<StockAlertView> getAlerts() {
        return alerts;
    }

    public void setStockMonitorService(StockMonitorService stockMonitorService) {
        this.stockMonitorService = stockMonitorService;
    }
}
