package com.challenge.remitance_api.infrastructure.gateway;

import com.challenge.remitance_api.businessrule.domain.Person;
import com.challenge.remitance_api.businessrule.gateway.PersonGateway;
import com.challenge.remitance_api.infrastructure.mapper.PersonMapper;
import com.challenge.remitance_api.infrastructure.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonGatewayImpl implements PersonGateway {
    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public Person findPersonById(Integer personId) {
        return personMapper.toDomain(personRepository.findPersonById(personId));
    }
}
