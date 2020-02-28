package ms.exchange.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import ms.exchange.domain.Exchange;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExchangeDto implements Serializable {
    private static final long serialVersionUID = 5461539344870300678L;

    @NotNull
    private Exchange exchange;

    public ExchangeDto(Exchange exchange) {
        this.exchange = exchange;
    }

}
