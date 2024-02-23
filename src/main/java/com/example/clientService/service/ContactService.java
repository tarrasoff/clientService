package com.example.clientService.service;

import com.example.clientService.dto.ContactDto;
import com.example.clientService.entity.client.Client;
import com.example.clientService.entity.contact.Contact;
import com.example.clientService.entity.contact.ContactType;
import com.example.clientService.mapper.ContactMapper;
import com.example.clientService.repository.ClientRepository;
import com.example.clientService.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;
    private final ClientRepository clientRepository;
    private final ContactMapper contactMapper;

    @Transactional
    public ContactDto addContact(UUID id, ContactDto contactDto) {
        Client client = clientRepository.findById(id).orElseThrow();
        Contact contact = contactMapper.toEntity(contactDto);
        contact.setClient(client);
        return contactMapper.toDto(contactRepository.save(contact));
    }

    @Transactional(readOnly = true)
    public List<ContactDto> getContactsByClientId(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow();
        List<Contact> contacts = client.getContacts();
        return contacts.stream().map(contactMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ContactDto> getContactsByTypeByClientId(UUID id, ContactType contactType) {
        List<Contact> contacts = contactRepository.findByClientIdAndType(id, contactType);
        return contacts.stream().map(contactMapper::toDto).collect(Collectors.toList());
    }
}