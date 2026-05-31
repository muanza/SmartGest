package com.smartgest.model.view;

import java.io.Serializable;

public class ModuleSummary implements Serializable {

    private String nome;
    private String descricao;
    private String rota;
    private String estado;

    public ModuleSummary() {
    }

    public ModuleSummary(String nome, String descricao, String rota, String estado) {
        this.nome = nome;
        this.descricao = descricao;
        this.rota = rota;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
