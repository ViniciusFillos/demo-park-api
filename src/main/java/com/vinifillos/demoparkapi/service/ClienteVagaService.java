package com.vinifillos.demoparkapi.service;


import com.vinifillos.demoparkapi.entity.ClienteVaga;
import com.vinifillos.demoparkapi.exception.EntityNotFoundException;
import com.vinifillos.demoparkapi.repository.ClienteVagaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteVagaService {

    private final ClienteVagaRepository clienteVagaRepository;

    @Transactional
    public ClienteVaga salvar(ClienteVaga clienteVaga) {
        return clienteVagaRepository.save(clienteVaga);
    }

    @Transactional
    @ReadOnlyProperty
    public ClienteVaga buscarPorRecibo(String recibo) {
            return clienteVagaRepository.findByReciboAndDataSaidaIsNull(recibo).orElseThrow(
                    () -> new EntityNotFoundException(String.format("Recibo %s não encontrado no sistema ou check-out já realizado", recibo))
            );
    }
}
