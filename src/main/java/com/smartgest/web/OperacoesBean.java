package com.smartgest.web;

import com.smartgest.model.view.BackupPlanView;
import com.smartgest.model.view.ChecklistItem;
import com.smartgest.service.BackupPolicyService;
import com.smartgest.service.PortfolioService;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "operacoesBean")
@ViewScoped
public class OperacoesBean implements Serializable {

    @EJB
    private BackupPolicyService backupPolicyService;

    @EJB
    private PortfolioService portfolioService;

    private BackupPlanView backupPlan;
    private List<ChecklistItem> checklist = Collections.emptyList();

    @PostConstruct
    public void init() {
        backupPlan = backupPolicyService == null ? new BackupPlanView() : backupPolicyService.loadPlan();
        checklist = portfolioService == null ? Collections.emptyList() : portfolioService.complianceChecklist();
    }

    public BackupPlanView getBackupPlan() {
        return backupPlan;
    }

    public List<ChecklistItem> getChecklist() {
        return checklist;
    }

    public void setBackupPolicyService(BackupPolicyService backupPolicyService) {
        this.backupPolicyService = backupPolicyService;
    }

    public void setPortfolioService(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }
}
