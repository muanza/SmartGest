package com.smartgest.model.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleView implements Serializable {

    private String numero;
    private String cliente;
    private String idioma;
    private String estado;
    private BigDecimal total;

    public SaleView() {
    }

    public SaleView(String numero, String cliente, String idioma, String estado, BigDecimal total) {
        this.numero = numero;
        this.cliente = cliente;
        this.idioma = idioma;
        this.estado = estado;
        this.total = total;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
