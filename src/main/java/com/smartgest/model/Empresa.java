package com.smartgest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Empresa/tenant do SmartGest.
 */
@Entity
@Table(name = "empresas")
public class Empresa {

    @Id
    @Column(name = "nif", length = 14, nullable = false)
    private String nif;

    @Column(name = "nome", nullable = false, length = 150)
    private String nome;

    @Column(name = "nome_base_dados", nullable = false, length = 80)
    private String nomeBaseDados;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_idioma", nullable = false)
    private Idioma idioma;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeBaseDados() {
        return nomeBaseDados;
    }

    public void setNomeBaseDados(String nomeBaseDados) {
        this.nomeBaseDados = nomeBaseDados;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
