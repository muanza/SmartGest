package com.smartgest.config;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Bean simples para validar o arranque do JSF.
 */
@ManagedBean(name = "homeBean")
@RequestScoped
public class HomeBean implements Serializable {

    public String getMessage() {
        return "SmartGest foundation ready";
    }
}
