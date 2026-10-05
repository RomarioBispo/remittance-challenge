package com.challenge.remitance_api.infrastructure.gateway;

import com.challenge.remitance_api.businessrule.gateway.CurrentQuoteGateway;
import com.challenge.remitance_api.infrastructure.config.ParametersConfig;
import com.challenge.remitance_api.infrastructure.gateway.response.DollarQuote;
import com.challenge.remitance_api.infrastructure.gateway.response.QuoteReponse;
import com.challenge.remitance_api.infrastructure.repository.DollarQuoteRepository;
import com.challenge.remitance_api.infrastructure.repository.entity.DollarQuoteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CurrentQuoteGatewayImpl implements CurrentQuoteGateway {
    private final DollarQuoteRepository dollarQuoteRepository;
    private final RestClient.Builder restClientBuilder;
    private final ParametersConfig parametersConfig;

    @Override
    //TODO: refactor this method later
    public Double getQuote() {

        LocalDate now = LocalDate.now();

        Optional<Double> dollarQuote = dollarQuoteRepository.findByDate(now);
        if (!dollarQuote.isPresent()){

            RestClient restClient = restClientBuilder.baseUrl(parametersConfig.getBaseURL()).build();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

            ResponseEntity<QuoteReponse> response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path(parametersConfig.getPath())
                            .queryParam("@dataCotacao", "'"+now.format(formatter)+"'")
                            .queryParam("$top", 1)
                            .queryParam("$format", "json")
                            .build())
                    .retrieve()
                    .toEntity(QuoteReponse.class);

            List<DollarQuote> body = response.getBody()!=null? response.getBody().getValue(): List.of();

            Optional<Double> mostRecentDollarQuote = dollarQuoteRepository.findMostRecentDollarQuote();
            if (body.isEmpty() && mostRecentDollarQuote.isPresent()) {
                return mostRecentDollarQuote.get();
            }

            Double cotacaoCompra = body.get(0).getCotacaoCompra();

            DollarQuoteEntity dollarQuoteEntity = DollarQuoteEntity.builder()
                    .value(cotacaoCompra)
                    .build();

            dollarQuoteRepository.save(dollarQuoteEntity);
            return cotacaoCompra;
        }
        return dollarQuote.get();
    }
}
