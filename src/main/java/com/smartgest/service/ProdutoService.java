package com.smartgest.service;

import com.smartgest.model.Categoria;
import com.smartgest.model.Produto;
import com.smartgest.repository.CategoriaRepository;
import com.smartgest.repository.ProdutoRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Serviço de negócio para produtos.
 */
@Stateless
public class ProdutoService {

    @EJB
    private ProdutoRepository produtoRepository;

    @EJB
    private CategoriaRepository categoriaRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public Produto findById(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto save(Produto produto, Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId);
        produto.setCategoria(categoria);
        return produtoRepository.save(produto);
    }

    public void delete(Long id) {
        produtoRepository.delete(id);
    }
}
