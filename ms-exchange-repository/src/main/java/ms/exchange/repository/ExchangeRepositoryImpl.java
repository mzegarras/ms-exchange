package ms.exchange.repository;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import ms.exchange.domain.Exchange;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Repository
@AllArgsConstructor
public class ExchangeRepositoryImpl implements ExchangeRepository {

    private DatabaseClient client;


    @Override
    public Mono<Exchange> getExchange(CurrencyCode origin, CurrencyCode destiny) {

        return client
                .execute("call SearchExchange(:p_origin,:p_destiny)")
                .bind("p_origin", origin.getCurrency().getCurrencyCode())
                .bind("p_destiny", destiny.getCurrency().getCurrencyCode())
                .map((row, rowMetadata) ->{
                    Exchange exchange = new Exchange();
                    exchange.setOrigin(CurrencyCode.getByCode(row.get("origin", String.class)));
                    exchange.setDestiny(CurrencyCode.getByCode(row.get("destiny", String.class)));
                    exchange.setExchangeRate(row.get("rate", Double.class));
                    return exchange;
                })
                .one()
                .switchIfEmpty(Mono.error(new RuntimeException("Pet not found!")));

    }

    @Override
    public Mono<Integer> save(Exchange exchange) {
        return client
                .execute("call InsertExchange(:p_origin,:p_destiny,:p_rate)")
                .bind("p_origin", exchange.getOrigin().getCurrency().getCurrencyCode())
                .bind("p_destiny", exchange.getDestiny().getCurrency().getCurrencyCode())
                .bind("p_rate", exchange.getExchangeRate())
                .fetch().rowsUpdated();

    }
}
