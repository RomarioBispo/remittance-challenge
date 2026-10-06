package com.challenge.remitance_api.infrastructure.mapper;

import com.challenge.remitance_api.businessrule.domain.Person;
import com.challenge.remitance_api.infrastructure.repository.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {
    PersonEntity toEntity(Person person);
    Person toDomain(PersonEntity personEntity);
}
