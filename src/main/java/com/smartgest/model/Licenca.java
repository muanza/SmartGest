package com.smartgest.model;

import java.time.LocalDate;
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
 * Licença atribuída a um tenant.
 */
@Entity
@Table(name = "licencas")
public class Licenca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_nif", nullable = false)
    private Empresa empresa;

    @Column(name = "chave_licenca", nullable = false, unique = true, length = 120)
    private String chaveLicenca;

    @Column(name = "estado", nullable = false, length = 30)
    private String estado;

    @Column(name = "limite_maquinas", nullable = false)
    private Integer limiteMaquinas;

    @Column(name = "validade", nullable = false)
    private LocalDate validade;

    @Column(name = "criada_em", nullable = false)
    private LocalDateTime criadaEm;

    public Long getId() { return id; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
    public String getChaveLicenca() { return chaveLicenca; }
    public void setChaveLicenca(String chaveLicenca) { this.chaveLicenca = chaveLicenca; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getLimiteMaquinas() { return limiteMaquinas; }
    public void setLimiteMaquinas(Integer limiteMaquinas) { this.limiteMaquinas = limiteMaquinas; }
    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
    public LocalDateTime getCriadaEm() { return criadaEm; }
    public void setCriadaEm(LocalDateTime criadaEm) { this.criadaEm = criadaEm; }
}
