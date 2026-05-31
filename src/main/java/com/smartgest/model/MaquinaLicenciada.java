package com.smartgest.model;

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
 * Máquina licenciada associada a uma licença.
 */
@Entity
@Table(name = "maquinas_licenciadas")
public class MaquinaLicenciada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "licenca_id", nullable = false)
    private Licenca licenca;

    @Column(name = "identificador_maquina", nullable = false, length = 200)
    private String identificadorMaquina;

    @Column(name = "descricao", length = 200)
    private String descricao;

    @Column(name = "ativa", nullable = false)
    private boolean ativa = true;

    @Column(name = "ultimo_heartbeat")
    private LocalDateTime ultimoHeartbeat;

    public Long getId() { return id; }
    public Licenca getLicenca() { return licenca; }
    public void setLicenca(Licenca licenca) { this.licenca = licenca; }
    public String getIdentificadorMaquina() { return identificadorMaquina; }
    public void setIdentificadorMaquina(String identificadorMaquina) { this.identificadorMaquina = identificadorMaquina; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public boolean isAtiva() { return ativa; }
    public void setAtiva(boolean ativa) { this.ativa = ativa; }
    public LocalDateTime getUltimoHeartbeat() { return ultimoHeartbeat; }
    public void setUltimoHeartbeat(LocalDateTime ultimoHeartbeat) { this.ultimoHeartbeat = ultimoHeartbeat; }
}
