package com.smartgest.model.view;

import java.io.Serializable;
import java.time.LocalDate;

public class LicenseStatusView implements Serializable {

    private String tenantNif;
    private String tipo;
    private String parceiro;
    private LocalDate validaAte;
    private boolean ativa;

    public LicenseStatusView() {
    }

    public LicenseStatusView(String tenantNif, String tipo, String parceiro, LocalDate validaAte, boolean ativa) {
        this.tenantNif = tenantNif;
        this.tipo = tipo;
        this.parceiro = parceiro;
        this.validaAte = validaAte;
        this.ativa = ativa;
    }

    public String getTenantNif() {
        return tenantNif;
    }

    public void setTenantNif(String tenantNif) {
        this.tenantNif = tenantNif;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getParceiro() {
        return parceiro;
    }

    public void setParceiro(String parceiro) {
        this.parceiro = parceiro;
    }

    public LocalDate getValidaAte() {
        return validaAte;
    }

    public void setValidaAte(LocalDate validaAte) {
        this.validaAte = validaAte;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
}
