package com.smartgest.web;

import com.smartgest.model.Empresa;
import com.smartgest.model.Utilizador;
import com.smartgest.service.EmpresaService;
import com.smartgest.service.UtilizadorService;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UtilizadorBeanTest {

    @Test
    void shouldLoadFirstEmpresaAndDefaultPerfil() {
        UtilizadorBean bean = new UtilizadorBean();
        bean.setUtilizadorService(new UtilizadorService() {
            @Override
            public List<Utilizador> findAll() {
                return Collections.emptyList();
            }
        });
        bean.setEmpresaService(new EmpresaService() {
            @Override
            public List<Empresa> findAll() {
                Empresa empresa = new Empresa();
                empresa.setNif("500000001");
                empresa.setNome("Empresa Teste");
                return Collections.singletonList(empresa);
            }
        });

        bean.init();

        Assertions.assertEquals("500000001", bean.getEmpresaNif());
        Assertions.assertEquals("OPERADOR", bean.getUtilizador().getPerfil());
        Assertions.assertTrue(bean.getUtilizador().isAtivo());
    }
}
