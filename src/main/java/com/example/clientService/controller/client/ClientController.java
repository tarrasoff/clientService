package com.example.clientService.controller.client;

import com.example.clientService.dto.ClientDto;
import com.example.clientService.service.ClientService;
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
@RequestMapping("/clients")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Клиенты", description = "Добавление и получение клиентов")
public class ClientController {
    private final ClientService clientService;

    @Operation(summary = "Добавление клиента")
    @PostMapping()
    public ClientDto createClient(@Valid @RequestBody ClientDto clientDto) {
        log.debug("createClient: {}", clientDto);
        return clientService.createClient(clientDto);
    }

    @Operation(summary = "Получение всех клиентов")
    @GetMapping("/allClients")
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @Operation(summary = "Получение клиента по id")
    @GetMapping("/{id}")
    public ClientDto getClientById(@Parameter(description = "Client ID", example = "1")
                                   @NotNull
                                   @PathVariable UUID id) {
        return clientService.getClientById(id);
    }
}