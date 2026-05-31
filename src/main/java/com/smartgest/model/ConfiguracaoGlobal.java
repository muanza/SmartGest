package com.smartgest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Configuração global na base master.
 */
@Entity
@Table(name = "configuracoes_globais")
public class ConfiguracaoGlobal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chave", nullable = false, unique = true, length = 100)
    private String chave;

    @Column(name = "valor", length = 500)
    private String valor;

    public Long getId() { return id; }
    public String getChave() { return chave; }
    public void setChave(String chave) { this.chave = chave; }
    public String getValor() { return valor; }
    public void setValor(String valor) { this.valor = valor; }
}
