package com.challenge.remitance_api.infrastructure.repository;

import com.challenge.remitance_api.infrastructure.repository.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {
}
