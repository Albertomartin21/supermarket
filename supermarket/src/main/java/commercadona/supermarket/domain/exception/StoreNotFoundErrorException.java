package commercadona.supermarket.domain.exception;

public class StoreNotFoundErrorException  extends NotFoundException {

    public StoreNotFoundErrorException(String msg) {
        super(msg);
    }
}
