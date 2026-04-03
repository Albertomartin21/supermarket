package commercadona.supermarket.domain.exception;

public class WorkerNotFoundErrorException extends NotFoundException {
      public WorkerNotFoundErrorException(String msg) {
        super(msg);
    }
}
