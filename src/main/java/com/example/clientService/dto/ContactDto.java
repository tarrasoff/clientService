package com.example.clientService.dto;

import com.example.clientService.entity.contact.ContactType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Contact DTO")
public class ContactDto {

    @Schema(description = "Contact")
    @NotEmpty(message = "Contact can not be empty")
    @NotNull(message = "Contact can not be null")
    private String contact;

    @Schema(description = "Type")
    @NotEmpty(message = "Type can not be empty")
    private ContactType type;
}