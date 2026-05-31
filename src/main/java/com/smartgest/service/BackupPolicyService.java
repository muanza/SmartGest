package com.smartgest.service;

import com.smartgest.model.view.BackupPlanView;
import javax.ejb.Stateless;

@Stateless
public class BackupPolicyService {

    public BackupPlanView loadPlan() {
        return new BackupPlanView(
                "Incremental de 15 em 15 minutos",
                "/var/backups/smartgest",
                "Activa - replicação assíncrona para cloud",
                "30 dias local + 180 dias cloud"
        );
    }
}
