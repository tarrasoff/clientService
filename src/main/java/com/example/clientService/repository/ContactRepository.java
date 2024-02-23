package com.example.clientService.repository;

import com.example.clientService.entity.contact.Contact;
import com.example.clientService.entity.contact.ContactType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID> {
    @Query(nativeQuery = true, value = """
            SELECT * FROM contacts 
            WHERE client_id = :id
            AND type = :contactType""")
    List<Contact> findByClientIdAndType(UUID id, ContactType contactType);
}