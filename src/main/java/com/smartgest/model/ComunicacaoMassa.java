package com.smartgest.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Comunicação de massa para empresas licenciadas.
 */
@Entity
@Table(name = "comunicacoes_massa")
public class ComunicacaoMassa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "assunto", nullable = false, length = 200)
    private String assunto;

    @Column(name = "mensagem", nullable = false)
    private String mensagem;

    @Column(name = "canal", nullable = false, length = 50)
    private String canal;

    @Column(name = "programada_para")
    private LocalDateTime programadaPara;

    @Column(name = "enviada_em")
    private LocalDateTime enviadaEm;

    public Long getId() { return id; }
    public String getAssunto() { return assunto; }
    public void setAssunto(String assunto) { this.assunto = assunto; }
    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
    public String getCanal() { return canal; }
    public void setCanal(String canal) { this.canal = canal; }
    public LocalDateTime getProgramadaPara() { return programadaPara; }
    public void setProgramadaPara(LocalDateTime programadaPara) { this.programadaPara = programadaPara; }
    public LocalDateTime getEnviadaEm() { return enviadaEm; }
    public void setEnviadaEm(LocalDateTime enviadaEm) { this.enviadaEm = enviadaEm; }
}
