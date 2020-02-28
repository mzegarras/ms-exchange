package ms.exchange.controller.web;

import com.neovisionaries.i18n.CurrencyCode;
import ms.exchange.domain.Exchange;
import ms.exchange.dto.ExchangeDto;
import ms.exchange.exceptions.CurrencyNotFoundException;
import ms.exchange.service.ExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@ContextConfiguration(classes = {ExchangeControllerTest.TestConfiguration.class})
public class ExchangeControllerTest {

    public static class TestConfiguration {
        @Bean
        public ExchangeController controller(ExchangeService service) {
            return new ExchangeController(service);
        }
    }

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ExchangeService service;

    @BeforeEach
    void cleanCachesAndMocks() {
        reset(service);
    }

    @Test
    void exchnage_amount_not_found() {
        // 1: Data

        // 2: Mocks & Stubs configuration
        when(service.getExchange(CurrencyCode.PEN,CurrencyCode.USD,100)).thenReturn(Mono.error(new CurrencyNotFoundException("Currency not found")));

        // 3: Business logic execution
        webTestClient.get().uri("/exchanges/PEN/destinies/USD?amount=100")
                .exchange()
                .expectStatus().isNotFound();

        // 4: Validating mocks behaviour
        Mockito.verify(service).getExchange(CurrencyCode.PEN,CurrencyCode.USD,100);
        Mockito.verifyNoMoreInteractions(service);

        // 5: Validating results
    }
    @Test
    void exchnage_amount_ok() {

        // 1: Data
        Exchange exchange = new Exchange();
        exchange.setOrigin(CurrencyCode.PEN);
        exchange.setDestiny(CurrencyCode.USD);
        exchange.setExchangeRate(3d);
        exchange.setAmount(100);
        exchange.setAmountExchange(300);
        ExchangeDto exchangeDto = new ExchangeDto(exchange);

        // 2: Mocks & Stubs configuration
        when(service.getExchange(CurrencyCode.PEN,CurrencyCode.USD,100)).thenReturn(Mono.just(exchangeDto));

        // 3: Business logic execution
        webTestClient.get().uri("/exchanges/PEN/destinies/USD?amount=100")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.exchange.origin").isEqualTo("PEN")
                .jsonPath("$.exchange.destiny").isEqualTo("USD")
                .jsonPath("$.exchange.exchangeRate").isEqualTo(3)
                .jsonPath("$.exchange.amount").isEqualTo(100)
                .jsonPath("$.exchange.amountExchange").isEqualTo(300);

        // 4: Validating mocks behaviour
        Mockito.verify(service).getExchange(CurrencyCode.PEN,CurrencyCode.USD,100);
        Mockito.verifyNoMoreInteractions(service);

        // 5: Validating results

    }




}
