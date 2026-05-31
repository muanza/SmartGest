package com.smartgest.model.view;

import java.io.Serializable;

public class ChecklistItem implements Serializable {

    private String titulo;
    private String descricao;
    private String estado;

    public ChecklistItem() {
    }

    public ChecklistItem(String titulo, String descricao, String estado) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.estado = estado;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
