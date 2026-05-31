package com.smartgest.service;

import com.smartgest.model.Idioma;
import com.smartgest.repository.IdiomaRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Serviço de consulta de idiomas.
 */
@Stateless
public class IdiomaService {

    @EJB
    private IdiomaRepository idiomaRepository;

    public List<Idioma> findAll() {
        return idiomaRepository.findAll();
    }
}
