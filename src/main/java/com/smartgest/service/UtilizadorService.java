package com.smartgest.service;

import com.smartgest.model.Empresa;
import com.smartgest.model.Utilizador;
import com.smartgest.repository.EmpresaRepository;
import com.smartgest.repository.UtilizadorRepository;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * Serviço de negócio para utilizadores.
 */
@Stateless
public class UtilizadorService {

    @EJB
    private UtilizadorRepository utilizadorRepository;

    @EJB
    private EmpresaRepository empresaRepository;

    public List<Utilizador> findAll() {
        return utilizadorRepository.findAll();
    }

    public Utilizador findById(Long id) {
        return utilizadorRepository.findById(id);
    }

    public Utilizador save(Utilizador utilizador, String empresaNif) {
        Empresa empresa = empresaRepository.findByNif(empresaNif);
        utilizador.setEmpresa(empresa);
        return utilizadorRepository.save(utilizador);
    }

    public void delete(Long id) {
        utilizadorRepository.delete(id);
    }
}
