package com.challenge.remitance_api.infrastructure.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="dollar_quote")
@Builder
public class DollarQuoteEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private Double value;
    private LocalDateTime quoteDate;
}
