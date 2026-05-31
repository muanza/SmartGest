package com.smartgest.service;

import com.smartgest.model.Categoria;
import com.smartgest.model.Produto;
import com.smartgest.repository.CategoriaRepository;
import com.smartgest.repository.ProdutoRepository;
import com.smartgest.testutil.ReflectionTestUtils;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ProdutoServiceTest {

    @Test
    void shouldDelegateFindOperations() {
        Produto produto = new Produto();
        produto.setId(7L);
        StubProdutoRepository produtoRepository = new StubProdutoRepository();
        produtoRepository.findAllResult = Collections.singletonList(produto);
        produtoRepository.findByIdResult = produto;
        ProdutoService service = new ProdutoService();
        ReflectionTestUtils.setField(service, "produtoRepository", produtoRepository);
        ReflectionTestUtils.setField(service, "categoriaRepository", new StubCategoriaRepository());

        List<Produto> produtos = service.findAll();

        Assertions.assertEquals(1, produtos.size());
        Assertions.assertSame(produto, service.findById(7L));
    }

    @Test
    void shouldAttachCategoriaWhenSaving() {
        Categoria categoria = new Categoria();
        categoria.setId(10L);
        StubCategoriaRepository categoriaRepository = new StubCategoriaRepository();
        categoriaRepository.findByIdResult = categoria;
        StubProdutoRepository produtoRepository = new StubProdutoRepository();
        ProdutoService service = new ProdutoService();
        ReflectionTestUtils.setField(service, "produtoRepository", produtoRepository);
        ReflectionTestUtils.setField(service, "categoriaRepository", categoriaRepository);

        Produto produto = new Produto();
        Produto saved = service.save(produto, 10L);

        Assertions.assertSame(produto, saved);
        Assertions.assertSame(categoria, produto.getCategoria());
        Assertions.assertSame(produto, produtoRepository.savedProduto);
    }

    @Test
    void shouldDelegateDelete() {
        StubProdutoRepository produtoRepository = new StubProdutoRepository();
        ProdutoService service = new ProdutoService();
        ReflectionTestUtils.setField(service, "produtoRepository", produtoRepository);
        ReflectionTestUtils.setField(service, "categoriaRepository", new StubCategoriaRepository());

        service.delete(9L);

        Assertions.assertEquals(9L, produtoRepository.deletedId);
    }

    private static final class StubProdutoRepository extends ProdutoRepository {

        private List<Produto> findAllResult = Collections.emptyList();
        private Produto findByIdResult;
        private Produto savedProduto;
        private Long deletedId;

        @Override
        public List<Produto> findAll() {
            return findAllResult;
        }

        @Override
        public Produto findById(Long id) {
            return findByIdResult;
        }

        @Override
        public Produto save(Produto produto) {
            savedProduto = produto;
            return produto;
        }

        @Override
        public void delete(Long id) {
            deletedId = id;
        }
    }

    private static final class StubCategoriaRepository extends CategoriaRepository {

        private Categoria findByIdResult;

        @Override
        public Categoria findById(Long id) {
            return findByIdResult;
        }
    }
}
