package ms.exchange.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("ms.exchanges.db")
public class DbProperties {

    private String host;
    private Integer port;
    private String database;
    private String userName;
    private String password;

}
