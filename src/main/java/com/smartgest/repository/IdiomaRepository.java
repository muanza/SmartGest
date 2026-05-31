package com.smartgest.repository;

import com.smartgest.model.Idioma;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repositório JPA para idiomas.
 */
@Stateless
public class IdiomaRepository {

    @PersistenceContext(unitName = "smartgestPU")
    private EntityManager entityManager;

    public List<Idioma> findAll() {
        return entityManager.createQuery("SELECT i FROM Idioma i WHERE i.ativo = TRUE ORDER BY i.nome", Idioma.class)
                .getResultList();
    }

    public Idioma findByCodigo(String codigo) {
        return entityManager.find(Idioma.class, codigo);
    }
}
