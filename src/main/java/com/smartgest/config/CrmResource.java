package com.smartgest.config;

import com.smartgest.model.view.BannerView;
import com.smartgest.model.view.LicenseStatusView;
import com.smartgest.service.CrmPortfolioService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/crm")
@Produces(MediaType.APPLICATION_JSON)
public class CrmResource {

    @EJB
    private CrmPortfolioService crmPortfolioService;

    @GET
    @Path("/health")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("service", "crm");
        response.put("status", "UP");
        response.put("message", "CRM online pronto para licenciamento.");
        return response;
    }

    @GET
    @Path("/licencas/{tenantNif}")
    public Map<String, Object> license(@PathParam("tenantNif") String tenantNif) {
        LicenseStatusView license = crmPortfolioService.findLicense(tenantNif);
        Map<String, Object> response = new HashMap<>();
        response.put("tenantNif", tenantNif);
        response.put("found", license != null);
        if (license != null) {
            response.put("tipo", license.getTipo());
            response.put("parceiro", license.getParceiro());
            response.put("validaAte", license.getValidaAte().toString());
            response.put("ativa", license.isAtiva());
        }
        return response;
    }

    @GET
    @Path("/banners")
    public List<Map<String, Object>> banners() {
        return crmPortfolioService.listBanners().stream()
                .map(this::toBannerMap)
                .collect(Collectors.toList());
    }

    private Map<String, Object> toBannerMap(BannerView banner) {
        Map<String, Object> response = new HashMap<>();
        response.put("titulo", banner.getTitulo());
        response.put("mensagem", banner.getMensagem());
        response.put("publicoAlvo", banner.getPublicoAlvo());
        response.put("ativo", banner.isAtivo());
        return response;
    }
}
