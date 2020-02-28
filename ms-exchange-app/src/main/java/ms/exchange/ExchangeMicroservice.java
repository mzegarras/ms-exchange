package ms.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;


@SpringBootApplication
public class ExchangeMicroservice
{
    public static void main(String[] args) throws IOException {
        SpringApplication.run(ExchangeMicroservice.class, args);
    }

}
