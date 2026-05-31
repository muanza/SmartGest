package com.smartgest.model.view;

import java.io.Serializable;

public class PartnerView implements Serializable {

    private String nome;
    private String email;
    private int totalTenants;
    private String estado;

    public PartnerView() {
    }

    public PartnerView(String nome, String email, int totalTenants, String estado) {
        this.nome = nome;
        this.email = email;
        this.totalTenants = totalTenants;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalTenants() {
        return totalTenants;
    }

    public void setTotalTenants(int totalTenants) {
        this.totalTenants = totalTenants;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
