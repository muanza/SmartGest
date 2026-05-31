package com.smartgest.model.view;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BroadcastView implements Serializable {

    private String titulo;
    private String canal;
    private String publico;
    private LocalDateTime dataEnvio;

    public BroadcastView() {
    }

    public BroadcastView(String titulo, String canal, String publico, LocalDateTime dataEnvio) {
        this.titulo = titulo;
        this.canal = canal;
        this.publico = publico;
        this.dataEnvio = dataEnvio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCanal() {
        return canal;
    }

    public void setCanal(String canal) {
        this.canal = canal;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(LocalDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }
}
