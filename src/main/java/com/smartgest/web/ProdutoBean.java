package com.smartgest.web;

import com.smartgest.model.Categoria;
import com.smartgest.model.Produto;
import com.smartgest.service.CategoriaService;
import com.smartgest.service.ProdutoService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * Bean JSF para CRUD de produtos.
 */
@ManagedBean(name = "produtoBean")
@ViewScoped
public class ProdutoBean implements Serializable {

    @EJB
    private ProdutoService produtoService;

    @EJB
    private CategoriaService categoriaService;

    private List<Produto> produtos = Collections.emptyList();
    private List<Categoria> categorias = Collections.emptyList();
    private Produto produto;
    private Long categoriaId;

    @PostConstruct
    public void init() {
        prepareNew();
        reload();
    }

    public void reload() {
        produtos = produtoService == null ? Collections.emptyList() : produtoService.findAll();
        categorias = categoriaService == null ? Collections.emptyList() : categoriaService.findAll();
        if (categoriaId == null && !categorias.isEmpty()) {
            categoriaId = categorias.get(0).getId();
        }
    }

    public void prepareNew() {
        produto = new Produto();
        produto.setAtivo(true);
        produto.setPreco(BigDecimal.ZERO);
        categoriaId = null;
    }

    public void edit(Long id) {
        Produto found = produtoService.findById(id);
        if (found != null) {
            produto = found;
            categoriaId = found.getCategoria() != null ? found.getCategoria().getId() : null;
        }
    }

    public void save() {
        produtoService.save(produto, categoriaId);
        addInfoMessage("Produto gravado com sucesso.");
        prepareNew();
        reload();
    }

    public void delete(Long id) {
        produtoService.delete(id);
        addInfoMessage("Produto removido com sucesso.");
        prepareNew();
        reload();
    }

    private void addInfoMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public void setProdutoService(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    public void setCategoriaService(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
}
