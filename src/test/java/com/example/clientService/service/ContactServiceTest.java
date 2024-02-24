package com.example.clientService.service;

import com.example.clientService.dto.ContactDto;
import com.example.clientService.entity.client.Client;
import com.example.clientService.entity.contact.Contact;
import com.example.clientService.entity.contact.ContactType;
import com.example.clientService.exception.EntityNotFoundException;
import com.example.clientService.mapper.ContactMapperImpl;
import com.example.clientService.repository.ClientRepository;
import com.example.clientService.repository.ContactRepository;
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
class ContactServiceTest {

    @InjectMocks
    private ContactService service;
    @Mock
    private ContactRepository contactRepository;
    @Mock
    private ClientRepository clientRepository;
    @Spy
    private ContactMapperImpl contactMapperImpl;
    private ContactDto contactDto;
    private Contact contact;
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

        client = Client.builder()
                .id(UUID.randomUUID())
                .name("name")
                .contacts(List.of(contact))
                .build();
    }

    @Test
    void testAddContact() {
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        when(contactRepository.save(any(Contact.class))).thenReturn(contact);

        ContactDto contactDtoResponse = service.addContact(client.getId(), contactDto);

        verify(contactRepository, times(1)).save(any(Contact.class));
        verify(clientRepository, times(1)).findById(any(UUID.class));
        assertEquals(contactDto.getContact(), contactDtoResponse.getContact());
    }

    @Test
    void testAddContactWhenClientNotFound() {
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> service.addContact(client.getId(), contactDto), "Client not found");
    }

    @Test
    void testGetContactsByClientId() {
        Client clientMock = mock(Client.class);
        Contact contactMock = mock(Contact.class);
        when(clientRepository.findById(any(UUID.class))).thenReturn(Optional.of(client));
        when(clientMock.getContacts()).thenReturn(List.of(contactMock));

        List<ContactDto> contacts = service.getContactsByClientId(client.getId());

        verify(clientRepository, times(1)).findById(any(UUID.class));
        assertEquals(1, contacts.size());
    }

    @Test
    void testGetContactsByTypeByClientId() {
        when(contactRepository.findByClientIdAndType(any(UUID.class), any(ContactType.class))).thenReturn(List.of(contact));

        List<ContactDto> contacts = service.getContactsByTypeByClientId(client.getId(), ContactType.EMAIL);

        verify(contactRepository, times(1)).findByClientIdAndType(any(UUID.class), any(ContactType.class));
        assertEquals(1, contacts.size());
    }
}