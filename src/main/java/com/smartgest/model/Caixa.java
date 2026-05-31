package com.smartgest.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
 * Caixa de POS.
 */
@Entity
@Table(name = "caixas")
public class Caixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false, unique = true, length = 40)
    private String codigo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operador_id")
    private Utilizador operador;

    @Column(name = "aberta", nullable = false)
    private boolean aberta;

    @Column(name = "saldo_abertura", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldoAbertura;

    @Column(name = "saldo_atual", nullable = false, precision = 12, scale = 2)
    private BigDecimal saldoAtual;

    @Column(name = "aberta_em")
    private LocalDateTime abertaEm;

    @Column(name = "fechada_em")
    private LocalDateTime fechadaEm;

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public Utilizador getOperador() { return operador; }
    public void setOperador(Utilizador operador) { this.operador = operador; }
    public boolean isAberta() { return aberta; }
    public void setAberta(boolean aberta) { this.aberta = aberta; }
    public BigDecimal getSaldoAbertura() { return saldoAbertura; }
    public void setSaldoAbertura(BigDecimal saldoAbertura) { this.saldoAbertura = saldoAbertura; }
    public BigDecimal getSaldoAtual() { return saldoAtual; }
    public void setSaldoAtual(BigDecimal saldoAtual) { this.saldoAtual = saldoAtual; }
    public LocalDateTime getAbertaEm() { return abertaEm; }
    public void setAbertaEm(LocalDateTime abertaEm) { this.abertaEm = abertaEm; }
    public LocalDateTime getFechadaEm() { return fechadaEm; }
    public void setFechadaEm(LocalDateTime fechadaEm) { this.fechadaEm = fechadaEm; }
}
