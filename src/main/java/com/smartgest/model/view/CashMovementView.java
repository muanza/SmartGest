package com.smartgest.model.view;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CashMovementView implements Serializable {

    private String tipo;
    private BigDecimal valor;
    private String operador;
    private String descricao;
    private LocalDateTime data;

    public CashMovementView() {
    }

    public CashMovementView(String tipo, BigDecimal valor, String operador, String descricao, LocalDateTime data) {
        this.tipo = tipo;
        this.valor = valor;
        this.operador = operador;
        this.descricao = descricao;
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
