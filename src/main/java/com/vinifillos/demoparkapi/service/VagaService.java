package com.vinifillos.demoparkapi.service;

import com.vinifillos.demoparkapi.entity.Vaga;
import com.vinifillos.demoparkapi.exception.CodigoUniqueViolationException;
import com.vinifillos.demoparkapi.exception.EntityNotFoundException;
import com.vinifillos.demoparkapi.repository.VagaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VagaService {

    private VagaRepository vagaRepository;

    @Transactional

    public Vaga salvar(Vaga vaga) {
        try {
            return vagaRepository.save(vaga);
        } catch (DataIntegrityViolationException ex) {
            throw new CodigoUniqueViolationException(
                    String.format("Vaga com código %s já cadastrada", vaga.getCodigo())
            );
        }
    }

    @Transactional
    @ReadOnlyProperty
    public Vaga buscarPorCodigo(String codigo) {
        return vagaRepository.findByCoddigo(codigo).orElseThrow(
                () -> new EntityNotFoundException(String.format("Vaga com código %s não foi encontrada", codigo))
        );
    }
}
