package com.example.clientService.entity.client;

import com.example.clientService.entity.contact.Contact;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name = "name", length = 128, nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "client")
    private List<Contact> contacts;
}
