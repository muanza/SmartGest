package com.smartgest.model.view;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaleItemView implements Serializable {

    private String produto;
    private int quantidade;
    private BigDecimal precoUnitario;
    private BigDecimal subtotal;

    public SaleItemView() {
    }

    public SaleItemView(String produto, int quantidade, BigDecimal precoUnitario, BigDecimal subtotal) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.subtotal = subtotal;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
