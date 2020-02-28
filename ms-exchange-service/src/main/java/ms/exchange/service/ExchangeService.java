package ms.exchange.service;


import com.neovisionaries.i18n.CurrencyCode;
import ms.exchange.domain.Exchange;
import ms.exchange.dto.ExchangeDto;
import reactor.core.publisher.Mono;

public interface ExchangeService {

    Mono<ExchangeDto> getExchange(CurrencyCode origin, CurrencyCode destiny, double amount);
    ExchangeDto calculate(final ExchangeDto exchangeDto);
    Mono<Integer> save(Exchange exchange);
}
