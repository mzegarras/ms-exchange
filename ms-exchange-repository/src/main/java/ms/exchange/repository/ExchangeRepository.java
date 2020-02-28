package ms.exchange.repository;

import com.neovisionaries.i18n.CurrencyCode;
import ms.exchange.domain.Exchange;
import reactor.core.publisher.Mono;

public interface ExchangeRepository {

    Mono<Exchange> getExchange(CurrencyCode origin, CurrencyCode destiny);
    Mono<Integer> save(Exchange exchange);
}
