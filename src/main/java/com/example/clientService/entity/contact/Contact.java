package com.example.clientService.entity.contact;

import com.example.clientService.entity.client.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(name = "contact", length = 128, nullable = false, unique = true)
    private String contact;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType type;
}