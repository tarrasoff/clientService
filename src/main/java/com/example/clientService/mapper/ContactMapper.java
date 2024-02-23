package com.example.clientService.mapper;

import com.example.clientService.dto.ContactDto;
import com.example.clientService.entity.contact.Contact;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    Contact toEntity(ContactDto contactDto);

    ContactDto toDto(Contact contact);
}