package ms.exchange.controller.web;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ms.exchange.controller.web.dto.ExchangeWebDto;
import ms.exchange.exceptions.CurrencyNotFoundException;
import ms.exchange.service.ExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;


@RestController
@RequestMapping("/exchanges")
@AllArgsConstructor
@Slf4j
public class ExchangeController {

    private final ExchangeService exchangeService;


    @PostMapping
    public Mono<ResponseEntity<ExchangeWebDto>> create(@Valid @RequestBody ExchangeWebDto exchangeWebDto) {


        return exchangeService.save(exchangeWebDto.getExchange())
                .map(dto->ResponseEntity.ok(new ExchangeWebDto(exchangeWebDto.getExchange())));

    }

    @GetMapping(path = "/{origin}/destinies/{destiny}")
    public Mono<ResponseEntity<ExchangeWebDto>> get(@PathVariable(name = "origin") CurrencyCode origin,
                                                    @PathVariable(name = "destiny") CurrencyCode destiny,
                                                    @RequestParam(value = "amount") double amount){



        return exchangeService.getExchange(origin,destiny,amount)
                .map(dto->ResponseEntity.ok(new ExchangeWebDto(dto.getExchange())))
                .onErrorResume(throwable -> throwable instanceof CurrencyNotFoundException,
                        throwable -> Mono.just(new ResponseEntity<>(HttpStatus.NOT_FOUND)));
    }

}
