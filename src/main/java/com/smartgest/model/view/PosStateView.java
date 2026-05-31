package com.smartgest.model.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class PosStateView implements Serializable {

    private String operador;
    private String estadoCaixa;
    private BigDecimal valorAbertura;
    private BigDecimal valorEmCaixa;
    private boolean ecranBloqueado;
    private boolean ecranCompleto;

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getEstadoCaixa() {
        return estadoCaixa;
    }

    public void setEstadoCaixa(String estadoCaixa) {
        this.estadoCaixa = estadoCaixa;
    }

    public BigDecimal getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(BigDecimal valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public BigDecimal getValorEmCaixa() {
        return valorEmCaixa;
    }

    public void setValorEmCaixa(BigDecimal valorEmCaixa) {
        this.valorEmCaixa = valorEmCaixa;
    }

    public boolean isEcranBloqueado() {
        return ecranBloqueado;
    }

    public void setEcranBloqueado(boolean ecranBloqueado) {
        this.ecranBloqueado = ecranBloqueado;
    }

    public boolean isEcranCompleto() {
        return ecranCompleto;
    }

    public void setEcranCompleto(boolean ecranCompleto) {
        this.ecranCompleto = ecranCompleto;
    }
}
