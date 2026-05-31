package com.smartgest.service;

import com.smartgest.model.view.CashMovementView;
import com.smartgest.model.view.PosStateView;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class PosService {

    public PosStateView loadDefaultState() {
        PosStateView state = new PosStateView();
        state.setOperador("Operador Caixa 01");
        state.setEstadoCaixa("FECHADO");
        state.setValorAbertura(BigDecimal.ZERO);
        state.setValorEmCaixa(BigDecimal.ZERO);
        state.setEcranBloqueado(false);
        state.setEcranCompleto(true);
        return state;
    }

    public List<CashMovementView> loadRecentMovements() {
        return new ArrayList<>(Arrays.asList(
                new CashMovementView("ABERTURA", new BigDecimal("150000.00"), "Operador Caixa 01", "Abertura do turno da manhã", LocalDateTime.now().minusHours(4)),
                new CashMovementView("ENTRADA", new BigDecimal("25000.00"), "Operador Caixa 01", "Reforço de troco", LocalDateTime.now().minusHours(3)),
                new CashMovementView("SAIDA", new BigDecimal("10000.00"), "Operador Caixa 01", "Pagamento de entrega", LocalDateTime.now().minusHours(1))
        ));
    }
}
