package com.smartgest.service;

import com.smartgest.model.Empresa;
import com.smartgest.model.Idioma;
import com.smartgest.repository.EmpresaRepository;
import com.smartgest.repository.IdiomaRepository;
import com.smartgest.testutil.ReflectionTestUtils;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmpresaServiceTest {

    @Test
    void shouldDelegateFindOperations() {
        Empresa empresa = new Empresa();
        empresa.setNif("500000001");
        StubEmpresaRepository empresaRepository = new StubEmpresaRepository();
        empresaRepository.findAllResult = Collections.singletonList(empresa);
        empresaRepository.findByNifResult = empresa;

        EmpresaService service = new EmpresaService();
        ReflectionTestUtils.setField(service, "empresaRepository", empresaRepository);
        ReflectionTestUtils.setField(service, "idiomaRepository", new StubIdiomaRepository());

        List<Empresa> empresas = service.findAll();

        Assertions.assertEquals(1, empresas.size());
        Assertions.assertSame(empresa, service.findByNif("500000001"));
    }

    @Test
    void shouldPopulateIdiomaAndDefaultDatabaseNameWhenSaving() {
        Idioma idioma = new Idioma();
        idioma.setCodigo("pt");
        StubIdiomaRepository idiomaRepository = new StubIdiomaRepository();
        idiomaRepository.findByCodigoResult = idioma;
        StubEmpresaRepository empresaRepository = new StubEmpresaRepository();
        EmpresaService service = new EmpresaService();
        ReflectionTestUtils.setField(service, "empresaRepository", empresaRepository);
        ReflectionTestUtils.setField(service, "idiomaRepository", idiomaRepository);

        Empresa empresa = new Empresa();
        empresa.setNif("500000001");
        empresa.setNomeBaseDados(" ");

        Empresa saved = service.save(empresa, "pt");

        Assertions.assertSame(saved, empresaRepository.savedEmpresa);
        Assertions.assertSame(idioma, saved.getIdioma());
        Assertions.assertEquals("smartgest_tenant_500000001", saved.getNomeBaseDados());
    }

    @Test
    void shouldPreserveExistingDatabaseNameWhenSaving() {
        Idioma idioma = new Idioma();
        idioma.setCodigo("en");
        StubIdiomaRepository idiomaRepository = new StubIdiomaRepository();
        idiomaRepository.findByCodigoResult = idioma;
        StubEmpresaRepository empresaRepository = new StubEmpresaRepository();
        EmpresaService service = new EmpresaService();
        ReflectionTestUtils.setField(service, "empresaRepository", empresaRepository);
        ReflectionTestUtils.setField(service, "idiomaRepository", idiomaRepository);

        Empresa empresa = new Empresa();
        empresa.setNif("500000001");
        empresa.setNomeBaseDados("tenant_custom");

        service.save(empresa, "en");

        Assertions.assertEquals("tenant_custom", empresa.getNomeBaseDados());
        Assertions.assertSame(idioma, empresa.getIdioma());
    }

    @Test
    void shouldDelegateDelete() {
        StubEmpresaRepository empresaRepository = new StubEmpresaRepository();
        EmpresaService service = new EmpresaService();
        ReflectionTestUtils.setField(service, "empresaRepository", empresaRepository);
        ReflectionTestUtils.setField(service, "idiomaRepository", new StubIdiomaRepository());

        service.delete("500000001");

        Assertions.assertEquals("500000001", empresaRepository.deletedNif);
    }

    private static final class StubEmpresaRepository extends EmpresaRepository {

        private List<Empresa> findAllResult = Collections.emptyList();
        private Empresa findByNifResult;
        private Empresa savedEmpresa;
        private String deletedNif;

        @Override
        public List<Empresa> findAll() {
            return findAllResult;
        }

        @Override
        public Empresa findByNif(String nif) {
            return findByNifResult;
        }

        @Override
        public Empresa save(Empresa empresa) {
            savedEmpresa = empresa;
            return empresa;
        }

        @Override
        public void delete(String nif) {
            deletedNif = nif;
        }
    }

    private static final class StubIdiomaRepository extends IdiomaRepository {

        private Idioma findByCodigoResult;

        @Override
        public Idioma findByCodigo(String codigo) {
            return findByCodigoResult;
        }
    }
}
