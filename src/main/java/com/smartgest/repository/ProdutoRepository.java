package com.smartgest.repository;

import com.smartgest.model.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Repositório JPA para produtos.
 */
@Stateless
public class ProdutoRepository {

    @PersistenceContext(unitName = "smartgestPU")
    private EntityManager entityManager;

    public List<Produto> findAll() {
        return entityManager.createQuery("SELECT p FROM Produto p ORDER BY p.descricao", Produto.class)
                .getResultList();
    }

    public Produto findById(Long id) {
        return entityManager.find(Produto.class, id);
    }

    public Produto save(Produto produto) {
        if (produto.getId() == null) {
            entityManager.persist(produto);
            return produto;
        }
        return entityManager.merge(produto);
    }

    public void delete(Long id) {
        Produto produto = findById(id);
        if (produto != null) {
            entityManager.remove(produto);
        }
    }
}
