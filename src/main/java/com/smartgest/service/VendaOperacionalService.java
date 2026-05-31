package com.smartgest.service;

import com.smartgest.model.view.SaleItemView;
import com.smartgest.model.view.SaleView;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class VendaOperacionalService {

    public List<SaleItemView> defaultItems() {
        return new ArrayList<>(Arrays.asList(
                new SaleItemView("Leitor código de barras", 1, new BigDecimal("95000.00"), new BigDecimal("95000.00")),
                new SaleItemView("Impressora térmica", 1, new BigDecimal("140000.00"), new BigDecimal("140000.00")),
                new SaleItemView("Papel térmico", 2, new BigDecimal("3500.00"), new BigDecimal("7000.00"))
        ));
    }

    public List<SaleView> recentSales() {
        return Arrays.asList(
                new SaleView("FT 2026/0001", "Cliente Balcão", "Português", "Emitida", new BigDecimal("242000.00")),
                new SaleView("FT 2026/0002", "Hotel Miramar", "English", "Sincronizada", new BigDecimal("480000.00")),
                new SaleView("FT 2026/0003", "Boutique Azure", "Français", "Pendente Cloud", new BigDecimal("92500.00"))
        );
    }

    public BigDecimal calculateTotal(List<SaleItemView> items) {
        return items.stream()
                .map(SaleItemView::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
