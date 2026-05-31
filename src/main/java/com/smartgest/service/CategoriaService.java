package com.smartgest.service;

import com.smartgest.model.Categoria;
import com.smartgest.repository.CategoriaRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Serviço de consulta de categorias.
 */
@Stateless
public class CategoriaService {

    @EJB
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }
}
