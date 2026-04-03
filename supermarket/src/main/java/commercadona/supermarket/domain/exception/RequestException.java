package commercadona.supermarket.domain.exception;

public class RequestException extends RuntimeException {
    public RequestException (String msg) {
        super(msg);
    }
}
