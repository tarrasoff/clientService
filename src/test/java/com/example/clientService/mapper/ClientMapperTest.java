package com.example.clientService.mapper;

import com.example.clientService.dto.ClientDto;
import com.example.clientService.entity.client.Client;
import com.example.clientService.entity.contact.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientMapperTest {
    @Spy
    private ClientMapperImpl clientMapperImpl;
    private ClientDto clientDto;
    private Client client;
    @Mock
    private List<Contact> contacts;

    @BeforeEach
    void setUp() {
        clientDto = ClientDto.builder()
                .name("name")
                .build();
        client = Client.builder()
                .id(UUID.randomUUID())
                .name("name")
                .contacts(contacts)
                .build();
    }

    @Test
    void toDto() {
        ClientDto clientDto = clientMapperImpl.toDto(client);
        assertEquals(clientDto.getName(), client.getName());
    }

    @Test
    void toEntity() {
        Client client = clientMapperImpl.toEntity(clientDto);
        assertEquals(clientDto.getName(), client.getName());
    }

}