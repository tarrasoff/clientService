package com.example.clientService.mapper;

import com.example.clientService.dto.ContactDto;
import com.example.clientService.entity.client.Client;
import com.example.clientService.entity.contact.Contact;
import com.example.clientService.entity.contact.ContactType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContactMapperTest {
    @Spy
    private ContactMapperImpl contactMapperImpl;
    private ContactDto contactDto;
    private Contact contact;
    @Mock
    private Client client;

    @BeforeEach
    void setUp() {
        contactDto = ContactDto.builder()
                .contact("contact@gmail.com")
                .type(ContactType.EMAIL)
                .build();
        contact = Contact.builder()
                .id(UUID.randomUUID())
                .client(client)
                .contact("contact@gmail.com")
                .type(ContactType.EMAIL)
                .build();
    }

    @Test
    void toDto() {
        ContactDto contactDto = contactMapperImpl.toDto(contact);
        assertEquals(contactDto.getContact(), contact.getContact());
        assertEquals(contactDto.getType(), contact.getType());
    }

    @Test
    void toEntity() {
        Contact contact = contactMapperImpl.toEntity(contactDto);
        assertEquals(contactDto.getContact(), contact.getContact());
        assertEquals(contactDto.getType(), contact.getType());
    }
}