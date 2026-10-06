package com.challenge.remitance_api.infrastructure.repository;

import com.challenge.remitance_api.infrastructure.repository.entity.DollarQuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface DollarQuoteRepository extends JpaRepository<DollarQuoteEntity, Integer> {
   @Query("select dq.value from dollar_quote dq where dq.quoteDate = :date")
   Optional<Double> findByDate(LocalDate date);

   @Query("select dq.value from dollar_quote dq order by dq.quoteDate desc limit 1")
   Optional<Double> findMostRecentDollarQuote();
}
