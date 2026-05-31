package com.smartgest.web;

import com.smartgest.model.Empresa;
import com.smartgest.model.Idioma;
import com.smartgest.service.EmpresaService;
import com.smartgest.service.IdiomaService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EmpresaBeanTest {

    @Test
    void shouldLoadDefaultIdiomaAndResetNewEmpresa() {
        EmpresaBean bean = new EmpresaBean();
        bean.setEmpresaService(new EmpresaService() {
            @Override
            public List<Empresa> findAll() {
                return Collections.emptyList();
            }
        });
        bean.setIdiomaService(new IdiomaService() {
            @Override
            public List<Idioma> findAll() {
                Idioma idioma = new Idioma();
                idioma.setCodigo("pt");
                idioma.setNome("Português");
                return Collections.singletonList(idioma);
            }
        });

        bean.init();

        Assertions.assertEquals("pt", bean.getIdiomaCodigo());
        Assertions.assertTrue(bean.getEmpresa().isAtivo());
    }
}
