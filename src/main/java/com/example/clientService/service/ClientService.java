package com.example.clientService.service;

import com.example.clientService.dto.ClientDto;
import com.example.clientService.entity.client.Client;
import com.example.clientService.mapper.ClientMapper;
import com.example.clientService.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Transactional
    public ClientDto createClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        return clientMapper.toDto(clientRepository.save(client));
    }

    @Transactional(readOnly = true)
    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto getClientById(UUID id) {
        return clientMapper.toDto(clientRepository.findById(id).orElseThrow());
    }
}