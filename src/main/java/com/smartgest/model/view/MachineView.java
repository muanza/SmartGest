package com.smartgest.model.view;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MachineView implements Serializable {

    private String tenantNif;
    private String hostname;
    private String ip;
    private boolean ativa;
    private LocalDateTime ultimoHeartbeat;

    public MachineView() {
    }

    public MachineView(String tenantNif, String hostname, String ip, boolean ativa, LocalDateTime ultimoHeartbeat) {
        this.tenantNif = tenantNif;
        this.hostname = hostname;
        this.ip = ip;
        this.ativa = ativa;
        this.ultimoHeartbeat = ultimoHeartbeat;
    }

    public String getTenantNif() {
        return tenantNif;
    }

    public void setTenantNif(String tenantNif) {
        this.tenantNif = tenantNif;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public LocalDateTime getUltimoHeartbeat() {
        return ultimoHeartbeat;
    }

    public void setUltimoHeartbeat(LocalDateTime ultimoHeartbeat) {
        this.ultimoHeartbeat = ultimoHeartbeat;
    }
}
