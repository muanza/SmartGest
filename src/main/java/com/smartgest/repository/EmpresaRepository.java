package com.smartgest.repository;

import com.smartgest.model.Empresa;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repositório JPA para empresas.
 */
@Stateless
public class EmpresaRepository {

    @PersistenceContext(unitName = "smartgestPU")
    private EntityManager entityManager;

    public List<Empresa> findAll() {
        return entityManager.createQuery("SELECT e FROM Empresa e ORDER BY e.nome", Empresa.class)
                .getResultList();
    }

    public Empresa findByNif(String nif) {
        return entityManager.find(Empresa.class, nif);
    }

    public Empresa save(Empresa empresa) {
        if (findByNif(empresa.getNif()) == null) {
            entityManager.persist(empresa);
            return empresa;
        }
        return entityManager.merge(empresa);
    }

    public void delete(String nif) {
        Empresa empresa = findByNif(nif);
        if (empresa != null) {
            entityManager.remove(empresa);
        }
    }
}
