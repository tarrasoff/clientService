package com.example.clientService.controller.contact;

import com.example.clientService.dto.ContactDto;
import com.example.clientService.entity.contact.ContactType;
import com.example.clientService.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Контакты", description = "Добавление и получение контактов")
public class ContactController {
    private final ContactService contactService;

    @Operation(summary = "Добавление контакта")
    @PostMapping("/{id}/contact")
    public ContactDto addContact(@Parameter(description = "Client ID", example = "1")
                                 @NotNull
                                 @PathVariable UUID id,
                                 @Valid
                                 @RequestBody ContactDto contactDto) {
        return contactService.addContact(id, contactDto);
    }

    @Operation(summary = "Получение контактов клиента по id")
    @GetMapping("/{id}/contacts")
    public List<ContactDto> getContactsByClientId(@Parameter(description = "Client ID", example = "1")
                                                  @NotNull
                                                  @PathVariable UUID id) {
        return contactService.getContactsByClientId(id);
    }

    @Operation(summary = "Получение контактов клиента по id и типу контакта")
    @GetMapping("/{id}/contacts/{contactType}")
    public List<ContactDto> getContactsByTypeByClientId(@Parameter(description = "Client ID", example = "1")
                                                        @NotNull
                                                        @PathVariable UUID id,
                                                        @Parameter(description = "Contact type", example = "PHONE")
                                                        @NotNull
                                                        @PathVariable ContactType contactType) {
        return contactService.getContactsByTypeByClientId(id, contactType);
    }
}