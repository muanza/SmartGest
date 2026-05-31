package com.smartgest.service;

import com.smartgest.model.Categoria;
import com.smartgest.repository.CategoriaRepository;
import com.smartgest.testutil.ReflectionTestUtils;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CategoriaServiceTest {

    @Test
    void shouldReturnCategoriasFromRepository() {
        Categoria categoria = new Categoria();
        categoria.setId(3L);
        StubCategoriaRepository categoriaRepository = new StubCategoriaRepository();
        categoriaRepository.findAllResult = Collections.singletonList(categoria);
        CategoriaService service = new CategoriaService();
        ReflectionTestUtils.setField(service, "categoriaRepository", categoriaRepository);

        List<Categoria> categorias = service.findAll();

        Assertions.assertEquals(1, categorias.size());
        Assertions.assertSame(categoria, categorias.get(0));
    }

    private static final class StubCategoriaRepository extends CategoriaRepository {

        private List<Categoria> findAllResult = Collections.emptyList();

        @Override
        public List<Categoria> findAll() {
            return findAllResult;
        }
    }
}
