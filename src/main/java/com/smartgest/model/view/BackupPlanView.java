package com.smartgest.model.view;

import java.io.Serializable;

public class BackupPlanView implements Serializable {

    private String frequencia;
    private String destinoLocal;
    private String sincronizacaoCloud;
    private String retencao;

    public BackupPlanView() {
    }

    public BackupPlanView(String frequencia, String destinoLocal, String sincronizacaoCloud, String retencao) {
        this.frequencia = frequencia;
        this.destinoLocal = destinoLocal;
        this.sincronizacaoCloud = sincronizacaoCloud;
        this.retencao = retencao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public String getDestinoLocal() {
        return destinoLocal;
    }

    public void setDestinoLocal(String destinoLocal) {
        this.destinoLocal = destinoLocal;
    }

    public String getSincronizacaoCloud() {
        return sincronizacaoCloud;
    }

    public void setSincronizacaoCloud(String sincronizacaoCloud) {
        this.sincronizacaoCloud = sincronizacaoCloud;
    }

    public String getRetencao() {
        return retencao;
    }

    public void setRetencao(String retencao) {
        this.retencao = retencao;
    }
}
