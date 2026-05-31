package com.smartgest.repository;

import com.smartgest.model.Categoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repositório JPA para categorias.
 */
@Stateless
public class CategoriaRepository {

    @PersistenceContext(unitName = "smartgestPU")
    private EntityManager entityManager;

    public List<Categoria> findAll() {
        return entityManager.createQuery("SELECT c FROM Categoria c ORDER BY c.nome", Categoria.class)
                .getResultList();
    }

    public Categoria findById(Long id) {
        return entityManager.find(Categoria.class, id);
    }
}
