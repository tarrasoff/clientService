package com.example.clientService.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Client DTO")
public class ClientDto {

    @Schema(description = "Client ID")
    private UUID id;

    @Schema(description = "Client ID")
    @NotEmpty(message = "Name can not be empty")
    private String name;
}