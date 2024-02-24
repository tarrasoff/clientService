package com.example.clientService.service;

import com.example.clientService.dto.ContactDto;
import com.example.clientService.entity.client.Client;
import com.example.clientService.entity.contact.Contact;
import com.example.clientService.entity.contact.ContactType;
import com.example.clientService.exception.EntityNotFoundException;
import com.example.clientService.mapper.ContactMapper;
import com.example.clientService.repository.ClientRepository;
import com.example.clientService.repository.ContactRepository;
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
public class ContactService {
    private final ContactRepository contactRepository;
    private final ClientRepository clientRepository;
    private final ContactMapper contactMapper;

    @Transactional
    public ContactDto addContact(UUID id, ContactDto contactDto) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Client not found"));
        log.debug("Find client by id: {}", id);
        Contact contact = contactMapper.toEntity(contactDto);
        log.debug("Create contact: {}", contactDto);
        if (contactDto.getType() == ContactType.EMAIL) {
            contact.setType(ContactType.EMAIL);
        } else {
            contact.setType(ContactType.PHONE);
        }
        log.debug("Set contact type: {}", contact.getType());
        contact.setClient(client);
        log.debug("Save contact: {}", contact);
        return contactMapper.toDto(contactRepository.save(contact));
    }

    @Transactional(readOnly = true)
    public List<ContactDto> getContactsByClientId(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Client not found"));
        log.debug("Find client by id: {}", id);
        List<Contact> contacts = client.getContacts();
        log.debug("Find contacts by client id: {}", id);
        return contacts.stream().map(contactMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ContactDto> getContactsByTypeByClientId(UUID id, ContactType contactType) {
        List<Contact> contacts = contactRepository.findByClientIdAndType(id, contactType);
        log.debug("Find contacts by client id: {}", id);
        return contacts.stream().map(contactMapper::toDto).collect(Collectors.toList());
    }
}