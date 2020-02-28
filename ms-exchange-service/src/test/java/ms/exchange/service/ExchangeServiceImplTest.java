package ms.exchange.service;

import com.neovisionaries.i18n.CurrencyCode;
import ms.exchange.domain.Exchange;
import ms.exchange.dto.ExchangeDto;
import ms.exchange.repository.ExchangeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ExchangeServiceImplTest.TestConfiguration.class})
public class ExchangeServiceImplTest {

    public static class TestConfiguration {

        @Bean
        public ExchangeService getExchangeService(ExchangeRepository exchangeRepository){
            ExchangeServiceImpl service = new ExchangeServiceImpl(exchangeRepository);
            return service;
        }

    }

    @MockBean
    private ExchangeRepository repository;

    @Autowired
    private ExchangeService service;

    @BeforeEach
    void cleanCachesAndMocks() {

        reset(repository);
    }

    @Test
    void calculate_exchange_ok() {

        // 1: Data
        Exchange exchange = new Exchange();
        exchange.setOrigin(CurrencyCode.PEN);
        exchange.setDestiny(CurrencyCode.USD);
        exchange.setExchangeRate(3d);


        // 2: Mocks & Stubs configuration
        when(repository.getExchange(CurrencyCode.PEN,CurrencyCode.USD)).thenReturn(Mono.just(exchange));

        // Business logic execution
        ExchangeDto dto = service.getExchange(CurrencyCode.PEN,CurrencyCode.USD,100)
                            .block();

        // 4: Validating mocks behaviour
        Mockito.verify(repository).getExchange(CurrencyCode.PEN,CurrencyCode.USD);
        Mockito.verifyNoMoreInteractions(repository);

        // Validating results
        assertEquals(300, dto.getExchange().getAmountExchange());
    }

    @Test
    void calculate_calculate_ok() {
        // Preparing data
        Exchange exchange = new Exchange();
        exchange.setOrigin(CurrencyCode.PEN);
        exchange.setDestiny(CurrencyCode.USD);
        exchange.setExchangeRate(2d);
        exchange.setAmount(100);
        ExchangeDto exchangeDto = new ExchangeDto(exchange);

        // Mocks & Stubs configuration

        // Business logic execution
        service.calculate(exchangeDto);

        // Validating mocks behaviour

        assertEquals(200, exchange.getAmountExchange());

        // Validating results

    }

}
