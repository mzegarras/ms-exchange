package ms.exchange.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neovisionaries.i18n.CurrencyCode;
import lombok.Data;

import java.io.Serializable;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Exchange implements Serializable {
    private static final long serialVersionUID = 3621291528457456564L;

    private CurrencyCode origin;
    private CurrencyCode destiny;
    private Double exchangeRate;

    private double amount;
    private double amountExchange;
}
