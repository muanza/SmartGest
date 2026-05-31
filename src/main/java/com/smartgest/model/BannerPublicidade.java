package com.smartgest.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Banner publicitário exibido no ecossistema SmartGest.
 */
@Entity
@Table(name = "banners_publicidade")
public class BannerPublicidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 200)
    private String titulo;

    @Column(name = "imagem_url", length = 500)
    private String imagemUrl;

    @Column(name = "link_url", length = 500)
    private String linkUrl;

    @Column(name = "ativo", nullable = false)
    private boolean ativo = true;

    @Column(name = "inicio_exibicao")
    private LocalDateTime inicioExibicao;

    @Column(name = "fim_exibicao")
    private LocalDateTime fimExibicao;

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
    public String getLinkUrl() { return linkUrl; }
    public void setLinkUrl(String linkUrl) { this.linkUrl = linkUrl; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public LocalDateTime getInicioExibicao() { return inicioExibicao; }
    public void setInicioExibicao(LocalDateTime inicioExibicao) { this.inicioExibicao = inicioExibicao; }
    public LocalDateTime getFimExibicao() { return fimExibicao; }
    public void setFimExibicao(LocalDateTime fimExibicao) { this.fimExibicao = fimExibicao; }
}
