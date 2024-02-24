package com.example.clientService.controller.client;

import com.example.clientService.dto.ClientDto;
import com.example.clientService.service.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientControllerTest {
    @InjectMocks
    private ClientController controller;
    @Mock
    private ClientService service;
    @Mock
    private ClientDto clientDto;
    @Mock
    private UUID id;

    @Test
    void testCreateClient() {
        controller.createClient(clientDto);
        verify(service, times(1)).createClient(clientDto);
    }

    @Test
    void testGetAllClients() {
        controller.getAllClients();
        verify(service, times(1)).getAllClients();
    }

    @Test
    void testGetClientById() {
        controller.getClientById(id);
        verify(service, times(1)).getClientById(id);
    }
}