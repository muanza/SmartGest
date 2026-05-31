package com.smartgest.service;

import com.smartgest.model.view.ChecklistItem;
import com.smartgest.model.view.ModuleSummary;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class PortfolioService {

    public List<ModuleSummary> listBillingModules() {
        return Arrays.asList(
                new ModuleSummary("POS", "Abertura e fecho de caixa, bloqueio do ecrã e operação full screen.", "/pos.xhtml", "MVP expandido"),
                new ModuleSummary("Vendas", "Pré-visualização de factura multilíngua, histórico e motor documental.", "/vendas.xhtml", "MVP expandido"),
                new ModuleSummary("Produtos", "CRUD de catálogo e categorias por tenant.", "/produtos.xhtml", "Base existente"),
                new ModuleSummary("Stock", "Alertas, reconciliação e movimentos operacionais.", "/stock.xhtml", "MVP expandido"),
                new ModuleSummary("Utilizadores", "Perfis administrador e operador.", "/utilizadores.xhtml", "Base existente"),
                new ModuleSummary("Operações", "Compliance AGT, backups e sincronização cloud.", "/operacoes.xhtml", "MVP expandido")
        );
    }

    public List<ModuleSummary> listCrmModules() {
        return Arrays.asList(
                new ModuleSummary("CRM Online", "Parceiros, licenças, tenants e activações.", "/crm.xhtml", "MVP expandido"),
                new ModuleSummary("API CRM", "Health check e licenciamento por API configurável.", "/crm.xhtml", "Exposto em /api/crm")
        );
    }

    public List<ChecklistItem> complianceChecklist() {
        return Arrays.asList(
                new ChecklistItem("Conformidade fiscal", "Checklist AGT para numeração, auditoria, retenção e segurança.", "Em preparação"),
                new ChecklistItem("Capacidade operacional", "Meta de 1000 facturas por dia por tenant suportada por base dedicada.", "Arquitectura definida"),
                new ChecklistItem("Multilingua", "Elementos da factura preparados para PT/EN/FR/ZH.", "Scaffold activo"),
                new ChecklistItem("Backup e cloud sync", "Backups incrementais locais com sincronização assíncrona.", "Configuração pronta")
        );
    }

    public List<String> architectureHighlights() {
        return Arrays.asList(
                "Base master para CRM, parceiros, licenças e catálogo global.",
                "Base dedicada por tenant com tenantID igual ao NIF da empresa.",
                "API CRM configurável para activação, renovação, bloqueio e health check.",
                "Separação lógica entre operações de facturação e CRM online."
        );
    }
}
