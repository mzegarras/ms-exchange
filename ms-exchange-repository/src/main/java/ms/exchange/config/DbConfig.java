package ms.exchange.config;

import io.r2dbc.spi.ConnectionFactory;
import com.github.jasync.r2dbc.mysql.JasyncConnectionFactory;
import com.github.jasync.sql.db.Configuration;
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.core.DatabaseClient;

@org.springframework.context.annotation.Configuration
@AllArgsConstructor
public class DbConfig {

    private DbProperties dbProperties;

    @Bean
    public ConnectionFactory getConnectionFactory(){

        Configuration configuration = new Configuration(dbProperties.getUserName(),
                                                    dbProperties.getHost(),
                                                    dbProperties.getPort(),
                                                    dbProperties.getPassword(),
                                                    dbProperties.getDatabase());

        ConnectionFactory connectionFactory = new JasyncConnectionFactory(new MySQLConnectionFactory(configuration));

        return connectionFactory;
    }

    @Bean
    public DatabaseClient getDatabaseClient(ConnectionFactory getConnectionFactory){
        DatabaseClient client = DatabaseClient.create(getConnectionFactory);
        return client;
    }


}
