package com.smartgest.web;

import com.smartgest.model.Empresa;
import com.smartgest.model.Idioma;
import com.smartgest.service.EmpresaService;
import com.smartgest.service.IdiomaService;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Bean JSF para CRUD de empresas.
 */
@ManagedBean(name = "empresaBean")
@ViewScoped
public class EmpresaBean implements Serializable {

    @EJB
    private EmpresaService empresaService;

    @EJB
    private IdiomaService idiomaService;

    private List<Empresa> empresas = Collections.emptyList();
    private List<Idioma> idiomas = Collections.emptyList();
    private Empresa empresa;
    private String idiomaCodigo;

    @PostConstruct
    public void init() {
        prepareNew();
        reload();
    }

    public void reload() {
        empresas = empresaService == null ? Collections.emptyList() : empresaService.findAll();
        idiomas = idiomaService == null ? Collections.emptyList() : idiomaService.findAll();
        if ((idiomaCodigo == null || idiomaCodigo.isBlank()) && !idiomas.isEmpty()) {
            idiomaCodigo = idiomas.get(0).getCodigo();
        }
    }

    public void prepareNew() {
        empresa = new Empresa();
        empresa.setAtivo(true);
        idiomaCodigo = "pt";
    }

    public void edit(String nif) {
        Empresa found = empresaService.findByNif(nif);
        if (found != null) {
            empresa = found;
            idiomaCodigo = found.getIdioma() != null ? found.getIdioma().getCodigo() : "pt";
        }
    }

    public void save() {
        empresaService.save(empresa, idiomaCodigo);
        addInfoMessage("Empresa gravada com sucesso.");
        prepareNew();
        reload();
    }

    public void delete(String nif) {
        empresaService.delete(nif);
        addInfoMessage("Empresa removida com sucesso.");
        prepareNew();
        reload();
    }

    private void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public List<Empresa> getEmpresas() {
        return empresas;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getIdiomaCodigo() {
        return idiomaCodigo;
    }

    public void setIdiomaCodigo(String idiomaCodigo) {
        this.idiomaCodigo = idiomaCodigo;
    }

    public void setEmpresaService(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    public void setIdiomaService(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }
}
