package com.smartgest.model.view;

import java.io.Serializable;

public class BannerView implements Serializable {

    private String titulo;
    private String mensagem;
    private String publicoAlvo;
    private boolean ativo;

    public BannerView() {
    }

    public BannerView(String titulo, String mensagem, String publicoAlvo, boolean ativo) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.publicoAlvo = publicoAlvo;
        this.ativo = ativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getPublicoAlvo() {
        return publicoAlvo;
    }

    public void setPublicoAlvo(String publicoAlvo) {
        this.publicoAlvo = publicoAlvo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
