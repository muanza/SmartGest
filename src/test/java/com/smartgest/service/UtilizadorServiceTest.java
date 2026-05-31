package com.smartgest.service;

import com.smartgest.model.Empresa;
import com.smartgest.model.Utilizador;
import com.smartgest.repository.EmpresaRepository;
import com.smartgest.repository.UtilizadorRepository;
import com.smartgest.testutil.ReflectionTestUtils;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilizadorServiceTest {

    @Test
    void shouldDelegateFindOperations() {
        Utilizador utilizador = new Utilizador();
        utilizador.setId(5L);
        StubUtilizadorRepository utilizadorRepository = new StubUtilizadorRepository();
        utilizadorRepository.findAllResult = Collections.singletonList(utilizador);
        utilizadorRepository.findByIdResult = utilizador;
        UtilizadorService service = new UtilizadorService();
        ReflectionTestUtils.setField(service, "utilizadorRepository", utilizadorRepository);
        ReflectionTestUtils.setField(service, "empresaRepository", new StubEmpresaRepository());

        List<Utilizador> utilizadores = service.findAll();

        Assertions.assertEquals(1, utilizadores.size());
        Assertions.assertSame(utilizador, service.findById(5L));
    }

    @Test
    void shouldAttachEmpresaWhenSaving() {
        Empresa empresa = new Empresa();
        empresa.setNif("500000001");
        StubEmpresaRepository empresaRepository = new StubEmpresaRepository();
        empresaRepository.findByNifResult = empresa;
        StubUtilizadorRepository utilizadorRepository = new StubUtilizadorRepository();
        UtilizadorService service = new UtilizadorService();
        ReflectionTestUtils.setField(service, "utilizadorRepository", utilizadorRepository);
        ReflectionTestUtils.setField(service, "empresaRepository", empresaRepository);

        Utilizador utilizador = new Utilizador();
        Utilizador saved = service.save(utilizador, "500000001");

        Assertions.assertSame(utilizador, saved);
        Assertions.assertSame(empresa, utilizador.getEmpresa());
        Assertions.assertSame(utilizador, utilizadorRepository.savedUtilizador);
    }

    @Test
    void shouldDelegateDelete() {
        StubUtilizadorRepository utilizadorRepository = new StubUtilizadorRepository();
        UtilizadorService service = new UtilizadorService();
        ReflectionTestUtils.setField(service, "utilizadorRepository", utilizadorRepository);
        ReflectionTestUtils.setField(service, "empresaRepository", new StubEmpresaRepository());

        service.delete(8L);

        Assertions.assertEquals(8L, utilizadorRepository.deletedId);
    }

    private static final class StubUtilizadorRepository extends UtilizadorRepository {

        private List<Utilizador> findAllResult = Collections.emptyList();
        private Utilizador findByIdResult;
        private Utilizador savedUtilizador;
        private Long deletedId;

        @Override
        public List<Utilizador> findAll() {
            return findAllResult;
        }

        @Override
        public Utilizador findById(Long id) {
            return findByIdResult;
        }

        @Override
        public Utilizador save(Utilizador utilizador) {
            savedUtilizador = utilizador;
            return utilizador;
        }

        @Override
        public void delete(Long id) {
            deletedId = id;
        }
    }

    private static final class StubEmpresaRepository extends EmpresaRepository {

        private Empresa findByNifResult;

        @Override
        public Empresa findByNif(String nif) {
            return findByNifResult;
        }
    }
}
