package com.example.clientService.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Client DTO")
public class ClientDto {

    @Schema(description = "Client name", example = "Andrey")
    @NotEmpty(message = "Name can not be empty")
    @NotNull(message = "Name can not be null")
    @Size(min = 1, max = 128, message = "Name must be between 1 and 128 characters")
    private String name;
}