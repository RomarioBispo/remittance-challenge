package com.challenge.remitance_api.infrastructure.gateway.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuoteReponse {
    private List<DollarQuote> value;
}
