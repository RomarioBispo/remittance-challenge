package com.challenge.remitance_api.businessrule.gateway;

import com.challenge.remitance_api.businessrule.domain.Person;

public interface PersonGateway {
    Person findPersonById(Integer personId);
}
