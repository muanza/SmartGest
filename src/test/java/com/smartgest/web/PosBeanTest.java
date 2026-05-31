package com.smartgest.web;

import com.smartgest.service.PosService;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PosBeanTest {

    @Test
    void shouldLoadDefaultPosState() {
        PosBean bean = new PosBean();
        bean.setPosService(new PosService());

        bean.init();

        Assertions.assertEquals("FECHADO", bean.getState().getEstadoCaixa());
        Assertions.assertTrue(bean.getState().isEcranCompleto());
        Assertions.assertFalse(bean.getMovements().isEmpty());
    }

    @Test
    void shouldRegisterOutgoingMovement() {
        PosBean bean = new PosBean();
        bean.setPosService(new PosService());
        bean.init();
        bean.getState().setValorEmCaixa(new BigDecimal("100.00"));
        bean.setMovementType("SAIDA");
        bean.setMovementValue(new BigDecimal("25.00"));

        bean.registerMovement();

        Assertions.assertEquals(new BigDecimal("75.00"), bean.getState().getValorEmCaixa());
    }
}
