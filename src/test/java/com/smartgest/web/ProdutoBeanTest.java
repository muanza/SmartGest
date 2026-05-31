package com.smartgest.web;

import com.smartgest.model.Categoria;
import com.smartgest.model.Produto;
import com.smartgest.service.CategoriaService;
import com.smartgest.service.ProdutoService;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProdutoBeanTest {

    @Test
    void shouldSelectFirstCategoriaOnInit() {
        ProdutoBean bean = new ProdutoBean();
        bean.setProdutoService(new ProdutoService() {
            @Override
            public List<Produto> findAll() {
                return Collections.emptyList();
            }
        });
        bean.setCategoriaService(new CategoriaService() {
            @Override
            public List<Categoria> findAll() {
                Categoria categoria = new Categoria();
                categoria.setId(10L);
                categoria.setNome("Geral");
                return Collections.singletonList(categoria);
            }
        });

        bean.init();

        Assertions.assertEquals(10L, bean.getCategoriaId());
        Assertions.assertEquals(BigDecimal.ZERO, bean.getProduto().getPreco());
    }
}
