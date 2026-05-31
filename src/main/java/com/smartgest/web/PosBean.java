package com.smartgest.web;

import com.smartgest.model.view.CashMovementView;
import com.smartgest.model.view.PosStateView;
import com.smartgest.service.PosService;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "posBean")
@ViewScoped
public class PosBean implements Serializable {

    @EJB
    private PosService posService;

    private PosStateView state;
    private List<CashMovementView> movements = Collections.emptyList();
    private BigDecimal movementValue = BigDecimal.ZERO;
    private String movementType = "ENTRADA";
    private String movementDescription = "Movimento operacional";

    @PostConstruct
    public void init() {
        state = posService == null ? new PosStateView() : posService.loadDefaultState();
        movements = posService == null ? new ArrayList<>() : new ArrayList<>(posService.loadRecentMovements());
    }

    public void openCash() {
        state.setEstadoCaixa("ABERTO");
        state.setValorAbertura(movementValue);
        state.setValorEmCaixa(movementValue);
        movements.add(0, new CashMovementView("ABERTURA", movementValue, state.getOperador(), "Abertura manual do caixa", LocalDateTime.now()));
        addMessage("Caixa aberto com sucesso.");
    }

    public void closeCash() {
        movements.add(0, new CashMovementView("FECHO", state.getValorEmCaixa(), state.getOperador(), "Fecho do turno actual", LocalDateTime.now()));
        state.setEstadoCaixa("FECHADO");
        addMessage("Caixa fechado com sucesso.");
    }

    public void registerMovement() {
        BigDecimal current = state.getValorEmCaixa() == null ? BigDecimal.ZERO : state.getValorEmCaixa();
        BigDecimal delta = "SAIDA".equals(movementType) ? movementValue.negate() : movementValue;
        state.setValorEmCaixa(current.add(delta));
        movements.add(0, new CashMovementView(movementType, movementValue, state.getOperador(), movementDescription, LocalDateTime.now()));
        addMessage("Movimento registado.");
    }

    public void toggleLock() {
        state.setEcranBloqueado(!state.isEcranBloqueado());
    }

    public void toggleFullscreen() {
        state.setEcranCompleto(!state.isEcranCompleto());
    }

    private void addMessage(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public PosStateView getState() {
        return state;
    }

    public List<CashMovementView> getMovements() {
        return movements;
    }

    public BigDecimal getMovementValue() {
        return movementValue;
    }

    public void setMovementValue(BigDecimal movementValue) {
        this.movementValue = movementValue;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getMovementDescription() {
        return movementDescription;
    }

    public void setMovementDescription(String movementDescription) {
        this.movementDescription = movementDescription;
    }

    public void setPosService(PosService posService) {
        this.posService = posService;
    }
}
