package ms.exchange.controller.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ms.exchange.domain.Exchange;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@NoArgsConstructor
public class ExchangeWebDto  implements Serializable {
    private static final long serialVersionUID = -820155032339374627L;

    @NotNull
    private Exchange exchange;

    public ExchangeWebDto(Exchange exchange) {
        this.exchange = exchange;
    }


}
