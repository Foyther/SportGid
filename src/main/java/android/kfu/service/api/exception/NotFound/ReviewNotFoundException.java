package android.kfu.service.api.exception.NotFound;

/**
 * Created by Nurislam on 20.12.2017.
 */
public class ReviewNotFoundException extends Exception {
    public ReviewNotFoundException() {
        super();
    }

    public ReviewNotFoundException(String message) {
        super(message);
    }

    public ReviewNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReviewNotFoundException(Throwable cause) {
        super(cause);
    }
}
