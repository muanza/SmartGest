package com.smartgest.model.view;

import java.io.Serializable;

public class StockAlertView implements Serializable {

    private String codigoProduto;
    private String descricao;
    private int quantidadeAtual;
    private int quantidadeMinima;
    private String estado;

    public StockAlertView() {
    }

    public StockAlertView(String codigoProduto, String descricao, int quantidadeAtual, int quantidadeMinima, String estado) {
        this.codigoProduto = codigoProduto;
        this.descricao = descricao;
        this.quantidadeAtual = quantidadeAtual;
        this.quantidadeMinima = quantidadeMinima;
        this.estado = estado;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
