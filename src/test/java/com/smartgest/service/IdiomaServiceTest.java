package com.smartgest.service;

import com.smartgest.model.Idioma;
import com.smartgest.repository.IdiomaRepository;
import com.smartgest.testutil.ReflectionTestUtils;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IdiomaServiceTest {

    @Test
    void shouldReturnIdiomasFromRepository() {
        Idioma idioma = new Idioma();
        idioma.setCodigo("pt");
        StubIdiomaRepository idiomaRepository = new StubIdiomaRepository();
        idiomaRepository.findAllResult = Collections.singletonList(idioma);
        IdiomaService service = new IdiomaService();
        ReflectionTestUtils.setField(service, "idiomaRepository", idiomaRepository);

        List<Idioma> idiomas = service.findAll();

        Assertions.assertEquals(1, idiomas.size());
        Assertions.assertSame(idioma, idiomas.get(0));
    }

    private static final class StubIdiomaRepository extends IdiomaRepository {

        private List<Idioma> findAllResult = Collections.emptyList();

        @Override
        public List<Idioma> findAll() {
            return findAllResult;
        }
    }
}
