package com.smartgest.service;

import com.smartgest.model.Empresa;
import com.smartgest.model.Idioma;
import com.smartgest.repository.EmpresaRepository;
import com.smartgest.repository.IdiomaRepository;
import com.smartgest.util.DatabaseNamingUtil;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Serviço de negócio para empresas.
 */
@Stateless
public class EmpresaService {

    @EJB
    private EmpresaRepository empresaRepository;

    @EJB
    private IdiomaRepository idiomaRepository;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa findByNif(String nif) {
        return empresaRepository.findByNif(nif);
    }

    public Empresa save(Empresa empresa, String idiomaCodigo) {
        Idioma idioma = idiomaRepository.findByCodigo(idiomaCodigo);
        empresa.setIdioma(idioma);
        if (empresa.getNomeBaseDados() == null || empresa.getNomeBaseDados().isBlank()) {
            empresa.setNomeBaseDados(DatabaseNamingUtil.toDatabaseName(empresa.getNif()));
        }
        return empresaRepository.save(empresa);
    }

    public void delete(String nif) {
        empresaRepository.delete(nif);
    }
}
