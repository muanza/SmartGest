package com.smartgest.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Linha de documento de facturação.
 */
@Entity
@Table(name = "linhas_factura")
public class LinhaFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facturacao_id", nullable = false)
    private Facturacao facturacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "descricao", nullable = false, length = 255)
    private String descricao;

    @Column(name = "quantidade", nullable = false, precision = 12, scale = 3)
    private BigDecimal quantidade;

    @Column(name = "preco_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "desconto", nullable = false, precision = 12, scale = 2)
    private BigDecimal desconto;

    @Column(name = "total_linha", nullable = false, precision = 12, scale = 2)
    private BigDecimal totalLinha;

    public Long getId() { return id; }
    public Facturacao getFacturacao() { return facturacao; }
    public void setFacturacao(Facturacao facturacao) { this.facturacao = facturacao; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getQuantidade() { return quantidade; }
    public void setQuantidade(BigDecimal quantidade) { this.quantidade = quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }
    public void setPrecoUnitario(BigDecimal precoUnitario) { this.precoUnitario = precoUnitario; }
    public BigDecimal getDesconto() { return desconto; }
    public void setDesconto(BigDecimal desconto) { this.desconto = desconto; }
    public BigDecimal getTotalLinha() { return totalLinha; }
    public void setTotalLinha(BigDecimal totalLinha) { this.totalLinha = totalLinha; }
}
