package com.smartgest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Idiomas suportados pelo sistema (PT/EN/FR/ZH).
 */
@Entity
@Table(name = "idiomas")
public class Idioma {

    @Id
    @Column(name = "codigo", length = 10, nullable = false)
    private String codigo;

    @Column(name = "nome", nullable = false, length = 80)
    private String nome;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
