package com.example.clientService.service;

import com.example.clientService.dto.ClientDto;
import com.example.clientService.entity.client.Client;
import com.example.clientService.entity.contact.Contact;
import com.example.clientService.entity.contact.ContactType;
import com.example.clientService.exception.EntityNotFoundException;
import com.example.clientService.mapper.ClientMapperImpl;
import com.example.clientService.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ClientServiceTest {
    @InjectMocks
    private ClientService service;
    @Mock
    private ClientRepository clientRepository;
    @Spy
    private ClientMapperImpl clientMapperImpl;
    private Client client;
    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        Contact contact = Contact.builder()
                .id(UUID.randomUUID())
                .client(client)
                .contact("contact@gmail.com")
                .type(ContactType.EMAIL)
                .build();

        client = Client.builder()
                .id(UUID.randomUUID())
                .name("name")
                .contacts(List.of(contact))
                .build();

        clientDto = ClientDto.builder()
                .name("name")
                .build();
    }

    @Test
    void testCreateClient() {
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        ClientDto savedClient = service.createClient(clientDto);

        verify(clientRepository, times(1)).save(any(Client.class));
        assertEquals(clientDto.getName(), savedClient.getName());
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));
        List<ClientDto> allClients = service.getAllClients();
        verify(clientRepository, times(1)).findAll();
        assertEquals(1, allClients.size());
    }

    @Test
    void testGetClientById() {
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        ClientDto clientDtoResponse = service.getClientById(client.getId());
        verify(clientRepository, times(1)).findById(any(UUID.class));
        assertEquals(clientDto.getName(), clientDtoResponse.getName());
    }

    @Test
    void testGetClientByIdWhenClientNotFound() {
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.getClientById(client.getId()), "Client not found");
    }
}