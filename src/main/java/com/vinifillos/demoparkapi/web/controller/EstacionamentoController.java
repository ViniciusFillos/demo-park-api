package com.vinifillos.demoparkapi.web.controller;

import com.vinifillos.demoparkapi.entity.ClienteVaga;
import com.vinifillos.demoparkapi.service.EstacionamentoService;
import com.vinifillos.demoparkapi.web.dto.EstacionamentoCreateDto;
import com.vinifillos.demoparkapi.web.dto.EstacionamentoResponseDto;
import com.vinifillos.demoparkapi.web.dto.mapper.ClienteVagaMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/v1/estacionamentos")
@RequiredArgsConstructor
public class EstacionamentoController {

    private final EstacionamentoService estacionamentoService;

    @PostMapping("/check-in")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EstacionamentoResponseDto> checkIn(@RequestBody @Valid EstacionamentoCreateDto dto) {
        ClienteVaga clieteVaga = ClienteVagaMapper.toClienteVaga(dto);
        estacionamentoService.checkIn(clieteVaga);
        EstacionamentoResponseDto responseDto = ClienteVagaMapper.toDto(clieteVaga);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{recibo}")
                .buildAndExpand(clieteVaga.getRecibo())
                .toUri();
        return ResponseEntity.created(location).body(responseDto);

    }
}
