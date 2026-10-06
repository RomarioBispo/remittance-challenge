package com.challenge.remitance_api.infrastructure.gateway;

import com.challenge.remitance_api.businessrule.domain.Person;
import com.challenge.remitance_api.businessrule.gateway.PersonGateway;
import com.challenge.remitance_api.infrastructure.exception.PersonIdNotFoundExceptionException;
import com.challenge.remitance_api.infrastructure.mapper.PersonMapper;
import com.challenge.remitance_api.infrastructure.repository.PersonRepository;
import com.challenge.remitance_api.infrastructure.repository.entity.PersonEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PersonGatewayImpl implements PersonGateway {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public Person findPersonById(Integer personId) {
        Optional<PersonEntity> personEntity = personRepository.findById(personId);
        if (!personEntity.isPresent()){
            throw new PersonIdNotFoundExceptionException("Person id not found");
        }
        return personMapper.toDomain(personEntity.get());
    }
}
