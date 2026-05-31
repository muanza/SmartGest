package com.smartgest.model.view;

import java.io.Serializable;

public class InvoiceTranslationView implements Serializable {

    private String campo;
    private String portugues;
    private String ingles;
    private String frances;
    private String mandarim;

    public InvoiceTranslationView() {
    }

    public InvoiceTranslationView(String campo, String portugues, String ingles, String frances, String mandarim) {
        this.campo = campo;
        this.portugues = portugues;
        this.ingles = ingles;
        this.frances = frances;
        this.mandarim = mandarim;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getPortugues() {
        return portugues;
    }

    public void setPortugues(String portugues) {
        this.portugues = portugues;
    }

    public String getIngles() {
        return ingles;
    }

    public void setIngles(String ingles) {
        this.ingles = ingles;
    }

    public String getFrances() {
        return frances;
    }

    public void setFrances(String frances) {
        this.frances = frances;
    }

    public String getMandarim() {
        return mandarim;
    }

    public void setMandarim(String mandarim) {
        this.mandarim = mandarim;
    }
}
