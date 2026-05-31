package com.smartgest.service;

import com.smartgest.model.view.InvoiceTranslationView;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class InvoiceTranslationService {

    public List<InvoiceTranslationView> listInvoiceLabels() {
        return Arrays.asList(
                new InvoiceTranslationView("Factura", "Factura", "Invoice", "Facture", "发票"),
                new InvoiceTranslationView("Cliente", "Cliente", "Customer", "Client", "客户"),
                new InvoiceTranslationView("Data de Emissão", "Data de Emissão", "Issue Date", "Date d'émission", "开票日期"),
                new InvoiceTranslationView("Total", "Total", "Total", "Total", "总计")
        );
    }

    public Map<String, String> labelsFor(String locale) {
        Map<String, String> labels = new LinkedHashMap<>();
        switch (locale) {
            case "en":
                labels.put("documento", "Invoice");
                labels.put("cliente", "Customer");
                labels.put("data", "Issue Date");
                labels.put("total", "Total");
                break;
            case "fr":
                labels.put("documento", "Facture");
                labels.put("cliente", "Client");
                labels.put("data", "Date d'émission");
                labels.put("total", "Total");
                break;
            case "zh":
                labels.put("documento", "发票");
                labels.put("cliente", "客户");
                labels.put("data", "开票日期");
                labels.put("total", "总计");
                break;
            case "pt":
            default:
                labels.put("documento", "Factura");
                labels.put("cliente", "Cliente");
                labels.put("data", "Data de Emissão");
                labels.put("total", "Total");
                break;
        }
        return labels;
    }
}
