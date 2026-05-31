package com.smartgest.repository;

import com.smartgest.model.Utilizador;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repositório JPA para utilizadores.
 */
@Stateless
public class UtilizadorRepository {

    @PersistenceContext(unitName = "smartgestPU")
    private EntityManager entityManager;

    public List<Utilizador> findAll() {
        return entityManager.createQuery("SELECT u FROM Utilizador u ORDER BY u.nome", Utilizador.class)
                .getResultList();
    }

    public Utilizador findById(Long id) {
        return entityManager.find(Utilizador.class, id);
    }

    public Utilizador save(Utilizador utilizador) {
        if (utilizador.getId() == null) {
            entityManager.persist(utilizador);
            return utilizador;
        }
        return entityManager.merge(utilizador);
    }

    public void delete(Long id) {
        Utilizador utilizador = findById(id);
        if (utilizador != null) {
            entityManager.remove(utilizador);
        }
    }
}
