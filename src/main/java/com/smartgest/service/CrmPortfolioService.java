package com.smartgest.service;

import com.smartgest.model.view.BannerView;
import com.smartgest.model.view.BroadcastView;
import com.smartgest.model.view.LicenseStatusView;
import com.smartgest.model.view.MachineView;
import com.smartgest.model.view.PartnerView;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.ejb.Stateless;

@Stateless
public class CrmPortfolioService {

    public List<PartnerView> listPartners() {
        return Arrays.asList(
                new PartnerView("Parceiro Luanda Norte", "luanda.norte@smartgest.local", 14, "Activo"),
                new PartnerView("Parceiro Benguela Sul", "benguela.sul@smartgest.local", 9, "Activo")
        );
    }

    public List<LicenseStatusView> listLicenses() {
        return Arrays.asList(
                new LicenseStatusView("500000001", "PRO", "Parceiro Luanda Norte", LocalDate.now().plusMonths(12), true),
                new LicenseStatusView("500000002", "PRO", "Parceiro Luanda Norte", LocalDate.now().plusMonths(6), true),
                new LicenseStatusView("500000003", "START", "Parceiro Benguela Sul", LocalDate.now().plusDays(25), true)
        );
    }

    public List<BannerView> listBanners() {
        return Arrays.asList(
                new BannerView("Campanha Junho", "Desconto de renovação anual.", "Todos os tenants", true),
                new BannerView("Nova funcionalidade", "POS com reconciliação rápida de caixa.", "Operadores", true)
        );
    }

    public List<BroadcastView> listBroadcasts() {
        return Arrays.asList(
                new BroadcastView("Manutenção programada", "Email", "Todas as empresas", LocalDateTime.now().minusDays(2)),
                new BroadcastView("Boas práticas de backup", "Banner + Email", "Administradores", LocalDateTime.now().minusHours(18))
        );
    }

    public List<MachineView> listMachines() {
        return Arrays.asList(
                new MachineView("500000001", "POS-LDA-01", "10.10.0.21", true, LocalDateTime.now().minusMinutes(4)),
                new MachineView("500000002", "POS-LDA-02", "10.10.0.22", true, LocalDateTime.now().minusMinutes(7)),
                new MachineView("500000003", "POS-BGU-01", "10.20.0.10", true, LocalDateTime.now().minusMinutes(9))
        );
    }

    public LicenseStatusView findLicense(String tenantNif) {
        return listLicenses().stream()
                .filter(item -> item.getTenantNif().equals(tenantNif))
                .findFirst()
                .orElse(null);
    }

    public List<LicenseStatusView> listPartnerPortfolio(String partnerName) {
        return listLicenses().stream()
                .filter(item -> item.getParceiro().equals(partnerName))
                .collect(Collectors.toList());
    }
}
