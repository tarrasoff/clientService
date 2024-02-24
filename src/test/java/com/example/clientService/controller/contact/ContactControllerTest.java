package com.example.clientService.controller.contact;

import com.example.clientService.dto.ContactDto;
import com.example.clientService.entity.contact.ContactType;
import com.example.clientService.service.ContactService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ContactControllerTest {
    @InjectMocks
    private ContactController controller;
    @Mock
    private ContactService service;
    @Mock
    private ContactDto contactDto;
    @Mock
    private UUID id;
    @Mock
    private ContactType contactType;

    @Test
    void testAddContact() {
        controller.addContact(id, contactDto);
        verify(service, times(1)).addContact(id, contactDto);
    }

    @Test
    void testGetContactsByClientId() {
        controller.getContactsByClientId(id);
        verify(service, times(1)).getContactsByClientId(id);
    }

    @Test
    void testGetContactsByTypeByClientId() {
        controller.getContactsByTypeByClientId(id, contactType);
        verify(service, times(1)).getContactsByTypeByClientId(id, contactType);
    }
}