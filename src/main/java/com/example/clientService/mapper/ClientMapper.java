package com.example.clientService.mapper;

import com.example.clientService.dto.ClientDto;
import com.example.clientService.entity.client.Client;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {

    Client toEntity(ClientDto clientDto);

    ClientDto toDto(Client client);
}