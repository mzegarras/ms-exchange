

# MS-Exchange MicroService

## Prerequisites

**Software**
* Java SDK 11.x or higher
* Maven 3.x or higher
* Docker 17.12.x or higher
* Docker Compose: 1.18.x or higher

## Building

```
mvn compile
```

## Testing

```
mvn test              # Run test cases
mvn verify            # Run test cases & static code validations & package
```

## Packaging

**Executable Jar**

```
mvn verify
```

**Container**

```
mvn verify
docker-compose build
docker-compose push ms
docker-compose up
```

**Test**


```
curl --location --request GET 'http://localhost:8080/exchanges/USD/destinies/PEN?amount=200'

```

```
curl --location --request POST 'http://localhost:8080/exchanges' \
--header 'Content-Type: application/json' \
--data-raw '{
    "exchange": {
        "origin": "USD",
        "destiny": "PEN",
        "exchangeRate": 4.0
    }
}'
```

**Load**


```
siege -r 1 -c 20 -v "http://localhost:8080/exchanges/USD/destinies/PEN?amount=200"
```

