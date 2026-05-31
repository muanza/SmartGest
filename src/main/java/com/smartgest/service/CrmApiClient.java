package com.smartgest.service;

import com.smartgest.util.ApplicationPropertiesLoader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import javax.ejb.Stateless;

@Stateless
public class CrmApiClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public URI buildHealthUri() {
        return URI.create(baseUrl() + "/crm/health");
    }

    public URI buildLicenseUri(String tenantNif) {
        return URI.create(baseUrl() + "/crm/licencas/" + tenantNif);
    }

    public String fetchHealth() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(buildHealthUri()).GET().build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    private String baseUrl() {
        Properties properties = ApplicationPropertiesLoader.load();
        return properties.getProperty("app.crm.api.base-url", "https://crm.smartgest.local/api");
    }
}
