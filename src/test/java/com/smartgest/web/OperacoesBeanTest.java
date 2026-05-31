package com.smartgest.web;

import com.smartgest.service.BackupPolicyService;
import com.smartgest.service.PortfolioService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OperacoesBeanTest {

    @Test
    void shouldLoadBackupAndChecklist() {
        OperacoesBean bean = new OperacoesBean();
        bean.setBackupPolicyService(new BackupPolicyService());
        bean.setPortfolioService(new PortfolioService());

        bean.init();

        Assertions.assertTrue(bean.getBackupPlan().getFrequencia().contains("15"));
        Assertions.assertFalse(bean.getChecklist().isEmpty());
    }
}
