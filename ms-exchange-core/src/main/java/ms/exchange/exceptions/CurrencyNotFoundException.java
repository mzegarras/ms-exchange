package ms.exchange.exceptions;

public class CurrencyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = -2116834756848174143L;

    public CurrencyNotFoundException (String msg) {
        super(msg);
    }
}
