package com.vinifillos.demoparkapi.service;

import com.vinifillos.demoparkapi.entity.Cliente;
import com.vinifillos.demoparkapi.exception.CpfUniqueViolationException;
import com.vinifillos.demoparkapi.exception.EntityNotFoundException;
import com.vinifillos.demoparkapi.repository.ClienteRepository;
import com.vinifillos.demoparkapi.repository.projection.ClienteProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
        try {
            return clienteRepository.save(cliente);
        } catch (DataIntegrityViolationException ex) {
            throw new CpfUniqueViolationException(String.format("CPF %s não pode ser cadastrado, já existe no sistema", cliente.getCpf()));
        }
    }

    @Transactional
    @ReadOnlyProperty
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format("Cliente id=%s não encontrado no sistema", id))
        );
    }

    @Transactional
    @ReadOnlyProperty
    public Page<ClienteProjection> getAll(Pageable pageable) {
        return clienteRepository.findAllPageable(pageable);
    }

    @Transactional
    @ReadOnlyProperty
    public Cliente buscarPorUsuarioId(Long id) {
        return clienteRepository.findByUsuarioId(id);
    }
}
