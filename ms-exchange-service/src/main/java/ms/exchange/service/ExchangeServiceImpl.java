package ms.exchange.service;


import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.exchange.domain.Exchange;
import ms.exchange.dto.ExchangeDto;
import ms.exchange.repository.ExchangeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    private ExchangeRepository repository;

    @Override
    public Mono<ExchangeDto> getExchange(CurrencyCode origin, CurrencyCode destiny, double amount){
        return repository.getExchange(origin,destiny)
                .map(entity->{
                            ExchangeDto dto = new ExchangeDto(entity);
                            dto.getExchange().setAmount(amount);
                            return dto;
                    })
                .map(this::calculate);
    }

    public ExchangeDto calculate(final ExchangeDto exchangeDto) {
        Exchange exchange = exchangeDto.getExchange();
        exchange.setAmountExchange(exchange.getAmount() * exchange.getExchangeRate());
        return exchangeDto;
    }

    @Override
    public Mono<Integer> save(Exchange exchange) {
        return repository.save(exchange);
    }
}
