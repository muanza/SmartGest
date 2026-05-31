package com.smartgest.service;

import com.smartgest.model.view.StockAlertView;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class StockMonitorService {

    public List<StockAlertView> loadAlerts() {
        return Arrays.asList(
                new StockAlertView("PRD-001", "Leitor código de barras", 3, 5, "Repor"),
                new StockAlertView("PRD-002", "Papel térmico", 25, 10, "Normal"),
                new StockAlertView("PRD-003", "Gaveta de caixa", 1, 3, "Crítico")
        );
    }
}
