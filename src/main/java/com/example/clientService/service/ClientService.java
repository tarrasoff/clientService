package com.example.clientService.service;

import com.example.clientService.dto.ClientDto;
import com.example.clientService.entity.client.Client;
import com.example.clientService.exception.EntityNotFoundException;
import com.example.clientService.mapper.ClientMapper;
import com.example.clientService.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Transactional
    public ClientDto createClient(ClientDto clientDto) {
        Client client = clientMapper.toEntity(clientDto);
        log.debug("Create client: {}", clientDto);
        Client savedClient = clientRepository.save(client);
        log.debug("Save client: {}", savedClient);
        return clientMapper.toDto(savedClient);
    }

    @Transactional(readOnly = true)
    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        log.debug("Find all clients");
        return clients.stream().map(clientMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClientDto getClientById(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Client not found"));
        log.debug("Find client by id: {}", id);
        return clientMapper.toDto(client);
    }
}