package com.challenge.remitance_api.infrastructure.gateway.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DollarQuote {
       private Double cotacaoCompra;
       private Double cotacaoVenda;
       private String dataHoraCotacao;
}