package com.smartgest.web;

import com.smartgest.model.Empresa;
import com.smartgest.model.Utilizador;
import com.smartgest.service.EmpresaService;
import com.smartgest.service.UtilizadorService;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Bean JSF para CRUD de utilizadores.
 */
@ManagedBean(name = "utilizadorBean")
@ViewScoped
public class UtilizadorBean implements Serializable {

    @EJB
    private UtilizadorService utilizadorService;

    @EJB
    private EmpresaService empresaService;

    private static final List<String> PERFIS = Arrays.asList("ADMINISTRADOR", "OPERADOR");

    private List<Utilizador> utilizadores = Collections.emptyList();
    private List<Empresa> empresas = Collections.emptyList();
    private Utilizador utilizador;
    private String empresaNif;

    @PostConstruct
    public void init() {
        prepareNew();
        reload();
    }

    public void reload() {
        utilizadores = utilizadorService == null ? Collections.emptyList() : utilizadorService.findAll();
        empresas = empresaService == null ? Collections.emptyList() : empresaService.findAll();
        if ((empresaNif == null || empresaNif.isBlank()) && !empresas.isEmpty()) {
            empresaNif = empresas.get(0).getNif();
        }
    }

    public void prepareNew() {
        utilizador = new Utilizador();
        utilizador.setAtivo(true);
        utilizador.setPerfil("OPERADOR");
        empresaNif = null;
    }

    public void edit(Long id) {
        Utilizador found = utilizadorService.findById(id);
        if (found != null) {
            utilizador = found;
            empresaNif = found.getEmpresa() != null ? found.getEmpresa().getNif() : null;
        }
    }

    public void save() {
        utilizadorService.save(utilizador, empresaNif);
        addInfoMessage("Utilizador gravado com sucesso.");
        prepareNew();
        reload();
    }

    public void delete(Long id) {
        utilizadorService.delete(id);
        addInfoMessage("Utilizador removido com sucesso.");
        prepareNew();
        reload();
    }

    private void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public List<Utilizador> getUtilizadores() {
        return utilizadores;
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public List<String> getPerfis() {
        return PERFIS;
    }

    public Utilizador getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(Utilizador utilizador) {
        this.utilizador = utilizador;
    }

    public String getEmpresaNif() {
        return empresaNif;
    }

    public void setEmpresaNif(String empresaNif) {
        this.empresaNif = empresaNif;
    }

    public void setUtilizadorService(UtilizadorService utilizadorService) {
        this.utilizadorService = utilizadorService;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
}
