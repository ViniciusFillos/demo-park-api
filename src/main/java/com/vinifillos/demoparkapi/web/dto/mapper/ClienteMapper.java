package com.vinifillos.demoparkapi.web.dto.mapper;

import com.vinifillos.demoparkapi.entity.Cliente;
import com.vinifillos.demoparkapi.web.dto.ClienteCreateDto;
import com.vinifillos.demoparkapi.web.dto.ClienteResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClienteMapper {


    public static Cliente toCliente(ClienteCreateDto dto) {
        return new ModelMapper().map(dto, Cliente.class);
    }
    public static ClienteResponseDto toDto(Cliente dto) {
        return new ModelMapper().map(dto, ClienteResponseDto.class);
    }
}
